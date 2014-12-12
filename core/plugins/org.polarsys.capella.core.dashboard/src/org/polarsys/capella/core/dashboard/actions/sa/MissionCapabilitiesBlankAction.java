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
package org.polarsys.capella.core.dashboard.actions.sa;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.dashboard.IImageKeys;
import org.polarsys.capella.core.dashboard.actions.AbstractCapellaAction;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 */
public class MissionCapabilitiesBlankAction extends AbstractCapellaAction {
  /**
   * Constructor.
   * @param capellaProject_p
   * @param session_p
   */
  public MissionCapabilitiesBlankAction(Project capellaProject_p, Session session_p) {
    super(Messages.MissionCapabilitiesBlankAction_Title, IImageKeys.IMAGE_DESCRIPTOR_NEW_DIAGRAM, capellaProject_p, session_p);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.actions.AbstractCapellaAction#doRun(org.polarsys.capella.core.data.capellamodeller.Project,
   *      org.eclipse.sirius.business.api.session.Session)
   */
  @Override
  protected void doRun(Project capellaProject_p, Session session_p) {
    new AbstractNewDiagramHyperlinkAdapter(session_p) {
      /**
       * @see org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractNewDiagramHyperlinkAdapter#getDiagramName()
       */
      @Override
      protected String getDiagramName() {
        return IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME;
      }

      /**
       * @see org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractHyperlinkAdapter#getModelElement(org.polarsys.capella.core.data.capellamodeller.Project)
       */
      @Override
      protected ModelElement getModelElement(Project project_p) {
        return ModelQueryHelper.getSACapabilityPkg(project_p);
      }
    }.linkActivated(null);
  }
}
