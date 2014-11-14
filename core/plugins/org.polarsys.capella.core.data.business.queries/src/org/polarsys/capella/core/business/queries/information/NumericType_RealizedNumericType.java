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
package org.polarsys.capella.core.business.queries.information;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;

/**
 * This is the query for the Numeric type realized information
 */
public class NumericType_RealizedNumericType extends DataTypeElement_RealizedInformation {
  
  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return DatatypePackage.Literals.NUMERIC_TYPE;
  }
}
