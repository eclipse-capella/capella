/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.ui.toolkit.dialogs.ImpactAnalysisDialog;

/**
 * Show a dialog that displays the impact analysis results for current selection.<br>
 * Single selection is taken into account.
 */
public class ImpactAnalysisAction extends BaseSelectionListenerAction {
  /**
   * Constructor.
   * 
   * @param site_p
   */
  public ImpactAnalysisAction() {
    super(Messages.ImpactAnalysisAction_Title);
  }

  /**
   * {@inheritDoc}
   */
  public void init(IWorkbenchPart view_p) {
    // Do nothing.
  }

  /**
   * Called from the Capella Explorer.
   * 
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // Get the selected model element.
    final EObject modelElement = (EObject) getStructuredSelection().getFirstElement();
    List<EObject> referencingElements = CrossReferencerHelper.getReferencingElements(modelElement);
    // Add representations that reference selected element.
    referencingElements.addAll(
        RepresentationHelper.getAllRepresentationDescriptorsTargetedBy(Collections.singletonList(modelElement)));
    // Create a formatted message.
    String formattedMessage = StringHelper.formatMessage(Messages.ImpactAnalysisDialog_Message,
        new String[] { EObjectLabelProviderHelper.getText(modelElement) });
    // Create the impact analysis dialog.
    ImpactAnalysisDialog dialog = new ImpactAnalysisDialog(referencingElements, Messages.ImpactAnalysisDialog_Title,
        formattedMessage);

    // Open it.
    dialog.open();
  }

  /**
   * Called from diagrams or semantic browser for instance.
   * 
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action_p) {
    run();
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  public void selectionChanged(IAction action_p, ISelection selection_p) {
    boolean enabled = selectionChanged(selection_p);
    action_p.setEnabled(enabled);
  }

  /**
   * Selection Changed.
   * 
   * @param selection_p
   * @return <code>true</code> means action is avalaible.
   */
  public boolean selectionChanged(ISelection selection_p) {
    // Re-use the code in LocateInCapellaExplorerAction to get the semantic element in specified selection.
    IStructuredSelection selection = (IStructuredSelection) selection_p;
    boolean enabled = false;
    if (!selection.isEmpty()) {
      EObject element = CapellaAdapterHelper.resolveBusinessObject(selection.getFirstElement());
      if ((null != element) && (CapellaResourceHelper.isSemanticElement(element))) {
        enabled = true;
        // Forward the selection.
        selectionChanged(new StructuredSelection(element));
      }
    }
    return enabled;
  }

  /**
   * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
   *      org.eclipse.ui.IWorkbenchPart)
   */
  public void setActivePart(IAction action_p, IWorkbenchPart targetPart_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.ui.actions.BaseSelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection_p) {
    boolean result = false;
    if ((selection_p != null) && !selection_p.isEmpty()) {
      result = CapellaResourceHelper.isSemanticElements(selection_p.toList()) ? true : false;
    }
    return result;
  }
}
