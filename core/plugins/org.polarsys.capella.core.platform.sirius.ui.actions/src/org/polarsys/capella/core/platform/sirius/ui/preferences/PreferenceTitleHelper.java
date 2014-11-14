/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;


/**
 */
public class PreferenceTitleHelper {
  
  public String getPreferenceTitle(EClass clazz_p){
  AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) MDEAdapterFactory.getEditingDomain();
  ItemProviderAdapter itemProviderAdapter = new ItemProviderAdapter(editingDomain.getAdapterFactory()) {
    /**
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getResourceLocator()
     */
    @Override
    protected ResourceLocator getResourceLocator() {
      return CapellaModellerEditPlugin.INSTANCE;
    }
  };
  String metaclassLabel = EObjectLabelProviderHelper.getMetaclassLabel(clazz_p, itemProviderAdapter);
  itemProviderAdapter.dispose();
  return metaclassLabel;
  }
}
