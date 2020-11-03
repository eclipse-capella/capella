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
import org.polarsys.capella.core.sirius.analysis.ModeStateMachineServices;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for states and modes
 */
public class ShowHideMSMStateMode extends AbstractShowHide {

  /**
   * 
   */
  public static final String CONTEXTUAL_CONTAINER = "CONTEXTUAL_CONTAINER"; //$NON-NLS-1$
  public static final String CONTEXTUAL_CONTAINER_VIEW = "CONTEXTUAL_CONTAINER_VIEW"; //$NON-NLS-1$
  private DDiagramContents content;

  /**
   * @param contentp
   */
  public ShowHideMSMStateMode(DDiagramContents contentp) {
    super(contentp);
    content = contentp;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic, DiagramContext context) {
    ContextItemElement lastContext = context.getLast();

    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic, context);

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
  protected boolean mustHide(ContextItemElement originCouple, DiagramContext context) {
    EObject semantic = originCouple.getValue();
    ContextItemElement item = context.getLast(ROOT);
    if (semantic != item.getValue()) {
      return false;
    }
    return semantic instanceof IState;
  }

  @Override
  protected boolean mustHide(DDiagramElement view, DiagramContext context) {
    return true;
  }

  @Override
  protected boolean isValidSemanticView(EObject semantic, DSemanticDecorator semanticView, DiagramContext context) {
    ContextItemElement element = context.getLast(ROOT);
    if (element == null) {
      return true;
    }
    if ((semantic != element.getValue()) && (semanticView instanceof DDiagramElement)) {
      ContextItemVariable clickedStateVar = context.getLastVariable(CONTEXTUAL_CONTAINER_VIEW);
      return (semanticView == clickedStateVar.getValue()) || EcoreUtil.isAncestor((EObject) clickedStateVar.getValue(), semanticView)
          || EcoreUtil.isAncestor(semanticView, (EObject) clickedStateVar.getValue());
    }
    return true;
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic, DiagramContext context, HashMapSet<String, DSemanticDecorator> relatedViews) {
    DiagramElementMapping mapping = super.getMapping(semantic, context, relatedViews);
    Collection<DSemanticDecorator> containerViews = relatedViews.get(CONTAINER);

    ContextItemVariable clickedStateVar = context.getLastVariable(CONTEXTUAL_CONTAINER_VIEW);

    EObject eContainer = semantic.eContainer();

    EObject parent = eContainer.eContainer();
    if (!containerViews.isEmpty()) {
      parent = containerViews.iterator().next().getTarget();
    }

      if (semantic instanceof State) {
        mapping = ModeStateMachineServices.getService().getMappingMSMStateMode((State) semantic, getContent().getDDiagram());
      }
      if (semantic instanceof Pseudostate) {
        mapping = ModeStateMachineServices.getService().getMappingMSMPseudostate((Pseudostate) semantic, getContent().getDDiagram());
      }
    return mapping;
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic, DiagramContext context, Collection<DSemanticDecorator> targetViews) {
    if (semantic instanceof IState) {
      return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
    }
    return super.retrieveDefaultContainer(semantic, context, targetViews);
  }

}
