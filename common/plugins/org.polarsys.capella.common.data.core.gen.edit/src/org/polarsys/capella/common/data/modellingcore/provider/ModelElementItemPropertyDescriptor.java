/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.data.modellingcore.provider;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.model.IDelegatedListener;
import org.polarsys.kitalpha.emde.model.edit.provider.ExtensionItemPropertyDescriptor;

public class ModelElementItemPropertyDescriptor extends ExtensionItemPropertyDescriptor {

  public ModelElementItemPropertyDescriptor(AdapterFactory adapterFactory, ResourceLocator resourceLocator,
      String displayName, String description, EStructuralFeature feature, boolean isSettable, boolean multiLine,
      boolean sortChoices, Object staticImage, String category, String[] filterFlags) {
    super(adapterFactory, resourceLocator, displayName, description, feature, isSettable, multiLine, sortChoices,
        staticImage, category, filterFlags);
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.emf.edit.provider.DslfactoryItemPropertyDescriptor#canSetProperty(java.lang.Object)
   */
  @Override
  public boolean canSetProperty(Object object_p) {
    boolean result = true;
    for (IConfigurationElement configurationElement : ExtensionPointHelper.getConfigurationElements("org.polarsys.capella.common.model", "DelegatedListener")) { //$NON-NLS-1$ //$NON-NLS-2$
      IDelegatedListener contributor = (IDelegatedListener) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
      if (null != contributor) {
        result &= contributor.canSetProperty(object_p);
      }
    }
    if (result) {
      result &= super.canSetProperty(object_p);
    }
    return result;
    // TODO Add extension point that will be consumed by other products
    // They will then implement canSetProperty (e.g by checking the cdo lock)
//    IPropertyEdition propertyEdition = null;
//    if (null == propertyEdition) {
//      return super.canSetProperty(object_p);
//    }
//    return propertyEdition.canSetProperty(object_p);
  }

}
