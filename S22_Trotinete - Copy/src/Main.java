import Classes.Trotineta;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Map<String, Trotineta> trotinete;
        // ex 2.
        System.out.println("\nex 2\n");

        FileReader file = new FileReader("./Date/trotinete.txt");
        BufferedReader bf = new BufferedReader(file);
        trotinete = bf.lines()
                .map(linie -> new Trotineta(
                        linie.split("\t")[0],
                        Float.parseFloat(linie.split("\t")[1]),
                        Float.parseFloat(linie.split("\t")[2]),
                        Float.parseFloat(linie.split("\t")[3])
                )).collect(Collectors.toMap(Trotineta::getID, Function.identity()));
        bf.close();

        trotinete.entrySet().stream()
                .filter(e -> e.getValue().getViteza_maxima()>50)
                .distinct()
                .forEach(e -> System.out.println(e.getValue()));

        // ex 3.
        System.out.println("\nex 3\n");

        var vitezeTrotinete = trotinete.values().stream()
                .distinct()
                .collect(Collectors.groupingBy(
                        Trotineta::getViteza_medie,
                        Collectors.summarizingDouble(Trotineta::getViteza_maxima)
                ));

        vitezeTrotinete.entrySet().stream()
                .forEach( e -> {
                    System.out.print("Viteza medie " + e.getKey() + " -> ");
                    System.out.print(e.getValue().getCount() + " trotinete, ");
                    System.out.print("suma distantelor parcurse " + e.getValue().getSum());
                    System.out.println();
                });

        // ex 4.
        FileOutputStream fos = new FileOutputStream("./Date/trotineteRapide.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
        for (var y : trotinete.entrySet())
            if (y.getValue().getViteza_medie()>14 && y.getValue().getViteza_maxima()>50)
                objectOutputStream.writeObject(y.getValue());
        objectOutputStream.close();

        // ex 5.
        List<Trotineta> tr = new ArrayList<>();
        FileInputStream fis = new FileInputStream("./Date/trotineteRapide.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fis);
        System.out.println("\nex 5\n");
        try {
            while(true) {
                Trotineta trt = (Trotineta) objectInputStream.readObject();
                tr.add(trt);
            }
        }
        catch (EOFException eofException){}
        objectInputStream.close();

        for(var x : tr)
            System.out.println(x);
    }
}