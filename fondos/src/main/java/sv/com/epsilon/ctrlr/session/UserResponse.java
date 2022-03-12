/**
 * 
 */
package sv.com.epsilon.ctrlr.session;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;




import sv.com.epsilon.response.Response;

/**
 * @author 50364
 *
 */

public class UserResponse implements Response,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2067841808957798002L;
	private String status="-1";
	private String user;
	private Integer idUser;
	private String token;
	private String look;
	private String lang;
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	public String getLook() {
		return look;
	}
	public void setLook(String look) {
		this.look = look;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	
	
}
