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
  protected void selectContributedMappingElementsToUse(Map contributedMappingsElements_p, Mapping mapping_p) {
    for (Mapping mapping : ExtendedMappingHelper.getExtendedMappings(mapping_p)) {
      selectContributedMappingElementsToUse(contributedMappingsElements_p, mapping);
    }

    for (MappingElement currentElement : mapping_p.getAllOwnedMappingElements()) {
      contributedMappingsElements_p.put(currentElement.getDomainMetaClass(), currentElement);
    }
  }
}
