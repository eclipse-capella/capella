/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.handlers;

import org.eclipse.core.commands.Command;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.services.IServiceLocator;

/**
 * 
 */
public class MigrationHandlers {

  public static Command getProjectCommand() {
    return getProjectCommand(PlatformUI.getWorkbench());
  }

  public static Command getAirdCommand() {
    return getAirdCommand(PlatformUI.getWorkbench());
  }

  public static Command getSemanticCommand() {
    return getSemanticCommand(PlatformUI.getWorkbench());
  }

  public static Command getProjectCommand(IServiceLocator workbench) {
    ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
    return service.getCommand("org.polarsys.capella.core.data.migration.project");
  }

  public static Command getAirdCommand(IServiceLocator workbench) {
    ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
    return service.getCommand("org.polarsys.capella.core.data.migration.aird");
  }

  public static Command getSemanticCommand(IServiceLocator workbench) {
    ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
    return service.getCommand("org.polarsys.capella.core.data.migration.model");
  }
}
