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
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, element, context)) {
      super.retrieveContainer(element, result, context);
    }
  }

  @Override
  protected void retrieveRequired(EObject element, List<EObject> result, IContext context) {
    super.retrieveRequired(element, result, context);
    result.add(((Allocation) element).getSourceElement());
    result.add(((Allocation) element).getTargetElement());
  }

  @Override
  protected void retrieveGoDeep(EObject eObject1, List<EObject> result, IContext iContext1) {
    super.retrieveGoDeep(eObject1, result, iContext1);

    Allocation element = (Allocation) eObject1;
    // Add related function if linked to the source of the transformation
    if (ContextScopeHandlerHelper.getInstance(iContext1).contains(ITransitionConstants.SOURCE_SCOPE, element, iContext1)) {

      EObject source = getSource(eObject1, iContext1);
      if (source != null) {
        result.add(source);
        ContextScopeHandlerHelper.getInstance(iContext1).add(ITransitionConstants.SOURCE_SCOPE, source, iContext1);
      }

      EObject target = getTarget(eObject1, iContext1);
      if (target != null) {
        result.add(target);
        ContextScopeHandlerHelper.getInstance(iContext1).add(ITransitionConstants.SOURCE_SCOPE, target, iContext1);
      }
    }
  }

  protected EObject getSource(EObject source, IContext context) {
    Allocation element = (Allocation) source;
    return element.getSourceElement();
  }

  protected EObject getTarget(EObject source, IContext context) {
    Allocation element = (Allocation) source;
    return element.getTargetElement();
  }

  @Override
  public IStatus transformRequired(EObject source, IContext context) {

    IStatus result = super.transformRequired(source, context);

    if (result.isOK()) {
      Allocation element = (Allocation) source;
      EObject sourceElement = getSource(source, context);
      EObject targetElement = getTarget(source, context);
      result = TransformationHandlerHelper.getInstance(context).checkTransformRequired(element, context, sourceElement, targetElement);
    }
    return result;

  }

  protected void premicesAllocationRelated(EObject eObject1, ArrayList<IPremise> needed) {
    Allocation element = (Allocation) eObject1;
    needed.addAll(createDefaultPrecedencePremices(element, ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT));
    needed.addAll(createDefaultPrecedencePremices(element, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT));
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    premicesAllocationRelated(element, needed);
  }

  protected void attachAllocationRelated(EObject element, EObject result, IContext context) {
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, context);
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    attachAllocationRelated(element, result, context);
  }

}
