/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers.queries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.DataTypeExt;

/**
 */
public class GetDataValues extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    List<DataPkg> listPackages = DataPkgExt.getAllDataPkgs((EObject) input);
    List<Object> result = new ArrayList<Object>(1);
    Iterator<DataPkg> itDataPkg = listPackages.iterator();
    while (itDataPkg.hasNext()) {
      DataPkg dataPkg = itDataPkg.next();
      // get all owned dataValues
      Iterator<DataValue> itDataValue = dataPkg.getOwnedDataValues().iterator();
      while (itDataValue.hasNext()) {
        // add to result
        result.add(itDataValue.next());
      }
      // get all owned dataTypes
      Iterator<DataType> itDataTypes = dataPkg.getOwnedDataTypes().iterator();
      while (itDataTypes.hasNext()) {
        DataType dataType = itDataTypes.next();
        // get all owned DataValues from DataType and add to result
        result.addAll(DataTypeExt.getAllDataValuesFromDataType(dataType));
      }
    }
    return result;
  }

}
