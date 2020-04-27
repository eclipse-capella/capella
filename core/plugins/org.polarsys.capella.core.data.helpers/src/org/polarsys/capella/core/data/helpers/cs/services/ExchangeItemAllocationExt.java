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

package org.polarsys.capella.core.data.helpers.cs.services;

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeMechanismExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;

/**
 */
public class ExchangeItemAllocationExt {

  /**
   * Change the communication link kind and protocol according to the mechanism
   */
  public static void changeExchangeItemMechanism(ExchangeItemAllocation alloc, ExchangeMechanism mechanism) {
    alloc.setSendProtocol(ExchangeMechanismExt.getProtocol(mechanism, true));
    alloc.setReceiveProtocol(ExchangeMechanismExt.getProtocol(mechanism, false));
  }

  public static void changeExchangeItem(ExchangeItemAllocation alloc, ExchangeItem exchangeItem1) {
    alloc.setAllocatedItem(exchangeItem1);
    changeExchangeItemMechanism(alloc, exchangeItem1.getExchangeMechanism());
  }
}
