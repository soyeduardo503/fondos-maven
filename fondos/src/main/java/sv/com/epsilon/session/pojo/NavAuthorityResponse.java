package sv.com.epsilon.session.pojo;

public class NavAuthorityResponse implements ResponseWS{

	private String authority="N";

	
	public NavAuthorityResponse(String authority) {
		super();
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	
}
