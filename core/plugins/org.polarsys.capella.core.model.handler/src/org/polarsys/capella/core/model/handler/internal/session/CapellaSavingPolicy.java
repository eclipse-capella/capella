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
package org.polarsys.capella.core.model.handler.internal.session;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.SavingPolicyImpl;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;

/**
 * Custom saving policy base on {@link ResourceSetSync} status.
 */
public class CapellaSavingPolicy extends SavingPolicyImpl {
  /**
   * Constructor.
   * @param domain_p
   */
  CapellaSavingPolicy(TransactionalEditingDomain domain_p) {
    super(domain_p);
  }

  /**
   * Overridden to avoid changes detection based on temporary file creation.<br> {@inheritDoc}
   */
  @Override
  protected boolean hasChangesToSave(Resource resource_p) {
    boolean result = false;
    if (!ResourceSetSync.isReadOnly(resource_p)){
      ResourceStatus resourceStatus = ResourceSetSync.getStatus(resource_p);
      // If resource status different from SYNC, there are some changes to save.
      if (ResourceStatus.SYNC != resourceStatus) {
        result = true;
      }
    }
    return result;
  }
}
