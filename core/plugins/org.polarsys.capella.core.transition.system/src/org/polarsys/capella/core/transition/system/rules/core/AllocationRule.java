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
package org.polarsys.capella.core.transition.system.rules.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 */
public class AllocationRule extends AbstractCapellaElementRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected EClass getSourceType() {
    return CapellacorePackage.Literals.ALLOCATION;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element_p, context_p)) {
      super.retrieveContainer(element_p, result_p, context_p);
    }
  }

  @Override
  protected void retrieveRequired(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveRequired(element_p, result_p, context_p);
    result_p.add(((Allocation) element_p).getSourceElement());
    result_p.add(((Allocation) element_p).getTargetElement());
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    Allocation element = (Allocation) source_p;
    // Add related function if linked to the source of the transformation
    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {

      EObject source = getSource(source_p, context_p);
      if (source != null) {
        result_p.add(source);
        ContextScopeHandlerHelper.getInstance(context_p).add(ITransitionConstants.SOURCE_SCOPE, source, context_p);
      }

      EObject target = getTarget(source_p, context_p);
      if (target != null) {
        result_p.add(target);
        ContextScopeHandlerHelper.getInstance(context_p).add(ITransitionConstants.SOURCE_SCOPE, target, context_p);
      }
    }
  }

  protected EObject getSource(EObject source_p, IContext context_p) {
    Allocation element = (Allocation) source_p;
    return element.getSourceElement();
  }

  protected EObject getTarget(EObject source_p, IContext context_p) {
    Allocation element = (Allocation) source_p;
    return element.getTargetElement();
  }

  @Override
  public IStatus transformRequired(EObject source_p, IContext context_p) {

    IStatus result = super.transformRequired(source_p, context_p);

    if (result.isOK()) {
      Allocation element = (Allocation) source_p;
      EObject sourceElement = getSource(source_p, context_p);
      EObject targetElement = getTarget(source_p, context_p);
      result = TransformationHandlerHelper.getInstance(context_p).checkTransformRequired(element, context_p, sourceElement, targetElement);
    }
    return result;

  }

  protected void premicesAllocationRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    Allocation element = (Allocation) element_p;
    needed_p.addAll(createDefaultPrecedencePremices(element, ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT));
    needed_p.addAll(createDefaultPrecedencePremices(element, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT));
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    premicesAllocationRelated(element_p, needed_p);
  }

  protected void attachAllocationRelated(EObject element_p, EObject result_p, IContext context_p) {
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, context_p);
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    attachAllocationRelated(element_p, result_p, context_p);
  }

}
