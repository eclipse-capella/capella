/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.diagram.actions;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.internal.Workbench;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.TestHelper;

import junit.framework.Test;

/**
 * Verify that after the "Refresh All Sub Representations" action, the PC related to the diagram's contextual element reappears
 */
public class RefreshAllSubRepresentations extends AbstractDiagramTestCase {
  
  public static String PC_2 = "0522138e-b42d-4392-bb8f-ec0afa751794";
  public static String PAB_DIAGRAM = "[PAB] Physical System";
  
  @Override
  public String getRequiredTestModel() {
    return "DiagramAction";
  }
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    TestHelper.disableAutoSaveJob();
  }
  
  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    GuiActions.refreshAllSubRepresentations(getAirdFileForLoadedModel(getRequiredTestModel()), session);
  }

  public static Test suite() {
    return new RefreshAllSubRepresentations();
  }
}
