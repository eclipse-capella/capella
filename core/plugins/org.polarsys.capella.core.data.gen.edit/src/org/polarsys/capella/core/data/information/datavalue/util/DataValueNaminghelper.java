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
package org.polarsys.capella.core.data.information.datavalue.util;

import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;

public class DataValueNaminghelper {

  public static String getReferencePrefix(DataValue reference_p) {
    EReference feature = reference_p.eContainmentFeature();
    if (feature != null) {
      if (feature.equals(DatatypePackage.eINSTANCE.getNumericType_OwnedMinValue()) ||
          feature.equals(DatatypePackage.eINSTANCE.getEnumeration_OwnedMinValue()) ||
          feature.equals(InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMinValue()))
      {
        return Messages.getString("ReferenceNaminghelper.MinValue") + " "; //$NON-NLS-1$ //$NON-NLS-2$
      }
      else if (feature.equals(DatatypePackage.eINSTANCE.getNumericType_OwnedMaxValue()) ||
               feature.equals(DatatypePackage.eINSTANCE.getEnumeration_OwnedMaxValue()) ||
               feature.equals(InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMaxValue()))
      {
        return Messages.getString("ReferenceNaminghelper.MaxValue") + " "; //$NON-NLS-1$ //$NON-NLS-2$
      }
      else if (feature.equals(DatatypePackage.eINSTANCE.getNumericType_OwnedDefaultValue()) ||
               feature.equals(DatatypePackage.eINSTANCE.getEnumeration_OwnedDefaultValue()) ||
               feature.equals(DatatypePackage.eINSTANCE.getStringType_OwnedDefaultValue()) ||
               feature.equals(DatatypePackage.eINSTANCE.getBooleanType_OwnedDefaultValue()) ||
               feature.equals(InformationPackage.eINSTANCE.getMultiplicityElement_OwnedDefaultValue()))
      {
        return Messages.getString("ReferenceNaminghelper.DefaultValue") + " "; //$NON-NLS-1$ //$NON-NLS-2$
      }
      else if (feature.equals(DatatypePackage.eINSTANCE.getNumericType_OwnedNullValue()) ||
               feature.equals(DatatypePackage.eINSTANCE.getEnumeration_OwnedNullValue()) ||
               feature.equals(DatatypePackage.eINSTANCE.getStringType_OwnedNullValue()) ||
               feature.equals(InformationPackage.eINSTANCE.getMultiplicityElement_OwnedNullValue()))
      {
        return Messages.getString("ReferenceNaminghelper.NullValue") + " "; //$NON-NLS-1$ //$NON-NLS-2$
      }
      else if (feature.equals(DatatypePackage.eINSTANCE.getStringType_OwnedMinLength()) ||
               feature.equals(InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMinLength()))
      {
        return Messages.getString("ReferenceNaminghelper.MinLength") + " "; //$NON-NLS-1$ //$NON-NLS-2$
      }
      else if (feature.equals(DatatypePackage.eINSTANCE.getStringType_OwnedMaxLength()) ||
               feature.equals(InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMaxLength()))
      {
        return Messages.getString("ReferenceNaminghelper.MaxLength") + " "; //$NON-NLS-1$ //$NON-NLS-2$
      }
      else if (feature.equals(InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMinCard()))
      {
        return Messages.getString("ReferenceNaminghelper.MinCard") + " "; //$NON-NLS-1$ //$NON-NLS-2$
      }
      else if (feature.equals(InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMaxCard()))
      {
        return Messages.getString("ReferenceNaminghelper.MaxCard") + " "; //$NON-NLS-1$ //$NON-NLS-2$
      }
      else if (feature.equals(InformationPackage.eINSTANCE.getCollectionValue_OwnedDefaultElement()))
      {
        return Messages.getString("ReferenceNaminghelper.DefaultElement") + " "; //$NON-NLS-1$ //$NON-NLS-2$
      }
      else if (feature.equals(DatavaluePackage.eINSTANCE.getEnumerationLiteral_DomainValue()))
      {
        return Messages.getString("ReferenceNaminghelper.Value") + " "; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    return ""; //$NON-NLS-1$
  }

  public static String getSuffix(NumericValue value_p) {
    Unit unit = value_p.getUnit();
    if (null == unit) {
      AbstractType type = value_p.getAbstractType();
      if (type instanceof PhysicalQuantity) {
        unit = ((PhysicalQuantity) type).getUnit();
      }
    }

    if (null != unit) {
      String name = unit.getName();
      if (null == name || "".equals(name)) { //$NON-NLS-1$
        name = "[Unit]"; //$NON-NLS-1$
      }
      return "(" + name + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    return ""; //$NON-NLS-1$
  }
}
