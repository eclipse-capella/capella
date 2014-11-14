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
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for ABCategory
 * 
 * containers of category pins must be set with sourceParts and targetParts variables
 *
 */
public class ShowHideABAbstractFunction extends ShowHideABRole {

  /**
   * @param content_p
   */
  public ShowHideABAbstractFunction(DDiagramContents content_p) {
    super(content_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic_p, DiagramContext context_p) {
    ContextItemElement lastContext = context_p.getLast();

    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic_p, context_p);

    if (lastContext.getValue() instanceof AbstractFunction) {
      //Retrieve all parts containing the given part
      Collection<EObject> result = new HashSet<EObject>();
      for (Object block : AbstractFunctionExt.getAllocationBlocks(lastContext.getValue())) {
        if (block instanceof Entity) {
          result.add((Component) block);
        } else if (block instanceof Role) {
          result.add((Role) block);
        } else if (block instanceof Component) {
          result.addAll(ComponentExt.getRepresentingParts((Component) block));
        } else if (block instanceof Component) {
          result.addAll(ComponentExt.getRepresentingParts((Component) block));
        }
      }

      //Retrains to already visible containers, to use the existing container displayed instead of display all available container.
      value.putAll(CONTAINER, result);
    }

    return value;
  }

  @Override
  protected boolean mustShow(ContextItemElement originCouple_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {

    if (originCouple_p.getValue() instanceof AbstractFunction) {
      //We don't reveal a parent function, if the getAncestor is already displayed somewhere
      if (originCouple_p.getAncestor() != null) {
        for (ContextItemView view : originCouple_p.getAncestor().getElement().getViews()) {
          if (view.getViews().get(VIEWS).size() > 0) {
            return false;
          }
        }
      }
    }
    return super.mustShow(originCouple_p, context_p, relatedViews_p);
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);

    if (semantic_p instanceof AbstractFunction) {
      mapping = FaServices.getFaServices().getMappingABAbstractFunction((AbstractFunction) semantic_p, getContent().getDDiagram());
    }
    return mapping;
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic_p, DiagramContext context_p, Collection<DSemanticDecorator> targetViews_p) {
    return super.retrieveDefaultContainer(semantic_p, context_p, targetViews_p);
  }

  @Override
  protected boolean mustShow(DSemanticDecorator containerView_p, EObject semantic_p, AbstractNodeMapping mapping_p) {
    if ((containerView_p instanceof DDiagram) && (semantic_p instanceof AbstractFunction)) {
      return false;
    }
    return super.mustShow(containerView_p, semantic_p, mapping_p);
  }

  @Override
  protected boolean mustShow(EObject semantic_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    return super.mustShow(semantic_p, context_p, relatedViews_p);
  }

  @Override
  protected boolean mustHide(EObject semantic_p, DiagramContext context_p) {
    return semantic_p instanceof AbstractFunction;
  }

}
