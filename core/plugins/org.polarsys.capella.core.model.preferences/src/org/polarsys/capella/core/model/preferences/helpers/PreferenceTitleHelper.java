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
package org.polarsys.capella.core.model.preferences.helpers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

/**
 * 
 */
public class PreferenceTitleHelper {

  public String getPreferenceTitle(EClass clazz) {
    EObject object = clazz.getEPackage().getEFactoryInstance().create(clazz);
    AdapterFactory factory = CapellaAdapterFactoryProvider.getInstance().getAdapterFactory();
    ItemProviderAdapter provider = EObjectLabelProviderHelper.getItemProvider(object, factory);
    return EObjectLabelProviderHelper.getMetaclassLabel(object.eClass(), provider);
  }
  
}
