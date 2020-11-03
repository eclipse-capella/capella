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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

/**
 * Delete functional chain involvement
 */
public class DWF_DF_04_Resolver extends AbstractDeleteCommandResolver {
 
  /**
   * {@inheritDoc}
   */
  @Override
  public Object getElementToDelete(Object obj) {
    if (obj instanceof FunctionalChainInvolvement) {
      return obj;
    }
    return null;
  }

  
}
