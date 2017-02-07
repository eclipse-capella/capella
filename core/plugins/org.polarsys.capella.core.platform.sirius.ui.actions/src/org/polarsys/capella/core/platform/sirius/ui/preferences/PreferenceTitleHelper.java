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
package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

/**
 * 
 */
public class PreferenceTitleHelper {

  public String getPreferenceTitle(EClass clazz) {
    EObject obj = clazz.getEPackage().getEFactoryInstance().create(clazz);
    IItemLabelProvider provider = (IItemLabelProvider) CapellaAdapterFactoryProvider.getInstance().getAdapterFactory().adapt(obj, IItemLabelProvider.class);
    if (provider instanceof ItemProviderAdapter) {
      return EObjectLabelProviderHelper.getMetaclassLabel(obj.eClass(), (ItemProviderAdapter) provider);
    }
    return ICommonConstants.EMPTY_STRING;
  }
}
