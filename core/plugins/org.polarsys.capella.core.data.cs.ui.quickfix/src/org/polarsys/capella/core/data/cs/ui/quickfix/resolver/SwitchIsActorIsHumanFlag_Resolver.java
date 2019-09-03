/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import org.polarsys.capella.core.data.cs.Component;

/**
 * Delete physical path involvement
 */
public class SwitchIsActorIsHumanFlag_Resolver extends SwitchHumanActorLabels_Resolver {
  protected boolean isActor;
  protected boolean isHuman;
  protected String label;

  public SwitchIsActorIsHumanFlag_Resolver(String label, boolean isActor, boolean isHuman) {
    this.label = label;
    this.isActor = isActor;
    this.isHuman = isHuman;
  }

  @Override
  protected void switchLabel(Component component) {
    component.setActor(isActor);
    component.setHuman(isHuman);
  }

  @Override
  public String getLabel() {
    return label;
  }
}
