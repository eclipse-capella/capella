/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search.searchfor;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

/**
 * Utility class for images
 */
public final class ModelSearchImagesUtil {
	/**
	 * A 'registry' {@link Map} of all image descriptors identified by their URL 
	 */
	private static Map<URL, ImageDescriptor> imageDescriptorMap;
	
	/**
	 * A 'registry' {@link Map} of all images identified by their URL 
	 */
	private static Map<URL, Image> imageMap;
	
	/**
	 * Missing image {@link ImageDescriptor}
	 */
	public static ImageDescriptor MISSING_IMG_DESC;
	
	/**
	 * Static initialization
	 */
	static {
		imageDescriptorMap = new HashMap<URL, ImageDescriptor>();
		imageMap = new HashMap<URL, Image>();
		MISSING_IMG_DESC = ImageDescriptor.getMissingImageDescriptor();
		MISSING_IMG_DESC.getImageData().scaledTo(16, 16);
	}
	
    /**
     * Get an Image from a bundle ID and image relative path to the given bundle location
     * 
     * @param url Relative URL to the wanted image
     * 
     * @return ImageDescriptor built from the image file, Empty non null ImageDescriptor otherwise
     * 
     * @see ImageDescriptor
     */
    public static Image getImage(URL url) {
    	if (imageMap.containsKey(url)) {
    		return imageMap.get(url);
    	}
      Image image = getImageDescriptor(url).createImage();
      imageMap.put(url, image);
      return image;
    }
    
    /**
     * Get an Image from a bundle ID and image relative path to the given bundle location
     * 
     * @param bundle Given bundle ID which location will be resolve as root to search images into
     * @param imagePath Relative path to the wanted image
     * 
     * @return ImageDescriptor built from the image file, Empty non null ImageDescriptor otherwise
     * 
     * @see ImageDescriptor
     */
    public static Image getImage(Bundle bundle, String imagePath) {
	    URL url = null;
	    if (bundle != null){
	        url = FileLocator.find(bundle, new Path(imagePath), null);
	    	if (imageMap.containsKey(url)) {
	    		return imageMap.get(url);
	    	}
        Image image = getImageDescriptor(bundle, imagePath).createImage();
        imageMap.put(url, image);
        return image;
	    }
	    return MISSING_IMG_DESC.createImage();
    }
    
    /**
     * Get an ImageDescriptor from a bundle ID and image relative path to the given bundle location
     * 
     * @param bundle Given bundle ID which location will be resolve as root to search images into
     * @param imagePath Relative path to the wanted image
     * 
     * @return ImageDescriptor built from the image file, Empty non null ImageDescriptor otherwise
     * 
     * @see ImageDescriptor
     */
    public static ImageDescriptor getImageDescriptor(Bundle bundle, String imagePath) {
	    ImageDescriptor desc = MISSING_IMG_DESC;
	    URL url = null;
	    if (bundle != null){
	        url = FileLocator.find(bundle, new Path(imagePath), null);
	        if (imageDescriptorMap.containsKey(url)) {
	        	desc = imageDescriptorMap.get(url);
	        } else {
	        	desc = ImageDescriptor.createFromURL(url);
	        	imageDescriptorMap.put(url, desc);
	        }
	    }
	    return desc;
    }
    
    /**
     * Get an ImageDescriptor from a bundle ID and image relative path to the given bundle location
     * 
     * @param url Relative URL to the wanted image
     * 
     * @return ImageDescriptor built from the image file, Empty non null ImageDescriptor otherwise
     * 
     * @see ImageDescriptor
     */
    public static ImageDescriptor getImageDescriptor(URL url) {
	    ImageDescriptor desc = ImageDescriptor.getMissingImageDescriptor();
	    if (imageDescriptorMap.containsKey(url)) {
	        desc = imageDescriptorMap.get(url);
	    } else {
	        desc = ImageDescriptor.createFromURL(url);
	        imageDescriptorMap.put(url, desc);
	    }
	    return desc;
    }
}
