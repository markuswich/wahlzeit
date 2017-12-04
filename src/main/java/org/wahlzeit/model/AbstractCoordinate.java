/*
 * Classname: AbstractCoordinate
 *
 * Date: 26.11.17
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

public abstract class AbstractCoordinate implements Coordinate {
	
	protected static final double EPSILON = 0.0000001;

	
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		assertClassInvariants();
		
		if (this instanceof CartesianCoordinate) {
			CartesianCoordinate thisCartesian = (CartesianCoordinate) this;
			
			return new CartesianCoordinate(thisCartesian.getX(), thisCartesian.getY(), thisCartesian.getZ());
		}
		
		SphericCoordinate thisSpheric = (SphericCoordinate) this;
		
		double x = thisSpheric.getRadius() * Math.sin(Math.toRadians(thisSpheric.getLongitude())) * Math.cos(Math.toRadians(thisSpheric.getLatitude()));
		double y = thisSpheric.getRadius() * Math.sin(Math.toRadians(thisSpheric.getLatitude())) * Math.sin(Math.toRadians(thisSpheric.getLongitude()));
		double z = thisSpheric.getRadius() * Math.cos(Math.toRadians(thisSpheric.getLongitude()));
		
		//postconditions
		assert(!Double.isNaN(x)) : "x can not be NaN";
		assert(!Double.isNaN(y)) : "y can not be NaN";
		assert(!Double.isNaN(z)) : "z can not be NaN";
		
		assert(!Double.isInfinite(x)) : "x can not be infinite";
		assert(!Double.isInfinite(y)) : "y can not be infinite";
		assert(!Double.isInfinite(z)) : "z can not be infinite";
		
		assertClassInvariants();
		return new CartesianCoordinate(x, y, z);
	}
	
	@Override
	public double getCartesianDistance(Coordinate coord) {
		//preconditions
		assert(coord != null) : "The given coordinate can not be null";
		
		assertClassInvariants();
		
		CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
		CartesianCoordinate cartesian = coord.asCartesianCoordinate();
		
		double inRoot = Math.pow(thisCartesian.getX() - cartesian.getX(), 2) + 
						Math.pow(thisCartesian.getY() - cartesian.getY(), 2) + 
						Math.pow(thisCartesian.getZ() - cartesian.getZ(), 2);
		
		double distance = Math.sqrt(inRoot);
		
		//postconditions
		assert(distance >= 0) : "distance must be bigger or equal to zero";
		
		assertClassInvariants();
		return distance;
	}
	
	@Override
	public SphericCoordinate asSphericCoordinate() {
		assertClassInvariants();
		
		if (this instanceof SphericCoordinate) {
			SphericCoordinate thisSpheric = (SphericCoordinate) this;
			
			return new SphericCoordinate(thisSpheric.getLatitude(), thisSpheric.getLongitude(), thisSpheric.getRadius());
		}
		
		CartesianCoordinate thisCartesian = (CartesianCoordinate) this;
		
		double r = Math.sqrt(Math.pow(thisCartesian.getX(), 2) + Math.pow(thisCartesian.getY(), 2) + Math.pow(thisCartesian.getZ(), 2));
		double theta = Math.toDegrees(Math.acos(thisCartesian.getZ() / r));
		double phi = Math.toDegrees(Math.atan(thisCartesian.getY() / thisCartesian.getX()));
		
		//postconditions
		assert(!Double.isNaN(theta)) : "latitude can not be NaN";
		assert(!Double.isNaN(phi)) : "longitude can not be NaN";
		assert(!Double.isNaN(r)) : "radius can not be NaN";
		
		assert(-90 <= theta && theta <= 90) : "latitude must be between -90 and 90 (inclusive)";
		assert(-180 <= phi && phi <= 180) : "longitude must be between -180 and 180 (inclusive)";
		assert(0 <= r) : "radius must be bigger or equal to zero";
		assert(!Double.isInfinite(r)) : "radius can not be infinite";
		
		assertClassInvariants();
		return new SphericCoordinate(theta, phi, r);
	}
	
	@Override
	public double getSphericDistance(Coordinate coord) {
		//preconditions
		assert(coord != null) : "The given coordinate can not be null";
			
		assertClassInvariants();
		
		SphericCoordinate thisSpheric = this.asSphericCoordinate();
		SphericCoordinate spheric = coord.asSphericCoordinate();
		
		
		double distance = Math.sqrt(Math.pow(thisSpheric.getRadius(), 2) + Math.pow(spheric.getRadius(), 2) - 2 * thisSpheric.getRadius() * spheric.getRadius() * 
				(Math.sin(Math.toRadians(thisSpheric.getLatitude())) * Math.sin(Math.toRadians(spheric.getLatitude())) * Math.cos(Math.toRadians(thisSpheric.getLongitude() - spheric.getLongitude())) + 
						Math.cos(Math.toRadians(thisSpheric.getLatitude())) * Math.cos(Math.toRadians(spheric.getLatitude()))));
		
		//postconditions
		assert(distance >= 0) : "distance must be bigger or equal to zero";
		
		assertClassInvariants();
		return distance;
	}
	
	@Override
	public double getDistance(Coordinate coord) {
		//preconditions
		assert(coord != null) : "The given coordinate can not be null";
		
		double distance = this.asCartesianCoordinate().getCartesianDistance(coord);
		
		//postconditions
		assert(distance >= 0) : "distance must be bigger or equal to zero";
		
		assertClassInvariants();
		return distance;
	}
	
	@Override
	public abstract boolean isEqual(Coordinate coord);
	
	@Override
	public boolean equals(Object obj) {
		assertClassInvariants();
		
		if(obj == null) {
			return false;
		}
		if(obj instanceof Coordinate == false) {
			return false;
		}
		if(obj == this) {
			return true;
		}
		
		assertClassInvariants();
		return this.isEqual((Coordinate) obj);
	}
	
	
	protected void assertClassInvariants() {
		
	}
}