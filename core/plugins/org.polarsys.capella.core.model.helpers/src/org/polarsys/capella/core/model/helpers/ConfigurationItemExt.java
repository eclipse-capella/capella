/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
   * @param configItem the ConfigurationItem
   * @param pc the PhysicalComponent
   * @return true if the ConfigurationItem has implemented the PhysicalComponent
   */
  static public boolean hasImplementedPC(ConfigurationItem configItem, PhysicalComponent pc) {
    boolean flag = false;
    //TODO EPIC
    for (Component cpnt : configItem.getRealizedComponents()) {
      if (cpnt.equals(pc)) {
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
   * @param configItem the ConfigurationItem
   * @param apa the PhysicalComponent
   * @return true if the ConfigurationItem has implemented the PhysicalComponent
   */
  static public boolean hasImplementedAbstractPhysicalArtifacts(ConfigurationItem configItem, AbstractPhysicalArtifact apa) {
    boolean flag = false;
    for (AbstractPhysicalArtifact cpnt : configItem.getAllocatedPhysicalArtifacts()) {
      if (cpnt.equals(apa)) {
        flag = true;
        break;
      }
    }
    return flag;
  }
	/**
	 * This method adds a physical component implementation.
	 * 
	 * @param epbsComponent component implementing
	 * @param physicalComponent component to be implemented
	 */
	public static void addImplementedPhysicalComponent(ConfigurationItem epbsComponent, PhysicalComponent physicalComponent) {
		PhysicalArtifactRealization impl = EpbsFactory.eINSTANCE.createPhysicalArtifactRealization();
		impl.setSourceElement(epbsComponent);
		impl.setTargetElement(physicalComponent);
		epbsComponent.getOwnedPhysicalArtifactRealizations().add(impl);
	}

  /**
   * This method removes a physical component implementation.
   * 
   * @param configurationItem the configuration item who implements the physical component
   * @param physicalComponent the implemented physical component
   */
  public static void removeImplementedPhysicalComponent(ConfigurationItem configurationItem, PhysicalComponent physicalComponent) {
	  PhysicalArtifactRealization implementLink = null;
    ListIterator<PhysicalArtifactRealization> it = configurationItem.getOwnedPhysicalArtifactRealizations().listIterator();
    while (it.hasNext()) {
    	PhysicalArtifactRealization lnk = it.next();
      if (lnk.getTargetElement().equals(physicalComponent)) {
        implementLink = lnk;
      }
    }

    if (implementLink != null) {
      configurationItem.getOwnedPhysicalArtifactRealizations().remove(implementLink);
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
