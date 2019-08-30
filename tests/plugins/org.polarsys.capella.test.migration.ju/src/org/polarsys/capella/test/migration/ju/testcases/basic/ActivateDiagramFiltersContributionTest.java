/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.migration.ju.testcases.basic;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;

public class ActivateDiagramFiltersContributionTest extends BasicTestCase {

  private final String SOURCE_MODEL = "FunctionalChains";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(SOURCE_MODEL);
  }

  @Override
  public void test() throws Exception {

    final String FCD_DIAGRAM_NAME = "[SFCD] FunctionalChain 1";
    final String OAB_DIAGRAM_NAME = "[OAB] Operational Context";
    final String SAB_DIAGRAM_NAME = "[SAB] System";
    final String LAB_DIAGRAM_NAME = "[LAB] Logical System";
    final String PAB_DIAGRAM_NAME = "[PAB] Physical System";

    IProject project = IResourceHelpers.getEclipseProjectInWorkspace(SOURCE_MODEL);

    if (project.exists()) {
      // Migrate the model
      MigrationHelper.migrateProject(project);
      checkFiltersMigrated(FCD_DIAGRAM_NAME, Arrays.asList(IFilterNameConstants.FILTER_FCD_HIDE_ASSOCIATION_LINKS));
      checkFiltersMigrated(OAB_DIAGRAM_NAME,
          Arrays.asList(IFilterNameConstants.FILTER_XAB_HIDE_SEQUENCING_INFORMATION));
      checkFiltersMigrated(SAB_DIAGRAM_NAME,
          Arrays.asList(IFilterNameConstants.FILTER_XAB_HIDE_SEQUENCING_INFORMATION));
      checkFiltersMigrated(LAB_DIAGRAM_NAME,
          Arrays.asList(IFilterNameConstants.FILTER_XAB_HIDE_SEQUENCING_INFORMATION));
      checkFiltersMigrated(PAB_DIAGRAM_NAME,
          Arrays.asList(IFilterNameConstants.FILTER_XAB_HIDE_SEQUENCING_INFORMATION));
    } else {
      fail("Referenced project does not exist");
    }
  }

  private void checkFiltersMigrated(String diagram, List<String> filters) {
    Session session = getSessionForTestModel(SOURCE_MODEL);
    DDiagram dDiagram = (DDiagram) DiagramHelper.getDRepresentation(session, diagram);
    Optional<String> unmigratedFilter = filters
        .stream().filter(filter -> !dDiagram.getActivatedFilters().stream()
            .map(activatedFilter -> activatedFilter.getName()).collect(Collectors.toList()).contains(filter))
        .findFirst();
    if (unmigratedFilter.isPresent()) {
      fail("The filter " + unmigratedFilter + " is not activated on the diagram " + dDiagram.getName());
    }
  }
}
