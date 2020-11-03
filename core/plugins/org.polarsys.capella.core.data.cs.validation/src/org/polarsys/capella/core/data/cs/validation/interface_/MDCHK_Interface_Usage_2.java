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
package org.polarsys.capella.core.data.cs.validation.interface_;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that an interface is used by one of the sub Logical Component.
 */
public class MDCHK_Interface_Usage_2 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Interface) {
        Interface itf = (Interface) eObj;
        List<LogicalComponent> composites = new ArrayList<LogicalComponent>();
        List<LogicalComponent> leafs = new ArrayList<LogicalComponent>();

        // Retrieve LogicalComponent and LogicalComponentComposite who use from current Interface
        for (LogicalComponent lc : InterfaceExt.getUserLogicalComponents(itf)) {
          if (ComponentExt.isComposite(lc))
            composites.add(lc);
          else
            leafs.add(lc);
        }

        for (LogicalComponent currentLcc : composites) {
          boolean localFlag = false;
          List<Component> subLC = new ArrayList<Component>();
          subLC.addAll(ComponentExt.getAllSubUsedComponents(currentLcc));
          subLC.remove(currentLcc);

          // Check if current Interface is implemented by one of Sub-LogicalComponent (from current LogicalComposite) at least
          for (Component currentSubLC : subLC) {
            if (leafs.contains(currentSubLC))
              localFlag = true;
          }

          if (!localFlag) {
            return createFailureStatus(ctx, new Object[] { itf.getName() });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
