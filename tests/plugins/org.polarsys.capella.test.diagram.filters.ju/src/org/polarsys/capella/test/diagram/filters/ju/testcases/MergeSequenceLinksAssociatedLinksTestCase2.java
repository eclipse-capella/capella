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
package org.polarsys.capella.test.diagram.filters.ju.testcases;

import java.util.Arrays;
import java.util.List;

public class MergeSequenceLinksAssociatedLinksTestCase2 extends MergeSequenceLinksFilterTests {
  @Override
  protected String getDiagramName() {
    return "[LFCD] FunctionalChain 2";
  }

  @Override
  protected List<String> getFilteredObjetIDs() {  
    return Arrays.asList(new String [] {
        LA_FUNCTIONAL_EXCHANGE_1,
        LA_FUNCTIONAL_EXCHANGE_1_2,
        LA_SEQUENCE_LINK_1,
        LA_SEQUENCE_LINK_2
    });
  }
}
