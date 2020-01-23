/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.richtext.handlers;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.ui.properties.richtext.clipboard.RichTextLinksClipboard;

public class CopyAsDescriptionLinkHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    Collection<?> selectedElements = getSelectedElements(event);
    Collection<EObject> descriptorsOrBusinessObjects = CapellaAdapterHelper
        .resolveDescriptorsOrBusinessObjects(selectedElements);

    RichTextLinksClipboard clipboard = RichTextLinksClipboard.getInstance();

    clipboard.clearCopiedElements();
    clipboard.addCopiedElements(descriptorsOrBusinessObjects);

    return null;
  }

  protected Collection<?> getSelectedElements(ExecutionEvent event) {
    ISelection selection = HandlerUtil.getCurrentSelection(event);

    if (selection.isEmpty() || !(selection instanceof IStructuredSelection)) {
      return Collections.emptyList();
    }

    return ((IStructuredSelection) selection).toList();
  }

}
