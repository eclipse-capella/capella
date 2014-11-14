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
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    MultiplicityElement element = (MultiplicityElement) source_p;
    result_p.add(element.getOwnedMinCard());
    result_p.add(element.getOwnedMaxCard());
    result_p.add(element.getOwnedDefaultValue());
    result_p.add(element.getOwnedMaxLength());
    result_p.add(element.getOwnedMaxValue());
    result_p.add(element.getOwnedMinLength());
    result_p.add(element.getOwnedMinValue());
    result_p.add(element.getOwnedNullValue());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMinCard(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMaxCard(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedDefaultValue(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMaxLength(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMaxValue(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMinLength(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMinValue(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedNullValue(), context_p);
    }

  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
  }
}
