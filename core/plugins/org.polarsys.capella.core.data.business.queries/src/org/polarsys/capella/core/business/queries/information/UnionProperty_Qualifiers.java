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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionKind;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

/**
 * This is the query for Union Properties qualifiers
 */
public class UnionProperty_Qualifiers extends CapellaElement_CurrentAndHigherLevelsQuery {
  /**
   * Returns discriminant of the parent <code>Union</code> of the given <code>UnionProperty</code>
   * @param currentProperty_p the given <code>UnionProperty</code>
   * @return the discriminant of the parent union if there is any, null otherwise
   */
  protected static UnionProperty getParentUnionDiscriminant(UnionProperty currentProperty_p) {
    EObject container = currentProperty_p.eContainer();
    if (container instanceof Union) {
      Union union = (Union) container;
      return union.getDiscriminant();
    }
    return null;
  }

  /**
   * This method allows to get all qualifiers of all union properties contained by the union parent of the given <code>UnionProperty</code>.
   * @param currentProperty_p the given union property
   * @return the list of all qualifiers
   */
  protected static List<DataValue> getAllParentUnionUnionPropertiesQualifiers(UnionProperty currentProperty_p) {
    List<DataValue> returnValue = new ArrayList<DataValue>();
    EObject container = currentProperty_p.eContainer();
    if (container instanceof Union) {
      Union union = (Union) container;
      EList<Property> properties = union.getContainedProperties();
      for (Property property : properties) {
        if (property instanceof UnionProperty) {
          UnionProperty uProp = (UnionProperty) property;
          returnValue.addAll(uProp.getQualifier());
        }
      }
    }
    return returnValue;
  }

  /**
   * Allows to know if the given <code>UnionProperty</code>'s parent union is a variant or not
   * @param currentProperty_p the union property
   * @return <code>true</code> if it is, <code>false</code> otherwise
   */
  protected static boolean isParentUnionVariant(UnionProperty currentProperty_p) {
    EObject container = currentProperty_p.eContainer();
    if (container instanceof Union) {
      Union union = (Union) container;
      return union.getKind() == UnionKind.VARIANT ? true : false;
    }
    return false;
  }

