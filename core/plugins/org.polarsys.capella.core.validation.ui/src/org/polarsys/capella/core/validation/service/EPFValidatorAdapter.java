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
package org.polarsys.capella.core.validation.service;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IBatchValidator;

import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.core.validation.CapellaValidatorAdapter;

/**
 */
public class EPFValidatorAdapter extends CapellaValidatorAdapter {

  /**
     * 
     */
  public EPFValidatorAdapter() {
  }

  /**
   * @see org.polarsys.capella.core.validation.EValidatorAdapter#appendDiagnostics(org.eclipse.core.runtime.IStatus,
   *      org.eclipse.emf.common.util.DiagnosticChain)
   */
  @Override
  protected void appendDiagnostics(IStatus status, DiagnosticChain diagnostics) {
    // Deal recursively with multi status.
    if (status.isMultiStatus()) {
      IStatus[] children = status.getChildren();
      for (IStatus element : children) {
        appendDiagnostics(element, diagnostics);
      }
    } else if (status instanceof IConstraintStatus) {
      diagnostics.add(new ConstraintStatusDiagnostic((IConstraintStatus) status));
    }

  }

  /**
   * @see org.polarsys.capella.core.validation.EValidatorAdapter#getValidator()
   */
  @Override
  public IBatchValidator getValidator() {

    return super.getValidator();
  }

}
