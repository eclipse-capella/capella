/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures;

import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.util.ModellingcoreSwitch;

/**
 * Computes signatures for elements in the Modellingcore package.
 * 
 * - The signature for an AbstractNamedElement is its name
 * 
 */
public class ModellingcoreSignatures extends ModellingcoreSwitch<String>{
  
  private boolean lowerCaseNames;
  
  /**
   * @param lowerCaseNames whether signatures should be all lowercase.
   */
  public ModellingcoreSignatures(boolean lowerCaseNames){
    this.lowerCaseNames = lowerCaseNames;
  }
  
  @Override
  public String caseAbstractNamedElement(AbstractNamedElement elem){
    String result = elem.getName();
    if (lowerCaseNames && result != null){
      result = result.toLowerCase();
    }
    return result;
  }

}
