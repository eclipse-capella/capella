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
package org.polarsys.capella.core.af.integration;

import java.util.Collections;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.osgi.framework.Version;
import org.polarsys.capella.common.bundle.FeatureHelper;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.platform.sirius.ted.IMetadataProvider;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaFeatureHelper;
import org.polarsys.kitalpha.ad.metadata.helpers.MetadataHelper;
import org.polarsys.kitalpha.ad.metadata.helpers.ViewpointMetadata;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;

/**
 * This class ensures version compliancy for Capella and viewpoints.
 */
public class CapellaMetadataProvider implements IMetadataProvider {

  /**
   * Return the platform current version, based on the Capella Viewpoint. If performance are here, we shall use it as
   * the main way to retrieve capella version
   */
  public static Version getCurrentVersion() {
    // We try to load the capella viewpoint first.
    try {
      Version version = ViewpointManager
          .readVersion(ViewpointManager.getViewpoint(AFIntegrationPlugin.CAPELLA_VIEWPOINT_ID));
      return version;

    } catch (Exception e) {
      // We never know if ViewpointManager return an exception
      return Version.parseVersion(FeatureHelper.getCapellaVersion(false));
    }
  }

  /**
   * For an AFM file, retrieve all used viewpoints and versions. Shall be located into Kitalpha
   */
  public static Map<String, Version> getViewpointsUsage(IFile afmFile) {

    // check all used VP are available (we suppose they are coming with migration tooling)
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getLoadOptions().put(GMFResource.OPTION_ABORT_ON_ERROR, Boolean.TRUE);
    resourceSet.getLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
    try {
      resourceSet.getResource(EcoreUtil2.getURI((IFile) afmFile), true);
      return MetadataHelper.getViewpointMetadata(resourceSet).getViewpointReferences();

    } finally {
      for (Resource r : resourceSet.getResources()) {
        r.unload();
      }
      resourceSet.getResources().clear();
    }
  }

  /**
   * Retrieve the AFM aside the given file
   */
  public static IFile getAFM(IFile fileToMigrate) {
    IFile file = fileToMigrate.getProject().getFile(fileToMigrate.getProjectRelativePath().removeFileExtension()
        .addFileExtension(ViewpointMetadata.STORAGE_EXTENSION));
    return file;
  }

  /**
   * For a model file, returns whether the model needs to be migrated or not.
   */
  public IStatus checkVersion(IFile file) {
    IFile afm = getAFM(file);

    // If there is no afm aside given file, we use the legacy way to check version
    if (!afm.exists()) {
      Version fileVersion = CapellaFeatureHelper.getFileVersion((IFile) file);
      Version currentVersion = CapellaMetadataProvider.getCurrentVersion();
      return isMigrationRequired(fileVersion, currentVersion);
    }

    return checkMetadata(file);
  }

  /**
   * For a given file, retrieve if the model is fully compatible with the current platform. Checks current platform
   * version and viewpoints.
   */
  public IStatus checkMetadata(IFile file) {
    IFile afm = getAFM(file);

    // If there is no afm file, we raise an error
    if (!afm.exists()) {
      return new Status(IStatus.ERROR, AFIntegrationPlugin.getSymbolicName(),
          NLS.bind(Messages.NoMetadataException_Message, EcoreUtil2.getURI(file).toPlatformString(true)));
    }

    ResourceSet set = new ResourceSetImpl();
    set.getLoadOptions().put(GMFResource.OPTION_ABORT_ON_ERROR, Boolean.TRUE);
    set.getLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
    try {
      URI uri = EcoreUtil2.getURI(file);
      set.getResource(uri, true);
      return checkMetadata(uri, set);

    } catch (Exception e) {
      return new Status(IStatus.ERROR, AFIntegrationPlugin.getSymbolicName(), e.getMessage());

    } finally {
      for (Resource r : set.getResources()) {
        r.unload();
      }
      set.getResources().clear();
    }
  }

