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
package org.polarsys.capella.core.data.cs.validation.part;

import java.util.ArrayList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Checks that context packages contain at least one component (the root component) of the correct level.
 */
public class ComponentPkg_RootComponentPart extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentPkg) {
        ComponentPkg pkg = (ComponentPkg) eObj;
        BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(eObj);
        if (!(architecture instanceof OperationalAnalysis)
            && (BlockArchitectureExt.getComponentPkg(architecture, false) == eObj)) {
          ArrayList<EObject> parts = new ArrayList<EObject>();
          if (architecture.getSystem() != null) {
            parts.addAll(((ComponentPkg) eObj).getOwnedParts());
            parts.retainAll(architecture.getSystem().getRepresentingParts());
            if (parts.isEmpty()) {
              return ctx.createFailureStatus(new Object[] { pkg.getName(), pkg.eClass().getName() });
            }
          } else {
            return ctx.createFailureStatus(new Object[] { pkg.getName(), pkg.eClass().getName() });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
