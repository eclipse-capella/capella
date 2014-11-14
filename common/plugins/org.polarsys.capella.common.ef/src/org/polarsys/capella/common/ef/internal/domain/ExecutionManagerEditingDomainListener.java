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
  public void createdEditingDomain(EditingDomain editingDomain_p) {
    //Nothing here
  }

  @Override
  public void disposedEditingDomain(EditingDomain editingDomain_p) {
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().getExecutionManager(editingDomain_p);
    if (manager != null) {
      ExecutionManagerRegistry.getInstance().removeManager(manager);
    }
  }

}
