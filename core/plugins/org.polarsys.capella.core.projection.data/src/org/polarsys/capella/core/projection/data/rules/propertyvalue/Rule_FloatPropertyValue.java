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
package org.polarsys.capella.core.projection.data.rules.propertyvalue;

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

public class Rule_FloatPropertyValue extends Rule_AbstractPropertyValue {

  public Rule_FloatPropertyValue() {
    super(CapellacorePackage.Literals.FLOAT_PROPERTY_VALUE, CapellacorePackage.Literals.FLOAT_PROPERTY_VALUE);
    registerAttributeUpdate(CapellacorePackage.Literals.FLOAT_PROPERTY_VALUE__VALUE);
  }
  
}
