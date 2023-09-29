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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views;

import java.util.List;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.TraceNameHelper;

/**
 * @deprecated
 */
public class SelectTraceType extends WizardPage {

  /** Parent wizard */
  public AddTraceWizard parentWizard;
  /** UI - ComboBox */
  private Combo comboType;

  /**
   * @param pageName
   */
  public SelectTraceType(String pageName, AddTraceWizard parentWizard) {
    super(pageName);
    this.parentWizard = parentWizard;
    setTitle(Messages.getString("SelectTraceType.title")); //$NON-NLS-1$
  }

  /**
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent) {
    // **Layout definition
    RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
    rowLayout.wrap = true;
    rowLayout.fill = true;
    rowLayout.justify = false;

    // **Main composite
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(rowLayout);

    // label question
    Label label = new Label(composite, SWT.READ_ONLY | SWT.SINGLE | SWT.NONE);
    label.setText(Messages.getString("SelectTraceType.question_selection")); //$NON-NLS-1$

    // Combo displaying types
    comboType = new Combo(composite, SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);
    NamedElement context = parentWizard.getCurrentNamedElement();
    if(context == null) context = parentWizard.getWorkingNamedElement();
    ResourceSet rSet = context.eResource().getResourceSet();
    List<String> traceTypes = TraceNameHelper.getTraceTypes(rSet);
    for (String traceType : traceTypes) {
      comboType.add(traceType);
    }
    comboType.select(0);
    parentWizard.setTraceType(comboType.getText());
    comboType.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent e) {
        //
      }

      public void widgetSelected(SelectionEvent e) {
        if (e.getSource() instanceof Combo) {
          Combo combo = (Combo) e.getSource();
          parentWizard.setTraceType(combo.getText());
        }
      }
    });

    setControl(composite);
  }

}
