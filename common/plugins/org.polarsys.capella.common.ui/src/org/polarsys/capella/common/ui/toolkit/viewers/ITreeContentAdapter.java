/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.toolkit.viewers;

/**
 * Adapter for Tree-based representation.
 */
public interface ITreeContentAdapter {
  /**
   * Get parent of specified child in given context.
   * @param child
   * @param context
   * @return <code>null</code> if no parent found.
   */
  public Object getParent(Object child, Object context);
}
