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

package org.polarsys.capella.common.queries.queryContext;

import java.util.Hashtable;
import java.util.List;

import org.polarsys.capella.common.queries.internal.IPrivateQueryContext;

/**
 */
public abstract class AbstractQueryContext implements IPrivateQueryContext {

  private int callingLevel = 0;
  private Hashtable<Integer, Hashtable<Object, List<Object>>> queryIdentifierToCacheTable = new Hashtable<Integer, Hashtable<Object, List<Object>>>();

  @Override
  public int getExecutionLevel() {
    return callingLevel;
  }

  @Override
  public List<Object> getResultFromCache(Integer queryId, Object semanticsObject) {
    Hashtable<Object, List<Object>> cacheTable = queryIdentifierToCacheTable.get(queryId);
    if (cacheTable == null) {
      return null;
    }
    return cacheTable.get(semanticsObject);
  }

  @Override
  public void addInCache(Integer queryId, Object semanticsObject, List<Object> res) {
    Hashtable<Object, List<Object>> cacheTable = queryIdentifierToCacheTable.get(queryId);
    if (cacheTable == null) {
      cacheTable = new Hashtable<Object, List<Object>>();
      queryIdentifierToCacheTable.put(queryId, cacheTable);
    }
    cacheTable.put(semanticsObject, res);
  }

  @Override
  public void resetCache() {
    queryIdentifierToCacheTable = new Hashtable<Integer, Hashtable<Object, List<Object>>>();
  }

  @Override
  public void incCallLevel() {
    callingLevel++;
  }

  @Override
  public void decCallLevel() {
    callingLevel--;
  }
}
