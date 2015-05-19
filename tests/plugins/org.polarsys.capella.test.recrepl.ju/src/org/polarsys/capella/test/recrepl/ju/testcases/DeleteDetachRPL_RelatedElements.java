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
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.test.recrepl.ju.ModelConstants_re;
import org.polarsys.capella.test.recrepl.ju.RecReplTestCase;

public class DeleteDetachRPL_RelatedElements extends RecReplTestCase {
	
	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList(new String [] {"re"});  //$NON-NLS-1$
	}

	@Override	
	public void performTest() throws Exception {

	    /////////////////////////////////////////
	   // Delete Replica and related elements //
	  /////////////////////////////////////////
		
		deleteReplicaAndRelatedElements(getObjects(ModelConstants_re.PC_11));
		assertFalse(getObject(ModelConstants_re.PC_11) != null);
		assertFalse(getObject(ModelConstants_re.PC_12) != null);
		assertFalse(getObject(ModelConstants_re.PF_13) != null);
		EObject object = getObject(ModelConstants_re.PC_21);
		CatalogElement rpl = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
		assertTrue(rpl != null);
		CatalogElement rec = rpl.getOrigin();
		Collection<EObject> links_rec = EObjectExt.getReferencers(rec, RePackage.Literals.CATALOG_ELEMENT__ORIGIN);
		assertTrue(links_rec.size() == 2);
		
	    //////////////////////////////////////////////////
	   // Delete Replica and preserve related elements //
	  //////////////////////////////////////////////////
		
		deleteReplicaPreserveRelatedElements(getObjects(ModelConstants_re.PC_21));
		object = getObject(ModelConstants_re.PC_21);
		assertTrue(getObject(ModelConstants_re.PC_21) != null);
		assertTrue(getObject(ModelConstants_re.PC_22) != null);
		assertTrue(getObject(ModelConstants_re.PF_23) != null);
		assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).size() == 0);
		object = getObject(ModelConstants_re.PC_31);
		rpl = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
		assertTrue(rpl != null);
		rec = rpl.getOrigin();
		links_rec = EObjectExt.getReferencers(rec, RePackage.Literals.CATALOG_ELEMENT__ORIGIN);
		assertTrue(links_rec.size() == 1);
	}

}
