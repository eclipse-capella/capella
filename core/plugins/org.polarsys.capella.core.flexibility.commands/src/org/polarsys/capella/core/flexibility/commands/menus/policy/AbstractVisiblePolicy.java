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
package org.polarsys.capella.core.flexibility.commands.menus.policy;

import java.util.HashSet;

/**
 *
 */
public class AbstractVisiblePolicy implements IActionPolicy {

  private HashSet<String> visibles = new HashSet<String>();

  /**
   * @return the visibles
   */
  public void add(String commandId_p) {
    visibles.add(commandId_p);
  }

  /**
   * {@inheritDoc}
   */
  public boolean cover(String commandId_p) {
    return visibles.contains(commandId_p);
  }

}
