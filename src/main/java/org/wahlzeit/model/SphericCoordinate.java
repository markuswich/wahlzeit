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


public class SphericCoordinate implements Coordinate {
	
	private static final double EPSILON = 0.0000001;
	
	private double latitude = 0.0;
	private double longitude = 0.0;
	private double radius = 0.0;
	
	public SphericCoordinate(double latitude, double longitude, double radius) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
	}
	
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		double x = this.getRadius() * Math.sin(Math.toRadians(this.getLongitude())) * Math.cos(Math.toRadians(this.getLatitude()));
		double y = this.getRadius() * Math.sin(Math.toRadians(this.getLatitude())) * Math.sin(Math.toRadians(this.getLongitude()));
		double z = this.getRadius() * Math.cos(Math.toRadians(this.getLongitude()));
		
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
		return new SphericCoordinate(this.getLatitude(), this.getLongitude(), this.getRadius());
	}
	
	@Override
	public double getSphericDistance(Coordinate coord) {
		SphericCoordinate spheric = coord.asSphericCoordinate();
		
		return Math.sqrt(Math.pow(this.getRadius(), 2) + Math.pow(spheric.getRadius(), 2) - 2 * this.getRadius() * spheric.getRadius() * 
				(Math.sin(Math.toRadians(this.getLatitude())) * Math.sin(Math.toRadians(spheric.getLatitude())) * Math.cos(Math.toRadians(this.getLongitude() - spheric.getLongitude())) + 
						Math.cos(Math.toRadians(this.getLatitude())) * Math.cos(Math.toRadians(spheric.getLatitude()))));
	}
	
	@Override
	public double getDistance(Coordinate coord) {
		return this.getSphericDistance(coord);
	}
	
	@Override
	public boolean isEqual(Coordinate coord) {
		SphericCoordinate spheric = coord.asSphericCoordinate();
		
		if(Math.abs(this.latitude - spheric.getLatitude()) < EPSILON && Math.abs(this.longitude - spheric.getLongitude()) < EPSILON && Math.abs(this.radius - spheric.getRadius()) < EPSILON) {
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
		return Objects.hash(this.latitude, this.longitude, this.radius);
    }
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getRadius() {
		return radius;
	}
	
	
	public double setLatitude(double latitude) {
		return this.latitude = latitude;
	}
	
	public double setLongitude(double longitude) {
		return this.longitude = longitude;
	}
	
	public double setRadius(double radius) {
		return this.radius = radius;
	}
	
}