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
package org.polarsys.capella.core.projection.common.rules.cs;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_AbstractTrace;

public class Rule_ComponentFunctionalAllocation extends Rule_AbstractTrace {

  public Rule_ComponentFunctionalAllocation() {
    super(FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION, FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION);
  }

  public Rule_ComponentFunctionalAllocation(EClass source, EClass target) {
    super(source, target);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION;
  }

  @Override
  protected boolean shouldUpdate() {
    return true;
  }

}
