/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.semantic.queries.sirius.annotation;

import org.polarsys.capella.common.helpers.query.IQuery;

public abstract class DAnnotationQuery implements IQuery {

  protected final String source;
  
  public DAnnotationQuery(String source) {
    this.source = source;
  }

}
