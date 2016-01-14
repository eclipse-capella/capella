/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.explorer.activity.ju.testcases;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.ActivityExplorerEditor;
import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * Test - Regarding corresponding preference:
 * <ol>
 * <li>ActivityExplorer does not open when a Session opens,</li>
 * <li>ActivityExplorer opens when the action is called,</li>
 * <li>ActivityExplorer is closed when a Session is closed.</li>
 * </ol>
 */
public class ManualOpen extends AbstractActivityExplorerTestCase {

  @Override
  public void test() throws Exception {
    // Hypothesis: project already imported in workspace (by setUp()), but session not open.
    
    // Open session using Capella's OpenSessionAction (but do not open Activity Explorer).
    IFile airdFile = getAirdFileForLoadedModel(TEST_PROJECT_NAME);
    GuiActions.openSession(airdFile, false);
    // No editor (i.e. AE is not open).
    assertNull(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());
    // Call Open AE action.
    GuiActions.launchOpenActivityExplorerAction(airdFile);
    
    // Check AE Editor is open.
    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    assertTrue(activeEditor instanceof ActivityExplorerEditor);
    
    // Check ActivityExplorer content is valid.
    ActivityExplorerEditor activityExplorerEditor = (ActivityExplorerEditor) activeEditor;
    checkActivityExploreContent(activityExplorerEditor, TEST_PROJECT_NAME);

    // Close session.
    Session session = SessionHelper.getSession(airdFile);
    GuiActions.closeSession(session);

    // No editor (i.e. ActivityExplorer is not open).
    assertNull(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());
  }
  
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    // Check test execution duration (30s).
    long durationLimit = 30000;
    assertTrue("Test execution took more than " + 30000 / 10 + " seconds.", getExecutionDuration() <= durationLimit);
  }
}
