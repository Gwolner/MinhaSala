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
import javax.servlet.http.HttpSession;

/**
 *
 * @author wolner
 */

@ManagedBean(name = "uController")
@SessionScoped
public class UsuarioController {
    
    private Usuario cadastro;
    private Usuario selecao;

    @PostConstruct
    public void init(){
        this.cadastro = new Usuario();
        this.selecao = new Usuario();
        
    }
    
    public void insert(String confirma){
        
        if(!confirma.equals(this.cadastro.getSenha())){
             FacesContext.getCurrentInstance().addMessage(
                    "formModal:txtPassword", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "A senha não confere!")
            );
             
            return;
        }
        
        ManagerDao.getCurrentInstance().insert(this.cadastro);
        
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Usuario cadastrado com sucesso.")
        );
        
        this.cadastro = new Usuario();
    }
    
    public List<Usuario> readAll(){
        
        String query = "select u from Usuario u";
        
        return ManagerDao.getCurrentInstance().read(query, Usuario.class);        
    }
    
    public void update(){
        ManagerDao.getCurrentInstance().update(this.selecao);
        
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Usuario foi alterado com sucesso.")
        );
    }
    
    public void updateUsuarioLogado(){
        
        LoginController login = (LoginController) ((HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true)).getAttribute("lController");
        
        Usuario user = login.getLogado();
        
        ManagerDao.getCurrentInstance().update(user);
        
        FacesContext.getCurrentInstance().addMessage(
            null, new FacesMessage("Seus dados foram alterados com sucesso.")
        );
    }
    
    public void updateSenha(String atual, String nova, String confirma){
        
        LoginController login = (LoginController) ((HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true)).getAttribute("lController");
        
        if(!atual.equals(login.getLogado().getSenha())){
            FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro","A senha atual está errada.")
            );
            
            return;
        }
        
        if(!nova.equals(confirma)){
            FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro","Senha nova e a confirmação não conferem.")
            );
            
            return;
        }
        
        Usuario user = login.getLogado();
        
        user.setSenha(nova);
        
        ManagerDao.getCurrentInstance().update(user);
        
        login.setLogado(null);
        
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("A senha foi alterado com sucesso.")
        );
        
    }
    
    public void delete(){
        ManagerDao.getCurrentInstance().delete(this.selecao);
        
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Usuario deletado.")
        );
    }
    
    
    public Usuario getCadastro() {
        return cadastro;
    }

    public void setCadastro(Usuario cadastro) {
        this.cadastro = cadastro;
    }

    public Usuario getSelecao() {
        return selecao;
    }

    public void setSelecao(Usuario selecao) {
        this.selecao = selecao;
    }
}
