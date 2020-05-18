/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.commands;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.transition.common.commands.DefaultCommand;

public class DeleteReplicaAndRelatedElementsCommand extends DefaultCommand {

	/**
	 * @param selection
	 * @param progressMonitor
	 */
	public DeleteReplicaAndRelatedElementsCommand(
			Collection<?> selection, IProgressMonitor progressMonitor) {
		super(selection, progressMonitor);
	}

	@Override
	  public String getName() {
	    return getClass().getName();
	  }
	
	protected void performTransformation(Collection<?> source) {
		
	  Collection<EObject> semanticElements = CapellaAdapterHelper.resolveSemanticObjects(source);
			HashSet<CatalogElement> catalogElements = new HashSet<>();
      for (EObject selected : semanticElements) {
				if (selected instanceof CatalogElement && ((CatalogElement) selected).getKind()!=CatalogElementKind.REC) {
					catalogElements.add((CatalogElement) selected);
				} else {
					catalogElements.addAll(ReplicableElementExt.getReferencingReplicas((EObject) selected));
				}
			}
      
      HashSet<EObject> elements_objects = new HashSet<>();
			for (CatalogElement selected : catalogElements) {
				elements_objects.addAll(catalogElements);
				/* For each elements selected, search for its content */
				for(CatalogElementLink elementLink : selected.getOwnedLinks()) {
					if(elementLink.getTarget() instanceof ModelElement) {
						elements_objects.add(elementLink.getTarget());
					}
				}
			}

			CapellaDeleteCommand delete = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(elements_objects), elements_objects, true, !isHeadless(), true);
			delete.execute();
		}

	protected boolean isHeadless() {
		return true;
	}
}
