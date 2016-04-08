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

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.detachment.propertyvalues.scrutinizers.PropertyGroupScrutinizer;
import org.polarsys.capella.detachment.propertyvalues.scrutinizers.PropertyValuePackageScrutinizer;
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
					deleteProperties(result, monitor);
				} 
				if (finder instanceof PropertyGroupScrutinizer) {
					Map<EObject, Boolean> result = ((PropertyGroupScrutinizer)finder).getAnalysisResult();
					deleteProperties(result, monitor);
				}
				if (finder instanceof PropertyValuePackageScrutinizer) {
					Map<EObject, Boolean> result = ((PropertyValuePackageScrutinizer)finder).getAnalysisResult();
					deleteProperties(result, monitor);
				}
			}
			monitor.done();
		} catch (ModelScrutinyException e) {
			e.printStackTrace();
		}
		
		

	}

	private void deleteProperties(Map<EObject, Boolean> result, IProgressMonitor monitor) {
		
		int size = result.size();
		monitor.beginTask("Delete Property Values/Groups/Packages", size);
		for(Entry<EObject, Boolean> e: result.entrySet()){	
			if (e.getValue()){
				monitor.subTask("Delete " + e.getKey());
				EcoreUtil.delete(e.getKey());
				monitor.subTask(e.getKey() + " deleted");
			}
		}
	}

}
