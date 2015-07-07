package dao;

import java.util.HashSet;
import java.util.Set;

import entidade.User;

public class userDAO {
	
	private static Set<User> usuarios;
	
	static {
		
		usuarios = new HashSet<User>();
		
		usuarios.add( new User( 1L, "usuario", "123456" ) );
		usuarios.add( new User( 1L, "admin", "123456" ) );
	}
	
	public boolean getUser( User usuario ){
		
		return usuarios.contains( usuario );
	}

}