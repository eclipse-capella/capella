/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.ui.quickfix.resolver;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

@Deprecated
public class DWF_D_38_Resolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker) {
    final EObject value = getModelElements(marker).get(0);
    if ((null != value) && (value instanceof Association)) {
      Association association = (Association) value;
      EList<Property> navigableMembers = association.getNavigableMembers();
      // consider only if one of the role is navigable
      if ((null != navigableMembers) && (navigableMembers.size() == 1)) {
        Property property = navigableMembers.get(0);
        CapellaUIPropertiesPlugin.getDefault().openWizard(property);
      }
    }
  }
  
  @Override
  protected String[] getResolvableRuleIds() {
    return noRuleIds;
  }
}
