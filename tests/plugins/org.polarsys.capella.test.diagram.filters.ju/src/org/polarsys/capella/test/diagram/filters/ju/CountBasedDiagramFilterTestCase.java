/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.junit.Assert;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.NonDirtyTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.HelperMessages;

/**
 * This class is a generic test case for diagram filter test. To use it, create a test case that inherits from this
 * class and implement abstract methods, that are (see method documentation for more details) :
 * 
 * - getTestProjectName() - getDiagramName() - getFilterName() - getBeforeFilterDiagramElementCount() -
 * getAfterFilterDiagramElementCount
 * 
 * Notice that the tested diagram in the model of test must not have the tested filter activated by default.
 **/
public abstract class CountBasedDiagramFilterTestCase extends NonDirtyTestCase {

  // Following values are obtained by using methods defined in concrete test cases
  protected String diagramName = getDiagramName();
  protected String projectTestName = getTestProjectName();
  protected String filterName = getFilterName();

  // Internal variables
  protected Session session;

  // Following methods must be overridden by concrete test cases
  /** returns the name of the test project folder (by default in the folder "model") */
  protected abstract String getTestProjectName();

  /** returns the name of the tested diagram in the test project */
  protected abstract String getDiagramName();

  /** returns the name of the tested filter in the tested diagram */
  protected abstract String getFilterName();

  /** returns the diagram element count before applying the filter */
  protected abstract int getBeforeFilterDiagramElementCount();

  /** returns the diagram element count after applying the filter */
  protected abstract int getAfterFilterDiagramElementCount();

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }

  protected boolean isFiltered(DDiagramElement elt) {
    return DiagramHelper.isDiagramElementFiltered(elt);
  }

  @Override
  public void test() {
    session = getSessionForTestModel(projectTestName);
    IFile airdFile = getAirdFileForLoadedModel(projectTestName);
    GuiActions.openSession(airdFile, true);
    DDiagram diagram = (DDiagram) DiagramHelper.getDRepresentation(session, diagramName);
    Assert.assertNotNull(MessageFormat.format(HelperMessages.diagramNotContainedInSession, diagramName, airdFile),
        diagram);// test case check
    
    // Refresh
    DiagramHelper.refreshDiagram(diagram);
    
    // Check that count match before applying filter
    assertTrue(diagram.getDiagramElements().size() == getBeforeFilterDiagramElementCount());

    // Activate the filter
    FilterDescription filter = DiagramHelper.getFilterForDiagram(diagram, filterName);
    Assert.assertNotNull(MessageFormat.format(HelperMessages.filterNotFound, filterName, diagramName), filter);// test
    DiagramHelper.addFilterInDiagram(diagram, filter);
 
    // Refresh
    DiagramHelper.refreshDiagram(diagram);

    // Count the filtered objects
    int filteredCount = 0;
    for (DDiagramElement elt : diagram.getDiagramElements()) {
      if (isFiltered(elt)) {
        filteredCount++;
      }
    }

    // Check that count match after applying filter
    assertTrue((diagram.getDiagramElements().size()-filteredCount) == getAfterFilterDiagramElementCount());
  }
}
