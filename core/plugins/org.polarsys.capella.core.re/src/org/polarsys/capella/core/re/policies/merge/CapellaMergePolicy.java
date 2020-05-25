/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.policies.merge;

import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.re.policies.merge.ReMergePolicy;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class CapellaMergePolicy extends ReMergePolicy {

  public CapellaMergePolicy(IContext context) {
    super(context);
  }

  @Override
  public boolean copyFeature(EStructuralFeature feature, IFeaturedModelScope scope) {
    if (feature instanceof EAttribute && ((EAttribute) feature).isID()) {
      return false;
    }
    return super.copyFeature(feature, scope);
  }

  @Override
  protected String getNewIntrinsicID(EObject element, IFeaturedModelScope scope) {
    if (element instanceof ModelElement) {
      return ((ModelElement) element).getId();
    } else if (element != null && element.eClass() != null && element.eClass().getEIDAttribute() != null) {
      Object id = element.eGet(element.eClass().getEIDAttribute());
      if (id instanceof String) {
        return (String) id;
      }
    }
    return org.polarsys.capella.common.lib.IdGenerator.createId();
  }

}
