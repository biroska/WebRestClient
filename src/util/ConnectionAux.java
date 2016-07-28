package util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionAux {
	
	public static HttpURLConnection getConnection(String addres, String connectionType, String reposnseType) 
	throws MalformedURLException, IOException {

		HttpURLConnection conn = null;

		try {

			URL url = new URL(addres);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(connectionType);
			conn.setRequestProperty("Accept", reposnseType);

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return conn;
	}

}
