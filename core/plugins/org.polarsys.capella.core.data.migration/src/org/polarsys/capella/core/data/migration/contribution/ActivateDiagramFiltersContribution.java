/*******************************************************************************
 * Copyright (c) 2022 OBEO.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Obeo - 2303 Add computed transitions in M&S diagrams
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.contribution;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.osgi.framework.Version;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.core.sirius.analysis.helpers.DDiagramHelper;

/**
 * This class activate filters for computed transitions on MSM diagrams. This contribution is only active for model
 * version 7.0.0.
 */
public class ActivateDiagramFiltersContribution extends AbstractMigrationContribution {

  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {
    super.unaryMigrationExecute(currentElement, context);
    if (isValidModelVersion(context) && currentElement instanceof DSemanticDiagram
        && DDiagramHelper.isMSM((DSemanticDiagram) currentElement)) {
      DSemanticDiagram diagram = (DSemanticDiagram) currentElement;
      DiagramDescription description = diagram.getDescription();

      List<FilterDescription> filtersToActivate = description.getFilters() //
          .stream()
          .filter(filter -> IFilterNameConstants.FILTER_MSM_HIDECOMPUTEDTRANSITIONS_ID.equals(filter.getName())) //
          .collect(Collectors.toList());

      diagram.getActivatedFilters().addAll(filtersToActivate);
    }
  }

  private boolean isValidModelVersion(MigrationContext context) {
    Version version = context.getCurrentVersion();
    return version.getMajor() < 6;
  }
}
