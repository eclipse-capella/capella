/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.semantic.prefs;

/**
 */
public class SemanticModelPreferencesMock implements ISemanticModelPreferences {

  private boolean _semanticMode;

  /**
   * {@inheritDoc}
   */
  public void setSemanticMode(boolean enabled_p) {
    _semanticMode = enabled_p;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isSemanticMode() {
    return _semanticMode;
  }

}
