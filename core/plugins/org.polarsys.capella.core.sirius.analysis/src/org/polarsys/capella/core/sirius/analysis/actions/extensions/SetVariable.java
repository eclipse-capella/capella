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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;

/**
 */
public class SetVariable extends AbstractExternalJavaAction {
  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  public void execute(Collection<? extends EObject> selections_p, Map<String, Object> parameters_p) {
    EObject context = (EObject) parameters_p.get(CONTEXT);
    String variable = (String) parameters_p.get(VARIABLE);
    Object value = parameters_p.get(VALUE);
    Assert.isNotNull(context);
    Assert.isNotNull(variable);
    // for acceleo2aql
    if (value instanceof Collection)
    	value = new ArrayList((Collection)value);
    InterpreterUtil.getInterpreter(context).setVariable(variable, value);
  }

}
