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

import java.util.Collection;
import java.util.Collections;
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
  @Override
  @SuppressWarnings("unchecked")
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
    DSemanticDecorator view = (DSemanticDecorator) parameters.get(VIEW);
    List<FunctionalChain> selectedChains = (List<FunctionalChain>) parameters.get(CHAINS);

    if (selectedChains == null) {
      selectedChains = Collections.emptyList();
    }
    FaServices.getFaServices().showInvolvedElementsInDataFlowBlank(selectedChains, view);

  }

}
