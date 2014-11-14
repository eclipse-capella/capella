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
package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.Collection;

/**
 */
public interface IDeleteCommandDelegation {
  /**
   * Allow to implement additional behavior at pre execution time of the {@link CapellaDeleteCommand}.<br>
   * This method is called at the beginning of {@link CapellaDeleteCommand#doExecute()}.
   * @param elementsToDelete_p
   * @return <code>null</code> means preExecute succeeds; otherwise an error message is returned.
   */
  public String preExecute(Collection<?> elementsToDelete_p);

}
