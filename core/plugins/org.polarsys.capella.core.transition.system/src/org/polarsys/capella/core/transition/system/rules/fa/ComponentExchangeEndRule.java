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

import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class ComponentExchangeEndRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.COMPONENT_EXCHANGE_END;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      ComponentExchangeEnd element = (ComponentExchangeEnd) source;
      result.add(element.getPart());
      result.add(element.getPort());
      ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, element.getPart(), context);
      ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, element.getPort(), context);
    }
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, FaPackage.Literals.COMPONENT_EXCHANGE_END__PART, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, FaPackage.Literals.COMPONENT_EXCHANGE_END__PORT, context);
  }

  @Override
  protected void premicesRelated(EObject eObject1, ArrayList<IPremise> needed) {
    super.premicesRelated(eObject1, needed);
    ComponentExchangeEnd element = (ComponentExchangeEnd) eObject1;
    needed.addAll(createDefaultPrecedencePremices(element, FaPackage.Literals.COMPONENT_EXCHANGE_END__PART));
    needed.addAll(createDefaultPrecedencePremices(element, FaPackage.Literals.COMPONENT_EXCHANGE_END__PORT));
  }
}
