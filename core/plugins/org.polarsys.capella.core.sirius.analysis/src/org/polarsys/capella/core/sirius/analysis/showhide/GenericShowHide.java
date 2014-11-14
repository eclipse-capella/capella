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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 */
public class GenericShowHide extends AbstractShowHide {

  protected EClass _eClass;
  protected EStructuralFeature _eFeature;

  /**
   * @param content_p
   */
  private GenericShowHide(DDiagramContents content_p) {
    super(content_p);
  }

  public static GenericShowHide createGenericShowHide(EClass eClass_p, EStructuralFeature eFeature_p, DDiagramContents content_p) {
    GenericShowHide sh = new GenericShowHide(content_p);
    sh._eClass = eClass_p;
    sh._eFeature = eFeature_p;
    return sh;
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic_p, DiagramContext context_p, Collection<DSemanticDecorator> targetViews_p) {
    if (_eClass.isInstance(semantic_p)) {
      return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
    }
    return super.retrieveDefaultContainer(semantic_p, context_p, targetViews_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);
    if (_eClass.isInstance(semantic_p)) {
      mapping = DiagramServices.getDiagramServices().getMapping(_eClass.getName(), getContent().getDDiagram());
    }
    return mapping;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected HashMapSet<String, EObject> getRelatedObjects(EObject semantic_p, DiagramContext context_p) {

    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic_p, context_p);

    ContextItemElement lastContext = context_p.getLast();
    EObject lastCtxValue = lastContext.getValue();
    Collection<EObject> result = new ArrayList<EObject>();

    if (_eClass.isInstance(lastCtxValue)) {
      List<EObject> referencers = EObjectExt.getReferencers(lastCtxValue, (EReference) _eFeature);
      result.addAll(referencers);
    }
    value.put(CONTAINER, result);

    return value;
  }

  @Override
  protected boolean mustHide(EObject semantic_p, DiagramContext context_p) {
    // We want to hide actors
    if (_eClass.isInstance(semantic_p)) {
      return true;
    }
    // And only these elements
    return false;
  }

  @Override
  protected boolean mustHide(DDiagramElement view_p, DiagramContext context_p) {
    if (view_p.getDiagramElementMapping() instanceof AbstractNodeMapping) {
      if (_eClass.isInstance(view_p.getTarget())) {
        return true;
      }
      return false;
    }
    return true;
  }

}
