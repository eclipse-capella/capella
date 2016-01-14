/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
   * @param string
   * @param plug
   */
  public RefinementException(String string, IRefinementPlug plug) {
    super(string);

    _plug = plug;
  }

  /**
   * @param string
   * @param plug
   * @param throwable
   */
  public RefinementException(String string, IRefinementPlug plug, Throwable throwable) {
    super(string, throwable);

    _plug = plug;
  }

  /**
   * @return the processor
   */
  public IRefinementPlug getPlug() {
    return _plug;
  }
}
