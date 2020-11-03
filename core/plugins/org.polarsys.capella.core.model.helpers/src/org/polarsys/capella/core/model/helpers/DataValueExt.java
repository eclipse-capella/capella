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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.information.CollectionValueReference;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.ComplexValueReference;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.information.datavalue.StringReference;

/**
 */
public class DataValueExt {

  /**
   * @see #getDataValueDependencies(DataValue)
   */
  public static Map<AbstractDependenciesPkg, Collection<EObject>> getDataValueDependencies2(DataValue dataValue) {

    Map<AbstractDependenciesPkg, Collection<EObject>> result = new HashMap<AbstractDependenciesPkg, Collection<EObject>>();

    // type of value
    AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result, dataValue.getType());

    return result;
  }

  /**
   * @param dataValue
   * @return all dependent packages of the collection
   */
  public static Collection<AbstractDependenciesPkg> getDataValueDependencies(DataValue dataValue) {
    return getDataValueDependencies2(dataValue).keySet();
  }

  public static ComponentArchitecture getRootComponentArchitecture(DataValue dataValue) {
    ComponentArchitecture arch = null;
    if (dataValue != null) {
      Object container = dataValue.eContainer();
      if (container instanceof Component) {
        arch = ComponentExt.getRootComponentArchitecture((Component) container);
      } else if (container instanceof Structure) {
        arch = StructureExt.getRootComponentArchitecture((Structure) container);
      }
    }
    return arch;
  }

  /**
   * @param modelElement
   *          : any 'ModelElement'
   * @return : 'BlockArchitecture', value can also be null
   */
  public static BlockArchitecture getRootBlockArchitecture(ModelElement modelElement) {
    BlockArchitecture arch = null;
    if (modelElement != null) {
      EObject container = modelElement.eContainer();
      if (container instanceof BlockArchitecture) {
        return (BlockArchitecture) container;
      } else if (container instanceof Component) {
        arch = ComponentExt.getRootBlockArchitecture((Component) container);
      } else if (container instanceof Structure) {
        arch = StructureExt.getRootBlockArchitecture((Structure) container);
      } else if (container instanceof Classifier) {
        arch = ClassifierExt.getRootBlockArchitecture((Classifier) container);
      } else {
        EObject container2 = container.eContainer();
        if (null != container2) {
          arch = getRootBlockArchitecture((ModelElement) container2);
        }
      }
    }
    return arch;
  }

  public static Component getRootComponent(ModelElement modelElement) {
    Component comp = null;
    if (modelElement != null) {
      Object container = modelElement.eContainer();
      if (container instanceof Component) {
        comp = (Component) container;
      } else if (container instanceof Structure) {
        comp = StructureExt.getRootComponent((Structure) container);
      }
    }
    return comp;
  }

  /**
   * Gets all the DataPkgs from the Parent Hierarchy of the root component/component architecture of the current
   * DataTypePkg according to layer visibility and multiple decomposition rules
   * 
   * @param dataTypePkg
   *          the DataTypePkg
   * @return list of DataPkgs
   */
  static public List<DataPkg> getDataPkgsFromParentHierarchy(ModelElement modelElement) {
    List<DataPkg> list = new ArrayList<DataPkg>(1);
    if (null != modelElement) {
      BlockArchitecture compArch = getRootBlockArchitecture(modelElement);
      if (null != compArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
        if (null != dataPkg) {
          list.add(dataPkg);
        }
        // if the layer visibility is there
        if (compArch instanceof SystemEngineering) {
          return list; // return if SystemEngineering
        }
        list.addAll(DataPkgExt.getDataPkgsFromBlockArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(modelElement);
      if (null != parentComp) {
        DataPkg dataPkg = parentComp.getOwnedDataPkg();
        if (null != dataPkg) {
          list.add(dataPkg);
        }
        list.addAll(DataPkgExt.getDataPkgsFromComponentParent(parentComp));
      }
    }
    return list;
  }

  /**
   * check data value and data type consistency
   * 
   * @param datValue
   * @param dataType
   * @return true if valid
   */
  static public boolean isDataValueConsitantWithDataType(DataValue datValue, DataType dataType) {
    if ((null != dataType) && (null != datValue)) {
      if ((dataType instanceof NumericType) || (dataType instanceof PhysicalQuantity)) {
        if (datValue instanceof NumericValue) {
          return true;
        }
      } else if (dataType instanceof BooleanType) {
        if (datValue instanceof AbstractBooleanValue) {
          return true;
        }
      } else if (dataType instanceof StringType) {
        if (datValue instanceof AbstractStringValue) {
          return true;
        }
      } else if (dataType instanceof Enumeration) {
        if (datValue instanceof AbstractEnumerationValue) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * @param eObj
   * @param referencedValue
   * @return
   */
  public static DataValue getReferencedValue(EObject eObj) {
    DataValue referencedValue = null;
    if (eObj instanceof BooleanReference) {
      referencedValue = ((BooleanReference) eObj).getReferencedValue();
    } else if (eObj instanceof NumericReference) {
      referencedValue = ((NumericReference) eObj).getReferencedValue();
    } else if (eObj instanceof StringReference) {
      referencedValue = ((StringReference) eObj).getReferencedValue();
    } else if (eObj instanceof EnumerationReference) {
      referencedValue = ((EnumerationReference) eObj).getReferencedValue();
    } else if (eObj instanceof ComplexValueReference) {
      referencedValue = ((ComplexValueReference) eObj).getReferencedValue();
    } else if (eObj instanceof CollectionValueReference) {
      referencedValue = ((CollectionValueReference) eObj).getReferencedValue();
    }
    return referencedValue;
  }

  public static Property getReferencedProperty(EObject eObj) {

    if (eObj instanceof BooleanReference) {
      return ((BooleanReference) eObj).getReferencedProperty();
    } else if (eObj instanceof NumericReference) {
      return ((NumericReference) eObj).getReferencedProperty();
    } else if (eObj instanceof StringReference) {
      return ((StringReference) eObj).getReferencedProperty();
    } else if (eObj instanceof EnumerationReference) {
      return ((EnumerationReference) eObj).getReferencedProperty();
    } else if (eObj instanceof ComplexValueReference) {
      return ((ComplexValueReference) eObj).getReferencedProperty();
    } else if (eObj instanceof CollectionValueReference) {
      return ((CollectionValueReference) eObj).getReferencedProperty();
    }
    return null;
  }

  /**
   * Return if true dataValue is type reference
   * 
   * @param eObj
   * @return
   */
  public static boolean isDataValueReferenceType(Object eObj) {
    if (null != eObj) {
      if ((eObj instanceof BooleanReference) || (eObj instanceof NumericReference) || (eObj instanceof StringReference)
          || (eObj instanceof EnumerationReference) || (eObj instanceof ComplexValueReference)
          || (eObj instanceof CollectionValueReference)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return appropriate dataValues from map of EObject with EReferences
   * 
   * @param mapOfDataTypeWithFeatures
   * @return list of dataValue
   */
  public static List<DataValue> getDataValuesFromMapOfEObjectAndEReferences(
      Map<EObject, List<EReference>> mapOfDataTypeWithFeatures) {
    List<DataValue> result = new ArrayList<DataValue>(1);
    Set<EObject> keySet = mapOfDataTypeWithFeatures.keySet();
    if (null == keySet) {
      return result;
    }
    for (EObject dataType : keySet) {
      List<EReference> referencesList = mapOfDataTypeWithFeatures.get(dataType);
      for (EReference eReference : referencesList) {
        if (eReference.isMany()) {
          @SuppressWarnings("unchecked")
          List<Object> listObjects = (List<Object>) dataType.eGet(eReference);
          if (null != listObjects) {
            Iterator<Object> iterator = listObjects.iterator();
            while (iterator.hasNext()) {
              Object object = iterator.next();
              if (object instanceof DataValue) {
                result.add((DataValue) object);
              }
            }
          }
        } else {
          Object object = dataType.eGet(eReference);
          if ((null != object) && (object instanceof DataValue)) {
            result.add((DataValue) object);
          }
        }
      }
    }

    return result;
  }

  /**
   * Return true if dataValue is of kind (ownedDataValue)
   * 
   * @param dataValue
   * @return
   */
  public static String getContainementFeatureofDataValue(DataValue eObject) {
    if (null == eObject) {
      return ICommonConstants.EMPTY_STRING;
    }
    EReference eContainmentFeature = eObject.eContainmentFeature();
    if (null != eContainmentFeature) {
      return getReableFeatureName(eContainmentFeature);
    }

    return eObject.getName();
  }

  public static String getReableFeatureName(EStructuralFeature eStrFea) {
    String eStrFeaName = eStrFea.getName();
    String readableFeatureName = eStrFeaName.replaceAll("owned", ICommonConstants.EMPTY_STRING); //$NON-NLS-1$
    return readableFeatureName;
  }

  /**
   * Return true if dataValue is typed
   * 
   * @param ownedDefaultValue
   * @return
   */
  public static boolean isDataValueTyped(DataValue ownedDefaultValue) {
    if (null == ownedDefaultValue) {
      return false;
    }
    AbstractType type = ownedDefaultValue.getAbstractType();
    if (null == type) {
      return false;
    }
    return true;
  }
}
