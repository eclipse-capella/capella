/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.queries.exceptions;

public class ContextShallNotBeNullException extends QueryException {

  public ContextShallNotBeNullException() {
    super("The context shall not be null (try to call QueryInterpretor.executeQuery with context=null).");
  }
}
