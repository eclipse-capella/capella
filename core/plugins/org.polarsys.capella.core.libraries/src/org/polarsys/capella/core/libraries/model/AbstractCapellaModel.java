/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.libraries.model;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.IModelIdentifier;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.common.libraries.model.AbstractUriModel;
import org.polarsys.capella.core.data.capellamodeller.Library;
import org.polarsys.capella.core.data.capellamodeller.Project;

public abstract class AbstractCapellaModel extends AbstractUriModel implements ICapellaModel {

  protected boolean _initializedAttributes = false;

  protected ModelIdentifier _identifier = null;

  protected boolean _isLibrary = true;

  protected TransactionalEditingDomain _domain;

  public TransactionalEditingDomain getEditingDomain() {
    return _domain;
  }

  public AbstractCapellaModel(ModelIdentifier identifier_p, TransactionalEditingDomain domain_p) {
    super(identifier_p.getUri());
    _domain = domain_p;
    _identifier = identifier_p;
  }

  @Override
  public IModelIdentifier getIdentifier() {
    return _identifier;
  }

  protected boolean isLibrary() {
    if (!_initializedAttributes) {
      initializeAttributes();
    }
    return _isLibrary;
  }

  protected boolean resolve() {
    return true;
  }

  protected Resource getResource(TransactionalEditingDomain domain, URI uri) {
    return getResource(domain, uri, resolve());
  }

  protected Resource getResource(TransactionalEditingDomain domain, URI uri, boolean resolve) {
    //this is fine, this will load the resource in the current editing domain
    try {
      return domain.getResourceSet().getResource(uri, resolve());
    } catch (Exception e) {
      //errors at loading can happen. we check the resource in other methods
    }
    return null;
  }

  private void initializeAttributes() {
    Resource resource = getResource(_domain, uriSemanticFile);
    Project project = CapellaLibraryExt.getProject(resource);
    _isLibrary = project instanceof Library;
    _initializedAttributes = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AccessPolicy getAccess(IModel library_p) {
    if (this.equals(library_p)) {
      return AccessPolicy.READ_AND_WRITE;
    }
    ModelInformation source = getModelInformation(this, false);
    ModelInformation target = getModelInformation(library_p, false);

    if ((source != null) && (target != null)) {
      //null can occurs when no modelInformation is created in the source or the target. in that case, we don't have to remove reference since there is none
      for (LibraryReference reference : source.getOwnedReferences()) {
        if ((reference.getLibrary() != null) && reference.getLibrary().equals(target)) {
          return reference.getAccessPolicy();
        }
      }
    }

    return getDefaultAccess(library_p);
  }

  protected ModelInformation getModelInformation(IModel model_p, boolean create_p) {
    if (model_p instanceof CapellaModel) {
      Resource target = getResource(_domain, ((CapellaModel) model_p).uriSemanticFile, create_p && resolve());
      ModelInformation targetObject = CapellaLibraryExt.getModelInformation(target, create_p);
      return targetObject;
    }
    return null;
  }

  public Project getProject(TransactionalEditingDomain domain_p) {
    Resource target = getResource(domain_p, uriSemanticFile);
    return CapellaLibraryExt.getProject(target);
  }

}
