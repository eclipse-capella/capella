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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class PhysicalLinkRule extends org.polarsys.capella.core.transition.system.rules.cs.PhysicalLinkRule {

  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    return CsPackage.Literals.PHYSICAL_LINK;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachContainement(EObject element_p, EObject result_p, IContext context_p) {
    super.attachContainement(element_p, result_p, context_p);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    PhysicalLink sourceElement = (PhysicalLink) source_p;

    java.util.Collection<EObject> transfoSources = (java.util.Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (transfoSources.contains(source_p)) {
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getLinkEnds(), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedPhysicalLinkEnds(), context_p);
    }

    result_p.addAll(sourceElement.getLinkEnds());
    result_p.addAll(sourceElement.getOwnedPhysicalLinkEnds());
    result_p.addAll(sourceElement.getOwnedComponentExchangeAllocations());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      PhysicalLink element = (PhysicalLink) element_p;
      if (!ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element_p, context_p)) {
        return new Status(IStatus.WARNING, Messages.Activity_Transition, "not in scope");
      }
      if (org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSource(element) == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transition, "source null");
      }
      if (org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTarget(element) == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transition, "target null");
      }
      if (!TransformationHandlerHelper.getInstance(context_p)
          .isOrWillBeTransformed(org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSource(element), context_p).isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transition, "source");
      }
      if (!TransformationHandlerHelper.getInstance(context_p)
          .isOrWillBeTransformed(org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTarget(element), context_p).isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transition, "target");
      }
      java.util.Collection<EObject> transfoSources = (java.util.Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
      if (transfoSources.contains(element_p)) {
        return result;
      }
    }
    return result;
  }
}
