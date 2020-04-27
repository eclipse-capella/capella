/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.reportlog;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.ui.PlatformUI;

public class LogDataHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    String defaultText = Messages.LogDataHandler_DefaultText;
    String title = Messages.LogDataHandler_Title;
    String message = Messages.LogDataHandler_Message;
    InputDialog d = new InputDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, message, defaultText, new IInputValidator() {
      
      @Override
      public String isValid(String newText) {
        if (newText.isEmpty()) {
          return Messages.LogDataHandler_Message;
        }
        return null;
      }
    });
    
    d.open();
    if (d.getReturnCode() == InputDialog.OK) {
      String value = d.getValue();
      IStatus status = new Status(IStatus.INFO, ReportLogActivator.getDefault().getPluginId(), value);
      ReportLogActivator.getDefault().getLog().log(status);
    }
    return null;
  }

}
