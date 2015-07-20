package dto;

import entidade.Banda;

public class DTOBanda {
	
	private String id;
	
	private Banda banda;
	
//	Informacoes Album
	private Long idAlbum;
	private String nomeAlbum;
	private Long anoDeLancamentoAlbum;
	
	public DTOBanda(){
		this.banda = new Banda();
	}
	
	public DTOBanda(String id) {
		super();
		this.id = id;
	}

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

	public Long getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(Long idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getNomeAlbum() {
		return nomeAlbum;
	}

	public void setNomeAlbum(String nomeAlbum) {
		this.nomeAlbum = nomeAlbum;
	}

	public Long getAnoDeLancamentoAlbum() {
		return anoDeLancamentoAlbum;
	}

	public void setAnoDeLancamentoAlbum(Long anoDeLancamentoAlbum) {
		this.anoDeLancamentoAlbum = anoDeLancamentoAlbum;
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
		DTOBanda other = (DTOBanda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}