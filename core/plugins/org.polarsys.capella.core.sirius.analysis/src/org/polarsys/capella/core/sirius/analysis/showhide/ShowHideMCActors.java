/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.showhide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.SiriusElementMappingSpecOperations;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for Actors/Components on Capability diagrams
 */
public class ShowHideMCActors extends ShowHideABComponent {

  IQueryFilter filter;
  protected IQueryContext qcontext = new QueryContext();

  public ShowHideMCActors(IQueryFilter filter, DDiagramContents content) {
    super(content);
    this.filter = filter;
  }

  @Override
  protected boolean mustHide(DDiagramElement view, DiagramContext context) {
    return true;
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple, DiagramContext context) {
    EObject semantic = originCouple.getValue();
    ContextItemElement item = context.getLast(ROOT);
    if (semantic != item.getValue()) {
      return false;
    }
    return isConcerned(semantic);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic, DiagramContext context) {
    HashMapSet<String, EObject> related = super.getRelatedObjects(semantic, context);
    for (EObject object : new ArrayList<EObject>(related.get(CONTAINER))) {
      if (object instanceof Part) {
        related.remove(CONTAINER, object);
        related.put(CONTAINER, ((Part) object).getAbstractType());
      }
    }
    return related;
  }

  protected boolean isConcerned(EObject object) {
    return (filter == null) || filter.keepElement(object, qcontext);
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic, DiagramContext context,
      HashMapSet<String, DSemanticDecorator> relatedViews) {
    DiagramElementMapping mapping = super.getMapping(semantic, context, relatedViews);

    if (isConcerned(semantic)) {
      mapping = ShowHideService.getService().getMapping(semantic.eClass(), getContent().getDDiagram());
      EObject container = context.getLast(CONTAINER) != null ? (EObject) context.getLast(CONTAINER).getValue() : null;
      EObject containerView = relatedViews.get(CONTAINER).isEmpty() ? null : relatedViews.get(CONTAINER).iterator()
          .next();
      if (SiriusElementMappingSpecOperations.checkPrecondition(mapping, semantic, container, containerView)) {
        return mapping;
      }
    }

    return mapping;
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic, DiagramContext context,
      Collection<DSemanticDecorator> targetViews) {
    // If no container has been found for a part, use diagram to put the given part
    return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
  }

}
