/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.system.rules.information.datatype;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class EnumerationRule extends DataTypeRule {

  @Override
  protected EClass getSourceType() {
    return DatatypePackage.Literals.ENUMERATION;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    Enumeration element = (Enumeration) source;
    result.add(element.getOwnedDefaultValue());
    result.addAll(element.getOwnedLiterals());
    result.add(element.getOwnedMinValue());
    result.add(element.getOwnedMaxValue());
    result.add(element.getOwnedNullValue());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedDefaultValue(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedLiterals(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMinValue(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMaxValue(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedNullValue(), context);
    }

  }
}
