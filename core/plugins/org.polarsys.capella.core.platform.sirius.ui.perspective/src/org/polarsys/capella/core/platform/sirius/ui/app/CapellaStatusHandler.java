/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.ui.app;

import java.util.List;

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
    StatusHandlerRegistry handlerRegistry = StatusHandlerRegistry.getDefault();
    String plugin = statusAdapter.getStatus().getPlugin();
    List<?> handlerDescriptors = handlerRegistry.getHandlerDescriptors(plugin);
    AbstractStatusHandler statusHandler = defaultHandler;
    if (handlerDescriptors != null && !handlerDescriptors.isEmpty()) {
      StatusHandlerDescriptor statusHandlerDescriptor = (StatusHandlerDescriptor) handlerDescriptors.get(0);
      try {
        statusHandler = statusHandlerDescriptor.getStatusHandler();
      } catch (CoreException ex) {
      }
    }
    statusHandler.handle(statusAdapter, style);
  }
}
