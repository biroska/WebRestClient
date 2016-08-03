package webService;

import java.util.List;

import entidade.Album;
import entidade.Banda;

public interface ServiceAccess {

	public List<Banda> getAllBandas();
	
	public void removerAlbum( Banda banda, Album album );
	
	public void editarAlbum( String id, Album album );
	
	public void salvarBanda( Banda banda );

	public void removerBanda(Long idBanda);

	public void salvarBanda(String xml);
}