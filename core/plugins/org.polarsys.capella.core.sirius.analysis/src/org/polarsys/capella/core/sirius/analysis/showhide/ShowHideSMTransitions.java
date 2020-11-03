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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for Transitions
 */
public class ShowHideSMTransitions extends ShowHideSMStateMode {

  /**
   * @param content_p
   */
  public ShowHideSMTransitions(DDiagramContents content_p) {
    super(content_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic_p, DiagramContext context_p) {

    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic_p, context_p);

    ContextItemElement lastContext = context_p.getLast();

    if (lastContext.getValue() instanceof StateTransition) {
      StateTransition trans = (StateTransition) lastContext.getValue();
      EObject source = trans.getSource();
      EObject target = trans.getTarget();
      value.put(SOURCE, source);
      value.put(TARGET, target);
    }
    return value;
  }

  @Override
  protected boolean mustShow(ContextItemElement originCouple_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {

    if (originCouple_p.getValue() instanceof StateTransition) {
      return true;
    }
    return super.mustShow(originCouple_p, context_p, relatedViews_p);
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);

    if (semantic_p instanceof StateTransition) {
      mapping = getContent().getMapping(MappingConstantsHelper.getMappingSMTransition(semantic_p, getContent().getDDiagram()));
    }
    return mapping;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean isValidSemanticView(EObject semantic_p, DSemanticDecorator semanticView_p, DiagramContext context_p) {
    return true;
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic_p, DiagramContext context_p, Collection<DSemanticDecorator> targetViews_p) {

    if (semantic_p instanceof StateTransition) {
      return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
    }

    return super.retrieveDefaultContainer(semantic_p, context_p, targetViews_p);
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple_p, DiagramContext context_p) {
    EObject semantic = originCouple_p.getValue();
    if (semantic instanceof StateTransition) {
      return true;
    }
    return super.mustHide(originCouple_p, context_p);

  }
}
