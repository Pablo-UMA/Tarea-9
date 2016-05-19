import static org.junit.Assert.*;

import org.junit.Before;


public class Test {

	Array_Asociativo a;
	
	@Before
	public void setUp(){
		a=new Array_Asociativo();
	}
	
	@org.junit.Test
	public void putInAnEmptyArray()  {
		
	}
	

	@org.junit.Test
	public void addNewValueWithPut() {
		Array_Asociativo b = new Array_Asociativo();
		b.put("nombre", "luis");
		a.put("nombre", "luis");
		assertEquals(a, b);
	}
	
	@org.junit.Test
	public void getValue()  {
		a.put("nombre", "luis");
		assertEquals("luis", a.get("nombre"));
	}
	
	@org.junit.Test
	public void getValueOrElseDefault()  {
		a.put("nombre", "luis");
		assertEquals("defecto",a.getOrElse("edad","defecto"));
	}
	
	@org.junit.Test
	public void getValueOrElse()  {
		a.put("nombre", "luis");
		assertEquals("luis", a.getOrElse("nombre", "defecto"));
	}
	
}
