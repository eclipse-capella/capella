/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for ABCategory
 * 
 * containers of category pins must be set with sourceParts and targetParts variables
 *
 */
public class ShowHideABFunctionalExchange extends ShowHideABAbstractFunction {

  /**
   * @param content_p
   */
  public ShowHideABFunctionalExchange(DDiagramContents content_p) {
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
    Collection<EObject> result = null;

    if (lastContext.getValue() instanceof FunctionalExchange) {
      FunctionalExchange exchange = (FunctionalExchange) lastContext.getValue();

      result = (Collection<EObject>) (Collection<? extends EObject>) Collections.singletonList(exchange.getSource());
      value.putAll(SOURCE, result);
      result = (Collection<EObject>) (Collection<? extends EObject>) Collections.singletonList(exchange.getTarget());
      value.putAll(TARGET, result);

    } else if (lastContext.getValue() instanceof FunctionInputPort) {
      FunctionInputPort port = (FunctionInputPort) lastContext.getValue();
      result = (Collection<EObject>) (Collection<? extends EObject>) Collections.singletonList(port.eContainer());
      value.putAll(CONTAINER, result);

    } else if (lastContext.getValue() instanceof FunctionOutputPort) {
      FunctionOutputPort port = (FunctionOutputPort) lastContext.getValue();
      result = (Collection<EObject>) (Collection<? extends EObject>) Collections.singletonList(port.eContainer());
      value.putAll(CONTAINER, result);

    }

    return value;
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);

    if (semantic_p instanceof FunctionalExchange) {
      mapping = FaServices.getFaServices().getMappingABFunctionalExchange(getContent().getDDiagram());

    } else if (semantic_p instanceof FunctionInputPort) {
      mapping = FaServices.getFaServices().getMappingABFunctionPort(getContent().getDDiagram());

    } else if (semantic_p instanceof FunctionOutputPort) {
      mapping = FaServices.getFaServices().getMappingABFunctionPort(getContent().getDDiagram());

    }
    return mapping;
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic_p, DiagramContext context_p, Collection<DSemanticDecorator> targetViews_p) {
    if (semantic_p instanceof AbstractFunction) {
      //If no container has been found for a part, use diagram to put the given part
      return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
    }

    return super.retrieveDefaultContainer(semantic_p, context_p, targetViews_p);
  }

  @Override
  protected boolean mustShow(EObject semantic_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    return super.mustShow(semantic_p, context_p, relatedViews_p);
  }

  @Override
  protected boolean mustShow(ContextItemElement originCouple_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {

    return super.mustShow(originCouple_p, context_p, relatedViews_p);
  }

  @Override
  protected boolean mustHide(EObject semantic_p, DiagramContext context_p) {
    //We want to hide component port
    if (semantic_p instanceof FunctionalExchange) {
      return true;
    }
    //We want to hide component exchange
    if (semantic_p instanceof FunctionInputPort) {
      return true;
    }
    if (semantic_p instanceof FunctionOutputPort) {
      return true;
    }

    return super.mustHide(semantic_p, context_p);
  }

}
