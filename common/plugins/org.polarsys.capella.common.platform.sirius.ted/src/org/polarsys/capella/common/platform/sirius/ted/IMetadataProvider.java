/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.platform.sirius.ted;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;

/**
 * Add capability to check consistency of loaded models on a Sirius Session 
 * created by org.polarsys.capella.common.platform.sirius.ted.SiriusSessionFactory
 */
public interface IMetadataProvider {
  
  /**
   * For a given model file (model, aird, afm), retrieve if the model is fully compatible with the current platform.
   * Checks current platform version and viewpoints.
   */
  public IStatus checkMetadata(IFile file);
  
  /**
   * For a given AFM file loaded in a resourceSet, retrieve if the model is fully compatible with the current platform.
   * Checks current platform version and viewpoints.
   */
  public IStatus checkMetadata(URI sessionResourceURI, ResourceSet set);
  
  /**
   * Register the given resource to the current session
   */
  public void registerMetadataResource(Resource resource, final Session session, final IProgressMonitor monitor);
  
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
   public Resource createMetadataResource(TransactionalEditingDomain domain, URI resourceURI,
      IProgressMonitor monitor);
  
}