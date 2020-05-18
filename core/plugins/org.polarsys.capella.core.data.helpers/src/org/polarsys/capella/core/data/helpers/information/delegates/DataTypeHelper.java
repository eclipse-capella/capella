/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.GeneralizableElementHelper;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class DataTypeHelper {
	private static DataTypeHelper instance;

	private DataTypeHelper() {
    // do nothing
	}

	public static DataTypeHelper getInstance(){
		if(instance == null)
			instance = new DataTypeHelper();
		return instance;
	}

	public Object doSwitch(DataType element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(DatatypePackage.Literals.DATA_TYPE__REALIZED_DATA_TYPES)) {
      ret = getRealizedDataTypes(element);
    } else if (feature.equals(DatatypePackage.Literals.DATA_TYPE__REALIZING_DATA_TYPES)) {
      ret = getRealizingDataTypes(element);
    } else if (feature.equals(DatatypePackage.Literals.DATA_TYPE__DEFAULT_VALUE)) {
			ret = getDefaultValue(element);
		} else if (feature.equals(DatatypePackage.Literals.DATA_TYPE__NULL_VALUE)) {
			ret = getNullValue(element);
		}

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = GeneralizableElementHelper.getInstance().doSwitch(element, feature);
		}		

		return ret;
	}	

  /**
   *
   */
  protected List<DataType> getRealizedDataTypes(DataType element) {
    List <DataType> ret = new ArrayList <>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof InformationRealization) {
        TraceableElement elt = trace.getTargetElement();
        if (elt instanceof DataType) {
          ret.add((DataType) elt);
        }
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<DataType> getRealizingDataTypes(DataType element) {
    List <DataType> ret = new ArrayList <>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof InformationRealization) {
        TraceableElement elt = trace.getSourceElement();
        if (elt instanceof DataType) {
          ret.add((DataType) elt);
        }
      }
    }
    return ret;
  }

	protected DataValue getDefaultValue(DataType element) {
		DataValue ret = null;

		if (element instanceof BooleanType) {
      ret = ((BooleanType) element).getOwnedDefaultValue();
    }
    else if (element instanceof StringType) {
      ret = ((StringType) element).getOwnedDefaultValue();
    }
    else if (element instanceof Enumeration) {
			ret = ((Enumeration) element).getOwnedDefaultValue();
		}
		else if (element instanceof NumericType) {
			ret = ((NumericType) element).getOwnedDefaultValue();
		}
		else if (element instanceof PhysicalQuantity) {
			ret = ((PhysicalQuantity) element).getOwnedDefaultValue();
		}

		return ret;
	}

	protected DataValue getNullValue(DataType element) {
		DataValue ret = null;

		if (element instanceof StringType) {
			ret = ((StringType) element).getOwnedNullValue();
		}
		else if (element instanceof Enumeration) {
			ret = ((Enumeration) element).getOwnedNullValue();
		}
		else if (element instanceof NumericType) {
			ret = ((NumericType) element).getOwnedNullValue();
		}
		else if (element instanceof PhysicalQuantity) {
			ret = ((PhysicalQuantity) element).getOwnedNullValue();
		}

		return ret;
	}
}
