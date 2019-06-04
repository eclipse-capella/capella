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

public class HideByDefaultSequencingInformationOAB extends DefaultActivatedFilterTestCase {

  private final String OA__SEQUENCE_LINK_INTERACTION_1_ID = "529b09a3-cdc2-455d-b4d1-7440e6ef09bd";
  private final String OA__SEQUENCE_LINK_INTERACTION_2_ID = "ea593610-e334-4e12-95c5-2d143742a81d";

  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getDiagramName() {
    return "[OAB] Sequencing Information Filter Test";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_FCD_HIDE_SEQUENCING_INFORMATION;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { OA__SEQUENCE_LINK_INTERACTION_1_ID, OA__SEQUENCE_LINK_INTERACTION_2_ID });
  }
}
