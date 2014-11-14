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
package org.polarsys.capella.core.projection.lc2pc.breakdownstrategy;

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.impl.Transfo;


/**
 */
public class LogicalComponentTransfo extends Transfo {

  private static final String ORG_POLARSYS_CAPELLA_CORE_PROJECTION_LC2PC_BREAKDOWNSTRATEGY = "org.polarsys.capella.core.projection.lc2pc.breakdownstrategy"; //$NON-NLS-1$

private static final long serialVersionUID = 0L;


  /**
   * @throws ClassNotFoundException 
   */
  public LogicalComponentTransfo(ITransfoRuleBase transfoRuleBase_p) throws ClassNotFoundException {
	  super (ORG_POLARSYS_CAPELLA_CORE_PROJECTION_LC2PC_BREAKDOWNSTRATEGY);
    setSpecificLinkKindFromMap(FaPackage.Literals.FUNCTION_INPUT_PORT, FaPackage.Literals.FUNCTION_INPUT_PORT, InformationPackage.Literals.PORT_REALIZATION);
    setSpecificLinkKindFromMap(FaPackage.Literals.FUNCTION_OUTPUT_PORT, FaPackage.Literals.FUNCTION_OUTPUT_PORT, InformationPackage.Literals.PORT_REALIZATION);

  }
}
