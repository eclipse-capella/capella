/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.handler.helpers;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.shared.id.handler.IScope;

/**
 * A scope browsing only semantic resources on the given resource set
 */
public class SemanticResourcesScope implements IScope {

  private ResourceSet _set;

  public SemanticResourcesScope(ResourceSet set) {
    _set = set;
  }

  @Override
  public List<Resource> getResources() {
    return _set.getResources().stream().filter(x -> CapellaResourceHelper.isCapellaResource(x))
        .collect(Collectors.toList());
  }

}
