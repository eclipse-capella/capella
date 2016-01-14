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
package org.polarsys.capella.test.refinement.ju.testcases.simple;

import java.util.Collections;

public class Simple_Communication_Mechanisms_TC2a extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public Simple_Communication_Mechanisms_TC2a() {
    super(Messages.Simple_Communication_Mechanisms_TC2a_Name,
        Messages.Simple_Communication_Mechanisms_TC2a_Desc,
        Collections.singletonList(Messages.Simple_Communication_Mechanisms_TC_LogicalArchitecture),
        Collections.singletonList(Messages.Simple_Communication_Mechanisms_TC_LogicalArchitecture),
        Messages.Simple_Communication_Mechanisms_TC2a_Src,
        Messages.Simple_Communication_Mechanisms_TC2a_Ref,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
