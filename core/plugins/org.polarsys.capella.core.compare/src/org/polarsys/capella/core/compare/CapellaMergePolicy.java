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
package org.polarsys.capella.core.compare;

import java.util.Set;

import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Property;


/**
 * A merge policy for Capella models.
 */
public class CapellaMergePolicy extends DefaultMergePolicy {
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy#getAdditionGroup(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  @Override
  public Set<EObject> getAdditionGroup(EObject element_p,
      IFeaturedModelScope scope_p) {
    Set<EObject> result = super.getAdditionGroup(element_p, scope_p);
    if (element_p instanceof Association) {
      Association association = (Association)element_p;
      result.addAll((association.getOwnedMembers()));
      result.addAll((association.getNavigableMembers()));
    } else if (element_p instanceof Property) {
      Property property = (Property)element_p;
      Association association = property.getAssociation();
      if (association != null)
        result.add(association);
    }
    return result;
  }
  
  /**
   * @see org.polarsys.capella.common.compare.policies.IMergePolicy#getNewIdFor(EObject)
   */
  @Override
  public String getNewIdFor(EObject element_p) {
    String result;
    if (element_p.eClass().getEIDAttribute() != null)
      result = IdGenerator.createId();
    else
      result = super.getNewIdFor(element_p);
    return result;
  }
  
}
