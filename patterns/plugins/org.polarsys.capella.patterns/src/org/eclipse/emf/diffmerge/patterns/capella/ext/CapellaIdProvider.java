/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.emf.diffmerge.patterns.capella.ext;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.patterns.core.api.ext.IIdProvider;
import org.eclipse.emf.diffmerge.patterns.core.util.ModelsUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.lib.IdGenerator;


/**
 * An implementation of an ID provider for Capella models.
 */
public class CapellaIdProvider implements IIdProvider {

  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IIdProvider#getNewIdFor(org.eclipse.emf.ecore.EObject)
   */
  public String getNewIdFor(EObject element_p) {
    return IdGenerator.createId();
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IIdProvider#getId(org.eclipse.emf.ecore.EObject, org.eclipse.emf.edit.domain.EditingDomain)
   */
  public String getId(EObject element_p, EditingDomain editingDomain_p) {
    String result = null;
    if (element_p != null)
      result = EcoreUtil.getID(element_p);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IIdProvider#getById(java.lang.String, java.util.Collection)
   */
  public EObject getById(String id_p, Collection<? extends Resource> scope_p) {
    if (id_p != null) {
      for (Resource currentResource : scope_p) {
        EObject result = currentResource.getEObject(id_p);
        if (result != null)
          return result;
      }
    }
    return null;
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IIdProvider#getByIdInContext(java.lang.String, java.lang.Object)
   */
  public EObject getByIdInContext(String id_p, Object context_p) {
    Collection<Resource> resources = ModelsUtil.getRelatedResources(context_p);
    return getById(id_p, resources);
  }

  /**
   * 
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IIdProvider#getByIdInResource(java.lang.String, org.eclipse.emf.ecore.EObject)
   */
  public EObject getByIdInResource(String id_p, EObject context_p) {
    EObject result = null;
    if (id_p != null && context_p != null) {
      Resource resource = context_p.eResource();
      if (resource != null) {
        Collection<Resource> resources = Collections.singleton(resource);
        result = getById(id_p, resources);
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IIdProvider#isApplicableTo(org.eclipse.emf.ecore.EObject)
   */
  public boolean isApplicableTo(EObject element_p) {
    return true;
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IIdProvider#isMainModel()
   */
  public boolean isMainModel() {
    return true;
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.core.api.ext.IIdProvider#requiresNewIntrinsicID(org.eclipse.emf.ecore.EObject, java.lang.Object)
   */
  public boolean requiresNewIntrinsicID(EObject element_p, Object scope_p) {
    return true;
  }

}
