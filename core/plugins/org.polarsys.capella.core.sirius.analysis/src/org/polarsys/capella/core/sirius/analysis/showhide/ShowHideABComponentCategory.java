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

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.sirius.analysis.ABServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for ABCategory containers of category pins must be set with sourceParts and targetParts
 * variables
 */
public class ShowHideABComponentCategory extends ShowHideABComponentExchange {

  public static final String NO_SOURCE_PORT = "npo"; //$NON-NLS-1$
  public static final String NO_TARGET_PORT = "npo"; //$NON-NLS-1$
  public static final String SOURCE_PORTS = "spo"; //$NON-NLS-1$
  public static final String TARGET_PORTS = "tpo"; //$NON-NLS-1$

  /**
   * @param content_p
   */
  public ShowHideABComponentCategory(DDiagramContents content_p) {
    super(content_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic_p, DiagramContext context_p) {
    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic_p, context_p);
    ContextItemElement lastContext = context_p.getLast();

    // For a category, related elements are pin for this category. so same semantic with both source/target.
    if (lastContext.getValue() instanceof ComponentExchangeCategory) {

      if (SOURCE.equals(lastContext.getKey())) {
        // If a sourcePin, available containers are sourceParts
        ContextItemVariable variable = context_p.getLastVariable(SOURCE_PARTS);
        if ((variable != null) && (variable.getValue() instanceof Collection)) {
          value.putAll(CONTAINER, (Collection<EObject>) variable.getValue());
        }

      } else if (TARGET.equals(lastContext.getKey())) {
        // If a targetPin, available containers are targetParts
        ContextItemVariable variable = context_p.getLastVariable(TARGET_PARTS);
        if ((variable != null) && (variable.getValue() instanceof Collection)) {
          value.putAll(CONTAINER, (Collection<EObject>) variable.getValue());
        }

      } else {
        ContextItemVariable noSourcePort = context_p.getLastVariable(NO_SOURCE_PORT);
        ContextItemVariable noTargetPort = context_p.getLastVariable(NO_TARGET_PORT);

        // If a edge category, same semantic as source/target
        ContextItemVariable variable = context_p.getLastVariable(SOURCE_PORTS);
        if ((variable != null) && (variable.getValue() instanceof Collection)) {
          value.putAll(SOURCE, (Collection<EObject>) variable.getValue());
        } else {

          if ((noSourcePort == null) || Boolean.FALSE.equals(noSourcePort.getValue())) {
            value.put(SOURCE, semantic_p);
          } else {
            // If a sourcePin, available containers are sourceParts
            variable = context_p.getLastVariable(SOURCE_PARTS);
            if ((variable != null) && (variable.getValue() instanceof Collection)) {
              value.putAll(SOURCE, (Collection<EObject>) variable.getValue());
            }
          }
        }

        variable = context_p.getLastVariable(TARGET_PORTS);
        if ((variable != null) && (variable.getValue() instanceof Collection)) {
          value.putAll(TARGET, (Collection<EObject>) variable.getValue());
        } else {
          if ((noTargetPort == null) || Boolean.FALSE.equals(noTargetPort.getValue())) {
            value.put(TARGET, semantic_p);
          } else {
            // If a sourcePin, available containers are sourceParts
            variable = context_p.getLastVariable(TARGET_PARTS);
            if ((variable != null) && (variable.getValue() instanceof Collection)) {
              value.putAll(TARGET, (Collection<EObject>) variable.getValue());
            }
          }
        }
      }
    }

    return value;
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);

