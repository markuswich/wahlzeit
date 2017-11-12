 /*
 * Classname: CoordinateTest
 *
 * Date: 05.11.17
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */


package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CoordinateTest {
	
	private static final double EPSILON = 0.0000001;
	
	Coordinate zero;
	Coordinate coord1;
	Coordinate coord2;
	Coordinate coord3;
	Coordinate coord4;
	Coordinate coord5;
	Coordinate coord6;
	Coordinate coord7;
	Coordinate coord8;
	
	@Before
	public void setUp() {
		zero = new Coordinate(0.0, 0.0, 0.0);
		coord1 = new Coordinate(1.23, 4.56, 7.89);
		coord2 = new Coordinate(0.0, 0.0, 0.0);
		coord3 = new Coordinate(10, 4, 3);
		coord4 = new Coordinate(-2, -3, -4);
		
		coord5 = new Coordinate(2, 0, 0);
		coord6 = new Coordinate(0, 3, 0);
		coord7 = new Coordinate(0, 0, 4);
		
		coord8 = new Coordinate(1.23, 4.56, 7.89);
    }
	
	@Test
	public void testConstructor() {
		assertNotNull(zero);
		assertNotNull(coord1);
		assertNotNull(coord2);
		assertNotNull(coord3);
		assertNotNull(coord4);
		assertNotNull(coord5);
		assertNotNull(coord6);
		assertNotNull(coord7);
		assertNotNull(coord8);
		
		
	}
	
	
	@Test
	public void testGetter() {
		assertEquals(coord1.getX(), 1.23, EPSILON);
		assertEquals(coord1.getY(), 4.56, EPSILON);
		assertEquals(coord1.getZ(), 7.89, EPSILON);
	}
	
	
	@Test
	public void testSetter() {
		coord2.setX(3);
		coord2.setY(2);
		coord2.setZ(1);
		
		
		assertEquals(coord2.getX(), 3, EPSILON);
		assertEquals(coord2.getY(), 2, EPSILON);
		assertEquals(coord2.getZ(), 1, EPSILON);
	}
	
	
	
	
	
	@Test
	public void testGetDistance() {

		assertEquals(coord3.getDistance(coord4), 15.5563491861, EPSILON);
		
		assertEquals(zero.getDistance(coord5), 2, EPSILON);
		assertEquals(zero.getDistance(coord6), 3, EPSILON);
		assertEquals(zero.getDistance(coord7), 4, EPSILON);
	}
	
	
	@Test
	public void testEquals() {
		assertTrue(zero.equals(zero));
		assertTrue(coord1.equals(coord8));
		
		
		assertFalse(zero.equals(coord1));
		
		assertFalse(zero.equals(null));
		assertFalse(zero.equals(new Object()));
	}
	
	
	
	@Test
	public void testIsEqual() {
		assertTrue(zero.isEqual(zero));
		assertTrue(coord1.isEqual(coord8));
		
		assertFalse(zero.isEqual(coord1));
	}
	
	@Test(expected = NullPointerException.class) 
	public void testIsEqualException() { 
		zero.isEqual(null);
	}
	
	
	@Test
	public void testHashCode() {
		assertTrue(zero.hashCode() == zero.hashCode());
		assertTrue(coord1.hashCode() == coord8.hashCode());
		
		assertFalse(coord1.hashCode() == coord3.hashCode());
	}
	
	
}
