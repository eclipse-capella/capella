/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.orb;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForORB extends DiagramObjectFilterTestCase {

  protected final String ALLOCATED_INTERACTION_ID = "3e7ee01c-9a6d-4f79-827d-7fb2cd14ea64";
  protected final String INTERACTION_ID = "9a065225-bfae-4c36-97cc-82a9b5326663";
  protected final String PROPERTY_VALUE_ID = "67e5ecbe-cfd8-49f5-8947-83d6b5fde730";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[ORB] Operational Role Blank";
  }

}
