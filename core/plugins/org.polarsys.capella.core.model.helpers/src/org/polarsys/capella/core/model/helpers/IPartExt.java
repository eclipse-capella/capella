/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers;

import java.util.Collection;
import java.util.List;

import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;

public interface IPartExt {

  /*
   * Associate the Part (part) to the AbstractType(abstractType) Object given in Parameter and store the Part into
   * Context package in layer given in parameter (componentArchitecture)
   */
  void addPart(AbstractType abstractType, Part part, ComponentArchitecture componentArchitecture);

  /**
   * Retrieve the helper part.componentExchanges returns all component exchanges directly connected to part, or by a
   * component exchange end.
   * 
   * @param part
   * @return
   */
  List<ComponentExchange> getComponentExchanges(Part part);

  /**
   * Returns components related to given parts.
   */
  List<Component> getComponentsOfParts(Collection<? extends Partition> parts);

  /**
   * Returns sub components of the component which are used (have a part).
   */

  List<Part> getSubUsedParts(Part part);

  List<Part> getSubUsedAndDeployedParts(Part part);

  List<Component> getSubUsedAndDeployedComponentsOfPart(Part part);

  List<DeployableElement> getDeployedElements(Part part);

  List<Part> getDeployedParts(Part part);

  /**
   * Return all the deployable elements of given part
   * 
   * @param part
   *          : a model element
   * @return list of deployable element
   */
  List<DeployableElement> getAllDeployableElements(Part part);

  boolean isDeploying(Part partDeployer, Part deployed);

  List<DeploymentTarget> getDeployingElements(Part part);

  /**
   * Return all the deployable components from given component
   * 
   * @param component
   *          : a model element
   * @return : list of deployable Component
   */
  List<Component> getAllDeployableComponents(Component component);

  List<Part> getAllPartsFromBlockArch(BlockArchitecture architecture);

  List<Part> getAllPartsFromPhysicalArchitecture(PhysicalArchitecture blockArch);

  /**
   * Retrieve part ancestors.
   * 
   * @param currentPart
   */
  Collection<Part> getFirstPartAncestors(Part currentPart);

}