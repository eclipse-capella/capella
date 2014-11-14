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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

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
    InterpreterUtil.getInterpreter(context).setVariable(variable, value);
  }

}
