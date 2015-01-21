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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.Messages;

/**
 * Not used
 */
@Deprecated
public class Rule_StateFragment extends org.polarsys.capella.core.projection.scenario.uml2.rules.Rule_StateFragment {
  /**
   * @param eclass_p
   */
  public Rule_StateFragment() {
    super();
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    StateFragment state = (StateFragment) element_p;
    if ((state.getRelatedAbstractState() == null) && (state.getRelatedAbstractFunction() != null)) {
      return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, Messages.Rule_StateFragment_FailMsg);
    }
    return super.transformRequired(element_p, context_p);
  }

}
