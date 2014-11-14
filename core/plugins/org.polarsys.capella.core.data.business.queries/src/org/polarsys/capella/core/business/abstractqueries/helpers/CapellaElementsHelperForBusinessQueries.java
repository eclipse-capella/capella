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
package org.polarsys.capella.core.business.abstractqueries.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.helpers.information.delegates.DataValueHelper;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.CollectionValue;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.NumericTypeKind;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.ComplexValue;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.DataValueExt;
import org.polarsys.capella.core.model.utils.EClassExt;

/**
 * This class is a helper whose purpose is to give some utility methods for the business queries involving capella elements.
 */
public class CapellaElementsHelperForBusinessQueries {
  /**
   * Filters the given capella elements list to return only the discrete datatypes
   * @param elements_p the list
   * @return the filtered list
   */
  public static List<CapellaElement> getOnlyDiscreteDatatypes(List<CapellaElement> elements_p) {
    List<CapellaElement> onlyDiscreteDatatypes = new ArrayList<CapellaElement>();
    for (CapellaElement element : elements_p) {
      if ((element instanceof DataType) && ((DataType) element).isDiscrete()) {
        onlyDiscreteDatatypes.add(element);
      }
    }
    return onlyDiscreteDatatypes;
  }

  /**
   * Filters the given list to avoid elements which cannot be instantiated as an instance of the given eclass
   * @param list_p the list
   * @param eclass_p the eclass
   * @return the filtered list
   */
  public static List<CapellaElement> filterWithAvailableEClass(List<CapellaElement> list_p, EClass eclass_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    for (CapellaElement element : list_p) {
      if (CapellaElementsHelperForBusinessQueries.canBeInstanciatedAs(element, eclass_p)) {
        returnValue.add(element);
      }
    }
    return returnValue;
  }

  /**
   * Returns from the given layer:<br>
   * <ul>
   * <li>The data values whose type is the same as the given capella element.</li>
   * <li>The data values with no type if the argument <code>acceptNoType_p</code> is set to <code>true</code>.</li>
   * <li>The data values whose type is one of the parent type of the given capella element if the argument <code>acceptSuperClassifiers_p</code> is set to
   * <code>true</code>.</li>
   * </ul>
   * @param dataPkg_p the data package
   * @param capellaElement_p the given capella element
   * @param acceptNoType_p <code>true</code> if you want to put in the list the elements with no type
   * @param acceptSubClassifiers_p <code>true</code> if you want to put in the list the elements whose type is a sub classifier of the given capella element
   * @param restrictToInstancesOf if you want to restrict the result to instances of a class (or of its subclasses), pass it here, or null otherwise
   * @param toSkip_p the element you have to skip, typically, the element from where the query has been launched. May be <code>null</code> if you don't want to
   *          skip anything
   * @return the list of the applicable data values
   */
  public static List<CapellaElement> getValuesTypedBy(DataPkg dataPkg_p, GeneralizableElement capellaElement_p, boolean acceptNoType_p,
      boolean acceptSubClassifiers_p, EClass restrictToInstancesOf_p, CapellaElement toSkip_p) {
    List<EClass> restricToInstancesOf = null;
    if (null != restrictToInstancesOf_p) {
      restricToInstancesOf = new ArrayList<EClass>();
      restricToInstancesOf.add(restrictToInstancesOf_p);
    }
    return getValuesTypedBy(dataPkg_p, capellaElement_p, acceptNoType_p, acceptSubClassifiers_p, restricToInstancesOf, toSkip_p);
  }

  /**
   * Returns from the given layer:<br>
   * <ul>
   * <li>The data values whose type is the same as the given capella element.</li>
   * <li>The data values with no type if the argument <code>acceptNoType_p</code> is set to <code>true</code>.</li>
   * <li>The data values whose type is one of the parent type of the given capella element if the argument <code>acceptSuperClassifiers_p</code> is set to
   * <code>true</code>.</li>
   * </ul>
   * @param dataPkg_p the data package
   * @param type_p the given capella element
   * @param acceptNoType_p <code>true</code> if you want to put in the list the elements with no type
   * @param acceptSubClassifiers_p <code>true</code> if you want to put in the list the elements whose type is a sub classifier of the given capella element
   * @param toSkip_p the element you have to skip, typically, the element from where the query has been launched. May be <code>null</code> if you don't want to
   *          skip anything
   * @return the list of the applicable data values
   */
  public static List<CapellaElement> getValuesTypedBy(DataPkg dataPkg_p, DataType type_p, boolean acceptNoType_p, boolean acceptSubClassifiers_p,
      CapellaElement toSkip_p) {
    List<EClass> restricToInstancesOf = new ArrayList<EClass>();
    return getValuesTypedBy(dataPkg_p, type_p, acceptNoType_p, acceptSubClassifiers_p, restricToInstancesOf, toSkip_p);
  }

