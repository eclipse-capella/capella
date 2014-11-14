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
package org.polarsys.capella.core.refinement.scenarios.core.exceptions;

import org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug;

/**
 */
public abstract class RefinementException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  private IRefinementPlug _plug = null;

  /**
   * @param string_p
   * @param plug_p
   */
  public RefinementException(String string_p, IRefinementPlug plug_p) {
    super(string_p);

    _plug = plug_p;
  }

  /**
   * @return the processor
   */
  public IRefinementPlug getPlug() {
    return _plug;
  }
}
