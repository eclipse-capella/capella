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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 * A MarkerResolution that delegates resolution to a transactional command.
 * 
 * By default, after the command has executed, the marker
 * for which the resolution ran will be deleted, that is,
 * we assume that the command execution resolves the marker
 * successfully. If you do not want this to happen, or require
 * more sophisticated action, you can just override the
 * deleteMarker() method.
 * 
 */
public class CommandMarkerResolution extends AbstractCapellaMarkerResolution {

  private ICommand command;
  
  public CommandMarkerResolution(ICommand command){
    this.command = command;
  }
  
  public void run(IMarker marker){
    getExecutionManager(marker).execute(command);
    deleteMarker(marker);
  }
  
  @Override
  public String getLabel(){
    return command.getName();
  }
  
  protected ExecutionManager getExecutionManager(IMarker marker) {
    return TransactionHelper.getExecutionManager(getModelElements(marker));
  }

  protected void deleteMarker(IMarker marker){
    if (marker.exists()){
      try {
        marker.delete();
      } catch (CoreException e){
        PluginActivator.getDefault().log(IStatus.ERROR, e.getLocalizedMessage(), e);
      }
    }
  }

}
