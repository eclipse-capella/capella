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
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.sirius.analysis.FaServices;

/**
 *
 */
public class ShowHideRelatedElements extends AbstractExternalJavaAction {

  public static final String CHAINS = "chains"; //$NON-NLS-1$
  public static final String VIEW = "view"; //$NON-NLS-1$
  
  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  @SuppressWarnings("unchecked")
  public void execute(Collection<? extends EObject> selections_p, Map<String, Object> parameters_p) {
    DSemanticDecorator view_p = (DSemanticDecorator) parameters_p.get(VIEW);
    List<FunctionalChain> selectedChains = (List<FunctionalChain>) parameters_p.get(CHAINS);
    
    if (selectedChains == null){
      selectedChains = new ArrayList<FunctionalChain>();
    }
    FaServices.getFaServices().showInvolvedElementsInDataFlowBlank(selectedChains, view_p);

  }

}
