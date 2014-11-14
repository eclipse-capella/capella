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
