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

package org.polarsys.capella.common.libraries;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.libraries.manager.LibraryManager;

/**
 */
public abstract class ILibraryManager {

  public static ILibraryManager INSTANCE = new LibraryManager();

  public static TransactionalEditingDomain DEFAULT_EDITING_DOMAIN = null;

  /**
   * Returns all available models
   */
  @Deprecated
  public abstract Collection<IModelIdentifier> getAvailableModels();

  /**
   * Returns all available models for the given {@link TransactionalEditingDomain}
   */
  public abstract Collection<IModelIdentifier> getAvailableModels(TransactionalEditingDomain domain);

  /** 
   * Returns all models related to the given editing domain.
   * (instanciate all available models via library providers)
   */
  public abstract Collection<IModel> getAllModels(TransactionalEditingDomain domain);

  /**
   * Returns the model corresponding to the given identifier.
   */
  public abstract IModel getModel(TransactionalEditingDomain domain, IModelIdentifier identifier);

  /** 
   * Returns the main model for the editing domain 
   */
  public abstract IModel getModel(TransactionalEditingDomain domain);

  /** 
   * Returns the model corresponding to the given object. 
   * If the object is stored outside a model or a library, returns null
   */
  public abstract IModel getModel(EObject object);

  /**
   * Returns an identifier for the given model uri
   */
  public abstract IModelIdentifier getModelIdentifier(URI modelUri);
}
