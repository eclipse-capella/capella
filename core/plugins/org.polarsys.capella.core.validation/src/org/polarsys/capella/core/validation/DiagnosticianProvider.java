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
package org.polarsys.capella.core.validation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.util.Diagnostician;

import org.polarsys.capella.core.model.handler.validation.AbstractDiagnosticianProvider;
import org.polarsys.capella.core.model.handler.validation.CapellaDiagnostician;

/**
 * Provide a Diagnostician to Capella validate actions via the diagnosticianProvider extension point. 
 * A side-effect of using this extension point is that we ensure that this plugin can make any required 
 * modifications to the global EValidator.Registry before any validation action is run.
 * @see CapellaValidationActivator
 */
public class DiagnosticianProvider extends AbstractDiagnosticianProvider {

  @Override
  public Diagnostician getDiagnostician(AdapterFactory adapterFactory_p, IProgressMonitor progressMonitor_p) {
    // just return a capella diagnostician..
    // the wanted side effect is that the plugin has been activated at this point
    return new CapellaDiagnostician(adapterFactory_p, progressMonitor_p);
  }

}
