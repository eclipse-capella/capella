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
package org.polarsys.capella.core.explorer.activity.ui.pages;

import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.sirius.analysis.IViewpointNameConstants;

public class PhysicalArchitecturePage extends AbstractCapellaPage {

  public static final String PAGE_ID = "org.polarsys.capella.core.explorer.activity.ui.page.physical.architecture";

  @Override
  protected String getHeaderTitle() {
    return NamingConstants.CreatePhysicalArchCmd_name;
  }

  @Override
  public EClass getFilteringMetaClassForCommonViewpoint() {
    return PaPackage.Literals.PHYSICAL_ARCHITECTURE;
  }

  @Override
  public Set<String> getHandledViewpoint() {
    if (!handledViewpoint.contains(IViewpointNameConstants.PHYSICAL_ARCHITECTURE_VIEWPOINT_NAME)) {
      handledViewpoint.add(IViewpointNameConstants.PHYSICAL_ARCHITECTURE_VIEWPOINT_NAME);
    }
    return handledViewpoint;
  }
}
