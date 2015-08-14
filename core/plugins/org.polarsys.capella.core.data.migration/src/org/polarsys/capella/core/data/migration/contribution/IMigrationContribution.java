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
package org.polarsys.capella.core.data.migration.contribution;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.xml.sax.Attributes;

/**
 * 
 */
public interface IMigrationContribution {

  public IStatus preMigrationExecute(IResource fileToMigrate, MigrationContext context);

  public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet, MigrationContext context);

  public void postMigrationExecuteCommands(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context);

  public void preSaveResource(ExecutionManager executionManager, Resource resource, MigrationContext context);

  public boolean isValidResource(IResource uri, MigrationContext context);

  public boolean isValidURI(URI uri, MigrationContext context);

  public Object getValue(EObject peekObject, EStructuralFeature feature, Object value, int position, Resource resource,
      MigrationContext context);

  public EStructuralFeature getFeature(EObject peekObject, EStructuralFeature feature, Resource resource,
      MigrationContext context);

  public String getFeatureName(String prefix, String name, boolean isElement, EObject peekObject, String value,
      Resource resource, MigrationContext context);

  public String getQName(EObject peekObject, String typeQName, EStructuralFeature feature, Resource resource,
      XMLHelper helper, MigrationContext context);

  public EFactory getEFactory(String prefix, Resource resource, MigrationContext context);

  public void updateElement(EObject peekObject, String typeName, EObject result, EStructuralFeature feature,
      Resource resource, MigrationContext context);

  public void dispose();

  public void dispose(ExecutionManager manager, ResourceSet resourceSet, MigrationContext context);

  /**
   * Should return "xmlns:prefix"
   * @param prefix
   * @param context
   * @return
   */
  public String getNSPrefix(String prefix, MigrationContext context);

  public String getNSURI(String prefix, String nsUri, MigrationContext context);

  public void contributePackageRegistry(org.eclipse.emf.ecore.EPackage.Registry packageRegistry,
      MigrationContext context);

  public void unaryPostMigrationExecute(Notifier currentElement, MigrationContext context);

  public void unaryEndPostMigrationExecute(ExecutionManager executionManager, Resource resource,
      MigrationContext context);

  public void newResource(Resource resource, MigrationContext context);

  public String getHandleProxy(InternalEObject proxy, String uriLiteral, Resource resource, MigrationContext context);

  public IStatus handleError(XMIException e, Resource resource, MigrationContext context);

  public void endElement(EObject peekEObject, Attributes attribs, String uri, String localName, String name,
      Resource resource, MigrationContext context);

  public boolean ignoreSetFeatureValue(EObject peekObject, EStructuralFeature feature, Object value, int position,
      XMLResource resource, MigrationContext context);

  public boolean ignoreUnknownFeature(String prefix, String name, boolean isElement, EObject peekObject, String value,
      XMLResource resource, MigrationContext context);

  /**
   * @param resource
   * @param result
   */
  public void createdXMLHelper(XMLResource resource, XMLHelper result);
}
