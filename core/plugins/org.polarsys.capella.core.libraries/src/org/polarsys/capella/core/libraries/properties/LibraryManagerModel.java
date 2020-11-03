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
package org.polarsys.capella.core.libraries.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.libraries.model.CapellaLibraryExt;
import org.polarsys.capella.core.libraries.utils.AbstractModelComparator;

/**
 */
public class LibraryManagerModel {

  public IModel.Edit rootModel;

  // for references management
  private HashMap<IModel, Collection<IModel>> model2currentReferencedLibraries;
  private List<IModel> allReferencedLibraries;
  private Collection<IModel> initialReferencedLibrariesByRootModel;
  private Collection<IModel> initialAllReferencedLibrariesByRootModel;
  private Collection<Collection<IModel>> cycles;
  private List<IModel> allLibraries;

  // for active state management
  private HashMap<IModel, Boolean> library2InitialActiveState;
  private HashMap<IModel, Boolean> library2CurrentActiveState;

  // for access policy management
  private HashMap<IModel, AccessPolicy> initialAccessPolicies;
  private HashMap<IModel, AccessPolicy> currentAccessPolicies;

  TransactionalEditingDomain domain;

  public LibraryManagerModel(TransactionalEditingDomain transactionalEditingDomain_p, IModel.Edit rootModel_) {
    rootModel = rootModel_;
    domain = transactionalEditingDomain_p;

    // for references management
    allLibraries = new ArrayList<IModel>();
    allLibraries.addAll(rootModel_.getAvailableReferences());
    for (IModel model : ILibraryManager.INSTANCE.getAllModels(transactionalEditingDomain_p)) {
      if (rootModel_.canReference(model) && !allLibraries.contains(model)) {
        allLibraries.add(model);
      }
    }

    // prevent basic-loop
    allLibraries.remove(rootModel);
    Collections.sort(allLibraries, new AbstractModelComparator());

    // compute referenced libraries
    initialReferencedLibrariesByRootModel = rootModel.getAvailableReferences();
    model2currentReferencedLibraries = new HashMap<IModel, Collection<IModel>>();
    model2currentReferencedLibraries.put(rootModel, new ArrayList<IModel>(initialReferencedLibrariesByRootModel));
    for (IModel library : allLibraries) {
      model2currentReferencedLibraries.put(library, library.getAvailableReferences());
    }
    cycles = new ArrayList<Collection<IModel>>();
    allReferencedLibraries = new ArrayList<IModel>();
    computeAllReferencedLibraries();
    initialAllReferencedLibrariesByRootModel = new ArrayList<IModel>(allReferencedLibraries);

    // for active state management
    library2InitialActiveState = new HashMap<IModel, Boolean>();
    for (IModel library : allReferencedLibraries) {
      library2InitialActiveState.put(library, new Boolean(rootModel.isActive(library)));
    }
    library2CurrentActiveState = new HashMap<IModel, Boolean>(library2InitialActiveState);

    // for access policies
    initialAccessPolicies = new HashMap<IModel, AccessPolicy>();
    for (IModel library : allReferencedLibraries) {
      initialAccessPolicies.put(library, rootModel_.getAccess(library));
    }
    currentAccessPolicies = new HashMap<IModel, AccessPolicy>(initialAccessPolicies);
  }

  /*------------------*/
  /*- STATE GETTERS -*/
  /*----------------*/

  public Boolean getInitialActiveState(IModel library_p) {
    return library2InitialActiveState.get(library_p);
  }

  public Boolean getActiveState(IModel library_p) {
    Boolean activeState = library2CurrentActiveState.get(library_p);
    if (activeState == null) {
      activeState = new Boolean(rootModel.isActive(library_p));
      library2CurrentActiveState.put(library_p, Boolean.TRUE);
    }
    return library2CurrentActiveState.get(library_p);
  }

  public Collection<IModel> getInitialReferencedLibrariesByRootModel() {
    return initialReferencedLibrariesByRootModel;
  }

  public Collection<IModel> getInitialAllReferencedLibrariesByRootModel() {
    return initialAllReferencedLibrariesByRootModel;
  }

  public List<IModel> getAllReferencedLibrariesByRootModel() {
    return allReferencedLibraries;
  }

  public Collection<IModel> getReferencedLibrariesByRootModel() {
    return model2currentReferencedLibraries.get(rootModel);
  }

  public Collection<Collection<IModel>> getCycles() {
    return cycles;
  }

  public List<IModel> getAllLibraries() {
    return allLibraries;
  }

  public IModel.Edit getRootModel() {
    return rootModel;
  }

