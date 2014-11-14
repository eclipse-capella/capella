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
   * @param namespace_p
   */
  static public void removeTraces(Namespace namespace_p) {
    
    if ( null != namespace_p ) {
      
      for (Trace trace : namespace_p.getOwnedTraces()) {
        trace.setSourceElement(null);
        trace.setTargetElement(null);
      }
      
      namespace_p.getOwnedTraces().clear();

    }
   
    return;
  }
  
}
