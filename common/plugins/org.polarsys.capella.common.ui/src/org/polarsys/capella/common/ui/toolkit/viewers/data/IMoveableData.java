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

package org.polarsys.capella.common.ui.toolkit.viewers.data;

/**
 * Provides services to move data.
 */
public interface IMoveableData {
  /**
   * Swap elements between specified indexes.
   * @param element element to swap.
   * @param index current position.
   * @param newIndex new position.
   */
  public void swap(Object element, int index, int newIndex);
}
