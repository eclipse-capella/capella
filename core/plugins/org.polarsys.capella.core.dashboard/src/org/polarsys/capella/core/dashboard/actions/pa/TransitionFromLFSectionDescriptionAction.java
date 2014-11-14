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
package org.polarsys.capella.core.dashboard.actions.pa;

import org.eclipse.swt.widgets.Shell;

import org.polarsys.capella.core.dashboard.actions.AbstractDescriptionAction;

/**
 * Description action for Transition from Logical Functions.
 */
public class TransitionFromLFSectionDescriptionAction extends AbstractDescriptionAction {
  /**
   * Constructor.
   * @param shell_p
   */
  public TransitionFromLFSectionDescriptionAction(Shell shell_p) {
    super(shell_p);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.actions.AbstractDescriptionAction#getDescriptionFile()
   */
  @Override
  protected String getDescriptionFile() {
    return "description/pa/TransitionFromLFSectionDescriptionAction.xml"; //$NON-NLS-1$
  }
}
