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
package org.polarsys.capella.core.model.helpers.refmap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;


/**
 * This class represents the value used by a mapping entry
 */
public class VPair extends Pair<EClass[], EReference[]> {
  public VPair(EClass[] semanticLinks_p, EReference[] compositionReferences_p) {
    super(semanticLinks_p, compositionReferences_p);
  }
}
