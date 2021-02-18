/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * This Handler copies the selected result in the Interpreter View as text.
 */
public class CopyTextHandler extends AbstractDiagramCommandHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    String text = getSelectionAsText();
    if (text != null) {
      Clipboard c = new Clipboard(HandlerUtil.getActiveWorkbenchWindow(event).getShell().getDisplay());
      Transfer[] transfers = new Transfer[] { TextTransfer.getInstance() };
      Object[] data = new Object[] { text };
      c.setContents(data, transfers);
      c.dispose();
    }
    return null;
  }

  /**
   * @return the current selection represented as text
   */
  private String getSelectionAsText() {
    StringBuilder builder = new StringBuilder();
    String result = null;
    IStructuredSelection selection = getSelection();
    for (Object o : selection.toArray()) {
      if (o instanceof ModelElement) {
        builder.append(((ModelElement) o).getLabel());
      } else {
        builder.append(o.toString());
      }
      builder.append(ICommonConstants.LINE_SEPARATOR);
    }

    if (builder.length() > 0) {
      result = builder.toString();
    }
    return result;
  }

  @Override
  public boolean isEnabled() {
    return getSelection().size() != 0;
  }
}
