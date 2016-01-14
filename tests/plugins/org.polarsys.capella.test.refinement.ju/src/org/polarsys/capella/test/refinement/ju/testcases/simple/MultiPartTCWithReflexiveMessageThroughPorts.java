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

public class MultiPartTCWithReflexiveMessageThroughPorts extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public MultiPartTCWithReflexiveMessageThroughPorts() {
    super(Messages.MultiPartTCWithReflexiveMessageThroughPorts_Name,
        Messages.MultiPartTCWithReflexiveMessageThroughPorts_Desc,
        Collections.singletonList(Messages.MultiPartTC_LogicalArchitecture),
        Collections.singletonList(Messages.MultiPartTC_LogicalArchitecture),
        Messages.MultiPartTCWithReflexiveMessageThroughPorts_Src,
        Messages.MultiPartTCWithReflexiveMessageThroughPorts_Ref,
        Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC5b_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part, Messages.MultiPartTC_LogicalSystem_LC5a_Part,
            Messages.MultiPartTC_LogicalSystem_LC5b_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part, Messages.MultiPartTC_LogicalSystem_LC5a_Part,
            Messages.MultiPartTC_LogicalSystem_LC5b_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part, Messages.MultiPartTC_LogicalSystem_LC5a_Part,
            Messages.MultiPartTC_LogicalSystem_LC5b_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part, Messages.MultiPartTC_LogicalSystem_LC5a_Part),
        Arrays.asList(Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC5a_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC5a_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC5a_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC5a_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC5a_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC5a_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC5a_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC5a_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC5a_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC5a_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC5a_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC5a_Part, Messages.MultiPartTC_LogicalSystem_LC5b_Part)),
        Collections.EMPTY_LIST);
  }
}
