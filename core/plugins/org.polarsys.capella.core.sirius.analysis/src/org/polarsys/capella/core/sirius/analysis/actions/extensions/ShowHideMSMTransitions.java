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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.sirius.analysis.ModeStateMachineServices;

/**
 */
public class ShowHideMSMTransitions extends AbstractExternalJavaAction {

  public static final String ELEMENT_VIEW = "view"; //$NON-NLS-1$
  public static final String SELECTED_TRANSITIONS = "selected transitions"; //$NON-NLS-1$
  public static final String VISIBLE_TRANSITIONS = "visible transitions"; //$NON-NLS-1$
  public static final String VISIBLE_TRANSITION_VIEWS = "visible transition views"; //$NON-NLS-1$

  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  @Override
  @SuppressWarnings("unchecked")
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

    DSemanticDecorator view = (DSemanticDecorator) parameters.get(ELEMENT_VIEW);

    List<StateTransition> selectedTransitions = (List<StateTransition>) parameters.get(SELECTED_TRANSITIONS);
    List<StateTransition> visibleTransitions = (List<StateTransition>) parameters.get(VISIBLE_TRANSITIONS);
    List<DEdge> visibleTransitionViews = (List<DEdge>) parameters.get(VISIBLE_TRANSITION_VIEWS);

    if (selectedTransitions == null) {
      selectedTransitions = new ArrayList<StateTransition>();
    }
    if (visibleTransitions == null) {
      visibleTransitions = new ArrayList<StateTransition>();
    }
    if (visibleTransitionViews == null) {
      visibleTransitionViews = new ArrayList<DEdge>();
    }
    ModeStateMachineServices.getService().showHideMSMTransitions(view, selectedTransitions, visibleTransitions, visibleTransitionViews);
  }

}
