package Classes;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Element implements Comparable<Element>, Serializable {
    private int linie;
    private int coloana;
    private double valoare;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return linie == element.linie && coloana == element.coloana && Double.compare(element.valoare, valoare) == 0;
    }

    @Override
    public int compareTo(Element o) {
        if (valoare>o.valoare)
            return 1;
        else if (valoare<o.valoare)
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Element{" +
                "linie=" + linie +
                ", coloana=" + coloana +
                ", valoare=" + valoare +
                '}';
    }

    public Element(int linie, int coloana, double valoare) {
        this.linie = linie;
        this.coloana = coloana;
        this.valoare = valoare;
    }

    public Element() {
    }

    public int getLinie() {
        return linie;
    }

    public void setLinie(int linie) {
        this.linie = linie;
    }

    public int getColoana() {
        return coloana;
    }

    public void setColoana(int coloana) {
        this.coloana = coloana;
    }

    public double getValoare() {
        return valoare;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }
}