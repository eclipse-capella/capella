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

import java.util.Arrays;
import java.util.Collections;

public class MultiPartTCWithoutAmbiguity extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public MultiPartTCWithoutAmbiguity() {
    super(Messages.MultiPartTCWithoutAmbiguity_Name,
        Messages.MultiPartTCWithoutAmbiguity_Desc,
        Collections.singletonList(Messages.MultiPartTC_LogicalArchitecture),
        Collections.singletonList(Messages.MultiPartTC_LogicalArchitecture),
        Messages.MultiPartTCWithoutAmbiguity_Src,
        Messages.MultiPartTCWithoutAmbiguity_Ref,
        Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC1a_Part, Messages.MultiPartTC_LogicalSystem_LC1a_Part, Messages.MultiPartTC_LogicalSystem_LC1b_Part, Messages.MultiPartTC_LogicalSystem_LC1b_Part),
        Arrays.asList(Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC1a_Part, Messages.MultiPartTC_LogicalSystem_LC1b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC1a_Part, Messages.MultiPartTC_LogicalSystem_LC1b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC1a_Part, Messages.MultiPartTC_LogicalSystem_LC1b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC1a_Part, Messages.MultiPartTC_LogicalSystem_LC1b_Part)),
        Collections.EMPTY_LIST);
  }
}
