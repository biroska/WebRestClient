package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import service.Service;
import converter.ConverterHelper;
import dto.DTOBanda;
import dto.Teste;
import entidade.Banda;

@SessionScoped
@ManagedBean(name="mainMB")
public class mainMB implements Serializable {

	private Service service = new Service();
	
	private ArrayList<Banda> listaBandas;
	private ArrayList<DTOBanda> listaDTOBandas;
	private String parametroConsulta;
	public DTOBanda selectedBand = new DTOBanda();
	private Teste gambi = new Teste();
	
	public mainMB(){
		this.listaBandas = new ArrayList<Banda>( service.getAllBandas() );
		this.listaDTOBandas = new ArrayList<DTOBanda>( ConverterHelper.convertFrom( listaBandas ) );
		this.selectedBand = new DTOBanda();
	}
	
	public void editarAlbum( String paramAlterar) {
		
		System.out.println("mainMB.buttonAction(): " + paramAlterar);

		addMessage("Registro Atualizado: " + getBandaFromList( paramAlterar ).getNomeAlbum() );
	}
	
	public void removerAlbum( String paramAlterar) {
		
		System.out.println("mainMB.remover(): " + paramAlterar );
		
		addMessage("Registro Removido: " + getBandaFromList( paramAlterar ).getNomeAlbum() );
	}
	
	public void novo() {
		this.selectedBand = new DTOBanda();
		this.selectedBand.setNomeAlbum("TesteNomeAlbum");
		this.parametroConsulta = "PORRAMETO!!!!!!!!";
		this.gambi.setTesteNome("adadadadadadasd");
		System.out.println("mainMB.novo()");
	}
	
	public void consultar() {
		
		System.out.println("mainMB.consultar(): " + parametroConsulta );
		
		addMessage("Consulta concluida");
	}
	
	public void onRowSelect( SelectEvent event ){
		System.out.println("mainMB.onRowSelect(): " + selectedBand.getNomeAlbum() );
		
		addMessage("Album selecionado " + selectedBand.getNomeAlbum());
	}
	
	private DTOBanda getBandaFromList( String id ){
		return listaDTOBandas.get( listaDTOBandas.indexOf( new DTOBanda( id ) ) );
	}
	
	private void addMessage(String text){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, text, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public ArrayList<Banda> getListaBandas() {
		return listaBandas;
	}

	public void setListaBandas(ArrayList<Banda> listaBandas) {
		this.listaBandas = listaBandas;
	}
	
	
	public ArrayList<DTOBanda> getListaDTOBandas() {
		return listaDTOBandas;
	}
	
	public void setListaDTOBandas(ArrayList<DTOBanda> listaDTOBandas) {
		this.listaDTOBandas = listaDTOBandas;
	}
	
	public String getParametroConsulta() {
		return parametroConsulta;
	}

	public void setParametroConsulta(String parametroConsulta) {
		this.parametroConsulta = parametroConsulta;
	}

	public DTOBanda getSelectedBand() {
		return selectedBand;
	}

	public void setSelectedBand(DTOBanda selectedBand) {
		this.selectedBand = selectedBand;
	}

	public Teste getGambi() {
		return gambi;
	}

	public void setGambi(Teste gambi) {
		this.gambi = gambi;
	}
	
	
}