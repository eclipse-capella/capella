/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.diffmerge.ui.sirius.SiriusDiffMergeLabelProvider;
import org.polarsys.kitalpha.ad.metadata.metadata.Metadata;
import org.polarsys.kitalpha.ad.metadata.metadata.ViewpointReference;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;
import org.polarsys.kitalpha.resourcereuse.model.Resource;


/**
 * A custom label provider for comparisons encompassing Capella elements.
 */
public class CapellaDiffMergeLabelProvider extends SiriusDiffMergeLabelProvider {
  
  /** The instance of this class (singleton pattern) */
  private static CapellaDiffMergeLabelProvider __instance = null;
  
  /**
   * Return the instance of this class (singleton pattern)
   * @return a non-null object
   */
  public static CapellaDiffMergeLabelProvider getInstance() {
    if (__instance == null)
      __instance = new CapellaDiffMergeLabelProvider();
    return __instance;
  }
  
  
  /**
   * Constructor
   */
  public CapellaDiffMergeLabelProvider() {
    // Nothing needed
  }
  
  /**
   * Return a label for the given metadata element
   * @param element_p a non-null element
   * @return a non-null string
   */
  protected String getMetadataText(Metadata element_p) {
    String result = element_p.eClass().getName();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.sirius.SiriusDiffMergeLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element_p) {
    String result = null;
    // ****** AFM
    if (element_p instanceof Metadata) {
      result = getMetadataText((Metadata)element_p);
    } else if (element_p instanceof ViewpointReference) {
      result = getViewpointReferenceText((ViewpointReference)element_p);
    }
    if (result == null)
      result = super.getText(element_p);
    return result;
  }
  
  /**
   * Return a label for the given viewpoint reference element
   * @param element_p a non-null element
   * @return a non-null string
   */
  protected String getViewpointReferenceText(ViewpointReference element_p) {
    String result = null;
    String vpId = element_p.getVpId();
    if (vpId != null) {
      Resource resource = ViewpointManager.getViewpoint(vpId);
      if (resource != null)
        result = resource.getName();
    }
    return result;
  }
  
}
