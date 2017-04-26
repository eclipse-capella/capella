/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.tiger.ITransfo;

abstract class Rule_FunctionPort extends InterfaceGenerationRule {

  private final static ProviderRequirerTracing tracing = new ProviderRequirerTracing();
  
  public Rule_FunctionPort(EClass sourceType_p, EClass targetType_p) {
    super(sourceType_p, targetType_p);
  }

  abstract Collection<ExchangeItem> getRelatedExchangeItems(FunctionPort fp);

  protected abstract Collection<FunctionalExchange> getRelatedFunctionalExchanges(FunctionPort p);
  
  @Override
  protected Collection<InterfaceInfo> transformToInterfaceInfo(EObject element, ITransfo transfo_p) {

    Collection<InterfaceInfo> result = new ArrayList<InterfaceInfo>();

    for (FunctionalExchange fe : getRelatedFunctionalExchanges((FunctionPort)element)){
      if (isExternal(fe)){
        return Collections.emptyList();
      }
    }

    for (ComponentPort cp : ((FunctionPort)element).getAllocatorComponentPorts()) {

      InterfaceProvider provider = null;
      InterfaceRequirer requirer = null;

      if (getRole() == ProviderRequirerRole.PROVIDER) {
        provider = new ComponentPortInterfaceAdapter(cp);
      } else {
        requirer = new ComponentPortInterfaceAdapter(cp);
      }

      if (getRelatedExchangeItems((FunctionPort)element).size() > 0){
        result.add(new InterfaceInfo(provider, requirer, tracing));
      }
    }
    return result;
  }

    abstract ProviderRequirerRole getRole();

    private boolean isExternal(FunctionalExchange exchange){
      AbstractFunction sourceF = FunctionalExchangeExt.getSourceFunction(exchange);
      AbstractFunction targetF = FunctionalExchangeExt.getTargetFunction(exchange);

      boolean result = true;
      result &= sourceF != null;
      result &= targetF != null;
      result &= sourceF.getAllocationBlocks().size() > 0;
      result &= targetF.getAllocationBlocks().size() > 0;
      result &= sourceF.getAllocationBlocks().get(0) != targetF.getAllocationBlocks().get(0);

      return result;
    }

}
