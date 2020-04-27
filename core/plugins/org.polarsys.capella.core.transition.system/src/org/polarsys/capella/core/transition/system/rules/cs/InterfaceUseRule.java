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

package org.polarsys.capella.core.transition.system.rules.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class InterfaceUseRule extends AbstractCapellaElementRule {

  public InterfaceUseRule() {
    registerUpdatedReference(CsPackage.Literals.INTERFACE_USE__USED_INTERFACE);
  }

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.INTERFACE_USE;
  }

  @Override
  public IStatus transformRequired(EObject source, IContext context) {
    IStatus result = super.transformRequired(source, context);

    if (result.isOK()) {
      InterfaceUse element = (InterfaceUse) source;
      EObject sourceElement = element.getUsedInterface();
      EObject targetElement = getSource(source, context);
      result = TransformationHandlerHelper.getInstance(context).checkTransformRequired(element, context, sourceElement, targetElement);
    }
    return result;

  }

  @Override
  protected EObject getSourceContainer(EObject element, EObject result, IContext context) {
    return getSource(element, context);
  }

  protected EObject getSource(EObject source, IContext context) {
    InterfaceUse element = (InterfaceUse) source;
    return element.getInterfaceUser();
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    InterfaceUse element = (InterfaceUse) source;
    result.add(getSource(source, context));

    // Add related function if linked to the source of the transformation
    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, element, context)) {
      result.add(element.getUsedInterface());
      ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, element.getUsedInterface(), context);
    }
  }

  @Override
  protected void premicesRelated(EObject eObject1, ArrayList<IPremise> needed) {
    super.premicesRelated(eObject1, needed);
    InterfaceUse element = (InterfaceUse) eObject1;
    needed.addAll(createDefaultPrecedencePremices(element, CsPackage.Literals.INTERFACE_USE__USED_INTERFACE));
    needed.addAll(createDefaultPrecedencePremices(Collections.singletonList(getSource(eObject1, getCurrentContext())), "part"));
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CsPackage.Literals.INTERFACE_USE__USED_INTERFACE, context);
  }

}
