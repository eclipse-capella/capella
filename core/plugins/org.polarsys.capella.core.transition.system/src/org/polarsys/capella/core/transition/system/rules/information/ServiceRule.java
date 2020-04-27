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
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    Service element = (Service) source;
    result.addAll(element.getOwnedParameters());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedParameters(), context);

    }
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
  }

}
