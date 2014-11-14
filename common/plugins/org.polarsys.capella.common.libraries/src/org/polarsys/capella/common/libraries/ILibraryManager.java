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
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;

import org.eclipse.sirius.business.api.session.Session;

/**
 */
public abstract class ILibraryManager {

  public final static String LIBRARY_MANAGER_EXTENSION = "org.polarsys.capella.common.libraries.libraryManager"; //$NON-NLS-1$

  public static ILibraryManager INSTANCE = getInstance();

  // DEPENDENT OF ECLIPSE WORKSPACE

  private final static ILibraryManager getInstance() {
    if (INSTANCE == null) {
      List<Object> values = QueryInterpretor.executeQuery(LIBRARY_MANAGER_EXTENSION, new QueryContext());
      if ((values != null) && !values.isEmpty()) {
        Object manager = values.iterator().next();
        if (manager instanceof ILibraryManager) {
          INSTANCE = (ILibraryManager) manager;
        }
      }
    }
    return INSTANCE;
  }

  /** Returns the model corresponding to the given eclipse project. The project must be opened. Returns null if the eclipse project is not a capella project. */
  public abstract List<IAbstractModel> getAbstractModel(IProject eclipseProject);

  /** Returns the model corresponding to the eclipse project whose the name is given. The project must be opened. */
  public abstract List<IAbstractModel> getAbstractModel(String eclipseProjectName);

  /** Returns true of the given eclipse project is a library. Returns false if the given eclipse project is a project or is not opened. */
  public abstract boolean isLibraryProject(IProject eclipseProject);

  /** Returns all models in the current workspace. Only models corresponding to opened eclipse project are returned. */
  public abstract Collection<IAbstractModel> getAllModelsInWorkspace();

  /** Returns all libraries in the current workspace. Only libraries corresponding to opened eclipse project are returned. */
  public abstract Collection<IAbstractLibrary> getAllLibrariesInWorkspace();

  // NOT DEPENDENT OF ECLIPSE WORKSPACE

  /** Returns the model corresponding to the given session. Returns null if the session is closed. */
  public abstract IAbstractModel getAbstractModel(Session session);

  public abstract IAbstractModel getAbstractModel(EObject modelElement);

  public abstract boolean isLibrary(IAbstractModel model);

  public abstract boolean addReferenceToLibrary(IAbstractModel referencingModel, IAbstractLibrary referencedLibrary);

  public abstract boolean removeReferenceToLibrary(IAbstractModel referencingModel, IAbstractLibrary referencedLibrary);

  public abstract Collection<IAbstractLibrary> getReferencedLibraries(IAbstractModel referencingModel, boolean onlyActiveOnes);

  public abstract Collection<IAbstractLibrary> getAllReferencedLibraries(IAbstractModel referencingModel, boolean onlyActiveOnes);

  public abstract void promoteRecInLibrary();// TODO to be finished

  public abstract boolean isActiveLibrary(IAbstractLibrary library, IAbstractModel contextModel);

  public abstract void setLibraryActiveState(IAbstractLibrary library, IAbstractModel contextModel, boolean activeState);

  public abstract AccessPolicy getAccessPolicy(IAbstractModel referencingModel, IAbstractLibrary library, boolean transitivityAllowed);

  public abstract boolean setAccessPolicy(IAbstractModel referencingModel, IAbstractLibrary referencedLibrary, AccessPolicy newAccessPolicy);

  public abstract AccessPolicy getDefaultAccessPolicy(IAbstractLibrary referencedLibrary);

  public abstract boolean getDefaultActiveState(IAbstractLibrary library, IAbstractModel contextModel);

  /**
   * Returns true if the object can be modified according to the access policy. This policy is obtained by calling the getAccessPolicy method with the model
   * corresponding to the given object session as the referencing model parameter and the library corresponding to the object project as the library parameter.
   * If the last parameter is not a library or equals to the first parameter, returns true.
   */
  public abstract boolean doesEditionIsAuthorized(EObject object);

}
