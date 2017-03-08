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

import java.lang.management.ManagementFactory;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 *
 */
public abstract class AbstractFixDiagramHelper {

  private static final Logger LOGGER = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.UI);

  private String logPrefix;

  public AbstractFixDiagramHelper() {
    // Do nothing
  }

  public Set<DRepresentation> fixDiagram(Session session) {
    Set<DRepresentation> res = new HashSet<DRepresentation>();

    for (Resource resource : session.getAllSessionResources()) {
      res.addAll(fixDiagram(resource));
    }
    return res;
  }
  
  public Set<DRepresentation> cleanDiagram(Session session) {
	  Set<DRepresentation> res = new HashSet<DRepresentation>();
		for (Resource resource : session.getAllSessionResources()) {
			res.addAll(cleanDiagram(resource));
		}
		return res; 
  }
  
  public Set<DRepresentation> fixDiagram(Resource resource) {
    // Init
    long start = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();

    // Execute the specific fix
    Map<DRepresentation, Integer> diagramToModifyObjectCount = doFixDiagrams(resource);
    
    // Finalize
    long stop = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
    
    // Log in info view
    logInfoView (resource, start, stop, diagramToModifyObjectCount);
    return diagramToModifyObjectCount.keySet();
  }
  
  public Set<DRepresentation> cleanDiagram(Resource resource) {
	    // Init
	    long start = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();

	    // Execute the specific fix
	    Map<DRepresentation, Integer> diagramToModifyObjectCount = doCleanDiagrams(resource);
	    
	    // Finalize
	    long stop = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
	    
	    // Log in info view
	    logInfoView (resource, start, stop, diagramToModifyObjectCount);
	    return diagramToModifyObjectCount.keySet();
  }
  
  private void logInfoView(Resource resource, long start, long stop, Map<DRepresentation, Integer> diagramToModifyObjectCount) {
    if (!diagramToModifyObjectCount.keySet().isEmpty()) {
      logInfo("-----");
      int totalModifiedObjectCount = 0;
      for (DRepresentation diagram : diagramToModifyObjectCount.keySet()) {
        Integer count = diagramToModifyObjectCount.get(diagram);
        logInfo(count + " annotation(s) restored in " + diagram.getName());
        totalModifiedObjectCount = totalModifiedObjectCount + count;
      }
      logInfo("-----");
      logInfo("Total restored annotation(s) : " + totalModifiedObjectCount);
      logInfo("Total fixed diagrams : " + diagramToModifyObjectCount.keySet().size());

      logInfo("Diagrams fix took : " + (stop - start) / 1000000 + " ms");
    } else {
      logInfo("Nothing to fix in " + resource.getURI().lastSegment());
    }
    logInfo("End processing " + resource.getURI().lastSegment());
    logInfo("-----------------------------------------------------------");
  }

  abstract protected Map<DRepresentation, Integer> doFixDiagrams(Resource resource);
  
  protected Map<DRepresentation, Integer> doCleanDiagrams(Resource resource) {
	return null;
  }

  protected void logInfo(String message) {
    LOGGER.info(logPrefix + " : " + message);
  }

  protected void incrementCounter(Map<DRepresentation, Integer> diagramToModifyObjectCount,
      DRepresentation representation) {
    // Put in the map
    Integer count = diagramToModifyObjectCount.get(representation);
    if (count != null) {
      count = count + 1;
      diagramToModifyObjectCount.put(representation, count);
    } else {
      diagramToModifyObjectCount.put(representation, 1);
    }
  }

  public String getLogPrefix() {
    return logPrefix;
  }

  public void setLogPrefix(String logPrefix) {
    this.logPrefix = logPrefix;
  }
}