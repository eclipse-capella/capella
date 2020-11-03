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
package org.polarsys.capella.detachment.propertyvalue.command;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.core.model.handler.crossreferencer.CapellaECrossReferenceAdapter;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.detachment.propertyvalue.messages.Messages;
import org.polarsys.capella.detachment.propertyvalues.scrutinizers.PropertyValuesScrutinizer;
import org.polarsys.kitalpha.model.common.commands.action.ModelCommand;
import org.polarsys.kitalpha.model.common.commands.exception.ModelCommandException;
import org.polarsys.kitalpha.model.common.scrutiny.analyzer.ModelScrutinyException;
import org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize;
import org.polarsys.kitalpha.model.common.scrutiny.registry.ModelScrutinyRegistry;
import org.polarsys.kitalpha.model.common.scrutiny.registry.ModelScrutinyRegistry.RegistryElement;

public class PropertyValuesDeleteCommand extends ModelCommand {
	
	Logger LOGGER = Logger.getLogger(PropertyValuesDeleteCommand.class);

	public PropertyValuesDeleteCommand() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void exec(ModelScrutinyRegistry analysis, Resource resource, IProgressMonitor monitor) throws ModelCommandException {
		
		try {
			RegistryElement regElt = analysis.getRegistryElement(getModelAnalysisID());
			
			for (IScrutinize finder : regElt.getFinders()) {
				if (finder instanceof PropertyValuesScrutinizer){
					Map<EObject, Boolean> result = ((PropertyValuesScrutinizer)finder).getAnalysisResult();
					deleteProperties(result, resource, monitor);
				} 
			}
			monitor.done();
		} catch (ModelScrutinyException e) {
			LOGGER.error(Messages.PVDetachmentCommand_RuntimeError, e);
		}
	}

	
	private void deleteProperties(Map<EObject, Boolean> result, Resource resource, IProgressMonitor monitor) {

		if (result != null && !result.isEmpty()){
			
			URI uri = resource.getURI();
			if (uri.lastSegment() == null || !uri.lastSegment().endsWith(SiriusUtil.SESSION_RESOURCE_EXTENSION))
				return;
			
			resource.getURI();
			
			Session session = SessionManager.INSTANCE.getExistingSession(uri);
			EditingDomain ed = null;
			
			if (session != null){
				ed = session.getTransactionalEditingDomain();
			} else {
				ed = TransactionUtil.getEditingDomain(resource);
			}
			
			monitor.beginTask(Messages.PVDetachmentCommand_MonitorMessage, 1);
			installCapellaECrossReferencerAdapter(resource.getResourceSet(), ed);
			
			Collection<Object> elementsToDelete = getElementsToDelete(result);
			Command deleteCommand = new CapellaDeleteCommand(ed, elementsToDelete, false);
			if (deleteCommand.canExecute()){
				ed.getCommandStack().execute(deleteCommand);
			}
			monitor.worked(1);
		}
	}
	
	/**
	 * Install an Capella ECross Referencer on resourceSet of resource if it is not already installed
	 * @param resource
	 * @param ed
	 */
	private void installCapellaECrossReferencerAdapter(ResourceSet set, EditingDomain ed) {
		EList<Adapter> eAdapters = set.eAdapters();
		
		for (Adapter adapter : eAdapters) {
			if (adapter instanceof CapellaECrossReferenceAdapter){
				return;
			}
		}
		ECrossReferenceAdapter adapter = new CapellaECrossReferenceAdapter(ed);
		eAdapters.add(adapter);
	}

	private Collection<Object> getElementsToDelete(Map<EObject, Boolean> result){
		HashSet<Object> toDelete = new HashSet<Object>();
		for (Entry<EObject, Boolean> e: result.entrySet()) {
			if (e.getValue()){
				toDelete.add(e.getKey());
			}
		}
		return toDelete;
	}
}
