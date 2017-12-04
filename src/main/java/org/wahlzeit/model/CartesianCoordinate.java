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


public class CartesianCoordinate extends AbstractCoordinate {
	
	
	private double x = 0.0;
	private double y = 0.0;
	private double z = 0.0;
	
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.assertClassInvariants();
	}
	
	
	@Override
	public boolean isEqual(Coordinate coord) {
		//preconditions
		assert(coord != null) : "The given coordinate can not be null";
		
		this.assertClassInvariants();
		
		CartesianCoordinate cartesian = coord.asCartesianCoordinate();
		
		if(Math.abs(this.x - cartesian.getX()) < EPSILON && Math.abs(this.y - cartesian.getY()) < EPSILON && Math.abs(this.z - cartesian.getZ()) < EPSILON) {
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
		return Objects.hash(this.x, this.y, this.z);
    }
	
	
	
	
	
	
	public double getX() {
		this.assertClassInvariants();
		return x;
	}
	
	public double getY() {
		this.assertClassInvariants();
		return y;
	}
	
	public double getZ() {
		this.assertClassInvariants();
		return z;
	}
	
	
	public void setX(double x) {
		//preconditions
		assert(!Double.isNaN(x)) : "x can not be NaN";
		assert(!Double.isInfinite(x)) : "x can not be infinite";
		
		this.assertClassInvariants();
		this.x = x;
	}
	
	public void setY(double y) {
		//preconditions
		assert(!Double.isNaN(y)) : "y can not be NaN";
		assert(!Double.isInfinite(y)) : "y can not be infinite";
		
		this.assertClassInvariants();
		this.y = y;
	}
	
	public void setZ(double z) {
		//preconditions
		assert(!Double.isNaN(z)) : "z can not be NaN";
		assert(!Double.isInfinite(z)) : "z can not be infinite";
		
		this.assertClassInvariants();
		this.z = z;
	}
	
	@Override
	protected void assertClassInvariants() {
		assert(!Double.isNaN(x)) : "x can not be NaN";
		assert(!Double.isNaN(y)) : "y can not be NaN";
		assert(!Double.isNaN(z)) : "z can not be NaN";
		
		assert(!Double.isInfinite(x)) : "x can not be infinite";
		assert(!Double.isInfinite(y)) : "y can not be infinite";
		assert(!Double.isInfinite(z)) : "z can not be infinite";
	}
	
}