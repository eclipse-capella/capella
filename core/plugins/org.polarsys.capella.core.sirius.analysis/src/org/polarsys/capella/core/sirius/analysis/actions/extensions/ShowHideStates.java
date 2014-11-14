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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.sirius.analysis.StateMachineServices;

/**
 */
public class ShowHideStates extends AbstractExternalJavaAction {

  public static final String ELEMENT_VIEW = "view"; //$NON-NLS-1$
  public static final String SELECTED_STATES = "selected states"; //$NON-NLS-1$
  public static final String VISIBLE_STATES = "visible states"; //$NON-NLS-1$
  public static final String VISIBLE_STATE_VIEWS = "visible state views"; //$NON-NLS-1$

  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  @Override
  @SuppressWarnings("unchecked")
  public void execute(Collection<? extends EObject> selections_p, Map<String, Object> parameters_p) {

    DSemanticDecorator view_p = (DSemanticDecorator) parameters_p.get(ELEMENT_VIEW);

    List<State> selectedStates = (List<State>) parameters_p.get(SELECTED_STATES);
    List<State> visibleStates = (List<State>) parameters_p.get(VISIBLE_STATES);
    List<AbstractDNode> visibleStateViews = (List<AbstractDNode>) parameters_p.get(VISIBLE_STATE_VIEWS);

    if (selectedStates == null) {
      selectedStates = new ArrayList<State>();
    }
    if (visibleStates == null) {
      visibleStates = new ArrayList<State>();
    }
    if (visibleStateViews == null) {
      visibleStateViews = new ArrayList<AbstractDNode>();
    }
    StateMachineServices.getService().showHideStatesInStateAndModeDiag(view_p, selectedStates, visibleStates, visibleStateViews);
  }

}
