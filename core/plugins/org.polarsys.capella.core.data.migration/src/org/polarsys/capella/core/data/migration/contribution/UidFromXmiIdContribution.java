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
import java.util.Map.Entry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.internal.representation.DRepresentationDescriptorToDRepresentationLinkManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.osgi.framework.Version;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.data.migration.Activator;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

/**
 * This class resets diagram's uid based on its xmiid
 */
public class UidFromXmiIdContribution extends AbstractMigrationContribution {

  HashMap<DRepresentation, String> representationToOriginalUidMap = new HashMap<>();
  HashMap<String, DRepresentationDescriptor> originalRepPathToDescriptorMap = new HashMap<>();

  @Override
  public void dispose(MigrationContext context) {
    super.dispose(context);
    representationToOriginalUidMap.clear();
    originalRepPathToDescriptorMap.clear();
  }

  @Override
  public void updateElement(EObject peekObject, String typeName, EObject result, EStructuralFeature feature,
      Resource resource, MigrationContext context) {
    super.updateElement(peekObject, typeName, result, feature, resource, context);

    // Store the original UID and representation of 1.2.x in a map in order to use later.
    if (result instanceof DRepresentation) {
      DRepresentation representation = (DRepresentation) result;
      String originalUid = representation.getUid();
      if (originalUid != null) {
        representationToOriginalUidMap.put(representation, originalUid);
      }
    }

    // Store the original RepPath and descriptor of 1.2.x in a map in order to use later.
    if (result instanceof DRepresentationDescriptor) {
      DRepresentationDescriptor descriptor = (DRepresentationDescriptor) result;
      if (descriptor.getRepPath() != null) {
        String originalRepPath = descriptor.getRepPath().getResourceURI().fragment();
        if (originalRepPath != null) {
          originalRepPathToDescriptorMap.put(originalRepPath, descriptor);
        }
      }
    }

  }

  /**
   * Migration from 1.2.x with x >= 1 only - In 1.2.0, it was still XMI:ID as a the reference for Representation. So
   * there is no problem.
   * 
   * - Since 1.2.x, it has been UID as the reference for Representation. However, in Sirius migration, it always
   * considers XMI:ID as the reference for Representation, so UID of each representation created in 1.2.x will be
   * totally replaced.
   * 
   * => In order to avoid so, we will RESET the uid of each representation in 1.3.0 to its own value in 1.2.x after the
   * migration of sirius is finished.
   */
  protected boolean isCompatibleVersion(MigrationContext context) {
    Version currentVersion = context.getCurrentVersion();
    if (currentVersion != null) {
      boolean isValidVersion = true;
      isValidVersion &= (currentVersion.getMajor() == 1);
      isValidVersion &= (currentVersion.getMinor() == 2);
      isValidVersion &= (currentVersion.getMicro() > 0);
      return isValidVersion;
    }
    return false;
  }

  @Override
  public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context) {
    super.postMigrationExecute(executionManager, resourceSet, context);

    if (isCompatibleVersion(context)) {
      int i = 0;
      for (Entry<DRepresentation, String> entry : representationToOriginalUidMap.entrySet()) {
        DRepresentation representation = entry.getKey();
        String originalUid = entry.getValue();
        // In 1.2.x, Representation.Uid == Descriptor.repPath
        DRepresentationDescriptor descriptor = originalRepPathToDescriptorMap.get(originalUid);

        if (descriptor != null && representation != null) {
          representation.setUid(originalUid);
          new DRepresentationDescriptorToDRepresentationLinkManager(descriptor).setRepresentation(representation);
          // This method updates the sirius CUSMTOM data "GMF_DIAGRAMS", which need to be corresponding
          // with Respresentation.UID and Descriptor.repPath
          // TODO: It is magic, not really understand!!
          // Is there any more comprehensible and clearer API method for doing so?
          ((XMIResource) representation.eResource()).setID(representation, originalUid);

          i++;
        }
      }

      if (i > 0) {
        IStatus status = new Status(IStatus.INFO, Activator.PLUGIN_ID,
            NLS.bind(org.polarsys.capella.core.data.migration.contribution.Messages.MigrationAction_UidMigration,
                context.getName(), i));
        Activator.getDefault().getLog().log(status);
      }
    }
  }

}
