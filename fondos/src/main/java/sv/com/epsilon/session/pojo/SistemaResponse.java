/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.session.pojo;

import java.io.Serializable;

/**
 *
 * @author eduardx
 */

public class SistemaResponse implements Serializable {

	 private static final long serialVersionUID = 1L;

	    private Integer idSystem;

	    private String name;

	    private String pagePrincipal;
	    private String context;

	    private String description;

	    private String status;
	    private String look;

	    private String icon;

	    private String port;

	    private String server;
	    
	    
	    private String protocol;

	    

	    public SistemaResponse() {
	    }

	    public SistemaResponse(Integer idSystem) {
	        this.idSystem = idSystem;
	    }

	    public SistemaResponse(Integer idSystem, String name, String pagePrincipal, String context) {
	        this.idSystem = idSystem;
	        this.name = name;
	        this.pagePrincipal = pagePrincipal;
	        this.context = context;
	    }

	    public Integer getIdSystem() {
	        return idSystem;
	    }

	    public void setIdSystem(Integer idSystem) {
	        this.idSystem = idSystem;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getPagePrincipal() {
	        return pagePrincipal;
	    }

	    public void setPagePrincipal(String pagePrincipal) {
	        this.pagePrincipal = pagePrincipal;
	    }

	    public String getContext() {
	        return context;
	    }

	    public void setContext(String context) {
	        this.context = context;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    

//	    
//	    public List<Option> getOptionList() {
//	        return optionList;
//	    }
	//
//	    public void setOptionList(List<Option> optionList) {
//	        this.optionList = optionList;
//	    }

	    public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public String getServer() {
			return server;
		}

		public void setServer(String server) {
			this.server = server;
		}

		public String getProtocol() {
			return protocol;
		}

		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}

		public String getLook() {
			return look;
		}

		public void setLook(String look) {
			this.look = look;
		}

		@Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (idSystem != null ? idSystem.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof SistemaResponse)) {
	            return false;
	        }
	        SistemaResponse other = (SistemaResponse) object;
	        if ((this.idSystem == null && other.idSystem != null) || (this.idSystem != null && !this.idSystem.equals(other.idSystem))) {
	            return false;
	        }
	        return true;
	    }

	    
	   

		@Override
	    public String toString() {
	        return protocol+"://"+server+":"+port+"/"+context+""+pagePrincipal;
	    }
		
		public String securityStart() {
	        return protocol+"://"+server+":"+port+"/"+context+"/security/index.xhtml";
	    }

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public String pageLogout() {
			// TODO Auto-generated method stub
			return protocol+"://"+server+":"+port+"/"+context+"/login.xhtml";
		}
    
	
}
