/**
 * 
 */
package com.senlang.sdip.util.poi;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.senlang.sdip.util.FormatException;

/**
 * @author Mc.D
 *
 */
public class PositionTest {

	/**
	 * Test method for {@link com.senlang.sdip.util.poi.Position#get(java.lang.String)}.
	 */
	@Test
	public void testGet() {
		try {
			System.out.println(Position.get("AA"));
			System.out.println(Position.get("A"));
			System.out.println(Position.get("ZZ"));
			assert Position.get("AA")==26;
			assert Position.get("A")==0;
			assert Position.get("ZZ")>0;
			try{
				Position.get("AAA");
				assert false;
			}catch(FormatException e){
				assert true;
			}
			
			try{
				Position.get("1");
				assert false;
			}catch(FormatException e){
				assert true;
			}
		} catch (FormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

}
