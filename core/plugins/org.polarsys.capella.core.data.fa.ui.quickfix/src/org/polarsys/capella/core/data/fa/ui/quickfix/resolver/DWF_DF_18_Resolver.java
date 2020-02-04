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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.fa.properties.controllers.SequenceLinkLinksController;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * DWF_DF_18 - SequenceLink with no associated FunctionalChainInvolvementLinks
 */
public class DWF_DF_18_Resolver extends AbstractCapellaMarkerResolution {
  private final String PROCESS_ICON = "icons/full/obj16/capella_process.gif";
  private String label;

  public DWF_DF_18_Resolver(String label) {
    this.label = label;
    super.setContributorId(CapellaUIResourcesPlugin.PLUGIN_ID);
    super.setImgKey(PROCESS_ICON);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);

    if (!modelElements.isEmpty()) {
      if (modelElements.get(0) instanceof SequenceLink) {
        openSelectionDialog((SequenceLink) modelElements.get(0), marker);
      }
    }
  }

  protected void openSelectionDialog(SequenceLink semanticElement, IMarker marker) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        SequenceLinkLinksController controller = new SequenceLinkLinksController();
        EReference semanticFeature = FaPackage.eINSTANCE.getSequenceLink_Links();

        // get available and current elements
        List<EObject> currentElements = controller.readOpenValues(semanticElement, semanticFeature, false);
        List<EObject> availableElements = controller.readOpenValues(semanticElement, semanticFeature, true);
        availableElements.removeAll(currentElements);

        String title = NamingHelper.getDefaultTitle(semanticElement);
        String message = NamingHelper.getDefaultMessage(semanticElement,
            (semanticFeature != null) ? semanticFeature.getName() : "");

        // calling selection wizard
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        List<EObject> allResults = SelectionDialogHelper.multiplePropertyTransfertDialogWizard(shell, title, message,
            availableElements, currentElements);
        if (null != allResults) {
          // write the choice of links
          controller.writeOpenValues(semanticElement, semanticFeature, allResults);
          deleteMarker(marker);
        }
      }
    };
    TransactionHelper.getExecutionManager(semanticElement).execute(command);
  }

  @Override
  protected void deleteMarker(IMarker marker) {
    // delete marker
    try {
      marker.delete();
    } catch (CoreException exception) {
      StatusManager.getManager().handle(
          new Status(IStatus.ERROR, PluginActivator.getDefault().getPluginId(), exception.getMessage(), exception));
    }
  }

  @Override
  public String getLabel() {
    return label;
  }
}
