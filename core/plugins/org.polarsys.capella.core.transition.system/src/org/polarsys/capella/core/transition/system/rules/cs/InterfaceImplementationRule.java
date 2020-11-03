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
  public EClass getTargetType(EObject element, IContext context) {
    return CsPackage.Literals.INTERFACE_IMPLEMENTATION;
  }

  @Override
  protected EObject getSourceContainer(EObject element, EObject result, IContext context) {
    return getSource(element, context);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    InterfaceImplementation element = (InterfaceImplementation) source;
    result.add(getSource(source, context));

    // Add related function if linked to the source of the transformation
    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, element, context)) {
      result.add(element.getImplementedInterface());
      ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, element.getImplementedInterface(), context);
    }
  }

  protected EObject getSource(EObject source, IContext context) {
    InterfaceImplementation element = (InterfaceImplementation) source;
    return element.getInterfaceImplementor();
  }

  @Override
  public IStatus transformRequired(EObject source, IContext context) {
    IStatus result = super.transformRequired(source, context);

    if (result.isOK()) {
      InterfaceImplementation element = (InterfaceImplementation) source;
      EObject sourceElement = element.getImplementedInterface();
      EObject targetElement = getSource(source, context);

      result = TransformationHandlerHelper.getInstance(context).checkTransformRequired(element, context, sourceElement, targetElement);
    }
    return result;

  }

  @Override
  protected void premicesRelated(EObject eObject1, ArrayList<IPremise> needed) {
    super.premicesRelated(eObject1, needed);
    InterfaceImplementation element = (InterfaceImplementation) eObject1;
    needed.addAll(createDefaultPrecedencePremices(element, CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE));
    needed.addAll(createDefaultPrecedencePremices(Collections.singletonList(getSource(eObject1, getCurrentContext())), "part"));
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE,
        context);
  }
}
