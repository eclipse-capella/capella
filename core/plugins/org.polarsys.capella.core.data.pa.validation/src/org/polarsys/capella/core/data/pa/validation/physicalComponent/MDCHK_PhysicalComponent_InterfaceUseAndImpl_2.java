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
package org.polarsys.capella.core.data.pa.validation.physicalComponent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that PC should not implement / use / provide / require  an interface belonging to a level other than the physical level
 */
public class MDCHK_PhysicalComponent_InterfaceUseAndImpl_2 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PhysicalComponent) {
 
        // continue if the preference transition of interface is active
        PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
        if (preferenceHelper.transitionInterfaceWhileComponentTransition()) {
          // physical component
          PhysicalComponent physicalComponent = (PhysicalComponent) eObj;
          
          // collect all the used, provided, required and implemented interfaces
          List<Interface> allInterface = new ArrayList<Interface>(1);
          allInterface.addAll(physicalComponent.getUsedInterfaces());
          allInterface.addAll(physicalComponent.getImplementedInterfaces());
          allInterface.addAll(physicalComponent.getProvidedInterfaces());
          allInterface.addAll(physicalComponent.getRequiredInterfaces());
          
          // check weather interface used/implemented/provided/required is not of the level other then physical
          for (Interface myInterface : allInterface) {
            // filter physicalArchitecture 
            if (! EcoreUtil2.isContainedBy(myInterface, PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
              return ctx_p.createFailureStatus(ctx_p,
                  new Object[] { CapellaElementExt.getValidationRuleMessagePrefix(physicalComponent) });
            }
          }
        } 
 
      }
    }
    return ctx_p.createSuccessStatus();
  }

}
