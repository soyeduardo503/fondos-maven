/**
 * 
 */
package sv.com.epsilon.ctrlr.wsclient;

import sv.com.epsilon.ctrlr.session.UserSessionToken;
import sv.com.epsilon.session.pojo.ContextRolRWS;
import sv.com.epsilon.session.pojo.NavAuthorityResponse;
import sv.com.epsilon.session.pojo.PageAuthorityRWS;
import sv.com.epsilon.session.pojo.SessionActiveRequest;
import sv.com.epsilon.session.pojo.SessionActiveResponse;
import sv.com.epsilon.session.pojo.SistemaRWS;
import sv.com.epsilon.session.pojo.SistemaResponse;
import sv.com.epsilon.session.pojo.Token;
import sv.com.epsilon.session.pojo.TokenValidRWS;

/**
 * @author 50364
 *
 */
public class AppCtrlr {

	public void validateActiveToken(String token,String context) throws Exception{
		String endpoint =  "/user/token";
		TokenValidRWS tokenValidate=new TokenValidRWS(token, context);
		
			Token resp = (Token) new WSClientCtrlr().sendRequest(tokenValidate, endpoint,Token.class,token);
			if(!resp.isValid()) {
				throw new Exception("Contexto no permitido");
			}
		
	
	}
	
	public void getDataUser(String token,String context) throws Exception{
		String endpoint =  "/user";
		TokenValidRWS tokenValidate=new TokenValidRWS(token, context);
		
		UserSessionToken resp = (UserSessionToken) new WSClientCtrlr().sendRequest(tokenValidate, endpoint,UserSessionToken.class,token);
		if(resp.getAct().equals("I")) {
				throw new Exception("Contexto no permitido");
		}
		
	
	}
	
	public SistemaResponse navContext(String token,String context,Integer idRol ) throws Exception{
		String endpoint =  "/context/update";
		ContextRolRWS contextReq=new ContextRolRWS(token, context, idRol);
		
		NavAuthorityResponse resp = (NavAuthorityResponse) new WSClientCtrlr().sendRequest(contextReq, endpoint,NavAuthorityResponse.class,token);
		if(resp.getAuthority().equals("N")) {
			throw new Exception("No Autorizado");
		}
		endpoint =  "/context/";
		SistemaResponse sis=(SistemaResponse)new WSClientCtrlr().sendRequest(new SistemaRWS(context), endpoint,SistemaResponse.class,token);
		
		return sis;
	
	}
	
	public String validateNav(String token, String context, String url,Integer idRol,String user) throws Exception {
		String endpoint="/context/nav";
		PageAuthorityRWS param=new PageAuthorityRWS(context,url,idRol,user);
		NavAuthorityResponse resp = (NavAuthorityResponse)new WSClientCtrlr().sendRequest(param, endpoint,NavAuthorityResponse.class,token);
		return resp.getAuthority();
	}
	
	public SistemaResponse obtenerPagePrincipal( String context,String token) throws Exception {
		String endpoint="/context/";
		SistemaRWS param=new SistemaRWS(context);
		SistemaResponse resp = (SistemaResponse)new WSClientCtrlr().sendRequest(param, endpoint,SistemaResponse.class,token);
		return resp;
	}
	
	

	public SessionActiveResponse findSessionActive(String token,String mac) throws Exception {
		String endpoint="/context/token/mac";
		//String mac, String user, Integer idRol, String context, Date expire, String token
		SessionActiveRequest req=new SessionActiveRequest();
		req.setToken(token);
		try {
			SessionActiveResponse	session = (SessionActiveResponse) new WSClientCtrlr().sendRequest(req, endpoint,SessionActiveResponse.class, token);
			return session;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw  e;
		} 
		
	}

}
