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
package org.polarsys.capella.common.queries;


/**
 */
public abstract class AbstractQuery implements IQuery {

  private String identifier;
  private String extendedQueryIdentifier;

  @Override
  public void setIdentifier(String identifier_p) {
    identifier = identifier_p;
  }

  @Override
  public String getIdentifier() {
    return identifier;
  }

  @Override
  public void setExtendedQueryIdentifier(String extendedQueryIdentifier_p) {
    extendedQueryIdentifier = extendedQueryIdentifier_p;
  }

  @Override
  public String getExtendedQueryIdentifier() {
    return extendedQueryIdentifier;
  }

}
