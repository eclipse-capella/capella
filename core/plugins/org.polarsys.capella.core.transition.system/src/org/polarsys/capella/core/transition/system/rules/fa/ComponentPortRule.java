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
  protected void retrieveRequired(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveRequired(element_p, result_p, context_p);
  }

  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.COMPONENT_PORT;
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InformationPackage.Literals.PORT__PROVIDED_INTERFACES, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InformationPackage.Literals.PORT__REQUIRED_INTERFACES, context_p);
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, InformationPackage.Literals.PORT__PROVIDED_INTERFACES));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, InformationPackage.Literals.PORT__REQUIRED_INTERFACES));
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    ComponentPort element = (ComponentPort) source_p;

    result_p.addAll(element.getIncomingPortAllocations());
    result_p.addAll(element.getOutgoingPortAllocations());

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
      result_p.addAll(element.getComponentExchanges());
      result_p.addAll(element.getProvidedInterfaces());
      result_p.addAll(element.getRequiredInterfaces());

      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getComponentExchanges(), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getProvidedInterfaces(), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getRequiredInterfaces(), context_p);
    }
  }

}
