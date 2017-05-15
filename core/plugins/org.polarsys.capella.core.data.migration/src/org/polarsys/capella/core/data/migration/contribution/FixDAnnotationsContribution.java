/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

/**
 * This class removes legacy annotations on diagrams
 */
public class FixDAnnotationsContribution extends AbstractMigrationContribution {

  private static List<String> dAnnotationMigrationMapping = Arrays.asList(
      "http://www.thalesgroup.com/mde/melody/NotVisibleInDoc", "http://www.thalesgroup.com/mde/melody/NotVisibleInLM",
      "http://www.thalesgroup.com/mde/melody/ProgressStatus", "http://www.thalesgroup.com/mde/melody/StatusReview");

  @Override
  public void unaryEndMigrationExecute(ExecutionManager executionManager, Resource resource, MigrationContext context) {
    super.unaryEndMigrationExecute(executionManager, resource, context);
    cleanDiagram(resource);
  }

  public Set<DRepresentation> cleanDiagram(Resource resource) {
    Map<DRepresentation, Integer> diagramToModifyObjectCount = doCleanDiagrams(resource);
    return diagramToModifyObjectCount.keySet();
  }

  protected Map<DRepresentation, Integer> doCleanDiagrams(Resource resource) {
    Map<DRepresentation, Integer> diagramToModifyObjectCount = new HashMap<DRepresentation, Integer>();
    for (DRepresentation representation : getAllRepresentations(resource)) {
      for (String oldAnnotationID : dAnnotationMigrationMapping) {
        DAnnotation oldAnnotation = RepresentationHelper.getAnnotation(oldAnnotationID, representation);
        if (oldAnnotation != null) {
          RepresentationHelper.removeAnnotation(oldAnnotationID, representation);
        }
      }
    }
    return diagramToModifyObjectCount;
  }

	public List<DRepresentation> getAllRepresentations(Resource resource) {
		List<DRepresentation> allRepresentations = new ArrayList<>();
		if (!CapellaResourceHelper.isAirdResource(resource.getURI())) {
			return Collections.emptyList();
		}
		for (EObject root : resource.getContents()) {
			if (root instanceof DRepresentation) {
				allRepresentations.add((DRepresentation) root);
			}
		}
		return allRepresentations;
	}
}
