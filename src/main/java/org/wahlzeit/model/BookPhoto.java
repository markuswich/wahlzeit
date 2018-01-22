/*
 * Classname: BookPhoto
 *
 * Date: 12.11.17
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


/*
 * Sequence of method calls that lead to the new object:
 * 1. BookPhotoManager.getInstance() (returns the Singleton instance of the BookPhotoManager Class, which is used to create Photo instances)
 * 2. call Method: createPhoto(String filename, Image uploadedImage) (inherited from PhotoManager)
 * 3. PhotoUtil.createPhoto(filename, id, uploadedImage) gets called
 * 4. PhotoFactory.getInstance() gets called (returns the Singleton instance of the BookPhotoFactory Class, which is used to create BookPhoto instances)
 * 5. createPhoto()/createPhoto(PhotoId id) from the BookPhotoFactory gets called and calls the constructor of the BookPhoto class
 * 
 * Object creation solution:
 * 1. Delegation: separate­object (delegated to BookPhotoFactory)
 * 2. Selection: by-subclassing (BookPhoto_Factory is a subclass of PhotoFactory)
 * 3. Configuration: N/A
 * 4. Instantiation: in-code (Constructor gets called directly via new)
 * 5. Initialization: by-fixed-signature (field assignment provided via fixed method signature)
 * 6. Building: N/A
 * 
 */


package org.wahlzeit.model;

import java.util.*;



public class BookPhoto extends Photo {
	
	private Book book;
	
	public BookPhoto() {
		super();
		book = null;
	}
	
	public BookPhoto(Book book) {
		super();
		this.book = book;
	}
	
	public BookPhoto(PhotoId myId) {
		super(myId);
	}
	
	public Book getBook() {
		return this.book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
}