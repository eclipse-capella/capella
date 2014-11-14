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
package org.polarsys.capella.core.model.handler.provider;

import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.IReadOnlyDelegationHandler;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * Specific read only handler for Capella resources.
 * @see CapellaResourceHelper
 */
public class CapellaReadOnlyDelegationHandler implements IReadOnlyDelegationHandler {
  /**
   * @see org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.IReadOnlyDelegationHandler#isReadOnly(org.eclipse.emf.ecore.resource.Resource)
   */
  public boolean isReadOnly(Resource resource_p) {
    // Always answer NOT in RO for Capella resource to allow all EMF commands to be executed even if the resource is in RO.
    // A pre-commit listener ensures the resource will be checked out. If not (end-user turned down the check-out) the transaction will be rolled back.
    return CapellaResourceHelper.isCapellaResource(resource_p) ? false : EcoreUtil2.isReadOnly(resource_p);
  }
}
