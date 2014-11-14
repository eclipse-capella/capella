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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

/**
 *
 */
public class LiteralBooleanValue_AbstractType extends AbstractValue_Type {

  /**
   * @see org.polarsys.capella.core.business.queries.information.AbstractValue_Type#getAvailableEClassForType()
   */
  @Override
  protected List<EClass> getAvailableEClassForType() {
    return Collections.singletonList(DatatypePackage.Literals.BOOLEAN_TYPE);
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return DatavaluePackage.Literals.LITERAL_BOOLEAN_VALUE;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.information.AbstractValue_Type#getEStructuralFeatures()
   */
  @Override
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
  }
  
}
