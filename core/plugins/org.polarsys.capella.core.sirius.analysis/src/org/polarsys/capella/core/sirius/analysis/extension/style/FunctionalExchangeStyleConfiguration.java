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
import java.util.HashMap;
import java.util.HashSet;
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
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;
import org.polarsys.capella.core.sirius.analysis.cache.DEdgeIconCache;

/**
 * The style configuration for functional exchanges
 */
@SuppressWarnings("restriction")
public class FunctionalExchangeStyleConfiguration extends SimpleStyleConfiguration implements IStyleConfigurationProvider {

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
        Set<FunctionalChain> chains = new HashSet<FunctionalChain>();

        // If semanticElements is the Functional Exchange as before Capella 7, don't try to compute an image
        EObject firstSemanticElement = edge.getSemanticElements().get(0);
        if (firstSemanticElement == null || !(firstSemanticElement instanceof FunctionalChain)) {
          return null;
        }

        chains.addAll((Collection<? extends FunctionalChain>) edge.getSemanticElements());
        HashMap<FunctionalChain, DNode> functionalChains = FunctionalChainServices.getFunctionalChainServices()
            .getDisplayedFunctionalChains(representationElement.getParentDiagram());
        FunctionalChainServices.getFunctionalChainServices().updateFunctionalExchangePieIcon(edge, chains,
            functionalChains);
        labelIcon = DEdgeIconCache.getInstance().getIcon((DEdge) representationElement);
      }
      return labelIcon;
    }
    return super.getLabelIcon(representationElement, editPart);
  }

  public boolean provides(DiagramElementMapping mapping, Style style) {
    if (mapping instanceof EdgeMapping) {
      String domainClass = ((EdgeMapping) mapping).getDomainClass();
      return "FunctionalExchange".equals(domainClass);
    }
    return false;
  }
}
