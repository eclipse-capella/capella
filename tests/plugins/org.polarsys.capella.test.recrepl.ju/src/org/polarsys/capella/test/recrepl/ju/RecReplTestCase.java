/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.recrepl.ju;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.RecCatalog;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.re.commands.CreateRecCommand;
import org.polarsys.capella.core.re.commands.CreateReplicaCommand;
import org.polarsys.capella.core.re.commands.DeleteReplicaAndRelatedElementsCommand;
import org.polarsys.capella.core.re.commands.DeleteReplicaPreserveRelatedElementsCommand;
import org.polarsys.capella.core.re.commands.UpdateCurCommand;
import org.polarsys.capella.core.re.commands.UpdateDefCommand;
import org.polarsys.capella.core.re.commands.UpdateReplicaCommand;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This class is a generic test case for REC/REPL tests.<br>
 * To use it, create a test case that inherits from this class and implement abstract methods, that are<br>
 * (see method documentation for more details) :<br>
 * <br>
 * - performTest()<br>
 * - getRequiredTestModels()<br>
 * 
 * @author Erwan Brottier
 */
public abstract class RecReplTestCase extends BasicTestCase {

	protected Resource modelResource;
	
	@Override
	public void test() throws Exception {		
		final Exception [] t = new Exception[] {null};
		TransactionHelper.getExecutionManager(getProject()).execute(new AbstractReadWriteCommand() {			
			@Override
			public void run() {
				try {
					performTest();
				} catch (Exception e) {
					t[0] = e;
				}
			}
		});
		if (t[0] != null)
			throw t[0];
	}

	/** overrides this method to write the test code */
	public abstract void performTest() throws Exception;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void createREC(Collection<EObject> elements) {
		ICommand command = new CreateRecCommand((Collection) elements, new NullProgressMonitor());
    executeCommand(command);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void createReplica(Collection<EObject> elements, CatalogElement rec) {
		ICommand command = new CreateReplicaCommand((Collection) elements, new NullProgressMonitor());
    RecReplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE, rec);
		executeCommand(command);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void updateCur(Collection<EObject> elements, CatalogElement rec) {
		ICommand command = new UpdateCurCommand((Collection) elements, new NullProgressMonitor());
    RecReplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET, rec);
		executeCommand(command);		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void updateReplica(Collection<EObject> elements, CatalogElement replica) {
		ICommand command = new UpdateReplicaCommand((Collection) elements, new NullProgressMonitor());
    RecReplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET, replica);
		executeCommand(command);		
	}	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void updateDef(Collection<EObject> elements) {
		ICommand command = new UpdateDefCommand((Collection) elements, new NullProgressMonitor());
		executeCommand(command);		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void deleteReplicaAndRelatedElements(Collection<EObject> elements) {
		ICommand command = new DeleteReplicaAndRelatedElementsCommand((Collection) elements, new NullProgressMonitor());
		executeCommand(command);				
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void deleteReplicaPreserveRelatedElements(Collection<EObject> elements) {
		ICommand command = new DeleteReplicaPreserveRelatedElementsCommand((Collection) elements, new NullProgressMonitor());
		executeCommand(command);				
	}
	
	
	
	
	protected RecCatalog getRecCatalog() {
		Project project = (Project) getProject();
		SystemEngineering sys = SystemEngineeringExt.getSystemEngineering(project);
		for (EObject element : sys.getOwnedExtensions())
			if (element instanceof RecCatalog)
				return (RecCatalog) element;
		return null;
	}
	
	protected CatalogElement getREC(String name) {
		for (CatalogElement element : getRecCatalog().getOwnedElements()) {
			if (element.getKind() == CatalogElementKind.REC && element.getName().equals(name))
				return element;
		}
		return null;
	}
	
  protected Resource getModelResource() {
  	if (modelResource == null) {
  		Session session = getSessionForTestModel(getRequiredTestModels().get(0));
  		modelResource = (Resource) session.getSemanticResources().toArray()[0];  		
  	}
  	return modelResource;
  }

  protected Project getProject() {
    return (Project) getModelResource().getContents().iterator().next();
  }

	protected void executeCommand(ICommand command) {
		try {
			TransactionHelper.getExecutionManager(getProject()).execute(command);			
		} finally {
			RecReplCommandManager.clear();
		}
	}
	  
  protected EObject getObject(String id) {
    return getModelResource().getEObject(id);
  }
    
  protected Collection<EObject> getObjects(String... ids_p) {
    Collection<EObject> objects = new ArrayList<EObject>();
    for (String id : ids_p)
      objects.add(getObject(id));
    return objects;
  }
}
