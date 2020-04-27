/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for Functional Exchange
 */
public class ShowHideFunctionalExchange extends ShowHideFunction {

  /**
   * @param content_p
   */
  public ShowHideFunctionalExchange(DDiagramContents content_p) {
    super(content_p);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  @Override
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic_p, DiagramContext context_p) {
    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic_p, context_p);
    ContextItemElement lastContext = context_p.getLast();

    if (lastContext.getValue() instanceof FunctionalExchange) {
      FunctionalExchange exchange = (FunctionalExchange) lastContext.getValue();
      value.put(SOURCE, getSource(exchange));
      value.put(TARGET, getTarget(exchange));

    } else if (SOURCE.equals(lastContext.getKey()) && lastContext.getValue() instanceof ActivityNode) {
      ActivityNode port = (ActivityNode) lastContext.getValue();
      EObject container = getContainer(port);
      if (container != null) {
        value.put(CONTAINER, container);
      }

    } else if (TARGET.equals(lastContext.getKey()) && lastContext.getValue() instanceof ActivityNode) {
      ActivityNode port = (ActivityNode) lastContext.getValue();
      EObject container = getContainer(port);
      if (container != null) {
        value.put(CONTAINER, container);
      }
    }

    return value;
  }

  private EObject getContainer(ActivityNode port) {
    DragAndDropTarget node = getContent().getBestContainer(port); // port.eContainer()
    if (port instanceof OperationalActivity) {
      if (node instanceof DDiagram) {
        return null;
      }
    }
    if (node instanceof DSemanticDecorator && (!(node instanceof DDiagram))) {
      return ((DSemanticDecorator) node).getTarget();
    }
    return port.eContainer();
  }

  private ActivityNode getSource(FunctionalExchange exchange) {
    if (!(exchange.getSource() instanceof OperationalActivity)) {
      return exchange.getSource();
    }
    if (!getContent().getDiagramElements(exchange.getSource()).isEmpty()) {
      return exchange.getSource();
    }
    DragAndDropTarget node = getContent().getBestContainer(exchange.getSource());
    if (node instanceof DSemanticDecorator && (!(node instanceof DDiagram))) {
      return (ActivityNode) ((DSemanticDecorator) node).getTarget();
    }
    return exchange.getSource();
  }

  private ActivityNode getTarget(FunctionalExchange exchange) {
    if (!(exchange.getTarget() instanceof OperationalActivity)) {
      return exchange.getTarget();
    }
    if (!getContent().getDiagramElements(exchange.getTarget()).isEmpty()) {
      return exchange.getTarget();
    }
    DragAndDropTarget node = getContent().getBestContainer(exchange.getTarget());
    if (node instanceof DSemanticDecorator && (!(node instanceof DDiagram))) {
      return (ActivityNode) ((DSemanticDecorator) node).getTarget();
    }
    return exchange.getTarget();
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);

    if (semantic_p instanceof FunctionalExchange) {
      String mappingName = MappingConstantsHelper.getMappingFunctionalExchange(getContent().getDDiagram());
      mapping = getContent().getMapping(mappingName);

    } else if (semantic_p instanceof FunctionInputPort) {
      String mappingName = MappingConstantsHelper.getMappingFunctionPort(getContent().getDDiagram());
      mapping = getContent().getMapping(mappingName);

    } else if (semantic_p instanceof FunctionOutputPort) {
      String mappingName = MappingConstantsHelper.getMappingFunctionPort(getContent().getDDiagram());
      mapping = getContent().getMapping(mappingName);

    } else if (semantic_p instanceof OperationalActivity) {
      // In case of an Interaction between two Operational Activities
      String mappingName = MappingConstantsHelper.getMappingFunction(getContent().getDDiagram());
      mapping = getContent().getMapping(mappingName);
    }
    return mapping;
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic_p, DiagramContext context_p, Collection<DSemanticDecorator> targetViews_p) {
    if (semantic_p instanceof AbstractFunction) {
      // If no container has been found for a part, use diagram to put the given part
      return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
    }

    return super.retrieveDefaultContainer(semantic_p, context_p, targetViews_p);
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple_p, DiagramContext context_p) {
    EObject semantic = originCouple_p.getValue();
    // We want to hide component port
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

    // We don't want to hide Operational Activities
    if (semantic instanceof OperationalActivity) {
      return false;
    }

    return super.mustHide(originCouple_p, context_p);
  }

  @Override
  protected boolean mustHide(DDiagramElement view_p, DiagramContext context_p) {
    if (view_p.getTarget() instanceof FunctionalExchange)
      return true;
    return super.mustHide(view_p, context_p);
  }
}
