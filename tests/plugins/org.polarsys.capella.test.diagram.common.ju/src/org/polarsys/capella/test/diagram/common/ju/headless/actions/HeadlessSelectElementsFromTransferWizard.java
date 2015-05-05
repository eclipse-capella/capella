/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.headless.actions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.polarsys.capella.core.sirius.analysis.actions.extensions.SelectElementsFromTransferWizard;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.headless.ITransfertWizardResult;

/**
 * SelectElementsFromTransferWizard specialization in order to avoid any ui call.
 */
public class HeadlessSelectElementsFromTransferWizard extends SelectElementsFromTransferWizard {

  @Override
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

    EObject context = (EObject) parameters.get("context"); //$NON-NLS-1$
    String resultVariable = (String) parameters.get("resultVariable"); //$NON-NLS-1$
    
    Assert.isNotNull(context);
    Assert.isNotNull(resultVariable);
    
    IHeadlessResult itwr = HeadlessResultOpProvider.INSTANCE.getCurrentOp(); 

    Object result = ((ITransfertWizardResult) itwr).getResult(selections, parameters); 
   
    InterpreterUtil.getInterpreter(context).setVariable(resultVariable, result);
  }
}
