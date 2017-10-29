/*
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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
