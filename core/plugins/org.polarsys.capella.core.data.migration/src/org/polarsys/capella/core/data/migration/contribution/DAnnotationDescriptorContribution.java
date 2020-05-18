/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.contribution;

import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.tools.report.util.LogExt;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.migration.Activator;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.diagram.helpers.DAnnotationHelper;
import org.polarsys.capella.core.diagram.helpers.IRepresentationAnnotationConstants;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.handler.helpers.SemanticResourcesScope;
import org.polarsys.capella.shared.id.handler.IdManager;

/**
 * This class migrates annotation from DRepresentation to DRepresentationDescriptor
 */
public class DAnnotationDescriptorContribution extends AbstractMigrationContribution {

  /**
   * Legacy annotation key for Contextual Elements
   */
  public static final String contextual_elements = "CONTEXTUAL_ELEMENTS"; //$NON-NLS-1$

  /**
   * Legacy annotation key for Initialize Diagram Elements
   */
  public static final String allocating_diagrams = "INITIALIZATION_REALIZING"; //$NON-NLS-1$

  /**
   * Legacy annotation key for Initialize Diagram Elements
   */
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
      diagrams.put(((DRepresentation) currentElement).getUid(), (DRepresentation) currentElement);
    }
  }

  @Override
  public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context) {
    super.postMigrationExecute(executionManager, resourceSet, context);

    int i = 0;
    for (String uid : diagrams.keySet()) {
      DRepresentation diagram = diagrams.get(uid);
      try {
        DRepresentationDescriptor descriptor = descriptors.get(diagram.getUid());
        if (descriptor != null) {
          int nb = migrate(diagram, descriptor, resourceSet);
          i += nb;
        }
      } catch (Exception e) {
        // Just a preventive catch while migration
      }
    }
    if (i > 0) {
      Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
      IStatus status = new Status(IStatus.INFO, Activator.PLUGIN_ID,
          NLS.bind(org.polarsys.capella.core.data.migration.contribution.Messages.MigrationAction_AnnotationMigration,
              context.getName(), i));
      LogExt.log(logger, status);
    }
  }

  @SuppressWarnings("deprecation")
  private int migrate(DRepresentation diagram, DRepresentationDescriptor descriptor, ResourceSet resourceSet) {
    int result = 0;

    // Remove annotations from Initialize From Existing Diagram, since never used and tooled.
    if (DAnnotationHelper.deleteAnnotation(allocating_diagrams, diagram)) {
      result++;
    }
    if (DAnnotationHelper.deleteAnnotation(allocated_diagrams, diagram)) {
      result++;
    }

    DAnnotation annotation = null;

    // Move progress status annotations towards the descriptor
    annotation = RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.NotVisibleInDoc, diagram);
    if (annotation != null) {
      RepresentationHelper.removeAnnotation(IRepresentationAnnotationConstants.NotVisibleInDoc, diagram);
      DAnnotationHelper.createAnnotation(IRepresentationAnnotationConstants.NotVisibleInDoc, descriptor);
      result++;
    }

    annotation = RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.NotVisibleInLM, diagram);
    if (annotation != null) {
      RepresentationHelper.removeAnnotation(IRepresentationAnnotationConstants.NotVisibleInLM, diagram);
      DAnnotationHelper.createAnnotation(IRepresentationAnnotationConstants.NotVisibleInLM, descriptor);
      result++;
    }

    annotation = RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.StatusReview, diagram);
    if (annotation != null) {
      DAnnotation created = DAnnotationHelper.createAnnotation(IRepresentationAnnotationConstants.StatusReview,
          descriptor);
      if (annotation.getDetails() != null) {
        created.getDetails().putAll(annotation.getDetails());
      }
      RepresentationHelper.removeAnnotation(IRepresentationAnnotationConstants.StatusReview, diagram);
      result++;
    }

    annotation = RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.ProgressStatus, diagram);
    if (annotation != null) {
      if (annotation.getDetails() != null) {
        String statusValue = annotation.getDetails().get(IRepresentationAnnotationConstants.PROGRESS_VALUE_KEYVALUE);

        try {
          IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(
              CapellacorePackage.Literals.CAPELLA_ELEMENT, CapellacorePackage.Literals.CAPELLA_ELEMENT__STATUS);

          if (null != query) {
            Collection<EObject> statuses = query
                .getAvailableElements((CapellaElement) CapellaAdapterHelper.resolveBusinessObject(descriptor));
            EnumerationPropertyLiteral literal = null;
            for (EObject review : statuses) {
              if (review instanceof EnumerationPropertyLiteral
                  && ((EnumerationPropertyLiteral) review).getLabel().equals(statusValue)) {
                literal = (EnumerationPropertyLiteral) review;
              }
            }
            if (literal != null) {
              DAnnotation created = DAnnotationHelper
                  .createAnnotation(IRepresentationAnnotationConstants.ProgressStatus, descriptor);
              created.getReferences().clear();
              created.getReferences().add(literal);
              result++;

            } else {
              Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
              IStatus status = new Status(IStatus.WARNING, Activator.PLUGIN_ID, NLS.bind(
                  org.polarsys.capella.core.data.migration.contribution.Messages.MigrationAction_MissingStatusMigration,
                  descriptor.getName(), statusValue));
              LogExt.log(logger, status);
            }
          }
        } catch (Exception e) {
          // Just a preventive catch while migration if migration occurs on an invalid descriptor
        }
      }
      RepresentationHelper.removeAnnotation(IRepresentationAnnotationConstants.StatusReview, diagram);
    }
    

    // Move contextual elements annotation towards the descriptor
    annotation = RepresentationHelper.getAnnotation(contextual_elements, diagram);
    if (annotation != null) {
      DAnnotation newAnnotation = null;
      for (String id : annotation.getDetails().values()) {
        EObject object = IdManager.getInstance().getEObject(id, new SemanticResourcesScope(resourceSet));
        if (object != null) {
          if (newAnnotation == null) {
            newAnnotation = DAnnotationHelper.createAnnotation(IRepresentationAnnotationConstants.ContextualElements, descriptor);
          }
          newAnnotation.getReferences().add(object);
          
        } else {
          Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
          IStatus status = new Status(IStatus.WARNING, Activator.PLUGIN_ID, NLS.bind(
              org.polarsys.capella.core.data.migration.contribution.Messages.MigrationAction_MissingContextualElementMigration,
              descriptor.getName(), id));
          LogExt.log(logger, status);
        }
      }

      RepresentationHelper.removeAnnotation(contextual_elements, diagram);
      result++;
    }
    
    return result;
  }

}
