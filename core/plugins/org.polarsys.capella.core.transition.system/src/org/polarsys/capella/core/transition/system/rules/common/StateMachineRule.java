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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
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
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);
    return BlockArchitectureExt.getFirstComponent(target, true);
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject parent = element_p.eContainer();
    while (parent != null) {
      if ((parent instanceof Block) || (parent instanceof org.polarsys.capella.core.data.information.Class)) {
        ISelectionContext sContext =
            SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION,
                element_p, result_p);
        EObject element = TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(parent, context_p, sContext);
        if (element != null) {
          return element;
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
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    // Nothing here
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    return super.transformDirectElement(element_p, context_p);
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);

    if (!(element_p.eContainer() instanceof org.polarsys.capella.core.data.information.Class)) {
      Collection<EObject> transfoSources = (Collection<EObject>) getCurrentContext().get(ITransitionConstants.TRANSITION_SOURCES);
      for (EObject transfoSource : transfoSources) {
        if ((transfoSource instanceof Part) || (transfoSource instanceof Component)) {
          needed_p.addAll(createDefaultPrecedencePremices(transfoSources, "part"));
        }
      }
    }
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    StateMachine sourceElement = (StateMachine) source_p;
    result_p.addAll(sourceElement.getOwnedRegions());

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedRegions(), context_p);
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
