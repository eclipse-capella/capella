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

package org.polarsys.capella.core.transition.system.rules.cs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 */
public class PhysicalPathRule extends AbstractCapellaElementRule {

  public PhysicalPathRule() {
    super();
  }

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.PHYSICAL_PATH;
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    // Nothing here. We don't want to add container
  }

  @Override
  protected EObject getBestContainer(EObject element, EObject result, IContext context) {
    Component target =
        (Component) TransformationHandlerHelper.getInstance(context).getBestTracedElement(element.eContainer(), context, CsPackage.Literals.COMPONENT);
    if ((target == null) || (ComponentExt.isActor(target))) {
      return null;
    }
    return super.getBestContainer(element, result, context);
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element, result);
    return BlockArchitectureExt.getContext(target);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    retrieveEnds(source, result, context);

    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      PhysicalPath element = (PhysicalPath) source;
      result.addAll(element.getOwnedComponentExchangeAllocations());

      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedComponentExchangeAllocations(), context);
    }
  }

  /**
   * @param source
   * @param result
   * @param context
   */
  protected void retrieveEnds(EObject source, List<EObject> result, IContext context) {
    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      PhysicalPath element = (PhysicalPath) source;
      result.addAll(element.getInvolvedInvolvements());
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, element.getInvolvedInvolvements(), context);
    }
  }

  protected void premicesExchangeRelated(EObject element, ArrayList<IPremise> needed) {
    needed.addAll(createDefaultPrecedencePremices(element, CsPackage.Literals.PHYSICAL_PATH__OWNED_PHYSICAL_PATH_INVOLVEMENTS));
  }

  protected void attachExchangeRelated(EObject element, EObject result, IContext context) {
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CsPackage.Literals.PHYSICAL_PATH__OWNED_PHYSICAL_PATH_INVOLVEMENTS,
        context);
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    attachExchangeRelated(element, result, context);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    premicesExchangeRelated(element, needed);
  }
}
