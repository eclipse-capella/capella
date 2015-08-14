/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.aird;

import java.util.HashMap;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.xml.sax.Attributes;

/**
 * 
 */
public class SiriusMigrationContribution extends AbstractMigrationContribution {

  HashMap<Resource, String> versions = new HashMap<Resource, String>();

  @Override
  public void newResource(Resource resource, MigrationContext context) {
    super.newResource(resource, context);

    if (CapellaResourceHelper.AIRD_FILE_EXTENSION.equals(resource.getURI().fileExtension())) {
      String version = (String) ((XMLResource) resource).getDefaultLoadOptions().get("VERSION");
      versions.put(resource, version);
    }

  }

  @Override
  public boolean ignoreUnknownFeature(String prefix, String name, boolean isElement, EObject peekObject, String value,
      XMLResource resource, MigrationContext context) {
    return false;
  }

  @Override
  public boolean ignoreSetFeatureValue(EObject peekObject, EStructuralFeature feature, Object value, int position,
      XMLResource resource, MigrationContext context) {

    if (XMLTypePackage.Literals.ANY_TYPE.equals(peekObject.eClass()) && "initialized".equals(feature.getName())) {
      return true;
    }
    if (XMLTypePackage.Literals.ANY_TYPE.equals(peekObject.eClass()) && "diagramSet".equals(feature.getName())) {
      return true;
    }
    return super.ignoreSetFeatureValue(peekObject, feature, value, position, resource, context);
  }

  @Override
  public void endElement(EObject peekEObject, Attributes attribs, String uri, String localName, String name,
      Resource resource, MigrationContext context) {
    RepresentationsFileMigrationService.getInstance().postXMLEndElement(peekEObject, attribs, uri, name, name,
        getLoadedVersion(resource));
  }

  private String getLoadedVersion(Resource resource) {
    return versions.get(resource);
  }

  @Override
  public boolean isValidResource(IResource uri, MigrationContext context) {
    return CapellaResourceHelper.isAirdResource(uri, true);
  }

  @Override
  public Object getValue(EObject peekObject, EStructuralFeature feature, Object value, int position, Resource resource,
      MigrationContext context) {
    String version = getLoadedVersion(resource);
    return RepresentationsFileMigrationService.getInstance().getValue(peekObject, feature, value, version);
  }

  @Override
  public EStructuralFeature getFeature(EObject peekObject, EStructuralFeature feature, Resource resource,
      MigrationContext context) {
    String version = getLoadedVersion(resource);
    return RepresentationsFileMigrationService.getInstance().getAffiliation(peekObject.eClass(), feature, version);
  }

}
