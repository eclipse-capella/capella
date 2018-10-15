/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.test.diagram.common.ju.headless.selector.HeadlessCapellaAnalysisSelector;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Test the case when you select a diagram and clone it .
 *
 */
public class CloneDiagramTestCase extends BasicTestCase {

  private String diagramName = "[CDB] Play Video Movie - Physical";
  private String projectName = "TestCloneDiagram";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectName);
  }
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    // The headless analysis selector should be initialized since the clone action can ask to select analysis
    HeadlessCapellaAnalysisSelector.INSTANCE
        .setSelectedURI(TestHelper.getAirdResource(getSession(projectName)).getURI());
  }
  
  @Override
  public void test() throws Exception {
    Session session = getSession(projectName);
    assertNotNull(session);
    DRepresentation drepresentation = DiagramHelper.getDRepresentation(session, diagramName);
    DRepresentationDescriptor drd = RepresentationHelper.getRepresentationDescriptor(session, drepresentation);
    Set<DRepresentationDescriptor> representations = new HashSet<DRepresentationDescriptor>();
    representations.add(drd);
    int sizeBforeCopy = drd.getEAnnotations().size();
    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(drd);
    domain.getCommandStack().execute(new RecordingCommand(domain) {

      @Override
      protected void doExecute() {
        Collection<DRepresentationDescriptor> cloneRepresentation = GuiActions.CloneDiagram(representations);
        int sizeAfterCopy = 0;
        for (DRepresentationDescriptor representationDescriptor : cloneRepresentation) {

          sizeAfterCopy = representationDescriptor.getEAnnotations().size();
        }
        assertEquals(sizeBforeCopy, sizeAfterCopy);
      }
    });

  }

}
