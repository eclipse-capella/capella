/*******************************************************************************
* Copyright (c) 2011 THALES GLOBAL SERVICES.
* 
* This program and the accompanying materials are made available under the
* terms of the Eclipse Public License 2.0 which is available at
* http://www.eclipse.org/legal/epl-2.0
* 
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
*	Obeo - Initial API and implementation
*   Thales - Contributor
*   
*   @see {@link org.eclipse.sirius.diagram.ui.tools.internal.delete.SiriusDeleteGlobalActionHandlerProvider}
*
*******************************************************************************/
package org.polarsys.capella.core.sirius.ui.actions;

import java.util.Hashtable;

import org.eclipse.gmf.runtime.common.ui.services.action.global.AbstractGlobalActionHandlerProvider;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandler;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandlerContext;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Manage the delete action.
 */
public class CapellaSiriusDeleteGlobalActionHandlerProvider extends AbstractGlobalActionHandlerProvider implements IPartListener {

  /**
   * List for handlers.
   */
  private Hashtable<IWorkbenchPart, CapellaSiriusDeleteGlobalActionHandler> handlerList = new Hashtable<IWorkbenchPart, CapellaSiriusDeleteGlobalActionHandler>();

  /**
   * Overridden to provide the {@link CapellaSiriusDeleteGlobalActionHandler}.
   * 
   * {@inheritDoc}
   */
  @Override
  public IGlobalActionHandler getGlobalActionHandler(IGlobalActionHandlerContext context) {
      final IWorkbenchPart part = context.getActivePart();
      /* Create the handler */
      if (!getHandlerList().containsKey(part)) {
          getHandlerList().put(part, new CapellaSiriusDeleteGlobalActionHandler());
          /*
           * Register as a part listener so that the cache can be cleared when
           * the part is disposed
           */
          part.getSite().getPage().addPartListener(this);
      }
      return getHandlerList().get(part);
  }

  /**
   * Returns the handlerList.
   * 
   * @return Hashtable
   */
  private Hashtable<IWorkbenchPart, CapellaSiriusDeleteGlobalActionHandler> getHandlerList() {
      return handlerList;
  }

  /**
   * {@inheritDoc}
   */
  public void partActivated(IWorkbenchPart part) {
    /*do nothing*/
  }

  /**
   * {@inheritDoc}
   */
  public void partBroughtToTop(IWorkbenchPart part) {
    /*do nothing*/
  }

  /**
   * Overridden to dispose the {@link IPartListener}.
   * 
   * {@inheritDoc}
   */
  public void partClosed(IWorkbenchPart part) {
      if (part != null && getHandlerList().containsKey(part)) {
          getHandlerList().remove(part);
      }
  }

  /**
   * {@inheritDoc}
   */
  public void partDeactivated(IWorkbenchPart part) {
    /* do nothing */
  }

  /**
   * {@inheritDoc}
   */
  public void partOpened(IWorkbenchPart part) {
    /* do nothing */
  }
}
