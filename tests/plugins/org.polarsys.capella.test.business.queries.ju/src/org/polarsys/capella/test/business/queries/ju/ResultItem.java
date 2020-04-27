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
package org.polarsys.capella.test.business.queries.ju;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;

/**
 * @author Erwan Brottier
 */
public class ResultItem {

  public enum Kind {
    OK, MISSING, ADDED
  } 
  
  protected QueryResult query;
  public Kind kind;
  protected EObject result;
  protected String resultId;
  
  public QueryResult getQuery() {
    return query;
  }

  public Kind getKind() {
    return kind;
  }

  public EObject getResult() {
    return result;
  }

  public String getResultId() {
    return resultId;
  }

  public static final int OK = 0;
  public static final int MISSING = 1;
  public static final int ADDED = 2;
  
  protected ResultItem(QueryResult query, EObject result, String resultId, Kind kind) {
    this.result = result;
    this.query = query;
    this.kind = kind;
    this.resultId=resultId;
  }

}
