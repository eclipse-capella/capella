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
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.sirius.analysis.ModeStateMachineServices;

/**
 */
public class ShowHideMSMStates extends AbstractExternalJavaAction {

  public static final String ELEMENT_VIEW = "view"; //$NON-NLS-1$
  public static final String SELECTED_STATES = "selected states"; //$NON-NLS-1$
  public static final String VISIBLE_STATES = "visible states"; //$NON-NLS-1$
  public static final String VISIBLE_STATE_VIEWS = "visible state views"; //$NON-NLS-1$

  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  @Override
  @SuppressWarnings("unchecked")
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

    DSemanticDecorator view = (DSemanticDecorator) parameters.get(ELEMENT_VIEW);

    List<State> selectedStates = (List<State>) parameters.get(SELECTED_STATES);
    List<State> visibleStates = (List<State>) parameters.get(VISIBLE_STATES);
    List<AbstractDNode> visibleStateViews = (List<AbstractDNode>) parameters.get(VISIBLE_STATE_VIEWS);

    if (selectedStates == null) {
      selectedStates = new ArrayList<State>();
    }
    if (visibleStates == null) {
      visibleStates = new ArrayList<State>();
    }
    if (visibleStateViews == null) {
      visibleStateViews = new ArrayList<AbstractDNode>();
    }
    ModeStateMachineServices.getService().showHideStatesInStateAndModeDiag(view, selectedStates, visibleStates, visibleStateViews);
  }

}
