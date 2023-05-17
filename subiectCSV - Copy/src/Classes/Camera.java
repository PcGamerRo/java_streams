package Classes;

import java.io.Serializable;

public class Camera implements Comparable<Camera>, Serializable {
    private String cod;
    private int nrPaturi;
    private float tarif;
    private int nrZileOcupare; // din 365

    public Camera(String cod, int nrPaturi, float tarif, int nrZileOcupare) {
        this.cod = cod;
        this.nrPaturi = nrPaturi;
        this.tarif = tarif;
        this.nrZileOcupare = nrZileOcupare;
    }

    public Camera() {}

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getNrPaturi() {
        return nrPaturi;
    }

    public void setNrPaturi(int nrPaturi) {
        this.nrPaturi = nrPaturi;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public int getNrZileOcupare() {
        return nrZileOcupare;
    }

    public void setNrZileOcupare(int nrZileOcupare) {
        this.nrZileOcupare = nrZileOcupare;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "cod='" + cod + '\'' +
                ", nrPaturi=" + nrPaturi +
                ", tarif=" + tarif +
                ", nrZileOcupare=" + nrZileOcupare +
                '}';
    }

    public float tarifPerPat(){
        return nrPaturi*tarif;
    }

    @Override
    public int compareTo(Camera o) {
        if (tarif/nrPaturi > o.tarif/o.nrPaturi)
            return 1;
        else if (tarif/nrPaturi < o.tarif/o.nrPaturi)
            return -1;
        return 0;
    }
}
