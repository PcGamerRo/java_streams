import Classes.Examen;

import java.io.*;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        //2
        Map<String, Examen> examene;
        FileReader fr = new FileReader("./Data/in.txt");
        BufferedReader br = new BufferedReader(fr);
        examene = br.lines()
                .map(e->{
                    Date dataEx;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        dataEx = dateFormat.parse(e.split("\t")[0]);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }

                    return new Examen(
                            dataEx,
                            e.split("\t")[1],
                            e.split("\t")[2],
                            Integer.parseInt(e.split("\t")[3]),
                            Integer.parseInt(e.split("\t")[4])
                    );
                })
                .collect(Collectors.toMap(Examen::getDisciplina, Function.identity()));

        examene.entrySet().stream().forEach(System.out::println);

        System.out.print("\nNr total de examene: ");
        System.out.println(examene.entrySet().stream().count());
        //3
        var absenteism = examene.values().stream()
                .collect(Collectors.groupingBy(Examen::getData));

        absenteism.entrySet().stream().forEach(
                e->{
                    e.getValue().stream().forEach(
                            x -> {
                                Format dateFormat = new SimpleDateFormat("dd/MM");
                                String res = dateFormat.format(e.getKey());
                                System.out.print(res + " ");
                                System.out.printf("%.2f\n", x.absenteism());
                            }
                    );
                }
        );

        System.out.println();

        //4
        FileWriter fw = new FileWriter("./Data/discipline.csv");
        BufferedWriter bw = new BufferedWriter(fw);
        var discipline = examene.values().stream()
                .collect(Collectors.groupingBy(
                        Examen::getDisciplina
                ));
        discipline.entrySet().stream().forEach(
                e -> {
                    System.out.println(e.getKey());
                    e.getValue().stream().forEach(
                            x -> {
                                SimpleDateFormat format = new SimpleDateFormat("dd/MM");
                                String res = format.format(x.getData());
                                System.out.println(x.getData() + ", " + x.getProfesor() + ", " + x.getStudExaminati());
                            }
                    );
                }
        );
    }
}