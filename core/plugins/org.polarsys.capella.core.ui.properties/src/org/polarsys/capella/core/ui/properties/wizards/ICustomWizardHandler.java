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
package org.polarsys.capella.core.ui.properties.wizards;

import org.eclipse.emf.ecore.EObject;

/**
 */
public interface ICustomWizardHandler {
  /**
   * Open a custom wizard for given model element.
   * @param modelElement_p must be not <code>null</code>.
   * @return <code>true</code> means the wizard was successfully finished.
   */
  public boolean openWizard(EObject object_p);
}
