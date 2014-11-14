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
   * @param capability_p The capability source.
   * @param extendedCapability_p The capability to extend.
   */
  public static void addExtendedCapability(AbstractCapability capability_p, AbstractCapability extendedCapability_p) {
    if ((capability_p != null) && (extendedCapability_p != null)) {
      if (!capability_p.getExtendedAbstractCapabilities().contains(extendedCapability_p)) {
        AbstractCapabilityExtend extend = InteractionFactory.eINSTANCE.createAbstractCapabilityExtend();
        capability_p.getExtends().add(extend);
        extend.setExtended(extendedCapability_p);
      }
    }
  }

  /**
   * This method adds an included capability.
   * @param capability_p The capability source.
   * @param includedCapability_p The capability to include.
   */
  public static void addIncludedCapability(AbstractCapability capability_p, AbstractCapability includedCapability_p) {
    if ((capability_p != null) && (includedCapability_p != null)) {
      if (!capability_p.getIncludedAbstractCapabilities().contains(includedCapability_p)) {
        AbstractCapabilityInclude include = InteractionFactory.eINSTANCE.createAbstractCapabilityInclude();
        capability_p.getIncludes().add(include);
        include.setIncluded(includedCapability_p);
      }
    }
  }

  /**
   * This method adds an inherited capability.
   * @param capability_p The capability source.
   * @param superCapability_p The parent capability to add.
   */
  public static void addSuperCapability(AbstractCapability capability_p, AbstractCapability superCapability_p) {
    if ((capability_p != null) && (superCapability_p != null)) {
      if (!capability_p.getSuper().contains(superCapability_p)) {
        AbstractCapabilityGeneralization generalization = InteractionFactory.eINSTANCE.createAbstractCapabilityGeneralization();
        capability_p.getSuperGeneralizations().add(generalization);
        generalization.setSuper(superCapability_p);
      }
    }
  }

  /**
   * This method retrieves the extended capabilities.
   * @param capability_p The capability source.
   * @return The list of extended capabilities.
   * @deprecated use org.polarsys.capella.core.data.interaction.AbstractCapability#getExtendedCapabilityUseCases() instead
   */
  @Deprecated
  public static List<AbstractCapability> getExtendedCapabilities(AbstractCapability capability_p) {
    return capability_p.getExtendedAbstractCapabilities();
  }

  /**
   * Gets the list of extended capabilities of the current capability
   * @param currentCapability_p the capability for which extended capabilities have to be found
   * @return list of extended capabilities
   */
  static public List<AbstractCapability> getExtendHierarchy(AbstractCapability currentCapability_p) {
    List<AbstractCapability> capabilityList = new ArrayList<AbstractCapability>();

    for (AbstractCapabilityExtend extend : currentCapability_p.getExtends()) {
      AbstractCapability extCap = extend.getExtended();
      if ((extCap != null) && !extCap.equals(currentCapability_p) && !capabilityList.contains(extCap)) {
        capabilityList.add(extCap);
        capabilityList.addAll(getExtendHierarchy(extCap));
      }
    }

    return capabilityList;
  }

  /**
   * This method retrieves the included capabilities.
   * @param capability_p The capability source.
   * @return The list of included capabilities.
   * @deprecated use org.polarsys.capella.core.data.interaction.AbstractCapability#getIncludedAbstractCapabilityUseCases() instead
   */
  @Deprecated
  public static List<AbstractCapability> getIncludedCapabilities(AbstractCapability capability_p) {
    return capability_p.getIncludedAbstractCapabilities();
  }

  /**
   * Return recursive included capabilities
   * @param capabilityList_p
   * @param currentCapability_p
   */
  static private void getIncludedCapabilitiesRecursively(List<AbstractCapability> capabilityList_p, AbstractCapability currentCapability_p) {
    EList<AbstractCapabilityInclude> including = currentCapability_p.getIncludes();
    for (AbstractCapabilityInclude cap : including) {
      AbstractCapability inclusionCap = cap.getIncluded();
      if ((inclusionCap != null) && !inclusionCap.equals(currentCapability_p) && !capabilityList_p.contains(inclusionCap)) {
        capabilityList_p.add(inclusionCap);
        getIncludingCapabilitiesRecursively(capabilityList_p, inclusionCap);
      }
    }
  }

  /**
   * Gets the list of included capabilities of the current capability
   * @param currentCapability_p the capability for which included capabilities have to be found
   * @return list of included capabilities
   */
  static public List<AbstractCapability> getIncludedHierarchy(AbstractCapability currentCapability_p) {
    List<AbstractCapability> capabilityList = new ArrayList<AbstractCapability>();

    getIncludedCapabilitiesRecursively(capabilityList, currentCapability_p);

    return capabilityList;
  }

  /**
   * Return recursive including capabilities
   * @param capabilityList_p
   * @param currentCapability_p
   */
  static private void getIncludingCapabilitiesRecursively(List<AbstractCapability> capabilityList_p, AbstractCapability currentCapability_p) {
    EList<AbstractCapabilityInclude> including = currentCapability_p.getIncluding();
    for (AbstractCapabilityInclude cap : including) {
      AbstractCapability inclusionCap = cap.getInclusion();
      if ((inclusionCap != null) && !inclusionCap.equals(currentCapability_p) && !capabilityList_p.contains(inclusionCap)) {
        capabilityList_p.add(inclusionCap);
        getIncludingCapabilitiesRecursively(capabilityList_p, inclusionCap);
      }
    }
  }

  /**
   * Gets the list of including capabilities of the current capability
   * @param currentCapability_p the capability for which including capabilities have to be found
   * @return list of including capabilities
   */
  static public List<AbstractCapability> getIncludingHierarchy(AbstractCapability currentCapability_p) {
    List<AbstractCapability> capabilityList = new ArrayList<AbstractCapability>();

    getIncludingCapabilitiesRecursively(capabilityList, currentCapability_p);

    return capabilityList;
  }

  /**
   * Gets the list of super capabilities of the current capability
   * @param currentCapability_p the capability for which super capabilities have to be found
   * @return list of super capabilities
   */
  static public List<AbstractCapability> getInheritanceHierarchy(AbstractCapability currentCapability_p) {
    List<AbstractCapability> capabilityList = new ArrayList<AbstractCapability>();

    getInheritanceHierarchyRecursively(capabilityList, currentCapability_p);

    return capabilityList;
  }

  static private void getInheritanceHierarchyRecursively(List<AbstractCapability> capabilityList_p, AbstractCapability currentCapability_p) {
    for (AbstractCapabilityGeneralization generalization : currentCapability_p.getSuperGeneralizations()) {
      AbstractCapability superCap = generalization.getSuper();
      if ((superCap != null) && !superCap.equals(currentCapability_p) && !capabilityList_p.contains(superCap)) {
        capabilityList_p.add(superCap);
        getInheritanceHierarchyRecursively(capabilityList_p, superCap);
      }
    }
  }

  /**
   * This method retrieves the scenarios related to the given use case.
   * @param capability_p the use case whose related scenarios will be retrieved
   * @return the related scenarios
   * @deprecated
   */
  @Deprecated
  public static List<Scenario> getRelatedScenarios(AbstractCapability capability_p) {
    return capability_p.getOwnedScenarios();
  }

  /**
   * Gets the list of capabilities related to the current capability (by Inheritance)
   * @param currentCapability_p the capability for which related capabilities have to be found
   * @return list of related capabilities
   */
  static public List<AbstractCapability> getSuperHierarchy(AbstractCapability currentCapability_p) {
    List<AbstractCapability> capabilityList = new ArrayList<AbstractCapability>();

    capabilityList.addAll(getInheritanceHierarchy(currentCapability_p));
    /*capabilityList.addAll(getIncludedHierarchy(currentCapability_p));
    capabilityList.addAll(getExtendHierarchy(currentCapability_p));*/

    return capabilityList;
  }

  /**
   * Gets the list of sub capabilities of the current capability
   * @param currentCapability_p the capability for which sub capabilities have to be found
   * @return list of super capabilities
   */
  static public List<AbstractCapability> getSubInheritanceHierarchy(AbstractCapability currentCapability_p) {
    List<AbstractCapability> capabilityList = new ArrayList<AbstractCapability>();

    getSubInheritanceHierarchyRecursively(capabilityList, currentCapability_p);

    return capabilityList;
  }

  static private void getSubInheritanceHierarchyRecursively(List<AbstractCapability> capabilityList_p, AbstractCapability currentCapability_p) {
    for (AbstractCapabilityGeneralization generalization : currentCapability_p.getSubGeneralizations()) {
      AbstractCapability subCap = generalization.getSub();
      if ((subCap != null) && !subCap.equals(currentCapability_p) && !capabilityList_p.contains(subCap)) {
        capabilityList_p.add(subCap);
        getInheritanceHierarchyRecursively(capabilityList_p, subCap);
      }
    }
  }

  /**
   * This method checks whether the capability has been included in the mission.
   * @param capability_p the capability included
   * @param mission_p the mission
   * @return true if the capability has been included in the mission
   */
  static public boolean isIncluded(AbstractCapability capability_p, Mission mission_p) {
    boolean isIncluded = false;

    for (CapabilityExploitation capabilityExploitation : mission_p.getOwnedCapabilityExploitations()) {
      if (capabilityExploitation.getCapability().equals(capability_p)) {
        isIncluded = true;
        break;
      }
    }

    return isIncluded;
  }

  /**
   * This method checks for Inheritance Relationship between two capabilities and return true if any of the relationships exists.
   * @param currentCapability_p the first Capability
   * @param capability_p the second Capability
   * @return if there is an Inheritance Relationship existence
   */
  static public boolean isSuperCapability(AbstractCapability currentCapability_p, AbstractCapability capability_p) {
    return getSuperHierarchy(currentCapability_p).contains(capability_p);
  }

  /**
   * This method removes an included capability from the specified capability.
   * @param capability_p The source capability.
   * @param extendedCapability_p The included capability to remove.
   */
  public static void removeExtendedCapability(AbstractCapability capability_p, AbstractCapability extendedCapability_p) {
    AbstractCapabilityExtend extend = null;
    ListIterator<AbstractCapabilityExtend> it = capability_p.getExtends().listIterator();
    while (it.hasNext()) {
      AbstractCapabilityExtend ext = it.next();
      if (ext.getExtended().equals(extendedCapability_p)) {
        extend = ext;
      }
    }
    if (extend != null) {
      capability_p.getExtends().remove(extend);
      extend.destroy();
    }
  }

  /**
   * This method removes an included capability from the specified capability.
   * @param capability_p The source capability.
   * @param includedCapability_p The included capability to remove.
   */
  public static void removeIncludedCapability(AbstractCapability capability_p, AbstractCapability includedCapability_p) {
    AbstractCapabilityInclude include = null;
    ListIterator<AbstractCapabilityInclude> it = capability_p.getIncludes().listIterator();
    while (it.hasNext()) {
      AbstractCapabilityInclude inc = it.next();
      if (inc.getIncluded().equals(includedCapability_p)) {
        include = inc;
      }
    }
    if (include != null) {
      capability_p.getIncludes().remove(include);
      include.destroy();
    }
  }

  /**
   * This method removes an inherited capability from the specified capability.
   * @param capability_p The source capability.
   * @param superCapability_p The parent capability to remove.
   */
  public static void removeSuperCapability(AbstractCapability capability_p, AbstractCapability superCapability_p) {
    AbstractCapabilityGeneralization generalization = null;
    ListIterator<AbstractCapabilityGeneralization> it = capability_p.getSuperGeneralizations().listIterator();
    while (it.hasNext()) {
      AbstractCapabilityGeneralization gen = it.next();
      if (gen.getSuper().equals(superCapability_p)) {
        generalization = gen;
      }
    }
    if (generalization != null) {
      capability_p.getSuperGeneralizations().remove(generalization);
      generalization.destroy();
    }
  }
}
