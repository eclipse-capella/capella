/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.transposer;

import java.util.Map;

import org.polarsys.capella.core.transition.common.transposer.current.GenericPurposeRegistry;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.Mapping;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingElement;

public final class ExtendedPurposeRegistry extends GenericPurposeRegistry {

  protected ExtendedPurposeRegistry() {
    super();
  }

  public static ExtendedPurposeRegistry getInstance() {
    return __instance;
  }

  protected static ExtendedPurposeRegistry __instance = new ExtendedPurposeRegistry();

  @Override
  public void init() {
    reset();
    getContributedPurposes().addAll(new ExtendedRuleMappingExtensionService().getContributedPurposes());
  }

  @Override
  protected void selectContributedMappingElementsToUse(Map contributedMappingsElements, Mapping mapping1) {
    for (Mapping mapping : ExtendedMappingHelper.getExtendedMappings(mapping1)) {
      selectContributedMappingElementsToUse(contributedMappingsElements, mapping);
    }

    for (MappingElement currentElement : mapping1.getAllOwnedMappingElements()) {
      contributedMappingsElements.put(currentElement.getDomainMetaClass(), currentElement);
    }
  }
}
