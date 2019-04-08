/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.contribution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.osgi.framework.Version;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.af.integration.CapellaMetadataProvider;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.core.sirius.analysis.helpers.DDiagramHelper;

/**
 * This class activate filters for computed elements FCD Diagrams.
 */
public class ActivateDiagramFiltersContribution extends AbstractMigrationContribution {

  @Override
  public void unaryEndMigrationExecute(ExecutionManager executionManager, Resource resource, MigrationContext context) {
    super.unaryEndMigrationExecute(executionManager, resource, context);
    if (isMigrationRequired(context)) {
      activateRelevantDiagramFilters(resource);
    }
  }

  private void activateRelevantDiagramFilters(Resource resource) {
    for (DDiagram diagram : getAllDiagrams(resource)) {
      if (DDiagramHelper.isFCD(diagram) || DDiagramHelper.isOPD(diagram)) {
        activateDiagramFilter(diagram, IFilterNameConstants.FILTER_FCD_HIDE_ASSOCIATION_LINKS);
      }
      else if (DDiagramHelper.isXAB(diagram)) {
        activateDiagramFilter(diagram, IFilterNameConstants.FILTER_XAB_HIDE_SEQUENCING_INFORMATION);
      }
    }
  }

  private List<DDiagram> getAllDiagrams(Resource resource) {
    List<DDiagram> allDiagrams = new ArrayList<>();
    if (!CapellaResourceHelper.isAirdResource(resource.getURI())) {
      return Collections.emptyList();
    }
    for (EObject root : resource.getContents()) {
      if (root instanceof DDiagram) {
        allDiagrams.add((DDiagram) root);
      }
    }
    return allDiagrams;
  }
  
  private void activateDiagramFilter(DDiagram diagram, String filterName) {
    EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
    for (FilterDescription filter : diagram.getDescription().getFilters()) {
      if (filterName.equals(filter.getName()) && !activatedFilters.contains(filter)) {
        activatedFilters.add(filter);
      }
    }
  }
  
  // We migrate from 1.2.x/1.3.0 --> 1.3.x (x >= 1)
  private boolean isMigrationRequired(MigrationContext context) {
    Version fileVersion = context.getCurrentVersion();
    Version platformVersion = CapellaMetadataProvider.getCurrentVersion();
    return (((fileVersion.getMajor() == 1 && fileVersion.getMinor() == 2 && fileVersion.getMicro() >= 0)
        || (fileVersion.getMajor() == 1 && fileVersion.getMinor() == 3 && fileVersion.getMicro() == 0))
        && (platformVersion.getMajor() == 1 && platformVersion.getMinor() == 3 && platformVersion.getMicro() >= 1));
  }
}
