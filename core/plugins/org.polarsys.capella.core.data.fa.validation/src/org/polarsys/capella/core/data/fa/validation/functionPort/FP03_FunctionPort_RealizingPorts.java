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
package org.polarsys.capella.core.data.fa.validation.functionPort;

import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPortExt;

/**
 */
public class FP03_FunctionPort_RealizingPorts extends FP03_FunctionPort {

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean validate(IValidationContext ctx_p, AbstractFunction fct1_p, FunctionPort fp1_p, AbstractFunction fct2_p, FunctionPort fp2_p) {
    if (FunctionExt.getRealizingFunctions(fct1_p).contains(fct2_p)) {
      if (!FunctionPortExt.getRealizingPorts(fp1_p).contains(fp2_p)) {
        return false;
      }
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getMessagePattern() {
    return "{0} (Function Port) on {1} (Function) shall be realized by {2} (Function Port) on {3} (Function)"; //$NON-NLS-1$
  }
}
