import Classes.Tip;
import Classes.Trotineta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("\nex2\n");

        //2
        Map<String, Trotineta> trotinete;

        FileReader fr = new FileReader("./Data/trotinete.csv");
        BufferedReader bw = new BufferedReader(fr);

        trotinete = bw.lines()
                .map(e->{
                    return new Trotineta(
                            e.split(",")[0],
                            Float.parseFloat(e.split(",")[1]),
                            Float.parseFloat(e.split(",")[2]),
                            Float.parseFloat(e.split(",")[3]),
                            Tip.valueOf(e.split(",")[4])
                    );
                }).collect(Collectors.toMap(Trotineta::getID, Function.identity()));
        bw.close();

        trotinete.values().stream()
                .filter(e -> e.getViteza_maxima()>50)
                .forEach(System.out::println);

        System.out.println();

        System.out.println("\nex3\n");

        //3
        var viteza = trotinete.values().stream()
                .collect(Collectors.groupingBy(
                        Trotineta::getViteza_medie
                ));
        viteza.entrySet().stream().forEach(
                e -> {
                    System.out.print(e.getKey() + " km/h -> ");
                    System.out.print(e.getValue().stream().count() + " trotinete, ");
                    System.out.println(e.getValue().stream()
                            .mapToDouble(x->x.getDistanta())
                            .sum()
                        + " km"
                    );
                }
        );

        //4
        FileOutputStream fos = new FileOutputStream("./Data/trotineteRapide.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        trotinete.entrySet().stream()
                .filter(e-> e.getValue().getViteza_medie()>14 && e.getValue().getViteza_maxima()>50)
                .forEach(
                e -> {
                    try {
                        oos.writeObject( new Trotineta(
                                e.getKey(),
                                e.getValue().getDistanta(),
                                e.getValue().getViteza_medie(),
                                e.getValue().getViteza_maxima(),
                                e.getValue().getTip_trotineta()
                        ) );
//                        oos.writeUTF(e.getKey());
//                        oos.writeDouble(e.getValue().getDistanta());
//                        oos.writeDouble(e.getValue().getViteza_medie());
//                        oos.writeDouble(e.getValue().getViteza_maxima());
//                        oos.writeUTF(e.getValue().getTip_trotineta().toString());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
        oos.close();

        System.out.println("\nex5\n");

        //5
        List<Trotineta> trotineteNoi = new ArrayList<>();
        FileInputStream fis = new FileInputStream("./Data/trotineteRapide.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        try{
            while (true) {
                trotineteNoi.add((Trotineta) ois.readObject());
            }
        }
        catch (EOFException eofException){}
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        trotineteNoi.stream().forEach(System.out::println);
    }
}