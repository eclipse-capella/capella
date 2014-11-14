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

import org.eclipse.jface.util.TransferDragSourceListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;

/**
 * A simple drag target adapter for {@link LocalSelectionTransfer}.
 */
public class ModelDragTargetAdapter extends DragSourceAdapter implements TransferDragSourceListener {

   private ISelectionProvider _provider;
    
   /**
    * Construct a new drag target adapter from  the selection provider given as parameter.
    * @param provider_p the selection provider
    */
   public ModelDragTargetAdapter(final ISelectionProvider provider_p) {
       _provider = provider_p;
   }
   
   /**
    * {@inheritDoc}
    *
    * @see org.eclipse.jface.util.TransferDragSourceListener#getTransfer()
    */
    public Transfer getTransfer() {
        return LocalSelectionTransfer.getInstance();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.swt.dnd.DragSourceAdapter#dragStart(org.eclipse.swt.dnd.DragSourceEvent)
     */
    @Override
    public void dragStart(final DragSourceEvent event) {
        final ISelection selection = _provider.getSelection();
        LocalSelectionTransfer.getInstance().setSelection(selection);
        LocalSelectionTransfer.getInstance().setSelectionSetTime(event.time & 0xFFFFFFFFL);
        event.doit = true;
    }
    
    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.swt.dnd.DragSourceAdapter#dragSetData(org.eclipse.swt.dnd.DragSourceEvent)
     */
    @Override
    public void dragSetData(final DragSourceEvent event) {
        event.data = LocalSelectionTransfer.getInstance().getSelection();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.swt.dnd.DragSourceAdapter#dragFinished(org.eclipse.swt.dnd.DragSourceEvent)
     */
    @Override
    public void dragFinished(final DragSourceEvent event) {
        LocalSelectionTransfer.getInstance().setSelection(null);
        LocalSelectionTransfer.getInstance().setSelectionSetTime(0);
    }
}
