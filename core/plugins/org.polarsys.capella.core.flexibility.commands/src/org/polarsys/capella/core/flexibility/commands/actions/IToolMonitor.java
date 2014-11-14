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
package org.polarsys.capella.core.flexibility.commands.actions;

import java.util.EventObject;

import org.eclipse.emf.common.command.CommandStackListener;

/**
 *
 */
public class IToolMonitor implements CommandStackListener {

  /**
   * {@inheritDoc}
   */
  public void commandStackChanged(EventObject event_p) {
    System.out.println(0);
  }

}
