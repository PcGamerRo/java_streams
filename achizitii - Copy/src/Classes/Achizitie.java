package Classes;

import java.io.Serializable;
import java.util.Arrays;

public class Achizitie implements Comparable<Achizitie>, Serializable {
    private String codProdus;
    private int[] data;
    private int cantitate;
    private float pretUnitar;

    public Achizitie(String cod, int[] data, int cantitate, float pretUnitar) {
        this.codProdus = cod;
        this.data = data;
        this.cantitate = cantitate;
        this.pretUnitar = pretUnitar;
    }

    public float valoare(){
        return cantitate*pretUnitar;
    }

    public Achizitie() {
    }

    public String getCodProdus() {
        return codProdus;
    }

    public void setCodProdus(String codProdus) {
        this.codProdus = codProdus;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public float getPretUnitar() {
        return pretUnitar;
    }

    public void setPretUnitar(float pretUnitar) {
        this.pretUnitar = pretUnitar;
    }

    @Override
    public String toString() {
        return "Achizitie{" +
                "cod='" + codProdus + '\'' +
                ", data=" + Arrays.toString(data) +
                ", cantitate=" + cantitate +
                ", pretUnitar=" + pretUnitar +
                '}';
    }

    @Override
    public int compareTo(Achizitie o) {
        if (this.valoare() > o.valoare())
            return 1;
        else if (this.valoare() < o.valoare())
            return -1;
        return 0;
    }
}
