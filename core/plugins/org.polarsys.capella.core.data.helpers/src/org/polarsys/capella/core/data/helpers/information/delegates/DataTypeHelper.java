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

	public Object doSwitch(DataType element_p, EStructuralFeature feature_p) {
		Object ret = null;

    if (feature_p.equals(DatatypePackage.Literals.DATA_TYPE__REALIZED_DATA_TYPES)) {
      ret = getRealizedDataTypes(element_p);
    } else if (feature_p.equals(DatatypePackage.Literals.DATA_TYPE__REALIZING_DATA_TYPES)) {
      ret = getRealizingDataTypes(element_p);
    } else if (feature_p.equals(DatatypePackage.Literals.DATA_TYPE__DEFAULT_VALUE)) {
			ret = getDefaultValue(element_p);
		} else if (feature_p.equals(DatatypePackage.Literals.DATA_TYPE__NULL_VALUE)) {
			ret = getNullValue(element_p);
		}

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = GeneralizableElementHelper.getInstance().doSwitch(element_p, feature_p);
		}		

		return ret;
	}	

  /**
   *
   */
  protected List<DataType> getRealizedDataTypes(DataType element_p) {
    List <DataType> ret = new ArrayList <DataType>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
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
  protected List<DataType> getRealizingDataTypes(DataType element_p) {
    List <DataType> ret = new ArrayList <DataType>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof InformationRealization) {
        TraceableElement elt = trace.getSourceElement();
        if (elt instanceof DataType) {
          ret.add((DataType) elt);
        }
      }
    }
    return ret;
  }

	protected DataValue getDefaultValue(DataType element_p) {
		DataValue ret = null;

		if (element_p instanceof BooleanType) {
      ret = ((BooleanType) element_p).getOwnedDefaultValue();
    }
    else if (element_p instanceof StringType) {
      ret = ((StringType) element_p).getOwnedDefaultValue();
    }
    else if (element_p instanceof Enumeration) {
			ret = ((Enumeration) element_p).getOwnedDefaultValue();
		}
		else if (element_p instanceof NumericType) {
			ret = ((NumericType) element_p).getOwnedDefaultValue();
		}
		else if (element_p instanceof PhysicalQuantity) {
			ret = ((PhysicalQuantity) element_p).getOwnedDefaultValue();
		}

		return ret;
	}

	protected DataValue getNullValue(DataType element_p) {
		DataValue ret = null;

		if (element_p instanceof StringType) {
			ret = ((StringType) element_p).getOwnedNullValue();
		}
		else if (element_p instanceof Enumeration) {
			ret = ((Enumeration) element_p).getOwnedNullValue();
		}
		else if (element_p instanceof NumericType) {
			ret = ((NumericType) element_p).getOwnedNullValue();
		}
		else if (element_p instanceof PhysicalQuantity) {
			ret = ((PhysicalQuantity) element_p).getOwnedNullValue();
		}

		return ret;
	}
}
