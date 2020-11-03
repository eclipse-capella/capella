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

package org.polarsys.capella.common.ef.internal.domain;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.domain.IEditingDomainListener;

/**
 * This class removes the ExecutionManager from the Registry when the editing domain is disposed
 */
public class ExecutionManagerEditingDomainListener implements IEditingDomainListener {

  @Override
  public void createdEditingDomain(EditingDomain editingDomain) {
    //Nothing here
  }

  @Override
  public void disposedEditingDomain(EditingDomain editingDomain) {
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().getExecutionManager(editingDomain);
    if (manager != null) {
      ExecutionManagerRegistry.getInstance().removeManager(manager);
    }
  }

}
