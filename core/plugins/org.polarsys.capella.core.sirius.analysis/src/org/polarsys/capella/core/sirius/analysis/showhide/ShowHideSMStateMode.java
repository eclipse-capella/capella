/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for states and modes
 */
public class ShowHideSMStateMode extends AbstractShowHide {

  /**
   * 
   */
  public static final String CONTEXTUAL_CONTAINER = "CONTEXTUAL_CONTAINER"; //$NON-NLS-1$
  public static final String CONTEXTUAL_CONTAINER_VIEW = "CONTEXTUAL_CONTAINER_VIEW"; //$NON-NLS-1$
  private DDiagramContents content;

  /**
   * @param content_p
   */
  public ShowHideSMStateMode(DDiagramContents content_p) {
    super(content_p);
    content = content_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic_p, DiagramContext context_p) {
    ContextItemElement lastContext = context_p.getLast();

    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic_p, context_p);

    EObject lastCtxValue = lastContext.getValue();
    Collection result = new ArrayList<EObject>();

    if ((lastCtxValue instanceof IState)) {

      List<EObject> referencers = EObjectExt.getReferencers(lastCtxValue, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
      for (EObject s : referencers) {
        result.add(s.eContainer());
      }
    }

    value.putAll(CONTAINER, result);
    return value;
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple_p, DiagramContext context_p) {
    EObject semantic = originCouple_p.getValue();
    ContextItemElement item = context_p.getLast(ROOT);
    if (semantic != item.getValue()) {
      return false;
    }
    return semantic instanceof IState;
  }

  @Override
  protected boolean mustHide(DDiagramElement view_p, DiagramContext context_p) {
    return true;
  }

  @Override
  protected boolean isValidSemanticView(EObject semantic_p, DSemanticDecorator semanticView_p, DiagramContext context_p) {
    ContextItemElement element = context_p.getLast(ROOT);
    if (element == null) {
      return true;
    }
    if ((semantic_p != element.getValue()) && (semanticView_p instanceof DDiagramElement)) {
      ContextItemVariable clickedStateVar = context_p.getLastVariable(CONTEXTUAL_CONTAINER_VIEW);
      return (semanticView_p == clickedStateVar.getValue()) || EcoreUtil.isAncestor((EObject) clickedStateVar.getValue(), semanticView_p)
          || EcoreUtil.isAncestor(semanticView_p, (EObject) clickedStateVar.getValue());
    }
    return true;
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);
    Collection<DSemanticDecorator> containerViews = relatedViews_p.get(CONTAINER);

    ContextItemVariable clickedStateVar = context_p.getLastVariable(CONTEXTUAL_CONTAINER_VIEW);

    EObject eContainer = semantic_p.eContainer();

    EObject parent = eContainer.eContainer();
    if (!containerViews.isEmpty()) {
      parent = containerViews.iterator().next().getTarget();
    }

    if ((parent instanceof State) && !containerViews.isEmpty()) {
      if ((semantic_p instanceof State)) {
        mapping = getContent().getMapping(MappingConstantsHelper.getMappingSMInnerStateMode(semantic_p, getContent().getDDiagram()));
      }
      if ((semantic_p instanceof Pseudostate)) {
        mapping = getContent().getMapping(MappingConstantsHelper.getMappingSMInnerPseudostate(semantic_p, getContent().getDDiagram()));
      }
    } else {
      if (semantic_p instanceof State) {
        mapping = getContent().getMapping(MappingConstantsHelper.getMappingSMStateMode(semantic_p, getContent().getDDiagram()));
      }
      if (semantic_p instanceof Pseudostate) {
        mapping = getContent().getMapping(MappingConstantsHelper.getMappingSMPseudostate(semantic_p, getContent().getDDiagram()));
      }
    }
    return mapping;
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic_p, DiagramContext context_p, Collection<DSemanticDecorator> targetViews_p) {
    if (semantic_p instanceof IState) {
      return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
    }
    return super.retrieveDefaultContainer(semantic_p, context_p, targetViews_p);
  }

}
