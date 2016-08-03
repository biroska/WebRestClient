package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import service.Service;
import util.JAXBAux;
import converter.ConverterHelper;
import dto.DTOBanda;
import entidade.Album;
import entidade.Banda;

@SessionScoped
@ManagedBean(name="mainMB")
public class MainMB implements Serializable {

	private static final long serialVersionUID = 6760065399209949225L;

	private Service service = new Service();
	
	private ArrayList<Banda> listaBandas;
	private ArrayList<DTOBanda> listaDTOBandas;
	private String parametroConsulta;
	public DTOBanda selectedBand = new DTOBanda();
	private DTOBanda bandaModal;
	
	private String errorMessage = null;
	
	@ManagedProperty(value="#{sessionMB}")
	private SessionMB sessionMB;
	
	public SessionMB getSessionMB() {
		return sessionMB;
	}

	public void setSessionMB(SessionMB sessionMB) {
		this.sessionMB = sessionMB;
	}

	public MainMB(){
		this.listaBandas = new ArrayList<Banda>( service.getAllBandas() );
		this.listaDTOBandas = new ArrayList<DTOBanda>( ConverterHelper.convertFrom( listaBandas ) );
		this.selectedBand = new DTOBanda();
		this.bandaModal = new DTOBanda();
		this.errorMessage = null;
	}
	
	public void editarAlbum( String id) {
		
		System.out.println("mainMB.buttonAction(): " + id);
		
		this.bandaModal = getBandaFromList( id );
		
		service.editarAlbum( id, new Album( this.bandaModal.getIdAlbum(), this.bandaModal.getNomeAlbum(), this.bandaModal.getAnoDeLancamentoAlbum() ) );

		this.bandaModal = getBandaFromList( id );
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void removerAlbum( String paramAlterar) {
		
		this.bandaModal = getBandaFromList( paramAlterar );
		
		service.removerAlbum( this.bandaModal.getBanda(), new Album( this.bandaModal.getIdAlbum(), this.bandaModal.getNomeAlbum(), this.bandaModal.getAnoDeLancamentoAlbum() ) );
		
		addMessage("Registro Removido: " + bandaModal.getBanda().getNome() );
		
		recarregarListaBandas();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void removerBanda( String paramAlterar) {
//		public void removerBanda( Long idBanda ){
		
		this.bandaModal = getBandaFromList( paramAlterar );
		
		service.removerAlbum( this.bandaModal.getBanda(), new Album( this.bandaModal.getIdAlbum(), this.bandaModal.getNomeAlbum(), this.bandaModal.getAnoDeLancamentoAlbum() ) );
		
		addMessage("Registro Removido: " + bandaModal.getBanda().getNome() );
		
		recarregarListaBandas();
	}

	public void novo() {
		this.bandaModal = new DTOBanda();
		System.out.println("mainMB.novo()");
	}
	
	public void salvar( ) {

		try {
			
			System.out.println("MainMB.salvar(): " + sessionMB );
			
			errorMessage = null;
			System.out.println("mainMB.salvar(): " + bandaModal.getId());
			
			if ( bandaModal.getId() != null ){ // EDICAO
				this.bandaModal = getBandaFromList( bandaModal.getId() );
				service.editarAlbum( bandaModal.getId(), new Album( this.bandaModal.getIdAlbum(), this.bandaModal.getNomeAlbum(), this.bandaModal.getAnoDeLancamentoAlbum() ) );
				
			} else {
				// Novo registro
				
				Banda convertFrom = ConverterHelper.convertFrom(bandaModal);
				String bandaToXML = JAXBAux.bandaToXML( convertFrom );
				
				//TODO - Passar o xml gerado para o servico
				
//				service.salvarBanda( ConverterHelper.convertFrom(bandaModal) );
				service.salvarBanda( bandaToXML );
			}

			addMessage("Registro salvo: " + bandaModal.getBanda().getNome());
			
			recarregarListaBandas();
		} catch (Exception e) {
			e.printStackTrace();
			addMessage("Ocorreu um erro " + e.getMessage());
			errorMessage = "Ocorreu um erro: " + e.getMessage();
		}
	}
	
	public void consultar() {
		
		System.out.println("mainMB.consultar(): " + parametroConsulta );
		
		addMessage("Consulta concluida");
	}

	public void recarregarListaBandas() {
		this.listaDTOBandas = ConverterHelper.convertFrom( (ArrayList) service.getAllBandas() );
		System.out.println("mainMB.recarregarListaBandas()");
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}