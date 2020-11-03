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

import java.util.stream.Stream;

import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.tools.internal.clipboard.SiriusClipboardGlobalActionHandler;

@SuppressWarnings("restriction")
public class CapellaGlobalActionHandler extends SiriusClipboardGlobalActionHandler {

  @Override
  public boolean canPaste(IGlobalActionContext context) {
    return super.canPaste(context) && isDiagram(context);
  }

  @Override
  protected boolean canCopy(IGlobalActionContext context) {
    return isNote(context);
  }

  @Override
  protected boolean canCut(IGlobalActionContext context) {
    return super.canCut(context) && isNote(context);
  }

  private boolean isNote(IGlobalActionContext context) {
    ISelection selection = context.getSelection();

    if (selection instanceof IStructuredSelection) {
      IStructuredSelection structuredSelection = (IStructuredSelection) selection;

      if (structuredSelection.isEmpty()) {
        return false;
      }

      try (Stream<Object> stream = Stream.of(structuredSelection.toArray())) {
        return stream.allMatch(entry -> entry instanceof NoteEditPart || entry instanceof TextEditPart);
      }
    }

    return true;
  }

  private boolean isDiagram(IGlobalActionContext context) {
    ISelection selection = context.getSelection();

    if (selection instanceof IStructuredSelection) {
      IStructuredSelection structuredSelection = (IStructuredSelection) selection;

      if (structuredSelection.isEmpty()) {
        return false;
      }

      try (Stream<Object> stream = Stream.of(structuredSelection.toArray())) {
        return stream.allMatch(DiagramEditPart.class::isInstance);
      }
    }

    return true;
  }
}
