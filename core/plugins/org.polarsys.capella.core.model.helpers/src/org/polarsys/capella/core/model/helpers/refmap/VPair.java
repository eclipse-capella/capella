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
import org.eclipse.emf.ecore.EReference;


/**
 * This class represents the value used by a mapping entry
 */
public class VPair extends Pair<EClass[], EReference[]> {
  public VPair(EClass[] semanticLinks, EReference[] compositionReferences) {
    super(semanticLinks, compositionReferences);
  }
}
