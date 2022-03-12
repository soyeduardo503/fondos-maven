/**
 * 
 */
package sv.com.epsilon.ctrlr.wsclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import sv.com.epsilon.ctrlr.session.UserResponse;
import sv.com.epsilon.entities.Sistema;
import sv.com.epsilon.entities.UserRols;
import sv.com.epsilon.session.pojo.UserRWS;

/**
 * @author 50364
 *
 */
public class SessionCtrlr {

	/**
	 * 
	 */
	public SessionCtrlr() {
	
	}
	
	
	public UserResponse authenticate(String user,String pass) {
		String endpoint =  "/user/login";
		UserRWS param=new UserRWS(user,pass);
		
		UserResponse resp;
		try {
			resp = (UserResponse) new WSClientCtrlr().sendRequest(param, endpoint,UserResponse.class,"");
			System.out.println(new Gson().toJson(resp));
			clearRol(resp);
			return resp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//, ParametroResWS.class);
		
		
		return new UserResponse();
	}


	private void clearRol(UserResponse resp) {
		List<UserRols> lrol=resp.getListRol();
		List<UserRols> rols=new ArrayList<UserRols>();
		HashMap<Integer,Sistema> sis=new HashMap<Integer, Sistema>();
		for(UserRols u:lrol) {
			
			if(sis.get( u.getIdRol().getIdSystem().getIdSystem())==null){
				sis.put(u.getIdRol().getIdSystem().getIdSystem(), u.getIdRol().getIdSystem());
				rols.add(u);
			}
		}
		resp.setListRol(rols);
	}
	
	
}
