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
import static org.junit.Assert.fail;


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
		zero = CartesianCoordinate.getInstance(0.0, 0.0, 0.0);
		coord1 = CartesianCoordinate.getInstance(1.23, 4.56, 7.89);
		coord2 = CartesianCoordinate.getInstance(0.0, 0.0, 0.0);
		coord3 = CartesianCoordinate.getInstance(10, 4, 3);
		coord4 = CartesianCoordinate.getInstance(-2, -3, -4);
		
		coord5 = CartesianCoordinate.getInstance(2, 0, 0);
		coord6 = CartesianCoordinate.getInstance(0, 3, 0);
		coord7 = CartesianCoordinate.getInstance(0, 0, 4);
		
		coord8 = CartesianCoordinate.getInstance(1.23, 4.56, 7.89);
		
		a = SphericCoordinate.getInstance(-45, 0, 9);
		b = SphericCoordinate.getInstance(70, 0, 4);
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
	public void testAsCartesianCoordinate() {
		try {
			assertTrue(coord1.isEqual(coord1.asCartesianCoordinate()));
			assertTrue(coord1.isEqual(coord8.asCartesianCoordinate()));
		} catch (ConversionException e) {
			fail("ConversionException");
		}
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
		try {
			assertEquals(coord3.getCartesianDistance(coord4), 15.5563491861, EPSILON);
			
			assertEquals(zero.getCartesianDistance(coord5), 2, EPSILON);
			assertEquals(zero.getCartesianDistance(coord6), 3, EPSILON);
			assertEquals(zero.getCartesianDistance(coord7), 4, EPSILON);
		} catch (ConversionException e) {
			fail("ConversionException");
		}
	}
	
	@Test
	public void testAsSphericCoordinate() {
		try {
			SphericCoordinate spheric = coord1.asSphericCoordinate();
			
			assertEquals(spheric.getLatitude(), 30.904855467355684, EPSILON);
			assertEquals(spheric.getLongitude(), 74.9044791557889, EPSILON);
			assertEquals(spheric.getRadius(), 9.195575022803087, EPSILON);
			
			System.out.println(spheric.getLatitude());
			System.out.println(spheric.getLongitude());
			System.out.println(spheric.getRadius());
		} catch (ConversionException e) {
			fail("ConversionException");
		}
	}
	
	@Test
	public void testGetSphericDistance() {
		try {
			assertEquals(a.getSphericDistance(b), 11.28, 0.01);
		} catch (ConversionException e) {
			fail("ConversionException");
		}
	}
	
	@Test
	public void testGetDistance() {
		try {
			assertEquals(coord3.getCartesianDistance(coord4), 15.5563491861, EPSILON);
			
			assertEquals(zero.getCartesianDistance(coord5), 2, EPSILON);
			assertEquals(zero.getCartesianDistance(coord6), 3, EPSILON);
			assertEquals(zero.getCartesianDistance(coord7), 4, EPSILON);
		} catch (ConversionException e) {
			fail("ConversionException");
		}
	}
	
	@Test
	public void testIsEqual() {
		try {
			assertTrue(zero.isEqual(zero));
			assertTrue(coord1.isEqual(coord8));
			
			assertFalse(zero.isEqual(coord1));
		} catch (ConversionException e) {
			fail("ConversionException");
		}
	}
	
	
	@Test
	public void testEquals() {
		assertTrue(zero.equals(zero));
		assertTrue(coord1.equals(coord8));
		
		
		assertFalse(zero.equals(coord1));
		
		assertFalse(zero.equals(null));
		assertFalse(zero.equals(new Object()));
	}
	
	
	@Test(expected = java.lang.IllegalArgumentException.class) 
	public void testIsEqualException() { 
		try {
			zero.isEqual(null);
		} catch (ConversionException e) {
			fail("ConversionException");
		}
	}
	
	
	@Test
	public void testHashCode() {
		assertTrue(zero.hashCode() == zero.hashCode());
		assertTrue(coord1.hashCode() == coord8.hashCode());
		
		assertFalse(coord1.hashCode() == coord3.hashCode());
	}
	
	
}
