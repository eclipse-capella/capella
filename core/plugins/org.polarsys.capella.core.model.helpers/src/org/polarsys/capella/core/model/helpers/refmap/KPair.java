/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers.refmap;

import org.eclipse.emf.ecore.EClass;


/**
 * This class represents the key used to identify a mapping
 */
public class KPair extends Pair<EClass, EClass> {
  public KPair(EClass linkSrc, EClass linkTgt) {
    super(linkSrc, linkTgt);
  }
}
