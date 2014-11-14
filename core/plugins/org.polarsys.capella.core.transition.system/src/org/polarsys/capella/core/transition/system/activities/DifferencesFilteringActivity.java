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
package org.polarsys.capella.core.transition.system.activities;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.transition.common.handlers.filter.AttributeValueFromSource;
import org.polarsys.capella.core.transition.common.handlers.filter.CompoundFilteringItems;
import org.polarsys.capella.core.transition.common.handlers.filter.ElementPresenceFromSource;
import org.polarsys.capella.core.transition.system.handlers.filter.AttributeNameValueFromSource;
import org.polarsys.capella.core.transition.system.handlers.filter.PreferenceFilterItem;
import org.polarsys.capella.core.transition.system.handlers.filter.RootFilterItem;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class DifferencesFilteringActivity extends org.polarsys.capella.core.transition.common.activities.DifferencesFilteringActivity implements
    ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.system.activities.DifferencesFilteringActivity"; //$NON-NLS-1$

  @Override
  protected IStatus initializeFilterItemHandlers(IContext context_p, CompoundFilteringItems handler_p, ActivityParameters activityParams_p) {
    super.initializeFilterItemHandlers(context_p, handler_p, activityParams_p);

    //Add default filters on transition.system only
    handler_p.addFilterItem(new RootFilterItem(), context_p);
    handler_p.addFilterItem(new PreferenceFilterItem(), context_p);
    handler_p.addFilterItem(new AttributeValueFromSource(), context_p);
    handler_p.addFilterItem(new ElementPresenceFromSource(), context_p);

    //Filter to avoid name propagation
    handler_p.addFilterItem(new AttributeNameValueFromSource(), context_p);
    return Status.OK_STATUS;
  }

}
