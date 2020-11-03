/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.validation.component;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule checks that only a Root Component exists at System/Logical/Physical level.
 */
public class MDCHK_OneRootComponent extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentArchitecture) {
        ComponentArchitecture componentArch = (ComponentArchitecture) eObj;
        Collection<Component> components = BlockArchitectureExt.getRootComponents(componentArch);
        int countRoot = components.size();
        if (countRoot > 1) {
          return ctx.createFailureStatus("Multiple root Components detected in " + componentArch.getName());
        }
        if (countRoot == 0) {
          return ctx.createFailureStatus("No root Component detected in " + componentArch.getName());
        }
      }
    }
    // No problem encountered
    return ctx.createSuccessStatus();
  }
}
