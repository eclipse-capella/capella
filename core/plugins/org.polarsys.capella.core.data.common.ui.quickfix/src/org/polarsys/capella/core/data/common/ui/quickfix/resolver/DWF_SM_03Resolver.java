/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class DWF_SM_03Resolver extends AbstractCapellaMarkerResolution {

	class DWF_SM_03ResolverCommand extends AbstractReadWriteCommand {

		List<EObject> _tgts;

		public DWF_SM_03ResolverCommand(List<EObject> tgts) {
			_tgts = tgts;
		}

		public void run() {
			for (EObject eObject : _tgts) {
				if (eObject instanceof State) {
					State stateToCorrect = (State) eObject;
					List<IState> ReferencedStates = stateToCorrect.getReferencedStates();
					List<IState> involvedStates = new ArrayList<IState>();
					for (Region region : stateToCorrect.getOwnedRegions()) {
						for (IState iState : region.getInvolvedStates()) {
							involvedStates.add(iState);
						}
					}

					/* Leveling both lists to have the same content */
					Set<IState> lst = new HashSet<IState>();
					lst.addAll(involvedStates);
					lst.addAll(ReferencedStates);

					for (IState iState : lst) {
						if (!involvedStates.contains(iState))
							stateToCorrect.getOwnedRegions().get(0).getInvolvedStates().add((AbstractState) iState);
						if (!ReferencedStates.contains(iState))
							stateToCorrect.getReferencedStates().add(iState);
					}
				}
			}
		}

	}

	public void run(IMarker marker) {
		List<EObject> tgts = getModelElements(marker);
		
		DWF_SM_03ResolverCommand cmd = new DWF_SM_03ResolverCommand(tgts);
		ExecutionManager em = TransactionHelper.getExecutionManager(tgts);
		em.execute(cmd);
		try {
			marker.delete();
		} catch (CoreException e) {
			//Catch exception silently,
			e.printStackTrace();
		}

	}

}