  /**
   * Returns from the given layer:<br>
   * <ul>
   * <li>The data values whose type is the same as the given capella element.</li>
   * <li>The data values with no type if the argument <code>acceptNoType_p</code> is set to <code>true</code>.</li>
   * <li>The data values whose type is one of the parent type of the given capella element if the argument <code>acceptSuperClassifiers_p</code> is set to
   * <code>true</code>.</li>
   * </ul>
   * @param dataPkg_p the data package
   * @param type_p the given capella element
   * @param acceptNoType_p <code>true</code> if you want to put in the list the elements with no type
   * @param acceptSubClassifiers_p <code>true</code> if you want to put in the list the elements whose type is a super classifier of the given capella element
   * @param restrictToInstancesOf_p if you want to restrict the result to instances of some classes (or of their subclasses), pass them here, or null otherwise
   * @param toSkip_p the element you have to skip, typically, the element from where the query has been launched. May be <code>null</code> if you don't want to
   *          skip anything
   * @return the list of the applicable data values
   */
  public static List<CapellaElement> getValuesTypedBy(DataPkg dataPkg_p, GeneralizableElement type_p, boolean acceptNoType_p, boolean acceptSubClassifiers_p,
      List<EClass> restrictToInstancesOf_p, CapellaElement toSkip_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (null != dataPkg_p) {
      // Gets a list of the superClassifiers of the data type
      List<GeneralizableElement> superClassifiers = GeneralizableElementExt.getAllSubGeneralizableElements(type_p);
      for (Object object : EObjectExt.getAll(dataPkg_p, DatavaluePackage.Literals.DATA_VALUE)) {
        if (object instanceof DataValue) {
          DataValue dataValue = (DataValue) object;
          if (((null != toSkip_p) && (dataValue != toSkip_p)) || (null == toSkip_p)) {
            //
            // Tests every data value in the data package
            AbstractType dataValueType = dataValue.getAbstractType();
            if ((acceptNoType_p && (null == dataValueType)) || ((null != dataValueType) && (dataValueType == type_p))) {
              // If the element is not typed (if acceptNoType_p is true) or if it has the given data type
              if (
              // if there is no restriction to some classes
              ((null == restrictToInstancesOf_p) || (0 == restrictToInstancesOf_p.size()))
              // OR
                  ||
                  // The list is restricted to the given types
                  canBeInstanciatedAs(dataValue, restrictToInstancesOf_p)) {
                availableElements.add(dataValue);
              }
            } else if (acceptSubClassifiers_p) {
              // of not, tests with every one of the numeric type super classifiers if acceptSuperClassifiers_p is true
              for (CapellaElement superClassifier : superClassifiers) {
                if (dataValueType == superClassifier) {
                  if (
                  // if there is no restriction to some classes
                  ((null == restrictToInstancesOf_p) || (0 == restrictToInstancesOf_p.size()))
                  // OR
                      ||
                      // The list is restricted to the given types
                      canBeInstanciatedAs(dataValue, restrictToInstancesOf_p)) {
                    availableElements.add(dataValue);
                    break;
                  }
                }
              }
            }
          }
        }
      }
    }
    return availableElements;
  }

  /**
   * Returns the Capella Elements instances of the given <code>EClass</code> in the given <code>DataPkg</code>, but avoiding the given <code>CapellaElement</code>
   * if it is given.
   * @param dataPkg_p the data package
   * @param eclass_p the EClass
   * @param capellaElement_p the Capella element
   * @return a list containing instances of <code>CapellaElement</code>
   */
  public static List<CapellaElement> getCapellaElementsInstancesOf(DataPkg dataPkg_p, EClass eclass_p, CapellaElement capellaElement_p) {
    return getCapellaElementsInstancesOf(dataPkg_p, (eclass_p != null) ? Collections.singletonList(eclass_p) : null, capellaElement_p);
  }

