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

import static org.polarsys.capella.core.model.helpers.FunctionAllocator.allocate;

import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

public class Rule_I_37_38_ComponentFunctionalAllocation extends Rule_I37_38{

  LogicalFunction lf1;
  LogicalComponent lc;

  LogicalFunction libraryRootFunction;
  LogicalFunction projectRootFunction;

  @Override
  public void setUp() throws Exception {
    super.setUp();

    executeCommand(() -> {

      projectRootFunction = (LogicalFunction) BlockArchitectureExt.getRootFunction(projectSkeleton.getLogicalArchitecture());
      libraryRootFunction = (LogicalFunction) BlockArchitectureExt.getRootFunction(librarySkeleton.getLogicalArchitecture());

      lf1 = LaFactory.eINSTANCE.createLogicalFunction();
      projectRootFunction.getOwnedFunctions().add(lf1);

      lc = LaFactory.eINSTANCE.createLogicalComponent();
      ((LogicalComponent)projectSkeleton.getLogicalArchitecture().getSystem()).getOwnedLogicalComponents().add(lc);

      allocate(lf1).on(lc);

    });

  }

  @SuppressWarnings("nls")
  @Override
  public void test() throws Exception {

      // can't move the function because a project component cannot allocate a function in a library
      expectRollback(() -> libraryRootFunction.getOwnedFunctions().add(lf1),
          "'[Logical Component]' cannot reference moved element '[Logical Function]' via 'allocatedFunctions'");

      // can move both functions + component
      // FIXME broken by https://bugs.polarsys.org/show_bug.cgi?id=1878
//      expectNoRollback(() -> {
//        libraryRootFunction.getOwnedFunctions().add(lf1);
//        librarySkeleton.getLogicalArchitecture().getOwnedLogicalComponent().getOwnedLogicalComponents().add(lc);
//      });

  }


}
