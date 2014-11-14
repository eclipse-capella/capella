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


/**
 * This class represents the key used to identify a mapping
 */
public class KPair extends Pair<EClass, EClass> {
  public KPair(EClass linkSrc_p, EClass linkTgt_p) {
    super(linkSrc_p, linkTgt_p);
  }
}
