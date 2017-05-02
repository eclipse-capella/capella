/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.common.ui.toolkit.viewers.AbstractContextMenuFiller;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;
import org.polarsys.capella.core.ui.toolkit.dialogs.ImpactAnalysisDialog;

/**
 * Show a dialog that displays the impact analysis results for current selection.<br>
 * Single selection is taken into account.
 */
public class ImpactAnalysisAction extends BaseSelectionListenerAction {
  /**
   * Constructor.
   * @param site_p
   */
  public ImpactAnalysisAction() {
    super(Messages.ImpactAnalysisAction_Title);
  }

  /**
   * Create {@link LocateInCapellaExplorerAction} to get the semantic element a selection.
   * @return
   */
  protected LocateInCapellaExplorerAction createLocateInCapellaExplorerAction() {
    LocateInCapellaExplorerAction action = new LocateInCapellaExplorerAction();
    return action;
  }

  /**
   * {@inheritDoc}
   */
  public void init(IWorkbenchPart view_p) {
    // Do nothing.
  }

  /**
   * Called from the Capella Explorer.
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // Get the selected model element.
    final EObject modelElement = (EObject) getStructuredSelection().getFirstElement();
    List<EObject> referencingElements = CrossReferencerHelper.getReferencingElements(modelElement);
    // Add representations that reference selected element.
    referencingElements.addAll(RepresentationHelper.getAllRepresentationDescriptorsTargetedBy(Collections.singletonList(modelElement)));
    // Create a formatted message.
    String formattedMessage =
        StringHelper.formatMessage(Messages.ImpactAnalysisDialog_Message, new String[] { EObjectLabelProviderHelper.getText(modelElement) });
    // Create the impact analysis dialog.
    ImpactAnalysisDialog dialog = new ImpactAnalysisDialog(referencingElements, Messages.ImpactAnalysisDialog_Title, formattedMessage);
    dialog.setContextMenuManagerFiller(new AbstractContextMenuFiller() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void fillMenuManager(IMenuManager contextMenuManager_p, final ISelection selection_p) {
        final LocateInCapellaExplorerAction selectInExplorerAction = new LocateInCapellaExplorerAction() {
          /**
           * {@inheritDoc}
           */
          @Override
          protected ISelection getSelection() {
            return selection_p;
          }
        };
        IAction action = new Action() {
          /**
           * {@inheritDoc}
           */
          @Override
          public void run() {
            selectInExplorerAction.run(this);
          }
        };
        // Ignore workbench part site, since in a dialog, site has no meaning.
        selectInExplorerAction.shouldIgnoreWorkbenchPartSite(true);
        action.setText(Messages.ImpactAnalysisAction_ShowInCapellaExplorer_Title);
        action.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_SHOW_IN_CAPELLA_EXPLORER));
        selectInExplorerAction.selectionChanged(action, selection_p);
        if (action.isEnabled()) {
          contextMenuManager_p.add(action);
        }
      }
    });
    // Open it.
    dialog.open();
  }

  /**
   * Called from diagrams or semantic browser for instance.
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action_p) {
    run();
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
   */
  public void selectionChanged(IAction action_p, ISelection selection_p) {
    boolean enabled = selectionChanged(selection_p);
    action_p.setEnabled(enabled);
  }

  /**
   * Selection Changed.
   * @param selection_p
   * @return <code>true</code> means action is avalaible.
   */
  public boolean selectionChanged(ISelection selection_p) {
    // Re-use the code in LocateInCapellaExplorerAction to get the semantic element in specified selection.
    LocateInCapellaExplorerAction action = createLocateInCapellaExplorerAction();
    EObject element = CapellaAdapterHelper.resolveSemanticObject(action.getFirstSelectedElement(selection_p), true);
    boolean enabled = false;
    if ((null != element) && (CapellaResourceHelper.isSemanticElement(element))) {
      enabled = true;
      // Forward the selection.
      selectionChanged(new StructuredSelection(element));
    }
    return enabled;
  }

  /**
   * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
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
