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
package org.polarsys.capella.core.transition.system.topdown.rules.common;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * @author Suzy Broto
 */
public class StateMachineRule extends org.polarsys.capella.core.transition.system.rules.common.StateMachineRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    TopDownTransformationHelper topDT = TopDownTransformationHelper.getInstance(context_p);
    EObject container = element_p.eContainer();
    if (((container instanceof Component) || (container instanceof Class)) && topDT.isTracedInTarget(container, context_p)) {
      result_p.add(container);
    }
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (container_p instanceof Component) {
      return CsPackage.Literals.BLOCK__OWNED_STATE_MACHINES;
    } else if (container_p instanceof Class) {
      return InformationPackage.Literals.CLASS__OWNED_STATE_MACHINES;
    }
    return element_p.eContainingFeature();
  }
}
