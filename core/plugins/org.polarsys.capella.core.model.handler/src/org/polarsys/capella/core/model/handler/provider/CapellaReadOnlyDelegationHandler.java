/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.handler.provider;

import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.IReadOnlyDelegationHandler;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * Specific read only handler for Capella resources.
 * @see CapellaResourceHelper
 */
public class CapellaReadOnlyDelegationHandler implements IReadOnlyDelegationHandler {
  /**
   * @see org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.IReadOnlyDelegationHandler#isReadOnly(org.eclipse.emf.ecore.resource.Resource)
   */
  public boolean isReadOnly(Resource resource_p) {
    // Always answer NOT in RO for Capella resource to allow all EMF commands to be executed even if the resource is in RO.
    // A pre-commit listener ensures the resource will be checked out. If not (end-user turned down the check-out) the transaction will be rolled back.
    return CapellaResourceHelper.isCapellaResource(resource_p) ? false : EcoreUtil2.isReadOnly(resource_p);
  }
}
