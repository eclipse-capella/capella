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
import org.polarsys.capella.core.sirius.analysis.ModeStateMachineServices;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for Transitions
 */
public class ShowHideMSMTransitions extends ShowHideMSMStateMode {

  /**
   * @param content
   */
  public ShowHideMSMTransitions(DDiagramContents content) {
    super(content);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic, DiagramContext context) {

    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic, context);

    ContextItemElement lastContext = context.getLast();

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
  protected boolean mustShow(ContextItemElement originCouple, DiagramContext context, HashMapSet<String, DSemanticDecorator> relatedViews) {

    if (originCouple.getValue() instanceof StateTransition) {
      return true;
    }
    return super.mustShow(originCouple, context, relatedViews);
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic, DiagramContext context, HashMapSet<String, DSemanticDecorator> relatedViews) {
    DiagramElementMapping mapping = super.getMapping(semantic, context, relatedViews);

    if (semantic instanceof StateTransition) {
      mapping = ModeStateMachineServices.getService().getMappingMSMTransition((StateTransition) semantic, getContent().getDDiagram());
    }
    return mapping;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean isValidSemanticView(EObject semantic, DSemanticDecorator semanticView, DiagramContext context) {
    return true;
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic, DiagramContext context, Collection<DSemanticDecorator> targetViews) {

    if (semantic instanceof StateTransition) {
      return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
    }

    return super.retrieveDefaultContainer(semantic, context, targetViews);
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple, DiagramContext context) {
    EObject semantic = originCouple.getValue();
    if (semantic instanceof StateTransition) {
      return true;
    }
    return super.mustHide(originCouple, context);

  }
}
