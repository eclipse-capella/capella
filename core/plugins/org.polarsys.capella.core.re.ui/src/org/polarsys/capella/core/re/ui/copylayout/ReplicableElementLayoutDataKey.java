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
package org.polarsys.capella.core.re.ui.copylayout;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.sirius.ui.copylayout.AbstractCapellaLayoutDataKey;
import org.polarsys.capella.core.sirius.ui.copylayout.CapellaDecoratorLayoutDataKey;

/**
 *
 */
public class ReplicableElementLayoutDataKey extends CapellaDecoratorLayoutDataKey {

  public ReplicableElementLayoutDataKey(AbstractCapellaLayoutDataKey key_p, EObject semantic_p) {
    super(key_p);
    _semantic = semantic_p;
  }

  @Override
  public EObject getSemantic() {
    return _semantic;
  }

}
