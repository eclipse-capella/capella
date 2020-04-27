/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * 
 */
public class MDCHKComponentInterfaceFunctionPortExchangeItemAllocationConsistency extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext context) {
    EObject eObj = context.getTarget();
    if (eObj instanceof Component) {
      Component component = (Component) eObj;
      Collection<IStatus> resultStatuses = validateComponent(context, component);
      if (!resultStatuses.isEmpty()) {
        // There are conflicts returns them as a multi-statuses status
        return ConstraintStatus.createMultiStatus(context, resultStatuses);
      }
    }

    return context.createSuccessStatus();
  }

  /**
   * @param context
   * @param component
   * @return
   */
  private Collection<IStatus> validateComponent(IValidationContext context, Component component) {
    Collection<IStatus> resultStatuses = new ArrayList<IStatus>();
    if (!component.getAllocatedFunctions().isEmpty()) {
      Set<AbstractExchangeItem> componentFunctionExchangeItems = ComponentExt.getAllocatedFunctionExchangeItems(component);
      Set<AbstractExchangeItem> exchangeItemsForInterfaces = getComponentInterfaceExchangeItems(component);
      String prefix = "\"" + component.getName() + "\" ( " + component.eClass().getName() + " ) ";
      
      boolean isOkForComponent = exchangeItemsForInterfaces.containsAll(componentFunctionExchangeItems);
      if (!isOkForComponent) {
        // (Component) has exchange items allocated to functions ports of its allocated functions not allocated to one of its Interfaces
        resultStatuses.add(context.createFailureStatus(context,
            prefix + "has exchange items allocated to functions ports of its allocated functions not allocated to one of its Interfaces"));
      }

      boolean isOkForRelatedInterface = componentFunctionExchangeItems.containsAll(exchangeItemsForInterfaces);
      if (!isOkForRelatedInterface) {
        resultStatuses.add(context.createFailureStatus(context,
            prefix + "has exchange items allocated by its Interfaces not allocated to one of functions ports of its allocated functions"));
      }

    }
    return resultStatuses;
  }

  /**
   * @param component
   * @param componentFunctionExchangeItems
   * @return all exchange items from the used/implemented/provided/required interface of the given component
   */
  private Set<AbstractExchangeItem> getComponentInterfaceExchangeItems(Component component) {
    Set<AbstractExchangeItem> exchangeItemsForCurrentInterfaces = new HashSet<AbstractExchangeItem>();
    Collection<Interface> relatedInterfaces = ComponentExt.getRelatedInterfaces(component);
    for (Interface interfasse : relatedInterfaces) {
      exchangeItemsForCurrentInterfaces.addAll(InterfaceExt.getAllExchangeItems(interfasse));
    }
    return exchangeItemsForCurrentInterfaces;
  }
}
