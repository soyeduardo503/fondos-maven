package sv.com.epsilon.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sv.com.epsilon.ctrlr.wsclient.AppCtrlr;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.session.pojo.SistemaResponse;

//@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter{

	public AuthorizationFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest reqt = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		UsuarioSessionMB usuarioSession;
		HttpSession ses = reqt.getSession(false);
		String autho=reqt.getHeader("Authorization");
		if(autho==null) {
			autho=request.getParameter("token");
		}
		if(ses==null)
			ses=reqt.getSession();
		String reqURI = reqt.getRequestURI();
		if( reqURI.indexOf("/public/") >= 0
				|| reqURI.contains("javax.faces.resource")){
			chain.doFilter(request, response);
			return ;
		}
		//Log.info("Session active "+(ses!=null?ses.getAttribute("usuarioSessionMB"):" false"));//usuarioSessionMB
		if( (reqURI.indexOf("/security/index.xhtml")>=0)) {
			
			{
				  
			        
//				if(autho!=null&&!autho.equals("")) {    
//					ses.setAttribute("token",reqt.getHeader("Authorization"));
//				//UsuarioSessionMB us=new UsuarioSessionMB();
//				//us.addValue("token", reqt.getHeader("Authorization"));
//				//ses.setAttribute("usuarioSessionMB",us );
//				//	resp.addCookie(new Cookie("token",reqt.getHeader("Authorization")));
//				}
//				if(ses!=null) {
//					usuarioSession=(UsuarioSessionMB)ses.getAttribute("usuarioSessionMB");
//					if(usuarioSession!=null)
//						usuarioSession.addValue("token", reqt.getHeader("Authorization"));
//				}
				chain.doFilter(request, response);
				return ;
			}
			
		}
		
			
			
			if (ses!=null&&ses.getAttribute("usuarioSessionMB") != null) {
				usuarioSession=(UsuarioSessionMB)ses.getAttribute("usuarioSessionMB");
				if(ses!=null)
					usuarioSession=(UsuarioSessionMB)ses.getAttribute("usuarioSessionMB");
				if(usuarioSession!=null) {
					if(usuarioSession.getValue("token")!=null) {
						AppCtrlr appCtrlr=new AppCtrlr();
						try {
							String yn=appCtrlr.validateNav(usuarioSession.getToken(), reqt.getContextPath(), reqURI,usuarioSession.getIdRol(),usuarioSession.getUser());
							if(yn.equals("N")) 
							{ 
								SistemaResponse sis = new AppCtrlr().obtenerPagePrincipal(reqt.getContextPath(), usuarioSession.getToken());
								resp.sendRedirect(sis.toString());
									return ;
							}
		
//							if(yn.equals("V")) {
//								resp.sendRedirect("http://localhost:8080/e-security/login.xhtml");
//								return ;
//							}
							if(yn.equals("Y")) 
								chain.doFilter(request, response);
						}catch (Exception e) {
							SistemaResponse sis;
							try {
								sis = new AppCtrlr().obtenerPagePrincipal(reqt.getContextPath(), usuarioSession.getToken());
								resp.sendRedirect(sis.pageLogout());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								
								e1.printStackTrace();
								resp.sendRedirect("http://localhost:8080/e-security/");
							}
							
						}
					}
				}
				
			
			
			        
			
//				chain.doFilter(request, response);
//				return ;
			
			
		}
		
		
			
				
			
			
		
		
	}

}
