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
package org.polarsys.capella.test.framework.actions.headless;

import org.eclipse.emf.common.util.Diagnostic;
import org.polarsys.capella.core.validation.ui.actions.CapellaValidateAction;

/**
 * @author Erwan Brottier
 */
public class HeadlessCapellaValidateAction extends CapellaValidateAction {

  protected Diagnostic diagnostic;

  /**
   * Overrided to not show dialog and keep diagnostic to allow getting it after
   * execution.
   */
  @Override
  protected void handleDiagnostic(Diagnostic diagnostic_p) {
    diagnostic = diagnostic_p;
  }

  public Diagnostic getDiagnostic() {
    return diagnostic;
  }
}