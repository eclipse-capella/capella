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
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.test.recrepl.ju.ModelConstants_re;
import org.polarsys.capella.test.recrepl.ju.RecReplTestCase;

public class CreateRPL_SharedElements_ExchangeItems extends RecReplTestCase {
	
	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList(new String [] {"re"});  //$NON-NLS-1$
	}

	@Override	
	public void performTest() throws Exception {

	    //////////////////////////////////////////////////////////////////
	   // Create a REC with 2 functions and an exchange linked to a EI //
	  //////////////////////////////////////////////////////////////////

		createREC(getObjects(ModelConstants_re.LF1, ModelConstants_re.LF2, ModelConstants_re.FUNCTIONALEXCHANGE_1));
    EObject object = getObject(ModelConstants_re.LF1);
    CatalogElement rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.LF2);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.FUNCTIONALEXCHANGE_1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    //EI should not be linked to REC
    object = getObject(ModelConstants_re.EI1);
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).isEmpty());
		
	    //////////////////////////////////////////////////////////////////////////////////
	   // Create a RPL, elements should be created, RPL element should be linked to EI //
	  //////////////////////////////////////////////////////////////////////////////////
    
		createReplica(getObjects(ModelConstants_re.LF1, ModelConstants_re.LF2, ModelConstants_re.FUNCTIONALEXCHANGE_1), rec);
    object = getObject(ModelConstants_re.LF1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    Collection<CatalogElement> replicas = ReplicableElementExt.getReplicas(rec);
    assertTrue(replicas.size() == 1);
    CatalogElement replica = replicas.iterator().next();
    assertTrue(replica != null);
    assertTrue(replica.getKind() == CatalogElementKind.RPL);
    assertTrue(replica.eContainer() != null);
    //Elements from replica should be created and attached
    assertTrue(replica.getOrigin().equals(rec));
    EObject LF1rpl = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.LF1));
    assertTrue(LF1rpl != null);
    assertTrue(LF1rpl.eContainer() != null);
    assertTrue(LF1rpl != getObject(ModelConstants_re.LF1));
    EObject LF2rpl = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.LF2));
    assertTrue(LF2rpl != null);
    assertTrue(LF2rpl.eContainer() != null);
    assertTrue(LF2rpl != getObject(ModelConstants_re.LF2));
    EObject FE1rpl = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.FUNCTIONALEXCHANGE_1));
    assertTrue(FE1rpl != null);
    assertTrue(FE1rpl.eContainer() != null);
    assertTrue(FE1rpl != getObject(ModelConstants_re.FUNCTIONALEXCHANGE_1));
    //Functional exchange should be linked to EI
    assertTrue(((FunctionalExchange) FE1rpl).getExchangedItems().contains(getObject(ModelConstants_re.EI1)));
    //EI should not be linked to RPL
    object = getObject(ModelConstants_re.EI1);
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).isEmpty());	
    
	    ////////////////////////
	   // Add EI1 to the REC //
	  ////////////////////////
    
    updateCur(getObjects(ModelConstants_re.EI1), rec);
    object = getObject(ModelConstants_re.FUNCTIONALEXCHANGE_1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    //I1 should  be linked to REC
    object = getObject(ModelConstants_re.EI1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);

	    /////////////////////////////////////////////////////////
	   // RPL and elements should be linked to EIrpl, not EI1 //
	  /////////////////////////////////////////////////////////
    
    updateReplica(getObjects(ModelConstants_re.LF1), replica);
    LF1rpl = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.FUNCTIONALEXCHANGE_1));
    assertTrue(LF1rpl != null);
    assertTrue(LF1rpl.eContainer() != null);
    //I1 should not be linked to RPL
    EObject I1rpl = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.EI1));
    assertTrue(((FunctionalExchange) LF1rpl).getExchangedItems().contains(I1rpl));
    //TODO When an shared elements is referenced through a EReference.isMany, previous I1 is not removed, but i guess it should be
    //EObject I1 = getObject(ModelConstants_re.EI1);
    //assertTrue(!((FunctionalExchange) LF1rpl).getExchangedItems().contains(I1));
	}

}
