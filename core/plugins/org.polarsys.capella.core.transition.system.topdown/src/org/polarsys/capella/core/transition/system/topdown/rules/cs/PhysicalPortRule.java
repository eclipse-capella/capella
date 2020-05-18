/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.rules.cs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class PhysicalPortRule extends org.polarsys.capella.core.transition.system.rules.cs.PhysicalPortRule {

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      boolean isValid = false;

      // If already traced into target architecture, we retrieve physicalLink
      if (TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(element_p, context_p)) {
        isValid = true;

      } else {
        if (TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(element_p.eContainer(), context_p).isOK()) {
          if (CsPackage.Literals.COMPONENT.isSuperTypeOf(TransformationHandlerHelper.getInstance(context_p).getTargetType(element_p.eContainer(), context_p))) {
            isValid = true;
          }
        }
        if (TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(element_p.eContainer(), context_p)) {
          if (CsPackage.Literals.COMPONENT.isSuperTypeOf(TransformationHandlerHelper.getInstance(context_p).getTargetType(element_p.eContainer(), context_p))) {
            isValid = true;
          }
        }
      }
      if (!isValid) {
        result = new Status(IStatus.WARNING, Messages.Activity_Transition, "Container not transitioned");
      } else {
        result = Status.OK_STATUS;
      }
    }

    return result;
  }

}
