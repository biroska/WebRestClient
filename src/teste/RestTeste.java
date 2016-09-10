package teste;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import converter.ConverterHelper;
import entidade.Banda;
import util.Constants;
import util.JAXBAux;

public class RestTeste {

	public static void main(String[] args) {

		
//		restGetExampleJersey();
		
		restPostExampleJersey();
		
		
	}

	private static void restGetExampleJersey() {
		Client client = Client.create();
		
		String getUrl = Constants.WEBSERVICE_ADDRESS.GET_ALL;
		
		WebResource webResource = client.resource(getUrl);
		
		ClientResponse response = webResource.accept("text/xml").get(ClientResponse.class);
		
		if(response.getStatus()!=200){
			throw new RuntimeException("HTTP Error: "+ response.getStatus());
		}
		
		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
	}
	
	private static void restPostExampleJersey() {
		
		Client client = Client.create();
		
		String postUrl = Constants.WEBSERVICE_ADDRESS.ADD;
		
		WebResource webResource = client.resource(postUrl);
		
		String inputData = buildBanda();
		
		ClientResponse response = webResource.type("text/xml").post(ClientResponse.class,inputData);
		
		if(response.getStatus()!=200){
			throw new RuntimeException("HTTP Error: "+ response.getStatus());
		}
		
		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
	}
	
	private static String buildBanda(){
		
		Banda banda = new Banda( "Teste rest post",  1999L );
		String bandaToXML = JAXBAux.bandaToXML( banda );
		
		
		return bandaToXML;
	}

}
