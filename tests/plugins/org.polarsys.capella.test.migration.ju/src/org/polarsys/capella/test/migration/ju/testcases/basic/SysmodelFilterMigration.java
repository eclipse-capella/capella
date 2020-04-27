/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;

public class SysmodelFilterMigration extends BasicTestCase {

  private static final String FRAGMENT_SEPARATOR = "\\@";
  private static final String FILTER_SEPARATOR = "\\'";
  private static final String FRAGMENT_FILTER_KEY = "filters";

  private static final String PLUGIN_TYPE = "plugin";
  private static final String VALID_PLUGIN = "org.polarsys.capella.core.sirius.analysis";
  private static final String DESCRIPTION_TYPE = "description";

  private static final String PROJECT_NAME = "filter.sysmodel";
  private IProject project;
  private Map<DiagramDescription, Set<String>> validFilterNames;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PROJECT_NAME);
  }

  public SysmodelFilterMigration() {
    validFilterNames = new HashMap<>();
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    project = IResourceHelpers.getEclipseProjectInWorkspace(PROJECT_NAME);
  }

  @Override
  public void test() throws Exception {

    if (project.exists()) {
      // pre migration
      Session session = getSessionForTestModel(PROJECT_NAME);
      Map<String, Integer> expectedFilterNumbers = computeExpectedFilterNumber(session);
      session.close(new NullProgressMonitor());

      MigrationHelper.migrateProject(project);

      // post migration
      session = getSessionForTestModel(PROJECT_NAME);

      List<DSemanticDiagram> diagrams = DialectManager.INSTANCE.getAllRepresentations(session).stream(). //
          filter(DSemanticDiagram.class::isInstance). //
          map(DSemanticDiagram.class::cast). //
          collect(Collectors.toList());

      boolean success = true;

      for (DSemanticDiagram diagram : diagrams) {

        Integer expectedFilterNumber = expectedFilterNumbers.get(diagram.getUid());
        int currentFilterNumber = diagram.getActivatedFilters().size();

        if (expectedFilterNumber != currentFilterNumber) {
          success = false;
          int lostNumberOfFilters = expectedFilterNumber - currentFilterNumber;

          System.err.println(lostNumberOfFilters + " lost filters for " + EObjectExt.getText(diagram));
        }

        // no invalid filters
        for (FilterDescription filterDescription : diagram.getActivatedFilters()) {
          String filterName = filterDescription.getName();

          if (filterDescription.eIsProxy()) {
            InternalEObject internalObjectFilter = (InternalEObject) filterDescription;
            URI filterURI = internalObjectFilter.eProxyURI();

            if (shouldAnalyseFilter(filterURI) && !isValidFilterName(diagram.getDescription(), filterName)) {
              String invalidFilterName = extractFilterName(filterURI);
              System.err.println("Invalid filter " + invalidFilterName + " for " + EObjectExt.getText(diagram));
              success = false;
            }
          }
        }
      }
      assertTrue(success);
    }
  }

  protected Map<String, Integer> computeExpectedFilterNumber(Session session) {

    return DialectManager.INSTANCE.getAllRepresentations(session).stream(). //
        filter(DSemanticDiagram.class::isInstance). //
        map(DSemanticDiagram.class::cast). //
        collect(Collectors.toMap(DSemanticDiagram::getUid, diagram -> diagram.getActivatedFilters().size()));
  }

  protected boolean isValidFilterName(DiagramDescription diagramDescription, String filterName) {

    Set<String> validDiagramFilterNames = validFilterNames.computeIfAbsent(diagramDescription,
        desc -> desc.getFilters().stream().map(IdentifiedElement::getName).collect(Collectors.toSet()));

    return validDiagramFilterNames.contains(filterName);
  }

  protected String extractFilterName(URI filterURI) {
    String fragment = filterURI.fragment();

    String[] tokens = fragment.split(FRAGMENT_SEPARATOR);

    if (tokens.length > 0) {
      String filterFragment = tokens[tokens.length - 1];

      if (filterFragment.startsWith(FRAGMENT_FILTER_KEY)) {
        tokens = filterFragment.split(FILTER_SEPARATOR);

        if (tokens.length == 3) {
          return tokens[1];
        }
      }
    }
    return null;
  }

  private boolean shouldAnalyseFilter(URI filterURI) {
    String[] filterSegments = filterURI.segments();

    return filterSegments.length == 4 && PLUGIN_TYPE.equals(filterSegments[0]) && VALID_PLUGIN.equals(filterSegments[1])
        && DESCRIPTION_TYPE.equals(filterSegments[2]);
  }

}
