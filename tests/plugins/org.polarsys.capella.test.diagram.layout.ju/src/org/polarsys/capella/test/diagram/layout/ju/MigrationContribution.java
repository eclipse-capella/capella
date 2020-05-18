/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.FoldingFilter;
import org.eclipse.sirius.diagram.FoldingPointFilter;
import org.eclipse.sirius.diagram.HideFilter;
import org.eclipse.sirius.diagram.HideLabelFilter;
import org.polarsys.capella.core.data.migration.aird.FilterMigrationContribution;
import org.polarsys.capella.core.data.migration.capella.ActorRefactoringMigrationContribution;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage;

public class MigrationContribution extends AbstractMigrationContribution {

  private Set<String> excludedFilters = new HashSet<>(Arrays.asList("ModelExtensionFilter",
      AbsoluteBoundsFilter.class.getSimpleName(), CollapseFilter.class.getSimpleName(),
      FoldingFilter.class.getSimpleName(), FoldingPointFilter.class.getSimpleName(), HideFilter.class.getSimpleName(),
      HideLabelFilter.class.getSimpleName()));

  @Override
  public Object getValue(EObject peekObject, EStructuralFeature feature, Object value, int position, Resource resource,
      MigrationContext context) {
    if (feature == LayoutPackage.Literals.ISEMANTIC_LAYOUT__ACTUAL_MAPPING) {
      String mapping = (String) value;
      mapping = URI.encodeFragment(mapping, false);
      if (ActorRefactoringMigrationContribution.MAPPINGS.containsKey(mapping)) {
        return URI.decode(ActorRefactoringMigrationContribution.MAPPINGS.get(mapping));
      }
    } else if (feature == LayoutPackage.Literals.ISEMANTIC_LAYOUT__APPLIED_FILTERS) {
      if (!(excludedFilters.contains(value)) && value instanceof String) {
        String filterName = (String) value;
        if (!filterName.endsWith(".filter")) {
          return FilterMigrationContribution.getValidFilterNameCandidate(filterName);
        }
      }
    }
    return super.getValue(peekObject, feature, value, position, resource, context);
  }

}
