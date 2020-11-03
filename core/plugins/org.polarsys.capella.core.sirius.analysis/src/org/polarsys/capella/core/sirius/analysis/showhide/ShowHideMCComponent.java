/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for Actors/Components on Capability diagrams
 */
public class ShowHideMCComponent extends ShowHideABComponent {
  public ShowHideMCComponent(DDiagramContents content) {
    super(content);
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
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
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

  @Override
  public DiagramElementMapping getMapping(EObject semantic, DiagramContext context,
      HashMapSet<String, DSemanticDecorator> relatedViews) {
    return getContent().getMapping(MappingConstantsHelper.getMappingCapability(semantic, getContent().getDDiagram()));
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic, DiagramContext context,
      Collection<DSemanticDecorator> targetViews) {
    // If no container has been found for a part, use diagram to put the given part
    return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
  }

}
