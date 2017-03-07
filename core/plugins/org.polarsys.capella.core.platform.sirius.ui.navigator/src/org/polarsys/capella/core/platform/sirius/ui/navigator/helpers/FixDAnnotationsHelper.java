/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.helpers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.handlers.Messages;
import org.polarsys.capella.core.ui.properties.annotations.IRepresentationAnnotationConstants;

import com.google.common.collect.ImmutableMap;

public class FixDAnnotationsHelper extends AbstractFixDiagramHelper {

  protected static Map<String, String> dAnnotationMigrationMapping = ImmutableMap.of(
      "http://www.thalesgroup.com/mde/melody/NotVisibleInDoc", IRepresentationAnnotationConstants.NotVisibleInDoc,
      "http://www.thalesgroup.com/mde/melody/NotVisibleInLM", IRepresentationAnnotationConstants.NotVisibleInLM,
      "http://www.thalesgroup.com/mde/melody/ProgressStatus", IRepresentationAnnotationConstants.ProgressStatus,
      "http://www.thalesgroup.com/mde/melody/StatusReview", IRepresentationAnnotationConstants.StatusReview);

  public FixDAnnotationsHelper() {
    setLogPrefix(Messages.FixDAnnotationsJobName);
  }
  
  public Set<DRepresentation> fixDiagramEventuallyClean(Session session, boolean onlyClean) {
		if(!onlyClean){
			return fixDiagram(session);
		}else{
			return cleanDiagram(session);
		}
  }

  @Override
  protected Map<DRepresentation, Integer> doFixDiagrams(Resource resource) {
    Map<DRepresentation, Integer> diagramToModifyObjectCount = new HashMap<DRepresentation, Integer>();

    DAnalysis dAnalysis = getFirstDAnalysis(resource);
    for (DView dView : dAnalysis.getOwnedViews()) {
      for (DRepresentation representation : dView.getOwnedRepresentations()) {

        for (String oldAnnotationID : dAnnotationMigrationMapping.keySet()) {
          DAnnotation oldAnnotation = RepresentationHelper.getAnnotation(oldAnnotationID, representation);

          // Old annotation is present
          if (oldAnnotation != null) {
            String newAnnotationID = dAnnotationMigrationMapping.get(oldAnnotationID);
            DAnnotation newAnnotation = RepresentationHelper.getAnnotation(newAnnotationID, representation);

            if (newAnnotation == null) {
              // No new annotation found, just replace old annotation by new annotation
              oldAnnotation.setSource(newAnnotationID);
            } else {
              // New annotation found, remove old annotation and preserve new one.
              RepresentationHelper.removeAnnotation(oldAnnotationID, representation);
            }
            incrementCounter(diagramToModifyObjectCount, representation);
          }
        }
      }
    }
    return diagramToModifyObjectCount;
  }
  
  protected Map<DRepresentation, Integer> doCleanDiagrams(Resource resource) {
	  Map<DRepresentation, Integer> diagramToModifyObjectCount = new HashMap<DRepresentation, Integer>();

	  DAnalysis dAnalysis = getFirstDAnalysis(resource);
	  	for (DView dView : dAnalysis.getOwnedViews()) {
	  		for (DRepresentation representation : dView.getOwnedRepresentations()) {

	  			for (String oldAnnotationID : dAnnotationMigrationMapping.keySet()) {
	  				DAnnotation oldAnnotation = RepresentationHelper.getAnnotation(oldAnnotationID, representation);

		          // Old annotation is present
		          if (oldAnnotation != null) {
		        	// Remove it
		        	RepresentationHelper.removeAnnotation(oldAnnotationID, representation);
			        incrementCounter(diagramToModifyObjectCount, representation);   
		          }
		        }
		      }
		 }
		 return diagramToModifyObjectCount;
  }

  public DAnalysis getFirstDAnalysis(Resource resource) {
    if (!CapellaResourceHelper.isAirdResource(resource.getURI())) {
      return null;
    }

    if (!resource.getAllContents().hasNext()) {
      return null;
    }

    for (Iterator<EObject> iterator = resource.getAllContents(); iterator.hasNext();) {
      EObject next = iterator.next();
      if (next instanceof DAnalysis) {
        return (DAnalysis) next;
      }
    }

    return null;
  }
}
