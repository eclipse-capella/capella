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

package org.polarsys.capella.common.libraries.provider;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.IModelIdentifier;

/**
 * A provider for libraries. 
 * A library is a IAbstractModel which can be referenced by another models
 */
public interface ILibraryProvider {

  /**
   * Returns all models available through the provider
   */
  @Deprecated
  public Collection<IModelIdentifier> getAvailableModels();

  /**
   * Returns all models available for the given {@link Session} through the provider
   */
  public Collection<IModelIdentifier> getAvailableModels(TransactionalEditingDomain domain);

  /**
   * Returns the related model if the object is linked to a model provided by this provider or null otherwise
   */
  public IModel getModel(EObject object);

  /**
   * Returns the root model for the given editing domain
   */
  public IModel getModel(TransactionalEditingDomain domain);

  /**
   * Returns a model for the given identifier and the specific editing domain
   */
  public IModel getModelDefinition(IModelIdentifier identifier, TransactionalEditingDomain domain);

  /**
   * Add a listener to be notified on provider state changed
   */
  public void addListener(ILibraryProviderListener listener);

  /**
   * Remove an already registered listener 
   */
  public void removeListener(ILibraryProviderListener listener);

  /**
   * @param semanticUri
   * @return
   */
  public IModelIdentifier getModelIdentifier(URI semanticUri);

}
