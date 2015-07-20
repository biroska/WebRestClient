package webService;

import java.util.ArrayList;
import java.util.List;

import entidade.Banda;

public class WebServiceAccess {

	private static ArrayList<Banda> listaBandas = new ArrayList<Banda>();

	/* Inicializa a base de bandas */
	static {
		
		Long id = 1L;
		
		listaBandas.add( new Banda( id++, "Led Zeppelin", 1968L) );
		listaBandas.add( new Banda( id++, "AC/DC", 1973L) );
		listaBandas.add( new Banda( id++, "Rolling Stones", 1962L) );
		listaBandas.add( new Banda( id++, "Black Sabbath", 1968L) );
		listaBandas.add( new Banda( id++, "Cream", 1966L) );
		listaBandas.add( new Banda( id++, "The Animals", 1964L) );
		listaBandas.add( new Banda( id++, "The Who", 1964L) );
		listaBandas.add( new Banda( id++, "Creedence Clearwater Revival", 1967L) );
		listaBandas.add( new Banda( id++, "The Mamas & the Papas", 1966L) );
		listaBandas.add( new Banda( id++, "Deep Purple", 1968L) );
	}
	
	
	public List<Banda> getAllBandas() {

		return listaBandas;
	}

}
