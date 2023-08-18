/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;

public class Rule_I_37_38_FunctionalExchange extends Rule_I37_38{

  LogicalFunction lf1;
  LogicalFunction lf2;
  FunctionalExchange fe;

  LogicalFunction libraryRootFunction;
  LogicalFunction projectRootFunction;

  @Override
  public void setUp() throws Exception {
    super.setUp();

    executeCommand(() -> {
      projectRootFunction = (LogicalFunction) BlockArchitectureExt.getRootFunction(projectSkeleton.getLogicalArchitecture());
      lf1 = LaFactory.eINSTANCE.createLogicalFunction();
      lf2 = LaFactory.eINSTANCE.createLogicalFunction();
      projectRootFunction.getOwnedFunctions().add(lf1);
      projectRootFunction.getOwnedFunctions().add(lf2);
      fe = FunctionalExchangeExt.createFunctionalExchange(lf1, lf2);

      libraryRootFunction = (LogicalFunction) BlockArchitectureExt.getRootFunction(librarySkeleton.getLogicalArchitecture());
    });

  }

  @SuppressWarnings("nls")
  @Override
  public void test() throws Exception {

      // can't move just one of the functions
      expectRollback(() -> libraryRootFunction.getOwnedFunctions().add(lf1), "'FunctionalExchange 1' cannot reference moved element 'FOP 1' via 'source'");
      expectRollback(() -> libraryRootFunction.getOwnedFunctions().add(lf2), "'FunctionalExchange 1' cannot reference moved element 'FIP 1' via 'target'");

      // can't move one function + exchange
      expectRollback(() -> {
        libraryRootFunction.getOwnedFunctions().add(lf1);
        libraryRootFunction.getOwnedFunctionalExchanges().add(fe);
      }, "'FunctionalExchange 1' cannot reference moved element 'FIP 1' via 'target'");

      // can't move one function + exchange
      expectRollback(() -> {
        libraryRootFunction.getOwnedFunctions().add(lf2);
        libraryRootFunction.getOwnedFunctionalExchanges().add(fe);
      }, "'FunctionalExchange 1' cannot reference moved element 'FOP 1' via 'source'");

      // can move both functions + exchange
      expectNoRollback(() -> {
        libraryRootFunction.getOwnedFunctions().add(lf2);
        libraryRootFunction.getOwnedFunctions().add(lf1);
        libraryRootFunction.getOwnedFunctionalExchanges().add(fe);
      });

  }


}
