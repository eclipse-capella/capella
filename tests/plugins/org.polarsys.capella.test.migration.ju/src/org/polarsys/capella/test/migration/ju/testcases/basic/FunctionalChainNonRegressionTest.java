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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.polarsys.capella.core.compare.CapellaDiffPolicy;
import org.polarsys.capella.core.compare.CapellaMatchPolicy;
import org.polarsys.capella.core.compare.CapellaMergePolicy;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;

public class FunctionalChainNonRegressionTest extends BasicTestCase {

  private static final String SOURCE_MODEL = "FunctionalChains";
  private static final String TARGET_MIGRATED_MODEL = "FunctionalChainsNonRegression";

  private static final String SOURCE_MODEL_RESOURCE = SOURCE_MODEL + "."
      + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION;
  private static final String TARGET_MIGRATED_MODEL_RESOURCE = TARGET_MIGRATED_MODEL + "."
      + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION;;

  private IProject sourceModelProject;
  private IProject targetMigratedModelProject;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(SOURCE_MODEL, TARGET_MIGRATED_MODEL);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    sourceModelProject = IResourceHelpers.getEclipseProjectInWorkspace(SOURCE_MODEL);
    targetMigratedModelProject = IResourceHelpers.getEclipseProjectInWorkspace(TARGET_MIGRATED_MODEL);
  }

  @Override
  public void test() throws Exception {

    if (sourceModelProject.exists() && targetMigratedModelProject.exists()) {

      // migrate the project
      MigrationHelper.migrateProject(sourceModelProject);

      Resource sourceResource = getResourceToTest(sourceModelProject, SOURCE_MODEL_RESOURCE);
      Resource targetResource = getResourceToTest(targetMigratedModelProject, TARGET_MIGRATED_MODEL_RESOURCE);

      IEditableModelScope sourceProjectScope = new FragmentedModelScope(sourceResource, true);
      IEditableModelScope targetProjectScope = new FragmentedModelScope(targetResource, true);

      EComparisonImpl comparison = new EComparisonImpl(sourceProjectScope, targetProjectScope);

      // the order of elements for this particular feature is not relevant
      comparison.compute(new CapellaMatchPolicy(), new CapellaDiffPolicy() {
        @Override
        protected boolean doConsiderOrdered(EStructuralFeature feature_p) {
          if (feature_p.equals(FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS)) {
            return false;
          }
          return super.doConsiderOrdered(feature_p);
        }
      }, new CapellaMergePolicy(), new NullProgressMonitor());

      assertOnlyProjectNameDifference(comparison.getDifferences(Role.TARGET), SOURCE_MODEL);

      assertOnlyProjectNameDifference(comparison.getDifferences(Role.REFERENCE), TARGET_MIGRATED_MODEL);
    }

  }

  /**
   * Asserts that the only difference lies in the projects name
   * 
   * @param differences
   *          the list of differences
   * @param projectName
   *          the project name
   */
  public void assertOnlyProjectNameDifference(List<IDifference> differences, String projectName) {
    assertEquals(1, differences.size());

    IDifference difference = differences.get(0);
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
