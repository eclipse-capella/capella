/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.helpers;

import java.util.Collection;
import java.util.Iterator;

import junit.framework.Assert;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters.CompositeFilterApplicationBuilder;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * Utility tools for diagram
 */
public class DiagramHelper {

  /**
   * @param seqDiagram
   * @return
   */
  public static Diagram getDiagram(DDiagram seqDiagram) {
    EList<AnnotationEntry> ownedAnnotationEntries = seqDiagram.getOwnedAnnotationEntries();
    AnnotationEntry annotationEntry = ownedAnnotationEntries.get(0);
    EObject data = annotationEntry.getData();
    Assert.assertTrue(data instanceof Diagram);
    final Diagram diagram = (Diagram) data;
    return diagram;
  }

  /**
   * Return the DRepresention with the given name, null otherwise
   * 
   * @param session_p
   *          the current Session.
   * @param name_p
   * @return <code>null</code> if an error occurred.
   */
  public static DRepresentation getDRepresentation(final Session session_p, final String name_p) {
    DRepresentation result = null;
    Collection<DRepresentation> dRepresentations = DialectManager.INSTANCE.getAllRepresentations(session_p);
    Iterator<DRepresentation> it = dRepresentations.iterator();
    while (it.hasNext() && (null == result)) {
      result = it.next();
      if (!result.getName().equals(name_p)) {
        result = null;
      }
    }
    return result;
  }
  
  public static boolean isDiagramElementFiltered(DDiagramElement element) {
    return new DDiagramElementQuery(element).isFiltered();
  }

  public static FilterDescription getFilterForDiagram(DDiagram diagram, String filterName) {
    DiagramDescription description = diagram.getDescription();
    EList<FilterDescription> filters = description.getFilters();
    for (FilterDescription filter : filters) {
      if (filter.getName().equalsIgnoreCase(filterName)) {
        return filter;
      }
    }
    return null;
  }

  public static void addFilterInDiagram(final DDiagram diagram, final FilterDescription filter) {
    AbstractCommand cmd = new AbstractReadWriteCommand() {
      public void run() {
        EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        activatedFilters.add(filter);
        CompositeFilterApplicationBuilder builder = new CompositeFilterApplicationBuilder(diagram);
        builder.computeCompositeFilterApplications();
        DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());// refreshes
                                                                            // the
                                                                            // diagram
      }
    };
    TestHelper.getExecutionManager(diagram).execute(cmd);
  }  

}
