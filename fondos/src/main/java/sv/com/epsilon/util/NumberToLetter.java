/**
 * 
 */
package sv.com.epsilon.util;

import java.util.regex.Pattern;



/**
 * @author usuario07
 * @date 16/01/2014
 * @time 18:28:22
 * @PCBM
 */

public class NumberToLetter {
	
	
	

    private final String[] UNIDADES = {" ", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};
    private final String[] UNIDADES_MONEDA = {" ", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};
    private final String[] DECENAS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
        "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ",
        "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};
    private final String[] CENTENAS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
        "setecientos ", "ochocientos ", "novecientos "};
    private final String[] DIA_MES={"primero","dos",  "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve ","diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
            "diecisiete ", "dieciocho ", "diecinueve", "veinte ","veintiuno","veintidos","veintidos","veintitres","veinticuatro","veinticinco","veintiseis","veintisiete","veintiocho","veintinueve","treinta","treinta y uno"};
   public NumberToLetter() {
   }
 
   public String ConvertirDocumento(String documento){

	   StringBuilder sb=new StringBuilder();
	   if(documento!=null)
	   for(int i=0;i<documento.length();i++){
		   if(documento.charAt(i)!='-'){
			   if(documento.charAt(i)=='0')
				   sb.append("cero");
			   try{
				   sb.append(getUnidades(documento.charAt(i)+""));   
			   }catch(Exception e){

				   sb.append("-");
			   }
		   }else{
			   sb.append("GUION ");
		   }
	   }
	   
	   return sb.toString().replaceAll("un","uno").replaceAll("UN","UNO").toUpperCase().trim();
   }
  
   public String convertirEnTexto(double numero,boolean moneda, boolean mayusculas) {
	   return convertirEnTexto( numero+"", moneda,  mayusculas) ;
   }
   
   public String convertirEnTexto(String numero,boolean moneda, boolean mayusculas) {
	   if(numero==null||"".equals(numero))
		   return "";
	   String literal = "";
       String parte_decimal="";    
       //si el numero utiliza (.) en lugar de (,) -> se reemplaza
       numero = numero.replace(".", ",");
       //si el numero no tiene parte decimal, se le agrega ,00
       if(numero.indexOf(",")==-1){
           numero = numero + ",00";
       }
       //se valida formato de entrada -> 0,00 y 999 999 999,00
       if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
           //se divide el numero 0000000,00 -> entero y decimal
           String Num[] = numero.split(",");            
           //de da formato al numero decimal
           if(Num[1].trim().length()==1)
				Num[1]=Num[1]+0;
			else if(Num[1].trim().length()>2)
				Num[1]=Num[1].substring(0, 2);
           if(Num[1]!=null&&!Num[1].equals("00")){
           	if(moneda)
           		{
           		
    			
           			parte_decimal = " D�LARES con "+Convertir(Num[1],false,mayusculas)+" CENTAVOS DE D�LAR";
           		}
           	else
           		parte_decimal=" punto "+Convertir(Num[1],false,mayusculas);
           }else
           {
        	   if(moneda )
        		   parte_decimal ="D�LARES";
        	   
           }
           //se convierte el numero a literal
           if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
               literal = "cero ";
           } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
               literal = getMillones(Num[0]);
           } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
               literal = getMiles(Num[0]);
           } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
               literal = getCentenas(Num[0]);
           } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
               literal = getDecenas(Num[0]);
           } else {//sino unidades -> 9
               literal = getUnidades(Num[0]);
           }
           //devuelve el resultado en mayusculas o minusculas
           if (mayusculas) {
               return (literal + parte_decimal).toUpperCase();
           } else {
               return (literal + parte_decimal);
           }
       } else {//error, no se puede convertir
           return literal = null;
       }
   }
   public String  Convertir(String numero,boolean moneda, boolean mayusculas){
	   return Convertir(numero+"", moneda, mayusculas,false);
   }
   public String  Convertir(String numero,boolean mayusculas){
	   return Convertir(numero+"", false, mayusculas,false);
   }
   public String  Convertir(int numero,boolean moneda, boolean mayusculas){
	   return Convertir(numero+"", moneda, mayusculas,false);
   }
   public String  Convertir(double numero, boolean mayusculas){
	   return Convertir(numero+"", false, mayusculas,false);
   }

    public String Convertir(String numero,boolean moneda, boolean mayusculas,boolean financiero) {
    	numero = numero.replace(",", "");
        String literal = "";
        String parte_decimal="";    
        //si el numero utiliza (.) en lugar de (,) -> se reemplaza
        numero = numero.replace(".", ",");
        //si el numero no tiene parte decimal, se le agrega ,00
        if(numero.indexOf(",")==-1){
            numero = numero + ",00";
        }
        //se valida formato de entrada -> 0,00 y 999 999 999,00
        if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
            //se divide el numero 0000000,00 -> entero y decimal
            String Num[] = numero.split(",");            
            //de da formato al numero decimal
            
            String ceroDecimal="";
            if(Num[1].startsWith("0"));
             ceroDecimal =buscarInicialDecimalCero(Num[1]);
            
            if(Num[1]!=null&&!Num[1].equals("0")){
            	//String dc=Convertir(Num[1],false,mayusculas);
            	
            	
            	
            	if(moneda)
            		{
            			if(Num[1].trim().length()==1){
            				Num[1]=Num[1]+0;
            			}else if(Num[1].trim().length()>2){
            				Num[1]=Num[1].substring(0, 2);
            			}
            			parte_decimal=(financiero? " DOLARES con "+ Num[1] + "/100 ":" CON "+Convertir(Num[1],false,mayusculas))+" CENTAVOS ";
            		}
            	else
            		parte_decimal=" punto "+ceroDecimal+Convertir(Num[1],false,mayusculas);
            }else{
            	   if(moneda )
            		   parte_decimal ="DOLARES";
            	   
            }
            //se convierte el numero a literal
            if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                literal = "cero ";
            } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                literal = getMillones(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                literal = getMiles(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
                literal = getCentenas(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                literal = getDecenas(Num[0]);
            } else {//sino unidades -> 9
            	if(moneda)
            		literal = getUnidadesMoneda(Num[0]);
            	else
            		literal = getUnidades(Num[0]);
            }
            //devuelve el resultado en mayusculas o minusculas
            if (mayusculas) {
                return (literal + parte_decimal).toUpperCase();
            } else {
                return (literal + parte_decimal);
            }
        } else {//error, no se puede convertir
            return literal = null;
        }
    }
    public String ConvertirLetrasDecimal(String numero, boolean mayusculas,boolean contarDecimal) {
    	numero = numero.replace(",", "");
        String literal = "";
        String parte_decimal="";    
        //si el numero utiliza (.) en lugar de (,) -> se reemplaza
        numero = numero.replace(".", ",");
        //si el numero no tiene parte decimal, se le agrega ,00
        if(numero.indexOf(",")==-1){
            numero = numero + ",00";
        }
        //se valida formato de entrada -> 0,00 y 999 999 999,00
        if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
            //se divide el numero 0000000,00 -> entero y decimal
            String Num[] = numero.split(",");            
            //de da formato al numero decimal
            String ceroDecimal="";
            if(Num[1]!=null&&!Num[1].equals("00")&&!Num[1].equals("0")){
            	//String dc=Convertir(Num[1],false,mayusculas);
            	
            	if(Num[1].length()==2){
            		if(Num[1].charAt(0)=='0'){
            			ceroDecimal="cero ";
            		}
            	}
            	
            	
            		parte_decimal=" punto "+ceroDecimal+Convertir(Num[1],false,mayusculas);
            }else{
            	               	   
            		   parte_decimal=mayusculas ?"punto CERO":"punto cero";
            }
            //se convierte el numero a literal
            if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                literal = "cero ";
            } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                literal = getMillones(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                literal = getMiles(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
                literal = getCentenas(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                literal = getDecenas(Num[0]);
            } else {//sino unidades -> 9
            	
            	
            		literal = getUnidades(Num[0]);
            }
            //devuelve el resultado en mayusculas o minusculas
            if (mayusculas) {
                return (literal + parte_decimal).toUpperCase();
            } else {
                return (literal + parte_decimal);
            }
        } else {//error, no se puede convertir
            return literal = null;
        }
    }
    
    

    private String getUnidadesMoneda(String numero) {// 1 - 9
        //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
        String num = numero.substring(numero.length() - 1);
        return UNIDADES_MONEDA[Integer.parseInt(num)];
    }

    /* funciones para convertir los numeros a literales */

    private String getUnidades(String numero) {// 1 - 9
        //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
        String num = numero.substring(numero.length() - 1);
        return UNIDADES[Integer.parseInt(num)];
    }

    private String getDecenas(String num) {// 99                        
        int n = Integer.parseInt(num);
        if (n < 10) {//para casos como -> 01 - 09
            return getUnidades(num);
        } else if (n > 19) {//para 20...99
            String u = getUnidades(num);
            if (u.trim().equals("")) { //para 20,30,40,50,60,70,80,90
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
            } else {
            		
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
            }
        } else {//numeros entre 11 y 19
            return DECENAS[n - 10];
        }
    }

    private String getCentenas(String num) {// 999 o 099
        if( Integer.parseInt(num)>99 ){//es centena
            if (Integer.parseInt(num) == 100) {//caso especial
                return " cien ";
            } else {
                 return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
            } 
        }else{//por Ej. 099 
            //se quita el 0 antes de convertir a decenas
            return getDecenas(Integer.parseInt(num)+"");            
        }        
    }

    private String getMiles(String numero) {// 999 999
        //obtiene las centenas
        String c = numero.substring(numero.length() - 3);
        //obtiene los miles
        String m = numero.substring(0, numero.length() - 3);
        String n="";
        //se comprueba que miles tenga valor entero
        if (Integer.parseInt(m) > 0) {
            n = getCentenas(m);           
            return n + "mil " + getCentenas(c);
        } else {
            return "" + getCentenas(c);
        }

    }

    private String getMillones(String numero) { //000 000 000        
        //se obtiene los miles
        String miles = numero.substring(numero.length() - 6);
        //se obtiene los millones
        String millon = numero.substring(0, numero.length() - 6);
        String n = "";
        if(millon.length()>1){
            n = getCentenas(millon) + "millones ";
        }else{
            n = getUnidades(millon) + "millon ";
        }
        return n + getMiles(miles);        
    }

	public String convertirEnTextoDiaDeMes(int dia,boolean mayusculas) {
		if(dia>0&&dia<32)
			return mayusculas?DIA_MES[dia].toUpperCase():DIA_MES[dia];
		else
			return "";
		
	}
	
	public String buscarInicialDecimalCero(String decimales){
		String ceroDecimal="";
		 decimales=decimales.trim();
		 
		 if(decimales.startsWith("0")){
			 ceroDecimal=" cero ";
			 
		 }
		 if(decimales.length()>1)
			 ceroDecimal +=buscarInicialDecimalCero(decimales.substring(1,decimales.length() ));
			 
		 return ceroDecimal;
	}
}