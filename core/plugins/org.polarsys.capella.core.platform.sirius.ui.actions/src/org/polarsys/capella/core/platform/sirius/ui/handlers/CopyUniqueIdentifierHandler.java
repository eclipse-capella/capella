/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.handlers;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.shared.id.handler.IdManager;

public class CopyUniqueIdentifierHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    String allUniqueIds = getSelectionAsText(event);
      if (!allUniqueIds.isBlank()) {
        Clipboard c = new Clipboard(HandlerUtil.getActiveWorkbenchWindow(event).getShell().getDisplay());
        Transfer[] transfers = new Transfer[] { TextTransfer.getInstance() };
        Object[] data = new Object[] { allUniqueIds };
        c.setContents(data, transfers);
        c.dispose();
    }
    return null;
  }

    public String getSelectionAsText(ExecutionEvent event) {
    ISelection selection = HandlerUtil.getCurrentSelection(event);
    if (selection instanceof IStructuredSelection) {
      IStructuredSelection structuredSelection = (IStructuredSelection) selection;
      String allUniqueIds = Arrays.stream(structuredSelection.toArray()).map(this::getUniqueId)
          .collect(Collectors.joining(ICommonConstants.LINE_SEPARATOR));
      return allUniqueIds;
    }
    return ICommonConstants.EMPTY_STRING;
  }

  private String getUniqueId(Object element) {
    String uniqueId = ICommonConstants.EMPTY_STRING;
    EObject object = CapellaAdapterHelper.resolveDescriptorOrBusinessObject(element);
    if (object != null) {
      return IdManager.getInstance().getId(object);
    }
    return uniqueId;
  }
}
