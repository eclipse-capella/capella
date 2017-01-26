/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
   * @param shell
   * @param selectionProvider
   */
  public MenuInput(Shell shell, ISelectionProvider selectionProvider) {
    super();
    shell = shell;
    selectionProvider = selectionProvider;
  }

  public MenuInput(IServiceLocator locator, ISelectionProvider selectionProvider) {
    super();
    locator = locator;
    selectionProvider = selectionProvider;
  }

  public IServiceLocator locator;

  public Shell shell;
  public ISelectionProvider selectionProvider;

}
