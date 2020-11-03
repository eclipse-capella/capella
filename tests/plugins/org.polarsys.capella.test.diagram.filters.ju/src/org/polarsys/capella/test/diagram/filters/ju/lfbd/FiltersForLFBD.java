/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.lfbd;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForLFBD extends DiagramObjectFilterTestCase {

  protected final String CONTROL_NODE_1_ID = "1ea6766a-7499-4e68-8a0a-96521e39e405";
  protected final String CONTROL_NODE_2_ID = "ff1420b4-d5c1-4d09-9553-d53f1d6ca8ee";
  protected final String PROPERTY_VALUES_ID = "7d725b80-73fe-4fcf-b151-3d9b6a58380d";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[LFBD] Root Logical Function";
  }

}
