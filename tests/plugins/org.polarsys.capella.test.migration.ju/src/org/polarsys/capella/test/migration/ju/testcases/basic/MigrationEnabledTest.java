/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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

import java.util.Arrays;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.polarsys.capella.core.data.migration.handlers.ProjectMigrationHandler;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;

public class MigrationEnabledTest extends BasicTestCase {

  @Override
  public void test() throws Exception {
    
    IProject notACapellaProject = ResourcesPlugin.getWorkspace().getRoot().getProject("notACapellaProject");
    notACapellaProject.create(null);
    notACapellaProject.open(null);
    
    IProject projectClosed = createCapellaProject("projectClosed");
    projectClosed.close(null);
    
    IProject validProject = createCapellaProject("validProject");
    
    IProject anotherValidProject = createCapellaProject("anotherValidProject");
    
    IProject projectWithoutAfm = createCapellaProject("projectWithoutAfm");
    deleteFile(projectWithoutAfm, CapellaResourceHelper.AFM_FILE_EXTENSION);
    
    IProject projectWithoutAird = createCapellaProject("projectWithoutAird");
    // close and open project before deleting aird file to avoid a popup
    projectWithoutAird.close(null);
    projectWithoutAird.open(null);
    deleteFile(projectWithoutAird, CapellaResourceHelper.AIRD_FILE_EXTENSION);
    
    IProject projectWithoutCapellaModel = createCapellaProject("projectWithoutCapellaModel");
    deleteFile(projectWithoutCapellaModel, CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION);
    
    assertFalse("Migration must be disabled if the selection is not a Capella project", canMigrate(notACapellaProject));
    assertFalse("Migration must be disabled if the selection is a closed project", canMigrate(projectClosed));
    assertFalse("Migration must be disabled if the selection is a project without a capella model", canMigrate(projectClosed));
    assertTrue("Migration must be enabled for this valid project", canMigrate(validProject));
    
    assertFalse("Migration must be disabled if one element of the selection is not a Capella project", canMigrate(notACapellaProject, validProject));
    assertFalse("Migration must be disabled if one element of the selection is a closed project", canMigrate(projectClosed, validProject));
    assertFalse("Migration must be disabled if one element of the selection is a project without a capella model", canMigrate(projectClosed, validProject));
    assertTrue("Migration must be enabled for this multiple selection of valid projects", canMigrate(validProject, anotherValidProject));
    
    assertTrue("Migration must be enabled for this project without an afm", canMigrate(projectWithoutAfm));
    assertTrue("Migration must be enabled for this project without an aird", canMigrate(projectWithoutAird));
    assertFalse("Migration must be disable for this project without a capella model", canMigrate(projectWithoutCapellaModel));
  }
  
  private IProject createCapellaProject(String name) {
    return GuiActions.newProject(name, false);
  }
  
  private void deleteFile(IProject project, String extension) throws CoreException {
    project.getFile(project.getName()+"."+extension).delete(true, null);
  }
  
  private boolean canMigrate(IProject... projectSelection) {
    return new ProjectMigrationHandler().isValidSelection(Arrays.asList((Object[])projectSelection));
  }
}
