/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.libraries.manager;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.ef.domain.IEditingDomainListener;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.policy.ResourceAccessPolicyListener;

public class LibraryEditingDomainListener implements IEditingDomainListener {

  private ResourceAccessPolicyListener listener = new ResourceAccessPolicyListener();

  /**
   * @see org.polarsys.capella.common.ef.domain.IEditingDomainListener#createdEditingDomain(EditingDomain)
   */
  @Override
  public void createdEditingDomain(EditingDomain editingDomain) {
    ((TransactionalEditingDomain) editingDomain).addResourceSetListener(listener);

    ILibraryManager manager = ILibraryManager.INSTANCE;
    if (manager instanceof IEditingDomainListener) {
      ((IEditingDomainListener) manager).createdEditingDomain(editingDomain);
    }
  }

  /**
   * @see org.polarsys.capella.common.ef.domain.IEditingDomainListener#disposedEditingDomain(EditingDomain)
   */
  @Override
  public void disposedEditingDomain(EditingDomain editingDomain) {
    ((TransactionalEditingDomain) editingDomain).removeResourceSetListener(listener);

    ILibraryManager manager = ILibraryManager.INSTANCE;
    if (manager instanceof IEditingDomainListener) {
      ((IEditingDomainListener) manager).disposedEditingDomain(editingDomain);
    }
  }
}
