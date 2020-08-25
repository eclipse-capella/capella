/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.platform.ju.testcases;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionServicesImpl;
import org.polarsys.capella.core.sirius.ui.danalysis.CapellaAnalysisSelector;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * This test ensures that CapellaAnalysisSelector is properly registered after Project creation or Opening session
 */
public class CustomDAnalysisSelection extends BasicTestCase {

  @Override
  public void test() throws Exception {
    newProject();
    openSession();
  }

  private void openSession() {
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("project");
    IFile file = project.getFile("project.aird");
    GuiActions.openSession(file, false);
    Session session = SessionHelper.getExistingSessions(project).stream().findAny().get();
    DAnalysisSelector analysisSelector = ((DAnalysisSessionServicesImpl) session.getServices()).getAnalysisSelector();
    assertTrue(analysisSelector instanceof CapellaAnalysisSelector);
    session.close(new NullProgressMonitor());
  }

  private void newProject() {
    IProject project = GuiActions.newProject("project", false);
    Session session = SessionHelper.getExistingSessions(project).stream().findAny().get();
    DAnalysisSelector analysisSelector = ((DAnalysisSessionServicesImpl) session.getServices()).getAnalysisSelector();
    assertTrue(analysisSelector instanceof CapellaAnalysisSelector);
    session.close(new NullProgressMonitor());
  }

}
