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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class PhysicalPortRule extends AbstractCapellaElementRule {

  public PhysicalPortRule() {
  }

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.PHYSICAL_PORT;
  }

  @Override
  protected void retrieveRequired(EObject element, List<EObject> result, IContext context) {
    super.retrieveRequired(element, result, context);
    result.add(element.eContainer());
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    PhysicalPort element = (PhysicalPort) source;

    result.addAll(element.getIncomingPortAllocations());
    result.addAll(element.getOutgoingPortAllocations());

    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, element, context)) {
      result.addAll(element.getInvolvedLinks());
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE, element.getInvolvedLinks(), context);

      result.addAll(element.getProvidedInterfaces());
      result.addAll(element.getRequiredInterfaces());
    }
  }

}
