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
import static org.junit.Assert.fail;


public class SphericCoordinateTest {
	
	private static final double EPSILON = 0.0000001;
	
	SphericCoordinate coord1;
	SphericCoordinate coord2;
	SphericCoordinate coord3;
	SphericCoordinate coord8;
	
	SphericCoordinate a;
	SphericCoordinate b;
	
	@Before
	public void setUp() {
		coord1 = SphericCoordinate.getInstance(74.904479155789, 30.904855467356, 9.1955750228031);
		coord2 = SphericCoordinate.getInstance(0, 0, 0);
		coord3 = SphericCoordinate.getInstance(21.80140948635, 74.435193338612, 11.180339887499);
		
		
		coord8 = SphericCoordinate.getInstance(74.904479155789, 30.904855467356, 9.1955750228031);
		
		a = SphericCoordinate.getInstance(-45, 0, 9);
		b = SphericCoordinate.getInstance(70, 0, 4);
    }
	
	@Test
	public void testConstructor() {
		assertNotNull(coord1);
		assertNotNull(coord2);
		assertNotNull(coord3);
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
	public void testAsCartesianCoordinate() {
		try {
			CartesianCoordinate cartesian = coord8.asCartesianCoordinate();
			
			assertEquals(cartesian.getX(), 1.23, 0.01);
			assertEquals(cartesian.getY(), 4.56, 0.01);
			assertEquals(cartesian.getZ(), 7.89, 0.01);
		} catch (ConversionException e) {
			fail("ConversionException");
		}
	}
	
	@Test
	public void testGetCartesianDistance() {
		//The current getCartesianDistance Mehtod doesnt work as intended, so the Testcases for it are missing are missing 
		//System.out.println(coord3.getCartesianDistance(coord4));
	}
	
	@Test
	public void testAsSphericCoordinate() {
		try {
			assertTrue(coord1.isEqual(coord1.asSphericCoordinate()));
			assertTrue(coord1.isEqual(coord8.asSphericCoordinate()));
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
		//see testGetCartesianDistance
	}
	
	@Test
	public void testIsEqual() {
		assertTrue(coord1.equals(coord1));
		assertTrue(coord1.equals(coord8));
		
		assertFalse(coord3.equals(coord1));
		
		assertFalse(coord1.equals(null));
		assertFalse(coord1.equals(new Object()));
	}
	
	@Test
	public void testEquals() {
		assertTrue(coord1.equals(coord1));
		assertTrue(coord1.equals(coord8));
		
		assertFalse(coord3.equals(coord1));
		
		assertFalse(coord1.equals(null));
		assertFalse(coord1.equals(new Object()));
	}
	
	@Test(expected = java.lang.IllegalArgumentException.class) 
	public void testIsEqualException() {
		try {
			coord1.isEqual(null);
		} catch (ConversionException e) {
			fail("ConversionException");
		}
	}
	
	@Test
	public void testHashCode() {
		assertTrue(coord1.hashCode() == coord8.hashCode());
		assertTrue(coord1.hashCode() == coord8.hashCode());
		
		assertFalse(coord1.hashCode() == coord3.hashCode());
	}
}
