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
package org.polarsys.capella.core.flexibility.commands.menus.provider;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.services.IServiceLocator;

/**
 *
 */
public class MenuInput {

  /**
   * @param shell_p
   * @param selectionProvider_p
   */
  public MenuInput(Shell shell_p, ISelectionProvider selectionProvider_p) {
    super();
    shell = shell_p;
    selectionProvider = selectionProvider_p;
  }

  public MenuInput(IServiceLocator locator_p, ISelectionProvider selectionProvider_p) {
    super();
    locator = locator_p;
    selectionProvider = selectionProvider_p;
  }

  public IServiceLocator locator;

  public Shell shell;
  public ISelectionProvider selectionProvider;

}
