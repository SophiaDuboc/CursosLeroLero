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
public class Turmas {

    private int id;
    private int instrutoresId;
    private int cursosId;
    private Date dataInicio;
    private Date dataFinal;
    private short cargaHoraria;

    public Turmas() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInstrutoresId(int instrutoresId) {
        this.instrutoresId = instrutoresId;
    }

    public void setCursosId(int cursosId) {
        this.cursosId = cursosId;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setCargaHoraria(short cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getId() {
        return id;
    }

    public int getInstrutoresId() {
        return instrutoresId;
    }

    public int getCursosId() {
        return cursosId;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public short getCargaHoraria() {
        return cargaHoraria;
    }

}
