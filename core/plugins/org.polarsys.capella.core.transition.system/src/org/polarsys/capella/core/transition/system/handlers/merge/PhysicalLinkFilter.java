/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class PhysicalLinkFilter extends EObjectCategoryFilter {

  public PhysicalLinkFilter(IContext context) {
    super(context, CsPackage.Literals.PHYSICAL_LINK);
  }

  @Override
  public boolean keepElement(Object element) {
    return element instanceof PhysicalLink || element instanceof PhysicalLinkCategory;
  }

}
