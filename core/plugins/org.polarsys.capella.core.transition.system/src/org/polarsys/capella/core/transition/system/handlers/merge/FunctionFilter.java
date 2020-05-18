/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.transition.system.preferences.PreferenceConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class FunctionFilter extends EObjectCategoryFilter {

  public FunctionFilter(IContext context) {
    super(context, CtxPackage.Literals.SYSTEM_FUNCTION, PreferenceConstants.P_F_TEXT);
  }

  @Override
  public boolean keepElement(Object element) {
    return element instanceof AbstractFunction;
  }

}
