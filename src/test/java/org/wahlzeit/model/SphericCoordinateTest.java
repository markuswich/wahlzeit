 /*
 * Classname: SphericCoordinateTest
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

public class SphericCoordinateTest {
	
	private static final double EPSILON = 0.0000001;
	
	SphericCoordinate zero;
	SphericCoordinate coord1;
	SphericCoordinate coord2;
	SphericCoordinate coord3;
	SphericCoordinate coord4;
	SphericCoordinate coord5;
	SphericCoordinate coord6;
	SphericCoordinate coord7;
	SphericCoordinate coord8;
	
	SphericCoordinate a;
	SphericCoordinate b;
	
	@Before
	public void setUp() {
		zero = new SphericCoordinate(0.0, 0.0, 0.0);
		coord1 = new SphericCoordinate(74.904479155789, 30.904855467356, 9.1955750228031);
		coord2 = new SphericCoordinate(0.0, 0.0, 0.0);
		coord3 = new SphericCoordinate(21.80140948635, 74.435193338612, 11.180339887499);
		coord4 = new SphericCoordinate(-123.69006752598, 137.9688862258	, 5.3851648071345);
		
		coord5 = new SphericCoordinate(2, 0, 0);
		coord6 = new SphericCoordinate(0, 3, 0);
		coord7 = new SphericCoordinate(0, 0, 4);
		
		coord8 = new SphericCoordinate(74.904479155789, 30.904855467356, 9.1955750228031);
		
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
		assertEquals(coord1.getLatitude(), 74.904479155789, EPSILON);
		assertEquals(coord1.getLongitude(), 30.904855467356, EPSILON);
		assertEquals(coord1.getRadius(), 9.1955750228031, EPSILON);
	}
	
	
	@Test
	public void testSetter() {
		coord2.setLatitude(3);
		coord2.setLongitude(2);
		coord2.setRadius(1);
		
		
		assertEquals(coord2.getLatitude(), 3, EPSILON);
		assertEquals(coord2.getLongitude(), 2, EPSILON);
		assertEquals(coord2.getRadius(), 1, EPSILON);
	}
	
	
	@Test
	public void testAsCartesianCoordinate() {
		CartesianCoordinate cartesian = coord8.asCartesianCoordinate();
		
		assertEquals(cartesian.getX(), 1.23, 0.01);
		assertEquals(cartesian.getY(), 4.56, 0.01);
		assertEquals(cartesian.getZ(), 7.89, 0.01);
		
	}
	
	@Test
	public void testGetCartesianDistance() {
		System.out.println(coord3.getCartesianDistance(coord4));
	}
	
	@Test
	public void testAsSphericCoordinate() {
		assertTrue(coord1.isEqual(coord1.asSphericCoordinate()));
		assertTrue(coord1.isEqual(coord8.asSphericCoordinate()));
	}
	
	@Test
	public void testGetSphericDistance() {
		assertEquals(a.getSphericDistance(b), 8.16, 0.01);
	}
	
	@Test
	public void testGetDistance() {
		
	}
	
	@Test
	public void testIsEqual() {
		assertTrue(zero.equals(zero));
		assertTrue(coord1.equals(coord8));
		
		assertFalse(zero.equals(coord1));
		
		assertFalse(zero.equals(null));
		assertFalse(zero.equals(new Object()));
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
