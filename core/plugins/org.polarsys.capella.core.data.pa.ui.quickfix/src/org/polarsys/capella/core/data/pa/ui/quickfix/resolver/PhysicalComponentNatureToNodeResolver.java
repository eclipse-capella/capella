/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.pa.ui.quickfix.resolver;

import org.polarsys.capella.core.data.pa.PhysicalComponentNature;

/**
 * Change the nature of the Physical Component to {@link PhysicalComponentNature.NODE}
 */
public class PhysicalComponentNatureToNodeResolver extends AbstractPhysicalComponentNatureChangeResolver {
  private String label = "Switch to NODE";

  @Override
  protected PhysicalComponentNature getPhysicalComponentNature() {
    return PhysicalComponentNature.NODE;
  }

  @Override
  public String getLabel() {
    return this.label;
  }
}
