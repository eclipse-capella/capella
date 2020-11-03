/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.gen.edit.decorators;

import org.eclipse.emf.edit.provider.IItemLabelProvider;

/**
 * @author Joao Barata
 */
public interface IDelegatedDecorator extends IItemLabelProvider {

  /**
   * This service allows to enable or disable this decorator according to a given context
   * @param object the object context
   * @return TRUE if this decorator has to be activated, FALSE otherwise
   */
  boolean appliesTo(Object object);
}
