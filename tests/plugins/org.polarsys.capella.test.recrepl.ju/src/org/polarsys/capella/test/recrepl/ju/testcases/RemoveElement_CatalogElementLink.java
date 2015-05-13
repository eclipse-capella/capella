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
package org.polarsys.capella.test.recrepl.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.test.recrepl.ju.ModelConstants_re;
import org.polarsys.capella.test.recrepl.ju.RecReplTestCase;

public class RemoveElement_CatalogElementLink extends RecReplTestCase {
	
	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList(new String [] {"re"});  //$NON-NLS-1$
	}

	@Override	
	public void performTest() throws Exception {

	    ////////////////////////////////////////////////////////////////////////////////////
	   // Create a REC and remove a linked element. CatalogElementLink should be removed //
	  ////////////////////////////////////////////////////////////////////////////////////

		createREC(getObjects(ModelConstants_re.LF1, ModelConstants_re.LF2, ModelConstants_re.FUNCTIONALEXCHANGE_1));
    EObject object = getObject(ModelConstants_re.LF1);
    CatalogElement re = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(re != null);
    int nbLinks = re.getOwnedLinks().size();
    object = getObject(ModelConstants_re.LF2);
    re = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(re != null);
    object = getObject(ModelConstants_re.FUNCTIONALEXCHANGE_1);
    re = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(re != null);
    object = getObject(ModelConstants_re.FIP_1);
    re = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(re != null);
    object = getObject(ModelConstants_re.FOP_1);
    re = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(re != null);
    assertTrue(re.eContainer() != null);
    re.setName(CreateREC_FunctionalExchange.class.getCanonicalName());
    //We remove the functional exchange
    CapellaDeleteCommand delete = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(getObject(ModelConstants_re.FUNCTIONALEXCHANGE_1)), Arrays.asList(getObject(ModelConstants_re.FUNCTIONALEXCHANGE_1)), true, false, false);
    delete.execute();
    int nbLinks2 = re.getOwnedLinks().size();
    assertTrue((nbLinks - 1) == nbLinks2);
	}

}
