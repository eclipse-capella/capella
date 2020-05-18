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
package org.polarsys.capella.common.ui.services.commands;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandImageService;

/**
 * Actions defined in the legacy extension point 'org.eclipse.ui.popupMenus' are implementing
 * org.eclipse.ui.IObjectActionDelegate or org.eclipse.ui.IActionDelegate.
 * 
 * This extension point is deprecated, but there is still many Actions implemented into Capella.
 * 
 * Contributions to 'org.eclipse.ui.popupMenus' extension points have been migrated to but to keep backwards
 * compatibility for now, IHandler are redirecting towards legacy ActionDelegates.
 * 
 * To avoid unexpected NullPointerException in ActionDelegates, this class intend to cover major of API from IAction
 * that was called in Capella ActionDelegate from the ExecutionEvent. (mainly action.getText() used in the
 * org.polarsys.capella.common.ef.command.ICommand.getName())
 * 
 * This class is not intended to be used, unless for backward compatibility. IHandler must be used instead of new
 * IActionDelegates.
 */
public class ActionCommandDelegate extends Action {

  Command command;

  public ActionCommandDelegate(ExecutionEvent event) {
    this.command = event.getCommand();
  }

  @Override
  public String getDescription() {
    try {
      return command.getDescription();
    } catch (NotDefinedException e) {
      // Nothing here
    }
    return command.getId();
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    try {
      ICommandImageService service = PlatformUI.getWorkbench().getService(ICommandImageService.class);
      return service.getImageDescriptor(command.getId());
    } catch (Exception e) {
      // Nothing here
    }
    return null;
  }

  @Override
  public String getText() {
    try {
      return command.getName();
    } catch (NotDefinedException e) {
      // Nothing here
    }
    return command.getId();
  }

}
