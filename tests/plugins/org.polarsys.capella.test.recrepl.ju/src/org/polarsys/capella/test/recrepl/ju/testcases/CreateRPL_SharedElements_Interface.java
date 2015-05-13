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
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.test.recrepl.ju.ModelConstants_re;
import org.polarsys.capella.test.recrepl.ju.RecReplTestCase;

public class CreateRPL_SharedElements_Interface extends RecReplTestCase {
	
	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList(new String [] {"re"});  //$NON-NLS-1$
	}

	@Override	
	public void performTest() throws Exception {

	    /////////////////////////////////////////////////
	   // Create a REC with 1 LC linked to Interfaces //
	  /////////////////////////////////////////////////
		
		System.out.println("(1)");
		createREC(getObjects(ModelConstants_re.LC_2));
		EObject object = getObject(ModelConstants_re.LC_2);
    CatalogElement rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    //I1 should not be linked to REC
    object = getObject(ModelConstants_re.I1);
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).isEmpty());
    //I2 should not be linked to REC
    object = getObject(ModelConstants_re.I2);
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).isEmpty());		

	    ///////////////////////////////////////////////////////////////////////////////////////////////////
	   // Create a RPL, elements should be created, RPL element should be linked to EI, but not the RPL //
	  ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    System.out.println("(2)");
    createReplica(getObjects(ModelConstants_re.LF1, ModelConstants_re.LF2, ModelConstants_re.FUNCTIONALEXCHANGE_1), rec);
    object = getObject(ModelConstants_re.LC_2);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    Collection<CatalogElement> replicas = ReplicableElementExt.getReplicas(rec);
    assertTrue(replicas.size() == 1);
    CatalogElement replica = replicas.iterator().next();
    assertTrue(replica != null);
    assertTrue(replica.getKind() == CatalogElementKind.RPL);
    assertTrue(replica.eContainer() != null);
    EObject LF1rpl = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.LC_2));
    assertTrue(LF1rpl != null);
    assertTrue(LF1rpl.eContainer() != null);
    assertTrue(LF1rpl != getObject(ModelConstants_re.LC_2));
    //I1 should not be linked to RPL
    object = getObject(ModelConstants_re.I1);
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).isEmpty());
    //I2 should not be linked to RPL
    object = getObject(ModelConstants_re.I2);
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).isEmpty());
    assertTrue(((LogicalComponent) LF1rpl).getUsedInterfaces().contains(getObject(ModelConstants_re.I2)));
    assertTrue(((LogicalComponent) LF1rpl).getProvidedInterfaces().contains(getObject(ModelConstants_re.I1)));
    
	    //////////////////////////////
	   // Add I1 and I2 to the REC //
	  //////////////////////////////
    
    System.out.println("(3)");
    updateCur(getObjects(ModelConstants_re.I1, ModelConstants_re.I2), rec);
    object = getObject(ModelConstants_re.LC_2);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    //I1 should  be linked to REC
    object = getObject(ModelConstants_re.I1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    //I2 should  be linked to REC
    object = getObject(ModelConstants_re.I2);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);

	}

}
