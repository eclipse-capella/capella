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
package org.polarsys.capella.core.transition.system.rules.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class StateMachineRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return CapellacommonPackage.Literals.STATE_MACHINE;
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context)
        .getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE, element, result);
    return BlockArchitectureExt.getOrCreateSystem(target);
  }

  /**
   * @param element
   * @param result
   * @param context
   * @return
   */
  @Override
  protected EObject getBestContainer(EObject element, EObject result, IContext context) {
    ISelectionContext sContext = SelectionContextHandlerHelper.getHandler(context).getSelectionContext(context,
        ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION, element, result);
    EObject parent = element.eContainer();
    while (parent != null) {
      EObject bestTracedElement = TransformationHandlerHelper.getInstance(context).getBestTracedElement(parent, context,
          sContext);
      if (bestTracedElement != null) {
        EStructuralFeature containmentFeature = getTargetContainementFeature(element, result, bestTracedElement,
            context);
        if (bestTracedElement.eClass().getEAllStructuralFeatures().contains(containmentFeature)) {
          return bestTracedElement;
        }
      }
      parent = parent.eContainer();
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {

  }

  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    return super.transformDirectElement(element, context);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);

    if (!(element.eContainer() instanceof org.polarsys.capella.core.data.information.Class)) {
      Collection<EObject> transfoSources = (Collection<EObject>) getCurrentContext()
          .get(ITransitionConstants.TRANSITION_SOURCES);
      for (EObject transfoSource : transfoSources) {
        if ((transfoSource instanceof Part) || (transfoSource instanceof Component)) {
          needed.addAll(createDefaultPrecedencePremices(transfoSources, "part"));
        }
      }
    }
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    StateMachine sourceElement = (StateMachine) source;
    result.addAll(sourceElement.getOwnedRegions());

    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
          sourceElement.getOwnedRegions(), context);
    }

  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element, EObject result, EObject container,
      IContext context) {
    if (container instanceof Component) {
      return CsPackage.Literals.BLOCK__OWNED_STATE_MACHINES;
    } else if (container instanceof Class) {
      return InformationPackage.Literals.CLASS__OWNED_STATE_MACHINES;
    } else if (container instanceof ComponentPkg) {
      return CsPackage.Literals.COMPONENT_PKG__OWNED_STATE_MACHINES;
    }
    return element.eContainingFeature();
  }

}
