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

package org.polarsys.capella.core.transition.system.rules.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.ctx.SystemComponent;
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
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    Region sourceElement = (Region) source;

    result.addAll(sourceElement.getOwnedStates());
    result.addAll(sourceElement.getOwnedTransitions());

    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedStates(), context);
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedTransitions(), context);
    }

  }

  @Override
  protected EClass getSourceType() {
    return CapellacommonPackage.Literals.REGION;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    ISelectionContext sContext =
        SelectionContextHandlerHelper.getHandler(context).getSelectionContext(context, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION);
    EObject parent = TransformationHandlerHelper.getInstance(context).getBestTracedElement(element.eContainer(), context, sContext);
    if ((parent != null) && (parent.eContainer() != null) && (parent.eContainer() instanceof SystemComponent)) {
      List<EObject> regions = (List) parent.eGet(element.eContainingFeature());
      if (regions.size() == 1) {
        return regions.get(0);
      }
    }
    return super.transformDirectElement(element, context);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, CapellacommonPackage.Literals.REGION__INVOLVED_STATES));
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacommonPackage.Literals.REGION__INVOLVED_STATES, context);
  }
}
