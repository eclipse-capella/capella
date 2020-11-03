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
package org.polarsys.capella.core.data.pa.validation.physicalComponent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that a Physical Component does not not implement / use / provided / required a logical interface
 * which is not used / implemented by at least one of the realized Logical Components
 */
public class MDCHK_PhysicalComponent_InterfaceUseAndImpl_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PhysicalComponent) {
        PhysicalComponent physicalComponent = (PhysicalComponent) eObj;
        // continue if the preference transition of interface is not active
        PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
        if (!preferenceHelper.transitionInterfaceWhileComponentTransition()) {
          // collect realized Logical Component
          List<LogicalComponent> realizedComponent = new ArrayList<LogicalComponent>(1);
          realizedComponent = physicalComponent.getRealizedLogicalComponents();

          // continue if pc realize at least one lc
          if (!realizedComponent.isEmpty()) {
            boolean failureStatus = false;
            // collect all the used / implemented / provided / required interfaces
            List<Interface> allInterface = new ArrayList<Interface>(1);
            allInterface.addAll(physicalComponent.getUsedInterfaces());
            allInterface.addAll(physicalComponent.getImplementedInterfaces());
            allInterface.addAll(physicalComponent.getProvidedInterfaces());
            allInterface.addAll(physicalComponent.getRequiredInterfaces());

            // check weather this interfaces are used and implemented by at least one of the realized logical component
            List<Interface> inValidInterfaces = new ArrayList<Interface>(1);
            for (Interface myInterface : allInterface) {
              BlockArchitecture rootBlockArchitecture = InterfacePkgExt.getRootBlockArchitecture(myInterface);
              // filter interface contained in Logical Architecture
              if (null != rootBlockArchitecture && rootBlockArchitecture instanceof LogicalArchitecture) {
                // collect all the user and implementor components
                List<Component> components = new ArrayList<Component>(1);

                components.addAll(InterfaceExt.getUserLogicalComponents(myInterface));
                components.addAll(InterfaceExt.getImplementerLogicalComponents(myInterface));
                components.addAll(InterfaceExt.getProviderComponent(myInterface));
                components.addAll(InterfaceExt.getRequireComponent(myInterface));

                if (!components.isEmpty()) {
                  boolean flag = false;
                  for (Component component : components) {
                    if (realizedComponent.contains(component)) {
                      // at least one realized Logical Component is using/Implementing the interface used by current
                      // Physical Component
                      flag = true;
                    }
                  }
                  if (!flag) {
                    failureStatus = true;
                    inValidInterfaces.add(myInterface);
                  }
                } else {
                  failureStatus = true;
                  inValidInterfaces.add(myInterface);
                }
              }
            }
            // collect all the invalid interfaces and return the failure status
            if (failureStatus) {
              String inValidInterfacesName = CapellaElementExt.getElementNamesSeperatedByComma(inValidInterfaces);
              return ctx_p.createFailureStatus(
                  new Object[] { CapellaElementExt.getValidationRuleMessagePrefix(physicalComponent),
                      inValidInterfacesName });
            }
          }

        }
      }
    }
    return ctx_p.createSuccessStatus();
  }

}
