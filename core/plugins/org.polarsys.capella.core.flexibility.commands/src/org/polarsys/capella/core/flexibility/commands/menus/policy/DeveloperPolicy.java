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
package org.polarsys.capella.core.flexibility.commands.menus.policy;

import org.polarsys.capella.core.flexibility.commands.actions.DefaultAction;
import org.polarsys.capella.core.flexibility.commands.dynamic.tools.InformationActionsProvider;

/**
 */
public class DeveloperPolicy extends AbstractHiddenPolicy {

  public DeveloperPolicy() {
    add(DefaultAction.getId(InformationActionsProvider.Descriptions.class));
    add(DefaultAction.getId(InformationActionsProvider.MustBeTransitioned.class));
    add(DefaultAction.getId(InformationActionsProvider.RetrieveCalls.class));
    add(DefaultAction.getId(InformationActionsProvider.RetrieveFromIdentifierTransitioned.class));
    add(DefaultAction.getId(InformationActionsProvider.RetrieveHelpers.class));
    add(DefaultAction.getId(InformationActionsProvider.RetrieveIDs.class));
    add(DefaultAction.getId(InformationActionsProvider.ShouldNotBeTransitioned.class));
  }

}
