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
package org.polarsys.capella.common.libraries;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;

/**
 */
public interface IAbstractModel extends IModelScope {

  public URI getCapellaModellerUri();// refactor name : getUri

  public String getName();

  public boolean isLibrary();

  public boolean addReferenceTo(IAbstractLibrary referencedLibrary);

  public boolean removeReferenceTo(IAbstractLibrary referencedLibrary);

  public Collection<IAbstractLibrary> getReferencedLibraries(boolean onlyActiveOnes);

  public Collection<IAbstractLibrary> getAllReferencedLibraries(boolean onlyActiveOnes);

}
