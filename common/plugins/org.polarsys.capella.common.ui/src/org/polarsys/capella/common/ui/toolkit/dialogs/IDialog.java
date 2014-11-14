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
package org.polarsys.capella.common.ui.toolkit.dialogs;

/**
 * Interfacize a jface dialog. 
 * An IDialog can be opened and closed. This interface is
 * provided to efficiently mock dialogs in tests without the need
 * of actually creating a "real" jface dialog.
 */
public interface IDialog {
  
  /**
   * @see org.eclipse.jface.dialogs.Dialog.open()
   */
  public int open();
  
  /**
   * @see org.eclipse.jface.dialogs.Dialog.close()
   */
  public boolean close();
  
}
