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
package org.polarsys.capella.core.sirius.analysis.showhide;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for ABCategory containers of category pins must be set with sourceParts and targetParts
 * variables
 */
public class ShowHideABComponentExchange extends ShowHideFunctionalExchange {

  public static final String SOURCE_PART_VIEWS = "spv"; //$NON-NLS-1$
  public static final String TARGET_PART_VIEWS = "tpv"; //$NON-NLS-1$

  /**
   * @param content_p
   */
  public ShowHideABComponentExchange(DDiagramContents content_p) {
    super(content_p);
  }

  @Override
  protected boolean isValidSemanticView(EObject semantic_p, DSemanticDecorator semanticView_p, DiagramContext context_p) {
    if ((semantic_p instanceof Part) || (semantic_p instanceof Entity)) {
      // Filter source part view to source part views
      ContextItemVariable variable = context_p.getLastVariable(SOURCE_PART_VIEWS);
      if (variable != null) {
        Collection<DDiagramElement> views = ((Collection) variable.getValue());
        if (views.iterator().hasNext() && (semantic_p == views.iterator().next().getTarget())) {
          if (!views.contains(semanticView_p)) {
            return false;
          }
        }
      }

      // Filter target part view to source part views
      variable = context_p.getLastVariable(TARGET_PART_VIEWS);
      if (variable != null) {
        Collection<DDiagramElement> views = ((Collection) variable.getValue());
        if (views.iterator().hasNext() && (semantic_p == views.iterator().next().getTarget())) {
          if (!views.contains(semanticView_p)) {
            return false;
          }
        }
      }

    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic_p, DiagramContext context_p) {
    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic_p, context_p);
    ContextItemElement lastContext = context_p.getLast();

    if (lastContext.getValue() instanceof ComponentExchange) {
      ComponentExchange exchange = (ComponentExchange) lastContext.getValue();
      EObject source = ComponentExchangeExt.getSourcePort(exchange);
      EObject target = ComponentExchangeExt.getTargetPort(exchange);
      if (source == null) {
        source = ComponentExchangeExt.getSourcePart(exchange);
      }
      if ((source == null) && (exchange instanceof CommunicationMean)) {
        source = ComponentExchangeExt.getSourceComponent(exchange);
      }

      if (target == null) {
        target = ComponentExchangeExt.getTargetPart(exchange);
      }
      if ((target == null) && (exchange instanceof CommunicationMean)) {
        target = ComponentExchangeExt.getTargetComponent(exchange);
      }
      value.put(SOURCE, source);
      value.put(TARGET, target);

    } else if (lastContext.getValue() instanceof ComponentPort) {
      ComponentPort port = (ComponentPort) lastContext.getValue();
      value.putAll(CONTAINER, (Collection<EObject>) (Collection<? extends EObject>) getCache(ComponentExt::getRepresentingParts, PortExt.getRelatedComponent(port)));
    }

    return value;
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);
    ContextItemElement lastContext = context_p.getLast();

    if (lastContext.getValue() instanceof ComponentPort) {
      String mappingName = MappingConstantsHelper.getMappingABComponentPort(getContent().getDDiagram());
      mapping = getContent().getMapping(mappingName);

    } else if (lastContext.getValue() instanceof ComponentExchange) {
      String mappingName = MappingConstantsHelper.getMappingABConnection(getContent().getDDiagram());
      mapping = getContent().getMapping(mappingName);
    }

    return mapping;
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic_p, DiagramContext context_p, Collection<DSemanticDecorator> targetViews_p) {
    return super.retrieveDefaultContainer(semantic_p, context_p, targetViews_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean mustShow(DSemanticDecorator source_p, DSemanticDecorator target_p, EObject exchange_p, EdgeMapping edgeMapping_p) {
    if (exchange_p instanceof ComponentExchange) {
      return CsServices.getService().isValidComponentExchangeEdge(exchange_p, source_p, target_p);
    }
    return super.mustShow(source_p, target_p, exchange_p, edgeMapping_p);
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple_p, DiagramContext context_p) {
    EObject semantic = originCouple_p.getValue();
    // We want to hide component port
    if (semantic instanceof ComponentPort) {
      return true;
    }
    // We want to hide component exchange
    if (semantic instanceof ComponentExchange) {
      return true;
    }
    // And only these elements
    return false;
  }

}
