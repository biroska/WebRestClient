package converter;

import java.util.ArrayList;

import dto.DTOBanda;
import entidade.Album;
import entidade.Banda;

public class ConverterHelper {

	
	public static ArrayList<DTOBanda> convertFrom( ArrayList<Banda> lista ){
		
		ArrayList<DTOBanda> listaDTO = new ArrayList<DTOBanda>();
		
		for (Banda banda : lista) {
			listaDTO.addAll( convertFrom( banda ) );
		}
		return listaDTO;
	}
	
	public static ArrayList<DTOBanda> convertFrom( Banda banda){
		
		ArrayList<DTOBanda> listaDTO = new ArrayList<DTOBanda>();
		
		for (Album album : banda.getAlbuns() ) {
			DTOBanda dto = new DTOBanda();
			dto.setId( "B"+banda.getId()+"A"+album.getId()  );
			dto.setBanda( banda );
			dto.setIdAlbum( album.getId() );
			dto.setNomeAlbum( album.getNome() );
			dto.setAnoDeLancamentoAlbum( album.getAnoDeLancamento() );
			
			listaDTO.add( dto );
		}
		return listaDTO;
	}	
}