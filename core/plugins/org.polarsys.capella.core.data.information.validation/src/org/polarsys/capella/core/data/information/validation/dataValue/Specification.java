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
package org.polarsys.capella.core.data.information.validation.dataValue;

/**
 */
public interface Specification<T> {
  public boolean isSatisfiedBy(T candidate);

  public Specification<T> or(Specification<T> specification);

  public Specification<T> and(Specification<T> specification);

  public Specification<T> not();
}
