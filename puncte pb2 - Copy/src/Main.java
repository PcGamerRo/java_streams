import Classes.Punct;

import javax.swing.text.Element;
import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        //2
        List<Punct> puncte;
        FileReader fr = new FileReader("./Data/puncte.csv");
        BufferedReader br = new BufferedReader(fr);
        puncte = br.lines()
                .map(e -> new Punct(
                        e.split("\t")[0],
                        e.split("\t")[1],
                        Double.parseDouble(e.split("\t")[2]),
                        Double.parseDouble(e.split("\t")[3])
                )).collect(Collectors.toList());
        System.out.println("numarul de puncte este: " + puncte.stream().count());
        //3
        var figuri = puncte.stream()
                .collect(Collectors.groupingBy(
                        Punct::getEticheta_figura
                ));
        figuri.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(e->{
                    System.out.print(e.getKey() + ", ");
                    System.out.println(e.getValue().stream().count() + " puncte");
                    System.out.println();
                });
        //4
        FileWriter fw = new FileWriter("./Data/distante.csv");
        BufferedWriter bw = new BufferedWriter(fw);
        figuri.entrySet().stream()
                .forEach( e -> {
                    e.getValue().stream()
                            .forEach(
                                    x -> {
                                        try {
                                            bw.write(e.getKey() + ", ");
                                            bw.write(x.getEticheta_punct() + ", ");
                                            bw.write(x.distante() +", ");
                                            bw.write("\n");
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                            );
                });
        bw.close();
    }
}