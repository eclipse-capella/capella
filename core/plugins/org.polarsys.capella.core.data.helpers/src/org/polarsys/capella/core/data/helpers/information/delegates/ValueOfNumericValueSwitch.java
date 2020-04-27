/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.information.delegates;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.util.DatavalueSwitch;

/**
 * TODO: this inner class is in org.polarsys.capella.core.sirius.analysis.InformationServices<br>
 * It should be only in this helper and its purpose is to be used by the <code>getvalue</code> method
 */
public class ValueOfNumericValueSwitch extends DatavalueSwitch<Object> {

  /**
   * Default constructor.
   * @param source <code>true</code> means that we test the source of the transition. Otherwise it means that we test the target of the transition.
   */
  public ValueOfNumericValueSwitch() {
    //
  }

  /**
   * {@inheritDoc}
   * @see org.polarsys.capella.core.data.information.datavalue.util.DatavalueSwitch#caseLiteralNumericValue(org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue)
   */
  @Override
  public Object caseLiteralNumericValue(LiteralNumericValue object) {
    return object.getValue();
  }

  /**
   * {@inheritDoc}
   * @see org.polarsys.capella.core.data.information.datavalue.util.DatavalueSwitch#caseNumericReference(org.polarsys.capella.core.data.information.datavalue.NumericReference)
   */
  @Override
  public Object caseNumericReference(final NumericReference object) {
    return

    new ValueOfNumericValueSwitch().doSwitch(object.getReferencedValue());
  }

  /**
   * {@inheritDoc}
   * @see org.polarsys.capella.core.data.information.datavalue.util.DatavalueSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public Object defaultCase(final EObject object) {
    return ""; //$NON-NLS-1$
  }
}
