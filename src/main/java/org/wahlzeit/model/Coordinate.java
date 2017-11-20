/*
 * Interfacename: Coordinate
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


public interface Coordinate {
	
	public CartesianCoordinate asCartesianCoordinate();
	public double getCartesianDistance(Coordinate coord);
	public SphericCoordinate asSphericCoordinate();
	public double getSphericDistance(Coordinate coord);
	public double getDistance(Coordinate coord);
	public boolean isEqual(Coordinate coord);
}