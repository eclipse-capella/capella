/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
