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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 *
 */
public class MultiplicityElementRule extends AbstractCapellaElementRule {

  public MultiplicityElementRule() {
    super();
    registerUpdatedAttribute(InformationPackage.Literals.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE);
    registerUpdatedAttribute(InformationPackage.Literals.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE);
    registerUpdatedAttribute(InformationPackage.Literals.MULTIPLICITY_ELEMENT__UNIQUE);
    registerUpdatedAttribute(InformationPackage.Literals.MULTIPLICITY_ELEMENT__ORDERED);
  }

  @Override
  protected EClass getSourceType() {
    return InformationPackage.Literals.MULTIPLICITY_ELEMENT;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    MultiplicityElement element = (MultiplicityElement) source;
    result.add(element.getOwnedMinCard());
    result.add(element.getOwnedMaxCard());
    result.add(element.getOwnedDefaultValue());
    result.add(element.getOwnedMaxLength());
    result.add(element.getOwnedMaxValue());
    result.add(element.getOwnedMinLength());
    result.add(element.getOwnedMinValue());
    result.add(element.getOwnedNullValue());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMinCard(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMaxCard(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedDefaultValue(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMaxLength(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMaxValue(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMinLength(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMinValue(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedNullValue(), context);
    }

  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
  }
}