  /**
   * Returns discriminant type of the parent <code>Union</code> of the given <code>UnionProperty</code>
   * @param currentProperty_p the given <code>UnionProperty</code>
   * @return the discriminant type of the parent union if there is any, null otherwise
   */
  protected AbstractType getParentUnionDiscriminantType(UnionProperty currentProperty_p) {
    UnionProperty discriminant = getParentUnionDiscriminant(currentProperty_p);
    if (null != discriminant) {
      return discriminant.getAbstractType();
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof UnionProperty) {
      UnionProperty ele = (UnionProperty) element_p;
      EList<DataValue> qualifiers = ele.getQualifier();
      for (DataValue qualifier : qualifiers) {
        currentElements.add(qualifier);
      }
    }
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return InformationPackage.Literals.UNION_PROPERTY;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InformationPackage.Literals.UNION_PROPERTY__QUALIFIER);
  }

  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    if (!(capellaElement_p instanceof UnionProperty)) {
      return Collections.emptyList();
    }
    UnionProperty unionProperty = (UnionProperty) capellaElement_p;
    AbstractType discriminantType = getParentUnionDiscriminantType(unionProperty);
    if (discriminantType instanceof BooleanType) {
      return filterAvailableData(getApplicableQualifiersForDiscriminantType(dataPkg_p, (BooleanType) discriminantType), unionProperty);
    } else if (discriminantType instanceof Enumeration) {
      return filterAvailableData(getApplicableQualifiersForDiscriminantType(dataPkg_p, (Enumeration) discriminantType), unionProperty);
    } else {
      // the discriminant is NOT a boolean type and NOT an enumeration
      // Gets the corresponding data values
      if (discriminantType instanceof DataType) {
        List<CapellaElement> dataValuesCorrespondingToDataType =
            CapellaElementsHelperForBusinessQueries.getValuesTypedBy(dataPkg_p, (DataType) discriminantType, false, true, null);
        // And the corresponding properties
        dataValuesCorrespondingToDataType.addAll(CapellaElementsHelperForBusinessQueries.getPropertiesTypedBy(dataPkg_p, (DataType) discriminantType,
            false));
        return filterAvailableData(dataValuesCorrespondingToDataType, unionProperty);
      }
      return Collections.emptyList();
    }
  }

  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getUnlevelizedData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getUnlevelizedData(CapellaElement capellaElement_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    if (!(capellaElement_p instanceof UnionProperty)) {
      return returnValue;
    }
    UnionProperty unionProperty = (UnionProperty) capellaElement_p;
    AbstractType discriminantType = getParentUnionDiscriminantType(unionProperty);
    if (discriminantType instanceof BooleanType) {
      return filterAvailableData(getApplicableQualifiersForDiscriminantType((BooleanType) discriminantType), unionProperty);
    } else if (discriminantType instanceof Enumeration) {
      return filterAvailableData(getApplicableQualifiersForDiscriminantType((Enumeration) discriminantType), unionProperty);
    } else {
      // the discriminant is NOT a boolean type and NOT an enumeration
      // nothing to be done here
    }
    return returnValue;
  }

  /**
   * Allows to know if the given data value shall be skipped for the available values for the given <code>UnionProperty</code> qualifiers.
   * @param value_p the data value
   * @param unionProperty_p the union property
   * @return <code>true</code> if it shall be skipped, <code>false</code> otherwise
   */
  protected boolean shallBeSkipped(DataValue value_p, UnionProperty unionProperty_p) {
    if (isParentUnionVariant(unionProperty_p)) {
      // When the parent union is a variant, no element is skipped
      return false;
    }
    List<DataValue> qualifiers = getAllParentUnionUnionPropertiesQualifiers(unionProperty_p);
    for (DataValue value : qualifiers) {
      if (value == value_p) {
        return true;
      }
    }
    return false;
  }

  /**
   * Filters the given list in order to skip the elements which shall be skipped for an affectation to the given <code>UnionProperty</code> qualifier.
   * @param list_p the list
   * @param unionProperty_p the union property
   * @return the filtered list
   */
  public List<CapellaElement> filterAvailableData(List<CapellaElement> list_p, UnionProperty unionProperty_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    for (CapellaElement capellaElement : list_p) {
      if (
      // The current element is a data value
      capellaElement instanceof DataValue
      // AND
          &&
          // This element do not have to be skipped
          !shallBeSkipped((DataValue) capellaElement, unionProperty_p)
      //
      ) {
        returnValue.add(capellaElement);
      }
    }
    return returnValue;
  }

  /**
   * Gets the applicable qualifiers for the given discriminant type
   * @param dataPkg_p the data package where the search is done
   * @param booleanType_p the discriminant type
   * @return the applicable qualifiers
   */
  protected List<CapellaElement> getApplicableQualifiersForDiscriminantType(DataPkg dataPkg_p, BooleanType booleanType_p) {
    // Boolean references and Expressions EClass's
    List<EClass> booleanReferencesAndExpression = new ArrayList<EClass>();
    booleanReferencesAndExpression.add(DatavaluePackage.Literals.BOOLEAN_REFERENCE);
    booleanReferencesAndExpression.add(DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE);
    return CapellaElementsHelperForBusinessQueries.getValuesTypedBy(dataPkg_p, booleanType_p, false, true, booleanReferencesAndExpression, null);
  }

  /**
   * Gets the applicable qualifiers for the given discriminant type
   * @param dataPkg_p the data package where the search is done
   * @param enumeration_p the discriminant type
   * @return the applicable qualifiers
   */
  protected List<CapellaElement> getApplicableQualifiersForDiscriminantType(DataPkg dataPkg_p, Enumeration enumeration_p) {
    List<CapellaElement> returnValue =
        CapellaElementsHelperForBusinessQueries.getValuesTypedBy(dataPkg_p, enumeration_p, false, true,
            DatavaluePackage.Literals.ENUMERATION_REFERENCE, null);
    returnValue.addAll(CapellaElementsHelperForBusinessQueries.getValuesTypedBy(dataPkg_p, enumeration_p, false, true,
        DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE, null));
    return returnValue;
  }

  /**
   * Gets the applicable qualifiers for the given discriminant type
   * @param blockArchitecture_p the layer where the search is done
   * @param booleanType_p the discriminant type
   * @return the applicable qualifiers
   */
  protected List<CapellaElement> getApplicableQualifiersForDiscriminantType(BooleanType booleanType_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    List<GeneralizableElement> rootSuperClassifiers = GeneralizableElementExt.getRootSupertypes(booleanType_p);
    for (CapellaElement capellaElement : rootSuperClassifiers) {
      if (capellaElement instanceof BooleanType) {
        // normally, should always be!
        BooleanType rootBooleanType = (BooleanType) capellaElement;
        returnValue.addAll(rootBooleanType.getOwnedLiterals());
      }
    }
    return returnValue;
  }

  /**
   * Gets the applicable qualifiers for the given discriminant type
   * @param blockArchitecture_p the layer where the search is done
   * @param enumeration_p the discriminant type
   * @return the applicable qualifiers
   */
  protected List<CapellaElement> getApplicableQualifiersForDiscriminantType(Enumeration enumeration_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    List<GeneralizableElement> rootSuperClassifiers = GeneralizableElementExt.getRootSupertypes(enumeration_p);
    for (CapellaElement capellaElement : rootSuperClassifiers) {
      if (capellaElement instanceof Enumeration) {
        // normally, should always be!
        Enumeration rootEnumerationType = (Enumeration) capellaElement;
        returnValue.addAll(rootEnumerationType.getOwnedLiterals());
      }
    }
    return returnValue;
  }
}
