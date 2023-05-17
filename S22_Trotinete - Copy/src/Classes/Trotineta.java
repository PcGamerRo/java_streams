package Classes;

import java.io.Serializable;

import static java.lang.Math.abs;

public class Trotineta implements Comparable<Trotineta>, Serializable {
    private String ID;
    private float distanta;
    private float viteza_medie;
    private float viteza_maxima;

    public Trotineta(String ID, float distanta, float viteza_medie, float viteza_maxima) {
        this.ID = ID;
        this.distanta = distanta;
        this.viteza_medie = viteza_medie;
        this.viteza_maxima = viteza_maxima;
    }

    public Trotineta() {}

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Trotineta{    ");
        stringBuilder.append("ID=' ").append(ID).append('\'');
        stringBuilder.append(", distanta=").append(distanta);
        stringBuilder.append(", viteza_medie=").append(viteza_medie);
        stringBuilder.append(", viteza_maxima=").append(viteza_maxima);
        stringBuilder.append( "      }");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Trotineta trotineta = (Trotineta)obj;
        return abs(trotineta.distanta - this.distanta) < 10;
    }

    @Override
    public int compareTo(Trotineta trotineta) {
        if (abs(trotineta.distanta - this.distanta) < 10)
                return 0;
        else if (this.distanta < trotineta.distanta)
            return 1;
        else
            return -1;
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

}
