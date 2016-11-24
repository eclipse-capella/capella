/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.sirius.analysis.ABServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for invisible FECategory. Containers of category pins must be set with sourceParts and
 * targetParts variables. No need to use getBestContainer to get the source and target Function.
 */
public class ShowHideInvisibleExchangeCategory extends ShowHideFunctionalExchange {

  public ShowHideInvisibleExchangeCategory(DDiagramContents content_p) {
    super(content_p);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected boolean isValidNodeView(DDiagramElement semanticView_p, DSemanticDecorator containerView_p) {
    return super.isValidNodeView(semanticView_p, containerView_p);
  }

  @Override
  protected boolean isValidSemanticView(EObject semantic_p, DSemanticDecorator semanticView_p,
      DiagramContext context_p) {
    return super.isValidSemanticView(semantic_p, semanticView_p, context_p);
  }

  @Override
  protected boolean mustHide(DDiagramElement view_p, DiagramContext context_p) {

    // A function port must be hidden if no edges or hidden edges
    if (view_p.getDiagramElementMapping() instanceof AbstractNodeMapping) {
      EObject target = view_p.getTarget();
      if ((target != null) && ((target instanceof FunctionPort || target instanceof ExchangeCategory))) {
        boolean result = true;
        if (result) {
          for (DEdge edge : ((EdgeTarget) view_p).getIncomingEdges()) {
            if (edge.isVisible()) {
              result = false;
              break;
            }
          }
        }
        if (result) {
          for (DEdge edge : ((EdgeTarget) view_p).getOutgoingEdges()) {
            if (edge.isVisible()) {
              result = false;
              break;
            }
          }
        }
        return result;
      }
    }

    return super.mustHide(view_p, context_p);
  }

  @Override
  protected boolean mustShow(ContextItemElement originCouple_p, DiagramContext context_p,
      HashMapSet<String, DSemanticDecorator> relatedViews_p) {

    EObject semantic = originCouple_p.getValue();

    ContextItemElement lastContext = context_p.getLast();
    // We display all available category, even if there is another view displayed
    if (semantic instanceof ExchangeCategory) {
      AbstractFunction source = (AbstractFunction) (((Collection) (context_p.getLastVariable(SOURCE_PARTS).getValue())))
          .iterator().next();
      AbstractFunction target = (AbstractFunction) (((Collection) (context_p.getLastVariable(TARGET_PARTS).getValue())))
          .iterator().next();
      ExchangeCategory category = (ExchangeCategory) semantic;

      if (SOURCE.equals(lastContext.getKey())) {
        // Do we display source port of the category ?
        return ABServices.getService().isSourceTargetCategoryFunction(source, target, category);

      } else if (TARGET.equals(lastContext.getKey())) {
        // Do we display target port of the category ?
        return ABServices.getService().isSourceTargetCategoryFunction(source, target, category);
      }

      // Do we display edge of the category ?
      return ABServices.getService().isSourceTargetCategoryFunction(source, target, category);
    }

    return super.mustShow(originCouple_p, context_p, relatedViews_p);
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
    if (lastContext.getValue() instanceof ExchangeCategory) {

      if (SOURCE.equals(lastContext.getKey()) && lastContext.getValue() instanceof ExchangeCategory) {
        ContextItemVariable variable = context_p.getLastVariable(SOURCE_PARTS);
        EObject container = getContainer((AbstractFunction) ((Collection) variable.getValue()).iterator().next());
        if (container != null) {
          value.put(CONTAINER, container);
        }

      } else if (TARGET.equals(lastContext.getKey()) && lastContext.getValue() instanceof ExchangeCategory) {
        ContextItemVariable variable = context_p.getLastVariable(TARGET_PARTS);
        EObject container = getContainer((AbstractFunction) ((Collection) variable.getValue()).iterator().next());
        if (container != null) {
          value.put(CONTAINER, container);
        }

      } else {
        // If a edge category, same semantic as source/target
        value.put(SOURCE, semantic_p);
        value.put(TARGET, semantic_p);
      }
    }

    return value;
  }

  private EObject getContainer(AbstractFunction abstractFunction) {
    if (abstractFunction != null && abstractFunction instanceof AbstractFunction) {
      if (!getContent().getDiagramElements(abstractFunction).isEmpty()) {
        return abstractFunction;
      }
    }
    return null;
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p,
      HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);

    ContextItemElement lastContext = context_p.getLast();
    if (lastContext.getValue() instanceof ExchangeCategory) {
      if (SOURCE.equals(lastContext.getKey())) {
        // If sourcePin, use FECategoryPin
        String mappingName = MappingConstantsHelper
            .getMappingFunctionalExchangeCategoryOutputPin(getContent().getDDiagram());
        mapping = getContent().getMapping(mappingName);

      } else if (TARGET.equals(lastContext.getKey())) {
        // If targetPin, use FECategoryPin
        String mappingName = MappingConstantsHelper
            .getMappingFunctionalExchangeCategoryInputPin(getContent().getDDiagram());
        mapping = getContent().getMapping(mappingName);

      } else {
        // Otherwise, use FECategoryEdge
        String mappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategory(getContent().getDDiagram());
        mapping = getContent().getMapping(mappingName);
      }
    }
    return mapping;
  }

