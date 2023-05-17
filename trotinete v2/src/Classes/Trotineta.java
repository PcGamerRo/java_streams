package Classes;

import java.io.Serializable;

public class Trotineta implements Comparable<Trotineta>, Serializable {
    private String ID;
    private float distanta;
    private float viteza_medie;
    private float viteza_maxima;
    private Tip tip_trotineta;

    @Override
    public String toString() {
        return "Trotineta{" +
                "ID='" + ID + '\'' +
                ", distanta=" + distanta +
                ", viteza_medie=" + viteza_medie +
                ", viteza_maxima=" + viteza_maxima +
                ", tip_trotineta=" + tip_trotineta +
                '}';
    }

    public Trotineta(String ID, float distanta, float viteza_medie, float viteza_maxima, Tip tip_trotineta) {
        this.ID = ID;
        this.distanta = distanta;
        this.viteza_medie = viteza_medie;
        this.viteza_maxima = viteza_maxima;
        this.tip_trotineta = tip_trotineta;
    }

    public Trotineta() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public float getDistanta() {
        return distanta;
    }

    public void setDistanta(float distanta) {
        this.distanta = distanta;
    }

    public float getViteza_medie() {
        return viteza_medie;
    }

    public void setViteza_medie(float viteza_medie) {
        this.viteza_medie = viteza_medie;
    }

    public float getViteza_maxima() {
        return viteza_maxima;
    }

    public void setViteza_maxima(float viteza_maxima) {
        this.viteza_maxima = viteza_maxima;
    }

    public Tip getTip_trotineta() {
        return tip_trotineta;
    }

    public void setTip_trotineta(Tip tip_trotineta) {
        this.tip_trotineta = tip_trotineta;
    }

    @Override
    public boolean equals(Object obj) {
        Trotineta trotineta = (Trotineta) obj;
        return ((this.distanta-trotineta.distanta)<10);
    }

    @Override
    public int compareTo(Trotineta o) {
        if (distanta<o.distanta)
            return -1;
        else if (distanta>o.distanta)
            return 1;
        return 0;
    }
}
