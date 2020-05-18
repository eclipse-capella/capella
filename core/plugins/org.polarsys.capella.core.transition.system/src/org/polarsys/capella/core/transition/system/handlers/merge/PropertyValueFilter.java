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
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.transition.system.preferences.PreferenceConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class PropertyValueFilter extends EObjectCategoryFilter {

  public PropertyValueFilter(IContext context) {
    super(context, CapellacorePackage.Literals.STRING_PROPERTY_VALUE, PreferenceConstants.P_PropertyValues_TEXT);
  }

  @Override
  public boolean keepElement(Object element) {
    return (element instanceof PropertyValuePkg) || (element instanceof AbstractPropertyValue)
        || (element instanceof PropertyValueGroup) || (element instanceof EnumerationPropertyLiteral)
        || (element instanceof EnumerationPropertyType) || (element instanceof EnumerationPropertyValue);
  }

}
