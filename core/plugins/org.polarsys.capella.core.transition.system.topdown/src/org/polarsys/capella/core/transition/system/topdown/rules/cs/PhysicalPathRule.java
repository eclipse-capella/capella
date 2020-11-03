/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class PhysicalPathRule extends org.polarsys.capella.core.transition.system.rules.cs.PhysicalPathRule {

  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    return CsPackage.Literals.PHYSICAL_PATH;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    PhysicalPath sourceElement = (PhysicalPath) source_p;

    java.util.Collection<EObject> transfoSources = (java.util.Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (transfoSources.contains(source_p)) {
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getInvolvedInvolvements(), context_p);
    }

    result_p.addAll(sourceElement.getInvolvedInvolvements());
    result_p.addAll(sourceElement.getOwnedComponentExchangeAllocations());
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      PhysicalPath element = (PhysicalPath) element_p;

      java.util.Collection<EObject> transfoSources = (java.util.Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
      if (transfoSources.contains(element_p)) {
        return result;
      }
      if (!ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element_p, context_p)) {
        return new Status(IStatus.WARNING, Messages.Activity_Transition, "not in scope");
      }
      if (PhysicalPathExt.getInvolvedElements(element).isEmpty()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transition, "no involved elements");
      }
      for (AbstractPathInvolvedElement involvedElt : PhysicalPathExt.getInvolvedElements(element)) {
        if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(involvedElt, context_p).isOK()) {
          return new Status(IStatus.WARNING, Messages.Activity_Transition, "involved element");

        }
      }

    }
    return result;
  }
}
