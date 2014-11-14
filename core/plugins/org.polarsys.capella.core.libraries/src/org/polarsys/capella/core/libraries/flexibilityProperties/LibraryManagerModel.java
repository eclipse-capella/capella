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
package org.polarsys.capella.core.libraries.flexibilityProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.polarsys.capella.core.libraries.utils.AbstractModelComparator;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.common.libraries.ILibraryManager;

/**
 */
public class LibraryManagerModel {

  public IAbstractModel rootModel;

  // for references management
  private Hashtable<IAbstractModel, Collection<IAbstractLibrary>> model2currentReferencedLibraries;
  private List<IAbstractLibrary> allReferencedLibraries;
  private Collection<IAbstractLibrary> initialReferencedLibrariesByRootModel;
  private Collection<IAbstractLibrary> initialAllReferencedLibrariesByRootModel;
  private Collection<Collection<IAbstractModel>> cycles;
  private List<IAbstractLibrary> allLibraries;

  // for active state management
  private Hashtable<IAbstractLibrary, Boolean> library2InitialActiveState;
  private Hashtable<IAbstractLibrary, Boolean> library2CurrentActiveState;

  // for access policy management
  private Hashtable<IAbstractLibrary, AccessPolicy> initialAccessPolicies;
  private Hashtable<IAbstractLibrary, AccessPolicy> currentAccessPolicies;

  public LibraryManagerModel(IAbstractModel rootModel_) {
    rootModel = rootModel_;
    // for references management
    allLibraries = (List<IAbstractLibrary>) ILibraryManager.INSTANCE.getAllLibrariesInWorkspace();
    if (ILibraryManager.INSTANCE.isLibrary(rootModel)) {
      allLibraries.remove(rootModel);
    }
    initialReferencedLibrariesByRootModel = ILibraryManager.INSTANCE.getReferencedLibraries(rootModel, false);
    model2currentReferencedLibraries = new Hashtable<IAbstractModel, Collection<IAbstractLibrary>>();
    model2currentReferencedLibraries.put(rootModel, new ArrayList<IAbstractLibrary>(initialReferencedLibrariesByRootModel));
    for (IAbstractLibrary library : allLibraries) {
      model2currentReferencedLibraries.put(library, ILibraryManager.INSTANCE.getReferencedLibraries(library, false));
    }
    cycles = new ArrayList<Collection<IAbstractModel>>();
    allReferencedLibraries = new ArrayList<IAbstractLibrary>();
    computeAllReferencedLibraries();
    initialAllReferencedLibrariesByRootModel = new ArrayList<IAbstractLibrary>(allReferencedLibraries);
    // for active state management
    library2InitialActiveState = new Hashtable<IAbstractLibrary, Boolean>();
    for (IAbstractLibrary library : allReferencedLibraries) {
      library2InitialActiveState.put(library, new Boolean(ILibraryManager.INSTANCE.isActiveLibrary(library, rootModel)));
    }
    library2CurrentActiveState = new Hashtable<IAbstractLibrary, Boolean>(library2InitialActiveState);
    // for access policies
    initialAccessPolicies = new Hashtable<IAbstractLibrary, AccessPolicy>();
    for (IAbstractLibrary library : allReferencedLibraries) {
      initialAccessPolicies.put(library, ILibraryManager.INSTANCE.getAccessPolicy(rootModel_, library, true));
    }
    currentAccessPolicies = new Hashtable<IAbstractLibrary, AccessPolicy>(initialAccessPolicies);
  }

  /*------------------*/
  /*- STATE GETTERS -*/
  /*----------------*/

  public Boolean getInitialActiveState(IAbstractLibrary library_p) {
    return library2InitialActiveState.get(library_p);
  }

  public Boolean getActiveState(IAbstractLibrary library_p) {
    Boolean activeState = library2CurrentActiveState.get(library_p);
    if (activeState == null) {
      activeState = new Boolean(ILibraryManager.INSTANCE.isActiveLibrary(library_p, rootModel));
      library2CurrentActiveState.put(library_p, activeState);
    }
    return activeState;
  }

  public Collection<IAbstractLibrary> getInitialReferencedLibrariesByRootModel() {
    return initialReferencedLibrariesByRootModel;
  }

  public Collection<IAbstractLibrary> getInitialAllReferencedLibrariesByRootModel() {
    return initialAllReferencedLibrariesByRootModel;
  }

