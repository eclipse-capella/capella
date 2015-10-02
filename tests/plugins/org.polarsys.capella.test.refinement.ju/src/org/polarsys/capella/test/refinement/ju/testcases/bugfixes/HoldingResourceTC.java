/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.refinement.ju.testcases.bugfixes;

import java.util.Collections;

public class HoldingResourceTC extends AbstractHoldingResourceTest {

  @SuppressWarnings("unchecked")
  public HoldingResourceTC() {
    super(HoldingResourceTestMessages.HoldingResourceTC_Name,
        HoldingResourceTestMessages.HoldingResourceTC_Desc,
        Collections.singletonList(HoldingResourceTestMessages.HoldingResource_LogicalArchitecture),
        Collections.singletonList(HoldingResourceTestMessages.HoldingResource_LogicalArchitecture),
        HoldingResourceTestMessages.HoldingResourceTC_ToRefine, "", Collections.EMPTY_LIST, Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
