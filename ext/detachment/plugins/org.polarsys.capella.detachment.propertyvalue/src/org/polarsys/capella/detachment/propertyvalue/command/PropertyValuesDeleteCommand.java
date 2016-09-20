/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.detachment.propertyvalues.scrutinizers.PropertyValuesScrutinizer;
import org.polarsys.kitalpha.model.common.commands.action.ModelCommand;
import org.polarsys.kitalpha.model.common.commands.exception.ModelCommandException;
import org.polarsys.kitalpha.model.common.scrutiny.analyzer.ModelScrutinyException;
import org.polarsys.kitalpha.model.common.scrutiny.analyzer.Scrutineer;
import org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize;
import org.polarsys.kitalpha.model.common.scrutiny.registry.ModelScrutinyRegistry.RegistryElement;

public class PropertyValuesDeleteCommand extends ModelCommand {
	
	Logger LOGGER = Logger.getLogger(PropertyValuesDeleteCommand.class);

	public PropertyValuesDeleteCommand() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void exec(Resource resource, IProgressMonitor monitor) throws ModelCommandException {
		
		try {
			RegistryElement regElt = Scrutineer.getRegistryElement(getModelAnalysisID());
			
			for (IScrutinize finder : regElt.getFinders()) {
				if (finder instanceof PropertyValuesScrutinizer){
					Map<EObject, Boolean> result = ((PropertyValuesScrutinizer)finder).getAnalysisResult();
					deleteProperties(result, resource, monitor);
				} 
			}
			monitor.done();
		} catch (ModelScrutinyException e) {
			LOGGER.error("An error was occured at the execution of Property Values detachment Command. See the error log for more details", e);
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
			
			monitor.beginTask("Delete Property Values/Groups/Packages", 1);
			
			Collection<Object> elementsToDelete = getElementsToDelete(result, null);
			Command deleteCommand = DeleteCommand.create(ed, elementsToDelete);
			if (deleteCommand.canExecute()){
				ed.getCommandStack().execute(deleteCommand);
			}
			monitor.worked(1);
		}
	}
	
	private Collection<Object> getElementsToDelete(Map<EObject, Boolean> result, Session session){
		HashSet<Object> toDelete = new HashSet<Object>();
		for (Entry<EObject, Boolean> e: result.entrySet()) {
			if (e.getValue()){
				toDelete.add(e.getKey());
			}
		}
		return toDelete;
	}
	
}
