/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.scenarios.core.exceptions;

import org.polarsys.capella.core.refinement.scenarios.core.plugs.IResolver;

/**
 */
public class ResolverException extends RefinementException {
	final static long serialVersionUID = 0;

  /**
   * @param string
   * @param plug
   */
  public ResolverException(String string, IResolver plug) {
    super(string, plug);
  }
}