  /**
   * Returns the Capella Elements instances of the given <code>EClass</code> in the given <code>DataPkg</code>, but avoiding the given <code>CapellaElement</code>
   * if it is given.
   * @param dataPkg_p the data package
   * @param eclass_p the list of EClasses
   * @param capellaElement_p the Capella element
   * @return a list containing instances of <code>CapellaElement</code>
   */
  public static List<CapellaElement> getCapellaElementsInstancesOf(DataPkg dataPkg_p, List<EClass> eclasses_p, CapellaElement capellaElement_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    // If the data package is not null
    if (null != dataPkg_p) {
      // Gets a list of the Capella Elements in the data package
      Set<EObject> allElements = new HashSet<EObject>();
      if (null != eclasses_p) {
        for (EClass cls : eclasses_p) {
          allElements.addAll(EObjectExt.getAll(dataPkg_p, cls));
        }
      } else {
        // If no <code><EClass/code> has been given in parameter, then retrieves all Capella Elements
        allElements = EObjectExt.getAll(dataPkg_p, CapellacorePackage.Literals.CAPELLA_ELEMENT);
      }
      for (EObject object : allElements) {
        if (object instanceof CapellaElement) {
          CapellaElement capellaElement = (CapellaElement) object;
          if ((
          // There is an element to avoid and it is not the current one OR there is no element to avoid
              ((null != capellaElement) && (capellaElement != capellaElement_p)) || (null == capellaElement_p))
              // AND
              &&
              // The current element is an instance of the wanted <code>EClass</code>
              canBeInstanciatedAs(capellaElement, eclasses_p)) {
            returnValue.add(capellaElement);
          }
        }
      }
    }
    return returnValue;
  }

  /**
   * Gets the data values instances of one of the given <code>EClass</code> in the given <code>DataPkg</code>
   * @param dataPkg_p the data package
   * @param eClass_p the <code>EClass</code>
   * @param nonTyped_p <code>true</code> if you want to accept the data values with no type, <code>false</code> otherwise
   * @param typed_p <code>true</code> if you want to accept the data values with a type, <code>false</code> otherwise
   * @return
   */
  public static List<CapellaElement> getDataValuesInstancesOf(DataPkg dataPkg_p, EClass eClass_p, boolean nonTyped_p, boolean typed_p) {
    List<EClass> eClasses = new ArrayList<EClass>();
    eClasses.add(eClass_p);
    return getDataValuesInstancesOf(dataPkg_p, eClasses, nonTyped_p, typed_p);
  }

  /**
   * Gets the data values instances of one of the given <code>EClass</code>'s in the given <code>DataPkg</code>
   * @param dataPkg_p the data package
   * @param eClasses_p the <code>EClass</code>'s
   * @param nonTyped_p <code>true</code> if you want to accept the data values with no type, <code>false</code> otherwise
   * @param typed_p <code>true</code> if you want to accept the data values with a type, <code>false</code> otherwise
   * @return
   */
  public static List<CapellaElement> getDataValuesInstancesOf(DataPkg dataPkg_p, List<EClass> eClasses_p, boolean nonTyped_p, boolean typed_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (null != dataPkg_p) {
      for (EObject object : EObjectExt.getAll(dataPkg_p, DatavaluePackage.Literals.DATA_VALUE)) {
        if (object instanceof DataValue) {
          DataValue dataValue = (DataValue) object;

          // Tests every data value in the data package
          for (EClass eClass : eClasses_p) {
            // Check for each one of the searched EClasses
            if (
            // dataValue is not null
            (null != dataValue
            // AND
                )
                &&
                // Its is an instance of one of the searched type
                canBeInstanciatedAs(dataValue, eClass)
                // AND
                && (
                // you want the untyped ones and the data value is untyped
                (nonTyped_p && (null == dataValue.getAbstractType()))
                // OR
                ||
                // you want the typed ones and the data value is typed
                (typed_p && (null != dataValue.getAbstractType())))) {
              availableElements.add(dataValue);
            }
          }
        }
      }
    }
    return availableElements;
  }

  /**
   * Returns all the data values of the given layer
   * @param dataPkg_p the data package
   * @return the list of the data values
   */
  public static List<CapellaElement> getDataValues(DataPkg dataPkg_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (null != dataPkg_p) {
      for (Object object : EObjectExt.getAll(dataPkg_p, DatavaluePackage.Literals.DATA_VALUE)) {
        if (object instanceof DataValue) {
          availableElements.add((DataValue) object);
        }
      }
    }
    return availableElements;
  }

