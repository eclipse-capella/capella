/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.helpers;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.handlers.Messages;

public class FixDAnnotationsCleanOptionHelper extends FixDAnnotationsHelper {

	public FixDAnnotationsCleanOptionHelper() {
	    setLogPrefix(Messages.FixDAnnotationsJobName);
	}
	
	public Set<DRepresentation> fixDiagramEventuallyClean(Session session, boolean onlyClean) {
		if(!onlyClean){
			return fixDiagram(session);
		}
		
		Set<DRepresentation> res = new HashSet<DRepresentation>();
		for (Resource resource : session.getAllSessionResources()) {
			res.addAll(cleanOldDAnnotations(resource));
		}
		return res;
	}
	
	
	public Set<DRepresentation> cleanOldDAnnotations(Resource resource) {
		// Init
		long start = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();

		// Execute the specific fix
		Map<DRepresentation, Integer> diagramToModifyObjectCount = doCleanOldDAnnotations(resource);
		    
		// Finalize
		long stop = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
		    
		// Log in info view
		logInfoView (resource, start, stop, diagramToModifyObjectCount);
		return diagramToModifyObjectCount.keySet();
	}
	
	 private Map<DRepresentation, Integer> doCleanOldDAnnotations(Resource resource) {
		 Map<DRepresentation, Integer> diagramToModifyObjectCount = new HashMap<DRepresentation, Integer>();

		    DAnalysis dAnalysis = getFirstDAnalysis(resource);
		    for (DView dView : dAnalysis.getOwnedViews()) {
		      for (DRepresentationDescriptor descriptor : dView.getOwnedRepresentationDescriptors()) {
		    	DRepresentation representation = descriptor.getRepresentation();
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

	private void logInfoView(Resource resource, long start, long stop, Map<DRepresentation, Integer> diagramToModifyObjectCount) {
		    if (!diagramToModifyObjectCount.keySet().isEmpty()) {
		      logInfo("-----");
		      int totalModifiedObjectCount = 0;
		      for (DRepresentation diagram : diagramToModifyObjectCount.keySet()) {
		        Integer count = diagramToModifyObjectCount.get(diagram);
		        logInfo(count+" annotation(s) removed from "+ diagram.getName());
		        totalModifiedObjectCount = totalModifiedObjectCount + count;
		      }
		      logInfo("-----");
		      logInfo("Total removed annotation(s) : " + totalModifiedObjectCount);
		      logInfo("Total fixed diagrams : " + diagramToModifyObjectCount.keySet().size());
		      logInfo("Diagrams fix took : " + (stop - start) / 1000000 + " ms");
		    } else {
		      logInfo("Nothing to fix in " + resource.getURI().lastSegment());
		    }
		    logInfo("End processing " + resource.getURI().lastSegment());
		    logInfo("-----------------------------------------------------------");
	 }
}
