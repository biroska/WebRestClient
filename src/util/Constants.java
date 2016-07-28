package util;

public interface Constants {
	
	public static final String PAGES_FOLDER = "pages/";
	
	public static final String WEBSERVICE_PREFIX = "http://localhost:8080/WebRest/";

	public interface WEBSERVICE_ADDRESS {
		public static final String GET_ALL = WEBSERVICE_PREFIX + "bandas";
		public static final String REMOVE = WEBSERVICE_PREFIX + "bandas/";
	}

	public interface REQUEST_TYPE {
		public static final String GET = "GET";
		public static final String DELETE = "DELETE";
	}
	
	public interface REQUEST_PROPERTY {
		public static final String XML = "text/xml";
		public static final String PLAIN = "text/plain";
	}
}
