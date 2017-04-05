/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.vp.ms.provider;

import java.util.Collection;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.model.IDelegatedListener;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.kitalpha.emde.model.edit.provider.ExtensionItemPropertyDescriptor;

public class CSConfigurationItemPropertyDescriptor extends ExtensionItemPropertyDescriptor {

  public CSConfigurationItemPropertyDescriptor(AdapterFactory adapterFactory, ResourceLocator resourceLocator,
      String displayName, String description, EStructuralFeature feature, boolean isSettable, boolean multiLine,
      boolean sortChoices, Object staticImage, String category, String[] filterFlags) {
    super(adapterFactory, resourceLocator, displayName, description, feature, isSettable, multiLine, sortChoices,
        staticImage, category, filterFlags);
  }

  // FIXME: canSetProperty is copied from ModelElementItemProvider.createPropertyDescriptor
  // Depends on https://bugs.polarsys.org/show_bug.cgi?id=739
  /**
   * @see org.polarsys.capella.common.mdsofa.emf.edit.provider.DslfactoryItemPropertyDescriptor#canSetProperty(java.lang.Object)
   */
  @Override
  public boolean canSetProperty(Object object_p) {
    boolean result = true;
    for (IConfigurationElement configurationElement : ExtensionPointHelper
        .getConfigurationElements("org.polarsys.capella.common.model", "DelegatedListener")) { //$NON-NLS-1$ //$NON-NLS-2$
      IDelegatedListener contributor = (IDelegatedListener) ExtensionPointHelper.createInstance(configurationElement,
          ExtensionPointHelper.ATT_CLASS);
      if (null != contributor) {
        result &= contributor.canSetProperty(object_p);
      }
    }
    if (result) {
      result &= super.canSetProperty(object_p);
    }
    return result;
    // TODO mettre en place un point d'extension qui sera implemente par le plugin collab capella.
    // En effet, ce dernier branchera le canSetProperty sur le lock cdo.
    // IPropertyEdition propertyEdition = null;
    // if (null == propertyEdition) {
    // return super.canSetProperty(object_p);
    // }
    // return propertyEdition.canSetProperty(object_p);
  }

  @Override
  protected Collection<?> getComboBoxObjects(Object object) {
    return ((CSConfiguration) object).getScope((Class<? extends ModelElement>) feature.getEType().getInstanceClass());
  }

}
