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
package org.polarsys.capella.core.transfo.logicalfunction.rules;

import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.projection.common.rules.fa.Rule_Function;

/**
 */
public class Rule_LogicalFunction extends Rule_Function {

  public Rule_LogicalFunction() {
    super(LaPackage.Literals.LOGICAL_FUNCTION, PaPackage.Literals.PHYSICAL_FUNCTION);
  }

}
