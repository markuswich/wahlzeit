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
		if (this instanceof CartesianCoordinate) {
			CartesianCoordinate thisCartesian = (CartesianCoordinate) this;
			
			return new CartesianCoordinate(thisCartesian.getX(), thisCartesian.getY(), thisCartesian.getZ());
		}
		
		SphericCoordinate thisSpheric = (SphericCoordinate) this;
		
		double x = thisSpheric.getRadius() * Math.sin(Math.toRadians(thisSpheric.getLongitude())) * Math.cos(Math.toRadians(thisSpheric.getLatitude()));
		double y = thisSpheric.getRadius() * Math.sin(Math.toRadians(thisSpheric.getLatitude())) * Math.sin(Math.toRadians(thisSpheric.getLongitude()));
		double z = thisSpheric.getRadius() * Math.cos(Math.toRadians(thisSpheric.getLongitude()));
		
		return new CartesianCoordinate(x, y, z);
	}
	
	@Override
	public double getCartesianDistance(Coordinate coord) {
		CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
		CartesianCoordinate cartesian = coord.asCartesianCoordinate();
		
		double inRoot = Math.pow(thisCartesian.getX() - cartesian.getX(), 2) + 
						Math.pow(thisCartesian.getY() - cartesian.getY(), 2) + 
						Math.pow(thisCartesian.getZ() - cartesian.getZ(), 2);
		return Math.sqrt(inRoot);
	}
	
	@Override
	public SphericCoordinate asSphericCoordinate() {
		if (this instanceof SphericCoordinate) {
			SphericCoordinate thisSpheric = (SphericCoordinate) this;
			
			return new SphericCoordinate(thisSpheric.getLatitude(), thisSpheric.getLongitude(), thisSpheric.getRadius());
		}
		
		CartesianCoordinate thisCartesian = (CartesianCoordinate) this;
		
		double r = Math.sqrt(Math.pow(thisCartesian.getX(), 2) + Math.pow(thisCartesian.getY(), 2) + Math.pow(thisCartesian.getZ(), 2));
		double theta = Math.toDegrees(Math.acos(thisCartesian.getZ() / r));
		double phi = Math.toDegrees(Math.atan(thisCartesian.getY() / thisCartesian.getX()));
		
		return new SphericCoordinate(theta, phi, r);
	}
	
	@Override
	public double getSphericDistance(Coordinate coord) {
		SphericCoordinate thisSpheric = this.asSphericCoordinate();
		SphericCoordinate spheric = coord.asSphericCoordinate();
		
		return Math.sqrt(Math.pow(thisSpheric.getRadius(), 2) + Math.pow(spheric.getRadius(), 2) - 2 * thisSpheric.getRadius() * spheric.getRadius() * 
				(Math.sin(Math.toRadians(thisSpheric.getLatitude())) * Math.sin(Math.toRadians(spheric.getLatitude())) * Math.cos(Math.toRadians(thisSpheric.getLongitude() - spheric.getLongitude())) + 
						Math.cos(Math.toRadians(thisSpheric.getLatitude())) * Math.cos(Math.toRadians(spheric.getLatitude()))));
	}
	
	@Override
	public double getDistance(Coordinate coord) {
		return this.asCartesianCoordinate().getCartesianDistance(coord);
	}
	
	@Override
	public abstract boolean isEqual(Coordinate coord);
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj instanceof Coordinate == false) {
			return false;
		}
		if(obj == this) {
			return true;
		}
		return this.isEqual((Coordinate) obj);
	}
}