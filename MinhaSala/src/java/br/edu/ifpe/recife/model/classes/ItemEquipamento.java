/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.model.classes;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author wolner
 */
//@Entity
//public class ItemEquipamento {
//    
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int codigo;
//    private int quantidade;
//    private Equipamento equipamento;
//
//    public int getCodigo() {
//        return codigo;
//    }
//
//    public void setCodigo(int codigo) {
//        this.codigo = codigo;
//    }
//
//    public int getQuantidade() {
//        return quantidade;
//    }
//
//    public void setQuantidade(int quantidade) {
//        this.quantidade = quantidade;
//    }
//    
//    public void alterarQuantidade(int quantidade)throws RuntimeException{
//        if(quantidade>this.quantidade){
//            throw new RuntimeException("Quantidade insuficiente");
//        }
//        
//        this.quantidade -= quantidade;
//    }
//
//    public Equipamento getEquipamento() {
//        return equipamento;
//    }
//
//    public void setEquipamento(Equipamento equipamento) {
//        this.equipamento = equipamento;
//    }
//    
//    
//}
