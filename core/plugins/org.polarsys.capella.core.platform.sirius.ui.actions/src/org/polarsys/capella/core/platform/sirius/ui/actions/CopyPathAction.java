/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.actions.AbstractTigAction;

@Deprecated
public class CopyPathAction extends AbstractTigAction implements
		IActionDelegate {

	public void run(IAction action) {
    String allPaths = getSelectionAsText();
    if (!allPaths.isBlank()) {
      Clipboard clipboard = new Clipboard(PlatformUI.getWorkbench().getDisplay());
      clipboard.setContents(new Object[] { allPaths }, new Transfer[] { TextTransfer.getInstance() });
    }

	}

  /**
   * @return the current selection represented as text
   */
  public String getSelectionAsText() {
    String result = null;
    List<ModelElement> elements = getSelectedElements();
    if (!elements.isEmpty()) {
      result = Arrays.stream(elements.toArray()).map(ModelElement.class::cast).map(this::computePathName)
          .collect(Collectors.joining(ICommonConstants.LINE_SEPARATOR));
    }
    return result;
  }

	private String computePathName(ModelElement element) {
		StringBuilder result = new StringBuilder(element.getLabel());
		EObject currentElement = element;
		while (currentElement.eContainer() != null){
			currentElement = currentElement.eContainer();
			if (currentElement instanceof ModelElement) {
				ModelElement me = (ModelElement) currentElement;
				result.insert(0, "::"); //$NON-NLS-1$
				result.insert(0, me.getLabel());				
			}
		}
		return result.toString();
	}
}
