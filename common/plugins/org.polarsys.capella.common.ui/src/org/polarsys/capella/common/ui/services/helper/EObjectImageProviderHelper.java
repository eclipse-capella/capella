/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.services.helper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;

/**
 * Provide services to display EObject.
 */
public class EObjectImageProviderHelper extends EObjectLabelProviderHelper {

  /**
   * Get the image for given object based on generated item provider.
   * @param object
   * @return<code>null</code> if one of parameters is <code>null</code> or if no image is found.
   */
  public static Image getImage(EObject object) {
    Object image = null;

    IItemLabelProvider provider = getItemLabelProvider(object);
    if (null != provider) {
      image = provider.getImage(object);
    }
    return (null != image) ? getImageFromObject(image) : null;
  }

  /**
   * Get the image descriptor for given object based on generated item provider.
   * @param object
   * @return<code>null</code> if one of parameters is <code>null</code> or if no image descriptor is found.
   */
  public static ImageDescriptor getImageDescriptor(EObject object) {
    Object image = null;

    IItemLabelProvider provider = getItemLabelProvider(object);
    if (null != provider) {
      image = provider.getImage(object);
    }
    return (null != image) ? getImageDescriptorFromObject(image) : null;
  }

  /**
   * Get Image from a object representation of it.
   * @param image
   * @return <code>null</code> if image creation fails.
   */
  public static Image getImageFromObject(Object image) {
    return ExtendedImageRegistry.getInstance().getImage(image);
  }

  /**
   * Get ImageDescriptor from a object representation of it.
   * @param image
   * @return <code>null</code> if image descriptor creation fails.
   */
  public static ImageDescriptor getImageDescriptorFromObject(Object image) {
    return ExtendedImageRegistry.getInstance().getImageDescriptor(image);
  }
  
}
