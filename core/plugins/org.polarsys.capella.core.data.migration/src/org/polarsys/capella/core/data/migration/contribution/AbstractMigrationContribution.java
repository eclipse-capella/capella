/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
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
public class AbstractMigrationContribution implements IMigrationContribution {

  @Override
  public Object getValue(EObject peekObject, EStructuralFeature feature, Object value, int position, Resource resource, MigrationContext context) {
    return null;
  }

  @Override
  public EStructuralFeature getFeature(EObject peekObject, EStructuralFeature feature, Resource resource, MigrationContext context) {
    return null;
  }

  @Override
  public String getFeatureName(String prefix, String name, boolean isElement, EObject peekObject, String value, Resource resource, MigrationContext context) {
    return null;
  }

  @Override
  public String getQName(EObject peekObject, String typeQName, EStructuralFeature feature, Resource resource, XMLHelper helper, MigrationContext context) {
    return null;
  }

  @Override
  public EFactory getEFactory(String prefix, Resource resource, MigrationContext context) {
    return null;
  }

  @Override
  public void updateElement(EObject peekObject, String typeName, EObject result, EStructuralFeature feature, Resource resource, MigrationContext context) {
    return;
  }

  @Override
  public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet, MigrationContext context) {
    return;
  }

  @Override
  public void postMigrationExecuteCommands(ExecutionManager executionManager, ResourceSet resourceSet, MigrationContext context) {
    return;
  }

  @Override
  public void dispose(MigrationContext context) {
    return;
  }

  public void createdXMLHelper(XMLResource resource, XMLHelper result) {

  }

  @Override
  public String getNSPrefix(String prefix, MigrationContext context) {
    return null;
  }

  @Override
  public String getNSURI(String prefix, String nsUri, MigrationContext context) {
    return null;
  }

  @Override
  public void contributePackageRegistry(Registry packageRegistry, MigrationContext context) {

  }

  public void dispose(ExecutionManager manager, ResourceSet resourceSet, MigrationContext context) {

  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.data.migration.contribution.IMigrationContribution#preMigrationExecute(org.eclipse.core .resources.IFile)
   */
  @Override
  public IStatus preMigrationExecute(IResource fileToMigrate, MigrationContext context, boolean checkVersion) {
    return Status.OK_STATUS;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.data.migration.contribution.IMigrationContribution#preSaveResource(org.polarsys.capella .common.ef.ExecutionManager,
   * org.eclipse.emf.ecore.resource.Resource, org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  public void preSaveResource(ExecutionManager executionManager, Resource resource, MigrationContext context) {

  }
  
  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.data.migration.contribution.IMigrationContribution#unaryPostMigrationExecute(org.eclipse .emf.common.notify.Notifier,
   * org.polarsys.capella.core.data.migration.context.MigrationContext)
   */
  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {

  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.data.migration.contribution.IMigrationContribution#unaryEndPostMigrationExecute(org.polarsys
   * .capella.common.ef.ExecutionManager, org.eclipse.emf.ecore.resource.Resource, org.polarsys.capella.core.data.migration.context.MigrationContext)
   */
  @Override
  public void unaryEndMigrationExecute(ExecutionManager executionManager, Resource resource, MigrationContext context) {

  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.data.migration.contribution.IMigrationContribution#newResource(org.eclipse.emf.ecore. resource.Resource,
   * org.polarsys.capella.core.data.migration.context.MigrationContext)
   */
  @Override
  public void newResource(Resource resource, MigrationContext context) {

  }

  public String getHandleProxy(InternalEObject proxy, String uriLiteral, Resource resource, XMLHelper helper, MigrationContext context) {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.data.migration.contribution.IMigrationContribution#handleError(org.eclipse.emf.ecore. xmi.XMIException,
   * org.eclipse.emf.ecore.resource.Resource, org.polarsys.capella.core.data.migration.context.MigrationContext)
   */
  @Override
  public IStatus handleError(XMIException e, Resource resource, MigrationContext context) {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.data.migration.contribution.IMigrationContribution#endElement(org.eclipse.emf.ecore.EObject , org.xml.sax.Attributes,
   * java.lang.String, java.lang.String, java.lang.String, org.eclipse.emf.ecore.resource.Resource,
   * org.polarsys.capella.core.data.migration.context.MigrationContext)
   */
  @Override
  public void endElement(EObject peekEObject, Attributes attribs, String uri, String localName, String name, Resource resource, MigrationContext context) {

  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.data.migration.contribution.IMigrationContribution#ignoreSetFeatureValue(org.eclipse. emf.ecore.EObject,
   * org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object, int, org.eclipse.emf.ecore.xmi.XMLResource,
   * org.polarsys.capella.core.data.migration.context.MigrationContext)
   */
  @Override
  public boolean ignoreSetFeatureValue(EObject peekObject, EStructuralFeature feature, Object value, int position, XMLResource resource,
      MigrationContext context) {
    return false;
  }

  public boolean ignoreUnknownFeature(String prefix, String name, boolean isElement, EObject peekObject, String value, XMLResource resource,
      MigrationContext context) {
    return false;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.polarsys.capella.core.data.migration.contribution.IMigrationContribution#unaryStartPostMigrationExecute(org.polarsys.capella.common.ef.ExecutionManager
   * , org.eclipse.emf.ecore.resource.Resource, org.polarsys.capella.core.data.migration.context.MigrationContext)
   */
  @Override
  public void unaryStartMigrationExecute(ExecutionManager executionManager, Resource resource, MigrationContext context) {

  }

  @Override
  public EStructuralFeature getFeature(EObject object, String prefix, String name, boolean isElement) {
    return null;
  }

  @Override
  public void updateCreatedObject(EObject peekObject, EObject eObject, String typeQName, EStructuralFeature feature,
      XMLResource resource, XMLHelper helper, MigrationContext context) {
    
  }
}
