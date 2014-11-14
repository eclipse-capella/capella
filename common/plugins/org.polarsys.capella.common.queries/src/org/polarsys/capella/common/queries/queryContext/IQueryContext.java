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
package org.polarsys.capella.common.queries.queryContext;

import org.polarsys.capella.common.queries.exceptions.EntryAlreadyExistInContextException;

/**
 */
public interface IQueryContext {

  public boolean hasValue(String key);

  public void putValue(String key, Object value) throws EntryAlreadyExistInContextException;

  public Object getValue(String key);

  public void overwriteValue(String key, Object value);

  public void resetCache();
}
