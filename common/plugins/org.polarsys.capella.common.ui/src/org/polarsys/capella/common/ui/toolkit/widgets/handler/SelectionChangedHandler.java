/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
   * @param control The control.
   */
  public void addControl(Control control) {
    // Lazy pattern creation.
    if (null == _controls) {
      _controls = new ArrayList<Control>(1);
    }
    _controls.add(control);
  }

  /**
   * Handle selection and recompute the enable state of all managed controls.
   * @param selection The selection.
   */
  public void handleSelection(ISelection selection) {
    // Delegate the enable computation to class that overrides this class.
    boolean enabled = doHandleSelection(selection);
    // Set the enable state to the control.
    for (Control control : _controls) {
      control.setEnabled(enabled);
    }
  }

  /**
   * Handle selection to enable or disable the managed controls.
   * @param selection The selection.
   * @return <code>True</code> means enable, <code>false</code> means disable.
   */
  protected abstract boolean doHandleSelection(ISelection selection);
}
