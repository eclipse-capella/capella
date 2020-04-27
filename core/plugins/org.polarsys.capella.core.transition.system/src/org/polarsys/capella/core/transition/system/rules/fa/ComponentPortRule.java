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

package org.polarsys.capella.core.transition.system.rules.fa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class ComponentPortRule extends AbstractCapellaElementRule {

  public ComponentPortRule() {
    super();
    registerUpdatedAttribute(FaPackage.Literals.COMPONENT_PORT__KIND);
    registerUpdatedAttribute(FaPackage.Literals.COMPONENT_PORT__ORIENTATION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveRequired(EObject element, List<EObject> result, IContext context) {
    super.retrieveRequired(element, result, context);
  }

  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.COMPONENT_PORT;
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InformationPackage.Literals.PORT__PROVIDED_INTERFACES, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InformationPackage.Literals.PORT__REQUIRED_INTERFACES, context);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, InformationPackage.Literals.PORT__PROVIDED_INTERFACES));
    needed.addAll(createDefaultPrecedencePremices(element, InformationPackage.Literals.PORT__REQUIRED_INTERFACES));
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    ComponentPort element = (ComponentPort) source;

    result.addAll(element.getIncomingPortAllocations());
    result.addAll(element.getOutgoingPortAllocations());

    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, element, context)) {
      result.addAll(element.getComponentExchanges());
      result.addAll(element.getProvidedInterfaces());
      result.addAll(element.getRequiredInterfaces());

      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, element.getComponentExchanges(), context);
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, element.getProvidedInterfaces(), context);
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, element.getRequiredInterfaces(), context);
    }
  }

}
