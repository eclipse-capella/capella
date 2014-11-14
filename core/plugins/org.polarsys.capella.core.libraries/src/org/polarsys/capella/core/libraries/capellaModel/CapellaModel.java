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
package org.polarsys.capella.core.libraries.capellaModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.impl.scopes.RootedModelScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.emf.ecore.InternalEObject;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.manager.LibraryManager;
import org.polarsys.capella.core.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.common.libraries.ILibraryManager;

public class CapellaModel extends RootedModelScope implements IAbstractModel {

  protected URI airdUri;
  protected String projectId;
  protected Project project;
  protected Session session;

  public CapellaModel(Project project_p) {
    super(project_p.eResource().getContents());
    project = project_p;
    projectId = project.getId();
  }

  /**
   * Returns the name of the last known location of this model's eclipse project.
   */
  public String getEclipseProjectName() {
    String result = null;
    IProject eclipseProject = EcoreUtil2.getProject(project);
    if (eclipseProject == null) {
      URI proxyURI = ((InternalEObject) project).eProxyURI();
      if (proxyURI != null) {
        if (proxyURI.isPlatformResource()) {
          // platform:/resource/project/project.melodymodeller
          result = URI.decode(proxyURI.segment(1));
        } else if ("cdo".equals(proxyURI.scheme())) {
          // cdo://repoMelody/project/project.melodymodeller (note the // vs / above)
          result = URI.decode(proxyURI.segment(0));
        }
      }
    } else {
      result = eclipseProject.getName();
    }
    return result;
  }
  
  @Override
  public String toString() {
    String s = "";
    if (project == null) {
      s += "project is null ";
    } else {
      s += project.getName();
    }
    if (session == null) {
      s += "(session = null";
    } else {
      s += "(sessionUri = " + session.getSessionResource().getURI().toString();
    }
    if (project != null) {
      s += ", project_hash = " + project.hashCode();
    }
    if (session != null) {
      s += ", session_hash = " + session.hashCode();
    }
    return s + ")";
  }

  public Project getProject() {
    return project;
  }

  @Override
  public URI getCapellaModellerUri() {
    return project.eResource().getURI();
  }

  @Override
  public String getName() {
    return project.getName();
  }

  public void setSession(Session session_p) {
    session = session_p;
    LibraryManagerExt.updateAirdReferences(this, ((LibraryManager) ILibraryManager.INSTANCE).cache);
  }

  public void setProject(Project project_p) {
    project = project_p;
  }

  public Session getSession() {
    return session;
  }

  public Session getExistingSession() {
    return session;
  }

  /**
   * Return the resource of this model whose it is actually the owner. These resources are : - the melodymodeller resource of this model, - its owned fragments
   */
  public List<Resource> getOwnedResources() {
    // we get semantic resources that are contained by the current project
    List<Resource> resources = new ArrayList<Resource>();
    for (Resource resource : session.getSemanticResources()) {
      List<EObject> objects = resource.getContents();
      if (objects.size() > 0) {
        EObject obj = EcoreUtil.getRootContainer(objects.get(0));
        if (obj == project) {
          resources.add(resource);
        }
      }
    }
    return resources;
  }

  @Override
  public boolean equals(Object object_p) {
    if (object_p instanceof CapellaModel) {
      return projectId.equals(((CapellaModel) object_p).projectId);
    }
    return false;
  }

  /*---------------------*/
  /*- SHORTCUT METHODS -*/
  /*-------------------*/

  @Override
  public boolean isLibrary() {
    return ILibraryManager.INSTANCE.isLibrary(this);
  }

  @Override
  public boolean addReferenceTo(IAbstractLibrary referencedLibrary_p) {
    return ILibraryManager.INSTANCE.addReferenceToLibrary(this, referencedLibrary_p);
  }

  @Override
  public boolean removeReferenceTo(IAbstractLibrary referencedLibrary_p) {
    return ILibraryManager.INSTANCE.removeReferenceToLibrary(this, referencedLibrary_p);
  }

  @Override
  public Collection<IAbstractLibrary> getReferencedLibraries(boolean onlyActiveOnes_p) {
    return ILibraryManager.INSTANCE.getReferencedLibraries(this, onlyActiveOnes_p);
  }

  @Override
  public Collection<IAbstractLibrary> getAllReferencedLibraries(boolean onlyActiveOnes_p) {
    return ILibraryManager.INSTANCE.getAllReferencedLibraries(this, onlyActiveOnes_p);
  }
}
