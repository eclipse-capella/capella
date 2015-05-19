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
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.test.recrepl.ju.ModelConstants_re;
import org.polarsys.capella.test.recrepl.ju.RecReplTestCase;

public class UpdateDefREC_AddElementOnRPL extends RecReplTestCase {
	
	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList(new String [] {"re"});  //$NON-NLS-1$
	}

	@Override	
	public void performTest() throws Exception {

	    ///////////////////////////////////////////////////
	   // Create a REC with 2 functions and an exchange //
	  ///////////////////////////////////////////////////

		createREC(getObjects(ModelConstants_re.LF1, ModelConstants_re.LF2, ModelConstants_re.FUNCTIONALEXCHANGE_1));
    CatalogElement rec = null;
    EObject object = null;
    object = getObject(ModelConstants_re.LF1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.LF2);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.FUNCTIONALEXCHANGE_1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    assertTrue(rec.eContainer() != null);
    rec.setName(CreateREC_FunctionalExchange.class.getCanonicalName());		
    
	    //////////////////////////////////////////////
	   // Create a RPL, elements should be created //
	  //////////////////////////////////////////////

    createReplica(getObjects(ModelConstants_re.LF3), rec);    
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
    
	    //////////////////////////////////////////////////////////////////////////////
	   // A function should be created and 2 links, one on the RPL, one on the REC //
	  //////////////////////////////////////////////////////////////////////////////

    EObject lf1 = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.LF1));
    Collection<EObject> elements = Arrays.asList(lf1, getObject(ModelConstants_re.LF4));
    updateDef(elements);
    object = getObject(ModelConstants_re.LF4);
    CatalogElementLink link = ReplicableElementExt.getReferencingLinks(object).iterator().next();    
    assertTrue(link != null);
    assertTrue(link.getSource().equals(replica));
    assertTrue(link.getTarget().equals(object));
    assertTrue(link.getOrigin() != null);
    assertTrue(link.getOrigin().getSource().equals(rec));
    assertTrue(!link.getOrigin().getTarget().equals(object));
    assertTrue(link.getOrigin().getTarget().eContainer().equals(object.eContainer()));
    int nbLinks = rec.getOwnedLinks().size();
    int nbLinks2 = replica.getOwnedLinks().size();
    assertTrue(nbLinks == nbLinks2);
	}

}
