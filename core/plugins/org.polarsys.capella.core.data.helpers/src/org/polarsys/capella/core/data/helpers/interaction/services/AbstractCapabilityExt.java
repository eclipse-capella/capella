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

package org.polarsys.capella.core.data.helpers.interaction.services;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.Scenario;

/**
 * AbstractCapability helpers
 */
public class AbstractCapabilityExt {

  /**
   * This method adds an extended capability.
   * @param capability The capability source.
   * @param extendedCapability The capability to extend.
   */
  public static void addExtendedCapability(AbstractCapability capability, AbstractCapability extendedCapability) {
    if ((capability != null) && (extendedCapability != null)) {
      if (!capability.getExtendedAbstractCapabilities().contains(extendedCapability)) {
        AbstractCapabilityExtend extend = InteractionFactory.eINSTANCE.createAbstractCapabilityExtend();
        capability.getExtends().add(extend);
        extend.setExtended(extendedCapability);
      }
    }
  }

  /**
   * This method adds an included capability.
   * @param capability The capability source.
   * @param includedCapability The capability to include.
   */
  public static void addIncludedCapability(AbstractCapability capability, AbstractCapability includedCapability) {
    if ((capability != null) && (includedCapability != null)) {
      if (!capability.getIncludedAbstractCapabilities().contains(includedCapability)) {
        AbstractCapabilityInclude include = InteractionFactory.eINSTANCE.createAbstractCapabilityInclude();
        capability.getIncludes().add(include);
        include.setIncluded(includedCapability);
      }
    }
  }

  /**
   * This method adds an inherited capability.
   * @param capability The capability source.
   * @param superCapability The parent capability to add.
   */
  public static void addSuperCapability(AbstractCapability capability, AbstractCapability superCapability) {
    if ((capability != null) && (superCapability != null)) {
      if (!capability.getSuper().contains(superCapability)) {
        AbstractCapabilityGeneralization generalization = InteractionFactory.eINSTANCE.createAbstractCapabilityGeneralization();
        capability.getSuperGeneralizations().add(generalization);
        generalization.setSuper(superCapability);
      }
    }
  }

  /**
   * This method retrieves the extended capabilities.
   * @param capability The capability source.
   * @return The list of extended capabilities.
   * @deprecated use org.polarsys.capella.core.data.interaction.AbstractCapability#getExtendedCapabilityUseCases() instead
   */
  @Deprecated
  public static List<AbstractCapability> getExtendedCapabilities(AbstractCapability capability) {
    return capability.getExtendedAbstractCapabilities();
  }

  /**
   * Gets the list of extended capabilities of the current capability
   * @param currentCapability the capability for which extended capabilities have to be found
   * @return list of extended capabilities
   */
  public static List<AbstractCapability> getExtendHierarchy(AbstractCapability currentCapability) {
    List<AbstractCapability> capabilityList = new ArrayList<>();

    for (AbstractCapabilityExtend extend : currentCapability.getExtends()) {
      AbstractCapability extCap = extend.getExtended();
      if ((extCap != null) && !extCap.equals(currentCapability) && !capabilityList.contains(extCap)) {
        capabilityList.add(extCap);
        capabilityList.addAll(getExtendHierarchy(extCap));
      }
    }

    return capabilityList;
  }

  /**
   * This method retrieves the included capabilities.
   * @param capability The capability source.
   * @return The list of included capabilities.
   * @deprecated use org.polarsys.capella.core.data.interaction.AbstractCapability#getIncludedAbstractCapabilityUseCases() instead
   */
  @Deprecated
  public static List<AbstractCapability> getIncludedCapabilities(AbstractCapability capability) {
    return capability.getIncludedAbstractCapabilities();
  }

