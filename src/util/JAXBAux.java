package util;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import entidade.Banda;

public class JAXBAux {
	
	public static String bandaToXML( Banda banda ) {

		JAXBContext jaxbContext = null;
		Marshaller jaxbMarshaller = null;
		
		StringWriter os = new StringWriter();
		
		try {
			jaxbContext = JAXBContext.newInstance(Banda.class);
			jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal( banda, os);
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return os == null ? null : os.toString();
	}

}
