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

  public Set<DRepresentation> fixDiagram(Resource resource) {
    // Init
    long start = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();

    // Execute the specific fix
    Map<DRepresentation, Integer> diagramToModifiedObjectCount = doFixDiagrams(resource);

    // Finalize
    long stop = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
    if (!diagramToModifiedObjectCount.keySet().isEmpty()) {
      logInfo("-----");
      int totalModifiedObjectCount = 0;
      for (DRepresentation diagram : diagramToModifiedObjectCount.keySet()) {
        Integer count = diagramToModifiedObjectCount.get(diagram);
        logInfo("Modified " + count + " object(s) in diagram: " + diagram.getName());
        totalModifiedObjectCount = totalModifiedObjectCount + count;
      }
      logInfo("-----");
      logInfo("Total modified objects: " + totalModifiedObjectCount);
      logInfo("Total modified diagrams: " + diagramToModifiedObjectCount.keySet().size());

      logInfo("Fix diagrams took: " + (stop - start) / 1000000 + " ms");
    } else {
      logInfo("Nothing to fix in " + resource.getURI().lastSegment());
    }
    logInfo("End processing " + resource.getURI().lastSegment());
    logInfo("-----------------------------------------------------------");
    return diagramToModifiedObjectCount.keySet();
  }

  abstract protected Map<DRepresentation, Integer> doFixDiagrams(Resource resource);

  protected void logInfo(String message) {
    LOGGER.info(logPrefix + " : " + message);
  }

  protected void incrementCounter(Map<DRepresentation, Integer> diagramToModifiedObjectCount,
      DRepresentation representation) {
    // Put in the map
    Integer count = diagramToModifiedObjectCount.get(representation);
    if (count != null) {
      count = count + 1;
      diagramToModifiedObjectCount.put(representation, count);
    } else {
      diagramToModifiedObjectCount.put(representation, 1);
    }
  }

  public String getLogPrefix() {
    return logPrefix;
  }

  public void setLogPrefix(String logPrefix) {
    this.logPrefix = logPrefix;
  }
}