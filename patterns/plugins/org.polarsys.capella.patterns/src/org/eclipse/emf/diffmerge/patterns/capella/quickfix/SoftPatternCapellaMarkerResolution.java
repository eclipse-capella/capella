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
package org.eclipse.emf.diffmerge.patterns.capella.quickfix;


/**
 * A quick fix for pattern instance issues that preserves the model elements covered by the instances.
 */
public class SoftPatternCapellaMarkerResolution extends PatternCapellaMarkerResolution {

  /**
   * @see org.eclipse.emf.diffmerge.patterns.capella.quickfix.PatternCapellaMarkerResolution#shouldKeepElements()
   */
  @Override
  protected boolean shouldKeepElements() {
    return true;
  }

}
