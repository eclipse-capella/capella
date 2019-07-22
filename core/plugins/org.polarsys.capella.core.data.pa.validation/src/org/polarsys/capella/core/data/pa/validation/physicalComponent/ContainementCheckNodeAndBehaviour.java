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
package org.polarsys.capella.core.data.pa.validation.physicalComponent;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * NodePC can't be contained in BehaviourPC, and BehaviourPc can't be contained in NodePC
 */
public class ContainementCheckNodeAndBehaviour extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PhysicalComponent) {
        PhysicalComponent currentElement = (PhysicalComponent) eObj;
        if (PhysicalComponentExt.isNode(currentElement)) {
          List<Component> allAncestors = ComponentExt.getAllPartitionableElementAncestors(currentElement);
          for (Component component : allAncestors) {
            if (component instanceof PhysicalComponent) {
              PhysicalComponent comp = (PhysicalComponent) component;
              if (PhysicalComponentExt.isBehaviour(comp)) {
                return ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                                               + "of nature NODE can't be contained in Physical Component of nature BEHAVIOUR"); //$NON-NLS-1$
              }
            }
          }
        } else if (PhysicalComponentExt.isBehaviour(currentElement)) {
          List<Component> allAncestors = ComponentExt.getAllPartitionableElementAncestors(currentElement);
          for (Component component : allAncestors) {
            if (component instanceof PhysicalComponent) {
              PhysicalComponent comp = (PhysicalComponent) component;
              if (PhysicalComponentExt.isNode(comp)) {
                return ctx.createFailureStatus("PhysicalComponent of nature BEHAVIOUR can't be contained in Physical Component of nature NODE"); //$NON-NLS-1$
              }
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
