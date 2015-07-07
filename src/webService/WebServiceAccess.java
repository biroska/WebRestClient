package webService;

import java.util.ArrayList;
import java.util.List;

import entidade.Banda;

public class WebServiceAccess {

	private static ArrayList<Banda> listaBandas = new ArrayList<Banda>();

	/* Inicializa a base de bandas */
	static {
		
		int id = 1;
		
		listaBandas.add( new Banda( id++, "Led Zeppelin", 1968) );
		listaBandas.add( new Banda( id++, "AC/DC", 1973) );
		listaBandas.add( new Banda( id++, "Rolling Stones", 1962) );
		listaBandas.add( new Banda( id++, "Black Sabbath", 1968 ) );
		listaBandas.add( new Banda( id++, "Cream", 1966) );
		listaBandas.add( new Banda( id++, "The Animals", 1964) );
		listaBandas.add( new Banda( id++, "The Who", 1964) );
		listaBandas.add( new Banda( id++, "Creedence Clearwater Revival", 1967) );
		listaBandas.add( new Banda( id++, "The Mamas & the Papas", 1966) );
		listaBandas.add( new Banda( id++, "Deep Purple", 1968) );
	}
	
	
	public List<Banda> getAllBandas() {

		return listaBandas;
	}

}
