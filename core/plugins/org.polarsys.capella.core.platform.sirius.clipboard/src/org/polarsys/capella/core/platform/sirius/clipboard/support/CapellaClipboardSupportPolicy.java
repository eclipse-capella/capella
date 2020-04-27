/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.clipboard.support;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.clipboard.core.IClipboardSupportPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

public class CapellaClipboardSupportPolicy implements IClipboardSupportPolicy {

  @Override
  public boolean provides(IAdaptable adaptable) {
    return adaptable.getAdapter(DSemanticDecorator.class) != null || adaptable.getAdapter(View.class) != null;
  }
}
