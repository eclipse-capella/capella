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
package org.polarsys.capella.core.data.epbs.validation.configurationItem;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.model.helpers.ConfigurationItemExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_ConfigurationItem_Allocation_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ConfigurationItem) {
        ConfigurationItem confItem = (ConfigurationItem) eObj;
        if(ConfigurationItemExt.isLeaf(confItem)){
          EList<PhysicalArtifactRealization> ownedPhysicalComponentRealisations = confItem.getOwnedPhysicalArtifactRealizations();
          if(null == ownedPhysicalComponentRealisations || ownedPhysicalComponentRealisations.isEmpty()){
        	  return ctx.createFailureStatus(new Object[] { confItem.getName() });  
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
