/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import java.sql.Date;

/**
 *
 * @author sophi
 */
public class Matriculas {

    private int id;
    private int turmaId;
    private int alunoId;
    private Date date;
    private double nota;

    public Matriculas() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public int getTurmaId() {
        return turmaId;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public Date getDate() {
        return date;
    }

    public double getNota() {
        return nota;
    }

}
