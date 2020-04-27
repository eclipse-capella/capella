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

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;

public class InvalidAirdReferenceMigrationTest extends BasicTestCase {
  private static final String SOURCE_MODEL = "InvalidAirdElement";

  // list of catalogelementLINK list
  private static final String CATALOG_ELEMENT_LINK_VALID_ID = "aea43153-d429-40af-b4c6-e94b37938167";

  private static final String CATALOG_ELEMENT_LINK_INVALID_ID_TAB[] = { "02e47334-fcc4-4e32-969c-5e34a0d65690",
      "a877eefc-a4ba-4ac2-976d-97b4026540ec", "caaeea07-82bd-49b5-a50a-ce898227bc0b",
      "e26ee636-8791-4ee6-8b07-23f08cd15961", "c5b5a23c-b47b-4459-b48e-b207f6fa3281",
      "ee2d2d33-383d-478f-8750-a1cf0dd621ab", "92e5d095-c227-49e9-aaf9-e9503de4cfd6",
      "a149ee02-67cd-47ef-aa86-036c28e44d8e", "6a9b09a2-f1f4-4c7e-a987-fd3f3d43816a",
      "ad3bddd5-e4cb-4e53-bded-1369ceb0e4a3" };

  private IProject sourceModelProject;

  @Override
  public void test() throws Exception {
    if (sourceModelProject.exists()) {
      // migrate the project
      MigrationHelper.migrateProject(sourceModelProject);

      Session session = getSessionForTestModel(SOURCE_MODEL);
      SessionContext context = new SessionContext(session);

      // Valid test
      EObject elem = context.getSemanticElement(CATALOG_ELEMENT_LINK_VALID_ID);
      assertTrue(((CatalogElementLink) elem).getTarget() != null);

      // invalid test
      for (String id : CATALOG_ELEMENT_LINK_INVALID_ID_TAB) {
        elem = context.getSemanticElement(id);
        assertEquals(((CatalogElementLink) elem).getTarget(), null);
      }

    }

  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(SOURCE_MODEL);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    sourceModelProject = IResourceHelpers.getEclipseProjectInWorkspace(SOURCE_MODEL);
  }

}
