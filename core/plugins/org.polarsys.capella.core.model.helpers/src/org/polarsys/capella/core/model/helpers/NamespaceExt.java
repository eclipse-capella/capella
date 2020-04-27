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

package org.polarsys.capella.core.model.helpers;

import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Trace;

/**
 * Utility class for {@link NamespaceExt} element
 *
 */
public class NamespaceExt {


  /**
   * Remove {@link Trace}s on an element 
   * @param namespace
   */
  static public void removeTraces(Namespace namespace) {
    
    if ( null != namespace ) {
      
      for (Trace trace : namespace.getOwnedTraces()) {
        trace.setSourceElement(null);
        trace.setTargetElement(null);
      }
      
      namespace.getOwnedTraces().clear();

    }
   
    return;
  }
  
}
