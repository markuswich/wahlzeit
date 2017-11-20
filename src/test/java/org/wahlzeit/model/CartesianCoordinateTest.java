 /*
 * Classname: CartesianCoordinateTest
 *
 * Date: 19.11.17
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

public class CartesianCoordinateTest {
	
	private static final double EPSILON = 0.0000001;
	
	CartesianCoordinate zero;
	CartesianCoordinate coord1;
	CartesianCoordinate coord2;
	CartesianCoordinate coord3;
	CartesianCoordinate coord4;
	CartesianCoordinate coord5;
	CartesianCoordinate coord6;
	CartesianCoordinate coord7;
	CartesianCoordinate coord8;
	
	SphericCoordinate a;
	SphericCoordinate b;
	
	@Before
	public void setUp() {
		zero = new CartesianCoordinate(0.0, 0.0, 0.0);
		coord1 = new CartesianCoordinate(1.23, 4.56, 7.89);
		coord2 = new CartesianCoordinate(0.0, 0.0, 0.0);
		coord3 = new CartesianCoordinate(10, 4, 3);
		coord4 = new CartesianCoordinate(-2, -3, -4);
		
		coord5 = new CartesianCoordinate(2, 0, 0);
		coord6 = new CartesianCoordinate(0, 3, 0);
		coord7 = new CartesianCoordinate(0, 0, 4);
		
		coord8 = new CartesianCoordinate(1.23, 4.56, 7.89);
		
		a = new SphericCoordinate(-45, 0, 9);
		b = new SphericCoordinate(70, 0, -4);
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
		
		assertNotNull(a);
		assertNotNull(b);
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
	public void testAsCartesianCoordinate() {
		assertTrue(coord1.isEqual(coord1.asCartesianCoordinate()));
		assertTrue(coord1.isEqual(coord8.asCartesianCoordinate()));
		/**CartesianCoordinate cartesian = new SphericCoordinate(74.904479155789, 30.904855467356, 9.1955750228031).asCartesianCoordinate();
		
		assertEquals(cartesian.getX(), 1.23, 0.01);
		assertEquals(cartesian.getY(), 4.56, 0.01);
		assertEquals(cartesian.getZ(), 7.89, 0.01);
		
		System.out.println(new SphericCoordinate(74.904479155789, 30.904855467356, 9.1955750228031).asCartesianCoordinate().getX());
		System.out.println(new SphericCoordinate(74.904479155789, 30.904855467356, 9.1955750228031).asCartesianCoordinate().getY());
		System.out.println(new SphericCoordinate(74.904479155789, 30.904855467356, 9.1955750228031).asCartesianCoordinate().getZ());**/
	}
	
	@Test
	public void testGetCartesianDistance() {
		assertEquals(coord3.getCartesianDistance(coord4), 15.5563491861, EPSILON);
		
		assertEquals(zero.getCartesianDistance(coord5), 2, EPSILON);
		assertEquals(zero.getCartesianDistance(coord6), 3, EPSILON);
		assertEquals(zero.getCartesianDistance(coord7), 4, EPSILON);
	}
	
	@Test
	public void testAsSphericCoordinate() {
		SphericCoordinate spheric = coord1.asSphericCoordinate();
		
		assertEquals(spheric.getLatitude(), 30.904855467356, EPSILON);
		assertEquals(spheric.getLongitude(), 74.90447915578930, EPSILON);
		assertEquals(spheric.getRadius(), 9.1955750228031, EPSILON);
		
		/**System.out.println(spheric.getLatitude());
		System.out.println(spheric.getLongitude());
		System.out.println(spheric.getRadius());**/
	}
	
	@Test
	public void testGetSphericDistance() {
		assertEquals(a.getSphericDistance(b), 8.16, 0.01);
	}
	
	@Test
	public void testGetDistance() {
		assertEquals(coord3.getCartesianDistance(coord4), 15.5563491861, EPSILON);
		
		assertEquals(zero.getCartesianDistance(coord5), 2, EPSILON);
		assertEquals(zero.getCartesianDistance(coord6), 3, EPSILON);
		assertEquals(zero.getCartesianDistance(coord7), 4, EPSILON);
	}
	
	@Test
	public void testIsEqual() {
		assertTrue(zero.isEqual(zero));
		assertTrue(coord1.isEqual(coord8));
		
		assertFalse(zero.isEqual(coord1));
	}
	
	
	@Test
	public void testEquals() {
		assertTrue(zero.equals(zero));
		assertTrue(coord1.equals(coord8));
		
		
		assertFalse(zero.equals(coord1));
		
		assertFalse(zero.equals(null));
		assertFalse(zero.equals(new Object()));
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
