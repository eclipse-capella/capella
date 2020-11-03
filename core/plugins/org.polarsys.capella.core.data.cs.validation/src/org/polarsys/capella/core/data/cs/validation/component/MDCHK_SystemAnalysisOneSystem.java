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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule checks that at System Analysis level there is only the System component and cannot contain any other
 * Component or Actor.
 */
public class MDCHK_SystemAnalysisOneSystem extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof SystemComponent) {
        SystemComponent component = (SystemComponent) eObj;
        boolean isRoot = BlockArchitectureExt.isRootComponent(component);
        if (isRoot) {
          List<Component> ownedComponents = ComponentExt.getSubDefinedComponents(component);
          List<Part> parts = component.getContainedParts();
          if (!ownedComponents.isEmpty() || !parts.isEmpty()) {
            return ctx.createFailureStatus();
          }
        }
      }
    }
    // No problem encountered
    return ctx.createSuccessStatus();
  }
}
