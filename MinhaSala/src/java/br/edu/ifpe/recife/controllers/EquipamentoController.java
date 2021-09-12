/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.controllers;

import br.edu.ifpe.recife.model.classes.*;
import br.edu.ifpe.recife.model.dao.ManagerDao;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author wolner
 */

@ManagedBean(name = "eController")
@SessionScoped
public class EquipamentoController {
    
    private Equipamento cadastro;
    private Equipamento selecao;

    @PostConstruct
    public void init(){
        this.cadastro = new Equipamento();
        this.selecao = new Equipamento();
    }
    
    public void insert(){
        ManagerDao.getCurrentInstance().insert(this.cadastro);
        
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Equipamento cadastrado com sucesso.")
        );
        
        this.cadastro = new Equipamento();
        
        //return "index.xhtml";
    }
    
    public List<Equipamento> readAll(){
        
        String query = "select p from Equipamento p";
        
        return ManagerDao.getCurrentInstance().read(query, Equipamento.class);        
    }
    
    public void update(){
        ManagerDao.getCurrentInstance().update(this.selecao);
        
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Produto foi alterado.")
        );
        
        //return "apresentaprodutos.xhtml";
    }
    
    public void delete(){
        ManagerDao.getCurrentInstance().delete(this.selecao);
        
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Equipamento deletado.")
        );
    }
    
    public List<Equipamento> filterBy(String nomeEMarca){
        
        String query = "select p from Equipamento p where p.nome LIKE '%"+nomeEMarca+"%' or "
                + "p.marca LIKE '%"+nomeEMarca+"%'";
        
        return ManagerDao.getCurrentInstance().read(query, Equipamento.class);
        
    }
    
    public Equipamento getCadastro() {
        return cadastro;
    }

    public void setCadastro(Equipamento cadastro) {
        this.cadastro = cadastro;
    }

    public Equipamento getSelecao() {
        return selecao;
    }

    public void setSelecao(Equipamento selecao) {
        this.selecao = selecao;
    }   
    
}
