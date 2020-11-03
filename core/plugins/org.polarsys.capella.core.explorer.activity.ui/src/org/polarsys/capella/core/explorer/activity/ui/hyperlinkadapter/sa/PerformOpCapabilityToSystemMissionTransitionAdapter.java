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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.sa;

import java.util.List;

import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaHyperlinkAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.ModelSelectionHelper;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.transition.system.topdown.ui.commands.ITransitionCommandConstants;
import org.polarsys.capella.core.transition.system.topdown.ui.commands.TransitionUICommandHelper;

/**
 * Perform an automated transition of Operational Capability to a System Mission.
 */
public class PerformOpCapabilityToSystemMissionTransitionAdapter extends AbstractCapellaHyperlinkAdapter {

  public PerformOpCapabilityToSystemMissionTransitionAdapter() {
    super(ActivityExplorerManager.INSTANCE.getRootSemanticModel());
  }

  @Override
  protected ModelElement getModelElement(EObject rootSemanticModel) {
    if (rootSemanticModel instanceof Project) {
      return ModelQueryHelper.getOperationalAnalysis((Project) rootSemanticModel);
    }
    return null;
  }

  @Override
  protected void linkPressed(HyperlinkEvent event, EObject rootSemanticModel, Session session) {
    if (rootSemanticModel instanceof Project) {
      List<OperationalCapability> operationalCapabilities = ModelSelectionHelper
          .selectOperationalCapabilities((Project) rootSemanticModel);
      TransitionUICommandHelper.getInstance().executeCommand(ITransitionCommandConstants.OC2SMTransition,
          (List) operationalCapabilities);
    }
  }
}
