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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for ABCategory containers of category pins must be set with sourceParts and targetParts
 * variables
 */
public class ShowHideABComponent extends AbstractShowHide {

  public static final String SOURCE_PARTS = "sourceParts"; //$NON-NLS-1$
  public static final String TARGET_PARTS = "targetParts"; //$NON-NLS-1$

  boolean containsDeployment = true;

  /**
   * @param content_p
   */
  public ShowHideABComponent(DDiagramContents content_p) {
    super(content_p);

    DiagramElementMapping mapping = FaServices.getFaServices().getMappingABDeployedElement(getContent().getDDiagram());

    containsDeployment = (mapping != null) && getContent().getDiagramElements(mapping).iterator().hasNext();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic_p, DiagramContext context_p) {
    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic_p, context_p);
    ContextItemElement lastContext = context_p.getLast();

    Collection<EObject> result = new ArrayList<EObject>();
    if (lastContext.getValue() instanceof Entity) {
      Entity entity = (Entity) lastContext.getValue();
      Collection<Part> parts = ComponentExt.getRepresentingParts(entity);
      if (parts.size() > 0) {
        EObject targetContainer = EcoreUtil2.getFirstContainer(parts.iterator().next(), CsPackage.Literals.COMPONENT);
        if (targetContainer instanceof Entity) {
          result.add(targetContainer);

          // Remove all parts of root component
          result.remove((BlockArchitectureExt.getFirstComponent(BlockArchitectureExt
              .getRootBlockArchitecture(semantic_p))));

        }
      }
    }

    if (lastContext.getValue() instanceof Part) {
      Part part = (Part) lastContext.getValue();

      // Retrieve all parts containing the given part
      Collection<EObject> result3 = new HashSet<EObject>();

      if (containsDeployment) {
        result3.addAll(PartExt.getDeployingElements(part));
      }

      Collection<EObject> result2 = new HashSet<EObject>();
      EObject targetContainer = EcoreUtil2.getFirstContainer(part, CsPackage.Literals.COMPONENT);
      if (targetContainer instanceof Component) {
        result2.addAll(ComponentExt.getRepresentingParts((Component) targetContainer));
      }

      // Remove all parts of root component
      result2.removeAll((BlockArchitectureExt.getFirstComponent(BlockArchitectureExt
          .getRootBlockArchitecture(semantic_p)).getRepresentingPartitions()));
      // Retrains to already visible containers, to use the existing container displayed instead of display all
      // available container. if none visible, we add element to diagram, not reveal parent

      result.addAll(result2);
      result.addAll(result3);

      // if we don't show directly the part, but the part is shown because of a related, we restrains the part to the
      // visible ones.
      if (lastContext.getAncestor() != null) {

        Collection<EObject> resul3 = new HashSet<EObject>();
        for (EObject object : result) {
          if (getContent().containsView(object)) {
            resul3.add(object);
          }
        }
        result = resul3;
      }

    }

    value.putAll(CONTAINER, result);

    return value;
  }

  @Override
  protected boolean mustShow(ContextItemElement originCouple_p, DiagramContext context_p,
      HashMapSet<String, DSemanticDecorator> relatedViews_p) {

    // Some restriction on revealing parts
    if ((originCouple_p.getValue() instanceof Part) || (originCouple_p.getValue() instanceof Entity)) {

      // We don't reveal a parent part, if the getAncestor is already displayed somewhere
      if (originCouple_p.getAncestor() != null) {
        for (ContextItemView view : originCouple_p.getAncestor().getElement().getViews()) {
          if (view.getViews().get(VIEWS).size() > 0) {
            return false;
          }
        }
      }

      // We don't reveal a part if there is another view with the other mapping already revealed
      DiagramElementMapping mapping = getMapping(originCouple_p.getValue(), context_p, relatedViews_p);
      for (ContextItemView view : originCouple_p.getViews()) {
        for (DSemanticDecorator dView : view.getViews().get(VIEWS)) {
          if ((dView instanceof DDiagramElement)
              && !mapping.equals(((DDiagramElement) dView).getDiagramElementMapping())) {
            return false;
          }
        }
      }

      // We don't reveal a part, if is it already displayed somewhere
      if (originCouple_p.getAncestor() != null) {
        for (ContextItemView view : originCouple_p.getViews()) {
          if (view.getViews().get(INITIAL_VIEWS).size() > 0) {
            return false;
          }
        }
      }
    }
    return super.mustShow(originCouple_p, context_p, relatedViews_p);
  }

  @Override
  protected boolean bypassRelatedElements(ContextItemElement originCouple_p, DiagramContext context_p) {
    // To avoid recurse to the top of containment, we don't show the part container if not a deployment
    if ((originCouple_p.getAncestor() != null)) {
      if ((originCouple_p.getValue() instanceof Part)
          && (originCouple_p.getAncestor().getElement().getValue() instanceof Part)) {
        if (!PartExt.getDeployingElements((Part) originCouple_p.getAncestor().getElement().getValue()).contains(
            (originCouple_p.getValue()))) {
          return true;
        }
      } else if ((originCouple_p.getValue() instanceof Entity)
          && (originCouple_p.getAncestor().getElement().getValue() instanceof Entity)) {
        return true;
      }
    }

    return super.bypassRelatedElements(originCouple_p, context_p);
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p,
      HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);

    if (semantic_p instanceof Entity) {
      mapping = FaServices.getFaServices().getMappingABComponent(semantic_p, getContent().getDDiagram());

    } else if (semantic_p instanceof Part) {
      Part part = (Part) semantic_p;
      if (containsDeployment && (PartExt.getDeployingElements(part).size() > 0)) {
        Collection<DSemanticDecorator> targetViews = relatedViews_p.get(CONTAINER);
        if (targetViews.size() != 0) {
          if (PartExt.getDeployingElements((Part) semantic_p).contains((targetViews.iterator().next().getTarget()))) {
            mapping = FaServices.getFaServices().getMappingABDeployedElement(getContent().getDDiagram());
            return mapping;
          }
        }

      }
      mapping = FaServices.getFaServices().getMappingABComponent(semantic_p, getContent().getDDiagram());

    }
    return mapping;
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic_p, DiagramContext context_p,
      Collection<DSemanticDecorator> targetViews_p) {
    if ((semantic_p instanceof Part) || (semantic_p instanceof Entity)) {
      // If no container has been found for a part, use diagram to put the given part
      return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
    }

    return super.retrieveDefaultContainer(semantic_p, context_p, targetViews_p);
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple_p, DiagramContext context_p) {
    EObject semantic = originCouple_p.getValue();
    return (semantic instanceof Part) || (semantic instanceof Entity);
  }

}
