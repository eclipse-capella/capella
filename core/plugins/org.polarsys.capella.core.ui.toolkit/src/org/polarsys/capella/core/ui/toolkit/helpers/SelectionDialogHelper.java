/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.toolkit.dialogs.OrderedTransferTreeListDialog;
import org.polarsys.capella.common.ui.toolkit.dialogs.OrderedUniqueTransferTreeListDialog;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.model.utils.CollectionExt;

/**
 */
public class SelectionDialogHelper {
  /**
   * Open selection wizard for given list of element
   * @param list : list of selection-able elements
   * @param shell : current shell
   * @param multiplicity : allows multiple selection or not
   * @param treeViewerExpandLevel
   * @return EObject : selected element from wizard (value can be null)
   */
  private static SelectElementsDialog getSelectionDialogWizard(Collection<? extends EObject> list, Shell shell, boolean multiplicity,
      int treeViewerExpandLevel) {
    // Displayed elements.
    Collection<? extends EObject> elements = (null != list) ? list : new ArrayList<EObject>(0);
    // call wizard
    SelectElementsDialog selectionDialog =
        new SelectElementsDialog(shell, TransactionHelper.getEditingDomain(elements), CapellaAdapterFactoryProvider.getInstance().getAdapterFactory(),
            Messages.SelectionDialogHelper_SelectionWizard_Title, ICommonConstants.EMPTY_STRING, elements, multiplicity, null, treeViewerExpandLevel);
    return selectionDialog;
  }

  /**
   * Open selection wizard for given list of elements
   * @param list : list of selection-able elements
   * @param shell : current shell
   * @return Collection<EObject> : selected elements from wizard (value can be null)
   */
  public static Collection<? extends EObject> simplePropertiesSelectionDialogWizard(Collection<EObject> list, Shell shell) {
    return simplePropertiesSelectionDialogWizard(list, shell, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Open selection wizard for given list of elements
   * @param list : list of selection-able elements
   * @param shell : current shell
   * @param treeViewerExpandLevel
   * @return Collection<EObject> : selected elements from wizard (value can be null)
   */
  public static Collection<? extends EObject> simplePropertiesSelectionDialogWizard(Collection<? extends EObject> list, Shell shell,
      int treeViewerExpandLevel) {
    SelectElementsDialog selectionDialog = getSelectionDialogWizard(list, shell, true, treeViewerExpandLevel);
    if (Window.OK == selectionDialog.open()) {
      return selectionDialog.getResult();
    }
    return null;
  }

  /**
   * Open selection wizard for given list of elements
   * @param list : list of selection-able elements
   * @param shell : current shell
   * @return EObject : selected element from wizard (value can be null)
   */
  public static EObject simplePropertySelectionDialogWizard(List<EObject> list, Shell shell) {
    return simplePropertySelectionDialogWizard(list, shell, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Open selection wizard for given list of elements
   * @param list : list of selection-able elements
   * @param shell : current shell
   * @param treeViewerExpandLevel
   * @return EObject : selected element from wizard (value can be null)
   */
  public static EObject simplePropertySelectionDialogWizard(Collection<? extends EObject> list, Shell shell, int treeViewerExpandLevel) {
    SelectElementsDialog selectionDialog = getSelectionDialogWizard(list, shell, false, treeViewerExpandLevel);
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
//  public static List<EObject> multiplePropertyTransfertDialogWizard(Shell shell_p, String title_p, String message_p, List<EObject> leftViewersource,
//      List<EObject> rightViewersource, DataLabelProvider leftLabelProvider_p, DataLabelProvider rightLabelProvider_p) {
//    return multiplePropertyTransfertDialogWizard(shell_p, title_p, message_p, leftViewersource, rightViewersource, leftLabelProvider_p, rightLabelProvider_p,
//        AbstractTreeViewer.ALL_LEVELS, AbstractTreeViewer.ALL_LEVELS);
//  }

  /**
   * Open selection wizard for given list of element
   * @param shell
   * @param title
   * @param message
   * @param leftViewersource
   * @param rightViewersource
   * @param leftLabelProvider
   * @param rightLabelProvider
   * @param leftViewerExpandLevel
   * @param rightViewerExpandLevel
   */
  public static List<EObject> multiplePropertyTransfertDialogWizard(Shell shell, String title, String message, List<EObject> leftViewersource,
      List<EObject> rightViewersource, DataLabelProvider leftLabelProvider, DataLabelProvider rightLabelProvider, int leftViewerExpandLevel,
      int rightViewerExpandLevel) {
    TransferTreeListDialog dialog =
        new TransferTreeListDialog(shell, title, message, leftLabelProvider, rightLabelProvider, leftViewerExpandLevel, rightViewerExpandLevel);
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
   * @param shell : current shell
   * @param title : the dialog title
   * @param message : the dialog message
   * @param leftViewerSource: the elements for the left part of the viewer
   * @param rightViewerSource: the elements for the right part of the viewer
   * @return The list of selected elements from the wizard, or null if the dialog was canceled
   */
  public static List<EObject> multiplePropertyTransfertDialogWizard(Shell shell, String title, String message, List<EObject> leftViewersource,
      List<EObject> rightViewersource) {

    TransferTreeListDialog dialog =
        new TransferTreeListDialog(shell, title, message,
        	TransactionHelper.getEditingDomain(CollectionExt.mergeCollections(leftViewersource, rightViewersource)),
        	CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
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
   * @param availableElements elements that can be transfered.
   * @param initialSelection selected elements at opening time (i.e already transfered).
   * @param shell current shell.
   * @param title title of the dialog.
   * @param message message of the dialog.
   * @return selected elements when 'ok' pressed or <code>null</code> if dialog is canceled.
   */
  public static List<EObject> openOrderedTransferDialog(List<EObject> availableElements, List<EObject> initialSelection, Shell shell, String title,
      String message) {
    List<EObject> returnedSelectedElements = null;

    OrderedTransferTreeListDialog dialog =
        new OrderedTransferTreeListDialog(shell,
        	TransactionHelper.getEditingDomain(CollectionExt.mergeCollections(availableElements, initialSelection)),
        	CapellaAdapterFactoryProvider.getInstance().getAdapterFactory(), title, message);
    dialog.setLeftInput(availableElements, null /* no context */);
    dialog.setRightInput(initialSelection, null /* no context */);
    if (Window.OK == dialog.open()) {
      Object[] result = dialog.getResult();
      returnedSelectedElements = new ArrayList<EObject>(result.length);
      for (Object object : result) {
        returnedSelectedElements.add((EObject) object);
      }
    }
    return returnedSelectedElements;
  }
  
  /**
   * Open an Ordered Unique Transfer Dialog for given elements.
   * @param availableElements elements that can be transfered.
   * @param initialSelection selected elements at opening time (i.e already transfered).
   * @param shell current shell.
   * @param title title of the dialog.
   * @param message message of the dialog.
   * @return selected elements when 'ok' pressed or <code>null</code> if dialog is canceled.
   */
  public static List<EObject> openOrderedUniqueTransferDialog(List<EObject> availableElements, List<EObject> initialSelection, Shell shell, String title,
      String message) {
    List<EObject> returnedSelectedElements = null;

    OrderedUniqueTransferTreeListDialog dialog =
        new OrderedUniqueTransferTreeListDialog(shell,
          TransactionHelper.getEditingDomain(CollectionExt.mergeCollections(availableElements, initialSelection)),
          CapellaAdapterFactoryProvider.getInstance().getAdapterFactory(), title, message);
    dialog.setLeftInput(availableElements, null /* no context */);
    dialog.setRightInput(initialSelection, null /* no context */);
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
