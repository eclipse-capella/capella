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
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.SiriusElementMappingSpecOperations;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 */
public class GenericShowHide extends AbstractShowHide {

  protected boolean exactMatch;
  protected EClass eClass;
  protected EStructuralFeature eFeature;

  /**
   * @param content
   * @param eClass
   * @param eFeature
   */
  public GenericShowHide(DDiagramContents content, EClass eClass, EStructuralFeature eFeature) {
    this(content, eClass, eFeature, false);
  }
  
  /**
   * 
   * @param content
   * @param eClass
   * @param eFeature
   * @param exactMatch
   *            The kind of hide:
   *            <ul>
   *            <li><b><tt>true</tt>&nbsp;&nbsp;&nbsp;</b> if only specified type must be considered;<br>
   *            <li><b><tt>false</tt>&nbsp;</b> if objects of inherited types are also wanted.
   *            </ul>
   */
  public GenericShowHide(DDiagramContents content, EClass eClass, EStructuralFeature eFeature, boolean exactMatch) {
    super(content);
    this.eClass = eClass;
    this.eFeature = eFeature;
    this.exactMatch = exactMatch;
  }

  public static GenericShowHide createGenericShowHide(EClass eClass, EStructuralFeature eFeature, DDiagramContents content) {
    return new GenericShowHide(content, eClass, eFeature);
  }
  
  public static GenericShowHide createGenericShowHide(EClass eClass, EStructuralFeature eFeature, DDiagramContents content, boolean exactMatch) {
    return new GenericShowHide(content, eClass, eFeature, exactMatch);
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic, DiagramContext context, Collection<DSemanticDecorator> targetViews) {
    if (eClass.isInstance(semantic)) {
      return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
    }
    return super.retrieveDefaultContainer(semantic, context, targetViews);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("restriction")
  @Override
  protected DiagramElementMapping getMapping(EObject semantic, DiagramContext context, HashMapSet<String, DSemanticDecorator> relatedViews) {
    if (isInstanceOf(semantic, eClass, exactMatch) ) {
      DiagramElementMapping mapping = DiagramServices.getDiagramServices().getMapping(eClass.getName(), getContent().getDDiagram());
      EObject container = context.getLast(CONTAINER) != null ? (EObject)context.getLast(CONTAINER).getValue() : null;
      EObject containerView = relatedViews.get(CONTAINER).isEmpty() ? null:  relatedViews.get(CONTAINER).iterator().next();
      if (SiriusElementMappingSpecOperations.checkPrecondition(mapping, semantic, container, containerView)) {
        return mapping;
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected HashMapSet<String, EObject> getRelatedObjects(EObject semantic, DiagramContext context) {

    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic, context);

    ContextItemElement lastContext = context.getLast();
    EObject lastCtxValue = lastContext.getValue();
    Collection<EObject> result = new ArrayList<EObject>();

    if (eClass.isInstance(lastCtxValue)) {
      List<EObject> referencers = EObjectExt.getReferencers(lastCtxValue, (EReference) eFeature);
      result.addAll(referencers);
    }
    value.put(CONTAINER, result);

    return value;
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple, DiagramContext context) {
    EObject semantic = originCouple.getValue();
    // We want to hide actors
    if (isInstanceOf(semantic, eClass, exactMatch)) {
      return true;
    }
    // And only these elements
    return false;
  }

  @Override
  protected boolean mustHide(DDiagramElement view, DiagramContext context) {
    if (view.getDiagramElementMapping() instanceof AbstractNodeMapping) {
      if (isInstanceOf(view.getTarget(), eClass, exactMatch)) {
        return true;
      }
      return false;
    }
    return true;
  }
  
  protected boolean isInstanceOf(EObject eObject, EClass type, boolean exactMatch) {
    if (type.isInstance(eObject)) {
      if (!exactMatch ||  eObject.eClass().getName() == type.getName()) {
        return true;
      }
    }
    return false;
  }
}
