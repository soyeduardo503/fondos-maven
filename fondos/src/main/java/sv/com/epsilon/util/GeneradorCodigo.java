/**
 * 
 */
package sv.com.epsilon.util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.facade.CategoriaFacade;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class GeneradorCodigo implements Serializable {

	/**
	 * 
	 */
	
	public static String  TypeCod="L";
	public static int numberSeed=48;
	public static int numberMax=57;
	public static int letterCapitalSeed=65;
	public static int maxCapitalSeed=90;
	public static int baseLenght=3;
	private CategoriaFacade categoriaFacade=new CategoriaFacade();
	public GeneradorCodigo() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public synchronized void makeCode(Categoria cat){
		
		Categoria c2=categoriaFacade.obtenerCategoriasFromCategoriasPadre(cat.getIdCategoriaPadre());
		
		
		String nextCode=((char)numberSeed)+"";
		String fatherCode="";
		fatherCode=cat.getIdCategoriaPadre()==null?"":cat.getIdCategoriaPadre().getCodigo();
		int lastChar=cat.getIdCategoriaPadre()==null?numberSeed:fatherCode.charAt(fatherCode.length()-1);
		if(c2!=null&&c2.getIdCategoria()!=null&&c2.getIdCategoria().intValue()>0){
			
			if(lastChar>=numberSeed&&lastChar<=numberMax){
				
				nextCode=nextLetter(c2,letterCapitalSeed,maxCapitalSeed);
					
				
			}else{
						
						
				nextCode=nextLetter(c2,numberSeed,numberMax);
				
			}
		}else{
			if(c2==null){
				nextCode=((char)letterCapitalSeed)+"";
			}
			if(lastChar>=numberSeed&&lastChar<=numberMax){
				nextCode=((char)letterCapitalSeed)+"";
			}
			
		}
		cat.setCodigo(nextCode.length()==1?fatherCode+nextCode:nextCode);
		
		
	}
	
	
	private String nextLetter(Categoria c2,int min, int max) {
		
		String codigo=c2.getCodigo();
		if(codigo.length()>1){
			String base=codigo.substring(0, codigo.length()-1);
			char newChar=(codigo.charAt(codigo.length()-1));
			return base+getNewChar(newChar, min, max);
		}
		return getNewChar(codigo.charAt(0), min, max)+"";
	}

	private String getNewChar(int charbase,int min, int max){
		charbase=charbase+1;
		if(charbase>max)
			return (char)min+""+(char)min;
		else
			return (char)charbase+"";
	}

	public String makeCodeCaracteristica(String lastCode, int min,int max){
		
		if(lastCode.length()>1){
			String base=lastCode.substring(0, lastCode.length()-1);
			char newChar=(lastCode.charAt(lastCode.length()-1));
			return base+getNewChar(newChar, min, max);
		}
		return getNewChar(lastCode.charAt(0), min, max)+"";
		
	}


	




	private boolean isMajor(int c,int maxValue){
		return c>maxValue;
	}
	public  void  categoriaFacade(Categoria cat){
		
	}
	
	private boolean isfirstFatherCategoria(Categoria categoria){
		return categoria.getIdCategoriaPadre()==null||categoria.getIdCategoriaPadre().getIdCategoria().intValue()==1;
	}
	

}
