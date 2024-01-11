/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.extension.style;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.sirius.analysis.PhysicalServices;
import org.polarsys.capella.core.sirius.analysis.cache.DEdgeIconCache;

/**
 * The style configuration for physical links.
 */
@SuppressWarnings("restriction")
public class PhysicalLinkStyleConfiguration extends SimpleStyleConfiguration implements IStyleConfigurationProvider {

  public StyleConfiguration createStyleConfiguration(DiagramElementMapping mapping, Style style) {
    return this;
  }

  @Override
  public Image getLabelIcon(DDiagramElement representationElement, IGraphicalEditPart editPart) {
    if (representationElement instanceof DEdge
        && (editPart instanceof DEdgeBeginNameEditPart || editPart instanceof DEdgeEndNameEditPart)
        && isShowIcon(representationElement, editPart)) {
      Image labelIcon = DEdgeIconCache.getInstance().getIcon((DEdge) representationElement);

      if (labelIcon == null) {
        DEdge edge = (DEdge) representationElement;
        Set<PhysicalPath> paths = new HashSet<PhysicalPath>();
        
        // If semanticElements is the PhysicalLink as before Capella 7, don't try to compute an image
        EObject firstSemanticElement = edge.getSemanticElements().get(0);
        if (firstSemanticElement == null || !(firstSemanticElement instanceof PhysicalPath)) {
          return null;
        }
        
        paths.addAll((Collection<? extends PhysicalPath>) edge.getSemanticElements());
        Map<PhysicalPath, DNode> physicalPaths = PhysicalServices.getService()
            .getDisplayedPhysicalPathsAndNodes(representationElement.getParentDiagram());
        PhysicalServices.getService().updatePhysicalLinkPieIcon(edge, paths, physicalPaths);
        labelIcon = DEdgeIconCache.getInstance().getIcon((DEdge) representationElement);
      }

      return labelIcon;
    }
    return super.getLabelIcon(representationElement, editPart);
  }

  public boolean provides(DiagramElementMapping mapping, Style style) {
    if (mapping instanceof EdgeMapping) {
      String domainClass = ((EdgeMapping) mapping).getDomainClass();
      return "PhysicalLink".equals(domainClass);
    }
    return false;
  }
}
