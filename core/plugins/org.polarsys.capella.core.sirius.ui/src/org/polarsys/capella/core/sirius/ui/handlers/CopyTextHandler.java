/*******************************************************************************
 * Copyright (c) 2021, 2023 THALES GLOBAL SERVICES.
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

import java.util.Arrays;
import java.util.stream.Collectors;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
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
  public String getSelectionAsText() {
    String result = null;
    IStructuredSelection selection = getSelection();
    if (!selection.isEmpty()) {
      result = Arrays.stream(selection.toArray()).map(CopyTextHandler::getLabel)
          .collect(Collectors.joining(ICommonConstants.LINE_SEPARATOR));
    }
    return result;
  }
  
  private static String getLabel(Object o) {
    Object adaptedObject = Adapters.adapt(o, EObject.class);
    if (adaptedObject != null) {
      o = adaptedObject;
    }
    return o != null ? EObjectLabelProviderHelper.getText(o) : null;
  }

  @Override
  public boolean isEnabled() {
    return getSelection().size() != 0;
  }
}
