/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ui.quickfix.generator.GenerateInterfacesResolutionGenerator;
import org.polarsys.capella.core.data.fa.ui.quickfix.resolver.command.DeallocateExchangeItemCommand;
import org.polarsys.capella.core.data.information.ExchangeItem;

public class GenerateInterfacesDeallocateResolver extends GenerateInterfacesResolver {

  @Override
  protected boolean run(ExchangeItem exchangeItem, Interface iface, List<EObject> exchanges, int statusCode,
      IMarker marker) {

    Collection<? extends EObject> targets = null;
    if (statusCode == GenerateInterfacesResolutionGenerator.UNKNOWN_EI_ON_INTERFACE) {
      targets = Collections.singleton(iface);
    } else if (statusCode == GenerateInterfacesResolutionGenerator.MISSING_EI_ON_INTERFACE) {
      targets = exchanges;
    }

    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(exchangeItem);
    AbstractCommand cmd = new DeallocateExchangeItemCommand(domain, exchangeItem, targets);
    cmd.setLabel(getLabel());
    domain.getCommandStack().execute(cmd);
    return !cmd.getResult().contains(Status.CANCEL_STATUS);
  }

}
