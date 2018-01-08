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

import org.wahlzeit.utils.PatternInstance;


@PatternInstance (
	patternName = "Template Method",
	participants = {"Abstract Class"}
)
public abstract class AbstractCoordinate implements Coordinate {
	
	protected static final double EPSILON = 0.0000001;

	
	@Override
	public CartesianCoordinate asCartesianCoordinate() throws IllegalArgumentException, ConversionException {
		try {
			assertClassInvariants();
			
			if (this instanceof CartesianCoordinate) {
				CartesianCoordinate thisCartesian = (CartesianCoordinate) this;
				
				return CartesianCoordinate.getInstance(thisCartesian.getX(), thisCartesian.getY(), thisCartesian.getZ());
			}
			
			SphericCoordinate thisSpheric = (SphericCoordinate) this;
			
			double x = thisSpheric.getRadius() * Math.sin(Math.toRadians(thisSpheric.getLongitude())) * Math.cos(Math.toRadians(thisSpheric.getLatitude()));
			double y = thisSpheric.getRadius() * Math.sin(Math.toRadians(thisSpheric.getLatitude())) * Math.sin(Math.toRadians(thisSpheric.getLongitude()));
			double z = thisSpheric.getRadius() * Math.cos(Math.toRadians(thisSpheric.getLongitude()));
			
			//postconditions
			assertDoubleValid(x);
			assertDoubleValid(y);
			assertDoubleValid(z);
			
			assertClassInvariants();
			return CartesianCoordinate.getInstance(x, y, z);
		} catch(IllegalArgumentException | AssertionError e) {
			throw new ConversionException("Could not convert coordinate to cartesian coordinate", e);
		}
	}
	
	@Override
	public double getCartesianDistance(Coordinate coord) throws IllegalArgumentException, ConversionException {
		//preconditions
		assertCoordinateNotNull(coord);
		
		assertClassInvariants();
		
		CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
		CartesianCoordinate cartesian = coord.asCartesianCoordinate();
		
		double inRoot = Math.pow(thisCartesian.getX() - cartesian.getX(), 2) + 
						Math.pow(thisCartesian.getY() - cartesian.getY(), 2) + 
						Math.pow(thisCartesian.getZ() - cartesian.getZ(), 2);
		
		double distance = Math.sqrt(inRoot);
		
		//postconditions
		assertDoubleInRange(distance, 0, Double.MAX_VALUE);
		
		assertClassInvariants();
		return distance;
	}
	
	@Override
	public SphericCoordinate asSphericCoordinate() throws IllegalArgumentException, ConversionException {
		try {
			assertClassInvariants();
		
			if (this instanceof SphericCoordinate) {
				SphericCoordinate thisSpheric = (SphericCoordinate) this;
				
				return SphericCoordinate.getInstance(thisSpheric.getLatitude(), thisSpheric.getLongitude(), thisSpheric.getRadius());
			}
			
			CartesianCoordinate thisCartesian = (CartesianCoordinate) this;
			
			double r = Math.sqrt(Math.pow(thisCartesian.getX(), 2) + Math.pow(thisCartesian.getY(), 2) + Math.pow(thisCartesian.getZ(), 2));
			double theta = Math.toDegrees(Math.acos(thisCartesian.getZ() / r));
			double phi = Math.toDegrees(Math.atan(thisCartesian.getY() / thisCartesian.getX()));
			
			//postconditions
			assertDoubleValid(theta);
			assertDoubleValid(phi);
			assertDoubleValid(r);
			
			assertDoubleInRange(theta, -90, 90);
			assertDoubleInRange(phi, -180, 180);
			assertDoubleInRange(r, 0, Double.MAX_VALUE);
			
			assertClassInvariants();
			return SphericCoordinate.getInstance(theta, phi, r);
		} catch(IllegalArgumentException | AssertionError e) {
			throw new ConversionException("Could not convert coordinate to spheric coordinate", e);
		}
	}
	
	@Override
	public double getSphericDistance(Coordinate coord) throws IllegalArgumentException, ConversionException {
		//preconditions
		assertCoordinateNotNull(coord);
		
		assertClassInvariants();
		
		SphericCoordinate thisSpheric = this.asSphericCoordinate();
		SphericCoordinate spheric = coord.asSphericCoordinate();
		
		
		double distance = Math.sqrt(Math.pow(thisSpheric.getRadius(), 2) + Math.pow(spheric.getRadius(), 2) - 2 * thisSpheric.getRadius() * spheric.getRadius() * 
				(Math.sin(Math.toRadians(thisSpheric.getLatitude())) * Math.sin(Math.toRadians(spheric.getLatitude())) * Math.cos(Math.toRadians(thisSpheric.getLongitude() - spheric.getLongitude())) + 
						Math.cos(Math.toRadians(thisSpheric.getLatitude())) * Math.cos(Math.toRadians(spheric.getLatitude()))));
		
		//postconditions
		assertDoubleValid(distance);
		assertDoubleInRange(distance, 0, Double.MAX_VALUE);
		
		assertClassInvariants();
		return distance;
	}
	
	@Override
	public double getDistance(Coordinate coord) throws IllegalArgumentException, ConversionException {
		//preconditions
		assertCoordinateNotNull(coord);
		
		double distance = this.asCartesianCoordinate().getCartesianDistance(coord);
		
		//postconditions
		assertDoubleValid(distance);
		assertDoubleInRange(distance, 0, Double.MAX_VALUE);
		
		assertClassInvariants();
		return distance;
	}
	
	@Override
	public abstract boolean isEqual(Coordinate coord) throws ConversionException;
	
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
		try {
			return this.isEqual((Coordinate) obj);
		} catch(ConversionException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	protected void assertClassInvariants() {
		
	}
	
	protected void assertCoordinateNotNull(Coordinate coord) throws IllegalArgumentException {
        if (coord == null) {
            throw new IllegalArgumentException("The given coordinate can not be null.");
        }
	}
	
	protected void assertDoubleValid(double num) throws IllegalArgumentException {
		if(Double.isNaN(num)) {
			throw new IllegalArgumentException("The given double can not be NaN");
		}
		if(Double.isInfinite(num)) {
			throw new IllegalArgumentException("The given double can not be infinite");
		}
	}
	
	protected void assertDoubleInRange(double val, double min, double max) throws IllegalArgumentException {
		if(!(min <= val && val <= max)) {
			throw new IllegalArgumentException("The given double " + val + " must be between " + min + " and " + max);
		}
	}
}