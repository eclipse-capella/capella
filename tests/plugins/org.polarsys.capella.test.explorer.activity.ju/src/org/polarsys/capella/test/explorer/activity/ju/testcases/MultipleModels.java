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
package org.polarsys.capella.test.explorer.activity.ju.testcases;

import java.util.Collection;
import java.util.List;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.ActivityExplorerEditor;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * Check ActivityExplorer behaviour when several models are opened then closed.
 */
public class MultipleModels extends BasicTestCase {
    
	@Override
	public void test() throws Exception {
	  String model1Name = "Model1";
	  String model2Name = "Model 2";
	  String model3Name = "Model3";
	  String model4Name = "Model 4";
	  
	  // Open models and check AE is correctly opened
		createProjectAndCheckAE(model1Name);
		createProjectAndCheckAE(model2Name);
		createProjectAndCheckAE(model3Name);
		createProjectAndCheckAE(model4Name);
		
		// There must be only 4 editors opened (and only ActivityExplorer editors)
		assertEquals(4, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences().length);
		assertEquals(4, ActivityExplorerTestsHelper.getOpenActivityExplorerEditors().size());

    // Close models and check AE is correctly closed
		closeSessionAndCheckAE(model1Name);
		closeSessionAndCheckAE(model2Name);
		closeSessionAndCheckAE(model3Name);
		closeSessionAndCheckAE(model4Name);
	}

  public void createProjectAndCheckAE(String projectName) {
    int previousAEEditorsNb = ActivityExplorerTestsHelper.getOpenActivityExplorerEditors().size();
    
    // Create project
    IProject createdProject = GuiActions.newProject(projectName, true);
    assertTrue(createdProject.isOpen());
    Collection<Session> createdSessions = SessionHelper.getExistingSessions(createdProject);
    assertEquals(1, createdSessions.size());
    Session createdSession = createdSessions.iterator().next();
    assertTrue(createdSession != null && createdSession.isOpen());
    
    // Check ActivityExplorer Editor is open, visible and valid
    int actualAEEditorsNb = ActivityExplorerTestsHelper.getOpenActivityExplorerEditors().size();
    assertEquals(previousAEEditorsNb + 1, actualAEEditorsNb);
    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    assertTrue(activeEditor instanceof ActivityExplorerEditor);
    ActivityExplorerTestsHelper.checkActivityExploreContent((ActivityExplorerEditor) activeEditor, createdSession, projectName);
  }
  
  public void closeSessionAndCheckAE(String projectName) {
    int previousAEEditorsNb = ActivityExplorerTestsHelper.getOpenActivityExplorerEditors().size();
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    assertTrue(project.isOpen());
    
    // Close the session
    Collection<Session> createdSessions = SessionHelper.getExistingSessions(project);
    assertEquals(1, createdSessions.size());
    Session createdSession = createdSessions.iterator().next();
    assertTrue(createdSession != null && createdSession.isOpen());
    GuiActions.closeSession(createdSession);
    assertFalse(createdSession.isOpen());

    // Check corresponding AE is closed
    int actualAEEditorsNb = ActivityExplorerTestsHelper.getOpenActivityExplorerEditors().size();
    assertEquals(previousAEEditorsNb - 1, actualAEEditorsNb);
    for (ActivityExplorerEditor openAEEditor : ActivityExplorerTestsHelper.getOpenActivityExplorerEditors()) {
      assertTrue(openAEEditor.getEditorInput().getSession() != createdSession);
    }
  }

}
