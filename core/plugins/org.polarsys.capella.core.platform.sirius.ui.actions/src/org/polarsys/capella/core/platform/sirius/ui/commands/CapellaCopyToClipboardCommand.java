/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.Collection;

import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.StructuredViewer;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.model.copypaste.SharedCopyPasteElements;
import org.polarsys.capella.common.model.copypaste.SharedCutPasteClipboard;

/**
 * Copy elements to the clipboard.
 * 
 * @see CapellaCopyCommand
 */
public class CapellaCopyToClipboardCommand extends CopyToClipboardCommand {

  private StructuredViewer _viewer;

  /**
   * @param domain
   * @param collection
   * @param viewer
   */
  public CapellaCopyToClipboardCommand(EditingDomain domain, Collection<?> collection, StructuredViewer viewer) {
    super(domain, collection);
    _viewer = viewer;
  }

  @Override
  protected boolean prepare() {
    copyCommand = CapellaCopyCommand.create(domain, sourceObjects);
    return copyCommand.canExecute();
  }
  
  /**
   * @see org.eclipse.emf.edit.command.CopyCommand#execute()
   */
  @Override
  public void doExecute() {
    
    // If the previous action was a 'cut', the viewer still
    // highlights the cut elements. We want to un-highlight
    // them here.
    Collection<?> clipboard = SharedCutPasteClipboard.getCutClipboard().getClipboard();
    if (null != clipboard) {
      final Collection<?> cutElements = EcoreUtil2.getAllContents(clipboard);
      _viewer.getControl().getDisplay().asyncExec(new Runnable() {
        @SuppressWarnings("synthetic-access")
        public void run() {
          _viewer.update(cutElements.toArray(), null);
        }
      });
    }
    
    // clean up the previous cut/copy operation store
    SharedCutPasteClipboard.getCutClipboard().clear();
    SharedCopyPasteElements.getInstance().clear();
    
    super.doExecute();

    // the copy command that's used internally will magically fill
    // the SharedCopyPasteElements again.
    // @see SharedInitializeCopyCommand
    
  }
  
}
