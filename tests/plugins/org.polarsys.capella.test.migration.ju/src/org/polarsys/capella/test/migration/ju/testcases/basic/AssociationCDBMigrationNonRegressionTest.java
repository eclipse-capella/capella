/*******************************************************************************
 * Copyright (c) 2025, THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.polarsys.capella.core.compare.CapellaDiffPolicy;
import org.polarsys.capella.core.compare.CapellaMatchPolicy;
import org.polarsys.capella.core.compare.CapellaMergePolicy;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;

public class AssociationCDBMigrationNonRegressionTest extends BasicTestCase {

    private static final String SOURCE_MODELV6 = "ECM_Rapid_Implementation";
  private static final String SOURCE_MODEL = "ECM_Rapid_Implementation";

  private static final String TARGET_MIGRATED_MODEL = "ECM_Rapid_ImplementationNonRegression";

  private static final String SOURCE_MODEL_RESOURCE = SOURCE_MODEL + "."
      + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION;
  private static final String TARGET_MIGRATED_MODEL_RESOURCE = TARGET_MIGRATED_MODEL + "."
      + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION;;

  private IProject sourceModelProjectV6;

  private IProject targetMigratedModelProject;

  private List<String> testModels = new LinkedList<String>();

  @Override
  public List<String> getRequiredTestModels() {
      return testModels;
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    sourceModelProjectV6 = IResourceHelpers.getEclipseProjectInWorkspace(SOURCE_MODELV6);
    targetMigratedModelProject = IResourceHelpers.getEclipseProjectInWorkspace(TARGET_MIGRATED_MODEL);
    testModels.addAll(Arrays.asList(SOURCE_MODELV6, TARGET_MIGRATED_MODEL));
  }


  @Override
  protected void tearDown() throws Exception {
      testModels.clear();
      super.tearDown();
  }

  @Override
  public void test() throws Exception {

      if (sourceModelProjectV6.exists() && targetMigratedModelProject.exists()) {

          // migrate the project
          MigrationHelper.migrateProject(sourceModelProjectV6);

      Resource targetResource = getResourceToTest(targetMigratedModelProject, TARGET_MIGRATED_MODEL_RESOURCE);
      IEditableModelScope targetProjectScope = new FragmentedModelScope(targetResource, true);

      assessExpectedDifferences(sourceModelProjectV6, targetProjectScope);
    }

  }

  private void assessExpectedDifferences(IProject sourceModelProject, IEditableModelScope targetProjectScope) {
      Resource sourceResource = getResourceToTest(sourceModelProject, SOURCE_MODEL_RESOURCE);

      IEditableModelScope sourceProjectScope = new FragmentedModelScope(sourceResource, true);

      EComparisonImpl comparison = new EComparisonImpl(sourceProjectScope, targetProjectScope);

      // the order of elements for this particular feature is not relevant
      comparison.compute(new CapellaMatchPolicy(), new CapellaDiffPolicy() {}, new CapellaMergePolicy(), new NullProgressMonitor());

      assertOnlyProjectNameDifference(comparison.getDifferences(Role.REFERENCE), TARGET_MIGRATED_MODEL);
  }

  /**
   * Asserts that the only difference lies in the projects name
   * 
   * @param differences
   *          the list of differences
   * @param projectName
   *          the project name
   */
  public void assertOnlyProjectNameDifference(Collection<IDifference<EObject>> differences, String projectName) {
    assertEquals(1, differences.size());

    IDifference<EObject> difference = differences.iterator().next();
    assertTrue(difference instanceof EAttributeValuePresence);
    assertEquals(((EAttributeValuePresence) difference).getValue(), projectName);
  }

  private Resource getResourceToTest(IProject project, String fileName) {
    IFile file = project.getFile(fileName);
    URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
    ResourceSet resourceSet = new ResourceSetImpl();

    return resourceSet.getResource(fileURI, true);
  }

}
