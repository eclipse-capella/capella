/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.transition.system.preferences.PreferenceConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class DataFilter extends EObjectCategoryFilter {

  public DataFilter(IContext context) {
    super(context, InformationPackage.Literals.CLASS, PreferenceConstants.P_Data_TEXT);
  }

  @Override
  public boolean keepElement(Object element) {
    return element instanceof DataValue || element instanceof DataType || element instanceof DataPkg
        || element instanceof org.polarsys.capella.core.data.information.Class
        || element instanceof org.polarsys.capella.core.data.information.Association;
  }

}