  public List<IAbstractLibrary> getAllReferencedLibrariesByRootModel() {
    return allReferencedLibraries;
  }

  public Collection<IAbstractLibrary> getReferencedLibrariesByRootModel() {
    return model2currentReferencedLibraries.get(rootModel);
  }

  public Collection<Collection<IAbstractModel>> getCycles() {
    return cycles;
  }

  public List<IAbstractLibrary> getAllLibraries() {
    return allLibraries;
  }

  public IAbstractModel getRootModel() {
    return rootModel;
  }

  public AccessPolicy getAccessPolicy(IAbstractLibrary library_p) {
    AccessPolicy accessPolicy = currentAccessPolicies.get(library_p);
    if (accessPolicy == null) {
      accessPolicy = ILibraryManager.INSTANCE.getDefaultAccessPolicy(library_p);
      currentAccessPolicies.put(library_p, accessPolicy);
    }
    return accessPolicy;
  }

  public AccessPolicy getInitialAccessPolicy(IAbstractLibrary library_p) {
    return initialAccessPolicies.get(library_p);
  }

  public boolean isAccessPolicyModifiable(IAbstractLibrary library) {
    return getReferencedLibrariesByRootModel().contains(library);
  }

  /*--------------------*/
  /*- STATE MODIFIERS -*/
  /*------------------*/

  public void setAccessPolicy(IAbstractLibrary library_p, AccessPolicy accessPolicy_p) {
    currentAccessPolicies.put(library_p, accessPolicy_p);
  }

  public void setActiveState(IAbstractLibrary library_p, boolean activeState) {
    library2CurrentActiveState.put(library_p, new Boolean(activeState));
  }

  public void addReferencedLibrary(IAbstractLibrary library_p) {
    model2currentReferencedLibraries.get(rootModel).add(library_p);
    // update active states
    if (!library2CurrentActiveState.containsKey(library_p)) {
      library2CurrentActiveState.put(library_p, getActiveState(library_p));
    }
    // update allReferencedLibraries
    computeAllReferencedLibraries();
    // update access policy
    if (!currentAccessPolicies.containsKey(library_p)) {
      currentAccessPolicies.put(library_p, ILibraryManager.INSTANCE.getDefaultAccessPolicy(library_p));
    }
  }

  public void removeReferencedLibrary(IAbstractLibrary library_p) {
    model2currentReferencedLibraries.get(rootModel).remove(library_p);
    // update allReferencedLibraries
    computeAllReferencedLibraries();
    // update the access policy of the removed library if this library is referenced by transitivity
    if (allReferencedLibraries.contains(library_p)) {
      currentAccessPolicies.put(library_p, ILibraryManager.INSTANCE.getAccessPolicy(rootModel, library_p, true));
    }
  }

  /*--------------------*/
  /*- PRIVATE METHODS -*/
  /*------------------*/

  @SuppressWarnings("unchecked")
  private void computeAllReferencedLibraries() {
    cycles.clear();
    Collection<IAbstractModel> list = new ArrayList<IAbstractModel>();
    Collection<IAbstractModel> currentPath = new ArrayList<IAbstractModel>();
    computeAllReferencedLibraries_(rootModel, list, cycles, currentPath);
    list.remove(rootModel);
    allReferencedLibraries.clear();
    allReferencedLibraries.addAll((Collection<? extends IAbstractLibrary>) list);
    Collections.sort(allReferencedLibraries, new AbstractModelComparator());
  }

  private void computeAllReferencedLibraries_(IAbstractModel referencingModel, Collection<IAbstractModel> result,
      Collection<Collection<IAbstractModel>> cycles_, Collection<IAbstractModel> currentPath) {
    if (currentPath.contains(referencingModel)) {
      Collection<IAbstractModel> cycle = new ArrayList<IAbstractModel>(currentPath);
      cycle.add(referencingModel);
      cycles_.add(cycle);

    } else if (!result.contains(referencingModel)) {
      result.add(referencingModel);
      currentPath.add(referencingModel);
      for (IAbstractLibrary library : model2currentReferencedLibraries.get(referencingModel)) {
        computeAllReferencedLibraries_(library, result, cycles_, currentPath);
      }
      currentPath.remove(referencingModel);
    }
  }

}
