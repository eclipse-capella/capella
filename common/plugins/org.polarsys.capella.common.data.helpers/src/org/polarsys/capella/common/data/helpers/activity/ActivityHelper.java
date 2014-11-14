/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.data.helpers.activity;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.activity.AbstractActivity;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityExchange;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.ActivityPartition;
import org.polarsys.capella.common.data.helpers.activity.delegates.AbstractActivityHelper;
import org.polarsys.capella.common.data.helpers.activity.delegates.ActivityEdgeHelper;
import org.polarsys.capella.common.data.helpers.activity.delegates.ActivityExchangeHelper;
import org.polarsys.capella.common.data.helpers.activity.delegates.ActivityNodeHelper;
import org.polarsys.capella.common.data.helpers.activity.delegates.ActivityPartitionHelper;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;

public class ActivityHelper implements IHelper {

	public Object getValue(EObject object_p, EStructuralFeature feature_p,
			EAnnotation annotation_p) {
		
		Object ret = null;
		
		if (object_p instanceof AbstractActivity) {
			ret = AbstractActivityHelper.getInstance().doSwitch((AbstractActivity) object_p, feature_p);
		} else
		if (object_p instanceof ActivityEdge) {
			ret = ActivityEdgeHelper.getInstance().doSwitch((ActivityEdge) object_p, feature_p);
		} else
		if (object_p instanceof ActivityNode) {
			ret = ActivityNodeHelper.getInstance().doSwitch((ActivityNode) object_p, feature_p);
		} else
		if (object_p instanceof ActivityPartition) {
			ret = ActivityPartitionHelper.getInstance().doSwitch((ActivityPartition) object_p, feature_p);
		} else
		if (object_p instanceof ActivityExchange) {
			ret = ActivityExchangeHelper.getInstance().doSwitch((ActivityExchange) object_p, feature_p);
		}
		
		if(null != ret || feature_p.getUpperBound() == 1)
			return ret;
		
		throw new HelperNotFoundException();
	}

}
