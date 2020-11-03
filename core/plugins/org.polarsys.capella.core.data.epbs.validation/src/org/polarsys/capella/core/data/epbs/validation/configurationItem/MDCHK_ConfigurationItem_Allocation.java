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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that a Configuration Item should implement at least one Physical Artifacts.
 */
public class MDCHK_ConfigurationItem_Allocation extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ConfigurationItem) {
        ConfigurationItem ci = (ConfigurationItem) eObj;

        if (ci.getAllocatedPhysicalArtifacts().size() == 0) {
          return ctx.createFailureStatus(new Object[] { ci.getName() });
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
