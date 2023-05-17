import Classes.Achizitie;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Achizitie> achizitii;
        FileReader fr = new FileReader("./Data/achizitii.txt");
        BufferedReader br = new BufferedReader(fr);
        achizitii = br.lines().map( e -> {
            String[] valori = e.split(",");
            int[] data = new int[3];
            data[0] = Integer.parseInt(valori[1]);
            data[1] = Integer.parseInt(valori[2]);
            data[2] = Integer.parseInt(valori[3]);

            return new Achizitie(valori[0], data, Integer.parseInt(valori[4]), Float.parseFloat(valori[5]));
            }
        ).collect(Collectors.toList());
        br.close();

        achizitii.stream()
                .sorted(Comparator.comparing(Achizitie::getCodProdus))
                .filter(e -> e.getData()[0]<=15 && e.getCantitate()>100)
                .forEach(System.out::println);

        var produse = achizitii.stream()
                .collect(Collectors.groupingBy(
                        Achizitie::getCodProdus
                ));

        produse.entrySet().stream()
                .forEach(e->{
                    System.out.print("Produs " + e.getKey() + " -> ");
                    System.out.print(e.getValue().stream().count() + " achizitii, ");
                    float val = 0;
                    System.out.println("valoare totala " + e.getValue().stream()
                            .mapToDouble(x -> x.getPretUnitar()*x.getCantitate())
                            .sum()
                    );
                });

        FileOutputStream fos = new FileOutputStream("./Data/produseFrecvente.dat");
        DataOutputStream dos = new DataOutputStream(fos);


//        produse.entrySet().stream()
//                .filter(e -> 12 / e.getValue().stream()
//                        .mapToDouble(x -> x.getCantitate())
//                        .sum() > 3)
//                .forEach(e -> {
//                    try {
//                        dos.writeUTF(e.getKey());
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                });
        dos.close();

        FileInputStream fis = new FileInputStream("./Data/produseFrecvente.dat");
        DataInputStream dis = new DataInputStream(fis);
        List<String> prod = new ArrayList<>();
        try {
            while (true){
                prod.add(dis.readUTF());
            }
        }
        catch (EOFException eofException){}
        dis.close();

        for(var x: prod)
            System.out.println(x);
    }
}