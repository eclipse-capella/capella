/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return DataValue Reference --> Referenced Value
 */
public class DataValueReferencingReferencedValue implements IQuery {

  /**
	 * 
	 */
  public DataValueReferencingReferencedValue() {
    // do nothing
  }

  /**
   * current.incomingPortRealisation.realizingport
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof DataValue) {
      DataValue dataValue = (DataValue) object;
      Collection<Setting> inverseReferencesOfEObject = CapellaElementExt.getInverseReferencesOfEObject(dataValue);
      for (Setting setting : inverseReferencesOfEObject) {
        EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
        if (eStructuralFeature.equals(DatavaluePackage.Literals.NUMERIC_REFERENCE__REFERENCED_VALUE)
            || eStructuralFeature.equals(DatavaluePackage.Literals.STRING_REFERENCE__REFERENCED_VALUE)
            || eStructuralFeature.equals(DatavaluePackage.Literals.ENUMERATION_REFERENCE__REFERENCED_VALUE)
            || eStructuralFeature.equals(DatavaluePackage.Literals.BOOLEAN_REFERENCE__REFERENCED_VALUE)
            || eStructuralFeature.equals(DatavaluePackage.Literals.COMPLEX_VALUE_REFERENCE__REFERENCED_VALUE)
            || eStructuralFeature.equals(InformationPackage.Literals.COLLECTION_VALUE_REFERENCE__REFERENCED_VALUE)) {
          if (null != setting.getEObject()) {
            result.add(setting.getEObject());
          }
        }
      }
    }
    return result;
  }
}
