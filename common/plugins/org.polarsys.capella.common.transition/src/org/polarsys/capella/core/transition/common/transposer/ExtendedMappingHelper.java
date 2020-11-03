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

package org.polarsys.capella.core.transition.common.transposer;

import java.util.Collection;

import org.polarsys.capella.core.transition.common.capellaHelpers.HashMapSet;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.Mapping;

/**
 *
 */
public class ExtendedMappingHelper {

  static HashMapSet<Mapping, Mapping> maps = new HashMapSet<Mapping, Mapping>();

  public static Collection<Mapping> getExtendedMappings(Mapping mapping) {
    return maps.get(mapping);
  }

  /**
   * @param currentMapping
   * @param extendedMapping
   */
  public static void addExtendedMapping(Mapping currentMapping, Mapping extendedMapping) {
    if ((currentMapping != null) && (extendedMapping != null)) {
      maps.put(currentMapping, extendedMapping);
      currentMapping.setExtendedMapping(extendedMapping);
      System.out.println(currentMapping.getId() + " extends " + extendedMapping.getId());
    }
  }

}
