/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.helpers;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.HoldingResource;

public class ExternalResourceHelper {

  public static Resource getExternalResource(TransactionalEditingDomain domain, String identifier) {
    return getExternalResource(domain, identifier, true);
  }

  protected static Resource getExternalResource(final TransactionalEditingDomain domain, String identifier,
      boolean create) {

    final URI uri = URI.createURI(
        "capella://capella/external_" + identifier + "." + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION);

    Resource hresource = domain.getResourceSet().getResource(uri, false);
    if (hresource == null) {
      ExecutionManagerRegistry.getInstance().getExecutionManager(domain).execute(new AbstractNonDirtyingCommand() {
        public void run() {
          HoldingResource resource = new HoldingResource(uri);
          domain.getResourceSet().getResources().add(resource);
        }
      });
    }

    hresource = domain.getResourceSet().getResource(uri, false);
    return hresource;
  }

  public static void attachToResource(EObject element, Resource resource) {
    if (resource != null) {
      resource.getContents().add(element);
    }
  }
}
