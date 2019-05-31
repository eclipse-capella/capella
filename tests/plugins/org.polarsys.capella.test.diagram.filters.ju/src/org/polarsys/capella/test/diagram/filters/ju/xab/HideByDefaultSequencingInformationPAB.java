/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.xab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DefaultActivatedFilterTestCase;

public class HideByDefaultSequencingInformationPAB extends DefaultActivatedFilterTestCase {

  private final String PA__SEQUENCE_LINK_EXCHANGE_1 = "eaaaa6c6-8675-4414-9448-cad625f9be64";
  private final String PA__SEQUENCE_LINK_EXCHANGE_2 = "c18fd509-6beb-45bf-92ca-815acf3eb612";

  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getDiagramName() {
    return "[PAB] Sequencing Information Filter Test";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_FCD_HIDE_SEQUENCING_INFORMATION;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { PA__SEQUENCE_LINK_EXCHANGE_1, PA__SEQUENCE_LINK_EXCHANGE_2 });
  }
}
