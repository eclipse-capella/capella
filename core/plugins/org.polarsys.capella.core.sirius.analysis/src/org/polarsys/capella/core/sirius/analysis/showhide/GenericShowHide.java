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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.SiriusElementMappingSpecOperations;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.queries.filters.EClassFilter;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 */
public class GenericShowHide extends AbstractShowHide {

  protected IQueryFilter filter;
  protected IQueryContext qcontext = new QueryContext();

  /**
   * @param content
   * @param eClass
   * @param eFeature
   */
  public GenericShowHide(DDiagramContents content, EClass eClass) {
    this(content, eClass, new EClassFilter(eClass));
  }

  public GenericShowHide(DDiagramContents content, EClass eClass, EStructuralFeature feature) {
    this(content, eClass, new EClassFilter(eClass));
  }

  /**
   * 
   * @param content
   * @param eClass
   * @param eFeature
   * @param exactMatch
   *          The kind of hide:
   *          <ul>
   *          <li><b><tt>true</tt>&nbsp;&nbsp;&nbsp;</b> if only specified type must be considered;<br>
   *          <li><b><tt>false</tt>&nbsp;</b> if objects of inherited types are also wanted.
   *          </ul>
   */
  public GenericShowHide(DDiagramContents content, EClass eClass, IQueryFilter filter) {
    super(content);
    this.filter = filter;
  }

  public static GenericShowHide createGenericShowHide(EClass eClass, DDiagramContents content) {
    return new GenericShowHide(content, eClass);
  }

  public static GenericShowHide createGenericShowHide(EClass eClass, DDiagramContents content, IQueryFilter filter) {
    return new GenericShowHide(content, eClass, filter);
  }

  protected boolean isConcerned(EObject object) {
    return (filter == null) || filter.keepElement(object, qcontext);
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic, DiagramContext context,
      Collection<DSemanticDecorator> targetViews) {
    if (isConcerned(semantic)) {
      return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
    }
    return super.retrieveDefaultContainer(semantic, context, targetViews);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("restriction")
  protected DiagramElementMapping getMapping(EObject semantic, DiagramContext context,
      HashMapSet<String, DSemanticDecorator> relatedViews) {

    if (isConcerned(semantic)) {
      DiagramElementMapping mapping = ShowHideService.getService().getMapping(semantic.eClass(),
          getContent().getDDiagram());
      EObject container = context.getLast(CONTAINER) != null ? (EObject) context.getLast(CONTAINER).getValue() : null;
      EObject containerView = relatedViews.get(CONTAINER).isEmpty() ? null : relatedViews.get(CONTAINER).iterator()
          .next();
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

    if (isConcerned(lastCtxValue)) {
      result.add(semantic.eContainer());
    }
    value.put(CONTAINER, result);

    return value;
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple, DiagramContext context) {
    EObject semantic = originCouple.getValue();
    if (isConcerned(semantic)) {
      return true;
    }
    // And only these elements
    return false;
  }

  @Override
  protected boolean mustHide(DDiagramElement view, DiagramContext context) {
    if (view.getDiagramElementMapping() instanceof AbstractNodeMapping) {
      if (isConcerned(view.getTarget())) {
        return true;
      }
      return false;
    }
    return true;
  }

}
