/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/**
 * Standard action for sorting the content of a list of selected element.
 */
public class SortSelectionAction extends SortContentAction {
  /**
   * Constructor.
   */
  public SortSelectionAction() {
    super(Messages.SortSelectionAction_Label);
  }

  /**
   * Find a common containment reference for a list of objects. If the objects do not have the same parent object,
   * return null.
   * 
   * @param objs
   * @return
   */
  private EReference getCommonContainmentReference(List objs) {
    EObject parent = null;
    EReference childContainmentReference = null;
    for (Object obj : objs) {
      if (!(obj instanceof EObject)) {
        return null;
      } else if (parent == null)
        parent = ((EObject) obj).eContainer();
      // If selected EObjects do not have the same parent, return null
      else if (parent != ((EObject) obj).eContainer())
        return null;
      if (childContainmentReference == null)
        childContainmentReference = (EReference) ((EObject) obj).eContainingFeature();
      // If the EObject is contained by another containment reference, there is no common containment reference
      else if (childContainmentReference != (EReference) ((EObject) obj).eContainingFeature())
        return null;
    }
    return childContainmentReference;
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#run()
   */
  @Override
  public void run() {
    final List<?> selectedElements = getStructuredSelection().toList();
    TransactionHelper.getExecutionManager(filterNonEObjects(selectedElements)).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        EReference commonContainmentReference = getCommonContainmentReference(selectedElements);
        List<Integer> childIndexes = new ArrayList<Integer>();
        List<EObject> childrenToSort = new ArrayList<EObject>();
        if (commonContainmentReference != null) {
          EList<EObject> ownedElements = (EList<EObject>) ((EObject) selectedElements.get(0)).eContainer().eGet(
              commonContainmentReference);
          for (final Object selectedElement : selectedElements) {
            int index = ownedElements.indexOf(selectedElement);
            childIndexes.add(index);
            childrenToSort.add((EObject) selectedElement);
          }

          Collections.sort(childrenToSort, abstractNamedElementNameComparator);

          // Sort containment features content.
          List<EObject> tmpList = new ArrayList<EObject>();
          for (EObject obj : ownedElements)
            tmpList.add(obj);

          if (tmpList.size() >= 2) {
            for (int i = 0; i < childIndexes.size(); i++) {
              tmpList.set(childIndexes.get(i), childrenToSort.get(i));
            }

            ECollections.setEList(ownedElements, tmpList);

            // Refresh the viewer
            CommonViewer _commonViewer = ((CommonNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage().findView(CapellaCommonNavigator.ID)).getCommonViewer();
            _commonViewer.refresh(true);
          }
        }
      }
    });
  }

  /**
   * No need to show Sort Selection action on only one element or elements which do not have the same parent
   */
  @Override
  public boolean updateSelection(IStructuredSelection selection) {
    if (selection.size() == 1)
      return false;
    final List<?> selectedElements = getStructuredSelection().toList();
    EReference commonContainmentReference = getCommonContainmentReference(selectedElements);
    if (commonContainmentReference == null)
      return false;
    return super.updateSelection(selection);
  }
}
