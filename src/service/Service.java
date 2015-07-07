package service;

import java.util.ArrayList;
import java.util.List;

import dao.userDAO;
import entidade.Banda;
import entidade.User;
import webService.WebServiceAccess;

public class Service {
	
	private userDAO dao = new userDAO();
	private WebServiceAccess service = new WebServiceAccess();
	
	public boolean getUser( User usuario ){
		
		return dao.getUser( usuario );
	}
	
	public List<Banda> getAllBandas(){
		
		List<Banda> allBandas = service.getAllBandas();
		
		return allBandas != null ? allBandas : new ArrayList<Banda>();
	}

}