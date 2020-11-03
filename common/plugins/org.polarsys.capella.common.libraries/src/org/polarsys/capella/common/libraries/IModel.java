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

package org.polarsys.capella.common.libraries;

import java.util.Collection;

/**
 * A model can have references toward another model/library
 */
public interface IModel {

  /**
   * Get a UNIQUE identifier for the given model.
   */
  public IModelIdentifier getIdentifier();

  /**
   * Returns model directly referenced by the current model (including references towards not available libraries)
   */
  public Collection<IModelIdentifier> getReferences();

  /**
   * Returns models referenced by the current model which are accessible via LibraryManager
   */
  public Collection<IModel> getAvailableReferences();

  /**
   * Returns whether a referenced library can be edited or not through the current model
   */
  public AccessPolicy getAccess(IModel library);

  /**
   * Returns whether the given referenced library is active or not. active means that a referenced library will be used by queries
   */
  public abstract boolean isActive(IModel library);

  /**
   * Returns the default active state for the given library. If no choice has been made yet for the given library, isActive will return this state.
   */
  public abstract boolean getDefaultActiveState(IModel library);

  /**
   * Returns the default access policy for the given library. If no choice has been made yet for the given library, getAccess will return this state.
   */
  public abstract AccessPolicy getDefaultAccess(IModel referencedLibrary);

  /**
   * Interface for editable model
   */
  public interface Edit extends IModel {

    /**
     * Returns whether the given model can be referenced by the current model (must returns true even if the given model is already referenced)
     */
    public boolean canReference(IModel referencedLibrary);

    /**
     * Add a reference to the given model
     */
    public void addReference(IModel referencedLibrary);

    /**
     * Remove a reference to the given model
     */
    public void removeReference(IModel referencedLibrary);

    /**
     * Set the access to the given model
     */
    public void setAccess(IModel library, AccessPolicy access);

    /**
     * Set the given library as active or not
     */
    public abstract void setActive(IModel library, boolean activeState);

    /**
     * Returns the default access that will be given on an unreferenced library that will be added as reference
     */
    public AccessPolicy getDefaultNewAccess(IModel referencedLibrary);
  }

}
