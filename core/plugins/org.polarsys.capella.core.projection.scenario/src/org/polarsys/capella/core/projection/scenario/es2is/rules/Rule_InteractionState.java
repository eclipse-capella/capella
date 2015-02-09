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
package org.polarsys.capella.core.projection.scenario.es2is.rules;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.Messages;

/**
 * Not used
 */
@Deprecated
public class Rule_InteractionState extends org.polarsys.capella.core.projection.scenario.uml2.rules.Rule_InteractionState {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    InteractionState state = (InteractionState) element_p;
    if ((state.getRelatedAbstractState() == null) && (state.getRelatedAbstractFunction() != null)) {
      return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, Messages.Rule_InteractionFunctionState_FailMsg);
    }

    List<TimeLapse> lapses = (List) EObjectExt.getReferencers(state, InteractionPackage.Literals.TIME_LAPSE__START);
    for (TimeLapse lapse : lapses) {
      if (lapse instanceof StateFragment) {
        StateFragment fragment = (StateFragment) lapse;
        if ((fragment.getRelatedAbstractState() == null) && (fragment.getRelatedAbstractFunction() != null)) {
          return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, Messages.Rule_InteractionFunctionState_FailMsg);
        }
      }
    }

    lapses = (List) EObjectExt.getReferencers(state, InteractionPackage.Literals.TIME_LAPSE__FINISH);
    for (TimeLapse lapse : lapses) {
      if (lapse instanceof StateFragment) {
        StateFragment fragment = (StateFragment) lapse;
        if ((fragment.getRelatedAbstractState() == null) && (fragment.getRelatedAbstractFunction() != null)) {
          return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, Messages.Rule_InteractionFunctionState_FailMsg);
        }
      }
    }

    return Status.OK_STATUS;
  }

}
