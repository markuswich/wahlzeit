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
import java.util.HashMap;


public class CartesianCoordinate extends AbstractCoordinate {
	
	
	private final double x;
	private final double y;
	private final double z;
	
	private static final HashMap<Integer, CartesianCoordinate> instances = new HashMap<Integer, CartesianCoordinate>();
	
	private CartesianCoordinate(double x, double y, double z) throws IllegalArgumentException {
		assertDoubleValid(x);
		assertDoubleValid(y);
		assertDoubleValid(z);
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.assertClassInvariants();
	}
	
	public static CartesianCoordinate getInstance(double x, double y, double z) {
		CartesianCoordinate res;
		synchronized(instances) {
			int hash = Objects.hash(x, y, z);
			
			if(instances.containsKey(hash)){
				res = instances.get(hash);
				return res;
			} else {
				res = new CartesianCoordinate(x, y, z);
				instances.put(hash, res);
				return res;
			}
		}
	}
	
	@Override
	public boolean isEqual(Coordinate coord) throws ConversionException {
		//preconditions
		assertCoordinateNotNull(coord);
		
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
	
	@Override
	protected void assertClassInvariants() {
		try {
			assertDoubleValid(x);
			assertDoubleValid(y);
			assertDoubleValid(z);
		} catch(IllegalArgumentException exp) {
			throw new AssertionError(exp.getMessage());
		}
	}
	
}