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
	
	@org.junit.Test
	public void removeTest() throws Exception {
		Array_Asociativo b = new Array_Asociativo();
		a.put("nombre", "luis");
		assertEquals(b, a.remove("nombre"));
	}
	
	@org.junit.Test
	public void removeTest2() throws Exception {
		a.put("nombre", "luis");
		assertEquals(a, a.remove("edad"));
	}
	
	@org.junit.Test
	public void containsKeyTest() {
		a.put("nombre", "luis");
		assertEquals(true,a.containsKey("nombre"));
	}
	
	@org.junit.Test
	public void sizeTest()  {
		a.put("nombre", "luis");
		assertEquals(1,a.size());
	}
	
	@org.junit.Test
	public void sizeTest2()  {
		assertEquals(0,a.size());
	}
}
