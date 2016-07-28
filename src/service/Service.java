package service;

import java.util.ArrayList;
import java.util.List;

import util.SessionAux;
import webService.MockServiceAccess;
import webService.ServiceAccess;
import webService.WebServiceAccess;
import dao.userDAO;
import entidade.Album;
import entidade.Banda;
import entidade.User;

public class Service implements ServiceAccess {
	
	private userDAO dao = new userDAO();
	private ServiceAccess webService = SessionAux.MOCK ? new MockServiceAccess() : new WebServiceAccess();
	
	public boolean getUser( User usuario ){
		
		return dao.getUser( usuario );
	}
	
	public List<Banda> getAllBandas(){
		
		List<Banda> allBandas = webService.getAllBandas();
		
		return allBandas != null ? allBandas : new ArrayList<Banda>();
	}
	
	public void removerAlbum( Banda banda, Album album ){
		webService.removerAlbum( banda, album );
	}
	
	public void removerBanda( Long idBanda ){
		webService.removerBanda( idBanda );
	}
	
	public void editarAlbum( String id, Album album ){
		webService.editarAlbum( id, album );
	}
	
	public void salvarBanda( Banda banda ){
		webService.salvarBanda( banda );
	}

}