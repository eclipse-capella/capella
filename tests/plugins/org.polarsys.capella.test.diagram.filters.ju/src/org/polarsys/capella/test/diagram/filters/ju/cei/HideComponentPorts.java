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
package org.polarsys.capella.test.diagram.filters.ju.cei;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class HideComponentPorts extends DiagramObjectFilterTestCase {

  private final String IN_COMPONENT_PORT_ID = "f285a64f-a917-4bc5-9e03-4749f2303f96";
  private final String OUT_COMPONENT_PORT_ID = "07b6952d-152d-4cd4-8c6a-cae3db3a6d29";
  private final String STANDARD_COMPONENT_PORT_ID = "ad590566-fb37-46a8-9f7b-3c57353a9fb7";
  private final String IN_OUT_COMPONENT_PORT_ID = "e7c3f00c-9761-4145-99a3-fc764fdcc500";

  private final String PORT_1 = "a12d10e7-469c-4001-a0c6-4e652401259d";
  private final String PORT_2 = "f9db1445-3e2d-42c3-bcc5-29baef84a03e";
  private final String PORT_3 = "1a066a3b-2893-47d9-acdb-2a0e2bb6c686";
  private final String PORT_4 = "ad590566-fb37-46a8-9f7b-3c57353a9fb7";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel"; //$NON-NLS-1$
  }

  @Override
  protected String getDiagramName() {
    return "Contextual Component External Interfaces Test Diagram"; //$NON-NLS-1$
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_CEI_HIDE_COMPONENT_PORTS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(IN_COMPONENT_PORT_ID, OUT_COMPONENT_PORT_ID, STANDARD_COMPONENT_PORT_ID,
        IN_OUT_COMPONENT_PORT_ID, PORT_1, PORT_2, PORT_3, PORT_4);
  }
}
