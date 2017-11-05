/*
 * Classname: LocationTest
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

public class LocationTest {
	
	
	Coordinate coordinate;
	Location location;
	
	
	@Before
	public void initialize() {
		coordinate = new Coordinate(0, 0, 0);
		location = new Location(coordinate);
	}

	
	
	@Test
	public void testConstructorAndGetter() {
		assertNotNull(location);
		
		assertNotNull(location.getCoordinate());
	}
	
	
}
