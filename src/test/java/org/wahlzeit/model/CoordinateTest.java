


package org.wahlzeit.model;

import org.junit.Test;
import static org.junit.Assert.assertTrue;




public class CoordinateTest {
	
	@Test
	public void testGetDistance() {
		Coordinate zero = new Coordinate(0.0, 0.0, 0.0);
		
		Coordinate x = new Coordinate(0.0, 0.0, 5.0);
		Coordinate y = new Coordinate(4.0, 0.0, 0.0);
		Coordinate z = new Coordinate(0.0, -3.0, 0.0);
		
		assertEquals(0.0, zero.getDistance(zero))
		
		assertEquals(5.0, zero.getDistance(x))
		assertEquals(4.0, zero.getDistance(y))
		assertEquals(3.0, zero.getDistance(z))
		
		assertEquals(5.0, y.getDistance(z))
		
		
		assertTrue(false);
	}
	
	@Test
	public void testIsEqual() {
		Coordinate x = new Coordinate(0.0, 0.0, 5.0);
		Coordinate y = new Coordinate(0.0, 0.0, -5.0);
		Coordinate z = new Coordinate(0.0, 0.0, 5.0);
		
		assertFalse(x.isEquals(y))
		assertFalse(y.isEquals(z))
		assertTrue(x.isEquals(x))
		assertTrue(x.isEquals(z))
		
		
	}
	
	
	
}
