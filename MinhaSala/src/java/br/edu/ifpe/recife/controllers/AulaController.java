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


@ManagedBean(name = "aController")
@ViewScoped
public class AulaController {
    
    private Sala selecionada;
    private Equipamento selecionado;
    private Aula aulaSelecionada;
    private Aula aulaCadastrada;
    
    @PostConstruct
    public void init(){    
        this.aulaSelecionada = new Aula();
        this.aulaCadastrada = new Aula();
    }
    
    public void insertAula(){
        
        this.aulaCadastrada.getEquipamentos().add(this.selecionado);
        
        ManagerDao.getCurrentInstance().insert(this.aulaSelecionada);
        
        LoginController login = (LoginController)((HttpSession)FacesContext.getCurrentInstance().
                getExternalContext().getSession(true)).getAttribute("lController");
        
        Usuario u = login.getLogado();
        
        u.getAulas().add(this.aulaSelecionada);
        
        ManagerDao.getCurrentInstance().update(u);
        
        this.aulaCadastrada = new Aula();
        
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(
                    "A Aula foi inserida com sucesso!"));
    }
    
     public void insert(){        
        ManagerDao.getCurrentInstance().insert(this.aulaCadastrada);
        this.aulaCadastrada = new Aula();        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aula cadastrada com sucesso!"));
    }
    
    
    public List<Usuario> getOfertasOutrosUsuarios(){
        
        LoginController login = (LoginController)((HttpSession)FacesContext.getCurrentInstance().
                getExternalContext().getSession(true)).getAttribute("lController");
        
        Usuario u = login.getLogado();
        
        if(u == null)
            return null;
        
        String sql = "select u from Usuario u where u.codigo <> "+u.getCodigo();
        
        return ManagerDao.getCurrentInstance().read(sql, Usuario.class);
        
    }
    
    public void inserirEquipamento(Equipamento equipamento){        
        this.aulaSelecionada.getEquipamentos().add(equipamento);
        
    }
    
    public List<Aula> readAll(){
        
        String query = "select a from Aula a";
        
        return ManagerDao.getCurrentInstance().read(query, Aula.class);        
    }

    public Equipamento getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Equipamento selecionado) {
        this.selecionado = selecionado;
    }

    public Aula getOfertaSelecionada() {
        return aulaSelecionada;
    }

    public void setOfertaSelecionada(Aula aulaSelecionada) {
        this.aulaSelecionada = aulaSelecionada;
    }

    public Sala getSelecionada() {
        return selecionada;
    }

    public void setSelecionada(Sala selecionada) {
        this.selecionada = selecionada;
    }

    public Aula getAulaSelecionada() {
        return aulaSelecionada;
    }

    public void setAulaSelecionada(Aula aulaSelecionada) {
        this.aulaSelecionada = aulaSelecionada;
    }

    public Aula getAulaCadastrada() {
        return aulaCadastrada;
    }

    public void setAulaCadastrada(Aula aulaCadastrada) {
        this.aulaCadastrada = aulaCadastrada;
    }
    
    
}
