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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.ActivityExplorerEditor;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.input.ActivityExplorerEditorInput;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.CommonActivityExplorerPage;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.junit.Assert;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

/**
 * Helper class for Activity Explorer tests. 
 */
public class ActivityExplorerTestsHelper {

  public static final int MIN_NB_ACTIVITY_EXPLORER_TABS = 6;
  
  public static void checkActivityExploreContent(ActivityExplorerEditor activityExplorerEditor, Session session, String testProjectName) {
    // Check AE editor is associated to the expected Session.
    ActivityExplorerEditorInput aeEditorInput = activityExplorerEditor.getEditorInput();
    Assert.assertNotNull(aeEditorInput);
    Assert.assertEquals(session, aeEditorInput.getSession());
    
    // Check editor's title.
    Assert.assertTrue(activityExplorerEditor.getPartName().contains(testProjectName));
    
    // Minimum 6 tabs (Workflow + one for each viewpoint).
    Collection<CommonActivityExplorerPage> activityExplorerPages = activityExplorerEditor.getPages();
    Assert.assertTrue(activityExplorerPages.size() >= MIN_NB_ACTIVITY_EXPLORER_TABS);
    
    // Collect page names.
    Set<String> pageNames = new HashSet<String>();
    for (CommonActivityExplorerPage activityExplorerPage : activityExplorerPages) {
      pageNames.add(activityExplorerPage.getPartName());
    }
    Assert.assertTrue(pageNames.contains(NamingConstants.CreateOpAnalysisCmd_name));
    Assert.assertTrue(pageNames.contains(NamingConstants.CreateSysAnalysisCmd_name));
    Assert.assertTrue(pageNames.contains(NamingConstants.CreateLogicalArchCmd_name));
    Assert.assertTrue(pageNames.contains(NamingConstants.CreatePhysicalArchCmd_name));
    Assert.assertTrue(pageNames.contains("EPBS")); // See org.polarsys.capella.core.explorer.activity.ui/plugin.xml
  }
 
  
  public static List<ActivityExplorerEditor> getOpenActivityExplorerEditors() {
    List<ActivityExplorerEditor> openAEEditors = new ArrayList<ActivityExplorerEditor>();
    IEditorReference[] openEditorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
    for (IEditorReference openEditorReference : openEditorReferences) {
      IEditorPart editor = openEditorReference.getEditor(false);
      if (editor instanceof ActivityExplorerEditor) {
        openAEEditors.add((ActivityExplorerEditor) editor);  
      }
    }
    return openAEEditors;
  }
  
}
