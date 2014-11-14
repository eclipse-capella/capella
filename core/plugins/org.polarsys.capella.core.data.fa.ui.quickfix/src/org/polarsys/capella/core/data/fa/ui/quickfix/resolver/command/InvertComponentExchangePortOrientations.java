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
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 * Invert the orientations of the ports of a ComponentExchange.
 */
public class InvertComponentExchangePortOrientations extends AbstractReadWriteCommand {

  private ComponentExchange exchange;

  public InvertComponentExchangePortOrientations(ComponentExchange exchange_p) {
    exchange = exchange_p;
  }

  public void run() {
    ComponentPort sourceCompPort = null;
    ComponentPort targetCompPort = null;
    Port sourcePort = ComponentExchangeExt.getSourcePort(exchange);
    Port targetPort = ComponentExchangeExt.getTargetPort(exchange);
    if (sourcePort instanceof ComponentPort) {
      sourceCompPort = (ComponentPort) sourcePort;
    }
    if (targetPort instanceof ComponentPort) {
      targetCompPort = (ComponentPort) targetPort;
    }
    if ((null != sourceCompPort) && (null != targetCompPort)) {
      OrientationPortKind sourceOrientation = sourceCompPort.getOrientation();
      sourceCompPort.setOrientation(targetCompPort.getOrientation());
      targetCompPort.setOrientation(sourceOrientation);
    }

  }

  @Override
  public String getName() {
    return "Invert ComponentExchange port orientations"; //$NON-NLS-1$
  }

}
