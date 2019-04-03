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

import org.eclipse.core.resources.IProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
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
    
    IProject project = IResourceHelpers.getEclipseProjectInWorkspace(SOURCE_MODEL);
    
    if (project.exists()) {
      
      // Migrate the model
      MigrationHelper.migrateProject(project);
      
      // Get the session again, because it might have changed
      Session session = getSessionForTestModel(SOURCE_MODEL);
      DDiagram fcdDiagram = (DDiagram) DiagramHelper.getDRepresentation(session, FCD_DIAGRAM_NAME);
      
      // Now check that the filter of interest is present on the diagram and is activated by default
      for (FilterDescription filter : fcdDiagram.getActivatedFilters()) {
        if (filter.getName().equals(IFilterNameConstants.FILTER_FCD_HIDE_ASSOCIATION_LINKS)) {
          return;
        }
      }
      
      fail("The filter is not activated on the diagram");
    }
    
    else {
      fail("Referenced project does not exist");
    }
  }
}
