package entidade;

public class Album {

	private String nome;
	private Long anoDeLancamento;
	private Long id;
	
	public Album(){}
	
	public Album( Long id, String nome, Long anoDeLancamento ){
		
		this.id = id;
		this.nome = nome;
		this.anoDeLancamento = anoDeLancamento;
	}
	
	public Album(String nome, Long anoDeLancamento) {
		super();
		this.nome = nome;
		this.anoDeLancamento = anoDeLancamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getAnoDeLancamento() {
		return anoDeLancamento;
	}

	public void setAnoDeLancamento(Long anoDeLancamento) {
		this.anoDeLancamento = anoDeLancamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((anoDeLancamento == null) ? 0 : anoDeLancamento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Album other = (Album) obj;
		if (anoDeLancamento == null) {
			if (other.anoDeLancamento != null)
				return false;
		} else if (!anoDeLancamento.equals(other.anoDeLancamento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}