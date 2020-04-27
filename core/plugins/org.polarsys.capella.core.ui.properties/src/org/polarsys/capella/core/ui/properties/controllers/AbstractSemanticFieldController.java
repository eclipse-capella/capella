/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.controllers;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public abstract class AbstractSemanticFieldController {

  /**
   * @param value
   * @return
   */
  protected boolean editValueWizard(EObject value) {
    return CapellaUIPropertiesPlugin.getDefault().getCustomWizardHandler().openWizard((ModelElement) value);
  }
}