  /**
   * Returns the data values typed with root super types of the given capella element in the given layer
   * @param blockArchitecture_p the given layer
   * @param capellaElement_p the given capella element
   * @param limitToInstancesOf_p pass an <code>EClass</code> here if you want to limit the results to instances of this <code>EClass</code>, pass
   *          <code>null</code> otherwise
   * @return the list of the applicable data values
   */
  public static List<CapellaElement> getDataValuesTypedWithRootSuperTypesOf(BlockArchitecture blockArchitecture_p, CapellaElement capellaElement_p,
      EClass limitToInstancesOf_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (capellaElement_p instanceof DataType) {
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(blockArchitecture_p);
      // If the data package is not null
      if (null != dataPkg) {
        // Gets a list of the superClassifiers of the data type
        List<GeneralizableElement> rootSuperClassifiers = GeneralizableElementExt.getRootSupertypes((DataType) capellaElement_p);
        for (EObject object : EObjectExt.getAll(dataPkg, DatavaluePackage.Literals.DATA_VALUE)) {
          if (object instanceof DataValue) {
            DataValue dataValue = (DataValue) object;
            // Tests every data value in the data package
            AbstractType dataValueType = dataValue.getAbstractType();
            // tests with every one of the root super classifiers
            for (CapellaElement superClassifier : rootSuperClassifiers) {
              // if the type is the current super classifier
              if (dataValueType == superClassifier) {
                if (
                //
                (
                // the results are limited to a given EClass
                    (null != limitToInstancesOf_p
                    // AND
                    ) &&
                    // the current data value is an instance of this EClass
                    canBeInstanciatedAs(dataValue, limitToInstancesOf_p))
                    // OR
                    || (// the results are not limited to any given EClass
                    null == limitToInstancesOf_p)) {
                  availableElements.add(dataValue);
                }
                break;
              }
            }
          }
        }
      }
    }
    return availableElements;
  }

  /**
   * Returns in the given layer:<br>
   * <ul>
   * <li>The properties with no type if <code>acceptNoType_p</code> is <code>true</code>.</li>
   * <li>The properties whose type is the same as the given capella element.</li>
   * <li>The properties whose type is one of the parent type of the given capella element.</li>
   * </ul>
   * @param dataPkg_p the data package
   * @param dataType_p the given capella element. If <code>null</code>, every property is returned
   * @param acceptNoType_p <code>true</code> if you accept untyped elements, <code>false</code> otherwise
   * @return the list of the applicable data values
   */
  public static List<CapellaElement> getPropertiesTypedBy(DataPkg dataPkg_p, GeneralizableElement dataType_p, boolean acceptNoType_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (null != dataPkg_p) {
      List<GeneralizableElement> superClassifiers = GeneralizableElementExt.getAllSubGeneralizableElements(dataType_p);
      for (CapellaElement capellaElement : DataPkgExt.getAllClassifierFromDataPkg(dataPkg_p)) {
        if (capellaElement instanceof Classifier) {
          Classifier classifier = (Classifier) capellaElement;
          // Gets the features of the classifier in order to get its properties
          EList<Feature> ownedFeatures = classifier.getOwnedFeatures();
          for (Feature feature : ownedFeatures) {
            if (feature instanceof Property) {
              // Then tests the properties in oder to checks if it matches with the rules
              Property property = (Property) feature;
              AbstractType propertyType = property.getAbstractType();
              if ((dataType_p == null) || (acceptNoType_p && (null == propertyType)) || ((null != propertyType) && (propertyType == dataType_p))) {
                availableElements.add(property);
                continue;
              }
              // if not, tests with every one of the numeric type super classifiers
              for (CapellaElement superClassifier : superClassifiers) {
                if (propertyType == superClassifier) {
                  availableElements.add(property);
                  break;
                }
              }
            }
          }
        }
      }
    }
    return availableElements;
  }

