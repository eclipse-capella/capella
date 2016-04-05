/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.system.activities;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.core.transition.common.handlers.filter.AttributeValueFromSource;
import org.polarsys.capella.core.transition.common.handlers.filter.CompoundFilteringItems;
import org.polarsys.capella.core.transition.common.handlers.filter.ElementPresenceFromSource;
import org.polarsys.capella.core.transition.system.handlers.filter.AttributeNameValueFromSource;
import org.polarsys.capella.core.transition.system.handlers.filter.ElementPresenceManyToOne;
import org.polarsys.capella.core.transition.system.handlers.filter.ElementPresenceOneToMany;
import org.polarsys.capella.core.transition.system.handlers.filter.PreferenceFilterItem;
import org.polarsys.capella.core.transition.system.handlers.filter.RootFilterItem;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class DifferencesFilteringActivity extends org.polarsys.capella.core.transition.common.activities.DifferencesFilteringActivity implements
    ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.system.activities.DifferencesFilteringActivity"; //$NON-NLS-1$

  @Override
  protected IStatus initializeFilterItemHandlers(IContext context, CompoundFilteringItems handler, ActivityParameters activityParams) {
    super.initializeFilterItemHandlers(context, handler, activityParams);

    //Add default filters on transition.system only
    handler.addFilterItem(new RootFilterItem(), context);
    handler.addFilterItem(new PreferenceFilterItem(), context);
    handler.addFilterItem(new AttributeValueFromSource(), context);
    handler.addFilterItem(new ElementPresenceFromSource(), context);

    //Filter to avoid name propagation
    handler.addFilterItem(new AttributeNameValueFromSource(), context);

    handler.addFilterItem(new ElementPresenceOneToMany(), context);
    handler.addFilterItem(new ElementPresenceManyToOne(), context);

    return Status.OK_STATUS;
  }

}
