package converter;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import entidade.Banda;
import entidade.Bandas;

public class JAXB {

	public static ArrayList<Banda> jaxbXMLToObject( InputStream stream ) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Bandas.class);
		Unmarshaller un = context.createUnmarshaller();
//		ArrayList<Banda> obj = (ArrayList<Banda>) un.unmarshal( stream );
		Bandas obj = (Bandas) un.unmarshal( stream );
		
		if ( obj != null && obj.getBandaLista() != null && !obj.getBandaLista().isEmpty() )
			return obj.getBandaLista();
		else
			return null;		
	}
}