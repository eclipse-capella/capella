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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.test.recrepl.ju.ModelConstants_re;
import org.polarsys.capella.test.recrepl.ju.RecReplTestCase;

public class CreateRPL_ContainedInCatalog extends RecReplTestCase {
	
	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList(new String [] {"re"});  //$NON-NLS-1$
	}

	@Override	
	public void performTest() throws Exception {

	    //////////////////////////////////
	   // Create a REC with 1 function //
	  //////////////////////////////////

		createREC(getObjects(ModelConstants_re.LF1));
    EObject object = getObject(ModelConstants_re.LF1);
    CatalogElement re = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(re != null);
    re.setName(CreateRPL_ContainedInCatalog.class.getCanonicalName());
		
	    //////////////////////////////////////////////////////////////
	   // Create a RPL. RPL should be contained in the REC Catalog //
	  //////////////////////////////////////////////////////////////
	
		createReplica(getObjects(ModelConstants_re.LF1, ModelConstants_re.LF2, ModelConstants_re.FUNCTIONALEXCHANGE_1), (CatalogElement) re);
    object = getObject(ModelConstants_re.LF1);
    re = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(re != null);
    Collection<CatalogElement> replicas = ReplicableElementExt.getReplicas(re);
    assertTrue(replicas.size() == 1);
    CatalogElement replica = replicas.iterator().next();
    assertTrue(replica != null);
    CatalogElementPkg pkg = (CatalogElementPkg) replica.eContainer();
    assertTrue(pkg != null);		
	}

}
