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
package org.polarsys.capella.core.sirius.ui;

import java.util.Hashtable;

import org.eclipse.gmf.runtime.common.ui.services.action.global.AbstractGlobalActionHandlerProvider;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandler;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandlerContext;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandlerProvider;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

public class CapellaGlobalActionHandlerProvider extends AbstractGlobalActionHandlerProvider
implements
		IGlobalActionHandlerProvider {
	

    /**
     * List for handlers.
     */
    private Hashtable<IWorkbenchPart, IGlobalActionHandler> handlerList = new Hashtable<IWorkbenchPart, IGlobalActionHandler>();



	public IGlobalActionHandler getGlobalActionHandler(
			IGlobalActionHandlerContext context) {
        final IWorkbenchPart part = context.getActivePart();
        /* Create the handler */
        if (!getHandlerList().containsKey(part)) {
            getHandlerList().put(part, new CapellaGlobalActionHandler());
            /*
             * Register as a part listener  so that the cache can be cleared when
             * the part is disposed
             */
            part.getSite().getPage().addPartListener(initListener(context));
        }
        return (IGlobalActionHandler) getHandlerList().get(part);	
        }

	/**
	 * Returns the handlerList.
	 * 
	 * @return Hashtable
	 */
	private Hashtable<IWorkbenchPart, IGlobalActionHandler> getHandlerList() {
	    return handlerList;
	}
	
	   /**
     * Initialize a listener removing the GlobalAction on closing the given part
     * 
     * @param context
     *            the context to clean
     * @return the expected listener
     */
    private IPartListener initListener(final IGlobalActionHandlerContext context) {
        return new IPartListener() {

            private IWorkbenchPart localPart = context.getActivePart();

            /**
             * @see org.eclipse.ui.IPartListener#partActivated(IWorkbenchPart)
             */
            public void partActivated(IWorkbenchPart part) {
            }

            /**
             * @see org.eclipse.ui.IPartListener#partBroughtToTop(IWorkbenchPart)
             */
            public void partBroughtToTop(IWorkbenchPart part) {
            }

            /**
             * @see org.eclipse.ui.IPartListener#partClosed(IWorkbenchPart)
             */
            public void partClosed(IWorkbenchPart part) {
                /* Remove the cache associated with the part */
                if (part != null && part == localPart && getHandlerList().containsKey(part)) {
                    getHandlerList().remove(part);
                    localPart.getSite().getPage().removePartListener(this);
                    localPart = null;
                }
            }

            /**
             * @see org.eclipse.ui.IPartListener#partDeactivated(IWorkbenchPart)
             */
            public void partDeactivated(IWorkbenchPart part) {
            }

            /**
             * @see org.eclipse.ui.IPartListener#partOpened(IWorkbenchPart)
             */
            public void partOpened(IWorkbenchPart part) {
            }
        };
    }

}

