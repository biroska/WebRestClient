package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.Service;
import util.Constants;
import util.SessionAux;
import entidade.User;

@SessionScoped
@ManagedBean(name="loginMB")
public class LoginMB implements Serializable {

	private static final long serialVersionUID = -7687173547108655294L;

	private User usuario;
	private boolean mock;
	
	private Service service;
	
	public LoginMB(){
		this.usuario = new User();
		this.service = new Service();
	}
	
	public String autenticar(){
		
		if ( service.getUser( usuario ) ){
			return  Constants.PAGES_FOLDER + "main";
		}
		
		return "";
		
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public boolean isMock() {
		return mock;
	}

	public void setMock(boolean mock) {
		this.mock = mock;
		SessionAux.MOCK = mock;
		System.out.println("LoginMB.setMock(): SessionAux.MOCK " + SessionAux.MOCK );
	}
}