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
    public int compare(EObject o1_p, EObject o2_p) {
      // Work only with AbstractNamedElements, for other types, no order is given.
      
      String o1Name = null;
      String o2Name = null;
      EAttribute attribute_o1 = CapellaResourceHelper.getEditableAttribute(o1_p);
      EAttribute attribute_o2 = CapellaResourceHelper.getEditableAttribute(o2_p);
      
      if (attribute_o1 != null) {
         o1Name = (String) o1_p.eGet(attribute_o1);
        }
      if (attribute_o2 != null) {
          o2Name = (String) o2_p.eGet(attribute_o2);
         }
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
              System.out.println(ownedElements);
              if (ownedElements.size() > 2) {
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
	Collection<EObject> result = new ArrayList<EObject>();
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
  public boolean updateSelection(IStructuredSelection selection_p) {
    if (selection_p.isEmpty()) {
      return false;
    }
    for (Object selectedElement : selection_p.toList()) {
      // Deal with capella elements and Emde extensions.
      if (!(CapellaResourceHelper.isSemanticElement(selectedElement))) {
        return false;
      }
    }
    return true;
  }
}
