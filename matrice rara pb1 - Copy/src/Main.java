import Classes.Element;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Element> matriceRara;
        // 2
        FileReader fr = new FileReader("./Data/matricerara.csv");
        BufferedReader br = new BufferedReader(fr);

        matriceRara = br.lines()
                .map(e->new Element(
                        Integer.parseInt(e.split(",")[0]),
                        Integer.parseInt(e.split(",")[1]),
                        Double.parseDouble(e.split(",")[2])
                ))
                .collect(Collectors.toList());
        br.close();

        long nr = matriceRara.stream()
                .filter(e -> e.getValoare()<0)
                .count();
        System.out.println("Numarul elementelor negative este: " + nr);

        // 3
        var coloanaVal = matriceRara.stream()
                .collect(Collectors.groupingBy(
                        Element::getColoana,
                        Collectors.summarizingDouble(Element::getValoare)
                ));
        coloanaVal.entrySet().stream()
                .forEach( e-> {
                    System.out.print(e.getKey()+": ");
                    System.out.println(e.getValue().getSum()/e.getValue().getCount());
                });

        // 4
        FileOutputStream fos = new FileOutputStream("./Data/diagonal.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        matriceRara.stream()
                .filter(e -> e.getLinie()==e.getColoana())
                .forEach(e-> {
                    try {
                        oos.writeObject(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
        oos.close();

        System.out.println();

        // 5
        List<Element> elements = new ArrayList<>();
        FileInputStream fis = new FileInputStream("./Data/diagonal.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        try{
            while(true){
                elements.add((Element)ois.readObject());
            }
        } catch (EOFException eofException){}

        elements.stream().forEach(System.out::println);
    }
}