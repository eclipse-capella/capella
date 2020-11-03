/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.odesign.identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramDescriptionHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ToolGroup;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

@SuppressWarnings("restriction")
public class IdentifierHelper {

  private static final String IGNORED_MODEL_EXTENSION_FILTER = "ModelExtensionFilter";

  private static final Predicate<IdentifiedElement> toolFilter = tool -> tool instanceof ContainerCreationDescription
      || tool instanceof NodeCreationDescription || tool instanceof ToolDescription || tool instanceof PopupMenu
      || tool instanceof OperationAction || tool instanceof EdgeCreationDescription;

  private static final Predicate<IdentifiedElement> filterFilter = tool -> tool instanceof CompositeFilterDescription
      && !tool.getName().equals(IGNORED_MODEL_EXTENSION_FILTER);

  public static Stream<IdentifiedElement> getTools(DiagramDescription description) {
    // In case of reused popup menu, description.getAllTools() doesn't retrieve sub reused menus
    return Stream.concat(getAllTools(description).stream().flatMap(x -> {
      if (x instanceof PopupMenu) {
        return Stream.concat(Stream.of(x), ((PopupMenu) x).getMenuItemDescription().stream());
      }
      return Stream.of((IdentifiedElement) x);
    }).distinct(), description.getFilters().stream()).filter(toolFilter.or(filterFilter));
  }

  /**
   * Get all the direct tools : owned tools, menus but not the reused tools. This is a modified version of the
   * getAllTools method from the {@link DiagramDescriptionHelper}. The main difference is that this version does not
   * include the reused tools.
   *
   * @param description
   *          Diagram description
   * @return The direct tools
   *
   * @see DiagramDescriptionHelper#getAllTools(DiagramDescription)
   */
  private static Set<AbstractToolDescription> getAllTools(DiagramDescription self) {
    final Set<AbstractToolDescription> result = new LinkedHashSet<AbstractToolDescription>();

    // Owned tools
    if (self.getToolSection() != null) {
      final Iterator<AbstractToolDescription> it = getTools(self.getToolSection()).iterator();
      while (it.hasNext()) {
        final AbstractToolDescription eObj = it.next();
        result.add(eObj);
      }
    }

    // Layers tools
    final Iterator<Layer> it = LayerHelper.getAllLayers(self).iterator();
    while (it.hasNext()) {
      final Layer layer = it.next();
      if (layer != null) {
        result.addAll(getAllTools(layer));
      }
    }

    return result;
  }

  private static EList<AbstractToolDescription> getAllTools(Layer layer) {
    final Set<AbstractToolDescription> result = new LinkedHashSet<AbstractToolDescription>();
    final Iterator<EObject> it = layer.eAllContents();
    while (it.hasNext()) {
      final EObject eObj = it.next();
      result.addAll(getTools(eObj));
    }
    return new EcoreEList.UnmodifiableEList<AbstractToolDescription>((InternalEObject) layer.eContainer(),
        DescriptionPackage.eINSTANCE.getLayer_AllTools(), result.size(), result.toArray());
  }

  private static Collection<AbstractToolDescription> getTools(final EObject eObj) {
    final Collection<AbstractToolDescription> tools = new ArrayList<AbstractToolDescription>();
    if (eObj instanceof AbstractToolDescription) {
      tools.add((AbstractToolDescription) eObj);
    } else if (eObj instanceof ToolGroup) {
      tools.addAll(((ToolGroup) eObj).getTools());
    } else if (eObj instanceof ToolSection) {
      for (final ToolEntry toolEntry : ((ToolSection) eObj).getOwnedTools()) {
        tools.addAll(getTools(toolEntry));
      }
    }
    return tools;
  }
}