    ContextItemElement lastContext = context_p.getLast();
    if (lastContext.getValue() instanceof ComponentExchangeCategory) {
      if (SOURCE.equals(lastContext.getKey())) {
        // If sourcePin, use ABCategoryPin
        mapping = getContent().getMapping(MappingConstantsHelper.getMappingABComponentCategoryPin(getContent().getDDiagram()));

      } else if (TARGET.equals(lastContext.getKey())) {
        // If targetPin, use ABCategoryPin
        mapping = getContent().getMapping(MappingConstantsHelper.getMappingABComponentCategoryPin(getContent().getDDiagram()));

      } else {
        // Otherwise, use ABCategoryEdge
        mapping = getContent().getMapping(MappingConstantsHelper.getMappingABComponentCategory(getContent().getDDiagram())); 
      }
    }
    return mapping;
  }

  @Override
  protected boolean isValidEdgeView(DEdge edge_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    DiagramElementMapping categoryMapping = getContent().getMapping(MappingConstantsHelper.getMappingABComponentCategory(getContent().getDDiagram())); 

    // Category edge is not oriented, so if we have an inverse edge, we return it, instead of creating another edge
    if (categoryMapping.equals(edge_p.getActualMapping())) {
      if (sourceView_p.equals(edge_p.getTargetNode()) && targetView_p.equals(edge_p.getSourceNode())) {
        return true;
      }
    }
    return super.isValidEdgeView(edge_p, sourceView_p, targetView_p);
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic_p, DiagramContext context_p, Collection<DSemanticDecorator> targetViews_p) {
    return super.retrieveDefaultContainer(semantic_p, context_p, targetViews_p);
  }

  @Override
  protected boolean mustShow(ContextItemElement originCouple_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    EObject semantic = originCouple_p.getValue();
    if (((semantic instanceof Part) || (semantic instanceof Entity))) {
      // We don't reveal a part, if it is already displayed somewhere
      for (ContextItemView view : originCouple_p.getViews()) {
        if (view.getViews().get(VIEWS).size() > 0) {
          return false;
        }
      }
      // We never display a new part
      return false;
    }
    // We display all available category, even if there is another view displayed
    if (semantic instanceof ComponentExchangeCategory) {
      return true;
    }

    return super.mustShow(originCouple_p, context_p, relatedViews_p);
  }

  @Override
  protected boolean mustShow(DSemanticDecorator source_p, DSemanticDecorator target_p, EObject exchange_p, EdgeMapping edgeMapping_p) {
    if (exchange_p instanceof ComponentExchangeCategory) {
      return ABServices.getService().isValidABComponentCategoryEdge((ComponentExchangeCategory) exchange_p, source_p, target_p);
    }
    return super.mustShow(source_p, target_p, exchange_p, edgeMapping_p);
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple_p, DiagramContext context_p) {
    EObject semantic = originCouple_p.getValue();

    // We want to hide component exchange
    if (semantic instanceof ComponentExchangeCategory) {
      return true;
    }

    // An hide should hide all category mappings
    return super.mustHide(originCouple_p, context_p);
  }

  @Override
  protected boolean hideInsteadOfRemoveView(DDiagramElement element_p, DiagramContext context_p) {
    EObject target = element_p.getTarget();
    // We want to hide (not remove views) of the port if diagram is synchronized
    if (target instanceof ComponentPort) {
      return getContent().getDDiagram().isSynchronized();
    }
    return super.hideInsteadOfRemoveView(element_p, context_p);
  }

  @Override
  protected boolean mustHide(DDiagramElement view_p, DiagramContext context_p) {

    // A component port must be hide if no edges or hidden edges
    if (view_p.getDiagramElementMapping() instanceof AbstractNodeMapping) {
      EObject target = view_p.getTarget();
      if ((target != null) && ((target instanceof ComponentPort))) {
        boolean result = true;
        if (result) {
          for (DEdge edge : ((EdgeTarget) view_p).getIncomingEdges()) {
            if (getContent().isVisible(edge)) {
              result = false;
              break;
            }
          }
        }
        if (result) {
          for (DEdge edge : ((EdgeTarget) view_p).getOutgoingEdges()) {
            if (getContent().isVisible(edge)) {
              result = false;
              break;
            }
          }
        }
        return result;
      }
    }

    if ((view_p.getTarget() instanceof ComponentExchange) && (view_p instanceof DEdge)) {
      ComponentExchange ce = (ComponentExchange) view_p.getTarget();
      if (isDelegation(ce)) {
        EdgeTarget source = getSourceDelegationView((DEdge) view_p, ce);
        boolean result = true;
        if (result) {
          for (DEdge edge : (source).getIncomingEdges()) {
            if (edge.getTarget() instanceof ComponentExchangeCategory) {
              EObject sourceTarget = ((DSemanticDecorator) edge.getSourceNode()).getTarget();
              EObject targetTarget = ((DSemanticDecorator) edge.getTargetNode()).getTarget();

              if ((sourceTarget instanceof ComponentExchangeCategory) && (targetTarget instanceof ComponentPort)) {
                result = false;
              }
              if ((targetTarget instanceof ComponentExchangeCategory) && (sourceTarget instanceof ComponentPort)) {
                result = false;
              }
            }
          }
        }
        if (result) {
          for (DEdge edge : (source).getOutgoingEdges()) {
            if (edge.getTarget() instanceof ComponentExchangeCategory) {
              EObject sourceTarget = ((DSemanticDecorator) edge.getSourceNode()).getTarget();
              EObject targetTarget = ((DSemanticDecorator) edge.getTargetNode()).getTarget();

              if ((sourceTarget instanceof ComponentExchangeCategory) && (targetTarget instanceof ComponentPort)) {
                result = false;
              }
              if ((targetTarget instanceof ComponentExchangeCategory) && (sourceTarget instanceof ComponentPort)) {
                result = false;
              }
            }
          }
        }
        if (!result) {
          return result;
        }
      }
    }
    return super.mustHide(view_p, context_p);
  }

  protected EdgeTarget getSourceDelegationView(DEdge view_p, ComponentExchange link_p) {
    EdgeTarget result = (view_p).getSourceNode();
    return result;
  }

  /**
   * @param ce_p
   * @return
   */
  protected boolean isDelegation(ComponentExchange ce_p) {
    return ComponentExchangeExt.isDelegation(ce_p);
  }

  @Override
  protected Collection<DDiagramElement> showNodes(DSemanticDecorator containerView_p, EObject semantic_p, DDiagramContents content_p, AbstractNodeMapping mapping_p) {
    Collection<DDiagramElement> nodes = super.showNodes(containerView_p, semantic_p, content_p, mapping_p);
    // Reveal all hidden nodes for component ports
    if (semantic_p instanceof ComponentPort) {
      for (DDiagramElement element : nodes) {
        getContent().deferredShow(element);
      }
    }
    return nodes;
  }

  @Override
  protected Collection<DDiagramElement> showEdges(DSemanticDecorator source_p, DSemanticDecorator target_p, EObject exchange_p, DDiagramContents content_p,
      EdgeMapping edgeMapping_p) {
    Collection<DDiagramElement> nodes = super.showEdges(source_p, target_p, exchange_p, content_p, edgeMapping_p);
    // Reveal all hidden nodes for component exchanges
    if (exchange_p instanceof ComponentExchange) {
      for (DDiagramElement element : nodes) {
        getContent().deferredShow(element);
      }
    }
    return nodes;
  }

}
