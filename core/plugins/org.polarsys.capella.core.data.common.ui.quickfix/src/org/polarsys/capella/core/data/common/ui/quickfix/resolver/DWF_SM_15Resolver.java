/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.common.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.sirius.analysis.StateMachineServices;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

public class DWF_SM_15Resolver extends AbstractDeleteCommandResolver {

  @Override
  public Object getElementToDelete(final Object obj) {
    if (obj != null && obj instanceof State) {
      List<IState> mixReferencedStates = new ArrayList<IState>();
      for (IState s : ((State) obj).getReferencedStates()) {
        if (StateMachineServices.isReferencedState(s, (State) obj) && s.eClass() != ((State) obj).eClass())
          mixReferencedStates.add(s);
      }

      for (final IState state : mixReferencedStates) {
        AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {
          @Override
          public void run() {
            ((State) obj).getReferencedStates().remove(state);
            ((State) obj).getOwnedRegions().get(0).getInvolvedStates().remove(state);
          }
        };
        TransactionHelper.getExecutionManager((EObject) obj).execute(abstrctCommand);

      }
    }
    return null;
  }
}
