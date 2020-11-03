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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.projection.interfaces.InterfaceGeneration;
import org.polarsys.capella.core.projection.interfaces.InterfaceGenerationPreferences;
import org.polarsys.capella.core.tiger.ITransfo;

/**
 */
public class Rule_FunctionalExchange_Interface extends InterfaceGenerationRule {

  private final TracingStrategy tracingStrategy = new ExchangeTracing();

  /* cache computed information */
  final static String KEY_INTERFACEINFO_MAP = "org.polarsys.capella.core.projection.interfaces.generateInterfaces.INFOMAP"; //$NON-NLS-1$

  public Rule_FunctionalExchange_Interface() {
    super(FaPackage.Literals.FUNCTIONAL_EXCHANGE, CsPackage.Literals.INTERFACE);
  }

  @Override
  protected Collection<InterfaceInfo> transformToInterfaceInfo(EObject element, ITransfo transfo) {
    InterfaceInfo info = getInterfaceInfo((FunctionalExchange) element, transfo);
    if (info != null) {
      InterfaceGenerationPreferences prefs = InterfaceGeneration.getPreferences(transfo);
      if (info.getExchangeItems(prefs).size() > 0) {
        return Collections.singleton(info);
      }
    }
    return Collections.emptyList();
  }

  /**
   * Find the interface information data for a given exchange. Returns an empty lisf for internal exchanges. Multiple
   * elements are returned if the related function ports is allocated to multiple distinct component port pairs and/or
   * the exchange is allocated to multiple distinct component exchanges that connect different pairs of component ports.
   */
  private Collection<InterfaceInfo> getInterfaceInfo(FunctionalExchange exchange) {

    Collection<InterfaceInfo> result = new LinkedHashSet<InterfaceInfo>();

    // watch out: source and target function is not related to input and output port
    AbstractFunction sourceFunction = FunctionalExchangeExt.getSourceFunction(exchange);
    AbstractFunction targetFunction = FunctionalExchangeExt.getTargetFunction(exchange);

    for (ComponentExchange ce : exchange.getAllocatingComponentExchanges()) {
      ComponentPort leftPort = (ComponentPort) ce.getSourcePort();
      ComponentPort rightPort = (ComponentPort) ce.getTargetPort();
      if (leftPort != null && rightPort != null && sourceFunction != null && targetFunction != null) {

        ComponentPort providing = null;
        ComponentPort requiring = null;

        Component leftComponent = PortExt.getRelatedComponent(leftPort);
        Component rightComponent = PortExt.getRelatedComponent(rightPort);

        if (leftComponent.getAllocatedFunctions().contains(sourceFunction)
            && rightComponent.getAllocatedFunctions().contains(targetFunction)) {
          requiring = leftPort;
          providing = rightPort;
        } else if (rightComponent.getAllocatedFunctions().contains(sourceFunction)
            && leftComponent.getAllocatedFunctions().contains(targetFunction)) {
          requiring = rightPort;
          providing = leftPort;
        }
        if (providing != null && requiring != null && providing != requiring) {
          result.add(new InterfaceInfo(new ComponentPortInterfaceAdapter(providing),
              new ComponentPortInterfaceAdapter(requiring), tracingStrategy));
        }
      }
    }

    FunctionInputPort fip = exchange.getTargetFunctionInputPort();
    FunctionOutputPort fop = exchange.getSourceFunctionOutputPort();

    if (fip != null && fop != null) {

      Collection<InterfaceRequirer> allRequiring = analyzePort(sourceFunction, fop, result.isEmpty());
      Collection<InterfaceProvider> allProviding = analyzePort(targetFunction, fip, result.isEmpty());

      for (InterfaceRequirer requiring : allRequiring) {
        for (InterfaceProvider providing : allProviding) {
          if (providing != requiring) {
            result.add(new InterfaceInfo(providing, requiring, tracingStrategy));
          }
        }
      }
    }

    return result;

  }

  @SuppressWarnings("unchecked")
  private <T> Collection<T> analyzePort(AbstractFunction f, FunctionPort port,
      boolean createComponentInterfaceAdapter) {
    Collection<T> result = new ArrayList<T>();
    for (ComponentPort p : port.getAllocatorComponentPorts()) {
      result.add((T) new ComponentPortInterfaceAdapter(p));
    }
    if (result.isEmpty() && createComponentInterfaceAdapter) {
      for (AbstractFunctionalBlock allocator : f.getAllocationBlocks()) {
        if (allocator instanceof Component) {
          result.add((T) new ComponentInterfaceAdapter((Component) allocator));
        }
      }
    }
    return result;
  }

  /* cache already known results here */
  @SuppressWarnings("unchecked")
  private static Map<FunctionalExchange, InterfaceInfo> getInfoMap(ITransfo transfo) {
    Map<FunctionalExchange, InterfaceInfo> map = (Map<FunctionalExchange, InterfaceInfo>) transfo
        .get(KEY_INTERFACEINFO_MAP);
    if (map == null) {
      map = new HashMap<FunctionalExchange, InterfaceInfo>();
      transfo.put(KEY_INTERFACEINFO_MAP, map);
    }
    return map;
  }

  private void logMultipleInfosFound(EObject exchange, ITransfo transfo) {
    // Do not log error in the Information view when this rule is executed during a model validation
    if (!transfo.isDryRun()) {
      _logger.error(new EmbeddedMessage("Skipping generation for functional exchange "
          + EObjectLabelProviderHelper.getText(exchange) + " which has inconsistent or multiple port/ce allocations",
          _logger.getName(), exchange));
    }
  }

  private void logNoInfosFound(EObject element) {
    if (_logger.isDebugEnabled()) {
      _logger.debug(new EmbeddedMessage(
          "Skipping generation for internal functional exchange " + EObjectLabelProviderHelper.getText(element),
          _logger.getName(), element));
    }
  }

  /**
   * Returns the interface for the given functional exchange, or null if no such interface exists.
   *
   * @param exchange
   * @param transfo
   * @return
   */
  public static Interface getInterface(FunctionalExchange exchange, ITransfo transfo) {
    Interface result = null;
    InterfaceInfo info = getInfoMap(transfo).get(exchange);
    if (info != null) {
      result = info.getInterface(false);
    }
    return result;
  }

  InterfaceInfo getInterfaceInfo(FunctionalExchange element, ITransfo transfo) {
    Map<FunctionalExchange, InterfaceInfo> infomap = getInfoMap(transfo);
    InterfaceInfo result = null;
    if (infomap.containsKey(element)) {
      result = getInfoMap(transfo).get(element);
    } else {
      Collection<InterfaceInfo> infos = getInterfaceInfo((FunctionalExchange) element);
      if (infos.size() == 1) {
        result = infos.iterator().next();
        for (FunctionalExchange exchange : result.getFunctionalExchanges()) {
          infomap.put(exchange, result);
        }
      } else if (infos.isEmpty()) {
        logNoInfosFound(element); // internal exchange
      } else {
        logMultipleInfosFound(element, transfo); // exchange is somehow allocated multiple times/and/or/inconsistently
      }
      infomap.put((FunctionalExchange) element, result);
    }
    return result;
  }

}