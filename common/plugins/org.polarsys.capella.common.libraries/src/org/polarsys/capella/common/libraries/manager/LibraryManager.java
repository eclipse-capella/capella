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
package org.polarsys.capella.common.libraries.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.IModelIdentifier;
import org.polarsys.capella.common.libraries.provider.ILibraryProvider;
import org.polarsys.capella.common.libraries.provider.ILibraryProviderListener;

/**
 */
public class LibraryManager extends ILibraryManager implements ILibraryProviderListener {

  private static final String PROVIDER_EXTENSION = "org.polarsys.capella.common.libraries.providers"; //$NON-NLS-1$

  /**
   * List of all active providers
   */
  Collection<ILibraryProvider> _providers = null;

  /**
   * A stored version of all model identifiers. it is cleared each time a provider notifies us.
   */
  Collection<IModelIdentifier> _models = null;

  public Collection<ILibraryProvider> getProviders() {

    if (_providers == null) {
      _providers = new HashSet<ILibraryProvider>();

      for (IConfigurationElement element : Platform.getExtensionRegistry().getConfigurationElementsFor(PROVIDER_EXTENSION)) {
        try {
          ILibraryProvider provider = (ILibraryProvider) element.createExecutableExtension("class");
          if (provider != null) {
            _providers.add(provider);
          }
        } catch (Exception exception_p) {
          //nothing here, a provider can't be loaded
        }
      }

      for (ILibraryProvider provider : _providers) {
        provider.addListener(this);
      }
    }

    return _providers;
  }

  @Override
  public Collection<IModelIdentifier> getAvailableModels() {
    if (_models == null) {
      _models = new ArrayList<IModelIdentifier>();
      for (ILibraryProvider provider : getProviders()) {
        for (IModelIdentifier model : provider.getAvailableModels()) {
          if (!_models.contains(model)) {
            _models.add(model);
          }
        }
      }
    }
    return _models;
  }

  @Override
  public void onLibraryProviderChanged(ILibraryProviderEvent event_p) {
    //We should clear cache (maybe we could do less)
    _models = null;
  }

  @Override
  public Collection<IModel> getAllModels(TransactionalEditingDomain domain_p) {
    HashMap<IModelIdentifier, IModel> models = new HashMap<IModelIdentifier, IModel>();

    for (IModelIdentifier identifier : getAvailableModels()) {
      IModel model = getModel(domain_p, identifier);
      if (model != null) {
        models.put(identifier, model);
      }
    }

    return models.values();
  }

  @Override
  public IModel getModel(TransactionalEditingDomain domain_p, IModelIdentifier identifier_p) {
    if (identifier_p != null) {
      for (ILibraryProvider provider : getProviders()) {
        IModel model = provider.getModelDefinition(identifier_p, domain_p);
        if (model != null) {
          return model;
        }
      }
    }
    return null;
  }

  @Override
  public IModel getModel(EObject object_p) {
    for (ILibraryProvider provider : getProviders()) {
      IModel model = provider.getModel(object_p);
      if (model != null) {
        return model;
      }
    }
    return null;
  }

  @Override
  public IModel getModel(TransactionalEditingDomain domain_p) {
    for (ILibraryProvider provider : getProviders()) {
      IModel model = provider.getModel(domain_p);
      if (model != null) {
        return model;
      }
    }
    return null;
  }

  @Override
  public IModelIdentifier getModelIdentifier(URI semanticUri_p) {
    for (ILibraryProvider provider : getProviders()) {
      IModelIdentifier model = provider.getModelIdentifier(semanticUri_p);
      if (model != null) {
        return model;
      }
    }
    return null;
  }

}
