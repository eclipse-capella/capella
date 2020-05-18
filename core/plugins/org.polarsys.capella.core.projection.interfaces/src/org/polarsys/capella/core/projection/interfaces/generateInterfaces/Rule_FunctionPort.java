/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.tiger.ITransfo;

abstract class Rule_FunctionPort extends InterfaceGenerationRule {

  private static final ProviderRequirerTracing tracing = new ProviderRequirerTracing();
  
  private static final Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);

  public Rule_FunctionPort(EClass sourceType, EClass targetType) {
    super(sourceType, targetType);
  }

  abstract Collection<ExchangeItem> getRelatedExchangeItems(FunctionPort fp);

  protected abstract Collection<FunctionalExchange> getRelatedFunctionalExchanges(FunctionPort p);
  
  @Override
  protected Collection<InterfaceInfo> transformToInterfaceInfo(EObject element, ITransfo transfo) {

    Collection<InterfaceInfo> result = new ArrayList<>();

    for (FunctionalExchange fe : getRelatedFunctionalExchanges((FunctionPort)element)){
      if (isExternal(fe, transfo)){
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

      if (!getRelatedExchangeItems((FunctionPort)element).isEmpty()){
        result.add(new InterfaceInfo(provider, requirer, tracing));
      }
    }
    return result;
  }

    abstract ProviderRequirerRole getRole();

    private boolean isExternal(FunctionalExchange exchange, ITransfo transfo){
      
      AbstractFunction sourceF = FunctionalExchangeExt.getSourceFunction(exchange);
      AbstractFunction targetF = FunctionalExchangeExt.getTargetFunction(exchange);

      boolean result = true;
      result &= sourceF != null;
      result &= targetF != null;
      result &= !sourceF.getAllocationBlocks().isEmpty();
      if (!result) {
        if(!transfo.isDryRun()){
          // The source function has not been allocated to any component
          String name = sourceF != null ? sourceF.getName() : "Unknown";
          String message = name + " has not been allocated to any component";
          logger.warn(new EmbeddedMessage(message, logger.getName(), Arrays.asList(sourceF)));
        }          
        return true;
      }
      result &= !targetF.getAllocationBlocks().isEmpty();
      if (!result) {
        if(!transfo.isDryRun()){
          // The target function has not been allocated to any component
          String message = targetF.getName() + " has not been allocated to any component";
          logger.warn(new EmbeddedMessage(message, logger.getName(), Arrays.asList(targetF)));
        }
        return true;
      }
      result &= sourceF.getAllocationBlocks().get(0) != targetF.getAllocationBlocks().get(0);

      return result;
    }
}
