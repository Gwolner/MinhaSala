/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.controllers;

import br.edu.ifpe.recife.model.classes.Aula;
import br.edu.ifpe.recife.model.classes.Equipamento;
import br.edu.ifpe.recife.model.classes.Sala;
import br.edu.ifpe.recife.model.classes.Usuario;
import br.edu.ifpe.recife.model.dao.ManagerDao;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wolner
 */

@ManagedBean(name = "sController")
@ViewScoped
public class SalaController {
    
    private Sala cadastro;
    private Sala selecao;

    @PostConstruct
    public void init(){
        this.cadastro = new Sala();
        this.selecao = new Sala();
    }
    
    public void insert(){
        ManagerDao.getCurrentInstance().insert(this.cadastro);
        
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Sala cadastrada com sucesso.")
        );
        
        this.cadastro = new Sala();
        
        //return "index.xhtml";
    }
    
    public List<Sala> readAll(){
        
        String query = "select s from Sala s";
        
        return ManagerDao.getCurrentInstance().read(query, Sala.class);        
    }
    
    public void update(){
        ManagerDao.getCurrentInstance().update(this.selecao);
        
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Sala foi alterada.")
        );
        
        //return "apresentaprodutos.xhtml";
    }
    
    public void delete(){
        ManagerDao.getCurrentInstance().delete(this.selecao);
        
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Sala deletada.")
        );
    }
    
    public List<Sala> filterBy(String nomeSala){
        
        String query = "select s from Sala s where s.nome LIKE '%"+nomeSala+"%'";
        
        return ManagerDao.getCurrentInstance().read(query, Equipamento.class);
        
    }
    
    public Sala getCadastro() {
        return cadastro;
    }

    public void setCadastro(Sala cadastro) {
        this.cadastro = cadastro;
    }

    public Sala getSelecao() {
        return selecao;
    }

    public void setSelecao(Sala selecao) {
        this.selecao = selecao;
    }
    
    
 
}
