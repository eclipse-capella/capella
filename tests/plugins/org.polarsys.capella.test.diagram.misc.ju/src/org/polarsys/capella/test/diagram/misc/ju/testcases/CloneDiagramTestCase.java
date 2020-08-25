/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.junit.Assert;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.diagram.helpers.DAnnotationHelper;
import org.polarsys.capella.core.diagram.helpers.RepresentationAnnotationHelper;
import org.polarsys.capella.core.diagram.helpers.naming.DAnnotationSourceConstants;
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
    Session session = getSession(projectName);
    HeadlessCapellaAnalysisSelector.INSTANCE.setSelectedURI(session, TestHelper.getAirdResource(session).getURI());
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(projectName);
    assertNotNull(session);

    DRepresentation originalRepresentation = DiagramHelper.getDRepresentation(session, diagramName);
    DRepresentationDescriptor originalDescriptor = RepresentationHelper.getRepresentationDescriptor(session,
        originalRepresentation);

    int sizeBforeCopy = originalDescriptor.getEAnnotations().size();

    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(originalDescriptor);
    domain.getCommandStack().execute(new RecordingCommand(domain) {

      @Override
      protected void doExecute() {
        // Clone
        DRepresentationDescriptor clonedDescriptor = GuiActions.CloneDiagram(originalDescriptor);

        // Check that name is different
        assertNotEquals(originalDescriptor.getName(), clonedDescriptor.getName());

        // Check description
        assertEquals(originalRepresentation.getDocumentation(), clonedDescriptor.getDocumentation());

        // Check annotations
        int sizeAfterCopy = clonedDescriptor.getEAnnotations().size();
        assertEquals(sizeBforeCopy, sizeAfterCopy);

        // Check package
        DAnnotation originalAnnotation = DAnnotationHelper
            .getAnnotation(DAnnotationSourceConstants.CAPELLA_DIAGRAM_PACKAGE, originalDescriptor, false);
        DAnnotation clonedAnnotation = DAnnotationHelper
            .getAnnotation(DAnnotationSourceConstants.CAPELLA_DIAGRAM_PACKAGE, clonedDescriptor, false);
        assertNotNull(clonedAnnotation);

        String originalPkg = originalAnnotation.getDetails()
            .get(DAnnotationSourceConstants.CAPELLA_DIAGRAM_PACKAGE_KEY);
        String clonedPkg = clonedAnnotation.getDetails().get(DAnnotationSourceConstants.CAPELLA_DIAGRAM_PACKAGE_KEY);
        assertEquals(originalPkg, clonedPkg);

        // Check contextual elements
        List<EObject> originalContextualElements = ContextualDiagramHelper.getService()
            .getContextualElements(originalDescriptor);
        List<EObject> contextualElements = ContextualDiagramHelper.getService().getContextualElements(clonedDescriptor);
        Assert.assertArrayEquals(originalContextualElements.toArray(), contextualElements.toArray());

        // Check visible in doc
        assertEquals(RepresentationAnnotationHelper.isVisibleInDoc(originalDescriptor),
            RepresentationAnnotationHelper.isVisibleInDoc(clonedDescriptor));

        // Check visible for traceability
        assertEquals(RepresentationAnnotationHelper.isVisibleInLM(originalDescriptor),
            RepresentationAnnotationHelper.isVisibleInLM(clonedDescriptor));

        // Check review
        assertEquals(RepresentationAnnotationHelper.getStatusReview(originalDescriptor),
            RepresentationAnnotationHelper.getStatusReview(clonedDescriptor));

        // Check progress status
        assertEquals(RepresentationAnnotationHelper.getProgressStatus(originalDescriptor),
            RepresentationAnnotationHelper.getProgressStatus(clonedDescriptor));
      }
    });
  }
}
