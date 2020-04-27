/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.handlers.scope;

import org.eclipse.core.runtime.IStatus;
import org.polarsys.capella.core.transition.common.ExtensionHelper;
import org.polarsys.capella.core.transition.common.constants.ISchemaConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ExtendedScopeHandler extends DefaultScopeHandler {

  String mapping;
  String purpose;

  public ExtendedScopeHandler(String mapping, String purpose) {
    this.mapping = mapping;
    this.purpose = purpose;
  }

  @Override
  public IStatus init(IContext context) {

    for (Object handler : ExtensionHelper.collectFromExtensions(context, ISchemaConstants.EXTENSION_ID,
        ISchemaConstants.SCOPE_RETRIEVER, purpose, mapping)) {
      if (handler instanceof IScopeRetriever) {
        addScopeRetriever((IScopeRetriever) handler, context);
      }
    }

    for (Object handler : ExtensionHelper.collectFromExtensions(context, ISchemaConstants.EXTENSION_ID,
        ISchemaConstants.SCOPE_FILTER, purpose, mapping)) {
      if (handler instanceof IScopeFilter) {
        addScopeFilter((IScopeFilter) handler, context);
      }
    }

    return super.init(context);
  }

}