  /**
   * Return recursive included capabilities
   * @param capabilityList
   * @param currentCapability
   */
  private static void getIncludedCapabilitiesRecursively(List<AbstractCapability> capabilityList, AbstractCapability currentCapability) {
    EList<AbstractCapabilityInclude> including = currentCapability.getIncludes();
    for (AbstractCapabilityInclude cap : including) {
      AbstractCapability inclusionCap = cap.getIncluded();
      if ((inclusionCap != null) && !inclusionCap.equals(currentCapability) && !capabilityList.contains(inclusionCap)) {
        capabilityList.add(inclusionCap);
        getIncludingCapabilitiesRecursively(capabilityList, inclusionCap);
      }
    }
  }

  /**
   * Gets the list of included capabilities of the current capability
   * @param currentCapability the capability for which included capabilities have to be found
   * @return list of included capabilities
   */
  public static List<AbstractCapability> getIncludedHierarchy(AbstractCapability currentCapability) {
    List<AbstractCapability> capabilityList = new ArrayList<>();

    getIncludedCapabilitiesRecursively(capabilityList, currentCapability);

    return capabilityList;
  }

  /**
   * Return recursive including capabilities
   * @param capabilityList
   * @param currentCapability
   */
  private static void getIncludingCapabilitiesRecursively(List<AbstractCapability> capabilityList, AbstractCapability currentCapability) {
    EList<AbstractCapabilityInclude> including = currentCapability.getIncluding();
    for (AbstractCapabilityInclude cap : including) {
      AbstractCapability inclusionCap = cap.getInclusion();
      if ((inclusionCap != null) && !inclusionCap.equals(currentCapability) && !capabilityList.contains(inclusionCap)) {
        capabilityList.add(inclusionCap);
        getIncludingCapabilitiesRecursively(capabilityList, inclusionCap);
      }
    }
  }

  /**
   * Gets the list of including capabilities of the current capability
   * @param currentCapability the capability for which including capabilities have to be found
   * @return list of including capabilities
   */
  public static List<AbstractCapability> getIncludingHierarchy(AbstractCapability currentCapability) {
    List<AbstractCapability> capabilityList = new ArrayList<>();

    getIncludingCapabilitiesRecursively(capabilityList, currentCapability);

    return capabilityList;
  }

  /**
   * Gets the list of super capabilities of the current capability
   * @param currentCapability the capability for which super capabilities have to be found
   * @return list of super capabilities
   */
  public static List<AbstractCapability> getInheritanceHierarchy(AbstractCapability currentCapability) {
    List<AbstractCapability> capabilityList = new ArrayList<>();

    getInheritanceHierarchyRecursively(capabilityList, currentCapability);

    return capabilityList;
  }

  private static void getInheritanceHierarchyRecursively(List<AbstractCapability> capabilityList, AbstractCapability currentCapability) {
    for (AbstractCapabilityGeneralization generalization : currentCapability.getSuperGeneralizations()) {
      AbstractCapability superCap = generalization.getSuper();
      if ((superCap != null) && !superCap.equals(currentCapability) && !capabilityList.contains(superCap)) {
        capabilityList.add(superCap);
        getInheritanceHierarchyRecursively(capabilityList, superCap);
      }
    }
  }

  /**
   * This method retrieves the scenarios related to the given use case.
   * @param capability the use case whose related scenarios will be retrieved
   * @return the related scenarios
   * @deprecated
   */
  @Deprecated
  public static List<Scenario> getRelatedScenarios(AbstractCapability capability) {
    return capability.getOwnedScenarios();
  }

  /**
   * Gets the list of capabilities related to the current capability (by Inheritance)
   * @param currentCapability the capability for which related capabilities have to be found
   * @return list of related capabilities
   */
  public static List<AbstractCapability> getSuperHierarchy(AbstractCapability currentCapability) {
    List<AbstractCapability> capabilityList = new ArrayList<>();

    capabilityList.addAll(getInheritanceHierarchy(currentCapability));
    /*capabilityList.addAll(getIncludedHierarchy(currentCapability));
    capabilityList.addAll(getExtendHierarchy(currentCapability));*/

    return capabilityList;
  }

