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
package org.polarsys.capella.core.ui.metric.actions;

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.ui.metric.IImageKeys;
import org.polarsys.capella.core.ui.metric.MetricActivator;
import org.polarsys.capella.core.ui.metric.MetricMessages;
import org.polarsys.capella.core.ui.metric.utils.ProgressMonitoringPropagator;

public class ProgressMonitoringSetAction extends BaseSelectionListenerAction {

  private class ProgressSetDialog extends Dialog {

    /** Selected enumeration */
    private EnumerationPropertyLiteral _enum = null;

    /**
     * Constructor
     */
    protected ProgressSetDialog(Shell parentShell_p) {
      super(parentShell_p);
    }

    /** accessor */
    public EnumerationPropertyLiteral getSelectedEnum() {
      return _enum;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("synthetic-access")
    @Override
    protected Control createContents(Composite parent_p) {

      getShell().setText(MetricMessages.progressMonitoring_setAction_dialog_title);

      Composite comp = new Composite(parent_p, SWT.NONE);
      comp.setLayout(new GridLayout(2, false));
      comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

      new Label(comp, SWT.NONE).setText(MetricMessages.progressMonitoring_setAction_dialog_main_lbl);

      new Label(comp, SWT.NONE);

      new Label(comp, SWT.NONE).setText(MetricMessages.progressMonitoring_setAction_dialog_combo_lbl);

      final Combo combo = new Combo(comp, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.BORDER);
      EnumerationPropertyType ept = CapellaProjectHelper.getEnumerationPropertyType(_rootSemanticObject, CapellaProjectHelper.PROGRESS_STATUS_KEYWORD);
      int i = 0;
      int i0 = 0;
      for (EnumerationPropertyLiteral enumz : ept.getOwnedLiterals()) {
        combo.add(enumz.getLabel());
        combo.setData(String.valueOf(i++), enumz);
      }
      combo.select(i0);
      _enum = (EnumerationPropertyLiteral) combo.getData(String.valueOf(i0));

      GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
      combo.setLayoutData(gd);

      combo.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
          _enum = (EnumerationPropertyLiteral) combo.getData(String.valueOf(combo.getSelectionIndex()));
          return;
        }
      });

      return super.createContents(parent_p);
    }

  }

  /** the root semantic object selected */
  private EObject _rootSemanticObject;

  /**
   * Constructor.
   */
  public ProgressMonitoringSetAction() {
    super(MetricMessages.progressMonitoring_setAction_lbl);
    setImageDescriptor(MetricActivator.getDefault().getImageDescriptor(IImageKeys.IMG_PROGRESS_MONITORING));
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    EObject rootSemanticObject = ProgressMonitoringActionsHelper.getSelectedEObject(getStructuredSelection());
    if (null == rootSemanticObject) {
      return;
    }
    _rootSemanticObject = rootSemanticObject;

    Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
    ProgressSetDialog d = new ProgressSetDialog(shell);

    int ret = d.open();

    final EnumerationPropertyLiteral enu = d.getSelectedEnum();

    if (Window.OK == ret) {
      TransactionHelper.getExecutionManager(_rootSemanticObject).execute(new AbstractReadWriteCommand() {
        @SuppressWarnings("synthetic-access")
        public void run() {
          ProgressMonitoringPropagator.getInstance().applyPropertiesOn(Collections.singletonList(enu), _rootSemanticObject);
        }

        @Override
        public String getName() {
          return MetricMessages.progressMonitoring_setAction_cmd_lbl;
        }
      });
    }

    // in order to avoid any memory leak with models...
    _rootSemanticObject = null;
  }

}
