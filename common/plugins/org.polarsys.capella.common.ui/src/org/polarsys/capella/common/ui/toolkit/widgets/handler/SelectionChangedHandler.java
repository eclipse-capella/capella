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
package org.polarsys.capella.common.ui.toolkit.widgets.handler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Control;

/**
 * A selection changed handler set the enable state to the managed control list according to a selection.<br>
 * Client must override {@link #doHandleSelection(ISelection)}, to provide the rule which really computes the enable state depending on the given selection.
 */
public abstract class SelectionChangedHandler {
  /**
   * Control that the selection changed handler computes the state enable value for.
   */
  private List<Control> _controls;

  /**
   * Add a control in the list of managed controls.
   * @param control_p The control.
   */
  public void addControl(Control control_p) {
    // Lazy pattern creation.
    if (null == _controls) {
      _controls = new ArrayList<Control>(1);
    }
    _controls.add(control_p);
  }

  /**
   * Handle selection and recompute the enable state of all managed controls.
   * @param selection_p The selection.
   */
  public void handleSelection(ISelection selection_p) {
    // Delegate the enable computation to class that overrides this class.
    boolean enabled = doHandleSelection(selection_p);
    // Set the enable state to the control.
    for (Control control : _controls) {
      control.setEnabled(enabled);
    }
  }

  /**
   * Handle selection to enable or disable the managed controls.
   * @param selection_p The selection.
   * @return <code>True</code> means enable, <code>false</code> means disable.
   */
  protected abstract boolean doHandleSelection(ISelection selection_p);
}
