/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.accelerators;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;

public class AcceleratorOnFCILinkHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ISelection selection = HandlerUtil.getActiveMenuSelection(event);
    List<FunctionalChainInvolvementLink> fciLinks = getFCILinksFromSelection(selection);

    if (!fciLinks.isEmpty()) {
      ExecutionManager manager = TransactionHelper.getExecutionManager(fciLinks);

      if (manager != null) {
        manager.execute(new AbstractReadWriteCommand() {
          @Override
          public void run() {
            fciLinks.stream().forEach(FunctionalChainExt::createSequenceLink);

            IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
            DiagramServices.getDiagramServices().refreshRepresentationOfEditor(activeEditor);
          }
        });
      }
    }

    return null;
  }

  private List<FunctionalChainInvolvementLink> getFCILinksFromSelection(ISelection selection) {
    if (selection instanceof StructuredSelection) {
      StructuredSelection structuredSelection = (StructuredSelection) selection;

      try (Stream<Object> stream = Stream.of(structuredSelection.toArray())) {
        return stream.map(ModelAdaptation::adaptToCapella) //
            .filter(FunctionalChainInvolvementLink.class::isInstance) //
            .map(FunctionalChainInvolvementLink.class::cast) //
            .filter(link -> link.getInvolved() instanceof FunctionalExchange) //
            .collect(Collectors.toList());
      }

    }
    return Collections.emptyList();
  }

  @Override
  public boolean isEnabled() {
    IWorkbench workbench = PlatformUI.getWorkbench();
    IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
    IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
    ISelection selection = activePage.getSelection();

    return !getFCILinksFromSelection(selection).isEmpty();
  }
}
