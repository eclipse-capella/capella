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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver.command;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

public class EditComponentExchange extends AbstractReadWriteCommand {
  private ComponentExchange exchange;

  public EditComponentExchange(ComponentExchange exchange_p) {
    exchange = exchange_p;
  }

  @Override
  public String getName() {
    return "Edit functional exchange allocation"; //$NON-NLS-1$
  }

  public void run() {
    CapellaUIPropertiesPlugin.getDefault().openWizard(exchange);
  }

}
