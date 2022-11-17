/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.compare;

import org.eclipse.emf.diffmerge.ui.sirius.SiriusDifferenceCategoryProvider;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;

/**
 * Capella DifferenceCategoryProvider Populates a DiffMerge category with all filters from the Capella project explorer
 * 
 * @author Erwann Traisnel
 *
 */
public class CapellaDifferenceCategoryProvider extends SiriusDifferenceCategoryProvider {

  /**
   * Contribute Difference Categories
   */
  @Override
  public void provideCategories(EMFDiffNode node_p) {
    super.provideCategories(node_p);
    CapellaDifferenceCategoryUtil.eINSTANCE.provideCapellaCategories(node_p);
  }
}
