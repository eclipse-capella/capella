/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.api.query.AbstractNodeMappingQuery;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for Function
 */
public class ShowHideFunction extends ShowHideABRole {

  /**
   * @param content_p
   */
  public ShowHideFunction(DDiagramContents content_p) {
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
      AbstractFunction function = (AbstractFunction) lastContext.getValue();

      if (DiagramHelper.getService().isArchitectureBlank(getContent().getDDiagram())) {

        // Retrieve all parts containing the given part
        Collection<EObject> result = new HashSet<EObject>();
        for (Object block : AbstractFunctionExt.getAllocationBlocks(lastContext.getValue())) {
          if (block instanceof Entity) {
            result.add((Component) block);
          } else if (block instanceof Role) {
            result.add((Role) block);
          } else if (block instanceof Component) {
            result.addAll(getCache(ComponentExt::getRepresentingParts, (Component) block));
          }
        }

        // Retrains to already visible containers, to use the existing container displayed instead of display all
        // available container.
        value.putAll(CONTAINER, result);

      } else {

        DragAndDropTarget node = getContent().getBestContainer(function);
        if (node instanceof DSemanticDecorator && (!(node instanceof DDiagram))) {
          value.put(CONTAINER, ((DSemanticDecorator) node).getTarget());
        }

      }

    }

    return value;
  }

  @Override
  protected boolean mustShow(ContextItemElement originCouple_p, DiagramContext context_p,
      HashMapSet<String, DSemanticDecorator> relatedViews_p) {

    if (originCouple_p.getValue() instanceof AbstractFunction) {
      // We don't reveal a parent function, if the getAncestor is already displayed somewhere
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
  public DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p,
      HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);

    if (semantic_p instanceof AbstractFunction) {
      String mappingName = MappingConstantsHelper.getMappingFunction(getContent().getDDiagram());
      mapping = getContent().getMapping(mappingName);
    }

    return mapping;
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic_p, DiagramContext context_p,
      Collection<DSemanticDecorator> targetViews_p) {
    if (!DiagramHelper.getService().isArchitectureBlank(getContent().getDDiagram())) {
      if ((semantic_p instanceof AbstractFunction)) {
        // If no container has been found for a function, use diagram to put the given function
        return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
      }
    }
    return super.retrieveDefaultContainer(semantic_p, context_p, targetViews_p);
  }

  @Override
  protected boolean mustShow(DSemanticDecorator containerView_p, EObject semantic_p, AbstractNodeMapping mapping_p) {
    if (DiagramHelper.getService().isArchitectureBlank(getContent().getDDiagram())) {
      // On architecture blank, we don't display a function on the blank of the diagram
      if ((containerView_p instanceof DDiagram) && (semantic_p instanceof AbstractFunction)) {
        return false;
      }
    }
    boolean result = super.mustShow(containerView_p, semantic_p, mapping_p);

    // to know if the Function Port is present in its parent, we call mapping candidate expression in addition to the precondition expression
    if (semantic_p instanceof FunctionPort && new AbstractNodeMappingQuery(mapping_p).hasCandidatesExpression()) {
      Collection<EObject> candidates = DiagramServices.getDiagramServices().evaluateCandidateExpression(mapping_p,
          getContent().getDDiagram(), containerView_p, semantic_p);

      result = result && candidates.contains(semantic_p);
    }
    return result;
  }

  @Override
  protected boolean mustHide(DDiagramElement view_p, DiagramContext context_p) {
    if (view_p.getTarget() instanceof AbstractFunction)
      return true;
    return super.mustHide(view_p, context_p);
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple_p, DiagramContext context_p) {
    EObject semantic = originCouple_p.getValue();
    if (CONTAINER.equals(originCouple_p.getKey())) {
      return false;
    }
    return semantic instanceof AbstractFunction;
  }

}
