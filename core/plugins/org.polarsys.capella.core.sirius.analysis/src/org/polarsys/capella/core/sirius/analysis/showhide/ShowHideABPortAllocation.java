/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.information.PortAllocation;
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
public class ShowHideABPortAllocation extends ShowHideABComponentExchange {

  /**
   * @param content_p
   */
  public ShowHideABPortAllocation(DDiagramContents content_p) {
    super(content_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic_p, DiagramContext context_p) {
    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic_p, context_p);
    ContextItemElement lastContext = context_p.getLast();

    if (lastContext.getValue() instanceof PortAllocation) {
      PortAllocation exchange = (PortAllocation) lastContext.getValue();
      EObject source = exchange.getSourceElement();
      EObject target = exchange.getTargetElement();
      if (source != null) {
        value.put(SOURCE, source);
      }
      if (target != null) {
        value.put(TARGET, target);
      }
    }

    return value;
  }

  @SuppressWarnings("deprecation")
  @Override
  public DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p,
      HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);
    ContextItemElement lastContext = context_p.getLast();

    if (lastContext.getValue() instanceof PortAllocation) {
      mapping = FaServices.getFaServices().getMappingABPortAllocation(getContent().getDDiagram());
    }

    return mapping;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean mustShow(DSemanticDecorator source_p, DSemanticDecorator target_p, EObject exchange_p,
      EdgeMapping edgeMapping_p) {
    if (exchange_p instanceof PortAllocation) {
      return CsServices.getService().isValidPortAllocationEdge((PortAllocation) exchange_p, source_p, target_p);
    }
    return super.mustShow(source_p, target_p, exchange_p, edgeMapping_p);
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple_p, DiagramContext context_p) {
    EObject semantic = originCouple_p.getValue();

    // We want to hide the port allocation
    if (semantic instanceof PortAllocation) {
      return true;
    }
    
    // And only these elements
    return super.mustHide(originCouple_p, context_p);
  }

}
