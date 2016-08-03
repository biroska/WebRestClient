package webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;

import converter.JAXB;
import entidade.Album;
import entidade.Banda;
import util.ConnectionAux;
import util.Constants;

public class WebServiceAccess implements ServiceAccess {

	private static ArrayList<Banda> listaBandas = new ArrayList<Banda>();
	
	/* Inicializa a base de bandas */
	static {
		
		Long id = 1L;
		
		listaBandas.add( new Banda( id++, "MOCK", 1968L) );
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
	
	@Override
	public List<Banda> getAllBandas() {

		listaBandas = new ArrayList<Banda>();
		
		HttpURLConnection conn = null;
		
		try {
			 
			conn = ConnectionAux.getConnection(  Constants.WEBSERVICE_ADDRESS.GET_ALL, 
												 Constants.REQUEST_TYPE.GET,
												 Constants.REQUEST_PROPERTY.XML);
	 
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
	 
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
	
			try {
				listaBandas = JAXB.jaxbXMLToObject( conn.getInputStream() );
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
	
	@Override
	public void removerAlbum( Banda banda, Album album ){
//		int index = listaBandas.indexOf( banda );
//		boolean remove = listaBandas.get( index ).getAlbuns().remove( album );
		
		if ( banda == null || banda.getId() == null ){
			return;
		}
		
		if ( album == null || album.getId() == null ){
			return;
		}
		
		HttpURLConnection conn = null;
		
		try {
			conn = ConnectionAux.getConnection( Constants.WEBSERVICE_ADDRESS.REMOVE +
					   								banda.getId()+"/"+album.getId(),
												Constants.REQUEST_TYPE.DELETE,
												Constants.REQUEST_PROPERTY.PLAIN);
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	@Override
	public void removerBanda( Long idBanda ){
		
		if ( idBanda == null || idBanda < 0 ){
			return;
		}
		
		HttpURLConnection conn = null;
		
		try {
			conn = ConnectionAux.getConnection( Constants.WEBSERVICE_ADDRESS.REMOVE + idBanda,
												Constants.REQUEST_TYPE.GET,
												Constants.REQUEST_PROPERTY.XML);
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	@Override
	public void editarAlbum( String id, Album album ){
		
		String[] split = id.split("A");
		int idBanda = Integer.valueOf( split[0].substring( 1 ) ) -1;
		int idAlbum = Integer.valueOf( split[1] );
		
//		System.out.println("WebServiceAccess.editarAlbum(): " + idBanda +" ========= "+idAlbum);
		listaBandas.get( idBanda ).getAlbuns().get( idAlbum ).setNome( album.getNome() );
		listaBandas.get( idBanda ).getAlbuns().get( idAlbum ).setAnoDeLancamento( album.getAnoDeLancamento() );
	}
	
	@Override
	public void salvarBanda( Banda banda ){
		
		banda.setId( (long) listaBandas.size() +1 );
		
		for (int i = 0; i < banda.getAlbuns().size(); i++) {
			banda.getAlbuns().get( i ).setId( (long) i );
		}
		
		listaBandas.add( banda );
	}
	
	@Override
	public void salvarBanda( String xml ){
		
		if ( StringUtils.isBlank( xml ) ){
			return;
		}
		
		HttpURLConnection conn = null;
		
		try {
			conn = ConnectionAux.getConnection( Constants.WEBSERVICE_PREFIX,
												Constants.REQUEST_TYPE.POST,
												Constants.REQUEST_PROPERTY.PLAIN);
			
			conn.setDoOutput( true );
			conn.setDoInput( true );
			
			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			
			OutputStream os = conn.getOutputStream();
			
			OutputStreamWriter osw = new OutputStreamWriter( os );
			osw.write( xml );
			osw.flush();
			// TODO ERRO 404
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
}