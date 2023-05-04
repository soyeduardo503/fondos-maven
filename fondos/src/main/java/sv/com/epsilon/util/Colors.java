/**
 * 
 */
package sv.com.epsilon.util;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author martinezc
 *
 */
public enum Colors {

	skyblue(142, 202, 230, 1),
	bluegreen(33, 158, 188, 1),
	prussianblue(2, 48, 71, 1),
	selectiveyellow(255, 183, 3, 1),
	alloyorange(202, 103, 2, 1),
	midnightgreen(0, 95, 115, 1),
	darkcyan(10, 147, 150, 1),
	tiffanyblue(148, 210, 189, 1),
	vanilla(233, 216, 166, 1),
	gamboge(238, 155, 0, 1),
	orangepantone(251, 86, 7, 1),
	orangewheel(247, 127, 0, 1),
	rust(187, 62, 3, 1),
	rufous(174, 32, 18, 1),
	auburn(155, 34, 38, 1),
	amber(255, 190, 11, 1),
	
	rose(255, 0, 110, 1),
	blueviolet(131, 56, 236, 1),
	azure(58, 134, 255, 1),
	utorange(251, 133, 0, 1),
	richblack(0, 18, 25, 1),
	;
	
	
	private Integer r;
	private Integer g;
	private Integer b;
	private Integer i;
	
	private Colors(Integer r,Integer g,Integer b,Integer i) {
		this.r=r;
		this.g=g;
		this.b=b;
		this.i=i;
	}
	
	@Override
	public String toString() {
		return "rgb("+r+","+g+","+b+","+i+")";
	}
	
	public static Stream<Colors> getByCount(Integer i){
		return Stream.of(values()).limit(i);
	}
	
	public static Optional<Colors> getByCount(String name){
		return Stream.of(values()).filter(c->c.name().equalsIgnoreCase(name)).findAny();
	}
}
