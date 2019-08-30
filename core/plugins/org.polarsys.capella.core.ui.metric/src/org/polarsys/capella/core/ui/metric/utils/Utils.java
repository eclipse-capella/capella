/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.metric.utils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.services.helper.EObjectImageProviderHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

/**
 * Utility class on resource. It returns images and text on EObject even if they are not associated to an Editing Domain
 */
public class Utils {

  /**
   * Get image for specified {@link EObject}
   * @param eObject
   * @return <code>null</code> if not found.
   */
  public static Image getImage(EObject eObject) {
    Image result = null;
    IItemLabelProvider itemProvider = getIItemLabelProvider(eObject);
    if (null != itemProvider) {
      result = EObjectImageProviderHelper.getImageFromObject(itemProvider.getImage(eObject));
    }
    return result;
  }

  /**
   * Get text for specified {@link EObject}
   * @param eObject
   * @return <code>null</code> if not found.
   */
  public static String getText(EObject eObject) {
    String result = ICommonConstants.EMPTY_STRING;
    IItemLabelProvider itemProvider = getIItemLabelProvider(eObject);
    if (null != itemProvider) {
      result = itemProvider.getText(eObject);
    }
    return result;
  }

  /**
   * Get a generic item provider.
   * @return an {@link ItemProviderAdapter} if any.
   */
  private static IItemLabelProvider getIItemLabelProvider(EObject object) {
    return
        (IItemLabelProvider) CapellaAdapterFactoryProvider.getInstance().getAdapterFactory().adapt(object, IItemLabelProvider.class);
    
  }
  
}
