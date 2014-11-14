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
package org.polarsys.capella.core.model.helpers;

import java.util.ListIterator;

import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 * EPBSComponent helpers
 * 
 */
public class ConfigurationItemExt {

  /**
   * Checks whether the ConfigurationItem has implemented the PhysicalComponent
   * 
   * 
   * @param configItem_p the ConfigurationItem
   * @param pc_p the PhysicalComponent
   * @return true if the ConfigurationItem has implemented the PhysicalComponent
   */
  static public boolean hasImplementedPC(ConfigurationItem configItem_p, PhysicalComponent pc_p) {
    boolean flag = false;
    for (Component cpnt : configItem_p.getAllocatedComponents()) {
      if (cpnt.equals(pc_p)) {
        flag = true;
        break;
      }
    }
    return flag;
  }

  /**
   * Checks whether the ConfigurationItem has implemented the PhysicalComponent
   * 
   * 
   * @param configItem_p the ConfigurationItem
   * @param pc_p the PhysicalComponent
   * @return true if the ConfigurationItem has implemented the PhysicalComponent
   */
  static public boolean hasImplementedAbstractPhysicalArtifacts(ConfigurationItem configItem_p, AbstractPhysicalArtifact apa_p) {
    boolean flag = false;
    for (AbstractPhysicalArtifact cpnt : configItem_p.getAllocatedPhysicalArtifacts()) {
      if (cpnt.equals(apa_p)) {
        flag = true;
        break;
      }
    }
    return flag;
  }
	/**
	 * This method adds a physical component implementation.
	 * 
	 * @param epbsComponent_p component implementing
	 * @param physicalComponent_p component to be implemented
	 */
	public static void addImplementedPhysicalComponent(ConfigurationItem epbsComponent_p, PhysicalComponent physicalComponent_p) {
		PhysicalArtifactRealization impl = EpbsFactory.eINSTANCE.createPhysicalArtifactRealization();
		impl.setSourceElement(epbsComponent_p);
		impl.setTargetElement(physicalComponent_p);
		epbsComponent_p.getOwnedPhysicalArtifactRealizations().add(impl);
	}

  /**
   * This method removes a physical component implementation.
   * 
   * @param configurationItem_p the configuration item who implements the physical component
   * @param physicalComponent_p the implemented physical component
   */
  public static void removeImplementedPhysicalComponent(ConfigurationItem configurationItem_p, PhysicalComponent physicalComponent_p) {
	  PhysicalArtifactRealization implementLink = null;
    ListIterator<PhysicalArtifactRealization> it = configurationItem_p.getOwnedPhysicalArtifactRealizations().listIterator();
    while (it.hasNext()) {
    	PhysicalArtifactRealization lnk = it.next();
      if (lnk.getTargetElement().equals(physicalComponent_p)) {
        implementLink = lnk;
      }
    }

    if (implementLink != null) {
      configurationItem_p.getOwnedPhysicalArtifactRealizations().remove(implementLink);
      implementLink.destroy();
    }
  }
  
 /**
  * Check if the current element is leaf element
  * @param current
  * @return
  */
  public static boolean isLeaf(ConfigurationItem current) {
    if (current == null) {
      return false;
    }
    
    return current.getOwnedConfigurationItems().isEmpty();
  }
}
