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
package org.polarsys.capella.core.model.handler.command;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * An contributor to CapellaDeleteCommand. Interested parties may register their
 * delegation via the deleteCommandDelegation extension point.
 * 
 */
public abstract class AbstractCapellaDeleteHook {

  /**
   * Informs about immediate deletion of the objects in the parameter.
   * Implementors can cancel the deletion of objects by returning an 
   * IStatus with a status code other than IStatus.OK. 
   * 
   * @param elementsToDelete. Never null.
   * @return Status.OK_STATUS if the delete should proceed. Any other status
   * if the delete should be cancelled.
   */
  public IStatus preDelete(Collection<?> elementsToDelete) {
    return Status.OK_STATUS;
  }

}
