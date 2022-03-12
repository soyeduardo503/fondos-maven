/**
 * 
 */
package sv.com.epsilon.test;

import sv.com.epsilon.facade.CategoriaFacade;

/**
 * @author Zeta
 *
 */
public class MyTest {

	//@Test
	public void test() {
			System.out.println(new CategoriaFacade().findAll());
	}
	
//	@Test
//	public void testRead() {
//		File f=new File("C:\\eclipse\\db-inventario\\src\\sv\\com\\epsilon\\entities");
//		File[] fles=f.listFiles();
//		for(File myFile:fles) {
//			System.out.println("<mapping class=\"sv.com.epsilon.entities."+(myFile.getName().replace(".java", ""))+"\"/>");
//		//System.out.println(myFile.getName());
//		}
//	}

}
