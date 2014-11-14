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
package org.polarsys.capella.core.flexibility.commands;

import org.eclipse.ui.IStartup;

import org.polarsys.capella.core.flexibility.commands.actions.IToolMonitor;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 *
 */
public class Startup implements IStartup {

  /**
   * @see org.eclipse.ui.IStartup#earlyStartup()
   */
  public void earlyStartup() {
    MDEAdapterFactory.getEditingDomain().getCommandStack().addCommandStackListener(new IToolMonitor());
  }
}
