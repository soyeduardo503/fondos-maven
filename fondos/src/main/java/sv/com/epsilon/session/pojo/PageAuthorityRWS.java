/**
 * 
 */
package sv.com.epsilon.session.pojo;

/**
 * @author 50364
 *
 */
public class PageAuthorityRWS implements RequestWS{

	private String context;
	private String url;
	private Integer idRol;
	private String user;
	public PageAuthorityRWS() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PageAuthorityRWS(String context, String url, Integer idRol, String user) {
		super();
		this.context = context;
		this.url = url;
		this.idRol = idRol;
		this.user = user;
	}


	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
	
}
