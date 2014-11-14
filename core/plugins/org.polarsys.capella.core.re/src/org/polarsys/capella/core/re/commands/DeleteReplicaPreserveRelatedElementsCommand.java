/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.commands;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.transition.common.commands.TransitionCommand;
import org.polarsys.capella.core.transition.system.helpers.SemanticHelper;

/**
 *  
 *
 */
public class DeleteReplicaPreserveRelatedElementsCommand extends TransitionCommand {

	/**
	 * @param selection_p
	 * @param progressMonitor_p
	 */
	public DeleteReplicaPreserveRelatedElementsCommand(
			Collection<Object> selection_p, IProgressMonitor progressMonitor_p) {
		super(selection_p, progressMonitor_p);
	}

	@Override
	  public String getName() {
	    return getClass().getName();
	  }

	  
	protected void performTransformation(Collection<Object> elements_p) {
	   
			HashSet<CatalogElement> elements = new HashSet<CatalogElement>();
			for (Object selected : SemanticHelper.getSemanticObjects(elements_p)) {
				if (selected instanceof EObject) {
					if (selected instanceof CatalogElement && ((CatalogElement) selected).getKind()!=CatalogElementKind.REC) {
						elements.add((CatalogElement) selected);
					} else {
						elements.addAll(ReplicableElementExt.getReferencingReplicas((EObject) selected));
					}
				}
			}
			CapellaDeleteCommand delete = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(elements), elements, true, !isHeadless(), true);
			delete.execute();
		}
	protected boolean isHeadless() {
		return true;
	}
}
