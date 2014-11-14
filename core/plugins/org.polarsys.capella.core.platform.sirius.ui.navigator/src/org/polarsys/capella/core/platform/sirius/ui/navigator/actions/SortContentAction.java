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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 * Standard action for sorting the content of a {@link CapellaElement} element.
 */
public class SortContentAction extends BaseSelectionListenerAction {
  /**
   * Name comparator for AbstractNamedElements.
   */
  public static final Comparator<EObject> abstractNamedElementNameComparator = new Comparator<EObject>() {
    @Override
    public int compare(EObject o1_p, EObject o2_p) {
      // Work only with AbstractNamedElements, for other types, no order is given.
      if (!(o1_p instanceof AbstractNamedElement) || !(o2_p instanceof AbstractNamedElement)) {
        return 0;
      }
      String o1Name = ((AbstractNamedElement) o1_p).getName();
      String o2Name = ((AbstractNamedElement) o2_p).getName();
      // Two null names -> keep same order.
      if ((null == o1Name) && (null == o2Name)) {
        return 0;
      }
      // Put element with null name first.
      if ((null == o1Name) && (null != o2Name)) {
        return -1;
      }
      if ((null != o1Name) && (null == o2Name)) {
        return 1;
      }
      // Use String compareTo.
      return o1Name.compareTo(o2Name);
    }
  };

  /**
   * Constructor.
   * @param domain_p
   */
  public SortContentAction() {
    super(Messages.SortContentAction_Label);
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#run()
   */
  @Override
  public void run() {
    final List<?> selectedElements = getStructuredSelection().toList();
    MDEAdapterFactory.getExecutionManager().execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        for (final Object selectedElement : selectedElements) {
          // Ignore non EObject elements.
          if (!(selectedElement instanceof EObject)) {
            continue;
          }
          EObject selectedEObject = (EObject) selectedElement;
          // Sort containement features content.
          for (EReference ownedRef : selectedEObject.eClass().getEAllContainments()) {
            if (ownedRef.isMany()) {
              @SuppressWarnings("unchecked")
              EList<EObject> ownedElements = (EList<EObject>) selectedEObject.eGet(ownedRef);
              if (ownedElements.size() > 2) {
                ECollections.sort(ownedElements, abstractNamedElementNameComparator);
              }
            }
          }
        }
      }
    });
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  public boolean updateSelection(IStructuredSelection selection_p) {
    if (selection_p.isEmpty()) {
      return false;
    }
    for (Object selectedElement : selection_p.toList()) {
      // Deal with capella elements only.
      if (!(selectedElement instanceof CapellaElement)) {
        return false;
      }
    }
    return true;
  }
}
