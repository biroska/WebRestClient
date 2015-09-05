package util;

public interface Constants {
	
	public static final String PAGES_FOLDER = "pages/";
	
	public static final String WEBSERVICE_PREFIX = "http://localhost:8080/WebRest/";

	public interface WEBSERVICE_ADDRESS {
		public static final String GET_ALL = WEBSERVICE_PREFIX + "bandas";
	}

	public interface REQUEST_TYPE {
		public static final String GET = "GET";
	}
	
	public interface REQUEST_PROPERTY {
		public static final String XML = "text/xml";
	}
}
