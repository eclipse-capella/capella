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
package org.polarsys.capella.core.transfo.statemachine.rules;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public abstract class Rule_AbstractState extends Rule_CapellaElement {

  public Rule_AbstractState(EClass sourceType_p, EClass targetType_p) {
    super(sourceType_p, targetType_p, CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    if (source_p instanceof State) {
      result_p.addAll(((State) source_p).getOwnedRegions());
    }

    if (source_p instanceof IState) {
      IState state = (IState) source_p;
      EObject containerCurrent = EcoreUtil2.getFirstContainer(state, CsPackage.Literals.COMPONENT);

      for (IState s : state.getReferencedStates()) {
        EObject containerState = EcoreUtil2.getFirstContainer(s, CsPackage.Literals.COMPONENT);
        if (containerCurrent.equals(containerState)) {
          result_p.add(s);
        }
      }

    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return CapellacommonPackage.Literals.REGION__OWNED_STATES;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE__ENTRY, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE__DO_ACTIVITY, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE__EXIT, context_p);

  }

}
