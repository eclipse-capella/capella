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
package org.polarsys.capella.core.transition.common.handlers.filter;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem.FilterAction;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This interface will be @Deprecated soon!
 */
public interface IFilterItem extends IHandler {

  String getIdentifier();

  boolean isApplicable(EClass differenceClass);

  String getDescription(IDifference difference);

  boolean isMergeable(EStructuralFeature feature, IContext context);

  boolean isMergeable(IDifference difference, Role role, IContext context);

  boolean isDisplayable(IDifference difference, Role role, IContext context);

  FilterAction getDestinationRole(IDifference difference, Role role, IContext context);

  boolean isReadOnly(IDifference diff, Role role, IContext context);

}
