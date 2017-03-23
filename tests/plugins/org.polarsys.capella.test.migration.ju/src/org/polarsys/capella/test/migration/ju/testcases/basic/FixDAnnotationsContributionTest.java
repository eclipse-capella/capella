/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.migration.ju.testcases.basic;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.ui.properties.annotations.IRepresentationAnnotationConstants;
import org.polarsys.capella.core.ui.properties.annotations.RepresentationAnnotationHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;
import org.polarsys.capella.test.migration.ju.model.MigrationDiagram;

public class FixDAnnotationsContributionTest extends MigrationDiagram {

  private static List<String> dAnnotationMigrationMapping = Arrays.asList(
      "http://www.thalesgroup.com/mde/melody/NotVisibleInDoc", "http://www.thalesgroup.com/mde/melody/NotVisibleInLM",
      "http://www.thalesgroup.com/mde/melody/ProgressStatus", "http://www.thalesgroup.com/mde/melody/StatusReview");

  private static String BASIC_ANNOTATION = "basicAnnotation";
  
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
    ExecutionManagerRegistry.getInstance().getExecutionManager(session.getTransactionalEditingDomain()).execute(new AbstractReadWriteCommand() {
      
      @Override
      public void run() {
        Session session = getSessionForTestModel(getRequiredTestModels().get(0));
        Collection<DRepresentation> diagrams = DialectManager.INSTANCE.getAllRepresentations(session);
        
        assertTrue("Test model should contain at least one diagram", !diagrams.isEmpty());

        
        for (DRepresentation representation : diagrams) {
          //Set a basic annotation
          RepresentationAnnotationHelper.setAnnotation(representation, BASIC_ANNOTATION, Boolean.FALSE);

          //Check that setAnnotation is working properly
          assertNotNull(RepresentationHelper.getAnnotation(BASIC_ANNOTATION, representation));
          
          //Set legacy management annotations
          for (String annotationKey : dAnnotationMigrationMapping) {
            RepresentationAnnotationHelper.setAnnotation(representation, annotationKey, Boolean.FALSE);
          }

          //Set current management annotations
          RepresentationAnnotationHelper.setAnnotation(representation, IRepresentationAnnotationConstants.NotVisibleInDoc, Boolean.FALSE);
          RepresentationAnnotationHelper.setAnnotation(representation, IRepresentationAnnotationConstants.NotVisibleInLM, Boolean.FALSE);
        }
        
      }
    });
    
    
    GuiActions.saveSession(session);
    GuiActions.closeSession(session);
  }

  private void checkAnnotations() {

    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    
    for (DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
      
      //Check that a basic annotation is kept after migration
      assertNotNull(RepresentationHelper.getAnnotation(BASIC_ANNOTATION, representation));

      //Check that a legacy management annotation is removed after migration
      for (String annotationKey : dAnnotationMigrationMapping) {
        assertNull(RepresentationHelper.getAnnotation(annotationKey, representation));
      }

      //Check that a current management annotation is kept after migration
      assertNotNull(RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.NotVisibleInDoc, representation));
      assertNotNull(RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.NotVisibleInLM, representation));

    }
    
    System.out.println();
  }

  
}
