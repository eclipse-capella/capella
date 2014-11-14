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
package org.polarsys.capella.common.ui.toolkit.viewers.data;

/**
 * Provides services to move data.
 */
public interface IMoveableData {
  /**
   * Swap elements between specified indexes.
   * @param element_p element to swap.
   * @param index_p current position.
   * @param newIndex_p new position.
   */
  public void swap(Object element_p, int index_p, int newIndex_p);
}
