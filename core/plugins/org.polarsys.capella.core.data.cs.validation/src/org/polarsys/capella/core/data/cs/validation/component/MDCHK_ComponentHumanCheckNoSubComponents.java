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
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule checks that a Component or Actor whit the 'is Human' flag set on true cannot have sub Components, sub
 * Actors, also no contained Parts or managing sub-packages.
 */
public class MDCHK_ComponentHumanCheckNoSubComponents extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      Component component = null;
      String message = null;
      if (eObj instanceof Entity) {
        component = (Component) eObj;
        if (component.isActor()) {
          message = "cannot be decomposed";
        }
      } else if (eObj instanceof Component) {
        component = (Component) eObj;
        if (component.isHuman()) {
          message = "is HUMAN and cannot be decomposed";
        }
      }

      if (message != null) {
        if (isDecomposed(component)) {
          return ctx.createFailureStatus(
              new Object[] { CapellaElementExt.getValidationRuleMessagePrefix(component), message });
        }
      }
    }
    // No problem encountered
    return ctx.createSuccessStatus();
  }

  private boolean isDecomposed(Component component) {
    List<Component> ownedComponents = ComponentExt.getSubDefinedComponents(component);
    List<Part> parts = component.getContainedParts();
    List<ComponentPkg> packages = ComponentExt.getContainedComponentPkgs(component);
    // sub Components, sub Actors, contained Parts or managing subpackages.
    if (!ownedComponents.isEmpty() || !parts.isEmpty() || !packages.isEmpty()) {
      return true;
    }
    return false;
  }
}
