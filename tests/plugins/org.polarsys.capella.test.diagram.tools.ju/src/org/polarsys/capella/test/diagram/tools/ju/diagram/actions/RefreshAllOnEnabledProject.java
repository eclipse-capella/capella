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
package org.polarsys.capella.test.diagram.tools.ju.diagram.actions;

/**
 * Verify that after the "Refresh All Sub Representations" action, the option "Refresh on opening" on project setting is
 * still enabled
 */
public class RefreshAllOnEnabledProject extends RefreshAllOnDisabledProject {

  @Override
  protected boolean getValue() {
    return true;
  }
}
