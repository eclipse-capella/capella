/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.contribution;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.osgi.framework.Version;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

/**
 * Migration Contribution that activates a number of filters for existing model diagrams. <br/>
 * This contribution is only active for model versions 1.4.x or 5.0.0.
 *
 */
public class EnableFCAndPPFiltersMigrationContribution extends AbstractMigrationContribution {

  private static final Set<String> FILTER_CANDIDATE_NAMES = new HashSet<>(
      Arrays.asList(IMappingNameConstants.HIDE_OVERLAPPED_PHYSICAL_PATHS_ICON,
          IMappingNameConstants.HIDE_OVERLAPPED_PHYSICAL_PATHS_LABEL,
          IMappingNameConstants.HIDE_OVERLAPPED_FUNCTIONAL_CHAINS_ICON,
          IMappingNameConstants.HIDE_OVERLAPPED_FUNCTIONAL_CHAINS_LABEL));

  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {

    super.unaryMigrationExecute(currentElement, context);

    if (isValidModelVersion(context) && currentElement instanceof DSemanticDiagram) {
      DSemanticDiagram diagram = (DSemanticDiagram) currentElement;
      DiagramDescription description = diagram.getDescription();

      List<FilterDescription> filtersToActivate = description.getFilters() //
          .stream() //
          .filter(filter -> FILTER_CANDIDATE_NAMES.contains(filter.getName())) //
          .collect(Collectors.toList());

      diagram.getActivatedFilters().addAll(filtersToActivate);
    }
  }

  private boolean isValidModelVersion(MigrationContext context) {
    Version version = context.getCurrentVersion();
    return (version.getMajor() == 1 && version.getMinor() == 4) || (version.getMajor() == 5 && version.getMinor() == 0);
  }

}
