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
package org.polarsys.capella.core.libraries.utils;

import java.util.Comparator;

import org.polarsys.capella.common.libraries.IModel;

/**
 */
public class AbstractModelComparator implements Comparator<IModel> {

  @Override
  public int compare(IModel m1, IModel m2) {
    return m1.getIdentifier().getName().compareTo(m2.getIdentifier().getName());
  }

}
