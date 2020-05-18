/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

public class DataValueHelper {
  /**
   * The used logger
   */
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);

  private static DataValueHelper instance;

  private DataValueHelper() {
    // do nothing
  }

  public static DataValueHelper getInstance() {
    if (instance == null) {
    	instance = new DataValueHelper();
    }
    return instance;
  }

  public Object doSwitch(DataValue element, EStructuralFeature feature) {

    if (feature.equals(DatavaluePackage.Literals.DATA_VALUE__TYPE)) {
      return getType(element);
    }

    // no helper found... searching in super classes...
    return NamedElementHelper.getInstance().doSwitch(element, feature);
  }

  protected Type getType(DataValue element) {
    AbstractType absType = element.getAbstractType();
    if (absType instanceof Type) {
      return (Type) absType;
    }
    return null;
  }

  /**
   * Returns the value of the given numeric value.
   * @param value the numeric value.
   * @return the value of the given numeric value.
   */
  public static Object getValue(final NumericValue value) {
    return new ValueOfNumericValueSwitch().doSwitch(value);
  }

  /**
   * Allows to convert the given numeric value into an <code>int</code>
   * @param value the numeric value
   * @return the corresponding <code>int</code> or <code>-1</code> if it us impossible to convert
   */
  public static int getValueAsInt(final NumericValue value) {
    int foundInt;
    try {
      String string = (String) (getValue(value));
      foundInt = Integer.parseInt(string);
    } catch (ClassCastException exception) {
      __logger.warn(Messages.DataValueHelper_cannotConvertToIntTheNumericValue + value, exception);
      return -1;
    } catch (NumberFormatException exception) {
      // DO NOT LOG, this case is handled by the caller.
      return -1;
    }

    return foundInt;
  }
}
