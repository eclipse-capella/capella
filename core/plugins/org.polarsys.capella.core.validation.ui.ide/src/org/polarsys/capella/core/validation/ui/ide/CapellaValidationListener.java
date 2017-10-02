/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.validation.ui.ide;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IValidationListener;
import org.eclipse.emf.validation.service.ValidationEvent;
import org.eclipse.ui.statushandlers.StatusManager;

public class CapellaValidationListener implements IValidationListener {

  @Override
  public void validationOccurred(ValidationEvent event) {

    if (event.getEvaluationMode() == EvaluationMode.LIVE) {
      if (event.getSeverity() >= IStatus.ERROR) {
        IStatus forManager = null;
        if (event.getValidationResults().size() > 1) {
          forManager = new MultiStatus(PluginActivator.getDefault().getPluginId(), 1, "Live Validation Errors", null);
          for (IStatus s : event.getValidationResults()) {
            ((MultiStatus)forManager).add(s);
          }
        } else {
          forManager = event.getValidationResults().iterator().next();
        }
        StatusManager.getManager().handle(forManager, StatusManager.SHOW);
      }
    }
  }

}
