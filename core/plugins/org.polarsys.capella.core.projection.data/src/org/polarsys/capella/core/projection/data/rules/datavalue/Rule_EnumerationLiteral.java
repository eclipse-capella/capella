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
package org.polarsys.capella.core.projection.data.rules.datavalue;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;

public class Rule_EnumerationLiteral extends Rule_CapellaElement {

  public Rule_EnumerationLiteral() {
    super(DatavaluePackage.Literals.ENUMERATION_LITERAL, DatavaluePackage.Literals.ENUMERATION_LITERAL);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    EnumerationLiteral enumLiteral = (EnumerationLiteral) source_p;
    result_p.addAll(enumLiteral.getOwnedPropertyValues());
    result_p.addAll(enumLiteral.getOwnedPropertyValueGroups());
    result_p.addAll(enumLiteral.getOwnedConstraints());
  }

}
