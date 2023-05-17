package Classes;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class NotaContabila implements Comparable<NotaContabila> {
    private Date data;
    private int contDebitor;
    private int contCreditor;
    private double suma;

    public NotaContabila(Date data, int contDebitor, int contCreditor, double suma) {
        this.data = data;
        this.contDebitor = contDebitor;
        this.contCreditor = contCreditor;
        this.suma = suma;
    }

    public NotaContabila() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotaContabila that = (NotaContabila) o;
        return contDebitor == that.contDebitor && contCreditor == that.contCreditor && Double.compare(that.suma, suma)==0;
    }

    @Override
    public int compareTo(NotaContabila o) {
        if (suma>o.suma)
            return 1;
        else if(suma<o.suma)
                return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "NotaContabila{" +
                "data=" + data +
                ", contDebitor=" + contDebitor +
                ", contCreditor=" + contCreditor +
                ", suma=" + suma +
                '}';
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getContDebitor() {
        return contDebitor;
    }

    public void setContDebitor(int contDebitor) {
        this.contDebitor = contDebitor;
    }

    public int getContCreditor() {
        return contCreditor;
    }

    public void setContCreditor(int contCreditor) {
        this.contCreditor = contCreditor;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }
}