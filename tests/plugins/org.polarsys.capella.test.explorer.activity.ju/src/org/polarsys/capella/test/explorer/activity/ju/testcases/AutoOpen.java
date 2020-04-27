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

import java.util.Collections;
import java.util.List;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.ActivityExplorerEditor;
import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * Test - Regarding corresponding preference:<br>
 * <ol>
 * <li>ActivityExplorer is opened when a Session opens,</li>
 * <li>ActivityExplorer is closed when a Session is closed.</li>
 * </ol> 
 */
public class AutoOpen extends BasicTestCase {

  public static final String TEST_PROJECT_NAME = "EmptyModel";
  
  @Override
  public void test() throws Exception {
    // Hypothesis: project already imported in workspace (by setUp()), but session not open.
    // Try to close Session if already open.
    Session oldSession = SessionHelper.getSession(getAirdFileForLoadedModel(TEST_PROJECT_NAME));
    if (oldSession != null && oldSession.isOpen()) {
      GuiActions.closeSession(oldSession);
    }
    
    // Open session using Capella's OpenSessionAction.
    IFile airdFile = getAirdFileForLoadedModel(TEST_PROJECT_NAME);
    GuiActions.openSession(airdFile, true);
    final Session session = SessionHelper.getSession(airdFile);
    
    // Check ActivityExplorer Editor is open.
    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    assertTrue(activeEditor instanceof ActivityExplorerEditor);
    
    // Check ActivityExplorer content is valid.
    ActivityExplorerEditor activityExplorerEditor = (ActivityExplorerEditor) activeEditor;
    ActivityExplorerTestsHelper.checkActivityExploreContent(activityExplorerEditor, session, TEST_PROJECT_NAME);

    // Close session.
    GuiActions.closeSession(session);
    
    // No editor (i.e. ActivityExplorer is closed).
    assertNull(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());
  }
  
  @Override
  public List<String> getRequiredTestModels() {
    return Collections.singletonList(TEST_PROJECT_NAME);
  }
  
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    // Check test execution duration (30s).
    long durationLimit = 30000;
    assertTrue("Test execution took more than " + 30000 / 10 + " seconds.", getExecutionDuration() <= durationLimit);
  }
}
