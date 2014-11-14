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
package org.polarsys.capella.core.transition.system.topdown.activities;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.transition.common.handlers.filter.CompoundFilteringItems;
import org.polarsys.capella.core.transition.common.handlers.filter.TargetAttributeFilterItem;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.filter.AppliedPropertyValuesFilterItem;
import org.polarsys.capella.core.transition.system.topdown.handlers.filter.AttributeStringValueFromSource;
import org.polarsys.capella.core.transition.system.topdown.handlers.filter.AttributeUpdateFilterItem;
import org.polarsys.capella.core.transition.system.topdown.handlers.filter.EmptyPackageFilterItem;
import org.polarsys.capella.core.transition.system.topdown.handlers.filter.OrderingDifferencesFilterItem;
import org.polarsys.capella.core.transition.system.topdown.handlers.filter.RealizationLinkFilterItem;
import org.polarsys.capella.core.transition.system.topdown.handlers.filter.ReferenceOnPresenceItem;
import org.polarsys.capella.core.transition.system.topdown.handlers.filter.ReferencePropagationFilterItem;
import org.polarsys.capella.core.transition.system.topdown.handlers.filter.RemoveRealizedFilterItem;
import org.polarsys.capella.core.transition.system.topdown.handlers.filter.TargetDifferencesFilterItem;
import org.polarsys.capella.core.transition.system.topdown.handlers.filter.UnwantedElementPresenceFilterItem;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class DifferencesFilteringActivity extends org.polarsys.capella.core.transition.system.activities.DifferencesFilteringActivity implements
    ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.system.topdown.activities.DifferencesFilteringActivity"; //$NON-NLS-1$

  @Override
  protected IStatus initializeFilterItemHandlers(IContext context_p, CompoundFilteringItems handler_p, ActivityParameters activityParams_p) {
    super.initializeFilterItemHandlers(context_p, handler_p, activityParams_p);

    handler_p.addFilterItem(new RemoveRealizedFilterItem(), context_p);
    handler_p.addFilterItem(new RealizationLinkFilterItem(), context_p);
    handler_p.addFilterItem(new TargetAttributeFilterItem(), context_p);
    handler_p.addFilterItem(new ReferenceOnPresenceItem(), context_p);
    handler_p.addFilterItem(new TargetDifferencesFilterItem(), context_p);
    handler_p.addFilterItem(new OrderingDifferencesFilterItem(), context_p);

    handler_p.addFilterItem(new AttributeUpdateFilterItem(), context_p);
    handler_p.addFilterItem(new AttributeStringValueFromSource(), context_p);
    handler_p.addFilterItem(new ReferencePropagationFilterItem(), context_p);
    handler_p.addFilterItem(new EmptyPackageFilterItem(), context_p);

    handler_p.addFilterItem(new UnwantedElementPresenceFilterItem(), context_p);

    handler_p.addFilterItem(new AppliedPropertyValuesFilterItem(), context_p);

    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean displayLog(IContext context_p) {
    return OptionsHandlerHelper.getInstance(context_p).getBooleanValue(context_p, ITopDownConstants.OPTIONS_SCOPE, ITopDownConstants.OPTIONS_LOG,
        ITopDownConstants.OPTIONS_LOG__DEFAULT.booleanValue());
  }
}
