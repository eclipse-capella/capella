/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
  public String[] getRelatedProperties() {
    
    return new String[] {ITopDownConstants.OPTIONS_TRANSITION__PCCI_ENABLED};
  }

  @Override
  public void updatedValue(IProperty property, IPropertyContext context) {
    Object currentValue = context.getCurrentValue(property);
    setEnabled(Boolean.valueOf(currentValue.toString()));
  }
}
