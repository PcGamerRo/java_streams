package Classes;

import java.io.Serializable;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Punct implements Comparable<Punct>, Serializable {
    private String eticheta_figura;
    private String eticheta_punct;
    private double OX;
    private double OY;

    public double distante(){
        if (OX>OY)
            return sqrt(pow(OX, 2) - pow(OY, 2));
        return sqrt(pow(OY, 2) - pow(OX, 2));
    }

    @Override
    public int compareTo(Punct o) {
        if (this.distante() > o.distante())
            return 1;
        else if (this.distante() < o.distante())
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Punct{" +
                "eticheta_figura='" + eticheta_figura + '\'' +
                ", eticheta_punct='" + eticheta_punct + '\'' +
                ", OX=" + OX +
                ", OY=" + OY +
                '}';
    }

    public Punct(String eticheta_figura, String eticheta_punct, double OX, double OY) {
        this.eticheta_figura = eticheta_figura;
        this.eticheta_punct = eticheta_punct;
        this.OX = OX;
        this.OY = OY;
    }

    public Punct() {
    }

    public String getEticheta_figura() {
        return eticheta_figura;
    }

    public void setEticheta_figura(String eticheta_figura) {
        this.eticheta_figura = eticheta_figura;
    }

    public String getEticheta_punct() {
        return eticheta_punct;
    }

    public void setEticheta_punct(String eticheta_punct) {
        this.eticheta_punct = eticheta_punct;
    }

    public double getOX() {
        return OX;
    }

    public void setOX(double OX) {
        this.OX = OX;
    }

    public double getOY() {
        return OY;
    }

    public void setOY(double OY) {
        this.OY = OY;
    }

}
