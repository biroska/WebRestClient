package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import entidade.User;


@SessionScoped
@ManagedBean(name="sessionMB")
public class SessionMB implements Serializable {

	private static final long serialVersionUID = 8424820732119127L;
	
	private User usuario;
	
	@ManagedProperty(value="#{loginMB}")
	private LoginMB loginMB;
	
	public SessionMB(){	}
	
	public void setUserSession( ComponentSystemEvent event ){
		usuario = new User( loginMB.getUsuario().getUsuario(), loginMB.getUsuario().getSenha() );
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}
}