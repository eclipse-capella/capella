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
package org.polarsys.capella.core.platform.sirius.ui.navigator.drop;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.dnd.DND;

/**
 * Encapsulate basic constraints for a drag/drop operation.
 */
public class BasicDropConstraints {

  /**
   * Returns whether the drop is a COPY or not
   */
  protected boolean isCopy(int operation) {
    return operation == DND.DROP_COPY;
  }

  /**
   * Returns whether the element can be drop inside one of dragged elements
   */
  protected boolean allowInside(int operation) {
    return true;
  }

  /**
   * Returns whether the element can be drop inside one of parent of dragged elements
   */
  protected boolean allowInsideParent(int operation) {
    return true;
  }

  /**
   * Checks if basic drop constraints are satisfied:
   * 
   * - If the drop is not a copy drop, one cannot drop an element onto itself or its direct parent - One can never drop
   * an element onto one of its children
   * 
   * @param draggedElements
   *          elements being dragged
   * @param dropTarget
   *          the current drop target
   * @param operation
   *          the DND operation (copy, move, etc).
   * @return true if basic constraints are satisfied, false otherwise.
   */
  public boolean canDrop(List<EObject> draggedElements, EObject dropTarget, int operation) {
    boolean isCopy = isCopy(operation);
    boolean allowInside = allowInside(operation);
    boolean allowInsideParent = allowInsideParent(operation);

    for (EObject dragged : draggedElements) {

      // drag an element on itself is ok if we have copy semantics
      if (dragged == dropTarget && (!isCopy || !allowInside)) {
        return false;
      }

      // drag an element on its parent is ok if we have copy semantics
      if (dragged.eContainer() == dropTarget && (!isCopy || !allowInsideParent)) {
        return false;
      }

      // dragging an element onto one of its children is confusing,
      // we disallow this. even if it might theoretically be
      // valid in a copy operation.
      EObject container = dropTarget.eContainer();
      while (container != null) {
        if (dragged == container) {
          return false;
        }
        container = container.eContainer();
      }
    }
    return true;
  }
}
