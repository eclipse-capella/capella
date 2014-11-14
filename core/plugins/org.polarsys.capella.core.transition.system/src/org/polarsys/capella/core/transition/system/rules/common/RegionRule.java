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
package org.polarsys.capella.core.transition.system.rules.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class RegionRule extends AbstractCapellaElementRule {

  public RegionRule() {
    registerUpdatedReference(CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    Region sourceElement = (Region) source_p;

    result_p.addAll(sourceElement.getOwnedStates());
    result_p.addAll(sourceElement.getOwnedTransitions());

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedStates(), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedTransitions(), context_p);
    }

  }

  @Override
  protected EClass getSourceType() {
    return CapellacommonPackage.Literals.REGION;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    ISelectionContext sContext =
        SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION);
    EObject parent = TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(element_p.eContainer(), context_p, sContext);
    if ((parent != null) && (parent.eContainer() != null) && (parent.eContainer() instanceof org.polarsys.capella.core.data.ctx.System)) {
      List<EObject> regions = (List) parent.eGet(element_p.eContainingFeature());
      if (regions.size() == 1) {
        return regions.get(0);
      }
    }
    return super.transformDirectElement(element_p, context_p);
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacommonPackage.Literals.REGION__INVOLVED_STATES));
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.REGION__INVOLVED_STATES, context_p);
  }
}