  @Override
  protected boolean isValidEdgeView(DEdge edge_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    // Category edge is not oriented, so if we have an inverse edge, we return it, instead of creating another edge
    if (sourceView_p.equals(edge_p.getTargetNode()) && targetView_p.equals(edge_p.getSourceNode())) {
      String mappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategory(getContent().getDDiagram());
      DiagramElementMapping categoryMapping = getContent().getMapping(mappingName);
      if (categoryMapping.equals(edge_p.getActualMapping())) {
        return true;
      }
    }

    return super.isValidEdgeView(edge_p, sourceView_p, targetView_p);
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic_p, DiagramContext context_p,
      Collection<DSemanticDecorator> targetViews_p) {
    return super.retrieveDefaultContainer(semantic_p, context_p, targetViews_p);
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple_p, DiagramContext context_p) {
    EObject semantic = originCouple_p.getValue();
    if (semantic instanceof AbstractFunction) {
      return false;
    }
    if (semantic instanceof FunctionalExchange) {
      return true;
    }
    // We want to hide component exchange
    if (semantic instanceof FunctionInputPort) {
      return true;
    }
    if (semantic instanceof FunctionOutputPort) {
      return true;
    }
    if (semantic instanceof ExchangeCategory) {
      return true;
    }
    // An hide should hide all category mappings
    return false;
  }

  @Override
  protected boolean hideInsteadOfRemoveView(DDiagramElement element_p, DiagramContext context_p) {
    EObject target = element_p.getTarget();
    // We want to hide (not remove views) of FE and FP if diagram is synchronized
    if ((target != null) && ((target instanceof FunctionalExchange) || (target instanceof FunctionPort))) {
      return getContent().getDDiagram().isSynchronized();
    }
    return super.hideInsteadOfRemoveView(element_p, context_p);
  }

  @Override
  protected Collection<DDiagramElement> showNodes(DSemanticDecorator containerView_p, EObject semantic_p,
      DDiagramContents content_p, AbstractNodeMapping mapping_p) {
    Collection<DDiagramElement> nodes = super.showNodes(containerView_p, semantic_p, content_p, mapping_p);
    // Reveal all hidden nodes for component ports
    if (semantic_p instanceof FunctionPort) {
      for (DDiagramElement element : nodes) {
        getContent().deferredShow(element);
      }
    }
    return nodes;
  }

  @Override
  protected Collection<DDiagramElement> showEdges(DSemanticDecorator source_p, DSemanticDecorator target_p,
      EObject exchange_p, DDiagramContents content_p, EdgeMapping edgeMapping_p) {
    Collection<DDiagramElement> nodes = super.showEdges(source_p, target_p, exchange_p, content_p, edgeMapping_p);
    // Reveal all hidden nodes for component exchanges
    if (exchange_p instanceof FunctionalExchange) {
      for (DDiagramElement element : nodes) {
        getContent().deferredShow(element);
      }
    }
    return nodes;
  }
}
