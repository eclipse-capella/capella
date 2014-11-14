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
package org.polarsys.capella.core.ui.toolkit.decomposition;

/**
 * Class <code>DecompositionModelListener</code> serves as the listener for all the events occurring in the <code>DecompositionModel</code>
 * @see DecompositionModel#addDecompositionModelListener(DecompositionModelListener)
 *
 */
public interface DecompositionModelListener {
  /**
   * Invoked when some change happens in the <code>DecompositionModel</code>
   * @param event_p the DecompositionModelEvent
   */
  public void decompositionChanged(DecompositionModelEvent event_p);
}
