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

public class CreateREC_ComponentFunctionalAllocation extends RecReplTestCase {
	
	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList(new String [] {"re"});  //$NON-NLS-1$
	}

	@Override	
	public void performTest() throws Exception {

	    //////////////////////////////////////////////////////////////////////////////////////////////////
	   // Create a REC with 1 function and a component. Allocation must be included, only 1 allocation //
	  //////////////////////////////////////////////////////////////////////////////////////////////////

		createREC(getObjects(ModelConstants_re.LC_1, ModelConstants_re.LF1));
    EObject object = getObject(ModelConstants_re.LF1);
    CatalogElement rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.LC_1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.COMPONENT_FUNCTIONAL_ALLOCATION_TO_LF1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.LF2);
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).isEmpty());
    object = getObject(ModelConstants_re.COMPONENT_FUNCTIONAL_ALLOCATION_TO_LF2);
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).isEmpty());
    rec.setName(CreateREC_ComponentFunctionalAllocation.class.getCanonicalName());

	    ////////////////////////////////////////////////
	   // Add LF2 on REC, allocation must be include //
	  ////////////////////////////////////////////////
    
    updateCur(getObjects(ModelConstants_re.LF2), rec);
    object = getObject(ModelConstants_re.LF1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.LF2);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.LC_1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.COMPONENT_FUNCTIONAL_ALLOCATION_TO_LF1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.COMPONENT_FUNCTIONAL_ALLOCATION_TO_LF2);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
	}

}
