/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.controllers;

import br.edu.ifpe.recife.model.classes.Usuario;
import br.edu.ifpe.recife.model.dao.ManagerDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author wolner
 */

@ManagedBean(name = "lController")
@SessionScoped
public class LoginController {
    
    private Usuario logado;
    
    public String logar(String siape, String senha){
        
        try{
        Usuario aux = (Usuario) ManagerDao.getCurrentInstance()
                .read("Select u from Usuario u where u.siape=\""+siape+"\" and u.senha=\""+senha+"\"", Usuario.class).get(0);
        
        this.logado = aux;
        
        return "indexusuario.xhtml";
        
        }catch(IndexOutOfBoundsException ind){
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Erro ao logar", "Informações de login inválidas"));
            
            return null;
        }
        
    }

    public String logout(){
        this.logado = null;
        
        return "index.xhtml";
    }
    
    public Usuario getLogado() {
        return logado;
    }

    public void setLogado(Usuario logado) {
        this.logado = logado;
    }
    
    
}
