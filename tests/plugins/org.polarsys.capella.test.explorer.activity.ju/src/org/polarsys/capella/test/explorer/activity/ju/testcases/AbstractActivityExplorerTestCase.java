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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.ActivityExplorerEditor;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.Messages;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.CommonActivityExplorerPage;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * Base class for Activity Explorer tests. 
 */
public abstract class AbstractActivityExplorerTestCase extends BasicTestCase {

  public static final String TEST_PROJECT_NAME = "EmptyModel";
  public static final int MIN_NB_ACTIVITY_EXPLORER_TABS = 6;
  
  @Override
  public List<String> getRequiredTestModels() {
    return Collections.singletonList(TEST_PROJECT_NAME);
  }
  
  public void checkActivityExploreContent(ActivityExplorerEditor activityExplorerEditor, String testProjestName) {
    // Check editor's title.
    assertTrue(activityExplorerEditor.getPartName().contains(Messages.ActivityExplorerEditor_Title_Suffix));
    assertTrue(activityExplorerEditor.getPartName().contains(testProjestName));
    
    // Minimum 6 tabs (Workflow + one for each viewpoint).
    Collection<CommonActivityExplorerPage> activityExplorerPages = activityExplorerEditor.getPages();
    assertTrue(activityExplorerPages.size() >= MIN_NB_ACTIVITY_EXPLORER_TABS);
    
    // Collect page names.
    Set<String> pageNames = new HashSet<String>();
    for (CommonActivityExplorerPage activityExplorerPage : activityExplorerPages) {
      pageNames.add(activityExplorerPage.getPartName());
    }
    assertTrue(pageNames.contains(NamingConstants.CreateOpAnalysisCmd_name));
    assertTrue(pageNames.contains(NamingConstants.CreateSysAnalysisCmd_name));
    assertTrue(pageNames.contains(NamingConstants.CreateLogicalArchCmd_name));
    assertTrue(pageNames.contains(NamingConstants.CreatePhysicalArchCmd_name));
    assertTrue(pageNames.contains("EPBS")); // See org.polarsys.capella.core.explorer.activity.ui/plugin.xml
  }
  
}
