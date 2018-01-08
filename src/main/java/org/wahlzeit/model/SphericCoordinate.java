/*
 * Classname: SphericCoordinate
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

import java.lang.Math;
import java.util.Objects;
import java.util.HashMap;

import org.wahlzeit.utils.PatternInstance;


@PatternInstance (
	patternName = "Template Method",
	participants = {"Concrete Class"}
)
public class SphericCoordinate extends AbstractCoordinate {
	
	
	private final double latitude;
	private final double longitude;
	private final double radius;
	private static final HashMap<Integer, SphericCoordinate> instances = new HashMap<Integer, SphericCoordinate>();

	
	private SphericCoordinate(double latitude, double longitude, double radius) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		
		this.assertClassInvariants();
	}
	
	public static SphericCoordinate getInstance(double latitude, double longitude, double radius) {
		SphericCoordinate res;
		synchronized(instances) {
			int hash = Objects.hash(latitude, longitude, radius);
			
			if(instances.containsKey(hash)){
				res = instances.get(hash);
				return res;
			} else {
				res = new SphericCoordinate(latitude, longitude, radius);
				instances.put(hash, res);
				return res;
			}
		}
	}
	
	
	@Override
	public boolean isEqual(Coordinate coord) throws IllegalArgumentException, ConversionException {
		//preconditions
		assertCoordinateNotNull(coord);
		
		this.assertClassInvariants();
		
		SphericCoordinate spheric = coord.asSphericCoordinate();
		
		if(Math.abs(this.latitude - spheric.getLatitude()) < EPSILON && Math.abs(this.longitude - spheric.getLongitude()) < EPSILON && Math.abs(this.radius - spheric.getRadius()) < EPSILON) {
			this.assertClassInvariants();
			return true;
		} else {
			this.assertClassInvariants();
			return false;
		}
	}
	
	
	@Override
    public int hashCode() {
		this.assertClassInvariants();
		return Objects.hash(this.latitude, this.longitude, this.radius);
    }
	
	public double getLatitude() {
		this.assertClassInvariants();
		return latitude;
	}
	
	public double getLongitude() {
		this.assertClassInvariants();
		return longitude;
	}
	
	public double getRadius() {
		this.assertClassInvariants();
		return radius;
	}
	
	@Override
	protected void assertClassInvariants() throws IllegalArgumentException {
		try {
			assertDoubleValid(latitude);
			assertDoubleValid(longitude);
			assertDoubleValid(radius);
			
			assertDoubleInRange(latitude, -90, 90);
			assertDoubleInRange(longitude, -180, 180);
			assertDoubleInRange(radius, 0, Double.MAX_VALUE);
		} catch(IllegalArgumentException e) {
			throw new AssertionError(e.getMessage());
		}
	}
	
}