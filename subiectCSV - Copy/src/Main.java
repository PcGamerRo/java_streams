import Classes.Camera;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Camera c1 = new Camera("c1", 2, 100, 5);
        //Camera c2 = new Camera("c1", 3, 100, 5);
        //System.out.println(c1.compareTo(c2));

        System.out.println("\nEx 2\n");
        // 2
        Map<String, Camera> camere;

        FileReader fr = new FileReader("./Data/Camere.csv");
        BufferedReader br = new BufferedReader(fr);
        camere = br.lines()
                .map(
                    linie -> new Camera(
                            linie.split(",")[0],
                            Integer.parseInt(linie.split(",")[1]),
                            Float.parseFloat(linie.split(",")[2]),
                            Integer.parseInt(linie.split(",")[3])
                    )
                ).collect(Collectors.toMap(Camera::getCod, Function.identity()));
        br.close();

        camere.values().stream()
                .filter(e -> e.getNrZileOcupare() > 365/2)
                .forEach(e -> System.out.println(e));

        System.out.println("\nEx 3\n");
        //3
        var paturiCamere = camere.values().stream()
                .collect(Collectors.groupingBy(
                        Camera::getNrPaturi,
                        Collectors.summarizingInt(Camera::getNrZileOcupare)
                ));
        paturiCamere.entrySet().stream()
                .forEach(e -> {
                    System.out.print("Camera cu " + e.getKey() + " paturi -> ");
                    System.out.print(e.getValue().getCount() + " camere ");
                    System.out.println(  (float)e.getValue().getSum()/e.getValue().getCount() + " zile de ocupare in medie pe an " );
                });

        //4
        FileOutputStream fos = new FileOutputStream("./Data/camereEficiente.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        camere.values().stream()
                        .filter(e -> e.getNrZileOcupare() > 365*0.7)
                        .forEach(e -> {
                            try {
                                oos.writeObject(e);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
        oos.close();

        System.out.println("\nEx 5\n");
        //5
        Map<String, Camera> camereEficiente = new HashMap<>();
        FileInputStream fis = new FileInputStream("./Data/camereEficiente.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        try{
            while(true){
                Camera camera = (Camera)ois.readObject();
                camereEficiente.put(camera.getCod(), camera);
            }
        }catch (EOFException eofException){}

        camereEficiente.values().stream()
                .forEach(e -> System.out.println(e));

    }
}