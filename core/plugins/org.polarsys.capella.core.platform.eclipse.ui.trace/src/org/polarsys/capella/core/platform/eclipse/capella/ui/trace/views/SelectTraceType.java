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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views;

import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.TraceNameHelper;

/**
 * @deprecated
 */
public class SelectTraceType extends WizardPage {

  /** Parent wizard */
  public AddTraceWizard _parentWizard;
  /** UI - ComboBox */
  private Combo _comboType;

  /**
   * @param pageName_p
   */
  public SelectTraceType(String pageName_p, AddTraceWizard parentWizard_p) {
    super(pageName_p);
    _parentWizard = parentWizard_p;
    setTitle(Messages.getString("SelectTraceType.title")); //$NON-NLS-1$
  }

  /**
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent_p) {
    // **Layout definition
    RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
    rowLayout.wrap = true;
    rowLayout.fill = true;
    rowLayout.justify = false;

    // **Main composite
    Composite composite = new Composite(parent_p, SWT.NONE);
    composite.setLayout(rowLayout);

    // label question
    Label label = new Label(composite, SWT.READ_ONLY | SWT.SINGLE | SWT.NONE);
    label.setText(Messages.getString("SelectTraceType.question_selection")); //$NON-NLS-1$

    // Combo displaying types
    _comboType = new Combo(composite, SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);
    List<String> traceTypes = TraceNameHelper.getTraceTypes();
    for (String traceType : traceTypes) {
      _comboType.add(traceType);
    }
    _comboType.select(0);
    _parentWizard.setTraceType(_comboType.getText());
    _comboType.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent e_p) {
        //
      }

      public void widgetSelected(SelectionEvent e_p) {
        if (e_p.getSource() instanceof Combo) {
          Combo combo = (Combo) e_p.getSource();
          _parentWizard.setTraceType(combo.getText());
        }
      }
    });

    setControl(composite);
  }

}
