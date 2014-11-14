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

import java.util.Collection;

import org.polarsys.capella.core.transition.common.capellaHelpers.HashMapSet;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.Mapping;

/**
 *
 */
public class ExtendedMappingHelper {

  static HashMapSet<Mapping, Mapping> _maps = new HashMapSet<Mapping, Mapping>();

  public static Collection<Mapping> getExtendedMappings(Mapping mapping_p) {
    return _maps.get(mapping_p);
  }

  /**
   * @param currentMapping_p
   * @param extendedMapping_p
   */
  public static void addExtendedMapping(Mapping currentMapping_p, Mapping extendedMapping_p) {
    if ((currentMapping_p != null) && (extendedMapping_p != null)) {
      _maps.put(currentMapping_p, extendedMapping_p);
      currentMapping_p.setExtendedMapping(extendedMapping_p);
      System.out.println(currentMapping_p.getId() + " extends " + extendedMapping_p.getId());
    }
  }

}
