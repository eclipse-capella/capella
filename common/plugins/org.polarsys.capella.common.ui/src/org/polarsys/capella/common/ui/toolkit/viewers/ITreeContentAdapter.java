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
