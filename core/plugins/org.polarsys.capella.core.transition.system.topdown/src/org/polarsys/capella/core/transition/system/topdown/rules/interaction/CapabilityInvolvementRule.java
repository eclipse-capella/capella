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
package org.polarsys.capella.core.transition.system.topdown.rules.interaction;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.capella.core.transition.system.topdown.rules.common.InvolvementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class CapabilityInvolvementRule extends InvolvementRule {
  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    if (CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) element)) {
      // If the involvement to the System has been transformed, take it instead of creating a new one
      EntityOperationalCapabilityInvolvement inv = (EntityOperationalCapabilityInvolvement) element;
      InvolverElement capability = inv.getInvolver();
      InvolvedElement entity = inv.getInvolved();
      if (TopDownTransformationHelper.getInstance(context).isTargetASystem(entity, context)) {
        ISelectionContext selectionContext = SelectionContextHandlerHelper.getHandler(context)
            .getSelectionContext(context, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION);
        Capability transformedCapability = (Capability) TransformationHandlerHelper.getInstance(context)
            .getBestTracedElement(capability, context, selectionContext);
        SystemComponent transformedSystemComponent = (SystemComponent) TransformationHandlerHelper.getInstance(context)
            .getBestTracedElement(entity, context, selectionContext);
        List<CapabilityInvolvement> involvements = transformedCapability.getOwnedCapabilityInvolvements().stream()
            .filter(i -> i.getInvolved() == transformedSystemComponent).collect(Collectors.toList());
        if (!involvements.isEmpty()) {
          return involvements.get(0);
        }
      }
      return CtxFactory.eINSTANCE.createCapabilityInvolvement();
    }
    return CapellacommonFactory.eINSTANCE.createCapabilityRealizationInvolvement();
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element, EObject result, EObject container,
      IContext context) {
    if (CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) element)) {
      return CtxPackage.Literals.CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS;
    }
    return LaPackage.Literals.CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    Involvement element = (Involvement) source;
    EObject targetElement = element.getInvolved();

    if (TopDownTransformationHelper.getInstance(context).isTracedInTarget(targetElement, context)) {
      result.add(targetElement);
    }
  }
}
