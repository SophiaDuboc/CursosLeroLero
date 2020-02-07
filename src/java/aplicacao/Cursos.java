/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

/**
 *
 * @author sophi
 */
public class Cursos {

    private String nome;
    private String requisito;
    private String ementa;
    private short cargaHoraria;
    private double preco;

    public Cursos() {
    }

    private int id;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public void setCargaHoraria(short cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getRequisito() {
        return requisito;
    }

    public String getEmenta() {
        return ementa;
    }

    public short getCargaHoraria() {
        return cargaHoraria;
    }

    public double getPreco() {
        return preco;
    }

}
