/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for ABCategory containers of category pins must be set with sourceParts and targetParts
 * variables
 */
public class ShowHideABComponent extends AbstractShowHide {

  public static final String SOURCE_PARTS = "sourceParts"; //$NON-NLS-1$
  public static final String TARGET_PARTS = "targetParts"; //$NON-NLS-1$

  boolean containsNodeDeployment = true; //yellow
  boolean containsBehavioralDeployment = true; //blue

  /**
   * @param content_p
   */
  public ShowHideABComponent(DDiagramContents content_p) {
    super(content_p);
    DiagramElementMapping mapping = getContent()
        .getMapping(MappingConstantsHelper.getMappingABDeployedElement(getContent().getDDiagram()));
    containsNodeDeployment = StreamSupport.stream(getContent().getDiagramElements(mapping).spliterator(), false)
        .anyMatch(x -> getNature(x.getTarget()) == PhysicalComponentNature.NODE);
    containsBehavioralDeployment = StreamSupport.stream(getContent().getDiagramElements(mapping).spliterator(), false)
        .anyMatch(x -> getNature(x.getTarget()) == PhysicalComponentNature.BEHAVIOR);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic_p, DiagramContext context_p) {
    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic_p, context_p);
    ContextItemElement lastContext = context_p.getLast();
    Collection<EObject> result = new ArrayList<EObject>();

    BlockArchitecture blockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(semantic_p);
    Component systemComponent = blockArchitecture.getSystem();

    if (lastContext.getValue() instanceof Entity) {
      Entity entity = (Entity) lastContext.getValue();
      Collection<Part> parts = getCache(ComponentExt::getRepresentingParts, entity);
      if (parts.size() > 0) {
        EObject targetContainer = EcoreUtil2.getFirstContainer(parts.iterator().next(), CsPackage.Literals.COMPONENT);
        if (targetContainer instanceof Entity) {
          result.add(targetContainer);

          // Remove all parts of root component
          if (systemComponent != null) {
            result.remove(systemComponent);
          }

        }
      }
    }

    if (lastContext.getValue() instanceof Part) {
      Part part = (Part) lastContext.getValue();

      // Retrieve all parts containing the given part
      Collection<EObject> result3 = new HashSet<EObject>();

      if (containsNodeDeployment || containsBehavioralDeployment) {
        result3.addAll(getCache(PartExt::getDeployingElements, part));
      }

      Collection<EObject> result2 = new HashSet<EObject>();
      EObject targetContainer = EcoreUtil2.getFirstContainer(part, CsPackage.Literals.COMPONENT);
      if (targetContainer instanceof Component) {
        result2.addAll(ComponentExt.getRepresentingParts((Component) targetContainer));
      }

      // Remove all parts of root component
      if (systemComponent != null) {
        result2.removeAll(systemComponent.getRepresentingParts());
      }

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
      if (mapping == null) {
        return false;
      }

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
        if (!getCache(PartExt::getDeployingElements, (Part) originCouple_p.getAncestor().getElement().getValue())
            .contains((originCouple_p.getValue()))) {
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
  public DiagramElementMapping getMapping(EObject semantic, DiagramContext context,
      HashMapSet<String, DSemanticDecorator> relatedViews) {
    DiagramElementMapping mapping = super.getMapping(semantic, context, relatedViews);

    if (semantic instanceof Entity) {
      mapping = getContent()
          .getMapping(MappingConstantsHelper.getMappingABComponent(semantic, getContent().getDDiagram()));

    } else if (semantic instanceof Part) {
      Part part = (Part) semantic;
      List<DeploymentTarget> deployingElements = getCache(PartExt::getDeployingElements, part);
      PhysicalComponentNature nature = getNature(part);
      if (((containsBehavioralDeployment && nature == PhysicalComponentNature.BEHAVIOR) ||
          (containsNodeDeployment && nature == PhysicalComponentNature.NODE)) &&
          !deployingElements.isEmpty()) {
        Collection<DSemanticDecorator> targetViews = relatedViews.get(CONTAINER);
        if (!targetViews.isEmpty() && deployingElements.contains((targetViews.iterator().next().getTarget()))) {
          return getContent()
              .getMapping(MappingConstantsHelper.getMappingABDeployedElement(getContent().getDDiagram()));
        }
      }
      mapping = getContent()
          .getMapping(MappingConstantsHelper.getMappingABComponent(semantic, getContent().getDDiagram()));

    }
    return mapping;
  }
  
  /**
   * Returns the component nature for the target (only for Physical Component)
   * 
   * @param object
   *          the part.
   * @return the component nature, if type is a Physical Component.
   */
  protected PhysicalComponentNature getNature(EObject object) {
    EObject type = CsServices.getService().getComponentType(object);
    PhysicalComponentNature nature = null;
    if(type instanceof PhysicalComponent) {
      nature = ((PhysicalComponent) type).getNature();
    }
    return nature;
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
