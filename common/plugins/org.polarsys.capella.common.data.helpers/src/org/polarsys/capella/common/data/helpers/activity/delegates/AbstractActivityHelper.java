/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.data.helpers.activity.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.data.activity.AbstractActivity;
import org.polarsys.capella.common.data.activity.ActivityGroup;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.StructuredActivityNode;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.TraceableElementHelper;

public class AbstractActivityHelper {
	
	private static AbstractActivityHelper instance;
	
	private AbstractActivityHelper() {
	  // do nothing
	}
	
	public static AbstractActivityHelper getInstance(){
		if(instance == null)
			instance = new AbstractActivityHelper();
		return instance;
	}
	
	public Object doSwitch(AbstractActivity element, EStructuralFeature feature){
		Object ret = null;
		
		if (feature.equals(ActivityPackage.Literals.ABSTRACT_ACTIVITY__OWNED_STRUCTURED_NODES)) {
			ret = getOwnedStructuredNodes(element);
		}

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = TraceableElementHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}
	
	protected List<StructuredActivityNode> getOwnedStructuredNodes (AbstractActivity element) {
		List <ActivityGroup> groups = element.getOwnedGroups();
		List <StructuredActivityNode> ret = new ArrayList<> ();

		for(ActivityGroup group: groups) {
			if(group instanceof StructuredActivityNode){
				ret.add((StructuredActivityNode)group);		
			}
		}

		return ret;
	}
}
