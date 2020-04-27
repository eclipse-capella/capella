/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.migration.ju.testcases.basic;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.compare.CompareLayoutManager;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;
import org.polarsys.capella.test.migration.ju.model.Sysmodel;

public class SysmodelMigrationLayout extends Sysmodel {

  @Override
  public void test() throws Exception {
    
    IProject project = IResourceHelpers.getEclipseProjectInWorkspace(getRequiredTestModels().get(0));
    
    if (project.exists()) {

      // Activate DOREMI refresh viewpoints
      DiagramHelper.setPreferenceAutoRefresh(true);
      DiagramHelper.setPrefereneRefreshOnOpening(true);

      MigrationHelper.migrateProject(project);
      checkLayout();
    }
  }

  protected void checkLayout() {
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    CompareLayoutManager m = new CompareLayoutManager();
    SessionLayout persisted = m.getPersistedLayout(session);
    
    if (persisted == null) {
      SessionLayout current = m.getCurrentLayout(session);
      m.saveLayout(session, current);
      assertTrue(NLS.bind("Missing file ''{0}''", m.getLayoutUri(session)), false);
      
    } else {
      SessionLayout current = m.getCurrentLayout(session);
      IPath path = ResourcesPlugin.getWorkspace().getRoot().getLocation().removeLastSegments(1);
      
      IPath file = path.append(m.getLayoutUri(session).lastSegment());
      URI uri = URI.createFileURI(file.toOSString());
      m.saveLayout(session, current, uri);
      
      //m.exportImages(session, path);
      m.analysis(current, persisted);
    }
  }

}
