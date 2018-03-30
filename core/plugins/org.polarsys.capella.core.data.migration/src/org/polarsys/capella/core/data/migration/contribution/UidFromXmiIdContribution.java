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
import org.eclipse.sirius.business.internal.representation.DRepresentationDescriptorToDRepresentationLinkManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.data.migration.Activator;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

/**
 * This class resets diagram's uid based on its xmiid
 */
public class UidFromXmiIdContribution extends AbstractMigrationContribution {

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
      diagrams.put(currentElement.eResource().getURIFragment(currentElement), (DRepresentation) currentElement);
    }
  }

  /**
   * Migration from 1.1.x and 1.2.0 only
   */
  protected boolean isCompatibleVersion(MigrationContext context) {
    if (context.getCurrentVersion() != null) {
      if ((context.getCurrentVersion().getMajor() == 1 && context.getCurrentVersion().getMinor() == 1)
          || (context.getCurrentVersion().getMajor() == 1 && context.getCurrentVersion().getMinor() == 2
              && context.getCurrentVersion().getMicro() == 0)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context) {
    super.postMigrationExecute(executionManager, resourceSet, context);

    if (isCompatibleVersion(context)) {
      int i = 0;
      for (String xmiId : diagrams.keySet()) {
        DRepresentation diagram = diagrams.get(xmiId);
        try {
          DRepresentationDescriptor descriptor = descriptors.get(diagram.getUid());
          //we update diagram uid and related descriptor repPath
          if (descriptor != null && !xmiId.equals(diagram.getUid())) {
            diagram.setUid(xmiId);
            new DRepresentationDescriptorToDRepresentationLinkManager(descriptor).setRepresentation(diagram);
            i++;
          }
        } catch (Exception e) {
          // Just a preventive catch while migration
        }
      }
      if (i > 0) {
        IStatus status = new Status(IStatus.INFO, Activator.PLUGIN_ID, NLS.bind(org.polarsys.capella.core.data.migration.contribution.Messages.MigrationAction_UidMigration, context.getName(), i));
        Activator.getDefault().getLog().log(status);
      }
    }
  }

}
