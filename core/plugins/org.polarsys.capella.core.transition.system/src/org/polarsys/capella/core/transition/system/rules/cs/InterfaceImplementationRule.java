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
package org.polarsys.capella.core.transition.system.rules.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class InterfaceImplementationRule extends AbstractCapellaElementRule {

  public InterfaceImplementationRule() {
    registerUpdatedReference(CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE);
  }

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.INTERFACE_IMPLEMENTATION;
  }

  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    return CsPackage.Literals.INTERFACE_IMPLEMENTATION;
  }

  @Override
  protected EObject getSourceContainer(EObject element_p, EObject result_p, IContext context_p) {
    return getSource(element_p, context_p);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    InterfaceImplementation element = (InterfaceImplementation) source_p;
    result_p.add(getSource(source_p, context_p));

    // Add related function if linked to the source of the transformation
    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
      result_p.add(element.getImplementedInterface());
      ContextScopeHandlerHelper.getInstance(context_p).add(ITransitionConstants.SOURCE_SCOPE, element.getImplementedInterface(), context_p);
    }
  }

  protected EObject getSource(EObject source_p, IContext context_p) {
    InterfaceImplementation element = (InterfaceImplementation) source_p;
    return element.getInterfaceImplementor();
  }

  @Override
  public IStatus transformRequired(EObject source_p, IContext context_p) {
    IStatus result = super.transformRequired(source_p, context_p);

    if (result.isOK()) {
      InterfaceImplementation element = (InterfaceImplementation) source_p;
      EObject sourceElement = element.getImplementedInterface();
      EObject targetElement = getSource(source_p, context_p);

      result = TransformationHandlerHelper.getInstance(context_p).checkTransformRequired(element, context_p, sourceElement, targetElement);
    }
    return result;

  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    InterfaceImplementation element = (InterfaceImplementation) element_p;
    needed_p.addAll(createDefaultPrecedencePremices(element, CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE));
    needed_p.addAll(createDefaultPrecedencePremices(Collections.singletonList(getSource(element_p, getCurrentContext())), "part"));
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE,
        context_p);
  }
}
