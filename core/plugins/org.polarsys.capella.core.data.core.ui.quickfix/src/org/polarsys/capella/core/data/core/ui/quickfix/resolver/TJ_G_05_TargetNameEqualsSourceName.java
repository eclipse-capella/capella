/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.ui.quickfix.resolver;

import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 * rename target name
 */
public class TJ_G_05_TargetNameEqualsSourceName extends Abstract_TJ_G_05 {

  /**
   * {@inheritDoc}
   */
  @Override
  public void rename(AbstractNamedElement sourceNamedElement_p, AbstractNamedElement targetNamedElement_p) {
    targetNamedElement_p.setName(sourceNamedElement_p.getName());
  }

}
