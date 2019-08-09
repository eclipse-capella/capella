/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