  /**
   * Returns the available elements for cardinalities in the given level.<br>
   * The returned elements are:
   * <ul>
   * <li>All non typed NumericValues</li>
   * <li>All non typed Expressions</li>
   * <li>All NumericValues typed by a NumericType which Kind is INTEGER and min value is equal or greater than 0</li>
   * <li>All Expressions typed by a NumericType which Kind is INTEGER and min value is equal or greater than 0</li>
   * <li>All Properties typed by a NumericType which Kind is INTEGER and min value is equal or greater than 0</li>
   * </ul>
   * @param dataPkg_p the data package where the search is done
   * @return the list of the available elements
   */
  public static List<CapellaElement> getApplicableValuesForCardinalitiesInLevel(DataPkg dataPkg_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (null != dataPkg_p) {
      for (Object object : EObjectExt.getAll(dataPkg_p, DatavaluePackage.Literals.DATA_VALUE)) {
        if (object instanceof DataValue) {
          DataValue dataValue = (DataValue) object;
          if ((dataValue instanceof NumericValue) || (dataValue instanceof AbstractExpressionValue)) {
            // Tests every data value in the data package
            AbstractType dataValueType = dataValue.getAbstractType();
            if (
            // if there is no type
            (null == dataValueType
            // or
                )
                || (// The type is a numeric type
                ((dataValueType instanceof NumericType
                // and
                ) && (// its kind is "Integer"
                ((NumericType) dataValueType).getKind() == NumericTypeKind.INTEGER))
                // and
                &&
                // the min value of this type is equal or greater than 0
                ((null != ((NumericType) dataValueType).getOwnedMinValue()) && (DataValueHelper.getValueAsInt(((NumericType) dataValueType).getOwnedMinValue()) >= 0)))
            //
            ) {
              availableElements.add(dataValue);
            }
          }
        }
      }
    }
    return availableElements;
  }

  /**
   * This method allows to get all positive integer numeric values and expressions in the given level
   * @param dataPkg_p the data package
   * @return the list of the corresponding values
   */
  public static List<CapellaElement> getPositiveIntergerNumValAndExpressionsInLevel(DataPkg dataPkg_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (null != dataPkg_p) {
      for (Object object : EObjectExt.getAll(dataPkg_p, DatavaluePackage.Literals.DATA_VALUE)) {
        if (object instanceof DataValue) {
          DataValue dataValue = (DataValue) object;

          // Tests every data value in the data package
          if (dataValue instanceof NumericValue) {
            NumericType dataValueType = ((NumericValue) dataValue).getNumericType();
            if ((null != dataValueType) && NumericTypeKind.INTEGER.equals(dataValueType.getKind()) && (null != dataValueType.getOwnedMinValue())
                && (DataValueHelper.getValueAsInt(dataValueType.getOwnedMinValue()) >= 0)) {
              availableElements.add(dataValue);
            }
          } else if (dataValue instanceof AbstractExpressionValue) {
            DataType dataValueType = ((AbstractExpressionValue) dataValue).getExpressionType();
            if ((null != dataValueType)
                && ((dataValueType instanceof NumericType) && NumericTypeKind.INTEGER.equals(((NumericType) dataValueType).getKind()))
                && ((null != ((NumericType) dataValueType).getOwnedMinValue()) && (DataValueHelper.getValueAsInt(((NumericType) dataValueType)
                    .getOwnedMinValue()) >= 0))) {
              availableElements.add(dataValue);
            }
          }
        }
      }
    }
    return availableElements;
  }

