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
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.sirius.analysis.FaServices;

/**
 *
 */
public class ShowHideFunctions extends AbstractExternalJavaAction {

  public static final String ELEMENT_VIEW = "view"; //$NON-NLS-1$
  public static final String SELECTED_FUNCTIONS = "selected functions"; //$NON-NLS-1$
  public static final String VISIBLE_FUNCTIONS = "visible functions";  //$NON-NLS-1$
  public static final String VISIBLE_FUNCTION_VIEWS = "visible function views";  //$NON-NLS-1$
  
  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  @SuppressWarnings("unchecked")
  public void execute(Collection<? extends EObject> selections_p, Map<String, Object> parameters_p) {
    DSemanticDecorator view_p = (DSemanticDecorator) parameters_p.get(ELEMENT_VIEW);
    List<AbstractFunction> selectedFunctions = (List<AbstractFunction>) parameters_p.get(SELECTED_FUNCTIONS);
    List<AbstractFunction> visibleFunctions = (List<AbstractFunction>) parameters_p.get(VISIBLE_FUNCTIONS);
    List<AbstractDNode> visibleFunctionViews = (List<AbstractDNode>) parameters_p.get(VISIBLE_FUNCTION_VIEWS);
    if (selectedFunctions == null){
      selectedFunctions = new ArrayList<AbstractFunction>();
    }
    if (visibleFunctions == null){
      visibleFunctions = new ArrayList<AbstractFunction>();
    }
    if (visibleFunctionViews == null){
      visibleFunctionViews = new ArrayList<AbstractDNode>();
    }
    FaServices.getFaServices().showHideFunctionsInDataFlowBlank(view_p, selectedFunctions, visibleFunctions, visibleFunctionViews);

  }

}
