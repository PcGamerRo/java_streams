        // ex. 4.
        FileOutputStream fos = new FileOutputStream("./Date/trotineteRapide.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
        for (var y : trotinete.entrySet())
            if (y.getValue().getViteza_medie()>14 && y.getValue().getViteza_maxima()>50)
                objectOutputStream.writeObject(y.getValue());
        objectOutputStream.close();

        // ex. 5.
        List<Trotineta> tr = new ArrayList<>();
        FileInputStream fis = new FileInputStream("./Date/trotineteRapide.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fis);
        System.out.println("ex 5");
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
