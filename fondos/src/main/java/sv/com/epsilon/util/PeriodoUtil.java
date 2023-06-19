/**
 * 
 */
package sv.com.epsilon.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import sv.com.epsilon.presupuesto.pojo.Periodo;

/**
 * @author martinezc
 *
 */

public class PeriodoUtil {

	public static Periodo make(Integer year, Integer month) {
		Calendar inicio = Calendar.getInstance();
		Calendar fin=Calendar.getInstance();
		inicio.set(Calendar.YEAR, year);
		inicio.set(Calendar.MONTH, month-1);
		inicio.set(Calendar.DAY_OF_MONTH, 1);
		fin.set(Calendar.MONTH, month-1);	
		fin.set(Calendar.DAY_OF_MONTH, fin.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return new  Periodo(inicio, fin);
	}

	public static Periodo make(Date fechaInicial, Date fechaFinal) {
		Periodo periodo=new Periodo();
		periodo.setInicio(Calendar.getInstance());
		periodo.getInicio().setTime(fechaInicial);
		
		periodo.setFin(Calendar.getInstance());
		periodo.getFin().setTime(fechaFinal);
		return periodo;
	}
	
	public static Periodo make(Calendar fechaInicial, Calendar fechaFinal) {
		Periodo periodo=new Periodo();
		periodo.setInicio(fechaInicial);
		
		
		periodo.setFin(fechaFinal);

		return periodo;
	}
}
