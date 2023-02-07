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
package org.polarsys.capella.core.sirius.ui.handlers;

import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.polarsys.capella.core.sirius.ui.helper.SelectionHelper;

public class SelectElementsOfSameTypeCommandHandler extends AbstractSelectInEditorCommandHandler {

  @Override
  protected Collection<? extends DRepresentationElement> getElementsToSelect() {
    IStructuredSelection selection = getSelection();
    return SelectionHelper.eINSTANCE.getElementsOfSameType(selection);
  }
}
