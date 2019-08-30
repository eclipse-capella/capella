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
package org.polarsys.capella.test.odesign.identifier;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;

public class IdentifierHelper {

  private static final Predicate<IdentifiedElement> toolFilter = tool -> tool instanceof ContainerCreationDescription
      || tool instanceof NodeCreationDescription || tool instanceof ToolDescription || tool instanceof PopupMenu
      || tool instanceof OperationAction || tool instanceof EdgeCreationDescription;

  private static final Predicate<IdentifiedElement> filterFilter = tool -> tool instanceof CompositeFilterDescription;

  public static Stream<IdentifiedElement> getTools(DiagramDescription description) {
    //In case of reused popup menu, description.getAllTools() doesn't retrieve sub reused menus
    return Stream.concat(description.getAllTools().stream().flatMap(x -> {
      if (x instanceof PopupMenu) {
        return Stream.concat(Stream.of(x), ((PopupMenu) x).getMenuItemDescription().stream());
      }
      return Stream.of((IdentifiedElement) x);
    }).distinct(), description.getFilters().stream()).filter(toolFilter.or(filterFilter));
  }
}
