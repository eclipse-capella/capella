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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.ExecutionManager;
import org.polarsys.capella.common.tig.ef.command.ICommand;

/**
 * A MarkerResolution that delegates resolution to a TIG command.
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
  
  public CommandMarkerResolution(ICommand command_p){
    command = command_p;
  }
  
  public void run(IMarker marker){
    getExecutionManager().execute(command);
    deleteMarker(marker);
  }
  
  @Override
  public String getLabel(){
    return command.getName();
  }
  
  protected ExecutionManager getExecutionManager(){
    return MDEAdapterFactory.getExecutionManager();
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
