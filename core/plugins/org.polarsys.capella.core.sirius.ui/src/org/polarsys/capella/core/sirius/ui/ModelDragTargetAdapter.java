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

import org.eclipse.jface.util.TransferDragSourceListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.jface.util.LocalSelectionTransfer;

/**
 * A simple drag target adapter for {@link LocalSelectionTransfer}.
 */
public class ModelDragTargetAdapter extends DragSourceAdapter implements TransferDragSourceListener {

   private ISelectionProvider provider;
    
   /**
    * Construct a new drag target adapter from  the selection provider given as parameter.
    * @param selectionProvider the selection provider
    */
   public ModelDragTargetAdapter(final ISelectionProvider selectionProvider) {
       provider = selectionProvider;
   }
   
   /**
    * {@inheritDoc}
    *
    * @see org.eclipse.jface.util.TransferDragSourceListener#getTransfer()
    */
    public Transfer getTransfer() {
        return LocalSelectionTransfer.getTransfer();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.swt.dnd.DragSourceAdapter#dragStart(org.eclipse.swt.dnd.DragSourceEvent)
     */
    @Override
    public void dragStart(final DragSourceEvent event) {
        final ISelection selection = provider.getSelection();
        LocalSelectionTransfer.getTransfer().setSelection(selection);
        LocalSelectionTransfer.getTransfer().setSelectionSetTime(event.time & 0xFFFFFFFFL);
        event.doit = true;
    }
    
    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.swt.dnd.DragSourceAdapter#dragSetData(org.eclipse.swt.dnd.DragSourceEvent)
     */
    @Override
    public void dragSetData(final DragSourceEvent event) {
        event.data = LocalSelectionTransfer.getTransfer().getSelection();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.swt.dnd.DragSourceAdapter#dragFinished(org.eclipse.swt.dnd.DragSourceEvent)
     */
    @Override
    public void dragFinished(final DragSourceEvent event) {
        LocalSelectionTransfer.getTransfer().setSelection(null);
        LocalSelectionTransfer.getTransfer().setSelectionSetTime(0);
    }
}