  /**
   * Gets the list of sub capabilities of the current capability
   * @param currentCapability the capability for which sub capabilities have to be found
   * @return list of super capabilities
   */
  public static List<AbstractCapability> getSubInheritanceHierarchy(AbstractCapability currentCapability) {
    List<AbstractCapability> capabilityList = new ArrayList<>();

    getSubInheritanceHierarchyRecursively(capabilityList, currentCapability);

    return capabilityList;
  }

  private static void getSubInheritanceHierarchyRecursively(List<AbstractCapability> capabilityList, AbstractCapability currentCapability) {
    for (AbstractCapabilityGeneralization generalization : currentCapability.getSubGeneralizations()) {
      AbstractCapability subCap = generalization.getSub();
      if ((subCap != null) && !subCap.equals(currentCapability) && !capabilityList.contains(subCap)) {
        capabilityList.add(subCap);
        getSubInheritanceHierarchyRecursively(capabilityList, subCap);
      }
    }
  }

  /**
   * This method checks whether the capability has been included in the mission.
   * @param capability the capability included
   * @param mission the mission
   * @return true if the capability has been included in the mission
   */
  public static boolean isIncluded(AbstractCapability capability, Mission mission) {
    boolean isIncluded = false;

    for (CapabilityExploitation capabilityExploitation : mission.getOwnedCapabilityExploitations()) {
      if (capabilityExploitation.getCapability().equals(capability)) {
        isIncluded = true;
        break;
      }
    }

    return isIncluded;
  }

  /**
   * This method checks for Inheritance Relationship between two capabilities and return true if any of the relationships exists.
   * @param currentCapability the first Capability
   * @param capability the second Capability
   * @return if there is an Inheritance Relationship existence
   */
  public static boolean isSuperCapability(AbstractCapability currentCapability, AbstractCapability capability) {
    return getSuperHierarchy(currentCapability).contains(capability);
  }

  /**
   * This method removes an included capability from the specified capability.
   * @param capability The source capability.
   * @param extendedCapability The included capability to remove.
   */
  public static void removeExtendedCapability(AbstractCapability capability, AbstractCapability extendedCapability) {
    AbstractCapabilityExtend extend = null;
    ListIterator<AbstractCapabilityExtend> it = capability.getExtends().listIterator();
    while (it.hasNext()) {
      AbstractCapabilityExtend ext = it.next();
      if (ext.getExtended().equals(extendedCapability)) {
        extend = ext;
      }
    }
    if (extend != null) {
      capability.getExtends().remove(extend);
      extend.destroy();
    }
  }

  /**
   * This method removes an included capability from the specified capability.
   * @param capability The source capability.
   * @param includedCapability The included capability to remove.
   */
  public static void removeIncludedCapability(AbstractCapability capability, AbstractCapability includedCapability) {
    AbstractCapabilityInclude include = null;
    ListIterator<AbstractCapabilityInclude> it = capability.getIncludes().listIterator();
    while (it.hasNext()) {
      AbstractCapabilityInclude inc = it.next();
      if (inc.getIncluded().equals(includedCapability)) {
        include = inc;
      }
    }
    if (include != null) {
      capability.getIncludes().remove(include);
      include.destroy();
    }
  }

  /**
   * This method removes an inherited capability from the specified capability.
   * @param capability The source capability.
   * @param superCapability The parent capability to remove.
   */
  public static void removeSuperCapability(AbstractCapability capability, AbstractCapability superCapability) {
    AbstractCapabilityGeneralization generalization = null;
    ListIterator<AbstractCapabilityGeneralization> it = capability.getSuperGeneralizations().listIterator();
    while (it.hasNext()) {
      AbstractCapabilityGeneralization gen = it.next();
      if (gen.getSuper().equals(superCapability)) {
        generalization = gen;
      }
    }
    if (generalization != null) {
      capability.getSuperGeneralizations().remove(generalization);
      generalization.destroy();
    }
  }
}
