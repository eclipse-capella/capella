/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.updateconnections.ui;

import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

/**
 * The diff/merge policy used to merge connections between pairs of REC/RPL elements.
 */
public class UpdateConnectionsMergePolicy extends DefaultMergePolicy {
  
  /**
   * ID must not be copied, or the intrinsic ID to EObject cache in the containing resource will be corrupted. The new
   * ID is then set identically to all new model elements during resource attachHelper.
   */
  @Override
  public boolean copyAttribute(Object attribute, ITreeDataScope<EObject> scope) {
    if (attribute instanceof EAttribute && ((EAttribute) attribute).isID()) {
      return false;
    }
    return super.copyAttribute(attribute, scope);
  }
}