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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;
import org.polarsys.capella.core.sirius.analysis.accelerators.SelectOrCreateFunctionalExchangeDialog;
import org.polarsys.capella.core.sirius.analysis.accelerators.SelectOrCreateFunctionalExchangeDialog.NewFEData;

public class SelectOrCreateFunctionalExchangeWizard extends AbstractExternalJavaAction {

  // Many variables have been made global to allow reuse inside various component methods of this accelerator
  protected FunctionalChainServices fcsInstance = FunctionalChainServices.getFunctionalChainServices();

  protected DEdge seqLinkEdge;

  protected List<DNode> availableSourceFCIFViews;
  protected List<DNode> availableTargetFCIFViews;

  protected Set<AbstractFunction> availableSourceFunctions;
  protected Set<AbstractFunction> availableTargetFunctions;

  protected Shell currentShell;

  @Override
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

    EObject context = (EObject) parameters.get(CONTEXT);
    this.seqLinkEdge = (DEdge) context;

    currentShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    String messageDialogTitle = "Accelerator Information";

    this.availableSourceFCIFViews = fcsInstance.findFlatClosestFCIFunctionViewsAsSource(seqLinkEdge, true);
    if (availableSourceFCIFViews.isEmpty()) {
      MessageDialog.openInformation(currentShell, messageDialogTitle,
          "There is not any Functional Chain Involvement Function as source for the selected sequence link or they are all in collapsed container.");
      return;
    }

    this.availableTargetFCIFViews = fcsInstance.findFlatClosestFCIFunctionViewsAsTarget(seqLinkEdge, true);
    if (availableTargetFCIFViews.isEmpty()) {
      MessageDialog.openInformation(currentShell, messageDialogTitle,
          "There is not any Functional Chain Involvement Function as target for the selected sequence link or they are all in collapsed container.");
      return;
    }

    availableSourceFunctions = fcsInstance.getFunctionsFromFCIFDNodes(availableSourceFCIFViews);
    availableTargetFunctions = fcsInstance.getFunctionsFromFCIFDNodes(availableTargetFCIFViews);

    Set<FunctionalExchange> availableFEs = new HashSet<>();

    for (AbstractFunction sourceFunction : availableSourceFunctions) {
      for (AbstractFunction targetFunction : availableTargetFunctions) {
        List<FunctionalExchange> commonEdges = FunctionExt.getOutGoingExchange(sourceFunction);
        commonEdges.retainAll(FunctionExt.getIncomingExchange(targetFunction));

        availableFEs.addAll(commonEdges);
      }
    }

    selectOrCreateFunctionalExchangeData(availableFEs);
  }

  protected void selectOrCreateFunctionalExchangeData(Set<FunctionalExchange> availableFEs) {

    SelectOrCreateFunctionalExchangeDialog dialog = new SelectOrCreateFunctionalExchangeDialog(this.currentShell, availableFEs, availableSourceFunctions, availableTargetFunctions);
    int returnCode = dialog.open();

    NewFEData newFEData = null;
    AbstractFunction feSource = null;
    AbstractFunction feTarget = null;
    FunctionalExchange involvedFE = null;

    if (returnCode == SelectOrCreateFunctionalExchangeDialog.CREATION) {
      newFEData = dialog.getCreation();
      feSource = newFEData.getSource();
      feTarget = newFEData.getTarget();
    }

    if (returnCode == SelectOrCreateFunctionalExchangeDialog.SELECTION) {
      involvedFE = dialog.getSelection().stream().findFirst().orElse(null);
      feSource = FunctionalExchangeExt.getSourceFunction(involvedFE);
      feTarget = FunctionalExchangeExt.getTargetFunction(involvedFE);
    }

    createFunctionalExchange(newFEData, feSource, feTarget, involvedFE);
  }

  protected void createFunctionalExchange(NewFEData newFEData, AbstractFunction feSource, AbstractFunction feTarget,
      FunctionalExchange involvedFE) {

    String messageDialogTitle = "Accelerator Information";

    List<DNode> possibleSourceFCIFNodes = fcsInstance.getFCIFViewsInvolvingFunction(availableSourceFCIFViews, feSource);
    List<DNode> possibleTargetFCIFNodes = fcsInstance.getFCIFViewsInvolvingFunction(availableTargetFCIFViews, feTarget);

    int sourceSize = possibleSourceFCIFNodes.size();
    int targetSize = possibleTargetFCIFNodes.size();
    if (sourceSize > 1 || targetSize > 1) {
      MessageDialog.openInformation(currentShell, messageDialogTitle,
          "Impossible to create Functional Chain Involvement Link due to ambiguity of source and target");
    }

    if (sourceSize == 1 && targetSize == 1) {
      if (newFEData != null) {
        involvedFE = FunctionalExchangeExt.createFunctionalExchange(feSource, feTarget);
        involvedFE.setName(newFEData.getName());
        ((AbstractFunction) feSource.eContainer()).getOwnedFunctionalExchanges().add(involvedFE);
      }
      DNode sourceFCIFNode = possibleSourceFCIFNodes.get(0);
      DNode targetFCIFNode = possibleTargetFCIFNodes.get(0);
      fcsInstance.createFCILink(sourceFCIFNode, targetFCIFNode, involvedFE, seqLinkEdge);
    }
  }
}
