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
	private DTOBanda bandaModal;
	
	public mainMB(){
		this.listaBandas = new ArrayList<Banda>( service.getAllBandas() );
		this.listaDTOBandas = new ArrayList<DTOBanda>( ConverterHelper.convertFrom( listaBandas ) );
		this.selectedBand = new DTOBanda();
		this.bandaModal = new DTOBanda();
	}
	
	public void editarAlbum( String paramAlterar) {
		
		System.out.println("mainMB.buttonAction(): " + paramAlterar);

		this.bandaModal = getBandaFromList( paramAlterar );
		
	}
	
	public void removerAlbum( String paramAlterar) {
		
		System.out.println("mainMB.remover(): " + paramAlterar );
		this.bandaModal = getBandaFromList( paramAlterar );
		addMessage("Registro salvo: " + bandaModal.getBanda().getNome() );
	}
	
	public void novo() {
		this.bandaModal = new DTOBanda();
		System.out.println("mainMB.novo()");
	}
	
	public void salvar() {
		System.out.println("mainMB.salvar()");
		
		addMessage("Registro salvo: " + bandaModal.getBanda().getNome() );
	}
	
	public void consultar() {
		
		System.out.println("mainMB.consultar(): " + parametroConsulta );
		
		addMessage("Consulta concluida");
	}
	
	public void onRowSelect( SelectEvent event ){
		System.out.println("mainMB.onRowSelect(): " + selectedBand.getNomeAlbum() );
		
		this.bandaModal = selectedBand;
		
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

	public DTOBanda getBandaModal() {
		return bandaModal;
	}

	public void setBandaModal(DTOBanda bandaModal) {
		this.bandaModal = bandaModal;
	}
}