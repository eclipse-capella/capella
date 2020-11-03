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
package org.polarsys.capella.core.data.core.ui.quickfix.resolver;

import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 * rename source name
 */
public class TJ_G_05_SourceNameEqualsTargetName extends Abstract_TJ_G_05 {

  /**
   * {@inheritDoc}
   */
  @Override
  public void rename(AbstractNamedElement sourceNamedElement, AbstractNamedElement targetNamedElement) {
    sourceNamedElement.setName(targetNamedElement.getName());
  }

}
