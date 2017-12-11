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


public class SphericCoordinate extends AbstractCoordinate {
	
	
	private double latitude = 0.0;
	private double longitude = 0.0;
	private double radius = 0.0;
	
	public SphericCoordinate(double latitude, double longitude, double radius) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		
		this.assertClassInvariants();
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
	
	
	public void setLatitude(double latitude) throws IllegalArgumentException {
		//preconditions
		assertDoubleValid(latitude);
		assertDoubleInRange(latitude, -90, 90);
		
		this.assertClassInvariants();
		this.latitude = latitude;
		this.assertClassInvariants();
	}
	
	public void setLongitude(double longitude) throws IllegalArgumentException {
		//preconditions
		assertDoubleValid(longitude);
		assertDoubleInRange(longitude, -180, 180);
		
		this.assertClassInvariants();
		this.longitude = longitude;
		this.assertClassInvariants();
	}
	
	public void setRadius(double radius) throws IllegalArgumentException {
		//preconditions
		assertDoubleValid(radius);
		assertDoubleInRange(radius, 0, Double.MAX_VALUE);
		
		this.assertClassInvariants();
		this.radius = radius;
		this.assertClassInvariants();
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