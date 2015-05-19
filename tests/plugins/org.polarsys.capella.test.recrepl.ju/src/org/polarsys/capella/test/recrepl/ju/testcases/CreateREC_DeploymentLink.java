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
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.test.recrepl.ju.ModelConstants_re;
import org.polarsys.capella.test.recrepl.ju.RecReplTestCase;

public class CreateREC_DeploymentLink extends RecReplTestCase {
	
	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList(new String [] {"re"});  //$NON-NLS-1$
	}

	@Override	
	public void performTest() throws Exception {

	    ////////////////////////////////////////////////////////////////////////////
	   // Create a REC with 2 PC. Deployment must be include (only 1 deployment) //
	  ////////////////////////////////////////////////////////////////////////////
		
		createREC(getObjects(ModelConstants_re.PC_1, ModelConstants_re.PC_2));
		EObject object = getObject(ModelConstants_re.PC_1);
    CatalogElement rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.PC_1__PC_1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.PC_2);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.PC_2__PC_2);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.PART_DEPLOYMENT_LINK_TO_PC_2);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.PC_3);
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).isEmpty());
    object = getObject(ModelConstants_re.PC_3__PC_3);
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).isEmpty());
    object = getObject(ModelConstants_re.PART_DEPLOYMENT_LINK_TO_PC_3);
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).isEmpty());
    rec.setName(CreateREC_DeploymentLink.class.getCanonicalName());		
		
	    //////////////////////////////////////////
	   // Add PC3, Deployment must be included //
	  //////////////////////////////////////////

    updateCur(getObjects(ModelConstants_re.PC_1, ModelConstants_re.PC_3), rec);
    object = getObject(ModelConstants_re.PC_3);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.PC_3__PC_3);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.PART_DEPLOYMENT_LINK_TO_PC_3);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
	}

}
