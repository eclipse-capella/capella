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
package org.polarsys.capella.core.transition.system.rules.information.datatype;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class NumericTypeRule extends DataTypeRule {

  @Override
  protected EClass getSourceType() {
    return DatatypePackage.Literals.NUMERIC_TYPE;
  }

  public NumericTypeRule() {
    super();
    registerUpdatedAttribute(DatatypePackage.Literals.NUMERIC_TYPE__KIND);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    NumericType element = (NumericType) source_p;
    result_p.add(element.getOwnedDefaultValue());
    result_p.add(element.getOwnedMaxValue());
    result_p.add(element.getOwnedMinValue());
    result_p.add(element.getOwnedNullValue());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedDefaultValue(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMaxValue(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMinValue(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedNullValue(), context_p);

    }

  }
}
