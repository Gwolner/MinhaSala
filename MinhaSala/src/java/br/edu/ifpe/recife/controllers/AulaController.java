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
    private Aula aulaCadastrada;
    
    @PostConstruct
    public void init(){
        this.aulaCadastrada = new Aula();
    }
    
    public void insertAula(){
        
        
        //Recupera usuario logado
        LoginController login = (LoginController)((HttpSession)FacesContext.getCurrentInstance().
                getExternalContext().getSession(true)).getAttribute("lController");
        
        Usuario u = login.getLogado();
        
        //atribui a sala escolhida à aula
        this.aulaCadastrada.setSala(this.selecionada);
        
        //Atribui o usuario logado à aula
        this.aulaCadastrada.setUsuario(u);
        
        //Persiste a aula
        ManagerDao.getCurrentInstance().insert(this.aulaCadastrada);
        
        //Atribui a aula persistida a sala
        this.selecionada.getAulas().add(this.aulaCadastrada);
        ManagerDao.getCurrentInstance().update(this.selecionada);
        
        //Atribui a aula persistida ao usuario logado
        u.getAulas().add(this.aulaCadastrada);        
        ManagerDao.getCurrentInstance().update(u);
        
        //"Limpa" a aula para novo cadastro
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
    
    public List<Aula> getMinhasAulas(){
        
        LoginController login = (LoginController)((HttpSession)FacesContext.getCurrentInstance().
                getExternalContext().getSession(true)).getAttribute("lController");
        
        Usuario u = login.getLogado();
        
        if(u == null)
            return null;
        
        String sql = "select a from Aula a where a.usuario.codigo = "+u.getCodigo();
        
        return ManagerDao.getCurrentInstance().read(sql, Aula.class);
        
    }
    
    public List<Aula> getAulasAgendadas(){
        
        LoginController login = (LoginController)((HttpSession)FacesContext.getCurrentInstance().
                getExternalContext().getSession(true)).getAttribute("lController");
        
        Usuario u = login.getLogado();
        
        if(u == null)
            return null;
        
        String sql = "select a from Aula a where a.usuario.codigo = "
                + u.getCodigo() + " and a.status = 'Agendada'";
        
        return ManagerDao.getCurrentInstance().read(sql, Aula.class);
        
    }
    
    public List<Aula> getAulasLiberadas(){
        
        LoginController login = (LoginController)((HttpSession)FacesContext.getCurrentInstance().
                getExternalContext().getSession(true)).getAttribute("lController");
        
        Usuario u = login.getLogado();
        
        if(u == null)
            return null;
        
        String sql = "select a from Aula a where a.usuario.codigo = "
                + u.getCodigo() + " and a.status = 'Liberada'";
        
        return ManagerDao.getCurrentInstance().read(sql, Aula.class);
        
    }
    
    public void liberarAula(Aula aula){
        aula.setStatus("Liberada");
        ManagerDao.getCurrentInstance().update(aula);
    }
    
    public void inserirEquipamento(Equipamento equipamento){        
        this.aulaCadastrada.getEquipamentos().add(equipamento);
        
    }
    
    public List<Aula> readAll(){
        
        String query = "select a from Aula a";
        
        return ManagerDao.getCurrentInstance().read(query, Aula.class);        
    }

    public Sala getSelecionada() {
        return selecionada;
    }

    public void setSelecionada(Sala selecionada) {
        this.selecionada = selecionada;
    }

    public Aula getAulaCadastrada() {
        return aulaCadastrada;
    }

    public void setAulaCadastrada(Aula aulaCadastrada) {
        this.aulaCadastrada = aulaCadastrada;
    }
    
    
}
