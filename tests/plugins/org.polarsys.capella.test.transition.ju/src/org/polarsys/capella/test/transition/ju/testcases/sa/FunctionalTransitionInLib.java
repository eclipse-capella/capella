/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.transition.ju.testcases.sa;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Transition Test case in a Library.
 * 
 */
public class FunctionalTransitionInLib extends TopDownTransitionTestCase {

  private String WrongRealization = "Wrong Realization";
  private String ErrorMessage = "Error Message";

  private SystemFunction sf1;
  private SystemFunction sf2;
  private LogicalFunction rootLF;
  private LogicalFunction lf1;
  private LogicalFunction lf2;

  private String sf1_Id = "085a534c-236b-4566-9dad-2620daef212a";
  private String sf2_Id = "86df1fda-795e-4b32-966a-abb74a09b472";
  private String rootLF_Id = "65734a29-4691-42ba-b43e-56e0e504245f";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Library");
  }

  public void performTest() throws Exception {
    // Assign the objects
    sf1 = (SystemFunction) getObject(sf1_Id);
    sf2 = (SystemFunction) getObject(sf2_Id);
    rootLF = (LogicalFunction) getObject(rootLF_Id);

    doTest();
  }

  /**
   * Run the transition test "Functional Transition" from SF2
   * 
   * <pre>
   * Expected Result:\
   *             1. SF2 logical function is added in root logical function of Logical Architecture with realization link to SF2.\
   *             2. The size of created logical functions is 1
   * </pre>
   */

  public void doTest() throws Exception {
    performFunctionalTransition(Collections.singletonList(sf2));

    // Verify the size of functions created after transition
    assertEquals(1, rootLF.getOwnedFunctions().size());

    lf2 = (LogicalFunction) getAllocatingFunction(sf2);
    assertNotNull("Element shall not be null", lf2);
    FunctionRealization realization = lf2.getOutFunctionRealizations().get(0);
    assertTrue(NLS.bind(WrongRealization, lf2.getName(), sf2.getName()), realization.getAllocatedFunction() == sf2);
    FunctionKind actualKindLF2 = lf2.getKind();
    FunctionKind expectedKindLF2 = sf2.getKind();
    assertTrue(MessageFormat.format(ErrorMessage, lf2.getName(), actualKindLF2.getName(), expectedKindLF2.getName()),
        actualKindLF2.equals(expectedKindLF2));

    lf1 = (LogicalFunction) getAllocatingFunction(sf1);
    assertNull(lf1);
  }

  private AbstractFunction getAllocatingFunction(AbstractFunction function) {
    List<FunctionRealization> allocatingFunctions = function.getInFunctionRealizations();
    final int size = allocatingFunctions.size();
    if (size > 0) {
      return allocatingFunctions.get(size - 1).getAllocatingFunction();
    }
    return null;
  }
}
