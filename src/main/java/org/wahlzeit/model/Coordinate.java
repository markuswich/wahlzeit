

package org.wahlzeit.model;

import java.lang.Math;

public class Coordinate {
	
	private double x = 0.0;
	private double y = 0.0;
	private double z = 0.0;
	
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		
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
	
	
	public double getDistance(Coordinate coord) {
		double inRoot = Math.pow(x - coord.getX(), 2) + Math.pow(y - coord.getY(), 2) + Math.pow(z - coord.getZ(), 2);
		return Math.sqrt(inRoot);
	}
	
	public boolean isEqual(Coordinate coord) {
		if(this.x == coord.getX() && this.y == coord.getY() && this.z == coord.getZ()) {
			return true;
		} else {
			return false;
		}
	}
	
}