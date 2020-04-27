/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
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
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;

/**
 * Checks that the migration of this model does not cause the loss of Representation descriptions.
 */
public class RepresentationDescriptionNonRegressionTest extends BasicTestCase {

  private static final String MODEL_NAME = "representationDescription";

  /**
   * This model contains for each architecture level representations that have as documentation their own name.
   *  Name: "[LAB] Drone Architecture" | Description: "[LAB] Drone Architecture"
   */
  protected IProject modelProject;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    modelProject = IResourceHelpers.getEclipseProjectInWorkspace(MODEL_NAME);
  }

  @Override
  public void test() throws Exception {

    if (modelProject.exists()) {
      MigrationHelper.migrateProject(modelProject);

      Session session = getSessionForTestModel(MODEL_NAME);

      Predicate<? super DRepresentationDescriptor> isDescriptorNotMigrated = descriptor -> //
      descriptor.getDocumentation() == null || descriptor.getDocumentation().isEmpty()
          || !descriptor.getDocumentation().contains(descriptor.getName());

      List<DRepresentationDescriptor> notMigratedDescriptors = DialectManager.INSTANCE //
          .getAllRepresentationDescriptors(session) //
          .stream() //
          .filter(isDescriptorNotMigrated) //
          .collect(Collectors.toList());

      if (!notMigratedDescriptors.isEmpty()) {
        String errorMsg = notMigratedDescriptors.stream().map(DRepresentationDescriptor::getName)
            .collect(Collectors.joining("\n"));

        assertTrue(errorMsg, notMigratedDescriptors.isEmpty());
      }
    }

  }

}
