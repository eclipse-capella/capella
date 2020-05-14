/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.search;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.search.ui.ISearchResultViewPart;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;

public class CapellaSearchResultPageHandlerSelect implements IHandler {

  @Override
  public void addHandlerListener(IHandlerListener handlerListener) {
  }

  @Override
  public void dispose() {
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    CapellaSearchResultPage capellaSearchResultPage = (CapellaSearchResultPage) NewSearchUI.getSearchResultView()
        .getActivePage();
    IStructuredSelection selectionOnOtherView = HandlerUtil.getCurrentStructuredSelection(event);
    Object expectSelected = selectionOnOtherView.getFirstElement();

    if (!(expectSelected instanceof IProject)) {
      // If not IProject, resolve it to the representation descriptor or semantic element.
      expectSelected = CapellaAdapterHelper.resolveDescriptorOrBusinessObject(expectSelected);
    }

    // Perform the selection on the search result page
    capellaSearchResultPage.setSelection(new StructuredSelection(expectSelected));

    IStructuredSelection selectionOnSearchResultPage = (IStructuredSelection) capellaSearchResultPage.getSelection();
    if (selectionOnSearchResultPage != null) {
      Object actualSelected = selectionOnSearchResultPage.getFirstElement();
      if (!expectSelected.equals(actualSelected)) {
        // Show the information dialog if the current selected element is still not the wanted one.
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        String title = CapellaSearchConstants.CapellaSearchDialog_Title;

        String pattern = capellaSearchResultPage.getInput().getQuery().getLabel();
        String message = String.format(CapellaSearchConstants.CapellaSearchDialog_ShowIn_NotFound_Message, pattern);

        MessageDialog.openInformation(shell, title, message);
      } else {
        // Otherwise, get focus so user can work immediately on the wanted elemnt
        capellaSearchResultPage.setFocus();
      }
    }
    return null;
  }

  @Override
  public boolean isEnabled() {
    ISearchResultViewPart searchResultView = NewSearchUI.getSearchResultView();
    return searchResultView != null && searchResultView.getActivePage() instanceof CapellaSearchResultPage;
  }

  @Override
  public boolean isHandled() {
    return true;
  }

  @Override
  public void removeHandlerListener(IHandlerListener handlerListener) {
  }
}
