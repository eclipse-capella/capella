/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.pa;

import java.util.Collection;

import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaHyperlinkAdapter;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.transition.system.topdown.ui.commands.ITransitionCommandConstants;
import org.polarsys.capella.core.transition.system.topdown.ui.commands.TransitionUICommandHelper;

/**
 * Perform an automated transition of Logical Actors.
 */
public class PerformAutomatedTransitionOfLogicalActorsAdapter extends AbstractCapellaHyperlinkAdapter {
  /**
   * Constructor.
   */
  public PerformAutomatedTransitionOfLogicalActorsAdapter() {
    super(ActivityExplorerManager.INSTANCE.getRootSemanticModel());
  }

  @Override
  protected void linkPressed(HyperlinkEvent event, EObject rootSemanticModel, Session session) {
    ModelElement modelElement = getModelElement(rootSemanticModel);
    if (modelElement instanceof LogicalComponentPkg) {
      LogicalComponentPkg logicalCompPkg = (LogicalComponentPkg) modelElement;
      Collection<Component> logicalActors = ComponentPkgExt.getAllActors(logicalCompPkg);
      TransitionUICommandHelper.getInstance().executeCommand(ITransitionCommandConstants.ActorTransition,
          logicalActors);
    }
  }

  @Override
  protected ModelElement getModelElement(EObject rootSemanticModel) {
    if (rootSemanticModel instanceof Project) {
      return ModelQueryHelper.getLogicalComponentPkg((Project) rootSemanticModel);
    }
    return null;
  }
}
