/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.queries;

import java.util.ArrayList;
import java.util.List;

/**
 */
public abstract class AbstractQuery implements IQuery {

  private String identifier;
  private String extendedQueryIdentifier;
  private List<String> extendingQueryIdentifiers;

  @Override
  public void setIdentifier(String identifier) {
    this.identifier = identifier;
    extendingQueryIdentifiers = new ArrayList<String>();
  }

  @Override
  public String getIdentifier() {
    return identifier;
  }

  @Override
  public void setExtendedQueryIdentifier(String extendedQueryIdentifier) {
    this.extendedQueryIdentifier = extendedQueryIdentifier;
  }

  @Override
  public String getExtendedQueryIdentifier() {
    return extendedQueryIdentifier;
  }

  public void addExtendingQueryIdentifier(String extendingQueryIdentifier) {
    this.extendingQueryIdentifiers.add(extendingQueryIdentifier);
  }

  @Override
  public List<String> getExtendingQueryIdentifiers() {
    return extendingQueryIdentifiers;
  }

}
