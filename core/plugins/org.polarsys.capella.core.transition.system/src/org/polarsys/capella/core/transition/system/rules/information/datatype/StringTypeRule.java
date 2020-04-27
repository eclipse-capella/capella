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

package org.polarsys.capella.core.transition.system.rules.information.datatype;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class StringTypeRule extends DataTypeRule {

  @Override
  protected EClass getSourceType() {
    return DatatypePackage.Literals.STRING_TYPE;
  }

  public StringTypeRule() {
    super();
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    StringType element = (StringType) source;
    result.add(element.getOwnedDefaultValue());
    result.add(element.getOwnedMaxLength());
    result.add(element.getOwnedMinLength());
    result.add(element.getOwnedNullValue());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedDefaultValue(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMaxLength(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMinLength(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedNullValue(), context);
    }

  }
}
