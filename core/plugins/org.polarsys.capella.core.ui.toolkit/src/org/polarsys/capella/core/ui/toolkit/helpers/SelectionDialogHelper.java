/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.toolkit.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.capella.common.ui.toolkit.dialogs.OrderedTransferTreeListDialog;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 */
public class SelectionDialogHelper {
  /**
   * Open selection wizard for given list of element
   * @param list_p : list of selection-able elements
   * @param shell_p : current shell
   * @param multiplicity_p : allows multiple selection or not
   * @param treeViewerExpandLevel_p
   * @return EObject : selected element from wizard (value can be null)
   */
  private static SelectElementsDialog getSelectionDialogWizard(Collection<? extends EObject> list_p, Shell shell_p, boolean multiplicity_p,
      int treeViewerExpandLevel_p) {
    // Displayed elements.
    Collection<? extends EObject> elements = (null != list_p) ? list_p : new ArrayList<EObject>(0);
    // call wizard
    SelectElementsDialog selectionDialog =
        new SelectElementsDialog(shell_p, MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory(),
            Messages.SelectionDialogHelper_SelectionWizard_Title, ICommonConstants.EMPTY_STRING, elements, multiplicity_p, null, treeViewerExpandLevel_p);
    return selectionDialog;
  }

  /**
   * Open selection wizard for given list of elements
   * @param list_p : list of selection-able elements
   * @param shell_p : current shell
   * @return Collection<EObject> : selected elements from wizard (value can be null)
   */
  public static Collection<? extends EObject> simplePropertiesSelectionDialogWizard(Collection<EObject> list_p, Shell shell_p) {
    return simplePropertiesSelectionDialogWizard(list_p, shell_p, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Open selection wizard for given list of elements
   * @param list_p : list of selection-able elements
   * @param shell_p : current shell
   * @param treeViewerExpandLevel_p
   * @return Collection<EObject> : selected elements from wizard (value can be null)
   */
  public static Collection<? extends EObject> simplePropertiesSelectionDialogWizard(Collection<? extends EObject> list_p, Shell shell_p,
      int treeViewerExpandLevel_p) {
    SelectElementsDialog selectionDialog = getSelectionDialogWizard(list_p, shell_p, true, treeViewerExpandLevel_p);
    if (Window.OK == selectionDialog.open()) {
      return selectionDialog.getResult();
    }
    return null;
  }

  /**
   * Open selection wizard for given list of elements
   * @param list_p : list of selection-able elements
   * @param shell_p : current shell
   * @return EObject : selected element from wizard (value can be null)
   */
  public static EObject simplePropertySelectionDialogWizard(List<EObject> list_p, Shell shell_p) {
    return simplePropertySelectionDialogWizard(list_p, shell_p, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Open selection wizard for given list of elements
   * @param list_p : list of selection-able elements
   * @param shell_p : current shell
   * @param treeViewerExpandLevel_p
   * @return EObject : selected element from wizard (value can be null)
   */
  public static EObject simplePropertySelectionDialogWizard(Collection<? extends EObject> list_p, Shell shell_p, int treeViewerExpandLevel_p) {
    SelectElementsDialog selectionDialog = getSelectionDialogWizard(list_p, shell_p, false, treeViewerExpandLevel_p);
    if (Window.OK == selectionDialog.open()) {
      List<? extends EObject> result = selectionDialog.getResult();
      if (!result.isEmpty()) {
        return result.get(0);
      }
    }
    return null;
  }

  /**
   * Open selection wizard for given list of element
   * @param shell_p
   * @param title_p
   * @param message_p
   * @param leftViewersource
   * @param rightViewersource
   * @param leftLabelProvider_p
   * @param rightLabelProvider_p
   */
  public static List<EObject> multiplePropertyTransfertDialogWizard(Shell shell_p, String title_p, String message_p, List<EObject> leftViewersource,
      List<EObject> rightViewersource, DataLabelProvider leftLabelProvider_p, DataLabelProvider rightLabelProvider_p) {
    return multiplePropertyTransfertDialogWizard(shell_p, title_p, message_p, leftViewersource, rightViewersource, leftLabelProvider_p, rightLabelProvider_p,
        AbstractTreeViewer.ALL_LEVELS, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Open selection wizard for given list of element
   * @param shell_p
   * @param title_p
   * @param message_p
   * @param leftViewersource
   * @param rightViewersource
   * @param leftLabelProvider_p
   * @param rightLabelProvider_p
   * @param leftViewerExpandLevel
   * @param rightViewerExpandLevel
   */
  public static List<EObject> multiplePropertyTransfertDialogWizard(Shell shell_p, String title_p, String message_p, List<EObject> leftViewersource,
      List<EObject> rightViewersource, DataLabelProvider leftLabelProvider_p, DataLabelProvider rightLabelProvider_p, int leftViewerExpandLevel_p,
      int rightViewerExpandLevel_p) {
    TransferTreeListDialog dialog =
        new TransferTreeListDialog(shell_p, title_p, message_p, leftLabelProvider_p, rightLabelProvider_p, leftViewerExpandLevel_p, rightViewerExpandLevel_p);
    dialog.setLeftInput(leftViewersource, null /* no context */);
    dialog.setRightInput(rightViewersource, null /* no context */);
    if (Window.OK == dialog.open()) {
      Object[] result = dialog.getResult();
      List<EObject> returnedSelectedElements = new ArrayList<EObject>(result.length);
      for (Object object : result) {
        returnedSelectedElements.add((EObject) object);
      }
      return returnedSelectedElements;
    }
    return null;
  }

  /**
   * Open selection wizard with a list of elements displayed on the left, and a list of elements displayed on the right
   * @param shell_p : current shell
   * @param title_p : the dialog title
   * @param message_p : the dialog message
   * @param leftViewerSource_p: the elements for the left part of the viewer
   * @param rightViewerSource_p: the elements for the right part of the viewer
   * @return The list of selected elements from the wizard, or null if the dialog was canceled
   */
  public static List<EObject> multiplePropertyTransfertDialogWizard(Shell shell_p, String title_p, String message_p, List<EObject> leftViewersource,
      List<EObject> rightViewersource) {

    TransferTreeListDialog dialog =
        new TransferTreeListDialog(shell_p, title_p, message_p, MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory());
    dialog.setLeftInput(leftViewersource, null /* no context */);
    dialog.setRightInput(rightViewersource, null /* no context */);
    if (Window.OK == dialog.open()) {
      Object[] result = dialog.getResult();
      List<EObject> returnedSelectedElements = new ArrayList<EObject>(result.length);
      for (Object object : result) {
        returnedSelectedElements.add((EObject) object);
      }
      return returnedSelectedElements;
    }
    return null;
  }

  /**
   * Open an Ordered Transfer Dialog for given elements.
   * @param availableElements_p elements that can be transfered.
   * @param initialSelection_p selected elements at opening time (i.e already transfered).
   * @param shell_p current shell.
   * @param title_p title of the dialog.
   * @param message_p message of the dialog.
   * @return selected elements when 'ok' pressed or <code>null</code> if dialog is canceled.
   */
  public static List<EObject> openOrderedTransferDialog(List<EObject> availableElements_p, List<EObject> initialSelection_p, Shell shell_p, String title_p,
      String message_p) {
    List<EObject> returnedSelectedElements = null;

    OrderedTransferTreeListDialog dialog =
        new OrderedTransferTreeListDialog(shell_p, MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory(), title_p, message_p);
    dialog.setLeftInput(availableElements_p, null /* no context */);
    dialog.setRightInput(initialSelection_p, null /* no context */);
    if (Window.OK == dialog.open()) {
      Object[] result = dialog.getResult();
      returnedSelectedElements = new ArrayList<EObject>(result.length);
      for (Object object : result) {
        returnedSelectedElements.add((EObject) object);
      }
    }
    return returnedSelectedElements;
  }
}
