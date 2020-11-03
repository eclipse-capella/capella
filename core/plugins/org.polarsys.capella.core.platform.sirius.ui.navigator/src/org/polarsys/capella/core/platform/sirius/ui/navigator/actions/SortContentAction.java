/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * Standard action for sorting the content of a {@link CapellaElement} element.
 */
public class SortContentAction extends BaseSelectionListenerAction {
  /**
   * Name comparator for AbstractNamedElements.
   */
  public static final Comparator<EObject> abstractNamedElementNameComparator = new Comparator<EObject>() {
    @Override
    public int compare(EObject o1, EObject o2) {
      // Work only with AbstractNamedElements, for other types, no order is given.

      String o1Name = null;
      String o2Name = null;
      EAttribute attributeO1 = CapellaResourceHelper.getEditableAttribute(o1);
      EAttribute attributeO2 = CapellaResourceHelper.getEditableAttribute(o2);

      if (attributeO1 != null) {
        o1Name = (String) o1.eGet(attributeO1);
      }
      if (attributeO2 != null) {
        o2Name = (String) o2.eGet(attributeO2);
      }
      // Two null names -> keep same order.
      if ((null == o1Name) && (null == o2Name)) {
        return 0;
      }
      // Put element with null name first.
      if (null == o1Name) {
        return -1;
      }
      if (null == o2Name) {
        return 1;
      }
      // Format Strings before compare them
      String o1NameInLowerCase = o1Name.toLowerCase().trim();
      String o2NameInLowerCase = o2Name.toLowerCase().trim();

      // Use String compareTo.
      return o1NameInLowerCase.compareTo(o2NameInLowerCase);
    }
  };

  /**
   * Constructor.
   */
  public SortContentAction() {
    super(Messages.SortContentAction_Label);
  }

  public SortContentAction(String sortLabel) {
    super(sortLabel);
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
        for (final Object selectedElement : selectedElements) {
          // Ignore non EObject elements.
          if (!(selectedElement instanceof EObject)) {
            continue;
          }
          EObject selectedEObject = (EObject) selectedElement;
          // Sort containment features content.
          for (EReference ownedRef : selectedEObject.eClass().getEAllContainments()) {
            if (ownedRef.isMany()) {
              @SuppressWarnings("unchecked")
              EList<EObject> ownedElements = (EList<EObject>) selectedEObject.eGet(ownedRef);
              if (ownedElements.size() >= 2) {
                ECollections.sort(ownedElements, abstractNamedElementNameComparator);
              }
            }
          }
        }
      }
    });
  }

  @SuppressWarnings("rawtypes")
  protected Collection<EObject> filterNonEObjects(Collection elements) {
    Collection<EObject> result = new ArrayList<>();
    for (Object elt : elements) {
      if (elt instanceof EObject) {
        result.add((EObject) elt);
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  public boolean updateSelection(IStructuredSelection selection) {
    if (selection.isEmpty()) {
      return false;
    }
    for (Object selectedElement : selection.toList()) {
      // Deal with capella elements and Emde extensions.
      if (!(CapellaResourceHelper.isSemanticElement(selectedElement))) {
        return false;
      }
    }
    return true;
  }
}
