/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import org.polarsys.capella.core.data.cs.Component;

/**
 * Delete physical path involvement
 */
public class SwitchIsHumanFlag_Resolver extends SwitchHumanActorLabels_Resolver {

  @Override
  protected void switchLabel(Component component) {
    component.setHuman(!component.isHuman());
  }
}
