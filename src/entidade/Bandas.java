package entidade;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bandas")
@XmlAccessorType (XmlAccessType.FIELD)
public class Bandas {
	
	@XmlElement(name="banda")
	private ArrayList<Banda> bandaLista;

	public ArrayList<Banda> getBandaLista() {
		return bandaLista;
	}

	public void setBandaLista(ArrayList<Banda> bandaLista) {
		this.bandaLista = bandaLista;
	}
}
