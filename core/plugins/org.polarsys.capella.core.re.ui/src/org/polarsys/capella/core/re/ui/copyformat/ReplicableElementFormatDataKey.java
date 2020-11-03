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
