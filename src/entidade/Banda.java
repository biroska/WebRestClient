package entidade;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Banda {

	private String nome;
	private Long anoDeFormacao;
	private Long id;
	private ArrayList<Album> albuns;
	
	public Banda(){
		albuns = new ArrayList<Album>();
	}
	
	public Banda(String nome, Long anoDeFormacao) {
		super();
		this.nome = nome;
		this.anoDeFormacao = anoDeFormacao;
		this.albuns = new ArrayList<Album>();
	}

	public Banda( Long id){
		this.id = id;
	}
	
	public Banda( Long id, String nome, Long anoDeFormacao ){
		
		this.setId( id );
		this.setNome( nome );
		this.setAnoDeFormacao( anoDeFormacao );
		this.albuns = new ArrayList<Album>();
		
		for ( Long i = 0L; i <= id; i++){
			this.albuns.add( new Album( i, nome + "_" + i, anoDeFormacao + i )  );
		}
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getAnoDeFormacao() {
		return anoDeFormacao;
	}

	public void setAnoDeFormacao(Long anoDeFormacao) {
		this.anoDeFormacao = anoDeFormacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<Album> getAlbuns() {
		return albuns;
	}

	public void setAlbuns(ArrayList<Album> albuns) {
		this.albuns = albuns;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Banda other = (Banda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}