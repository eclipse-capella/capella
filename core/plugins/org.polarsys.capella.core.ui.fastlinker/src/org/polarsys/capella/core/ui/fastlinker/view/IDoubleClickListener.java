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
package org.polarsys.capella.core.ui.fastlinker.view;

import org.eclipse.jface.viewers.SelectionChangedEvent;

/**
 * A listener which is notified of double-click events.<br>
 * Inspired from {@link org.eclipse.jface.viewers.IDoubleClickListener} which uses (unfortunately) an event tied to a viewer.
 */
public interface IDoubleClickListener {
  /**
   * Notifies of a double click.
   * @param selectionChangedEvent_p event object describing the double-click selection.
   */
  public void doubleClick(SelectionChangedEvent selectionChangedEvent_p);
}