  public AccessPolicy getAccessPolicy(IModel library_p) {
    AccessPolicy accessPolicy = currentAccessPolicies.get(library_p);
    if (accessPolicy == null) {
      accessPolicy = rootModel.getDefaultNewAccess(library_p);
      currentAccessPolicies.put(library_p, accessPolicy);
    }
    return accessPolicy;
  }

  public AccessPolicy getInitialAccessPolicy(IModel library_p) {
    return initialAccessPolicies.get(library_p);
  }

  public boolean isAccessPolicyModifiable(IModel library) {
    return getReferencedLibrariesByRootModel().contains(library);
  }

  /*--------------------*/
  /*- STATE MODIFIERS -*/
  /*------------------*/

  public void setAccessPolicy(IModel library_p, AccessPolicy accessPolicy_p) {
    currentAccessPolicies.put(library_p, accessPolicy_p);
  }

  public void setActiveState(IModel library_p, boolean activeState) {
    library2CurrentActiveState.put(library_p, new Boolean(activeState));
  }

  public void addReferencedLibrary(IModel library_p) {
    // update referenced libraries
    model2currentReferencedLibraries.get(rootModel).add(library_p);
    // update active states
    if (!library2CurrentActiveState.containsKey(library_p)) {
      library2CurrentActiveState.put(library_p, getActiveState(library_p));
    }
    // update allReferencedLibraries
    computeAllReferencedLibraries();
    // update access policy
    if (!currentAccessPolicies.containsKey(library_p)) {
      currentAccessPolicies.put(library_p, rootModel.getDefaultNewAccess(library_p));
    }
  }

  public void removeReferencedLibrary(IModel library_p) {
    // update referenced libraries
    model2currentReferencedLibraries.get(rootModel).remove(library_p);
    // update allReferencedLibraries
    computeAllReferencedLibraries();
    // update the access policy of the removed library if this library is referenced by transitivity
    if (allReferencedLibraries.contains(library_p)) {
      currentAccessPolicies.put(library_p, rootModel.getDefaultNewAccess(library_p));
    }
  }

  /*--------------------*/
  /*- PRIVATE METHODS -*/
  /*------------------*/

  private void computeAllReferencedLibraries() {
    cycles.clear();
    Collection<IModel> list = new ArrayList<IModel>();
    Collection<IModel> currentPath = new ArrayList<IModel>();
    computeAllReferencedLibraries_(rootModel, list, cycles, currentPath);
    list.remove(rootModel);
    allReferencedLibraries.clear();
    allReferencedLibraries.addAll(list);
    Collections.sort(allReferencedLibraries, new AbstractModelComparator());
  }

  private void computeAllReferencedLibraries_(IModel referencingModel, Collection<IModel> result, Collection<Collection<IModel>> cycles_,
      Collection<IModel> currentPath) {
    if (currentPath.contains(referencingModel)) {
      Collection<IModel> cycle = new ArrayList<IModel>(currentPath);
      cycle.add(referencingModel);
      cycles_.add(cycle);

    } else if (!result.contains(referencingModel)) {
      result.add(referencingModel);
      currentPath.add(referencingModel);
      if (model2currentReferencedLibraries.containsKey(referencingModel)) {
        for (IModel library : model2currentReferencedLibraries.get(referencingModel)) {
          computeAllReferencedLibraries_(library, result, cycles_, currentPath);
        }
      }
      currentPath.remove(referencingModel);
    }
  }

  /**
   * @return
   */
  public Collection<IModel> getUnsavedModels() {
    Collection<IModel> models = new ArrayList<IModel>();
    for (Session session : SessionManager.INSTANCE.getSessions()) {
      if (session.getStatus() == SessionStatus.DIRTY) {
        IModel sessionModel = ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());
        if ((sessionModel != null) && !(rootModel.equals(sessionModel))) {
          models.add(sessionModel);
        }
      }
    }
    return models;
  }
  
  public Collection<IModel> getUnresolvableReferencedLibraries() {
    return allReferencedLibraries.stream()
        .filter(lib -> CapellaLibraryExt.isUnresolvableIdentifier(lib.getIdentifier())).collect(Collectors.toList());
  }

  public boolean isUnsavedRootModel() {
    for (Session session : SessionManager.INSTANCE.getSessions()) {
      if (session.getStatus() == SessionStatus.DIRTY) {
        IModel sessionModel = ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());
        if ((sessionModel != null) && (rootModel.equals(sessionModel))) {
          return true;
        }
      }
    }
    return false;
  }

}
