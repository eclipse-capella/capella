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

import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * Set the orientation of a ComponentPort.
 * 
 */
public class SetComponentPortOrientation extends AbstractReadWriteCommand {

  private ComponentPort port;
  private OrientationPortKind kind;
  
  public SetComponentPortOrientation(ComponentPort port, OrientationPortKind kind){
    this.port = port;
    this.kind = kind;
  }
  
  @Override
  public String getName() {
    return "Set orientation of ComponentPort " + port.getName() + " to " + kind; //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public void run() {
    port.setOrientation(kind);
  }
}
