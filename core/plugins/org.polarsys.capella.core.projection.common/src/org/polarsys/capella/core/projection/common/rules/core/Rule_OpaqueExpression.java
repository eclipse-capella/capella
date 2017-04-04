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
package org.polarsys.capella.core.projection.common.rules.core;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.projection.common.context.IContext;

public class Rule_OpaqueExpression extends Rule_CapellaElement {

  public Rule_OpaqueExpression() {
    super(DatavaluePackage.Literals.OPAQUE_EXPRESSION, DatavaluePackage.Literals.OPAQUE_EXPRESSION);
    _updatedAttributes.add(DatavaluePackage.Literals.OPAQUE_EXPRESSION__BODIES.getName());
    _updatedAttributes.add(DatavaluePackage.Literals.OPAQUE_EXPRESSION__LANGUAGES.getName());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element, EObject result, EObject container, IContext context) {
    return ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__OWNED_SPECIFICATION;
  }
}
