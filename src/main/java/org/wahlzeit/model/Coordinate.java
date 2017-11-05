/*
 * Classname: Coordinate
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

import java.lang.Math;
import java.util.Objects;


public class Coordinate {
	
	private static final double EPSILON = 0.0000001;
	
	private double x = 0.0;
	private double y = 0.0;
	private double z = 0.0;
	
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		
	}
	
	/**
	* Returns the euclidean distance between this coordinate and the coordinate given as an argument
	*/
	public double getDistance(Coordinate coord) {
		double inRoot = Math.pow(x - coord.getX(), 2) + Math.pow(y - coord.getY(), 2) + Math.pow(z - coord.getZ(), 2);
		return Math.sqrt(inRoot);
	}
	
	/**
	* Checks if this coordinate and the coordinate given as an argument are at the same position
	*/
	public boolean isEqual(Coordinate coord) {
		if(Math.abs(this.x - coord.getX()) < EPSILON && Math.abs(this.y - coord.getY()) < EPSILON && Math.abs(this.z - coord.getZ()) < EPSILON) {
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