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
package org.polarsys.capella.core.re.policies.merge;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.re.policies.merge.ReMergePolicy;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class CapellaMergePolicy extends ReMergePolicy {

  /**
   * @param context_p
   */
  public CapellaMergePolicy(IContext context_p) {
    super(context_p);
  }

  @Override
  public boolean copyFeature(EStructuralFeature feature_p) {
    if (ModellingcorePackage.Literals.MODEL_ELEMENT__ID.equals(feature_p)) {
      return false;
    }
    return super.copyFeature(feature_p);
  }

  @Override
  protected String getNewIdFor(EObject element_p) {
    if (element_p instanceof ModelElement) {
      return ((ModelElement) element_p).getId();
    }
    return org.polarsys.capella.common.lib.IdGenerator.createId();
  }

}
