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
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;

public class Rule_EnumerationPropertyLiteral extends Rule_CapellaElement {

  public Rule_EnumerationPropertyLiteral() {
    super(CapellacorePackage.Literals.ENUMERATION_PROPERTY_LITERAL, CapellacorePackage.Literals.ENUMERATION_PROPERTY_LITERAL);
  }

}
