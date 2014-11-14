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
package org.polarsys.capella.common.mdsofa.common.activator;

import org.eclipse.core.runtime.Plugin;

/**
 */
public abstract class AbstractActivator extends Plugin {
  /**
   * Get the plug-in ID according to MANISFEST.MF definition.
   * 
   * @return a String containing the plug-in ID.
   */
  public String getPluginId() {
    return getBundle().getSymbolicName();
  }
}
