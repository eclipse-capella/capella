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
package org.polarsys.capella.core.re.activities;

import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.re.policies.merge.CapellaMergePolicy;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DifferencesComputingActivity
    extends org.polarsys.capella.common.re.activities.DifferencesComputingActivity {

  public static final String ID = DifferencesComputingActivity.class.getCanonicalName();

  @Override
  protected IMergePolicy<EObject> createMergePolicy(IContext context) {
    return new CapellaMergePolicy(context);
  }

}
