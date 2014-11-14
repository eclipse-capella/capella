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

package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.jface.viewers.StructuredViewer;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.model.copypaste.SharedCopyPasteElements;
import org.polarsys.capella.common.model.copypaste.SharedCutPasteClipboard;

/**
 * The Capella command allowing to cut Capella elements.
 */
public class CapellaCutCommand extends CommandWrapper {
  private Collection<?> _selection;
  private StructuredViewer _viewer;
  private Collection<?> _oldClipboard;

  /**
   * Constructs the Capella command allowing to cut Capella elements.
   * @param label_p The label.
   * @param domain_p The editing domain.
   * @param _viewer_p
   * @param parameters_p The parameters.
   * @param command_p The command.
   */
  public CapellaCutCommand(String label_p, Collection<?> selection_p, StructuredViewer viewer_p) {
    super(label_p);
    _selection = selection_p;
    _viewer = viewer_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canUndo() {
    return true;
  }

  /**
   * @see org.eclipse.emf.edit.command.CutToClipboardCommand#execute()
   */
  @Override
  public void execute() {
    SharedCopyPasteElements.getInstance().clear();
    SharedCutPasteClipboard clipboard = SharedCutPasteClipboard.getCutClipboard();
    // Old clipboard data.
    _oldClipboard = clipboard.getClipboard();
    // New clipboard data.
    clipboard.setClipboard(_selection);
    HashSet<Object> objectsToReflesh = new HashSet<Object>(EcoreUtil2.getAllContents(clipboard.getClipboard()));
    if (_oldClipboard != null) {
      objectsToReflesh.addAll(EcoreUtil2.getAllContents(_oldClipboard));
    }
    refreshViewer(objectsToReflesh);
  }

  /**
   * @see org.eclipse.emf.common.command.CommandWrapper#prepare()
   */
  @Override
  protected boolean prepare() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void redo() {
    execute();
  }

  /**
   * @param elementsToUpdate_p
   */
  protected void refreshViewer(final Set<Object> elementsToUpdate_p) {
    _viewer.getControl().getDisplay().asyncExec(new Runnable() {
      @SuppressWarnings("synthetic-access")
      public void run() {
        _viewer.update(elementsToUpdate_p.toArray(), null);
      }
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void undo() {
    HashSet<Object> elementsToUpdate = new HashSet<Object>(0);
    SharedCutPasteClipboard clipboard = SharedCutPasteClipboard.getCutClipboard();
    if (null != _oldClipboard) {
      elementsToUpdate.addAll(EcoreUtil2.getAllContents(_oldClipboard));
    }
    Collection<?> currentClipboard = clipboard.getClipboard();
    if (null != currentClipboard) {
      elementsToUpdate.addAll(EcoreUtil2.getAllContents(currentClipboard));
    }
    clipboard.setClipboard(_oldClipboard);
    refreshViewer(elementsToUpdate);
    _oldClipboard = currentClipboard;
  }
}