  /**
   * Returns the applicable values for the given string type border values in the given level
   * @param dataPkg_p the data package where the search is done
   * @param capellaElement_p the string type (should be a <code>StringType</code> instance
   * @see getApplicablePropertiesForStringBorderValuesInLevel(BlockArchitecture blockArchitecture_p)
   * @return the list of the available elements
   */
  public static List<CapellaElement> getApplicablePropertiesForStringBorderValuesInLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    // First get all properties following the same rule as for cardinalities
    List<CapellaElement> tempValues = getApplicablePropertiesForCardinalitiesInLevel(dataPkg_p);
    // But for the string types, the border values, if they are properties, cannot be properties typed by the current string type
    for (CapellaElement elemnt : tempValues) {
      if ((elemnt instanceof Property) && (((Property) elemnt).getType() != capellaElement_p)) {
        returnValue.add(elemnt);
      }
    }
    return returnValue;
  }

  /**
   * Returns the applicable literals<br>
   * @param blockArchitecture_p
   * @return
   */
  public static List<CapellaElement> getApplicableLiteralsInLevel(BlockArchitecture blockArchitecture_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(blockArchitecture_p);
    if (null != dataPkg) {
      for (DataType dataType : DataPkgExt.getAllDataTypes(dataPkg)) {
        if (dataType instanceof Enumeration) {
          Enumeration enumeration = (Enumeration) dataType;
          for (EnumerationLiteral literal : enumeration.getOwnedLiterals()) {
            availableElements.add(literal);
          }
        }

      }
    }
    return availableElements;
  }

  /**
   * Returns the applicable properties for cardinalities using this rule:<br>
   * All Properties typed by a NumericType which Kind is INTEGER and min value is equal or greater than 0
   * @param dataPkg_p
   * @return
   */
  public static List<CapellaElement> getApplicablePropertiesForCardinalitiesInLevel(DataPkg dataPkg_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (null != dataPkg_p) {
      for (CapellaElement capellaElement : DataPkgExt.getAllClassifierFromDataPkg(dataPkg_p)) {
        if (capellaElement instanceof Classifier) {
          Classifier classifier = (Classifier) capellaElement;
          // Gets the features of the classifier in order to get its properties
          EList<Feature> ownedFeatures = classifier.getOwnedFeatures();
          for (Feature feature : ownedFeatures) {
            if (feature instanceof Property) {
              // Then tests the properties in oder to checks if it matches with the rules
              Property property = (Property) feature;
              Type propertyType = property.getType();

              if (
              // if there is a type
              (null != propertyType
              // and
                  )
                  &&
                  // The type is a numeric type
                  ((propertyType instanceof NumericType
                  // and
                  ) && (// its kind is "Integer"
                  ((NumericType) propertyType).getKind() == NumericTypeKind.INTEGER))
                  // and
                  &&
                  // the min value of this type is equal or greater than 0
                  ((null != ((NumericType) propertyType).getOwnedMinValue()) && (DataValueHelper.getValueAsInt(((NumericType) propertyType).getOwnedMinValue()) >= 0))
              //
              ) {
                availableElements.add(property);
              }
            }
          }
        }
      }
    }
    return availableElements;
  }

  /**
   * Returns the applicable values for multiplicity elements (properties, collections and parameters) queries such as null and default values.<br>
   * The rules are the following: it returns:
   * <ul>
   * <li>All non typed respectively NumericValues, StringValues, BooleanValues, EnumValues or Expressions</li>
   * <li>All non typed Expressions</li>
   * <li>All non typed ComplexValues if the type of the Property/Parameter is not a <code>Datatype</code> but a <code>Class</code></li>
   * <li>All (NumericValues OR StringValues OR BooleanValues OR EnumValues OR Expressions OR ComplexeValues) typed by the type of the Property/Parameter</li>
   * </ul>
   * @param dataPkg_p the data package where the search is done
   * @param mElement_p the element on which the query is applied, shall be a property
   * @return the elements matching the described rules
   */
  public static List<CapellaElement> getStandardApplicableValuesForMultiplicityElementInLevel(DataPkg dataPkg_p, MultiplicityElement mElement_p,
      List<EReference> eReferences) {

    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    AbstractType mulElementType = null;
    if ((mElement_p instanceof Property) || (mElement_p instanceof Parameter)) {
      TypedElement typedElement = (TypedElement) mElement_p;
      // Gets the property type
      mulElementType = typedElement.getAbstractType();
    } else if (mElement_p instanceof Collection) {
      Collection collection = (Collection) mElement_p;
      mulElementType = collection.getType();
    }
    if (null == mulElementType) {
      // Returns an empty list if there is no type
      return availableElements;
    }
    if (null != dataPkg_p) {
      for (Object object : EObjectExt.getAll(dataPkg_p, DatavaluePackage.Literals.DATA_VALUE)) {
        if (object instanceof DataValue) {
          DataValue dataValue = (DataValue) object;
          // Processes all the data value
          AbstractType dataValueType = dataValue.getAbstractType();
          if (null != dataValueType) {
            if ((mulElementType instanceof StringType)
                && (eReferences.contains(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE) || eReferences
                    .contains(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE))) {
              // filter String value
              // if data value is typed by the same type as the property
              if ((dataValue instanceof AbstractStringValue) && (dataValueType == mulElementType)) {
                availableElements.add(dataValue);
              }
            }
            // If the data value has a type, then only returns the one typed by the same type as the property
            else if ((dataValueType == mulElementType)
                     && ((dataValue instanceof NumericValue) || (dataValue instanceof AbstractStringValue) || (dataValue instanceof LiteralBooleanValue)
                         || (dataValue instanceof BooleanReference) || (dataValue instanceof EnumerationLiteral) || (dataValue instanceof EnumerationReference)
                         || (dataValue instanceof AbstractExpressionValue) || (dataValue instanceof CollectionValue) || (dataValue instanceof ComplexValue))) {
              availableElements.add(dataValue);
            }
          }
          // The value doesn't have any type
          else if (
          // The data value is an expression
          (dataValue instanceof AbstractExpressionValue
          // or
              )
              ||
              // Its is a non typed complex type and the property type is a Class
              ((mulElementType instanceof org.polarsys.capella.core.data.information.Class) && (dataValue instanceof ComplexValue))) {
            availableElements.add(dataValue);
          } else if (
          // The property type is a Numeric Type and the data value a numeric value
          ((mulElementType instanceof NumericType) && (dataValue instanceof NumericValue))
          // or
              ||
              // The property type is a String Type and the data value a string value
              ((mulElementType instanceof StringType) && (dataValue instanceof AbstractStringValue)) ||
              // The property type is a Boolean Type and the data value a literal boolean value or a boolean reference
              ((mulElementType instanceof BooleanType) && ((dataValue instanceof LiteralBooleanValue) || (dataValue instanceof BooleanReference)))
              // or
              ||
              // The property type is an Enumeration and the data value an enumeration literal or an enumeration reference
              ((mulElementType instanceof Enumeration) && ((dataValue instanceof EnumerationLiteral) || (dataValue instanceof EnumerationReference)))) {
            availableElements.add(dataValue);
          }

        }
      }
    }
    return availableElements;
  }

  /**
   * Returns the applicable values for multiplicity elements (properties, collections and parameters) queries such as null and default values.<br>
   * The rules are the following: it returns:
   * <ul>
   * <li>If element is GeneralizableElement, consider to check all its super Elements</li>
   * <li>All non typed respectively NumericValues, StringValues, BooleanValues, EnumValues or Expressions</li>
   * <li>All non typed Expressions</li>
   * <li>All non typed ComplexValues if the type of the Property/Parameter is not a <code>Datatype</code> but a <code>Class</code></li>
   * <li>All (NumericValues OR StringValues OR BooleanValues OR EnumValues OR Expressions OR ComplexeValues) typed by the type of the Property/Parameter</li>
   * </ul>
   * @param dataPkg_p the data package where the search is done
   * @param mElement_p the element on which the query is applied, shall be a property
   * @return the elements matching the described rules
   */
  public static List<CapellaElement> getApplicableValuesForMultEleConsideringSuperGenElements(DataPkg dataPkg_p, MultiplicityElement mElement_p,
      List<EReference> eReferences) {

    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    AbstractType mulElementType = null;
    if ((mElement_p instanceof Property) || (mElement_p instanceof Parameter)) {
      TypedElement typedElement = (TypedElement) mElement_p;
      // Gets the property type
      mulElementType = typedElement.getAbstractType();
    } else if (mElement_p instanceof Collection) {
      Collection collection = (Collection) mElement_p;
      mulElementType = collection.getType();
    }
    if (null == mulElementType) {
      // Returns an empty list if there is no type
      return availableElements;
    }
    List<GeneralizableElement> superGenEles = new ArrayList<GeneralizableElement>();
    if (mElement_p instanceof GeneralizableElement) {
      superGenEles = GeneralizableElementExt.getAllSuperGeneralizableElements((GeneralizableElement) mElement_p);
    }
    // do not continue if not super elements
    if (!superGenEles.isEmpty()) {
      if (null != dataPkg_p) {
        for (Object object : EObjectExt.getAll(dataPkg_p, DatavaluePackage.Literals.DATA_VALUE)) {
          if (object instanceof DataValue) {
            DataValue dataValue = (DataValue) object;
            // Processes all the data value
            AbstractType dataValueType = dataValue.getAbstractType();
            if (null != dataValueType) {
              if ((mulElementType instanceof StringType)
                  && (eReferences.contains(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE) || eReferences
                      .contains(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE))) {
                // filter String value
                // if data value is typed by the same type as the property
                if ((dataValue instanceof AbstractStringValue) && superGenEles.contains(dataValueType)) {
                  availableElements.add(dataValue);
                }
              }
              // If the data value has a type, then only returns the one typed by the same type as the property
              else if (superGenEles.contains(dataValueType)
                       && ((dataValue instanceof NumericValue) || (dataValue instanceof AbstractStringValue) || (dataValue instanceof LiteralBooleanValue)
                           || (dataValue instanceof BooleanReference) || (dataValue instanceof EnumerationLiteral)
                           || (dataValue instanceof EnumerationReference) || (dataValue instanceof AbstractExpressionValue)
                           || (dataValue instanceof CollectionValue) || (dataValue instanceof ComplexValue))) {
                availableElements.add(dataValue);
              }
            }
            // The value doesn't have any type
            else if (
            // The data value is an expression
            (dataValue instanceof AbstractExpressionValue
            // or
                )
                ||
                // Its is a non typed complex type and the property type is a Class
                ((mulElementType instanceof org.polarsys.capella.core.data.information.Class) && (dataValue instanceof ComplexValue))) {
              availableElements.add(dataValue);
            } else if (
            // The property type is a Numeric Type and the data value a numeric value
            ((mulElementType instanceof NumericType) && (dataValue instanceof NumericValue))
            // or
                ||
                // The property type is a String Type and the data value a string value
                ((mulElementType instanceof StringType) && (dataValue instanceof AbstractStringValue)) ||
                // The property type is a Boolean Type and the data value a literal boolean value or a boolean reference
                ((mulElementType instanceof BooleanType) && ((dataValue instanceof LiteralBooleanValue) || (dataValue instanceof BooleanReference)))
                // or
                ||
                // The property type is an Enumeration and the data value an enumeration literal or an enumeration reference
                ((mulElementType instanceof Enumeration) && ((dataValue instanceof EnumerationLiteral) || (dataValue instanceof EnumerationReference)))) {
              availableElements.add(dataValue);
            }

          }
        }
      }
    }

    return availableElements;
  }

  /**
   * Returns in the given layer the Properties typed with the same type as the given capella element
   * @param dataPkg_p the data package where the search is done
   * @param mElement_p the element on which the query is applied, shall be a <code>TypedElement</code>
   * @return the elements matching the described rules
   */
  public static List<CapellaElement> getPropertiesWithTypeOf(DataPkg dataPkg_p, CapellaElement mElement_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (mElement_p instanceof TypedElement) {
      TypedElement typedElement = (TypedElement) mElement_p;
      // Gets the property type
      Type propertyType = typedElement.getType();
      if (null == propertyType) {
        // Returns an empty list if there is no type
        return availableElements;
      }
      if (null != dataPkg_p) {
        for (CapellaElement capellaElement : DataPkgExt.getAllClassifierFromDataPkg(dataPkg_p)) {
          if (capellaElement instanceof Classifier) {
            Classifier classifier = (Classifier) capellaElement;
            // Gets the features of the classifier in order to get its properties
            EList<Feature> ownedFeatures = classifier.getOwnedFeatures();
            for (Feature feature : ownedFeatures) {
              if (feature instanceof Property) {
                // Then tests the properties in oder to checks if it matches with the rules
                Property foundProperty = (Property) feature;
                Type foundPropertyType = foundProperty.getType();

                if (foundPropertyType == propertyType) {
                  availableElements.add(typedElement);
                }
              }
            }
          }
        }
      }
    }
    return availableElements;
  }

  /**
   * Allows to know if the given <code>EObject</code> can be instantiated as a given <code>EClass</code> instance.
   * @param object_p the object
   * @param eClass_p the classifier
   * @return <code>true</code> if the instantiation is possible, <code>false</code> otherwise
   */
  public static boolean canBeInstanciatedAs(EObject object_p, EClass eClass_p) {
    return EClassExt.canBeInstanciatedAs(object_p, eClass_p);
  }

  /**
   * Allows to know if the given <code>EObject</code> can be instantiated as a one of the given <code>EClass</code>'s instance.
   * @param object_p the object
   * @param eClasses_p the classifiers list
   * @return <code>true</code> if the instantiation is possible, <code>false</code> otherwise
   */
  public static boolean canBeInstanciatedAs(EObject object_p, List<EClass> eClasses_p) {
    for (EClass eclass : eClasses_p) {
      if (canBeInstanciatedAs(object_p, eclass)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns dataValue consistency with given dataType
   * @param dataPkg_p the data package where the search is done
   * @param dataType_p the given capella element
   * @return list of DataValue
   */
  public static List<CapellaElement> getDataValuesConsistantWithDataType(DataPkg dataPkg_p, DataType dataType_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(1);

    List<CapellaElement> dataValues = getDataValues(dataPkg_p);
    for (CapellaElement dataValue : dataValues) {
      if (dataValue instanceof DataValue) {
        if (DataValueExt.isDataValueConsitantWithDataType((DataValue) dataValue, dataType_p)) {
          result.add(dataValue);
        }
      }

    }
    return result;
  }
}
