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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;

/**
 */
public class DataTypeExt {
  /**
   * @param context a Capella Element
   * @return all the dataTypes contained in the current and previous architectures
   */
  public static Collection<DataType> getAllDataTypes(final EObject context) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_DATA_TYPES, context);
  }

  /**
   * @see #getDataTypeDependencies(DataType)
   */
  public static Map<AbstractDependenciesPkg, Collection<EObject>> getDataTypeDependencies2(DataType dataType) {

    Map<AbstractDependenciesPkg, Collection<EObject>> result = new HashMap<AbstractDependenciesPkg, Collection<EObject>>();

    // superDataTypes
    for (Generalization aGeneralization : dataType.getSuperGeneralizations()) {
      AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result, aGeneralization.getSuper());
    }
    return result;
  }

  /**
   * @param dataType
   * @return all dependent packages of the collection
   */
  public static Collection<AbstractDependenciesPkg> getDataTypeDependencies(DataType dataType) {
    return getDataTypeDependencies2(dataType).keySet();
  }

  /**
   * Return all data Values contained in DataType
   * @param currentDataType
   * @return list of dataValues
   */
  public static List<DataValue> getAllDataValuesFromDataType(DataType currentDataType) {
    // result
    List<DataValue> result = new ArrayList<DataValue>(1);
    // map of EObject and Values as List of EReference
    Map<EObject, List<EReference>> mapOfDataTypeWithFeatures = new HashMap<EObject, List<EReference>>();
    List<EReference> referenceElement = new ArrayList<EReference>(1);
    // add corresponding references
    referenceElement.add(DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES);
    if (currentDataType instanceof BooleanType) {
      referenceElement.add(DatatypePackage.Literals.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE);
      referenceElement.add(DatatypePackage.Literals.BOOLEAN_TYPE__OWNED_LITERALS);
    } else if (currentDataType instanceof Enumeration) {
      referenceElement.add(DatatypePackage.Literals.ENUMERATION__OWNED_LITERALS);
      referenceElement.add(DatatypePackage.Literals.ENUMERATION__OWNED_DEFAULT_VALUE);
      referenceElement.add(DatatypePackage.Literals.ENUMERATION__OWNED_NULL_VALUE);
      referenceElement.add(DatatypePackage.Literals.ENUMERATION__OWNED_MIN_VALUE);
      referenceElement.add(DatatypePackage.Literals.ENUMERATION__OWNED_MAX_VALUE);
    } else if ((currentDataType instanceof NumericType) || (currentDataType instanceof PhysicalQuantity)) {
      referenceElement.add(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_DEFAULT_VALUE);
      referenceElement.add(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_NULL_VALUE);
      referenceElement.add(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MIN_VALUE);
      referenceElement.add(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MAX_VALUE);
    } else if (currentDataType instanceof StringType) {
      referenceElement.add(DatatypePackage.Literals.STRING_TYPE__OWNED_DEFAULT_VALUE);
      referenceElement.add(DatatypePackage.Literals.STRING_TYPE__OWNED_NULL_VALUE);
      referenceElement.add(DatatypePackage.Literals.STRING_TYPE__OWNED_MIN_LENGTH);
      referenceElement.add(DatatypePackage.Literals.STRING_TYPE__OWNED_MAX_LENGTH);
    }
    mapOfDataTypeWithFeatures.put(currentDataType, referenceElement);
    // retrieve all dataValues
    result.addAll(DataValueExt.getDataValuesFromMapOfEObjectAndEReferences(mapOfDataTypeWithFeatures));

    return result;
  }

}
