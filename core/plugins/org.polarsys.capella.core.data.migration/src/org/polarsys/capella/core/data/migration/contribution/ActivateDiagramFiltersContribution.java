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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.osgi.framework.Version;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.af.integration.CapellaMetadataProvider;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

/**
 * This class activate filters for computed component exchanges and physical links on LAB and PAB diagrams.
 */
public class ActivateDiagramFiltersContribution extends AbstractMigrationContribution {


  @Override
  public void unaryEndMigrationExecute(ExecutionManager executionManager, Resource resource, MigrationContext context) {
    super.unaryEndMigrationExecute(executionManager, resource, context);
    if(isMigrationRequired(context)){
      activateRelevantDiagramFilters(resource);      
    }
  }

  private void activateRelevantDiagramFilters(Resource resource) {
    for (DDiagram diagram : getAllLabAndPabDiagrams(resource)) {
      for (FilterDescription filter : diagram.getDescription().getFilters()) {
        if (isRelevantFilter(filter.getName()) && !diagram.getActivatedFilters().contains(filter)) {
          diagram.getActivatedFilters().add(filter);
        }
      }
    }
  }
  
  private boolean isRelevantFilter(String filterName) {
    return IFilterNameConstants.FILTER_LAB_HIDE_COMPUTED_CE.equals(filterName)
        || IFilterNameConstants.FILTER_PAB_HIDE_COMPUTED_CE.equals(filterName)
        || IFilterNameConstants.FILTER_LAB_HIDE_COMPUTED_PL.equals(filterName)
        || IFilterNameConstants.FILTER_PAB_HIDE_COMPUTED_PL.equals(filterName);
  }
  
  private List<DDiagram> getAllLabAndPabDiagrams(Resource resource) {
    List<DDiagram> allDiagrams = new ArrayList<>();
    if (!CapellaResourceHelper.isAirdResource(resource.getURI())) {
      return Collections.emptyList();
    }
    for (EObject root : resource.getContents()) {
      if (root instanceof DDiagram) {
        DDiagram diagram = (DDiagram) root;
        if (IMappingNameConstants.PAB_DIAGRAM.equals(diagram.getDescription().getName())
            || IMappingNameConstants.LAB_DIAGRAM.equals(diagram.getDescription().getName())) {
          allDiagrams.add((DDiagram) root);
        }
      }
    }
    return allDiagrams;
  }
  
  private boolean isMigrationRequired(MigrationContext context){
    Version fileVersion = context.getCurrentVersion();
    Version platformVersion = CapellaMetadataProvider.getCurrentVersion();
    return ((fileVersion.getMajor() == 1 && fileVersion.getMinor() == 1 && fileVersion.getMicro() >= 0)
     && (platformVersion.getMajor() == 1 && platformVersion.getMinor() == 2 && platformVersion.getMicro() >= 0));
  }
}
