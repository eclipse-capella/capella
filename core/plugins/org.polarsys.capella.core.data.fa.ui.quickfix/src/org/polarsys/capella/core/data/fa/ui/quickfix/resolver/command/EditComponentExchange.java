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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver.command;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

public class EditComponentExchange extends AbstractReadWriteCommand {
  private ComponentExchange exchange;

  public EditComponentExchange(ComponentExchange exchange) {
    this.exchange = exchange;
  }

  @Override
  public String getName() {
    return "Edit functional exchange allocation"; //$NON-NLS-1$
  }

  public void run() {
    CapellaUIPropertiesPlugin.getDefault().openWizard(exchange);
  }

}