  /**
   * For a given AFM file loaded in a resourceSet, retrieve if the model is fully compatible with the current platform.
   * Checks current platform version and viewpoints.
   */
  public IStatus checkMetadata(URI sessionResourceURI, ResourceSet set) {
    if (!ViewpointManager.getInstance(set).hasMetadata()) {
      // If there is no afm file but the current check is about a capella project, we must raise an exception because
      // the afm file is missing.
      if (CapellaResourceHelper.isCapellaProject(sessionResourceURI)) {
        return new Status(IStatus.ERROR, AFIntegrationPlugin.getSymbolicName(),
            NLS.bind(Messages.NoMetadataException_Message,
                MetadataHelper.getViewpointMetadata(set).getExpectedMetadataStorageURI().toPlatformString(true)));
      }
    } else {
      // If there is an afm file, first we check if the capella viewpoint is present in the platform
      org.polarsys.kitalpha.resourcereuse.model.Resource vp = ViewpointManager
          .getViewpoint(AFIntegrationPlugin.CAPELLA_VIEWPOINT_ID);
      if (vp != null) {
        // then, if the afm file has this capella viewpoint
        Version fileVersion = MetadataHelper.getViewpointMetadata(set).getVersion(vp);
        if (fileVersion != null) {
          Version currentVersion = getCurrentVersion();

          // If version of Capella is not compatible (not same major/minor), we raise a migration required message
          IStatus migration = isMigrationRequired(fileVersion, currentVersion);
          if (!migration.isOK()) {
            return migration;
          }
        }
      }

      // If this viewpoint or another viewpoint is not compatible (not same major/minor), we raise a
      // missing/incompatible viewpoint
      IStatus result = ViewpointManager.checkViewpointsCompliancy(set);
      if (!result.isOK()) {
        return result;
      }
    }

    return Status.OK_STATUS;
  }

  /**
   * Returns whether the file of the given fileVersion requires a migration to be compatible to currentVersion
   * 
   * @see See also org.polarsys.capella.core.data.migration.af.ViewpointMigrationContribution.isMigrationPossible
   */
  private IStatus isMigrationRequired(Version fileVersion, Version currentVersion) {
    
    // If model from <MAJOR>.x towards <MAJOR>.y, we requires a migration.
    if (fileVersion.getMajor() == currentVersion.getMajor() && fileVersion.getMinor() != currentVersion.getMinor()) {
      return new Status(IStatus.ERROR, AFIntegrationPlugin.getSymbolicName(),
          NLS.bind(Messages.WrongCapellaVersionException_DetailedMessage, fileVersion));
    }
    
    // if not the same major/minor we requires a migration
    if (!(fileVersion.getMajor() == currentVersion.getMajor() && fileVersion.getMinor() == currentVersion.getMinor())) {
      return new Status(IStatus.ERROR, AFIntegrationPlugin.getSymbolicName(),
          Messages.WrongCapellaVersionException_Message);
    }

    
    return Status.OK_STATUS;
  }

  /**
   * Register the given resource to the current session
   */
  public void registerMetadataResource(Resource resource, final Session session, final IProgressMonitor monitor) {
    if (resource != null && session != null) {
      final URI metadataResourceURI = resource.getURI();
      ExecutionManagerRegistry.getInstance().getExecutionManager(session.getTransactionalEditingDomain())
          .execute(new AbstractReadWriteCommand() {
            @Override
            public void run() {
              session.addSemanticResource(metadataResourceURI, monitor);
            }
          });
    }
  }

  /**
   * Creates the AFM metadata resource</br>
   * (This resource will only be created when the aird resource does not exist yet, if the aird resource already exists
   * then a model migration shall be done)
   * 
   * @param domain
   * @param resourceURI
   * @param monitor
   * @return the created resource (may be null if the resource does not belong to a Capella project)
   */
  public Resource createMetadataResource(TransactionalEditingDomain domain, URI resourceURI, IProgressMonitor monitor) {
    SubMonitor progress = SubMonitor.convert(monitor, 2);
    try {
      if (!CapellaResourceHelper.isCapellaProject(resourceURI)) {
        return null;
      }

      URI uri = resourceURI.trimFileExtension().appendFileExtension(ViewpointMetadata.STORAGE_EXTENSION);

      progress.beginTask("Create an empty metadata resource", 1);
      Resource resource = MetadataHelper.getViewpointMetadata(domain.getResourceSet()).initMetadataStorage(uri);
      progress.worked(1);

      try {
        progress.beginTask("Save metadata model", 1);
        resource.save(Collections.emptyMap());
      } catch (Exception e) {
        // we couldn't do this
      }
      progress.worked(1);
      return resource;
    } finally {
      progress.done();
    }
  }

}