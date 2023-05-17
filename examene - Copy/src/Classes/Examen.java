package Classes;

import java.util.Date;

public class Examen implements Comparable<Examen> {
    private Date data;
    private String profesor;
    private String disciplina;
    private int studInscrisi;
    private int studExaminati;

    public double absenteism(){
        return (double)studInscrisi/studExaminati;
    }

    @Override
    public String toString() {
        return "Examen{" +
                "data=" + data +
                ", profesor='" + profesor + '\'' +
                ", disciplina='" + disciplina + '\'' +
                ", studInscrisi=" + studInscrisi +
                ", studExaminati=" + studExaminati +
                '}';
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getStudInscrisi() {
        return studInscrisi;
    }

    public void setStudInscrisi(int studInscrisi) {
        this.studInscrisi = studInscrisi;
    }

    public int getStudExaminati() {
        return studExaminati;
    }

    public void setStudExaminati(int studExaminati) {
        this.studExaminati = studExaminati;
    }

    public Examen() {
    }

    public Examen(Date data, String profesor, String disciplina, int studInscrisi, int studExaminati) {
        this.data = data;
        this.profesor = profesor;
        this.disciplina = disciplina;
        this.studInscrisi = studInscrisi;
        this.studExaminati = studExaminati;
    }

    @Override
    public int compareTo(Examen o) {
        if (studExaminati > o.studExaminati)
            return 1;
        else if(studExaminati < o.studExaminati)
            return -1;
        return 0;
    }
}
