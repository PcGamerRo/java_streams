import Classes.NotaContabila;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        //2
        List<NotaContabila> note;
        FileReader fr = new FileReader("./Data/jurnal.csv");
        BufferedReader br = new BufferedReader(fr);
        note = br.lines()
                .map( e -> {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    Date data = null;
                    try {
                        data = dateFormat.parse(e.split(",")[0]);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }

                    return new NotaContabila(
                            data,
                            Integer.parseInt(e.split(",")[1]),
                            Integer.parseInt(e.split(",")[2]),
                            Double.parseDouble(e.split(",")[3])
                    );
                })
                .collect(Collectors.toList());
        note.stream().forEach(System.out::println);

        System.out.println("Rulaj total: " + note.stream().mapToDouble(NotaContabila::getSuma).sum());
        //3
        var rulaj = note.stream()
                .collect(Collectors.groupingBy(NotaContabila::getContDebitor));
        rulaj.entrySet().stream().forEach(
                e -> {
                    System.out.print(e.getKey()+": ");
                    double suma = e.getValue().stream()
                            .mapToDouble(NotaContabila::getSuma)
                            .sum();
                    System.out.println(suma);
                }
        );
        //4
        FileWriter fw = new FileWriter("./Data/fisa.csv");
        BufferedWriter bw = new BufferedWriter(fw);

        System.out.println("\nIntroduceti contul debitor: ");
        int cont;
        Scanner scanner = new Scanner(System.in);
        cont = scanner.nextInt();

        rulaj.entrySet().stream()
                .forEach(
                        e -> {
                            e.getValue().stream().forEach(
                                    x -> {
                                        try {
                                            if (x.getContDebitor()==cont) {
                                                bw.write(x.getData().getDay() + "," + "Debitare,");
                                                bw.write(x.getContCreditor() + ",");
                                                bw.write(Double.toString(x.getSuma()));
                                                bw.write("\n");
                                            }
                                            else if (x.getContCreditor()==cont){
                                                bw.write(x.getData().getDay() + "," + "Creditare,");
                                                bw.write(x.getContDebitor() + ",");
                                                bw.write(Double.toString(x.getSuma()));
                                                bw.write("\n");
                                            }
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                            );
                        }
                );
        bw.close();
    }
}