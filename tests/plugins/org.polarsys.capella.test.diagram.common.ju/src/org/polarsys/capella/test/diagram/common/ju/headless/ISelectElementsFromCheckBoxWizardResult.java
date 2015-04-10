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
package org.polarsys.capella.test.diagram.common.ju.headless;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

/**
 * TODO to refactor, waiting for solution from sirius.analysis
 */
public interface ISelectElementsFromCheckBoxWizardResult extends IHeadlessResult {

  /** 
   * the simulated result. 
   * @param selections @see {@link SelectElementsFromListWizard}
   * @param parameters @see {@link SelectElementsFromListWizard}
   * @return the "as" selected element list.
   * @see {@link SelectElementsFromListWizard}
   */
  public Object getResult(Collection<? extends EObject> selections, Map<String, Object> parameters);
}
