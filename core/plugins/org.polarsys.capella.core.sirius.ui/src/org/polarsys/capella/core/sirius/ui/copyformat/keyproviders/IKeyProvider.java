/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.ui.copyformat.keyproviders;

import java.util.Collection;

import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;

/**
 * Retrieve all keys which can be used as a layout provider for the given key.
 * For instance, if a layout of a Logical Function is not found, layout from its System Function can also be used.
 */
public interface IKeyProvider {

  /**
   * Returns all other keys which can be used for the given key
   */
  public Collection<FormatDataKey> getKeys(FormatDataKey key);

}
