/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju;

import java.util.function.Predicate;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.polarsys.capella.core.data.fa.FunctionalExchange;

public class DiagramFilterUtils {

  private DiagramFilterUtils() {
    // defeat instantiation
  }

  public static final Predicate<DDiagramElement> FUNCTIONAL_EXCHANGE_EDGE_DECORATOR_PREDICATE = element -> {
    if (element instanceof DEdge && element.getTarget() instanceof FunctionalExchange) {
      DEdge edge = (DEdge) element;
      EdgeStyle ownedStyle = edge.getOwnedStyle();

      return ownedStyle.getSourceArrow() == EdgeArrows.NO_DECORATION_LITERAL
          && ownedStyle.getTargetArrow() == EdgeArrows.INPUT_FILL_CLOSED_ARROW_LITERAL;
    }
    return true;
  };

}
