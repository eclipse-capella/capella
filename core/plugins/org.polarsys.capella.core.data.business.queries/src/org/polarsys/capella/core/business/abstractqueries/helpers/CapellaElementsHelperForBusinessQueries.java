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
package org.polarsys.capella.core.business.abstractqueries.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
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
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.DataValueExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.model.utils.EClassExt;

/**
 * This class is a helper whose purpose is to give some utility methods for the business queries involving capella
 * elements.
 */
public class CapellaElementsHelperForBusinessQueries {

  /**
   * Constructor
   */
  private CapellaElementsHelperForBusinessQueries() {
  }

  /**
   * Filters the given capella elements list to return only the discrete datatypes
   * 
   * @param elements
   *          the list
   * @return the filtered list
   */
  public static List<EObject> getOnlyDiscreteDatatypes(List<EObject> elements) {
    List<EObject> onlyDiscreteDatatypes = new ArrayList<EObject>();
    for (EObject element : elements) {
      if ((element instanceof DataType) && ((DataType) element).isDiscrete()) {
        onlyDiscreteDatatypes.add(element);
      }
    }
    return onlyDiscreteDatatypes;
  }

  /**
   * Filters the given list to avoid elements which cannot be instantiated as an instance of the given eclass
   * 
   * @param list
   *          the list
   * @param eclass
   *          the eclass
   * @return the filtered list
   */
  public static List<CapellaElement> filterWithAvailableEClass(List<CapellaElement> list, EClass eclass) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    for (CapellaElement element : list) {
      if (CapellaElementsHelperForBusinessQueries.canBeInstanciatedAs(element, eclass)) {
        returnValue.add(element);
      }
    }
    return returnValue;
  }

  /**
   * Returns from the given layer:<br>
   * <ul>
   * <li>The data values whose type is the same as the given capella element.</li>
   * <li>The data values with no type if the argument <code>acceptNoType</code> is set to <code>true</code>.</li>
   * <li>The data values whose type is one of the parent type of the given capella element if the argument
   * <code>acceptSuperClassifiers</code> is set to <code>true</code>.</li>
   * </ul>
   * 
   * @param dataPkg
   *          the data package
   * @param capellaElement
   *          the given capella element
   * @param acceptNoType
   *          <code>true</code> if you want to put in the list the elements with no type
   * @param acceptSubClassifiers
   *          <code>true</code> if you want to put in the list the elements whose type is a sub classifier of the given
   *          capella element
   * @param restrictToInstancesOf
   *          if you want to restrict the result to instances of a class (or of its subclasses), pass it here, or null
   *          otherwise
   * @param toSkip
   *          the element you have to skip, typically, the element from where the query has been launched. May be
   *          <code>null</code> if you don't want to skip anything
   * @return the list of the applicable data values
   */
  public static List<CapellaElement> getValuesTypedBy(DataPkg dataPkg, GeneralizableElement capellaElement,
      boolean acceptNoType, boolean acceptSubClassifiers, EClass restrictToInstancesOf, CapellaElement toSkip) {
    List<EClass> restricToInstancesOf = null;
    if (null != restrictToInstancesOf) {
      restricToInstancesOf = new ArrayList<EClass>();
      restricToInstancesOf.add(restrictToInstancesOf);
    }
    return getValuesTypedBy(dataPkg, capellaElement, acceptNoType, acceptSubClassifiers, restricToInstancesOf, toSkip);
  }

  /**
   * Returns from the given layer:<br>
   * <ul>
   * <li>The data values whose type is the same as the given capella element.</li>
   * <li>The data values with no type if the argument <code>acceptNoType</code> is set to <code>true</code>.</li>
   * <li>The data values whose type is one of the parent type of the given capella element if the argument
   * <code>acceptSuperClassifiers</code> is set to <code>true</code>.</li>
   * </ul>
   * 
   * @param dataPkg
   *          the data package
   * @param type
   *          the given capella element
   * @param acceptNoType
   *          <code>true</code> if you want to put in the list the elements with no type
   * @param acceptSubClassifiers
   *          <code>true</code> if you want to put in the list the elements whose type is a sub classifier of the given
   *          capella element
   * @param toSkip
   *          the element you have to skip, typically, the element from where the query has been launched. May be
   *          <code>null</code> if you don't want to skip anything
   * @return the list of the applicable data values
   */
  public static List<CapellaElement> getValuesTypedBy(DataPkg dataPkg, DataType type, boolean acceptNoType,
      boolean acceptSubClassifiers, CapellaElement toSkip) {
    List<EClass> restricToInstancesOf = new ArrayList<EClass>();
    return getValuesTypedBy(dataPkg, type, acceptNoType, acceptSubClassifiers, restricToInstancesOf, toSkip);
  }

  /**
   * Returns from the given layer:<br>
   * <ul>
   * <li>The data values whose type is the same as the given capella element.</li>
   * <li>The data values with no type if the argument <code>acceptNoType</code> is set to <code>true</code>.</li>
   * <li>The data values whose type is one of the parent type of the given capella element if the argument
   * <code>acceptSuperClassifiers</code> is set to <code>true</code>.</li>
   * </ul>
   * 
   * @param dataPkg
   *          the data package
   * @param type
   *          the given capella element
   * @param acceptNoType
   *          <code>true</code> if you want to put in the list the elements with no type
   * @param acceptSubClassifiers
   *          <code>true</code> if you want to put in the list the elements whose type is a super classifier of the
   *          given capella element
   * @param restrictToInstancesOf
   *          if you want to restrict the result to instances of some classes (or of their subclasses), pass them here,
   *          or null otherwise
   * @param toSkip
   *          the element you have to skip, typically, the element from where the query has been launched. May be
   *          <code>null</code> if you don't want to skip anything
   * @return the list of the applicable data values
   */
  public static List<CapellaElement> getValuesTypedBy(DataPkg dataPkg, GeneralizableElement type, boolean acceptNoType,
      boolean acceptSubClassifiers, List<EClass> restrictToInstancesOf, CapellaElement toSkip) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (null != dataPkg) {
      // Gets a list of the superClassifiers of the data type
      List<GeneralizableElement> superClassifiers = GeneralizableElementExt.getAllSubGeneralizableElements(type);
      for (Object object : EObjectExt.getAll(dataPkg, DatavaluePackage.Literals.DATA_VALUE)) {
        if (object instanceof DataValue) {
          DataValue dataValue = (DataValue) object;
          if (((null != toSkip) && (dataValue != toSkip)) || (null == toSkip)) {
            //
            // Tests every data value in the data package
            AbstractType dataValueType = dataValue.getAbstractType();
            if ((acceptNoType && (null == dataValueType)) || ((null != dataValueType) && (dataValueType == type))) {
              // If the element is not typed (if acceptNoType is true) or if it has the given data type
              if (
              // if there is no restriction to some classes
              ((null == restrictToInstancesOf) || (0 == restrictToInstancesOf.size()))
                  // OR
                  ||
                  // The list is restricted to the given types
                  canBeInstanciatedAs(dataValue, restrictToInstancesOf)) {
                availableElements.add(dataValue);
              }
            } else if (acceptSubClassifiers) {
              // of not, tests with every one of the numeric type super classifiers if acceptSuperClassifiers is true
              for (CapellaElement superClassifier : superClassifiers) {
                if (superClassifier.equals(dataValueType) &&
                // if there is no restriction to some classes
                    ((null == restrictToInstancesOf) || (0 == restrictToInstancesOf.size())
                    // OR
                        ||
                        // The list is restricted to the given types
                        canBeInstanciatedAs(dataValue, restrictToInstancesOf))) {
                  availableElements.add(dataValue);
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
   * Returns the Capella Elements instances of the given <code>EClass</code> in the given <code>DataPkg</code>, but
   * avoiding the given <code>CapellaElement</code> if it is given.
   * 
   * @param dataPkg
   *          the data package
   * @param eclass
   *          the EClass
   * @param capellaElement
   *          the Capella element
   * @return a list containing instances of <code>CapellaElement</code>
   */
  public static List<EObject> getCapellaElementsInstancesOf(DataPkg dataPkg, EClass eclass,
      CapellaElement capellaElement) {
    return getCapellaElementsInstancesOf(dataPkg, (eclass != null) ? Collections.singletonList(eclass) : null,
        capellaElement);
  }

  /**
   * Returns the Capella Elements instances of the given <code>EClass</code> in the given <code>DataPkg</code>, but
   * avoiding the given <code>CapellaElement</code> if it is given.
   * 
   * @param dataPkg
   *          the data package
   * @param eclass
   *          the list of EClasses
   * @param capellaElement
   *          the Capella element
   * @return a list containing instances of <code>CapellaElement</code>
   */
  public static List<EObject> getCapellaElementsInstancesOf(DataPkg dataPkg, List<EClass> eclasses,
      CapellaElement capellaElement) {
    List<EObject> returnValue = new ArrayList<EObject>();
    // If the data package is not null
    if (null != dataPkg) {
      // Gets a list of the Capella Elements in the data package
      Set<EObject> allElements = new HashSet<EObject>();
      if (null != eclasses) {
        for (EClass cls : eclasses) {
          allElements.addAll(EObjectExt.getAll(dataPkg, cls));
        }
      } else {
        // If no <code><EClass/code> has been given in parameter, then retrieves all Capella Elements
        allElements = EObjectExt.getAll(dataPkg, CapellacorePackage.Literals.CAPELLA_ELEMENT);
      }
      for (EObject object : allElements) {
        if (object instanceof CapellaElement) {
          CapellaElement elt = (CapellaElement) object;
          if (
          // There is an element to avoid and it is not the current one OR there is no element to avoid
          (capellaElement == null || !elt.equals(capellaElement))
              // AND
              &&
              // The current element is an instance of the wanted <code>EClass</code>
              canBeInstanciatedAs(elt, eclasses)) {
            returnValue.add(elt);
          }
        }
      }
    }
    return returnValue;
  }

  /**
   * Gets the data values instances of one of the given <code>EClass</code> in the given <code>DataPkg</code>
   * 
   * @param dataPkg
   *          the data package
   * @param eClass
   *          the <code>EClass</code>
   * @param nonTyped
   *          <code>true</code> if you want to accept the data values with no type, <code>false</code> otherwise
   * @param typed
   *          <code>true</code> if you want to accept the data values with a type, <code>false</code> otherwise
   * @return
   */
  public static List<CapellaElement> getDataValuesInstancesOf(DataPkg dataPkg, EClass eClass, boolean nonTyped,
      boolean typed) {
    List<EClass> eClasses = new ArrayList<EClass>();
    eClasses.add(eClass);
    return getDataValuesInstancesOf(dataPkg, eClasses, nonTyped, typed);
  }

  /**
   * Gets the data values instances of one of the given <code>EClass</code>'s in the given <code>DataPkg</code>
   * 
   * @param dataPkg
   *          the data package
   * @param eClasses
   *          the <code>EClass</code>'s
   * @param nonTyped
   *          <code>true</code> if you want to accept the data values with no type, <code>false</code> otherwise
   * @param typed
   *          <code>true</code> if you want to accept the data values with a type, <code>false</code> otherwise
   * @return
   */
  public static List<CapellaElement> getDataValuesInstancesOf(DataPkg dataPkg, List<EClass> eClasses, boolean nonTyped,
      boolean typed) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (null != dataPkg) {
      for (EObject object : EObjectExt.getAll(dataPkg, DatavaluePackage.Literals.DATA_VALUE)) {
        if (object instanceof DataValue) {
          DataValue dataValue = (DataValue) object;

          // Tests every data value in the data package
          for (EClass eClass : eClasses) {
            // Check for each one of the searched EClasses
            if (
            // Its is an instance of one of the searched type
            canBeInstanciatedAs(dataValue, eClass)
                // AND
                && (
                // you want the untyped ones and the data value is untyped
                (nonTyped && (null == dataValue.getAbstractType()))
                    // OR
                    ||
                    // you want the typed ones and the data value is typed
                    (typed && (null != dataValue.getAbstractType())))) {
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
   * 
   * @param dataPkg
   *          the data package
   * @return the list of the data values
   */
  public static List<CapellaElement> getDataValues(DataPkg dataPkg) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (null != dataPkg) {
      for (Object object : EObjectExt.getAll(dataPkg, DatavaluePackage.Literals.DATA_VALUE)) {
        if (object instanceof DataValue) {
          availableElements.add((DataValue) object);
        }
      }
    }
    return availableElements;
  }

  /**
   * Returns the data values typed with root super types of the given capella element in the given layer
   * 
   * @param blockArchitecture
   *          the given layer
   * @param capellaElement
   *          the given capella element
   * @param limitToInstancesOf
   *          pass an <code>EClass</code> here if you want to limit the results to instances of this
   *          <code>EClass</code>, pass <code>null</code> otherwise
   * @return the list of the applicable data values
   */
  public static List<CapellaElement> getDataValuesTypedWithRootSuperTypesOf(BlockArchitecture blockArchitecture,
      CapellaElement capellaElement, EClass limitToInstancesOf) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (capellaElement instanceof DataType) {
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(blockArchitecture);
      // If the data package is not null
      if (null != dataPkg) {
        // Gets a list of the superClassifiers of the data type
        List<GeneralizableElement> rootSuperClassifiers = GeneralizableElementExt
            .getRootSupertypes((DataType) capellaElement);
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
                (null != limitToInstancesOf
                // AND
                ) &&
                // the current data value is an instance of this EClass
                    canBeInstanciatedAs(dataValue, limitToInstancesOf))
                    // OR
                    || (// the results are not limited to any given EClass
                    null == limitToInstancesOf)) {
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
   * <li>The properties with no type if <code>acceptNoType</code> is <code>true</code>.</li>
   * <li>The properties whose type is the same as the given capella element.</li>
   * <li>The properties whose type is one of the parent type of the given capella element.</li>
   * </ul>
   * 
   * @param dataPkg
   *          the data package
   * @param dataType
   *          the given capella element. If <code>null</code>, every property is returned
   * @param acceptNoType
   *          <code>true</code> if you accept untyped elements, <code>false</code> otherwise
   * @return the list of the applicable data values
   */
  public static List<CapellaElement> getPropertiesTypedBy(DataPkg dataPkg, GeneralizableElement dataType,
      boolean acceptNoType) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (null != dataPkg) {
      List<GeneralizableElement> superClassifiers = GeneralizableElementExt.getAllSubGeneralizableElements(dataType);
      for (EObject capellaElement : DataPkgExt.getAllClassifierFromDataPkg(dataPkg)) {
        if (capellaElement instanceof Classifier) {
          Classifier classifier = (Classifier) capellaElement;
          // Gets the features of the classifier in order to get its properties
          EList<Feature> ownedFeatures = classifier.getOwnedFeatures();
          for (Feature feature : ownedFeatures) {
            if (feature instanceof Property) {
              // Then tests the properties in oder to checks if it matches with the rules
              Property property = (Property) feature;
              AbstractType propertyType = property.getAbstractType();
              if ((dataType == null) || (acceptNoType && (null == propertyType))
                  || ((null != propertyType) && (propertyType == dataType))) {
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
   * 
   * @param dataPkg
   *          the data package where the search is done
   * @return the list of the available elements
   */
  public static List<CapellaElement> getApplicableValuesForCardinalitiesInLevel(DataPkg dataPkg) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (null != dataPkg) {
      for (Object object : EObjectExt.getAll(dataPkg, DatavaluePackage.Literals.DATA_VALUE)) {
        if (object instanceof DataValue) {
          DataValue dataValue = (DataValue) object;
          if ((dataValue instanceof NumericValue) || (dataValue instanceof AbstractExpressionValue)) {
            // Tests every data value in the data package
            AbstractType dataValueType = dataValue.getAbstractType();
            if (
            // if there is no type
            (null == dataValueType
            // or
            ) || (// The type is a numeric type
            ((dataValueType instanceof NumericType
            // and
            ) && (// its kind is "Integer"
            ((NumericType) dataValueType).getKind() == NumericTypeKind.INTEGER))
                // and
                &&
                // the min value of this type is equal or greater than 0
                ((null != ((NumericType) dataValueType).getOwnedMinValue())
                    && (DataValueHelper.getValueAsInt(((NumericType) dataValueType).getOwnedMinValue()) >= 0)))
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
   * 
   * @param dataPkg
   *          the data package
   * @return the list of the corresponding values
   */
  public static List<CapellaElement> getPositiveIntergerNumValAndExpressionsInLevel(DataPkg dataPkg) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (null != dataPkg) {
      for (Object object : EObjectExt.getAll(dataPkg, DatavaluePackage.Literals.DATA_VALUE)) {
        if (object instanceof DataValue) {
          DataValue dataValue = (DataValue) object;

          // Tests every data value in the data package
          if (dataValue instanceof NumericValue) {
            NumericType dataValueType = ((NumericValue) dataValue).getNumericType();
            if ((null != dataValueType) && NumericTypeKind.INTEGER.equals(dataValueType.getKind())
                && (null != dataValueType.getOwnedMinValue())
                && (DataValueHelper.getValueAsInt(dataValueType.getOwnedMinValue()) >= 0)) {
              availableElements.add(dataValue);
            }
          } else if (dataValue instanceof AbstractExpressionValue) {
            DataType dataValueType = ((AbstractExpressionValue) dataValue).getExpressionType();
            if ((null != dataValueType)
                && ((dataValueType instanceof NumericType)
                    && NumericTypeKind.INTEGER.equals(((NumericType) dataValueType).getKind()))
                && ((null != ((NumericType) dataValueType).getOwnedMinValue())
                    && (DataValueHelper.getValueAsInt(((NumericType) dataValueType).getOwnedMinValue()) >= 0))) {
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
   * 
   * @param dataPkg
   *          the data package where the search is done
   * @param capellaElement
   *          the string type (should be a <code>StringType</code> instance
   * @see getApplicablePropertiesForStringBorderValuesInLevel(BlockArchitecture blockArchitecture)
   * @return the list of the available elements
   */
  public static List<CapellaElement> getApplicablePropertiesForStringBorderValuesInLevel(DataPkg dataPkg,
      CapellaElement capellaElement) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    // First get all properties following the same rule as for cardinalities
    List<CapellaElement> tempValues = getApplicablePropertiesForCardinalitiesInLevel(dataPkg);
    // But for the string types, the border values, if they are properties, cannot be properties typed by the current
    // string type
    for (CapellaElement elemnt : tempValues) {
      if ((elemnt instanceof Property) && (((Property) elemnt).getType() != capellaElement)) {
        returnValue.add(elemnt);
      }
    }
    return returnValue;
  }

  /**
   * Returns the applicable literals<br>
   * 
   * @param blockArchitecture
   * @return
   */
  public static List<CapellaElement> getApplicableLiteralsInLevel(BlockArchitecture blockArchitecture) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(blockArchitecture);
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
   * 
   * @param dataPkg
   * @return
   */
  public static List<CapellaElement> getApplicablePropertiesForCardinalitiesInLevel(DataPkg dataPkg) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (null != dataPkg) {
      for (EObject capellaElement : DataPkgExt.getAllClassifierFromDataPkg(dataPkg)) {
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
              ) &&
              // The type is a numeric type
                  ((propertyType instanceof NumericType
                  // and
                  ) && (// its kind is "Integer"
                  ((NumericType) propertyType).getKind() == NumericTypeKind.INTEGER))
                  // and
                  &&
                  // the min value of this type is equal or greater than 0
                  ((null != ((NumericType) propertyType).getOwnedMinValue())
                      && (DataValueHelper.getValueAsInt(((NumericType) propertyType).getOwnedMinValue()) >= 0))
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
   * Returns the applicable values for multiplicity elements (properties, collections and parameters) queries such as
   * null and default values.<br>
   * The rules are the following: it returns:
   * <ul>
   * <li>All non typed respectively NumericValues, StringValues, BooleanValues, EnumValues or Expressions</li>
   * <li>All non typed Expressions</li>
   * <li>All non typed ComplexValues if the type of the Property/Parameter is not a <code>Datatype</code> but a
   * <code>Class</code></li>
   * <li>All (NumericValues OR StringValues OR BooleanValues OR EnumValues OR Expressions OR ComplexeValues) typed by
   * the type of the Property/Parameter</li>
   * </ul>
   * 
   * @param dataPkg
   *          the data package where the search is done
   * @param mElement
   *          the element on which the query is applied, shall be a property
   * @return the elements matching the described rules
   */
  public static List<CapellaElement> getStandardApplicableValuesForMultiplicityElementInLevel(DataPkg dataPkg,
      MultiplicityElement mElement, List<EReference> eReferences) {

    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    AbstractType mulElementType = null;
    if ((mElement instanceof Property) || (mElement instanceof Parameter)) {
      TypedElement typedElement = (TypedElement) mElement;
      // Gets the property type
      mulElementType = typedElement.getAbstractType();
    } else if (mElement instanceof Collection) {
      Collection collection = (Collection) mElement;
      mulElementType = collection.getType();
    }
    if (null == mulElementType) {
      // Returns an empty list if there is no type
      return availableElements;
    }
    if (null != dataPkg) {
      for (Object object : EObjectExt.getAll(dataPkg, DatavaluePackage.Literals.DATA_VALUE)) {
        if (object instanceof DataValue) {
          DataValue dataValue = (DataValue) object;
          // Processes all the data value
          AbstractType dataValueType = dataValue.getAbstractType();
          if (null != dataValueType) {
            if ((mulElementType instanceof StringType)
                && (eReferences.contains(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE)
                    || eReferences.contains(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE))) {
              // filter String value
              // if data value is typed by the same type as the property
              if ((dataValue instanceof AbstractStringValue) && (dataValueType == mulElementType)) {
                availableElements.add(dataValue);
              }
            }
            // If the data value has a type, then only returns the one typed by the same type as the property
            else if ((dataValueType == mulElementType) && ((dataValue instanceof NumericValue)
                || (dataValue instanceof AbstractStringValue) || (dataValue instanceof LiteralBooleanValue)
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
          ) ||
          // Its is a non typed complex type and the property type is a Class
              ((mulElementType instanceof org.polarsys.capella.core.data.information.Class)
                  && (dataValue instanceof ComplexValue))) {
            availableElements.add(dataValue);
          } else if (
          // The property type is a Numeric Type and the data value a numeric value
          ((mulElementType instanceof NumericType) && (dataValue instanceof NumericValue))
              // or
              ||
              // The property type is a String Type and the data value a string value
              ((mulElementType instanceof StringType) && (dataValue instanceof AbstractStringValue)) ||
              // The property type is a Boolean Type and the data value a literal boolean value or a boolean reference
              ((mulElementType instanceof BooleanType)
                  && ((dataValue instanceof LiteralBooleanValue) || (dataValue instanceof BooleanReference)))
              // or
              ||
              // The property type is an Enumeration and the data value an enumeration literal or an enumeration
              // reference
              ((mulElementType instanceof Enumeration)
                  && ((dataValue instanceof EnumerationLiteral) || (dataValue instanceof EnumerationReference)))) {
            availableElements.add(dataValue);
          }

        }
      }
    }
    return availableElements;
  }

  /**
   * Returns the applicable values for multiplicity elements (properties, collections and parameters) queries such as
   * null and default values.<br>
   * The rules are the following: it returns:
   * <ul>
   * <li>If element is GeneralizableElement, consider to check all its super Elements</li>
   * <li>All non typed respectively NumericValues, StringValues, BooleanValues, EnumValues or Expressions</li>
   * <li>All non typed Expressions</li>
   * <li>All non typed ComplexValues if the type of the Property/Parameter is not a <code>Datatype</code> but a
   * <code>Class</code></li>
   * <li>All (NumericValues OR StringValues OR BooleanValues OR EnumValues OR Expressions OR ComplexeValues) typed by
   * the type of the Property/Parameter</li>
   * </ul>
   * 
   * @param dataPkg
   *          the data package where the search is done
   * @param mElement
   *          the element on which the query is applied, shall be a property
   * @return the elements matching the described rules
   */
  public static List<CapellaElement> getApplicableValuesForMultEleConsideringSuperGenElements(DataPkg dataPkg,
      MultiplicityElement mElement, List<EReference> eReferences) {

    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    AbstractType mulElementType = null;
    if ((mElement instanceof Property) || (mElement instanceof Parameter)) {
      TypedElement typedElement = (TypedElement) mElement;
      // Gets the property type
      mulElementType = typedElement.getAbstractType();
    } else if (mElement instanceof Collection) {
      Collection collection = (Collection) mElement;
      mulElementType = collection.getType();
    }
    if (null == mulElementType) {
      // Returns an empty list if there is no type
      return availableElements;
    }
    List<GeneralizableElement> superGenEles = new ArrayList<GeneralizableElement>();
    if (mElement instanceof GeneralizableElement) {
      superGenEles = GeneralizableElementExt.getAllSuperGeneralizableElements((GeneralizableElement) mElement);
    }
    // do not continue if not super elements
    if (!superGenEles.isEmpty()) {
      if (null != dataPkg) {
        for (Object object : EObjectExt.getAll(dataPkg, DatavaluePackage.Literals.DATA_VALUE)) {
          if (object instanceof DataValue) {
            DataValue dataValue = (DataValue) object;
            // Processes all the data value
            AbstractType dataValueType = dataValue.getAbstractType();
            if (null != dataValueType) {
              if ((mulElementType instanceof StringType)
                  && (eReferences.contains(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE)
                      || eReferences.contains(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE))) {
                // filter String value
                // if data value is typed by the same type as the property
                if ((dataValue instanceof AbstractStringValue) && superGenEles.contains(dataValueType)) {
                  availableElements.add(dataValue);
                }
              }
              // If the data value has a type, then only returns the one typed by the same type as the property
              else if (superGenEles.contains(dataValueType) && ((dataValue instanceof NumericValue)
                  || (dataValue instanceof AbstractStringValue) || (dataValue instanceof LiteralBooleanValue)
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
            ) ||
            // Its is a non typed complex type and the property type is a Class
                ((mulElementType instanceof org.polarsys.capella.core.data.information.Class)
                    && (dataValue instanceof ComplexValue))) {
              availableElements.add(dataValue);
            } else if (
            // The property type is a Numeric Type and the data value a numeric value
            ((mulElementType instanceof NumericType) && (dataValue instanceof NumericValue))
                // or
                ||
                // The property type is a String Type and the data value a string value
                ((mulElementType instanceof StringType) && (dataValue instanceof AbstractStringValue)) ||
                // The property type is a Boolean Type and the data value a literal boolean value or a boolean reference
                ((mulElementType instanceof BooleanType)
                    && ((dataValue instanceof LiteralBooleanValue) || (dataValue instanceof BooleanReference)))
                // or
                ||
                // The property type is an Enumeration and the data value an enumeration literal or an enumeration
                // reference
                ((mulElementType instanceof Enumeration)
                    && ((dataValue instanceof EnumerationLiteral) || (dataValue instanceof EnumerationReference)))) {
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
   * 
   * @param dataPkg
   *          the data package where the search is done
   * @param mElement
   *          the element on which the query is applied, shall be a <code>TypedElement</code>
   * @return the elements matching the described rules
   */
  public static List<CapellaElement> getPropertiesWithTypeOf(DataPkg dataPkg, CapellaElement mElement) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (mElement instanceof TypedElement) {
      TypedElement typedElement = (TypedElement) mElement;
      // Gets the property type
      Type propertyType = typedElement.getType();
      if (null == propertyType) {
        // Returns an empty list if there is no type
        return availableElements;
      }
      if (null != dataPkg) {
        for (EObject capellaElement : DataPkgExt.getAllClassifierFromDataPkg(dataPkg)) {
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
   * 
   * @param object
   *          the object
   * @param eClass
   *          the classifier
   * @return <code>true</code> if the instantiation is possible, <code>false</code> otherwise
   */
  public static boolean canBeInstanciatedAs(EObject object, EClass eClass) {
    return EClassExt.canBeInstanciatedAs(object, eClass);
  }

  /**
   * Allows to know if the given <code>EObject</code> can be instantiated as a one of the given <code>EClass</code>'s
   * instance.
   * 
   * @param object
   *          the object
   * @param eClasses
   *          the classifiers list
   * @return <code>true</code> if the instantiation is possible, <code>false</code> otherwise
   */
  public static boolean canBeInstanciatedAs(EObject object, List<EClass> eClasses) {
    for (EClass eclass : eClasses) {
      if (canBeInstanciatedAs(object, eclass)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns dataValue consistency with given dataType
   * 
   * @param dataPkg
   *          the data package where the search is done
   * @param dataType
   *          the given capella element
   * @return list of DataValue
   */
  public static List<CapellaElement> getDataValuesConsistantWithDataType(DataPkg dataPkg, DataType dataType) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(1);

    List<CapellaElement> dataValues = getDataValues(dataPkg);
    for (CapellaElement dataValue : dataValues) {
      if ((dataValue instanceof DataValue)
          && DataValueExt.isDataValueConsitantWithDataType((DataValue) dataValue, dataType)) {
        result.add(dataValue);
      }
    }
    return result;
  }

  public static List<EObject> getDataFromLevel(DataPkg dataPkg, CapellaElement capellaElement, EClass type) {
    List<EObject> returnValue = new ArrayList<EObject>();
    List<EObject> availableElemsInTermOfTypes = CapellaElementsHelperForBusinessQueries
        .getCapellaElementsInstancesOf(dataPkg, type, capellaElement);
    for (EObject superCandidate : availableElemsInTermOfTypes) {
      if ((superCandidate instanceof GeneralizableElement) && (capellaElement instanceof GeneralizableElement)
          && isGoodSupertypeCandidate((GeneralizableElement) capellaElement, (GeneralizableElement) superCandidate)) {
        returnValue.add(superCandidate);
      }
    }
    return returnValue;
  }

  /**
   * Verifies if the candidate is a good supertype candidate for the context element by checking
   * <ul>
   * <li>they cannot be the same element</li>
   * <li>the context element is not already a supertype of the candidate, since that would introduce a cycle</li>
   * <li>the candidate cannot be a supertype of any existing supertype of the candidate</li>
   * </ul>
   * The third restriction is rather artificial, but that's how it was implemented before..
   * 
   * @param context
   * @param candidate
   * @return
   */
  public static boolean isGoodSupertypeCandidate(GeneralizableElement context, GeneralizableElement candidate) {
    return context != candidate // can't be a supertype of itself
        && !GeneralizableElementExt.getAllSuperGeneralizableElements(candidate).contains(context) // can't introduce a
                                                                                                  // cycle
        && !isSupertypeOfCurrentSupertypes(context, candidate); // can't be a supertype of current supertypes
  }

  /**
   * Is the candidate element a supertype of any of the current supertypes of the context element
   * 
   * @param context
   * @param candidate
   * @return
   */
  public static boolean isSupertypeOfCurrentSupertypes(GeneralizableElement context, GeneralizableElement candidate) {
    boolean result = false;
    for (GeneralizableElement sup : context.getSuper()) {
      if (GeneralizableElementExt.getAllSuperGeneralizableElements(sup).contains(candidate)) {
        result = true;
        break;
      }
    }
    return result;
  }

  /**
   * Retrieves all CapellaElements of type <strong>targetClass</strong> from the <strong>currentProject</strong> root
   * and provided <strong>blockArchitectures</strong>.
   * 
   * @param targetClass
   * @param currentProject
   * @param blkArchs
   * @return
   */
  public static List<CapellaElement> getFromProjectAndArchitectures(EClass targetClass, Project currentProject,
      List<BlockArchitecture> blkArchs) {

    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    EClass ignoredBlkArchClass = CapellacorePackage.Literals.MODELLING_ARCHITECTURE;

    // get all elements from current and upper architectures
    for (BlockArchitecture blkArch : blkArchs) {
      availableElements.addAll(
          EObjectExt.getAll(blkArch, targetClass).stream().map(CapellaElement.class::cast).collect(Collectors.toSet()));
    }

    // get all element from project and all subfolders excluding the modeling architecture packages
    ArrayList<EClass> filteredArchitectures = new ArrayList<>();
    filteredArchitectures.add(ignoredBlkArchClass);
    for (EObject content : currentProject.eContents()) {
      availableElements.addAll(EObjectExt.getAllFiltered(content, targetClass, filteredArchitectures).stream()
          .map(CapellaElement.class::cast).collect(Collectors.toSet()));
    }

    return availableElements;
  }

  /**
   * Retrieves all CapellaElements of type <strong>targetClass</strong> from the <strong>currentProject</strong> root
   * and current and upper <strong>blockArchitectures</strong> retrieved from the provided <strong>element</strong>.
   * 
   * @param element
   * @param targetClass
   * @return
   */
  public static List<CapellaElement> getFromProjectCurrentAndUpperArchitectures(CapellaElement element,
      EClass targetClass) {

    List<BlockArchitecture> blkArchs = BlockArchitectureExt.getRootAndPreviousBlockArchitectures(element);
    Project currentProject = ProjectExt.getProject(element);

    return getFromProjectAndArchitectures(targetClass, currentProject, blkArchs);
  }

  /**
   * Retrieves all CapellaElements of type <strong>targetClass</strong> from all library roots, current and upper
   * corresponding library <strong>blockArchitectures</strong> referenced by the project that contains the provided
   * <strong>element</strong>.
   * 
   * @param element
   * @param targetClass
   * @return
   */
  @SuppressWarnings("restriction")
  public static List<CapellaElement> getFromProjectCurrentAndUpperArchitecturesLib(CapellaElement element,
      EClass targetClass) {

    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    List<BlockArchitecture> blkArchs = BlockArchitectureExt.getRootAndPreviousBlockArchitectures(element);
    IModel currentProject = ILibraryManager.INSTANCE.getModel(element);

    if (element instanceof CapellaElement) {
      
      // Get all libraries referenced by the element's project
      List<IModel> libraries = (List<IModel>) LibraryManagerExt.getAllActivesReferences(currentProject);
      for (IModel library : libraries) {
        if (library instanceof CapellaModel) {
          
          // Get OA/SA/LA/PA levels from libraries
          List<BlockArchitecture> correspondingBlocks = blkArchs.stream()
              .map(arch -> QueryExt.getCorrespondingElementInLibrary(arch, (CapellaModel) library))
              .map(BlockArchitecture.class::cast).collect(Collectors.toList());

          // Get library project
          Project libraryProject = ((CapellaModel) library).getProject(TransactionHelper.getEditingDomain(element));
          
          // Add all elements found recursively from the project root and provided architectures
          availableElements.addAll(getFromProjectAndArchitectures(targetClass, libraryProject, correspondingBlocks));
        }
      }
    }

    return availableElements;
  }
}
