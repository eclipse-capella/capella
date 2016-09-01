/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.ui.copyformat;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.sirius.ui.copyformat.AbstractCapellaFormatDataKey;
import org.polarsys.capella.core.sirius.ui.copyformat.CapellaDecoratorFormatDataKey;

/**
 *
 */
public class ReplicableElementFormatDataKey extends CapellaDecoratorFormatDataKey {

  public ReplicableElementFormatDataKey(AbstractCapellaFormatDataKey key, EObject semantic) {
    super(key);
    _semantic = semantic;
  }

  @Override
  public EObject getSemantic() {
    return _semantic;
  }

}
