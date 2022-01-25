/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.aird;

import java.util.HashMap;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.UnresolvedReferenceException;
import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.data.migration.Activator;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.xml.sax.Attributes;

/**
 * This class triggers the first part of sirius repair action (metamodel modifications)
 */
@SuppressWarnings("restriction")
public class SiriusMigrationContribution extends AbstractMigrationContribution {

  public static final String SIRIUS_VERSION = "VERSION"; //$NON-NLS-1$

  /**
   * <aird, siriusVersion>
   */
  HashMap<Resource, String> versions = null;

  protected void addVersion(Resource resource, String version) {
    if (versions == null) {
      versions = new HashMap<Resource, String>();
    }
    versions.put(resource, version);
  }

  @Override
  public void newResource(Resource resource, MigrationContext context) {
    super.newResource(resource, context);

    if (CapellaResourceHelper.isAirdResource(resource.getURI())) {
      String version = (String) ((XMLResource) resource).getDefaultLoadOptions().get(SIRIUS_VERSION);
      addVersion(resource, version);
    }

  }

  /**
   * This method returns whether the given resource is an aird or not.
   * 
   * @apiNote this method shall not be called until the newResource has been called for a given resource.
   * 
   * @implNote this method could have been replaced by CapellaResourceHelper.isAirdResource but since each
   *           AbstractMigrationContribution is called on every EObject of all resources, its 10x quicker to check if
   *           version map has been filled by newResource method rather than looking for fileExtension substrings by
   *           CapellaResourceHelper.isAirdResource
   */
  private boolean isAirdResource(Resource resource) {
    return versions != null && versions.containsKey(resource);
  }

  private String getLoadedVersion(Resource resource) {
    if (versions != null) {
      return versions.get(resource);
    }
    return null;
  }

  @Override
  public void dispose(MigrationContext context) {
    super.dispose(context);
    if (versions != null) {
      versions.clear();
      versions = null;
    }
  }

  @Override
  public void dispose(ExecutionManager manager, ResourceSet resourceSet, MigrationContext context) {
    super.dispose(manager, resourceSet, context);
    if (versions != null) {
      versions.clear();
      versions = null;
    }
  }

  @Override
  public String getQName(EObject peekObject, String typeQName, EStructuralFeature feature, Resource resource,
      XMLHelper helper, MigrationContext context) {
    if ("viewpoint:DRepresentationContainer".equals(typeQName)) {
      return "viewpoint:DView";
    }
    return super.getQName(peekObject, typeQName, feature, resource, helper, context);
  }

  @Override
  public boolean ignoreSetFeatureValue(EObject peekObject, EStructuralFeature feature, Object value, int position,
      XMLResource resource, MigrationContext context) {
    return super.ignoreSetFeatureValue(peekObject, feature, value, position, resource, context);
  }

  @Override
  public void endElement(EObject peekEObject, Attributes attribs, String uri, String localName, String name,
      Resource resource, MigrationContext context) {

    // since we override the xmlHandler of airdResource, we need to call it here.
    if (isAirdResource(resource)) {
      String version = getLoadedVersion(resource);
      RepresentationsFileMigrationService.getInstance().postXMLEndElement(peekEObject, attribs, uri, name, name,
          version);
    }
  }

  @Override
  public Object getValue(EObject peekObject, EStructuralFeature feature, Object value, int position, Resource resource,
      MigrationContext context) {

    // since we override the xmlHelper of airdResource, we need to call it here.
    if (isAirdResource(resource)) {
      String version = getLoadedVersion(resource);
      return RepresentationsFileMigrationService.getInstance().getValue(peekObject, feature, value, version);
    }

    return super.getValue(peekObject, feature, value, position, resource, context);
  }

  @Override
  public EStructuralFeature getFeature(EObject peekObject, EStructuralFeature feature, Resource resource,
      MigrationContext context) {
    // It shoud be already called by RepresentationsFileExtendedMetaData registered on the aird resource in
    // AirdMigrationRunnable.AirDResourceImpl.init()
    return null;
  }

  @Override
  public void updateElement(EObject peekObject, String typeName, EObject result, EStructuralFeature feature,
      Resource resource, MigrationContext context) {

    // since we override the xmlHelper of airdResource, we need to call it here.
    if (isAirdResource(resource)) {
      String version = getLoadedVersion(resource);
      RepresentationsFileMigrationService.getInstance().updateCreatedObject(result, version);
    }

  }

  @Override
  public IStatus handleError(XMIException e, Resource resource, MigrationContext context) {

    // lower the unresolvedReferenceException to a lower priority. it's the repair-aird that will fix these issues.
    if (e instanceof UnresolvedReferenceException) {
      return new Status(IStatus.WARNING, Activator.getDefault().getBundle().getSymbolicName(), e.getMessage());
    }

    return null;
  }

}
