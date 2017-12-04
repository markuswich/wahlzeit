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
	public boolean isEqual(Coordinate coord) {
		this.assertClassInvariants();
		
		SphericCoordinate spheric = coord.asSphericCoordinate();
		
		if(Math.abs(this.latitude - spheric.getLatitude()) < EPSILON && Math.abs(this.longitude - spheric.getLongitude()) < EPSILON && Math.abs(this.radius - spheric.getRadius()) < EPSILON) {
			return true;
		} else {
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
	
	
	public void setLatitude(double latitude) {
		this.assertClassInvariants();
		this.latitude = latitude;
	}
	
	public void setLongitude(double longitude) {
		this.assertClassInvariants();
		this.longitude = longitude;
	}
	
	public void setRadius(double radius) {
		this.assertClassInvariants();
		this.radius = radius;
	}
	
	@Override
	protected void assertClassInvariants() {
		assert(!Double.isNaN(latitude)) : "latitude can not be NaN";
		assert(!Double.isNaN(longitude)) : "longitude can not be NaN";
		assert(!Double.isNaN(radius)) : "radius can not be NaN";
		
		assert(-90 <= latitude && latitude <= 90) : "latitude must be between -90 and 90 (inclusive)";
		assert(-180 <= longitude && longitude <= 180) : "longitude must be between -180 and 180 (inclusive)";
		assert(0 <= radius) : "radius must be bigger or equal to zero";
		assert(!Double.isInfinite(radius)) : "radius can not be infinite";
	}
	
}