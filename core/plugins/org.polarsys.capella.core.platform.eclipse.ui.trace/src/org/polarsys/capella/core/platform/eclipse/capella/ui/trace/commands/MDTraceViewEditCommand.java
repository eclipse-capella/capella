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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.MainWizard;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.ViewEditPage;
import org.polarsys.capella.core.ui.toolkit.dialogs.CapellaWizardDialog;

/**
 * <code>MDTraceViewEditCommand</code> Command to launch the viewer/editor of traces for a selected element.
 */
public class MDTraceViewEditCommand extends AbstractReadWriteCommand {

  /** selected element */
  private ModelElement _root;
  private MDTraceViewListener _listener;
  private MainWizard _wizard;

  @Override
  public String getName() {
    return "MDTraceViewEditCommand"; //$NON-NLS-1$
  }

  /**
   * @param root_p
   * @param listener_p
   */
  public MDTraceViewEditCommand(ModelElement root_p, MDTraceViewListener listener_p) {
    super();
    _root = root_p;
    _listener = listener_p;
  }

  public void run() {
    if (_root instanceof TraceableElement) {
      TraceableElement root = (TraceableElement) _root;
      _wizard = new MainWizard();
      ViewEditPage editPage = new ViewEditPage("editPage", root);//$NON-NLS-1$
      _wizard.addPage(editPage);
      _wizard.setWindowTitle(Messages.getString("MDTrace.window_title")); //$NON-NLS-1$
      // Adding to the main wizard dialog
      CapellaWizardDialog dialog = new CapellaWizardDialog(Display.getCurrent().getActiveShell(), _wizard);

      dialog.setPageSize(620, 400);

      if (dialog.open() != Window.OK) {
        _listener.setRollbackRequested(true);
      }
    }
  }

  /**
   * For testing purpose
   * @return the wizard
   */
  public MainWizard getWizard() {
    return _wizard;
  }

  /**
   * @see org.polarsys.capella.common.ef.command.ICommand#isReadOnly()
   */
  @Override
  public boolean isReadOnly() {
    return false;
  }
}
