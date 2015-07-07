package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import service.Service;
import entidade.Album;
import entidade.Banda;

@SessionScoped
@ManagedBean(name="mainMB")
public class mainMB implements Serializable {

	private static final long serialVersionUID = -7440852920428716001L;
	
	private Service service = new Service();
	
	private ArrayList<Banda> listaBandas;
	private ArrayList<InnerDTOBanda> listaDTOBandas;
	private String parametroConsulta;
	
	public mainMB(){
		listaBandas = new ArrayList<Banda>( service.getAllBandas() );
		listaDTOBandas = new ArrayList<InnerDTOBanda>( convertFrom( listaBandas ) );
	}
	
	public void editarAlbum( String paramAlterar) {
		
		System.out.println("mainMB.buttonAction(): " + paramAlterar);

		addMessage("Registro Atualizado");
	}
	
	public void removerAlbum( String paramAlterar) {
		
		System.out.println("mainMB.remover(): " + paramAlterar );
		
		addMessage("Registro Removido");
	}
	
	public void consultar() {
		
		System.out.println("mainMB.consultar(): " + parametroConsulta );
		
		addMessage("Consulta concluída");
	}
	
	public void addMessage(String text){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, text, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public ArrayList<Banda> getListaBandas() {
		return listaBandas;
	}

	public void setListaBandas(ArrayList<Banda> listaBandas) {
		this.listaBandas = listaBandas;
	}
	
	
	public ArrayList<InnerDTOBanda> getListaDTOBandas() {
		return listaDTOBandas;
	}
	
	public void setListaDTOBandas(ArrayList<InnerDTOBanda> listaDTOBandas) {
		this.listaDTOBandas = listaDTOBandas;
	}
	
	public String getParametroConsulta() {
		return parametroConsulta;
	}

	public void setParametroConsulta(String parametroConsulta) {
		this.parametroConsulta = parametroConsulta;
	}

	private ArrayList<InnerDTOBanda> convertFrom( Banda banda){
		
		ArrayList<InnerDTOBanda> listaDTO = new ArrayList<InnerDTOBanda>();
		
		for (Album album : banda.getAlbuns() ) {
			InnerDTOBanda dto = new InnerDTOBanda();
			dto.setId( "B"+banda.getId()+"A"+album.getId()  );
			dto.setBanda( banda );
			dto.setIdAlbum( album.getId() );
			dto.setNomeAlbum( album.getNome() );
			dto.setAnoDeLancamentoAlbum( album.getAnoDeLancamento() );
			
			listaDTO.add( dto );
		}
		return listaDTO;
	}
	
	private ArrayList<InnerDTOBanda> convertFrom( ArrayList<Banda> lista ){
		
		ArrayList<InnerDTOBanda> listaDTO = new ArrayList<InnerDTOBanda>();
		
		for (Banda banda : lista) {
			listaDTO.addAll( convertFrom( banda ) );
		}
		
		return listaDTO;
	}
	
	public class InnerDTOBanda {
		
		private String id;
		
		private Banda banda;
		
//		Informações Album
		private int idAlbum;
		private String nomeAlbum;
		private int anoDeLancamentoAlbum;
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Banda getBanda() {
			return banda;
		}

		public void setBanda(Banda banda) {
			this.banda = banda;
		}

		public int getIdAlbum() {
			return idAlbum;
		}

		public void setIdAlbum(int idAlbum) {
			this.idAlbum = idAlbum;
		}

		public String getNomeAlbum() {
			return nomeAlbum;
		}

		public void setNomeAlbum(String nomeAlbum) {
			this.nomeAlbum = nomeAlbum;
		}

		public int getAnoDeLancamentoAlbum() {
			return anoDeLancamentoAlbum;
		}

		public void setAnoDeLancamentoAlbum(int anoDeLancamentoAlbum) {
			this.anoDeLancamentoAlbum = anoDeLancamentoAlbum;
		}

		private String testeInner = "testeInner";

		public String getTesteInner() {
			return testeInner;
		}

		public void setTesteInner(String testeInner) {
			this.testeInner = testeInner;
		}
	}
}