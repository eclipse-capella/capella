/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.internal;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog displayed when uncontroling a fragment.
 */
public class UncontrolMessageDialog extends MessageDialog {
  private boolean _shouldUncontrolRepresentations;

  /**
   * Constructor.
   * @param parentShell_p
   * @param controledObjectLabel_p
   */
  public UncontrolMessageDialog(Shell parentShell_p, String controledObjectLabel_p) {
    super(parentShell_p, Messages.UncontrolMessageDialog_Title, null /* accept default window icon */, NLS.bind(Messages.UncontrolMessageDialog_Message,
        new String[] { controledObjectLabel_p }), QUESTION, new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL }, 0 /*
                                                                                                                                          * Ok is default
                                                                                                                                          */);
    _shouldUncontrolRepresentations = true;
  }

  /**
   * @see org.eclipse.jface.dialogs.MessageDialog#createCustomArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createCustomArea(Composite parent_p) {
    // Create a dummy label for spacing purposes
    new Label(parent_p, SWT.NULL);
    Button button = new Button(parent_p, SWT.CHECK);
    button.setText(Messages.UncontrolMessageDialog_AdditionnalMessage);
    button.setSelection(true);
    button.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        Button button_l = (Button) e_p.widget;
        _shouldUncontrolRepresentations = button_l.getSelection();
      }
    });
    return button;
  }

  /**
   * Should uncontrol representations in additions to semantic elements ?
   * @return
   */
  public boolean shouldUncontrolRepresentations() {
    return _shouldUncontrolRepresentations;
  }
}
