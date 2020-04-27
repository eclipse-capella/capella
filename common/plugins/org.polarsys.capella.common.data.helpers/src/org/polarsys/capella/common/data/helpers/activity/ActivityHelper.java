/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

	public Object getValue(EObject object, EStructuralFeature feature,
			EAnnotation annotation) {
		
		Object ret = null;
		
		if (object instanceof AbstractActivity) {
			ret = AbstractActivityHelper.getInstance().doSwitch((AbstractActivity) object, feature);
		} else
		if (object instanceof ActivityEdge) {
			ret = ActivityEdgeHelper.getInstance().doSwitch((ActivityEdge) object, feature);
		} else
		if (object instanceof ActivityNode) {
			ret = ActivityNodeHelper.getInstance().doSwitch((ActivityNode) object, feature);
		} else
		if (object instanceof ActivityPartition) {
			ret = ActivityPartitionHelper.getInstance().doSwitch((ActivityPartition) object, feature);
		} else
		if (object instanceof ActivityExchange) {
			ret = ActivityExchangeHelper.getInstance().doSwitch((ActivityExchange) object, feature);
		}
		
		if(null != ret || feature.getUpperBound() == 1)
			return ret;
		
		throw new HelperNotFoundException();
	}

}
