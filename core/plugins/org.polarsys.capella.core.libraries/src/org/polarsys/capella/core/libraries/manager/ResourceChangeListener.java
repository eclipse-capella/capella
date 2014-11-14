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
package org.polarsys.capella.core.libraries.manager;

import java.util.Hashtable;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.core.libraries.capellaModel.CapellaModel;

public class ResourceChangeListener implements IResourceChangeListener {

  protected LibraryManager libraryManager;
  protected Hashtable<IProject, List<IAbstractModel>> eclipseProject2CapellaModel = new Hashtable<IProject, List<IAbstractModel>>();
  protected Hashtable<IProject, Session> eclipseProject2Session = new Hashtable<IProject, Session>();
  protected Hashtable<IProject, String> eclipseProject2ProjectId = new Hashtable<IProject, String>();
  protected Hashtable<IProject, URI> eclipseProject2AirdUri = new Hashtable<IProject, URI>();

  public ResourceChangeListener(LibraryManager libraryManager_p) {
    libraryManager = libraryManager_p;
  }

  @Override
  public void resourceChanged(IResourceChangeEvent event) {

    IResourceDeltaVisitor visitor = new IResourceDeltaVisitor() {
      @Override
      public boolean visit(IResourceDelta delta) {
        IResource res = delta.getResource();
        if (res instanceof IWorkspaceRoot) {
          return true;
        } else if (res instanceof IProject) {
          if (delta.getKind() == IResourceDelta.REMOVED) {
            for (IAbstractModel m : libraryManager.cache.getLoadedModels()) {
              if ((m instanceof CapellaModel) && res.getName().equals(((CapellaModel) m).getEclipseProjectName())) {
                libraryManager.cache.deleteCapellaModel((CapellaModel) m);
              }
            }
          }
          return false;
        }
        return false;
      }
    };
    try {
      IResourceDelta delta = event.getDelta();
      if (delta != null) {
        delta.accept(visitor);
      }
    } catch (CoreException exception_p) {
    }
  }
}
