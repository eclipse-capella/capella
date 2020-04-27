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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

/**
 * Delete Physical Port
 */
public class DWF_DC_26_Resolver extends AbstractDeleteCommandResolver {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getElementToDelete(Object obj) {
    if (obj instanceof PhysicalComponent) {
      PhysicalComponent comp = (PhysicalComponent) obj;
      if (PhysicalComponentExt.isPhysicalComponentRoot(comp)) {
        return ComponentExt.getOwnedPhysicalPort(comp);
      }
    }
    return null;
  }

}
