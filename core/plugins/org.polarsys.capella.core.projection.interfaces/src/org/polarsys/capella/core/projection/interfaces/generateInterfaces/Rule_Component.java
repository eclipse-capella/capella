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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class Rule_Component extends CommonRule {

  /**
   * @param eclass_p
   */
  public Rule_Component() {
    super(CsPackage.Literals.COMPONENT, CsPackage.Literals.INTERFACE);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) {
    // Nothing to do
  }

  @Override
  protected void doAddContainer(EObject element_p, List<EObject> result_p) {
    // Nothing to do
  }

  @Override
  protected void doGoDeep(EObject element_p, List<EObject> result_p) {

    Component component = (Component) element_p;
    Collection<Component> components = ComponentExt.getAllSubUsedComponents(component);

    components.add(component);

    for (ComponentExchange connection : ComponentExt.getAllRelatedComponentExchange(component)) {
      Component sourceComponent = ComponentExchangeExt.getSourceComponent(connection);
      Component targetComponent = ComponentExchangeExt.getTargetComponent(connection);

      if (sourceComponent != null && targetComponent != null) {
        if ((sourceComponent.equals(component) || targetComponent.equals(component))) {
          result_p.add(connection);
          InterfaceGenerationFinalizer.register(connection, sourceComponent, targetComponent);
        }
      }
    }

    for (Component subComponent : components) {
      for (AbstractFunction function : subComponent.getAllocatedFunctions()) {

        // Process outgoing exchanges
        for (FunctionalExchange exchange : FunctionExt.getOutGoingExchange(function)) {
          AbstractFunction targetF = getFunctionContainer(exchange.getTarget());
          if (targetF != null) {
            for (ComponentFunctionalAllocation componentFunctionalAllocation : targetF.getComponentFunctionalAllocations()) {
              TraceableElement allocating = componentFunctionalAllocation.getSourceElement();
              result_p.add(exchange);
              InterfaceGenerationFinalizer.register(exchange, subComponent, (Component) allocating);
            }
          }
        }

        // Process incoming exchanges
        for (FunctionalExchange exchange : FunctionExt.getIncomingExchange(function)) {
          AbstractFunction targetF = getFunctionContainer(exchange.getSource());
          if (targetF != null) {
            for (ComponentFunctionalAllocation componentFunctionalAllocation : targetF.getComponentFunctionalAllocations()) {
              TraceableElement allocating = componentFunctionalAllocation.getSourceElement();
              result_p.add(exchange);
              InterfaceGenerationFinalizer.register(exchange, (Component) allocating, subComponent);
            }
          }
        }
      }
    }
  }

  @Override
  protected Object transformElement(EObject element_p, ITransfo transfo_p) {
    return null;
  }

  /**
   * Retrieve the first function containing the object, or the object himself if it's a function
   */
  private AbstractFunction getFunctionContainer(EObject object) {
    if (object instanceof AbstractFunction) {
      return (AbstractFunction) object;
    }
    return (AbstractFunction) EObjectExt.getFirstContainer(object, FaPackage.Literals.ABSTRACT_FUNCTION);
  }

}
