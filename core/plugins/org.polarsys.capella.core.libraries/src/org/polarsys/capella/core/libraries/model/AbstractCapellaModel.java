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
package org.polarsys.capella.core.libraries.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.IModelIdentifier;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.libraries.model.AbstractUriModel;
import org.polarsys.capella.core.data.capellamodeller.Library;
import org.polarsys.capella.core.data.capellamodeller.Project;

public abstract class AbstractCapellaModel extends AbstractUriModel implements ICapellaModel {

  protected boolean _initializedAttributes = false;

  protected ModelIdentifier _identifier = null;

  protected boolean _isLibrary = true;

  protected TransactionalEditingDomain _domain;

  public TransactionalEditingDomain getEditingDomain() {
    return _domain;
  }

  public AbstractCapellaModel(ModelIdentifier identifier_p, TransactionalEditingDomain domain_p) {
    super(identifier_p.getUri());
    _domain = domain_p;
    _identifier = identifier_p;
  }

  @Override
  public IModelIdentifier getIdentifier() {
    return _identifier;
  }

  public boolean isLibrary() {
    if (!_initializedAttributes) {
      initializeAttributes();
    }
    return _isLibrary;
  }

  protected boolean resolve() {
    return true;
  }

  protected Resource getResource(TransactionalEditingDomain domain, URI uri) {
    return getResource(domain, uri, resolve());
  }

  protected Resource getResource(TransactionalEditingDomain domain, URI uri, boolean resolve) {
    // this is fine, this will load the resource in the current editing domain
    try {
      return domain.getResourceSet().getResource(uri, resolve());
    } catch (Exception e) {
      // errors at loading can happen. we check the resource in other methods
    }
    return null;
  }

  private void initializeAttributes() {
    Resource resource = getResource(_domain, uriSemanticFile);
    Project project = CapellaLibraryExt.getProject(resource);
    _isLibrary = project instanceof Library;
    _initializedAttributes = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AccessPolicy getAccess(IModel library_p) {
    if (this.equals(library_p)) {
      return AccessPolicy.READ_AND_WRITE;
    }

    List<AccessInfo> accessInfos = new ArrayList<AccessInfo>();
    calculateAccessPolicy(this, library_p, accessInfos, 0);
    if (!accessInfos.isEmpty()) {
      Iterator<AccessInfo> itInfo = accessInfos.iterator();
      AccessInfo accessInfo = itInfo.next();
      int currentLevel = accessInfo.level;
      AccessPolicy result = accessInfo.accessPolicy;
      while (itInfo.hasNext()) {
        AccessInfo elt = itInfo.next();
        if (elt.level != currentLevel) {
          break;
        }
        result = resolveAccessPolicy(result, elt.accessPolicy);
      }
      return result;
    }

    return getDefaultAccess(library_p);
  }

  /**
   * Returns the heaviest access policy.
   */
  public AccessPolicy resolveAccessPolicy(AccessPolicy policy1, AccessPolicy policy2) {
    if ((policy1 == AccessPolicy.READ_AND_WRITE) || (policy2 == AccessPolicy.READ_AND_WRITE)) {
      return AccessPolicy.READ_AND_WRITE;
    }
    return AccessPolicy.READ_ONLY;
  }

  protected void calculateAccessPolicy(IModel model_p, IModel library_p, List<AccessInfo> policies, int level) {
    calculateAccessPolicy(model_p, library_p, policies, level, AccessPolicy.READ_AND_WRITE);
  }

  protected void calculateAccessPolicy(IModel model_p, IModel library_p, List<AccessInfo> policies, int level, AccessPolicy policy) {
    IModel model = model_p;
    IModel library = library_p;

    ModelInformation source = getModelInformation(model_p, false);
    ModelInformation target = getModelInformation(library_p, false);

    if ((source != null) && (target != null)) {
      for (LibraryReference reference : source.getOwnedReferences()) {
        if ((reference.getLibrary() != null) && reference.getLibrary().equals(target)) {
          policies.add(new AccessInfo(level, mergePolicies(policy, reference.getAccessPolicy())));
          return;
        }
      }
    }

    // Calculate access policy based on intermediate dependent libraries
    Collection<IModel> referencedLibraries = LibraryManagerExt.getReferences(model);
    for (IModel referencedLibrary : referencedLibraries) {
      if (CapellaLibraryExt.isUnresolvableIdentifier(referencedLibrary.getIdentifier())) {
        continue;
      }
      calculateAccessPolicy(referencedLibrary, library, policies, level + 1,
          mergePolicies(policy, model.getAccess(referencedLibrary)));
    }
  }
  
  protected AccessPolicy mergePolicies(AccessPolicy p1, AccessPolicy p2) {
    return (AccessPolicy.READ_ONLY.equals(p1) || AccessPolicy.READ_ONLY.equals(p2)) ? AccessPolicy.READ_ONLY : AccessPolicy.READ_AND_WRITE;
  }

  protected class AccessInfo {

    public int level;
    public AccessPolicy accessPolicy;

    public AccessInfo(int level_p, AccessPolicy accessPolicy_p) {
      level = level_p;
      accessPolicy = accessPolicy_p;
    }
  }

  protected ModelInformation getModelInformation(IModel model_p, boolean create_p) {
    if (model_p instanceof CapellaModel) {
      Resource target = getResource(_domain, ((CapellaModel)model_p).getUriSemanticFile(), create_p && resolve());
      ModelInformation targetObject = CapellaLibraryExt.getModelInformation(target, create_p);
      return targetObject;
    }
    return null;
  }

  public Project getProject(TransactionalEditingDomain domain_p) {
    Resource target = getResource(domain_p, uriSemanticFile);
    return CapellaLibraryExt.getProject(target);
  }

}
