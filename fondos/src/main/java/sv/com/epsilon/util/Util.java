
package sv.com.epsilon.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;

public class Util {

	public String dateToString(Date date) {
		String strDate = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm");
			strDate = dateFormat.format(date);

			System.out.println("converted Date to String: " + strDate);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return strDate;
	}

	public static boolean isNumericDouble(String cadena) {
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return false;
		}
	}
	
	public static boolean isNumericInteger(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return false;
		}
	}
	
	public static String anioActual(){
		Integer year = 0;
		try {
			Calendar cal= Calendar.getInstance();
			year= cal.get(Calendar.YEAR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(year);
	} 
	
	public static int mesActual(){
		Integer month = 0;
		try {
			Calendar cal= Calendar.getInstance();
			month= cal.get(Calendar.MONTH)+1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return month;
	} 
	

    public static boolean isDummySelectItem(UIComponent component, String value) {
        for (UIComponent children : component.getChildren()) {
            if (children instanceof UISelectItem) {
                UISelectItem item = (UISelectItem) children;
                if (item.getItemValue() == null && item.getItemLabel().equals(value)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    public static String periodoComprendido(String periodoPagoPlanilla,int diasPeriodo) {
    	if(periodoPagoPlanilla.length()==7)
    		periodoPagoPlanilla=(0+""+periodoPagoPlanilla);
    	int numeroPeriodo=Integer.parseInt(periodoPagoPlanilla.substring(0, 2));
    	int mes=Integer.parseInt(periodoPagoPlanilla.substring(2, 4));
    	int anio=Integer.parseInt(periodoPagoPlanilla.substring(4, 8));
    	String periodoComprendido="";

    	
    	periodoComprendido=calcularPeriodo(diasPeriodo,numeroPeriodo,mes,anio);

    	return periodoComprendido.toUpperCase();
    		
    }

   
    
    
	private static String calcularPeriodo(int diasPeriodo,int numeroPeriodo, int mes, int anio) {
		
		String mesString =new Meses().getList().get(mes-1).getNombre();
		String maxDayOfMonth=String.valueOf(Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
		String periodoTexto="";
		if(diasPeriodo==7) {
			if(numeroPeriodo==1) {
				periodoTexto= "DEL 01 AL 07 DE :VAR_MES DE :VAR_ANIO";
			}
			if(numeroPeriodo==2) {
				periodoTexto ="DEL 08 AL 15 DE :VAR_MES DE :VAR_ANIO";
			}
			if(numeroPeriodo==3) {
				periodoTexto= "DEL 16 AL 21 DE :VAR_MES DE :VAR_ANIO";
			}
			if(numeroPeriodo==4) {
				periodoTexto ="DEL 22 AL :VAR_MAX_DAY_MONTH DE :VAR_MES DE :VAR_ANIO";
			}
		}
		if(diasPeriodo==15) {
			if(numeroPeriodo==1) {
				periodoTexto= "DEL 01 AL 15 DE :VAR_MES DE :VAR_ANIO";
			}
			if(numeroPeriodo==2) {
				periodoTexto ="DEL 08 AL :VAR_MAX_DAY_MONT DE :VAR_MES DE :VAR_ANIO";
			}
			
		}
		if(diasPeriodo==30) {
			if(numeroPeriodo==1) {
				periodoTexto= "DEL 01 AL :VAR_MAX_DAY_MONT DE :VAR_MES DE :VAR_ANIO";
			}
			
		}
		return periodoTexto.replace(":VAR_MAX_DAY_MONT",maxDayOfMonth).replace(":VAR_MAX_DAY_MONT", mesString+"").replace(":VAR_ANIO", anio+"").replace(":VAR_MES", mesString);
	}
	
	public static Date parseStringToDate(String dateString,String format) throws ParseException {
		
		return new SimpleDateFormat(format).parse(dateString);
	}
	
	public static Double resetTotal(Double value) {
		try {
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}finally {
			value=0.0;
		}
	}
	private static BigDecimal total=new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
	private static BigDecimal totalFinal=new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
	private static Integer rowCount=1;
	
	public static Double addTotal(Double salario) {
		total=total.add(new BigDecimal(salario));
		totalFinal=totalFinal.add(new BigDecimal(salario));
		rowCount++;
		return salario;
	}
	
	
	
	public static Double getTotal() {
		Double d= total.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		total=new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return d;
	}
	
	public static Double getTotalCurrent() {
		Double d= totalFinal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		totalFinal=new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return d;
	}
	
	
	public static int getCurrentCount() {
		return rowCount;
	}
	public static void getLastOneCount() {
		rowCount=1;
		
	}
	public static Double getTotalFinal() {
		return  totalFinal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
	}
	
	public static BigDecimal round2(Double valor) {
		return new BigDecimal(valor).setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	public static BigDecimal round2(String valor) {
		return new BigDecimal(valor).setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal round2(BigDecimal valor) {
		return valor.setScale(2,BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal round4(BigDecimal valor) {
		return valor.setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal round(BigDecimal valor,int decimales) {
		return valor.setScale(decimales,BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal addAndRound2(BigDecimal bd1,BigDecimal bd2) {
		bd1=round2(bd1);
		bd2=round2(bd2);
		return bd1.add(bd2);
	}
	
	public static BigDecimal subAndRound2(BigDecimal bd1,BigDecimal bd2) {
		bd1=round2(bd1);
		bd2=round2(bd2);
		return bd1.subtract(bd2);
	}
	public static BigDecimal subAndRound2(Double valor1,Double valor2) {
		BigDecimal bd1=round2(valor1);
		BigDecimal bd2=round2(valor2);
		return bd1.subtract(bd2);
	}
	public static BigDecimal subsAndRound2(BigDecimal bd1,BigDecimal bd2) {
		bd1=round2(bd1);
		bd2=round2(bd2);
		return bd1.subtract(bd2);
	}
	
	public static <T> Predicate<T> distinctByKey(
		    Function<? super T, ?> keyExtractor) {
		  
		    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
		    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
		}
	
}