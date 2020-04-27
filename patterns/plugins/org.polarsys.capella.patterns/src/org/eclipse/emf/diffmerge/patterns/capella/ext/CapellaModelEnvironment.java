/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.emf.diffmerge.patterns.capella.ext;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.patterns.core.CorePatternsPlugin;
import org.eclipse.emf.diffmerge.patterns.core.api.IIdentifiedElement;
import org.eclipse.emf.diffmerge.patterns.core.api.IPatternInstanceMarker;
import org.eclipse.emf.diffmerge.patterns.core.api.ext.IModelEnvironment;
import org.eclipse.emf.diffmerge.patterns.core.api.ext.IPatternSupport;
import org.eclipse.emf.diffmerge.patterns.diagrams.sirius.extensions.AbstractSiriusModelEnvironment;
import org.eclipse.emf.diffmerge.patterns.repositories.catalogs.PatternCatalogResourceHelper;
import org.eclipse.emf.diffmerge.patterns.support.gen.commonpatternsupport.CommonPatternInstance;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;
import org.polarsys.kitalpha.patterns.emde.EmdePatternSupport;
import org.polarsys.kitalpha.patterns.emde.gen.emdepatternsupport.EmdePatternInstanceSet;
import org.polarsys.kitalpha.patterns.emde.gen.emdepatternsupport.EmdepatternsupportFactory;


/**
 * An implementation of a model environment for the Capella model infrastructure.
 */
public class CapellaModelEnvironment extends AbstractSiriusModelEnvironment {
  
  /**
   * Constructor
   */
  public CapellaModelEnvironment() {
    super();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IModelEnvironment#getEditingDomain(org.eclipse.emf.ecore.EObject)
   */
  public TransactionalEditingDomain getEditingDomain(EObject context_p) {
    TransactionalEditingDomain result = null;
    if (context_p instanceof IIdentifiedElement)
      result = getCommonCatalogEditingDomain();
    else if (context_p != null)
      result = TransactionHelper.getEditingDomain(context_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IModelEnvironment#getEditingDomain(org.eclipse.core.resources.IFile)
   */
  public EditingDomain getEditingDomain(IFile file_p) {
    EditingDomain domain = null;
    if(file_p != null){
      if(file_p.getFileExtension().equals(PatternCatalogResourceHelper.PATTERN_CATALOG_FILE_EXTENSION))
        return getCommonCatalogEditingDomain();
      domain = getExistingEditingDomain(file_p);
    }
    return domain;
  }
  
  /**
   * Try and find an existing editing domain in which the given IFile is loaded
   * @param file_p a potentially null IFile
   * @return a potentially null editing domain
   */
  private EditingDomain getExistingEditingDomain(IFile file_p) {
    EditingDomain result = null;
    if(file_p != null){
      Collection<Session> sessions = SessionManager.INSTANCE.getSessions();
      for(Session session : sessions){
        EditingDomain candidate = session.getTransactionalEditingDomain();
        Resource res = candidate.getResourceSet().getResource(URI.createURI(file_p.getFullPath().toString()), true);
        if(res != null){
          result = candidate;
          break;
        }
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IModelEnvironment#getModelResourceFromInstanceSet(org.eclipse.emf.diffmerge.patterns.core.api.IPatternInstanceMarker)
   */
  public Resource getModelResourceFromInstanceSet(IPatternInstanceMarker context_p) {
    if(context_p instanceof EObject){
      EObject set = (EObject)context_p;
      URI modelUri = set.eResource().getURI().trimFileExtension().appendFileExtension(
          CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION);
      IModelEnvironment env = CorePatternsPlugin.getDefault().getModelEnvironment();
      EditingDomain ed = TransactionHelper.getEditingDomain(set);
      if(env != null && ed != null){
        ResourceSet modelRSet = ed.getResourceSet();
        if(modelRSet != null){
          for(Resource res : modelRSet.getResources()){
            if(res.getURI()!= null && res.getURI().toPlatformString(true)!= null 
                && res.getURI().toString().equals(modelUri.toString())){
              return res;
            }
          }
        }
      }    
    }
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IModelEnvironment#getOrCreateInstanceSetForModelResource(org.eclipse.emf.ecore.resource.Resource)
   */
  public IPatternInstanceMarker getOrCreateInstanceSetForModelResource(
      Resource resource_p) {
    EmdePatternInstanceSet result = null ;
    if(resource_p != null){
      result = EmdepatternsupportFactory.eINSTANCE.createEmdePatternInstanceSet();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IModelEnvironment#isAppropriatePatternSupport(org.eclipse.emf.diffmerge.patterns.core.api.ext.IPatternSupport)
   */
  public boolean isAppropriatePatternSupport(IPatternSupport o) {
    return o instanceof EmdePatternSupport;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IModelEnvironment#isModelResource(org.eclipse.emf.ecore.resource.Resource)
   */
  public boolean isModelResource(Resource resource_p) {
    return CapellaResourceHelper.isCapellaResource(resource_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IModelEnvironment#isModelElement(java.lang.Object)
   */
  public boolean isModelElement(Object object_p) {
    return object_p instanceof CommonPatternInstance ||
        object_p instanceof ExtensibleElement;
  }
  
}
