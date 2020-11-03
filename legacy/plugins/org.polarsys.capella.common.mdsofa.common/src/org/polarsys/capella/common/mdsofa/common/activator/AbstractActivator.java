/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
