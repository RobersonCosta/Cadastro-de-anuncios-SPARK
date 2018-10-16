/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "anuncio")
public class Anuncio implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    @ManyToOne
    private Pessoa pessoa;
    
    @Column(name = "descricao")
    private String descricao;
    
    
    @Column(name = "valor")
    private String valor;

    public Anuncio() {
    }

    public Anuncio(int id, Pessoa pessoa, String descricao, String valor) {
        this.id = id;
        this.pessoa = pessoa;
        this.descricao = descricao;
        this.valor = valor;
    }
        
    
    public Anuncio(int id, String descricao, String valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }    
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
}