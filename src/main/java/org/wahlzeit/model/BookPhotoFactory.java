/*
 * Classname: BookPhotoFactory
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

package org.wahlzeit.model;


import java.util.logging.Logger;
import org.wahlzeit.services.LogBuilder;

public class BookPhotoFactory extends PhotoFactory {
	
	private static final Logger log = Logger.getLogger(BookPhotoFactory.class.getName());
	
	private static BookPhotoFactory instance = null;
	
	protected BookPhotoFactory() {
		// do nothing
	}
	
	/**
	 * Public singleton access method.
	 */
	@Override
	public static synchronized BookPhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic BookPhotoFactory").toString());
			setInstance(new BookPhotoFactory());
		}

		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	@Override
	protected static synchronized void setInstance(BookPhotoFactory bookPhotoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize BookPhotoFactory twice");
		}

		instance = bookPhotoFactory;
	}
	
	
	/**
	 * @methodtype factory
	 */
	@Override
	public BookPhoto createPhoto() {
		return new BookPhoto();
	}

	/**
	 * Creates a new photo with the specified id
	 */
	@Override
	public BookPhoto createPhoto(PhotoId id) {
		return new BookPhoto(id);
	}
	
	
	/**
	 * Loads a photo. The Java object is loaded from the Google Datastore, the Images in all sizes are loaded from the
	 * Google Cloud storage.
	 */
	@Override
	public BookPhoto loadPhoto(PhotoId id) {
	   /* Photo result =
                OfyService.ofy().load().type(Photo.class).ancestor(KeyFactory.createKey("Application", "Wahlzeit")).filter(Photo.ID, id).first().now();
        for (PhotoSize size : PhotoSize.values()) {
            GcsFilename gcsFilename = new GcsFilename("picturebucket", filename);



        }*/
		return null;
	}
	
}
