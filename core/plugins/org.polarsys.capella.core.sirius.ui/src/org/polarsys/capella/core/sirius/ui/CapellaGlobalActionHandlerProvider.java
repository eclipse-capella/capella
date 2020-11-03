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
package org.polarsys.capella.core.sirius.ui;

import java.util.Hashtable;
import java.util.Map;

import org.eclipse.gmf.runtime.common.ui.services.action.global.AbstractGlobalActionHandlerProvider;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandler;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandlerContext;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandlerProvider;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

public class CapellaGlobalActionHandlerProvider extends AbstractGlobalActionHandlerProvider
    implements IGlobalActionHandlerProvider {

  /**
   * A map containing for each IWorkbenchPart (Diagram Editor) the associated IGlobalActionHandler.
   */
  private Map<IWorkbenchPart, IGlobalActionHandler> actionHandlers = new Hashtable<>();

  /**
   * Returns the IGlobalActionHandler associated with the current context.
   */
  @Override
  public IGlobalActionHandler getGlobalActionHandler(IGlobalActionHandlerContext context) {
    final IWorkbenchPart part = context.getActivePart();
    /* Create the handler */
    if (!getActionHandlers().containsKey(part)) {
      getActionHandlers().put(part, new CapellaGlobalActionHandler());

      // Register a dispose listener
      part.getSite().getPage().addPartListener(disposeListener(context));
    }
    return getActionHandlers().get(part);
  }

  private Map<IWorkbenchPart, IGlobalActionHandler> getActionHandlers() {
    return actionHandlers;
  }

  /**
   * A dispose listener, that will clear the associated action handler when the part is disposed
   * 
   * @param context
   *          the initial context
   * @return a dispose listener, that will clear the associated action handler when the part is disposed
   */
  private IPartListener disposeListener(final IGlobalActionHandlerContext context) {
    return new IPartListener() {

      private IWorkbenchPart initialPart = context.getActivePart();

      @Override
      public void partClosed(IWorkbenchPart part) {
        if (part != null && part == initialPart && getActionHandlers().containsKey(part)) {
          getActionHandlers().remove(part);
          initialPart.getSite().getPage().removePartListener(this);
          initialPart = null;
        }
      }

      @Override
      public void partActivated(IWorkbenchPart part) {
        // not used
      }

      @Override
      public void partBroughtToTop(IWorkbenchPart part) {
        // not used
      }

      @Override
      public void partDeactivated(IWorkbenchPart part) {
        // not used
      }

      @Override
      public void partOpened(IWorkbenchPart part) {
        // not used
      }
    };
  }

}
