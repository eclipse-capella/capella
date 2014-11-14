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
  
  public SetComponentPortOrientation(ComponentPort port_p, OrientationPortKind kind_p){
    port = port_p;
    kind = kind_p;
  }
  
  @Override
  public String getName() {
    return "Set orientation of ComponentPort " + port.getName() + " to " + kind; //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public void run() {
    port.setOrientation(kind);
  }
}
