package webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import util.Constants;
import entidade.Album;
import entidade.Banda;

public class WebServiceAccess implements ServiceAccess {

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

		try {
			 
			URL url = new URL( Constants.WEBSERVICE_ADDRESS.GET_ALL );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod( Constants.REQUEST_TYPE.GET );
			conn.setRequestProperty("Accept", Constants.REQUEST_PROPERTY.XML );
	 
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
	 
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
	 
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
	 
		  } catch (MalformedURLException e) {
	 
			e.printStackTrace();
	 
		  } catch (IOException e) {
	 
			e.printStackTrace();
		  }
		
		
		return listaBandas;
	}
	
	public void removerAlbum( Banda banda, Album album ){
		int index = listaBandas.indexOf( banda );
		boolean remove = listaBandas.get( index ).getAlbuns().remove( album );
	}
	
	public void editarAlbum( String id, Album album ){
		
		String[] split = id.split("A");
		int idBanda = Integer.valueOf( split[0].substring( 1 ) ) -1;
		int idAlbum = Integer.valueOf( split[1] );
		
//		System.out.println("WebServiceAccess.editarAlbum(): " + idBanda +" ========= "+idAlbum);
		listaBandas.get( idBanda ).getAlbuns().get( idAlbum ).setNome( album.getNome() );
		listaBandas.get( idBanda ).getAlbuns().get( idAlbum ).setAnoDeLancamento( album.getAnoDeLancamento() );
	}
	
	public void salvarBanda( Banda banda ){
		
		banda.setId( (long) listaBandas.size() +1 );
		
		for (int i = 0; i < banda.getAlbuns().size(); i++) {
			banda.getAlbuns().get( i ).setId( (long) i );
		}
		
		listaBandas.add( banda );
	}
}