/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.libraries.provider.AbstractLibraryProvider;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.model.ModelIdentifier;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;

public abstract class AbstractCapellaProvider extends AbstractLibraryProvider {

  protected Resource getResource(TransactionalEditingDomain domain, URI uri, boolean resolve) {
    // we load the resource in the given ted.
    try {
      return domain.getResourceSet().getResource(uri, resolve);
    } catch (Exception e) {
      // if there is error at loading time, we will have a resource with errors. maybe we should remove it (tests on errors are made in other methods)
    }
    return null;
  }

  protected ModelIdentifier createModelIdentifier(Resource resource) {
    return CapellaModel.createModelIdentifier(resource);
  }

  protected IModel createModel(ModelIdentifier identifier, TransactionalEditingDomain domain) {
    return CapellaModel.createModel(identifier, domain);
  }

  protected boolean isHandled(URI uri) {
    return false;
  }

  protected boolean isLocalProject(TransactionalEditingDomain domain) {
      for (Resource resource : domain.getResourceSet().getResources()) {
          if (resource.getURI().scheme().equals(URIQuery.CDO_URI_SCHEME)) {
              return false;
          }
      }
      return true;
  }

  @Override
  public IModel getModel(TransactionalEditingDomain domain) {

    Collection<Resource> toCheck = new ArrayList<Resource>();
    for (Resource resource : domain.getResourceSet().getResources()) {
      if (isHandled(resource.getURI()) && !CapellaResourceHelper.isCapellaFragment(resource.getURI())) {
        if (resource.getErrors().isEmpty()) {
          toCheck.add(resource);
        }
      }
    }

    Collection<IModel> models = new ArrayList<IModel>();
    for (Resource resource : toCheck) {
      IModel model = createModel(createModelIdentifier(resource), domain);
      if ((model != null) && !(models.contains(model))) {
        models.add(model);
      }
    }

    IModel result = null;
    if (models.size() > 1) {
      // we find a model without any depending model in the current domain
      // we remove all models that another depends on, they are not the main model.
      for (IModel model : new ArrayList<IModel>(models)) {
        for (IModel reference : LibraryManagerExt.getAllReferences(model)) {
          models.remove(reference);
        }
      }
    }
    if (!models.isEmpty()) {
      result = models.iterator().next();
      // if there is many models (not fragments) without dependencies between them, it's a weird session
    }
    return result;
  }

  @Override
  public IModel getModel(EObject object) {
    EObject semanticElement = object;
    if (semanticElement == null) {
      return null;
    }

    // don't forget to adapt to capella
    if (!(CapellaResourceHelper.isSemanticElement(semanticElement))) {
      semanticElement = CapellaAdapterHelper.resolveSemanticObject(semanticElement);
    }

    // If we are in a Capella resource, the resource is loaded in the resourceSet, so we can return the model
    TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(object);
    if (semanticElement != null) {
      Resource resource = semanticElement.eResource();
      if ((resource != null) && isHandled(resource.getURI())) {
        if (CapellaResourceHelper.isCapellaResource(resource)) {
          return createModel(createModelIdentifier(resource), domain);
        }

        // otherwise, we should ask to the library manager
        ModelIdentifier identifier = createModelIdentifier(resource);
        return ILibraryManager.INSTANCE.getModel(domain, identifier);
      }
    }

    return null;
  }
}
