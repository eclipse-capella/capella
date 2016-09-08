/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui.app;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.internal.WorkbenchErrorHandlerProxy;
import org.eclipse.ui.internal.statushandlers.StatusHandlerDescriptor;
import org.eclipse.ui.internal.statushandlers.StatusHandlerRegistry;
import org.eclipse.ui.statushandlers.AbstractStatusHandler;
import org.eclipse.ui.statushandlers.StatusAdapter;

public class CapellaStatusHandler extends AbstractStatusHandler {

  WorkbenchErrorHandlerProxy defaultHandler  = new WorkbenchErrorHandlerProxy();

  @Override
  public void handle(StatusAdapter statusAdapter, int style) {

    try {
      ((StatusHandlerDescriptor)StatusHandlerRegistry.getDefault().getHandlerDescriptors(statusAdapter.getStatus().getPlugin()).get(0)).getStatusHandler().handle(statusAdapter, style);
   
    } catch (Exception e2) {
      try {
        defaultHandler.handle(statusAdapter, style);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
      
    };
  }

}
