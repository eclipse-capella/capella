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
package org.polarsys.capella.core.business.queries.oa;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.business.queries.fa.Connection_ConvoyedInformation;
import org.polarsys.capella.core.data.oa.OaPackage;

/**
 * This query return the ExchageItem from current and all the above levels
 * 
 */
public class CommunicationMean_ConvoyedInformation extends Connection_ConvoyedInformation {

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getEClass()
   */
  @Override
  public EClass getEClass() {
    return OaPackage.Literals.COMMUNICATION_MEAN;
  }
}
