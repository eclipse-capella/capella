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
package org.polarsys.capella.core.commands.preferences.service;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Extension of the {@link BooleanFieldEditor} class in order to
 * expose the change button for this {@link FieldEditor}
 */
public class BooleanFieldEditor2 extends BooleanFieldEditor {

  /**
   * Constructor
   * @see BooleanFieldEditor#BooleanFieldEditor(String, String, Composite)
   */
  public BooleanFieldEditor2(String label, String tooltip, Composite parent) {
    super(label, tooltip, parent);
  }

  /**
   * @see org.eclipse.jface.preference.BooleanFieldEditor#getChangeControl(Composite)
   */
  @Override
  public Button getChangeControl(Composite parent) {
    return super.getChangeControl(parent);
  }


}
