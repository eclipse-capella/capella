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
package org.polarsys.capella.test.migration.ju.testcases.basic;

import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.migration.contribution.DAnnotationDescriptorContribution;
import org.polarsys.capella.core.diagram.helpers.DAnnotationHelper;
import org.polarsys.capella.core.diagram.helpers.IRepresentationAnnotationConstants;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;
import org.polarsys.capella.test.migration.ju.model.MigrationDiagram;

public class DAnnotationDescriptorContributionTest extends MigrationDiagram {

  @Override
  public void test() throws Exception {

    IProject project = IResourceHelpers.getEclipseProjectInWorkspace(getRequiredTestModels().get(0));

    if (project.exists()) {
      addAnnotations();
      MigrationHelper.migrateProject(project);
      checkAnnotations();
    }

  }

  private void addAnnotations() {

    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    ExecutionManagerRegistry.getInstance().getExecutionManager(session.getTransactionalEditingDomain())
        .execute(new AbstractReadWriteCommand() {

          @Override
          public void run() {
            Session session = getSessionForTestModel(getRequiredTestModels().get(0));
            Collection<DRepresentationDescriptor> diagrams = DialectManager.INSTANCE
                .getAllRepresentationDescriptors(session);

            DRepresentationDescriptor descriptor = (DRepresentationDescriptor) diagrams.toArray()[0];
            DRepresentationDescriptor descriptor2 = (DRepresentationDescriptor) diagrams.toArray()[1];

            assertTrue("Test model should contain at least one diagram", !diagrams.isEmpty());

            DRepresentation representation = descriptor.getRepresentation();
            DRepresentation representation2 = descriptor2.getRepresentation();

            DAnnotation annotation = null;
            
            // create annotation on diagram for Initialize Diagram Elements
            annotation = DAnnotationHelper.getAnnotation(DAnnotationDescriptorContribution.allocated_diagrams, representation, true);
            
            annotation = DAnnotationHelper.getAnnotation(DAnnotationDescriptorContribution.allocating_diagrams, representation, true);

            // create annotation on diagram for Doc
            annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.NotVisibleInDoc, representation, true);

            // create annotation on diagram for LM
            annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.NotVisibleInLM, representation, true);


            // create annotation on diagram for Progress Status
            annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.ProgressStatus,
                representation, true);
            annotation.getDetails().put(IRepresentationAnnotationConstants.PROGRESS_VALUE_KEYVALUE, "DRAFT");

            annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.StatusReview,
                representation, true);
            annotation.getDetails().put(IRepresentationAnnotationConstants.REVIEW_VALUE_KEYVALUE, "MyReview");

            // create annotation on diagram for Progress Status
            annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.ProgressStatus,
                representation2, true);
            annotation.getDetails().put(IRepresentationAnnotationConstants.PROGRESS_VALUE_KEYVALUE, "WEIRD");

          }
        });

    GuiActions.saveSession(session);
    GuiActions.closeSession(session);
  }

  private void checkAnnotations() {

    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    Collection<DRepresentationDescriptor> diagrams = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);

    DRepresentationDescriptor descriptor = (DRepresentationDescriptor) diagrams.toArray()[0];
    DRepresentationDescriptor descriptor2 = (DRepresentationDescriptor) diagrams.toArray()[1];

    DAnnotation annotation = null;

    // Initialize Diagram elements annotation must not exist on both descriptor or diagram
    annotation = DAnnotationHelper.getAnnotation(DAnnotationDescriptorContribution.allocated_diagrams, descriptor, false);
    assertTrue(annotation == null);
    annotation = DAnnotationHelper.getAnnotation(DAnnotationDescriptorContribution.allocating_diagrams, descriptor, false);
    assertTrue(annotation == null);

    annotation = DAnnotationHelper.getAnnotation(DAnnotationDescriptorContribution.allocated_diagrams, descriptor.getRepresentation(), false);
    assertTrue(annotation == null);
    annotation = DAnnotationHelper.getAnnotation(DAnnotationDescriptorContribution.allocating_diagrams, descriptor.getRepresentation(), false);
    assertTrue(annotation == null);

    
    // annotation on descriptor for Doc must exist
    annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.NotVisibleInDoc, descriptor, false);
    assertTrue(annotation != null);

    // annotation on descriptor for LM must exist
    annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.NotVisibleInLM, descriptor, false);
    assertTrue(annotation != null);

    // annotation on descriptor for Progress Status must exist
    annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.ProgressStatus, descriptor, false);
    assertTrue(annotation != null);

    annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.StatusReview, descriptor, false);
    assertTrue(annotation != null);

    // annotation on descriptor for Progress Status must exist
    annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.ProgressStatus, descriptor2, false);
    assertTrue(annotation == null);

  }

}
