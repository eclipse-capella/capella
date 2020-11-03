/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.msm;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.LabelFilterTestCase;

public class ShowTriggerSourceFunctionForMSM extends LabelFilterTestCase {

  @Override
  protected String getTestProjectName() {
    return "ShowTriggerSourceFunctionFilter"; //$NON-NLS-1$
  }

  @Override
  protected String getDiagramName() {
    return "[MSM] Default Region"; //$NON-NLS-1$
  }

  @Override
  protected String getFilterName() {
    return IMappingNameConstants.SHOW_TRIGGER_SOURCE_FUNCTION;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { "77a5784e-0929-493a-a84d-057cebabbac7" //$NON-NLS-1$
    });
  }

  @Override
  protected List<String> getExpectedElementLabels() {
    return Arrays.asList(new String[] { ";[SystemFunction 1 ->] FunctionalExchange 1;" });
  }
}
