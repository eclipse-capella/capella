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
package org.polarsys.capella.core.data.migration.contribution;

import java.util.HashMap;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.data.migration.Activator;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.diagram.helpers.DAnnotationHelper;

/**
 * This class migrates annotation from DRepresentation to DRepresentationDescriptor
 */
public class DAnnotationDescriptorContribution extends AbstractMigrationContribution {

  public static final String allocating_diagrams = "INITIALIZATION_REALIZING"; //$NON-NLS-1$

  public static final String allocated_diagrams = "INITIALIZATION_REALIZED"; //$NON-NLS-1$
  
  HashMap<String, DRepresentationDescriptor> descriptors = new HashMap<String, DRepresentationDescriptor>();
  HashMap<String, DRepresentation> diagrams = new HashMap<String, DRepresentation>();

  @Override
  public void dispose(MigrationContext context) {
    super.dispose(context);
    descriptors.clear();
    diagrams.clear();
  }

  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {
    super.unaryMigrationExecute(currentElement, context);

    if (currentElement instanceof DRepresentationDescriptor) {
      if (((DRepresentationDescriptor) currentElement).getRepPath() != null) {
        descriptors.put(((DRepresentationDescriptor) currentElement).getRepPath().getResourceURI().fragment(),
            ((DRepresentationDescriptor) currentElement));
      }
    }

    if (currentElement instanceof DRepresentation) {
      diagrams.put(((DRepresentation)currentElement).getUid(), (DRepresentation) currentElement);
    }
  }

  @Override
  public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context) {
    super.postMigrationExecute(executionManager, resourceSet, context);

      int i = 0;
      for (String xmiId : diagrams.keySet()) {
        DRepresentation diagram = diagrams.get(xmiId);
        try {
          DRepresentationDescriptor descriptor = descriptors.get(diagram.getUid());
          if (descriptor != null) {
            if (migrate(diagram, descriptor)) {
              i++;
            }
          }
        } catch (Exception e) {
          // Just a preventive catch while migration
        }
      }
      if (i > 0) {
        IStatus status = new Status(IStatus.INFO, Activator.PLUGIN_ID, NLS.bind(org.polarsys.capella.core.data.migration.contribution.Messages.MigrationAction_AnnotationMigration, context.getName(), i));
        Activator.getDefault().getLog().log(status);
      }
  }
  
  private boolean migrate(DRepresentation diagram, DRepresentationDescriptor descriptor) {
    boolean result = false;
    
    //Remove annotations from Initialize From Existing Diagram, since never used and tooled.
    if (DAnnotationHelper.removeAnnotation(allocating_diagrams, diagram)) {
      result = true;
    }
    if (DAnnotationHelper.removeAnnotation(allocated_diagrams, diagram)) {
      result = true;
    }
    return result;
  }

}
