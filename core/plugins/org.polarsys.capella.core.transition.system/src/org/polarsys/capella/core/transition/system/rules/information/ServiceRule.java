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
package org.polarsys.capella.core.transition.system.rules.information;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ServiceRule extends AbstractCapellaElementRule {

  public ServiceRule() {
    super();
    registerUpdatedAttribute(CapellacorePackage.Literals.FEATURE__IS_ABSTRACT);
    registerUpdatedAttribute(CapellacorePackage.Literals.FEATURE__IS_STATIC);
    registerUpdatedAttribute(CapellacorePackage.Literals.FEATURE__VISIBILITY);
    registerUpdatedAttribute(InformationPackage.Literals.SERVICE__SYNCHRONISM_KIND);

  }

  @Override
  protected EClass getSourceType() {
    return InformationPackage.Literals.SERVICE;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    Service element = (Service) source_p;
    result_p.addAll(element.getOwnedParameters());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedParameters(), context_p);

    }
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
  }

}
