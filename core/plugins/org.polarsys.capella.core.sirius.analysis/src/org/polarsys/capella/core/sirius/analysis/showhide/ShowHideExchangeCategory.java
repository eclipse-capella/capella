/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.sirius.analysis.ABServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for FECategory containers of category pins must be set with sourceParts and targetParts
 * variables
 */
public class ShowHideExchangeCategory extends ShowHideFunctionalExchange {

  public static final String NO_SOURCE_PORT = "npo"; //$NON-NLS-1$
  public static final String NO_TARGET_PORT = "npo"; //$NON-NLS-1$
  public static final String SOURCE_PORTS = "sourcePorts"; //$NON-NLS-1$
  public static final String TARGET_PORTS = "targetPorts"; //$NON-NLS-1$

  public ShowHideExchangeCategory(DDiagramContents content) {
    super(content);
  }

  @Override
  protected boolean isValidNodeView(DDiagramElement semanticView, DSemanticDecorator containerView) {
    return super.isValidNodeView(semanticView, containerView);
  }

  @Override
  protected boolean isValidSemanticView(EObject semantic, DSemanticDecorator semanticView, DiagramContext context) {
    return super.isValidSemanticView(semantic, semanticView, context);
  }

  @Override
  protected boolean mustHide(DDiagramElement view, DiagramContext context) {
    // A function port must be hidden if no edges whose target is not FunctionalChain are visible
    if (view.getDiagramElementMapping() instanceof AbstractNodeMapping) {
      EObject target = view.getTarget();
      if ((target instanceof FunctionPort) || (target instanceof ExchangeCategory)) {
        EList<DEdge> relatedEdges = new BasicEList<>();
        relatedEdges.addAll(((EdgeTarget) view).getIncomingEdges());
        relatedEdges.addAll(((EdgeTarget) view).getOutgoingEdges());
        
        for (DEdge edge : relatedEdges) {
          if (edge != null && !(edge.getTarget() instanceof FunctionalChain) && getContent().isVisible(edge)) {
            return false;
          }
        }
        
        return true;
      }
    }
    return super.mustHide(view, context);
  }

  @Override
  protected boolean mustShow(ContextItemElement originCouple, DiagramContext context,
      HashMapSet<String, DSemanticDecorator> relatedViews) {

    EObject semantic = originCouple.getValue();

    ContextItemElement lastContext = context.getLast();

    if (semantic instanceof AbstractFunction || semantic instanceof Part || semantic instanceof Entity) {
      // Never reveal a new Function, a Part or an Actor
      return false;
    }

    // We display all available category, even if there is another view displayed
    if (semantic instanceof ExchangeCategory) {
      AbstractFunction source = (AbstractFunction) (((Collection) (context.getLastVariable(SOURCE_PARTS).getValue())))
          .iterator().next();
      AbstractFunction target = (AbstractFunction) (((Collection) (context.getLastVariable(TARGET_PARTS).getValue())))
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

    return super.mustShow(originCouple, context, relatedViews);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic, DiagramContext context) {
    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic, context);
    ContextItemElement lastContext = context.getLast();

    // For a category, related elements are pin for this category. so same semantic with both source/target.
    if (lastContext.getValue() instanceof ExchangeCategory) {

      if (SOURCE.equals(lastContext.getKey()) && lastContext.getValue() instanceof ExchangeCategory) {
        // If a targetPin, available containers are targetParts
        // ContextItemVariable variable = context.getLastVariable(SOURCEARTS);
        // value.put(CONTAINER, getContainer(variable));

        ContextItemVariable variable = context.getLastVariable(SOURCE_PARTS);
        EObject container = getContainer((AbstractFunction) ((Collection) variable.getValue()).iterator().next());
        if (container != null) {
          value.put(CONTAINER, container);
        }

      } else if (TARGET.equals(lastContext.getKey()) && lastContext.getValue() instanceof ExchangeCategory) {
        // If a targetPin, available containers are targetParts
        // ContextItemVariable variable = context.getLastVariable(TARGETARTS);
        // value.put(CONTAINER, getContainer(variable));

        ContextItemVariable variable = context.getLastVariable(TARGET_PARTS);
        EObject container = getContainer((AbstractFunction) ((Collection) variable.getValue()).iterator().next());
        if (container != null) {
          value.put(CONTAINER, container);
        }

      } else {
        // If a edge category, same semantic as source/target
        value.put(SOURCE, semantic);
        value.put(TARGET, semantic);
      }
    }

    return value;
  }

  private EObject getContainer(AbstractFunction abstractFunction) {
    return FaServices.getBestFunctionContainer(abstractFunction, getContent());
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic, DiagramContext context,
      HashMapSet<String, DSemanticDecorator> relatedViews) {
    
    DiagramElementMapping mapping = super.getMapping(semantic, context, relatedViews);

    ContextItemElement lastContext = context.getLast();
    if (lastContext.getValue() instanceof ExchangeCategory) {
      if (SOURCE.equals(lastContext.getKey())) {
        // If sourcePin, use FECategoryPin
        String mappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryOutputPin(getContent().getDDiagram());
        mapping = getContent().getMapping(mappingName);

      } else if (TARGET.equals(lastContext.getKey())) {
        // If targetPin, use FECategoryPin
        String mappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategoryInputPin(getContent().getDDiagram());
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
  protected boolean isValidEdgeView(DEdge edge, DSemanticDecorator sourceView, DSemanticDecorator targetView) {
    
    // Category edge is not oriented, so if we have an inverse edge, we return it, instead of creating another edge
    if (sourceView.equals(edge.getTargetNode()) && targetView.equals(edge.getSourceNode())) {
      String mappingName = MappingConstantsHelper.getMappingFunctionalExchangeCategory(getContent().getDDiagram());
      DiagramElementMapping categoryMapping = getContent().getMapping(mappingName);
      if (categoryMapping.equals(edge.getActualMapping())) {
         return true;
      }
    }
    return super.isValidEdgeView(edge, sourceView, targetView);
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic, DiagramContext context,
      Collection<DSemanticDecorator> targetViews) {
    return super.retrieveDefaultContainer(semantic, context, targetViews);
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple, DiagramContext context) {
    EObject semantic = originCouple.getValue();
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
  protected boolean hideInsteadOfRemoveView(DDiagramElement element, DiagramContext context) {
    EObject target = element.getTarget();
    // We want to hide (not remove views) of the port if diagram is synchronized
    if (target instanceof FunctionPort) {
      return getContent().getDDiagram().isSynchronized();
    }
    return super.hideInsteadOfRemoveView(element, context);
  }

  @Override
  protected Collection<DDiagramElement> showNodes(DSemanticDecorator containerView, EObject semantic,
      DDiagramContents content, AbstractNodeMapping mapping) {
    Collection<DDiagramElement> nodes = super.showNodes(containerView, semantic, content, mapping);
    // Reveal all hidden nodes for component ports
    if (semantic instanceof FunctionPort) {
      for (DDiagramElement element : nodes) {
        getContent().deferredShow(element);
      }
    }
    return nodes;
  }

  @Override
  protected Collection<DDiagramElement> showEdges(DSemanticDecorator source, DSemanticDecorator target,
      EObject exchange, DDiagramContents content, EdgeMapping edgeMapping) {
    Collection<DDiagramElement> nodes = super.showEdges(source, target, exchange, content, edgeMapping);
    // Reveal all hidden nodes for component exchanges
    if (exchange instanceof FunctionalExchange) {
      for (DDiagramElement element : nodes) {
        getContent().deferredShow(element);
      }
    }
    return nodes;
  }
}
