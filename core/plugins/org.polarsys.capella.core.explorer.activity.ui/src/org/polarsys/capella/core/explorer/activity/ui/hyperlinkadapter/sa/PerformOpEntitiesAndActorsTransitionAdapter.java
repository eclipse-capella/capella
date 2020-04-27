/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.sa;

import java.util.List;

import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaHyperlinkAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.ModelSelectionHelper;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.transition.system.topdown.ui.commands.ITransitionCommandConstants;
import org.polarsys.capella.core.transition.system.topdown.ui.commands.TransitionUICommandHelper;

/**
 * Perform an automated transition of Operational Entities and Actors.
 */
public class PerformOpEntitiesAndActorsTransitionAdapter extends AbstractCapellaHyperlinkAdapter {

  public PerformOpEntitiesAndActorsTransitionAdapter() {
    super(ActivityExplorerManager.INSTANCE.getRootSemanticModel());
  }

  @Override
  protected void linkPressed(HyperlinkEvent event, EObject rootSemanticModel, Session session) {
    if (rootSemanticModel instanceof Project) {
      List<Entity> entities = ModelSelectionHelper.selectEntities((Project) rootSemanticModel);
      if ((entities != null) && !entities.isEmpty()) {
        TransitionUICommandHelper.getInstance().executeCommand(ITransitionCommandConstants.OE2ActorTransition,
            (List) entities);
      }
    }
  }

  @Override
  protected ModelElement getModelElement(EObject rootSemanticModel) {
    if (rootSemanticModel instanceof Project) {
      return ModelQueryHelper.getOperationalAnalysis((Project) rootSemanticModel);
    }
    return null;
  }
}
