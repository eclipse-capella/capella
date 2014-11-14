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
package org.polarsys.capella.core.validation.commandline;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaValidateAction;

/**
 */
public class CapellaValidateComlineAction extends CapellaValidateAction {

  private Resource resourceToValidate;

  public void setSelectedObjects(List<EObject> selectedObjects_) {
    selectedObjects = selectedObjects_;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {

    // works fine
    Diagnostic diagnostic = super.validate(new NullProgressMonitor());
    handleDiagnostic(diagnostic);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleDiagnostic(Diagnostic diagnostic_p) {
    String outputFolder = "";
    List<?> data = diagnostic_p.getData();
    String message = diagnostic_p.getMessage();

    // createMarkers
    for (Diagnostic childDiagnostic : diagnostic_p.getChildren()) {
      eclipseResourcesUtil.createMarkers(resourceToValidate, childDiagnostic);
    }
  }

  /**
   * @param airdSemanticModel_p
   */
  public void setResource(Resource airdSemanticModel_p) {
    resourceToValidate = airdSemanticModel_p;

  }
}
