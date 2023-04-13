/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.pa;

import java.util.Collections;

import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaHyperlinkAdapter;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.transition.system.topdown.ui.commands.ITransitionCommandConstants;
import org.polarsys.capella.core.transition.system.topdown.ui.commands.TransitionUICommandHelper;

/**
 * Perform an automated transition of Capabilities from source architecture to target architecture.
 */
public class PerformAutomatedCapabilitiesTransitionAdapter extends AbstractCapellaHyperlinkAdapter {

  protected BlockArchitectureExt.Type blockType;

  /**
   * Constructor.
   */
  public PerformAutomatedCapabilitiesTransitionAdapter() {
    super(ActivityExplorerManager.INSTANCE.getRootSemanticModel());
    EObject rootSemanticModel = ActivityExplorerManager.INSTANCE.getRootSemanticModel();
    if (rootSemanticModel instanceof Project) {
      BlockArchitecture sourceArchitecture = ModelQueryHelper.getLogicalArchitecture((Project) rootSemanticModel);
      blockType = BlockArchitectureExt.getBlockArchitectureType(sourceArchitecture);
    }
  }

  @Override
  protected void linkPressed(HyperlinkEvent event, EObject rootSemanticModel, Session session) {
    ModelElement modelElement = getModelElement(rootSemanticModel);
    if (modelElement != null) {
      TransitionUICommandHelper.getInstance().executeCommand(ITransitionCommandConstants.CapabilityTransition,
          Collections.singleton((Object) modelElement));
    }
  }

  @Override
  protected ModelElement getModelElement(EObject rootSemanticModel) {
    if (blockType != null) {
      return BlockArchitectureExt.getBlockArchitecture(blockType, (Project) rootSemanticModel);
    }
    return null;
  }
}
