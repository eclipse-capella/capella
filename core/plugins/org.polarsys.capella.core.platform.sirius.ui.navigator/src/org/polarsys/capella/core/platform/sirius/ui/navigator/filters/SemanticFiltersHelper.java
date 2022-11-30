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
package org.polarsys.capella.core.platform.sirius.ui.navigator.filters;

import org.eclipse.ui.navigator.ICommonFilterDescriptor;

public class SemanticFiltersHelper {

  /** Filter for .afm file */
  private static final String AFM_FILTER = "capella.project.explorer.filters.metadata"; //$NON-NLS-1$

  /** Filter for .capella file */
  private static final String CAPELLA_FILTER = "capella.project.explorer.filters.capellaModeller"; //$NON-NLS-1$

  /** Filter for .aird file */
  private static final String AIRD_FILTER = "capella.project.explorer.filters.capellaAird"; //$NON-NLS-1$

  /** Filter for emf resources */
  private static final String EMF_FILTER = "capella.project.explorer.filters.EMFResource"; //$NON-NLS-1$

  /** Filter for vp per category */
  private static final String VP_CAT_FILTER = "capella.project.explorer.filters.ViewpointsFolderItem"; //$NON-NLS-1$

  /** Filter for vp per resource */
  private static final String VP_RES_FILTER = "capella.project.explorer.filters.ResourcesFolderItem"; //$NON-NLS-1$

  /** Filter for invalid representation */
  private static final String INVALID_REP_FILTER = "capella.project.explorer.filters.invalidRepresentation"; //$NON-NLS-1$

  /**
   * 
   * @param descriptor
   * @return whether the filter descriptor should be filtered out
   */
  public static boolean isSemanticFilter(ICommonFilterDescriptor descriptor) {
    String id = descriptor.getId();
    if (!descriptor.getId().contains("capella")) {
      return false;
    }
    return !(id.equals(AFM_FILTER) || id.equals(CAPELLA_FILTER) || id.equals(AIRD_FILTER) || id.equals(EMF_FILTER)
        || id.equals(VP_CAT_FILTER) || id.equals(VP_RES_FILTER) || id.equals(INVALID_REP_FILTER));
  }
}
