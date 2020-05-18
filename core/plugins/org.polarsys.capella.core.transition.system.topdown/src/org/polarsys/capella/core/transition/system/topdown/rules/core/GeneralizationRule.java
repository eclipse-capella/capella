/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.rules.core;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class GeneralizationRule extends org.polarsys.capella.core.transition.system.rules.core.GeneralizationRule {

  @Override
  public IStatus transformRequired(EObject source_p, IContext context_p) {
    IStatus result = super.transformRequired(source_p, context_p);

    if (!result.isOK()) {
      Generalization element = (Generalization) source_p;
      EObject targetElement = element.getSuper();

      if (TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(targetElement, context_p)) {
        return Status.OK_STATUS;

      }
    }
    return result;

  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    Generalization element = (Generalization) source_p;
    EObject targetElement = element.getSuper();

    if (TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(targetElement, context_p)) {
      result_p.add(targetElement);
    }

  }

}
