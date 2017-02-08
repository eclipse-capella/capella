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
package org.polarsys.capella.core.model.helpers;

import java.util.Collection;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.core.data.information.ExchangeItem;
import static org.polarsys.capella.core.model.helpers.ExchangeItemAllocator.allocate;


public class AllocateExchangeItemCommand extends RecordingCommand {

  private final Collection<?> targets;
  private final ExchangeItem exchangeItem;

  public String getLabel(){
    return "Allocate Exchange Item";//$NON-NLS-1$
  }

  public AllocateExchangeItemCommand(ExchangeItem ei, Collection<?> targets) {
    super(TransactionUtil.getEditingDomain(ei));
    this.targets = targets;
    this.exchangeItem = ei;
  }

  @Override
  protected void doExecute() {
    allocate(exchangeItem).on(targets);
  }
}