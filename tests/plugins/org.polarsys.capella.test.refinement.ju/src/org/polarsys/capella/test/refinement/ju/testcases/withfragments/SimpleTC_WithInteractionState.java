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
package org.polarsys.capella.test.refinement.ju.testcases.withfragments;

import java.util.Collections;

public class SimpleTC_WithInteractionState extends AbstractRefinementWithFragmentsTest {

  @SuppressWarnings("unchecked")
  public SimpleTC_WithInteractionState() {
    super(Messages.SimpleTC_WithInteractionState_Name,
        Messages.SimpleTC_WithInteractionState_Desc,
        Collections.singletonList(Messages.SimpleTC_LogicalArchitecture),
        Collections.singletonList(Messages.SimpleTC_LogicalArchitecture),
        Messages.SimpleTC_WithInteractionState_Src,
        Messages.SimpleTC_WithInteractionState_Ref,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
