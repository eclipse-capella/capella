/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.af;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.framework.Version;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.af.integration.AFIntegrationPlugin;
import org.polarsys.capella.core.af.integration.CapellaMetadataProvider;
import org.polarsys.capella.core.data.migration.Activator;
import org.polarsys.capella.core.data.migration.capella.Messages;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaFeatureHelper;
import org.polarsys.kitalpha.ad.metadata.helpers.MetadataHelper;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;

/**
 * This contribution ensures :
 * 
 * <li>That model isn't too old to perform migration</li>
 * <li>that all used viewpoints are available while migration</li>
 * <li>load the Capella current version of the migrated model</li>
 * 
 * It read in priority the AFM file to retrieve the version.
 * 
 * @author Thomas Guiu
 */
public class ViewpointMigrationContribution extends AbstractMigrationContribution {

  @Override
  public IStatus preMigrationExecute(IResource fileToMigrate, MigrationContext context, boolean checkVersion) {

    if (fileToMigrate instanceof IFile && MetadataHelper.isMetadataResource(fileToMigrate)) {
      return checkAFM(fileToMigrate, context, checkVersion);

    } else if (CapellaResourceHelper.isCapellaResource(fileToMigrate)) {
      // We check AFM
      IFile afm = CapellaMetadataProvider.getAFM((IFile) fileToMigrate);
      if (afm.exists()) {
        return checkAFM(afm, context, checkVersion);
      }

      // Otherwise, we check the old way to retrieve version
      return checkLegacy((IFile) fileToMigrate, context, checkVersion);

    } else if (CapellaResourceHelper.isAirdResource(fileToMigrate, true)) {
      // We check AFM
      IFile afm = CapellaMetadataProvider.getAFM((IFile) fileToMigrate);
      if (afm.exists()) {
        return checkAFM(afm, context, checkVersion);
      }
    }

    return Status.OK_STATUS;
  }

  @Override
  public void dispose(ExecutionManager manager, ResourceSet resourceSet, MigrationContext context) {
    super.dispose(manager, resourceSet, context);
  }

  private IStatus checkLegacy(IFile fileToMigrate, MigrationContext context, boolean checkVersion) {
    // If there is no AFM aside model, then we try to find version with the legacy way to retrieve version
    Version fileVersion = CapellaFeatureHelper.getFileVersion(fileToMigrate);
    context.setFileVersion(fileToMigrate, fileVersion);

    if (checkVersion) {
      Version currentVersion = CapellaMetadataProvider.getCurrentVersion();
      return isMigrationPossible(fileVersion, currentVersion, context);
    }
    return Status.OK_STATUS;
  }

  private IStatus checkAFM(IResource fileToMigrate, MigrationContext context, boolean checkVersion) {

    MultiStatus status = new MultiStatus(AFIntegrationPlugin.getSymbolicName(), IStatus.OK,
        "Some viewpoints are missing", null);

    try {
      if (Version.emptyVersion.equals(context.getFileVersion((IFile) fileToMigrate))) {

        Map<String, Version> viewpointUsages = CapellaMetadataProvider.getViewpointsUsage((IFile) fileToMigrate);

        // We load the AFM file and check if there is an incompatibility with Capella version
        Version fileVersion = viewpointUsages.get(AFIntegrationPlugin.CAPELLA_VIEWPOINT_ID);
        context.setFileVersion((IFile) fileToMigrate, fileVersion);

        if (checkVersion) {
          Version currentVersion = CapellaMetadataProvider.getCurrentVersion();
          IStatus version = isMigrationPossible(fileVersion, currentVersion, context);
          if (!version.isOK()) {
            return version;
          }
        }

        // We check for additional missing viewpoints
        for (String id : viewpointUsages.keySet()) {
          if (ViewpointManager.getViewpoint(id) == null) {
            status.add(new Status(IStatus.ERROR, AFIntegrationPlugin.getSymbolicName(),
                "The viewpoint '" + id + "' is missing"));
          }
        }

        return status;
      }
    } catch (Exception e) {
      e.printStackTrace();
      status.add(new Status(IStatus.ERROR, AFIntegrationPlugin.getSymbolicName(), e.getMessage()));
      return status;
    }

    return Status.OK_STATUS;

  }

  /**
   * Returns whether the file with the given version can be migrated towards to current version. Only n-1 version can be
   * migrated
   */
  private IStatus isMigrationPossible(Version fileVersion, Version currentVersion, MigrationContext context) {
    if (Version.emptyVersion.equals(fileVersion)) {
      String formattedMessage = NLS.bind(Messages.MigrationAction_ErrorDialog_CorruptedMessage,
          new String[] { context.getResourceName() });
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, formattedMessage);
    }

    if (!isMigrationPossible(fileVersion, currentVersion)) {
      String formattedMessage = NLS.bind(Messages.MigrationAction_ErrorDialog_TooOldMessage,
          new String[] { context.getResourceName(), currentVersion.toString() });
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, formattedMessage);
    }

    return Status.OK_STATUS;
  }

  @Override
  public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context) {

    if (CapellaResourceHelper.isAirdResource(context.getResource(), true)) {
      Resource resource = resourceSet.getResource(EcoreUtil2.getURI(context.getResource()), false);
      Resource initMetadata = MetadataHelper.initMetadata(resource);
      if (initMetadata != null) {
        boolean found = false;
        EObject eObject = resource.getContents().get(0);
        if (eObject instanceof DAnalysis) {
          DAnalysis analysis = (DAnalysis) eObject;
          ResourceDescriptor descriptor = new ResourceDescriptor(initMetadata.getURI());
          URI descriptorURI = descriptor.getResourceURI();
          EList<ResourceDescriptor> semanticResources = analysis.getSemanticResources();
          for (ResourceDescriptor semanticResource : semanticResources) {
            URI semanticResourceURI = semanticResource.getResourceURI();
            String str = URI.encodeFragment(semanticResourceURI.toString(), true);
            if (str.equals(descriptorURI.toString())) {
              found = true;
              break;
            }
          }
          if (!found) {
            semanticResources.add(descriptor);
          }
        }
      }
    }
  }

  /**
   * Returns whether the file with the given version can be migrated towards to current version. Only n-1 version can be
   * migrated
   * 
   * @see See also org.polarsys.capella.core.af.integration.CapellaMetadataProvider.isMigrationRequired
   */
  private boolean isMigrationPossible(Version fileVersion, Version currentVersion) {
    if (fileVersion.getMajor() == 0)
      return false;
    if (fileVersion.getMajor() == 1)
      return fileVersion.getMinor() == 4;
    if (fileVersion.getMajor() < currentVersion.getMajor())
      return true;
    if (fileVersion.getMajor() == currentVersion.getMajor())
      return fileVersion.getMinor() <= currentVersion.getMinor();
    return false;
  }
}
