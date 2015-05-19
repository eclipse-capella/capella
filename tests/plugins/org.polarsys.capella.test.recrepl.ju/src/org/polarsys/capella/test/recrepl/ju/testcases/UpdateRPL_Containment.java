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
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.test.recrepl.ju.ModelConstants_re;
import org.polarsys.capella.test.recrepl.ju.RecReplTestCase;

public class UpdateRPL_Containment extends RecReplTestCase {
	
	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList(new String [] {"re"});  //$NON-NLS-1$
	}

	@Override	
	public void performTest() throws Exception {

	    ///////////////////////////////////
	   // Create a REC with 2 functions //
	  ///////////////////////////////////
	
		createREC(getObjects(ModelConstants_re.LF1, ModelConstants_re.LF2));
	  EObject object = getObject(ModelConstants_re.LF1);
	  CatalogElement rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
	  assertTrue(rec != null);
	  object = getObject(ModelConstants_re.LF2);
	  rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
	  assertTrue(rec != null);
	  rec.setName(getClass().getCanonicalName());		
		
	    /////////////////////////////////////////////////////////
	   // Create a RPL in LF4, functions must be onwed by LF4 //
	  /////////////////////////////////////////////////////////
	  
	  createReplica(getObjects(ModelConstants_re.LF4), rec);
	  object = getObject(ModelConstants_re.LF1);
	  rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
	  assertTrue(rec != null);
	  Collection<CatalogElement> replicas = ReplicableElementExt.getReplicas(rec);
	  assertTrue(replicas.size() == 1);
	  CatalogElement replica = replicas.iterator().next();
	  assertTrue(replica != null);
	  EObject LF1rpl = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.LF1));
	  assertTrue(LF1rpl != null);
	  assertTrue(LF1rpl.eContainer().equals(getObject(ModelConstants_re.LF4)));
	  EObject LF2rpl = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.LF2));
	  assertTrue(LF2rpl != null);
	  assertTrue(LF2rpl.eContainer().equals(getObject(ModelConstants_re.LF4)));		
		
	    ////////////////////////
	   // Add LF3 on the REC //
	  ////////////////////////

		updateCur(getObjects(ModelConstants_re.LF3), rec);
    object = getObject(ModelConstants_re.LF3);
    CatalogElement re = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(re != null);
    assertTrue(re.equals(rec));
    re.setName(getClass().getCanonicalName());

	    //////////////////////////
	   // LF3 should be on LF4 //
	  //////////////////////////

    updateReplica(Arrays.asList(new EObject[] {replica}), replica);
    object = getObject(ModelConstants_re.LF3);
    re = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(re != null);
    replicas = ReplicableElementExt.getReplicas(re);
    assertTrue(replicas.size() == 1);
    replica = replicas.iterator().next();
    assertTrue(replica != null);
    assertTrue(replica.equals(replica));
    EObject LF3rpl = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.LF3));
    assertTrue(LF3rpl != null);
    assertTrue(LF3rpl.eContainer() != null);
    assertTrue(LF3rpl != getObject(ModelConstants_re.LF3));
    assertTrue(LF3rpl.eContainer().equals(getObject(ModelConstants_re.LF3).eContainer()));    
	}

}
