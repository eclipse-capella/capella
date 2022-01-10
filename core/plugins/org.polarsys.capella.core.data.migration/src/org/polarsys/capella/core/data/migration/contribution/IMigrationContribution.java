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
package org.polarsys.capella.core.data.migration.contribution;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
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

  /**
   * This method is called for each files that needs to be migrated (each files given per contributor)
   */
  IStatus preMigrationExecute(IResource fileToMigrate, MigrationContext context, boolean checkVersion);

  /**
   * This method allows to do additional migration on the resourceSet before resources are saved (this is run in a transactional command)
   */
  void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet, MigrationContext context);

  /**
   * This method allows to do additional commands on the resourceSet before resources are saved
   */
  void postMigrationExecuteCommands(ExecutionManager executionManager, ResourceSet resourceSet, MigrationContext context);

  /**
   * This method allows to do additional processing before saving any resources
   */
  void preSaveResource(ExecutionManager executionManager, Resource resource, MigrationContext context);

  /**
   * This method allows to retrieve the EFactory for the given prefix
   */
  EFactory getEFactory(String prefix, Resource resource, MigrationContext context);

  /**
   * This method allows to rename the type that will be created for the given typeQName into peekObject.eGet(feature)
   */
  String getQName(EObject peekObject, String typeQName, EStructuralFeature feature, Resource resource, XMLHelper helper, MigrationContext context);

  /**
   * This method allows to rename a feature
   */
  String getFeatureName(String prefix, String name, boolean isElement, EObject peekObject, String value, Resource resource, MigrationContext context);

  /**
   * This methods allows to retrieve another feature for the given feature
   */
  EStructuralFeature getFeature(EObject peekObject, EStructuralFeature feature, Resource resource, MigrationContext context);
  
  /**
   * This methods allows to retrieve another feature for the given feature
   */
  EStructuralFeature getFeature(EObject object, String prefix, String name, boolean isElement);
  
  /**
   * This method allows to retrieve another value for the given object and the given feature
   */
  Object getValue(EObject peekObject, EStructuralFeature feature, Object value, int position, Resource resource, MigrationContext context);

  /**
   * This method allows to do additional stuff to the given element created
   */
  void updateElement(EObject peekObject, String typeName, EObject createdElement, EStructuralFeature feature, Resource resource, MigrationContext context);

  /**
   * This method is called at the end of the migration
   */
  void dispose(MigrationContext context);

  /**
   * This method is called at the end of each MigrationRunnable
   */
  void dispose(ExecutionManager manager, ResourceSet resourceSet, MigrationContext context);

  /**
   * This method allows to rename a prefix to another Should return "xmlns:prefix"
   */
  String getNSPrefix(String prefix, MigrationContext context);

  /**
   * This method allows to rename an nsURI to another
   */
  String getNSURI(String prefix, String nsUri, MigrationContext context);

  /**
   * This method allows to contribute to the packageRegistry if someone wants to
   */
  void contributePackageRegistry(org.eclipse.emf.ecore.EPackage.Registry packageRegistry, MigrationContext context);

  /**
   * This method will be called before browsing each elements of the given resource
   */
  void unaryStartMigrationExecute(ExecutionManager executionManager, Resource resource, MigrationContext context);

  /**
   * This method will be called for each elements (this allows to avoid each contribution to browse each resources, browsing is made only one time)
   */
  void unaryMigrationExecute(EObject currentElement, MigrationContext context);

  /**
   * This method will be called after browsing each elements of the given resource
   */
  void unaryEndMigrationExecute(ExecutionManager executionManager, Resource resource, MigrationContext context);

  /**
   * This method is called to let contributions know that the given resource is created
   */
  void newResource(Resource resource, MigrationContext context);

  /**
   * This method is used to rename a given proxy to another
   */
  String getHandleProxy(InternalEObject proxy, String uriLiteral, Resource resource, XMLHelper helper, MigrationContext context);

  /**
   * This method is used to change priority of a given EMF error
   */
  IStatus handleError(XMIException e, Resource resource, MigrationContext context);

  /**
   * This method allows to perform modification after SAX-parsing the peekObject
   */
  void endElement(EObject peekEObject, Attributes attribs, String uri, String localName, String name, Resource resource, MigrationContext context);

  /**
   * This methods allows to ignore setting the given value in the given feature
   * @return true whether the value of the given feature should be ignored
   */
  boolean ignoreSetFeatureValue(EObject peekObject, EStructuralFeature feature, Object value, int position, XMLResource resource,
      MigrationContext context);

  /**
   * This method allows to ignore the given unknown feature
   */
  boolean ignoreUnknownFeature(String prefix, String name, boolean isElement, EObject peekObject, String value, XMLResource resource,
      MigrationContext context);

  /**
   * This method is a notification to let contribution know that the given helper will be used for the given resource
   */
  void createdXMLHelper(XMLResource resource, XMLHelper result);

  void updateCreatedObject(EObject peekObject, EObject eObject, String typeQName, EStructuralFeature feature,
      XMLResource resource, XMLHelper helper, MigrationContext context);
  
  /**
   * This method allows to set the value from the received ids
   */
  void setValueFromId(EObject object, EReference eReference, String ids);

}
