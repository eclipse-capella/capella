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
package org.polarsys.capella.test.framework.actions.gui;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters.CompositeFilterApplicationBuilder;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * @author Erwan Brottier
 */
public class SiriusActions {

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
