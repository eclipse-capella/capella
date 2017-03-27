/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

  @Override
  protected PhysicalComponentNature getPhysicalComponentNature() {
    return PhysicalComponentNature.NODE;
  }
}
