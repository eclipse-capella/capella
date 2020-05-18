/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.preferences;

import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;

public class CIKindPropertyPreference extends StringPropertyPreference implements ICompoundProperty {

  @Override
  public boolean isEnabled(IPropertyContext context) {
    IProperty property = context.getProperties().getProperty(ITopDownConstants.OPTIONS_TRANSITION__PCCI_ENABLED);
    return Boolean.TRUE.equals(context.getCurrentValue(property));
  }

  @Override
  public String[] getRelatedProperties() {
    return new String[] { ITopDownConstants.OPTIONS_TRANSITION__PCCI_ENABLED };
  }

  @Override
  public void updatedValue(IProperty property, IPropertyContext context) {
    // Nothing here
  }
}
