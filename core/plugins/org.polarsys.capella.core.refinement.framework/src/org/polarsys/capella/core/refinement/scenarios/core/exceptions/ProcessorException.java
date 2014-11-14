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

import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;

/**
 */
public class ProcessorException extends RefinementException {
  final static long serialVersionUID = 0;

  /**
   * @param string_p
   * @param plug_p
   */
  public ProcessorException(String string_p, IProcessor plug_p) {
    super(string_p, plug_p);
  }
}
