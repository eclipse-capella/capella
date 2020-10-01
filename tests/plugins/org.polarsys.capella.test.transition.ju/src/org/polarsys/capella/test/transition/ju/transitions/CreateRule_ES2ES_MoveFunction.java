/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Ensure that message from a functional exchange with a moved bound to a unrelated component in target architecture is
 * still propagated even if the component is not related to the original instance role
 */
public class CreateRule_ES2ES_MoveFunction extends TopDownTransitionTestCase {

  public static final String ES_CAPABILITY_1 = "ab4b3647-2d23-487d-8ba5-523c42e36f06"; //$NON-NLS-1$
  public static final String MSG_FUNCTIONALEXCHANGE_1 = "f126a854-ecaf-4de3-befc-ef9f2eb2e169"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("ES2ES_MoveFunction");
  }

  @Override
  public void performTest() throws Exception {
    performEStoESTransition(Arrays.asList(getObject(ES_CAPABILITY_1)));
    SequenceMessage msg = mustBeTransitioned(MSG_FUNCTIONALEXCHANGE_1);
    assertNotNull(msg.getInvokedOperation());
  }

}
