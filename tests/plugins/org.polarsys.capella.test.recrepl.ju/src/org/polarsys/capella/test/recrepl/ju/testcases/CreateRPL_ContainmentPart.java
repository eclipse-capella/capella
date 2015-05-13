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
import org.polarsys.capella.test.recrepl.ju.ModelConstants_re;
import org.polarsys.capella.test.recrepl.ju.RecReplTestCase;

public class CreateRPL_ContainmentPart extends RecReplTestCase {
	
	@Override
	public List<String> getRequiredTestModels() {
		return Arrays.asList(new String [] {"re"});  //$NON-NLS-1$
	}

	@Override	
	public void performTest() throws Exception {

	    ////////////////////////////////////////////////
	   // Create a REC with a part and its component //
	  ////////////////////////////////////////////////

		createREC(getObjects(ModelConstants_re.LC_1, ModelConstants_re.LC_1__LC_1));		
    EObject object = getObject(ModelConstants_re.LC_1);
    CatalogElement rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.LC_1__LC_1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    rec.setName(getClass().getCanonicalName());
    
      ////////////////////////////////////////////////////////
     // Create a RPL, component should be next to its part //
    ////////////////////////////////////////////////////////

    Collection<EObject> elements = getObjects(ModelConstants_re.LC_2);
    createReplica(elements, (CatalogElement) rec);
    object = getObject(ModelConstants_re.LC_1);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    Collection<CatalogElement> replicas = ReplicableElementExt.getReplicas(rec);
    assertTrue(replicas.size() == 1);
    CatalogElement replica = replicas.iterator().next();
    assertTrue(replica != null);
    assertTrue(replica.getKind() == CatalogElementKind.RPL);
    assertTrue(replica.eContainer() != null);
    // Elements from replica should be created and attached
    assertTrue(replica.getOrigin().equals(rec));
    EObject LF1rpl = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.LC_1));
    assertTrue(LF1rpl != null);
    assertTrue(LF1rpl.eContainer() != null);
    assertTrue(LF1rpl != getObject(ModelConstants_re.LC_1));
    EObject LF2rpl = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.LC_1__LC_1));
    assertTrue(LF2rpl != null);
    assertTrue(LF2rpl.eContainer() != null);
    assertTrue(LF2rpl != getObject(ModelConstants_re.LC_1__LC_1));
    // Component should be next to its part.
    assertTrue(LF1rpl.eContainer() == LF2rpl.eContainer());
    
      //////////////////////////////////////////////////////////////////////////////////
     // Create a REC with a part and its component, the component is inside its part //
    //////////////////////////////////////////////////////////////////////////////////
    
    elements = getObjects(ModelConstants_re.LC_3_LC_3, ModelConstants_re.LC_3_LC_3__LC_3);
    createREC(elements);
    object = getObject(ModelConstants_re.LC_3_LC_3);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    object = getObject(ModelConstants_re.LC_3_LC_3__LC_3);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    rec.setName(getClass().getCanonicalName());

	    ///////////////////////////////////////////////////////
	   // Create a RPL, component should be inside its part //
	  ///////////////////////////////////////////////////////
    
    elements = getObjects(ModelConstants_re.LC_2);
    createReplica(elements, rec);
    object = getObject(ModelConstants_re.LC_3_LC_3__LC_3);
    rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rec != null);
    replicas = ReplicableElementExt.getReplicas(rec);
    assertTrue(replicas.size() == 1);
    replica = replicas.iterator().next();
    assertTrue(replica != null);
    assertTrue(replica.getKind() == CatalogElementKind.RPL);
    assertTrue(replica.eContainer() != null);
    // Elements from replica should be created and attached
    assertTrue(replica.getOrigin().equals(rec));
    LF1rpl = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.LC_3_LC_3));
    assertTrue(LF1rpl != null);
    assertTrue(LF1rpl.eContainer() != null);
    assertTrue(LF1rpl != getObject(ModelConstants_re.LC_3_LC_3));
    LF2rpl = ReplicableElementExt.getReferencingElement(replica, getObject(ModelConstants_re.LC_3_LC_3__LC_3));
    assertTrue(LF2rpl != null);
    assertTrue(LF2rpl.eContainer() != null);
    assertTrue(LF2rpl != getObject(ModelConstants_re.LC_3_LC_3__LC_3));
    // Component should be inside its part.
    assertTrue(LF2rpl.eContainer() == LF1rpl);
	}

}
