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
package org.polarsys.capella.core.ui.metric.utils;

import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;

public class SetProgressRunSetup {

  private EnumerationPropertyLiteral enumPropertyLiteral;
  private boolean propagateWithoutFiltering;
  private boolean propagateToRepresentations;
  
  public SetProgressRunSetup(EnumerationPropertyLiteral enumPropertyLiteral, boolean propagateWithoutFiltering, boolean propagateToRepresentations){
    this.enumPropertyLiteral = enumPropertyLiteral;
    this.propagateWithoutFiltering = propagateWithoutFiltering;
    this.propagateToRepresentations = propagateToRepresentations;
  }
  
  public EnumerationPropertyLiteral getEnumPropertyLiteral(){
    return enumPropertyLiteral;
  }
  
  public boolean isPropagateWithoutFiltering(){
    return propagateWithoutFiltering;
  }
  
  public boolean isPropagateToRepresentations(){
    return propagateToRepresentations;
  }
}
