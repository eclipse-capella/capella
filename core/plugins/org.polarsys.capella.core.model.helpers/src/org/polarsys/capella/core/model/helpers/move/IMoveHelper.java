/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers.move;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

public interface IMoveHelper {
  /**
   * @param selectedModelElements
   * @param inputTargetElement
   * @return
   */
  public IStatus checkSemanticRules(List<EObject> selectedElements, EObject targetObject);
  
  /**
   * @param selectedModelElements
   * @param targetElement
   */
  public IStatus checkEMFRules(List<EObject> selectedModelElements, EObject targetElement);

}
