/**
 * 
 */
package sv.com.epsilon.ctrlr.wsclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import sv.com.epsilon.ctrlr.session.UserResponse;

import sv.com.epsilon.session.pojo.UserRWS;
import sv.com.epsilon.util.Log;

/**
 * @author 50364
 *
 */
public class WSClientCtrlr {

	/**
	 * 
	 */
	private String url="http://localhost:8181/WSEpsilonUSER"; 
	
	public WSClientCtrlr() {
		// TODO Auto-generated constructor stub
	}
	

	public WSClientCtrlr(String url) {
		super();
		this.url = url;
	}


	public Object sendRequest(String endPoint,Class typeClass) throws Exception {
		
		String urlWS=this.url+endPoint;
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
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type",  MediaType.APPLICATION_JSON);
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


		
		return new Gson().fromJson( sb.toString(),typeClass);
	}
	
	
	/**
	 * @param peticion
	 * @param endPoint
	 * @return
	 * @throws Exception
	 * 20 dic. 2021
	 */
	public Object sendRequestGet(String endPoint,Class typeClass,String token) throws Exception {
	
		return sendRequest(null, "GET", endPoint, typeClass, token); 
	}
	
	/**
	 * @param peticion
	 * @param endPoint
	 * @return
	 * @throws Exception
	 * 20 dic. 2021
	 */
	public Object sendRequest(Object peticion,String endPoint,Class typeClass,String token) throws Exception {
		return sendRequest(peticion,"POST",endPoint,typeClass,token);
	}
	public Object sendRequest(Object peticion,String method,String endPoint,Class typeClass,String token) throws Exception {
		String peticionjson=(new Gson().toJson(peticion));
		String urlWS=this.url+endPoint;
//		try {
//		if(PropertiesUtil.value("debugOnbase")!=null&&PropertiesUtil.value("debugOnbase").equals("1"))
			Log.info("Peticion -> "+peticionjson);
			
//		}catch (Exception e) {
//			Log.error(e, "debug onbase");
//		}
		
		Log.info(urlWS);
		
		StringBuilder sb=new StringBuilder();
		URL url = new URL(urlWS);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type",  MediaType.APPLICATION_JSON);
		conn.setRequestProperty("Authorization", token);
		conn.setConnectTimeout(50000);
		conn.setReadTimeout(180000);
		OutputStream os = conn.getOutputStream();
		os.write(peticionjson.getBytes());
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


		
		return new Gson().fromJson( sb.toString(),typeClass);
	}
	public List sendRequestForList(String endPoint,Type typeClass,String token) throws Exception {
		return sendRequestForList(null, "GET", endPoint, typeClass, token);
	}
	
	public List sendRequestForList(Object peticion,String method,String endPoint,Type typeClass,String token) throws Exception {
		String peticionjson=(new Gson().toJson(peticion));
		String urlWS=this.url+endPoint;
//		try {
//		if(PropertiesUtil.value("debugOnbase")!=null&&PropertiesUtil.value("debugOnbase").equals("1"))
			Log.info("Peticion -> "+peticionjson);
			
//		}catch (Exception e) {
//			Log.error(e, "debug onbase");
//		}
		
		Log.info(urlWS);
		
		StringBuilder sb=new StringBuilder();
		URL url = new URL(urlWS);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type",  MediaType.APPLICATION_JSON);
		conn.setRequestProperty("Authorization", token);
		conn.setConnectTimeout(50000);
		conn.setReadTimeout(180000);
		OutputStream os = conn.getOutputStream();
		if(peticion!=null)
		os.write(peticionjson.getBytes());
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


		
		return new Gson().fromJson( sb.toString(),typeClass);
	}
	
	/**
	 * Metodo que recibe la peticion para consumir el ws
	 * 
	 * 
	 * @param req
	 * @param endPoint
	 * @param classType
	 * @return
	 * @throws Exception
	 * 17 dic. 2021
	 */
	
	
	public Object connectGet(String endPoint,Class classType) throws Exception{
		return sendRequestGet(endPoint,classType,"");
		//String endPoint,Class typeClass,String token
	}
	
	public Object connect(Object req,String method,String endPoint,Class classType) throws Exception{
		return sendRequest(req, method, endPoint, classType, endPoint);
//		javax.ws.rs.client.ClientBuilder cb = ClientBuilder.newBuilder();
//		
//		String peticion=req!=null?new Gson().toJson(req):"";
//		System.out.println(peticion);
//		javax.ws.rs.client.Client client = cb.build();
//		
//		Response response =null;
//		Object  obj=null;
//
//		System.out.println(url+endPoint);
//		try {
//			
//			  WebTarget target = client.target(url+endPoint);
//			  Builder b=target.request();
//			  if(method.equals("GET"))
//				 response= b.get();
//				else				  
//		            response=     b.post(Entity.json(peticion));
//			 
//		             
//			    if (response.getStatus() != 201) throw new RuntimeException(
//		                 "Failed to create");
//		        obj  =new Gson().fromJson( response.toString(),classType);
//		         
//		      return obj;
//			
//			} catch (Exception e) {
//		      Log.error(e, "error en obtener respuesta");
//		      throw e;
//		    } finally {
//		    	  response=null;
//		    		        
//		    }   

	}
	
	public List connectGet(String endPoint,Type classType,String token) throws Exception{
		return connect(null,"GET",endPoint,classType,token);
	}
	
	public List connect(Object req,String method,String endPoint,Type classType,String token) throws Exception{
		javax.ws.rs.client.ClientBuilder cb = ClientBuilder.newBuilder();
		
		String peticion=req!=null?new Gson().toJson(req):"";
		System.out.println(peticion);
		javax.ws.rs.client.Client client = cb.build();
		
		Response response =null;
		List  obj=null;

		System.out.println(url+endPoint);
		try {
			//conn.setRequestProperty("Authorization", token);
			  WebTarget target = client.target(url+endPoint).property("Authorization", token);
			  Builder b=target.request();
			  if(method.equals("GET"))
				 response= b.get();
				else				  
		            response=     b.post(Entity.json(peticion));
			 
		             
			    if (response.getStatus() != 201) throw new RuntimeException(
		                 "Failed to create");
		        obj  =new Gson().fromJson( response.toString(),classType);
		         
		      return obj;
			
			} catch (Exception e) {
		      Log.error(e, "error en obtener respuesta");
		      throw e;
		    } finally {
		    	  response=null;
		    		        
		    }   

	}
	
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static void main(String[] args) throws Exception {
		String endpoint =  "/user/login";
		UserRWS param=new UserRWS("rh","123");
		
		UserResponse resp=  (UserResponse) new WSClientCtrlr().sendRequest(param, endpoint,UserResponse.class,"");//, ParametroResWS.class);
		System.out.println(new Gson().toJson(resp));
		
	}
	
}
