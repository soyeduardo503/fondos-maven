/**
 * 
 */
package sv.com.epsilon.ctrlr.wsclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import sv.com.epsilon.session.pojo.AuthorizedContextOK;
import sv.com.epsilon.util.Log;

/**
 * @author 50364
 *
 */
public class SecurityCtrlr {

	
public String sendToken(String endPoint,String token) throws Exception {
		
		String urlWS=endPoint;
//		try {
//		if(PropertiesUtil.value("debugOnbase")!=null&&PropertiesUtil.value("debugOnbase").equals("1"))
			
			
//		}catch (Exception e) {
//			Log.error(e, "debug onbase");
//		}
		
		Log.info(urlWS);
		
		StringBuilder sb=new StringBuilder();
		URL url = new URL(urlWS);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type",  MediaType.APPLICATION_JSON);
		conn.setRequestProperty("Authorization", token);
		conn.setConnectTimeout(50000);
		conn.setReadTimeout(180000);
		OutputStream os = conn.getOutputStream();
		
		os.flush();
		Log.info("Status OK?"+conn.getResponseCode());
		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		
		Log.info("Leyendo respuesta .... \n");
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}

		conn.disconnect();


		
		return sb.toString();
	}

}
