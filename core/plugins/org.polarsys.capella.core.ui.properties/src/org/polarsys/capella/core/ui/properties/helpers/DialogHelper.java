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
package org.polarsys.capella.core.ui.properties.helpers;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.swt.widgets.Control;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.providers.CapellaTransfertViewerLabelProvider;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;

public class DialogHelper {

  /**
   * Open simple selection dialog.<br>
   * @param control
   * @param list
   * @return
   */
  public static EObject openSimpleSelectionDialog(Control control, Collection<? extends EObject> list) {
    boolean expandViewer = CapellaUIPropertiesPlugin.getDefault().isAllowedExpandSingleViewerContent();
    CapellaTransfertViewerLabelProvider labelProvider = new CapellaTransfertViewerLabelProvider();
    return SelectionDialogHelper.simplePropertySelectionDialogWizard(list, labelProvider,control.getShell(), expandViewer ? AbstractTreeViewer.ALL_LEVELS : 0);
  }

  /**
   * Open multiple selection dialog.<br>
   * @param control
   * @param list
   * @return
   */
  public static Collection<? extends EObject> openMultiSelectionDialog(Control control, Collection<? extends EObject> list) {
    boolean expandViewer = CapellaUIPropertiesPlugin.getDefault().isAllowedExpandSingleViewerContent();
    CapellaTransfertViewerLabelProvider labelProvider = new CapellaTransfertViewerLabelProvider();
    return SelectionDialogHelper.simplePropertiesSelectionDialogWizard(list, labelProvider, control.getShell(), expandViewer ? AbstractTreeViewer.ALL_LEVELS : 0);
  }

  /**
   * Open standard transfer dialog.<br>
   * @param control
   * @param currentElements
   * @param availableElements
   * @param title
   * @param message
   * @return
   */
  public static List<EObject> openTransferDialog(Control control, List<EObject> currentElements, List<EObject> availableElements, String title, String message) {
    boolean expandLeftViewer = CapellaUIPropertiesPlugin.getDefault().isAllowedExpandLeftViewerContent();
    boolean expandRightViewer = CapellaUIPropertiesPlugin.getDefault().isAllowedExpandRightViewerContent();
	DataLabelProvider leftLabelProvider =  new CapellaTransfertViewerLabelProvider();
	DataLabelProvider rightLabelProvider =  new CapellaTransfertViewerLabelProvider();

    return SelectionDialogHelper.multiplePropertyTransfertDialogWizard(
      control.getShell(), title, message, availableElements, currentElements, leftLabelProvider, rightLabelProvider,
      expandLeftViewer ? AbstractTreeViewer.ALL_LEVELS : 0, expandRightViewer ? AbstractTreeViewer.ALL_LEVELS : 0);
  }
}
