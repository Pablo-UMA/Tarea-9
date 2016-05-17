import static org.junit.Assert.*;

import org.junit.Before;


public class Test {

	Array_Asociativo a;
	
	@Before
	public void setUp(){
		a=new Array_Asociativo();
	}
	
	@org.junit.Test
	public void addNewValueWithPutt() {
		assertEquals(Array_Asociativo b = new Array_Asociativo("nombre","luis"), b.put("lus"));
	}
	
}
