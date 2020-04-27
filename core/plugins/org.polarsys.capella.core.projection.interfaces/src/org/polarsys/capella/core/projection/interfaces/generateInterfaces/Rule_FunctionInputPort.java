/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;

public class Rule_FunctionInputPort extends Rule_FunctionPort {

  public Rule_FunctionInputPort() {
    super(FaPackage.Literals.FUNCTION_INPUT_PORT, CsPackage.Literals.INTERFACE);
  }

  @Override
  Collection<ExchangeItem> getRelatedExchangeItems(FunctionPort p) {
    return ((FunctionInputPort)p).getIncomingExchangeItems();
  }

  @Override
  ProviderRequirerRole getRole() {
    return ProviderRequirerRole.PROVIDER;
  }

  @Override
  protected Collection<FunctionalExchange> getRelatedFunctionalExchanges(FunctionPort p) {
    return (((FunctionInputPort)p).getIncomingFunctionalExchanges());
  }

  @Override
  protected void doGoDeep(EObject element, List<EObject> result) {
    result.addAll(((FunctionInputPort)element).getIncomingFunctionalExchanges());
  }

}