/*
 * Classname: CartesianCoordinate
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


public class CartesianCoordinate implements Coordinate {
	
	private static final double EPSILON = 0.0000001;
	
	private double x = 0.0;
	private double y = 0.0;
	private double z = 0.0;
	
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return new CartesianCoordinate(this.getX(), this.getY(), this.getZ());
	}
	
	@Override
	public double getCartesianDistance(Coordinate coord) {
		CartesianCoordinate cartesian = coord.asCartesianCoordinate();
		
		double inRoot = Math.pow(x - cartesian.getX(), 2) + Math.pow(y - cartesian.getY(), 2) + Math.pow(z - cartesian.getZ(), 2);
		return Math.sqrt(inRoot);
	}
	
	@Override
	public SphericCoordinate asSphericCoordinate() {
		double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
		double theta = Math.toDegrees(Math.acos(z / r));
		double phi = Math.toDegrees(Math.atan(y / x));
		
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
		return this.getCartesianDistance(coord);
	}
	
	@Override
	public boolean isEqual(Coordinate coord) {
		CartesianCoordinate cartesian = coord.asCartesianCoordinate();
		
		if(Math.abs(this.x - cartesian.getX()) < EPSILON && Math.abs(this.y - cartesian.getY()) < EPSILON && Math.abs(this.z - cartesian.getZ()) < EPSILON) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
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
	
	@Override
    public int hashCode() {
		return Objects.hash(this.x, this.y, this.z);
    }
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	
	public double setX(double x) {
		return this.x = x;
	}
	
	public double setY(double y) {
		return this.y = y;
	}
	
	public double setZ(double z) {
		return this.z = z;
	}
	
}