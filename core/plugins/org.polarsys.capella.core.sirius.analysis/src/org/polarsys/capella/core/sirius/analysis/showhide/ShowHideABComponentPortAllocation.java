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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.model.helpers.ComponentPortAllocationExt;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for ABCategory
 * 
 * containers of category pins must be set with sourceParts and targetParts variables
 *
 */
public class ShowHideABComponentPortAllocation extends ShowHideABPhysicalLink {

  /**
   * @param content_p
   */
  public ShowHideABComponentPortAllocation(DDiagramContents content_p) {
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

    if (lastContext.getValue() instanceof ComponentPortAllocation) {
      ComponentPortAllocation exchange = (ComponentPortAllocation) lastContext.getValue();
      EObject source = ComponentPortAllocationExt.getSourcePort(exchange);
      EObject target = ComponentPortAllocationExt.getTargetPort(exchange);
      if (source == null) {
        source = ComponentPortAllocationExt.getSourcePart(exchange);
      }
      if (target == null) {
        target = ComponentPortAllocationExt.getTargetPart(exchange);
      }
      value.put(SOURCE, source);
      value.put(TARGET, target);

    }

    return value;
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);
    ContextItemElement lastContext = context_p.getLast();

    if (lastContext.getValue() instanceof ComponentPortAllocation) {
      mapping = FaServices.getFaServices().getMappingABComponentPortAllocation(getContent().getDDiagram());
    }

    return mapping;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean mustShow(DSemanticDecorator source_p, DSemanticDecorator target_p, EObject exchange_p, EdgeMapping edgeMapping_p) {
    if (exchange_p instanceof ComponentPortAllocation) {
      return CsServices.getService().isValidComponentPortAllocationEdge((ComponentPortAllocation) exchange_p, source_p, target_p);
    }
    return super.mustShow(source_p, target_p, exchange_p, edgeMapping_p);
  }

  @Override
  protected boolean mustShow(ContextItemElement originCouple_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    return super.mustShow(originCouple_p, context_p, relatedViews_p);
  }

  @Override
  protected boolean mustHide(EObject semantic_p, DiagramContext context_p) {
    //We want to hide physical link
    if (semantic_p instanceof ComponentPortAllocation) {
      return true;
    }
    //And only these elements
    return super.mustHide(semantic_p, context_p);
  }

}
