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
package org.polarsys.capella.common.libraries.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.ef.domain.IEditingDomainListener;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.IModelIdentifier;
import org.polarsys.capella.common.libraries.provider.ILibraryProvider;
import org.polarsys.capella.common.libraries.provider.ILibraryProviderListener;

/**
 */
public class LibraryManager extends ILibraryManager implements ILibraryProviderListener, IEditingDomainListener {

  private static final String PROVIDER_EXTENSION = "org.polarsys.capella.common.libraries.providers"; //$NON-NLS-1$

  /**
   * List of all active providers
   */
  Collection<ILibraryProvider> providers = null;

  /**
   * A stored version of model per editing domain.
   */
  HashMap<TransactionalEditingDomain, IModel> modelsPerDomain = null;

  public Collection<ILibraryProvider> getProviders() {

    if (providers == null) {
      providers = new HashSet<ILibraryProvider>();

      for (IConfigurationElement element : Platform.getExtensionRegistry().getConfigurationElementsFor(PROVIDER_EXTENSION)) {
        try {
          ILibraryProvider provider = (ILibraryProvider) element.createExecutableExtension("class");
          if (provider != null) {
            providers.add(provider);
          }
        } catch (Exception exception) {
          // nothing here, a provider can't be loaded
        }
      }

      for (ILibraryProvider provider : providers) {
        provider.addListener(this);
      }
    }

    return providers;
  }

  @Override
  public Collection<IModelIdentifier> getAvailableModels() {
      return getAvailableModels(null);
  }

  @Override
  public Collection<IModelIdentifier> getAvailableModels(TransactionalEditingDomain domain) {
      Collection<IModelIdentifier> models = new ArrayList<IModelIdentifier>();
      for (ILibraryProvider provider : getProviders()) {
          for (IModelIdentifier model : provider.getAvailableModels(domain)) {
              if (!models.contains(model)) {
                  models.add(model);
              }
          }
      }
      return models;
    }

  @Override
  public void onLibraryProviderChanged(LibraryProviderEvent event) {
    // We should clear cache (maybe we could do less)
    modelsPerDomain = null;
  }

  @Override
  public Collection<IModel> getAllModels(TransactionalEditingDomain domain) {
    HashMap<IModelIdentifier, IModel> models = new HashMap<IModelIdentifier, IModel>();

    for (IModelIdentifier identifier : getAvailableModels(domain)) {
      IModel model = getModel(domain, identifier);
      if (model != null) {
        models.put(identifier, model);
      }
    }

    return models.values();
  }

  @Override
  public IModel getModel(TransactionalEditingDomain domain, IModelIdentifier identifier) {
    if (identifier != null) {
      for (ILibraryProvider provider : getProviders()) {
        IModel model = provider.getModelDefinition(identifier, domain);
        if (model != null) {
          return model;
        }
      }
    }
    return null;
  }

  @Override
  public IModel getModel(EObject object) {
    for (ILibraryProvider provider : getProviders()) {
      IModel model = provider.getModel(object);
      if (model != null) {
        return model;
      }
    }
    return null;
  }

  @Override
  public IModel getModel(TransactionalEditingDomain domain) {

    if (modelsPerDomain != null) {
      if (modelsPerDomain.containsKey(domain)) {
        return modelsPerDomain.get(domain);
      }
    }

    for (ILibraryProvider provider : getProviders()) {
      IModel model = provider.getModel(domain);
      if (model != null) {
        if (modelsPerDomain == null) {
          modelsPerDomain = new HashMap<TransactionalEditingDomain, IModel>();
        }
        modelsPerDomain.put(domain, model);
        return model;
      }
    }
    return null;
  }

  @Override
  public IModelIdentifier getModelIdentifier(URI semanticUri) {
    for (ILibraryProvider provider : getProviders()) {
      IModelIdentifier model = provider.getModelIdentifier(semanticUri);
      if (model != null) {
        return model;
      }
    }
    return null;
  }

  @Override
  public void createdEditingDomain(EditingDomain editingDomain) {
    // Nothing here
  }

  @Override
  public void disposedEditingDomain(EditingDomain editingDomain) {
    if (modelsPerDomain != null) {
      modelsPerDomain.remove(editingDomain);
    }
  }

}
