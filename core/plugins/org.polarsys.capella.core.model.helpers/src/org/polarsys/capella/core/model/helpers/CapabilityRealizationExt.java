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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 * The CapabilityRealizationUseCase helpers.
 */
public class CapabilityRealizationExt {

  /**
   * This method adds an involved component.
   *
   * @param capability the capability in which the component will be involved in
   * @param component the involved component
   */
  public static void addInvolvedComponent(CapabilityRealization capability, Component component) {
    if (component instanceof AbstractActor) {
      addInvolvedActor(capability, (AbstractActor) component);
    } else if (component instanceof SystemComponent) {
      addInvolvedSystemComponent(capability, (SystemComponent) component);
    }
  }

  /**
   * This method adds an involved actor.
   *
   * @param capabilityRealization the capability realization in which the actor will be involved in
   * @param actor the involved actor
   */
  public static void addInvolvedActor(CapabilityRealization capabilityRealization, AbstractActor actor) {
    if ((capabilityRealization != null) && (actor != null)) {
      if (!getInvolvedActors(capabilityRealization).contains(actor)) {
        ActorCapabilityRealizationInvolvement involvementLnk = CsFactory.eINSTANCE.createActorCapabilityRealizationInvolvement();

        capabilityRealization.getOwnedActorCapabilityRealizations().add(involvementLnk);

        involvementLnk.setInvolver(capabilityRealization);
        involvementLnk.setInvolved(actor);
      }
    }
  }

  /**
   * This method adds an involved component.
   *
   * @param capabilityRealization the capability realization in which the component will be involved in
   * @param systemComponent the involved component
   */
  public static void addInvolvedSystemComponent(CapabilityRealization capabilityRealization, SystemComponent systemComponent) {
    if ((capabilityRealization != null) && (systemComponent != null)) {
      if (!getInvolvedSystemComponents(capabilityRealization).contains(systemComponent)) {
        SystemComponentCapabilityRealizationInvolvement involvementLnk = CsFactory.eINSTANCE.createSystemComponentCapabilityRealizationInvolvement();
    
        capabilityRealization.getOwnedSystemComponentCapabilityRealizations().add(involvementLnk);

        involvementLnk.setInvolver(capabilityRealization);
        involvementLnk.setInvolved(systemComponent);
      }
    }
  }

  /**
   * This method removes an involved component.
   * @param capability the capability in which the component will not be involved in
   * @param component the non involved component
   */
  public static void removeInvolvedComponent(CapabilityRealization capability, Component component) {
    if (component instanceof AbstractActor) {
      removeInvolvedActor(capability, (AbstractActor) component);
    } else if (component instanceof SystemComponent) {
      removeInvolvedSystemComponent(capability, (SystemComponent) component);
    }
  }

  /**
   * This method removes an involved actor.
   * @param capability the capability in which the actor will not be involved in
   * @param actor the non involved actor
   */
  public static void removeInvolvedActor(CapabilityRealization capability, AbstractActor actor) {
    ActorCapabilityRealizationInvolvement actorCapabilityInvolvement = null;
    for (ActorCapabilityRealizationInvolvement involvement : capability.getInvolvedActors()) {
      if (involvement.getInvolved().equals(actor)) {
        actorCapabilityInvolvement = involvement;
      }
    }

    if (actorCapabilityInvolvement != null) {
      actorCapabilityInvolvement.destroy();
    }
  }

  /**
   * This method removes an involved system component.
   * @param capability the capability in which the system component will not be involved in
   * @param systemComponent the non involved system component
   */
  public static void removeInvolvedSystemComponent(CapabilityRealization capability, SystemComponent systemComponent) {
    SystemComponentCapabilityRealizationInvolvement systemComponentCapabilityInvolvement = null;
    for (SystemComponentCapabilityRealizationInvolvement involvement : capability.getInvolvedSystemComponents()) {
      if (involvement.getInvolved().equals(systemComponent)) {
        systemComponentCapabilityInvolvement = involvement;
      }
    }

    if (systemComponentCapabilityInvolvement != null) {
      systemComponentCapabilityInvolvement.destroy();
    }
  }

  /**
   * This method retrieves the involved logical components.
   * @param capability The capability realization whose contributing logical components will be retrieved
   * @return The contributing logical components
   */
  public static List<LogicalComponent> getInvolvedLogicalComponents(CapabilityRealization capability) {
    List<LogicalComponent> contributingComponents = new ArrayList<LogicalComponent>();
    List<SystemComponent> cpntSet = getInvolvedSystemComponents(capability);
    for (SystemComponent cpnt : cpntSet) {
      if (cpnt instanceof LogicalComponent) {
        contributingComponents.add((LogicalComponent) cpnt);
      }
    }
    return contributingComponents;
  }

  /**
   * This method retrieves the involved physical components.
   * @param capability The capability realization whose contributing physical components will be retrieved
   * @return The contributing physical components
   */
  public static List<PhysicalComponent> getInvolvedPhysicalComponents(CapabilityRealization capability) {
    List<PhysicalComponent> contributingComponents = new ArrayList<PhysicalComponent>();
    List<SystemComponent> cpntSet = getInvolvedSystemComponents(capability);
    for (SystemComponent cpnt : cpntSet) {
      if (cpnt instanceof PhysicalComponent) {
        contributingComponents.add((PhysicalComponent) cpnt);
      }
    }
    return contributingComponents;
  }

  /**
   * This method retrieves the involved configuration items.
   * @param capability The capability realization whose contributing configuration items will be retrieved
   * @return The contributing configuration items
   */
  public static List<ConfigurationItem> getInvolvedConfigurationItems(CapabilityRealization capability) {
    List<ConfigurationItem> contributingComponents = new ArrayList<ConfigurationItem>();
    List<SystemComponent> cpntSet = getInvolvedSystemComponents(capability);
    for (SystemComponent cpnt : cpntSet) {
      if (cpnt instanceof ConfigurationItem) {
        contributingComponents.add((ConfigurationItem) cpnt);
      }
    }
    return contributingComponents;
  }

  /**
   * This method retrieves the involved components.
   * @param capability The capability whose contributing system components will be retrieved
   * @return The contributing system components.
   */
  public static List<SystemComponent> getInvolvedSystemComponents(CapabilityRealization capability) {
    List<SystemComponent> contributingComponents = new ArrayList<SystemComponent>();
    List<SystemComponentCapabilityRealizationInvolvement> contributionSet = capability.getInvolvedSystemComponents();
    for (SystemComponentCapabilityRealizationInvolvement contrib : contributionSet) {
      contributingComponents.add((SystemComponent) contrib.getInvolved());
    }
    return contributingComponents;
  }

  /**
   * This method retrieves the involved actors.
   * @param capability the capability whose contributing actors will be retrieved
   * @return the contributing actors
   */
  public static List<AbstractActor> getInvolvedActors(CapabilityRealization capability) {
    List<AbstractActor> contributingComponents = new ArrayList<AbstractActor>();
    List<ActorCapabilityRealizationInvolvement> contributionSet = capability.getInvolvedActors();
    for (ActorCapabilityRealizationInvolvement contrib : contributionSet) {
      InvolvedElement elt = contrib.getInvolved();
      if (elt instanceof AbstractActor) {
        contributingComponents.add((AbstractActor) contrib.getInvolved());
      }
    }
    return contributingComponents;
  }

  /**
   * This method retrieves the involved components.
   * @param capability the capability whose contributing components will be retrieved
   * @return the contributing components
   */
  public static List<Component> getInvolvedComponents(CapabilityRealization capability) {
    List<Component> involvedComponents = new ArrayList<Component>();

    involvedComponents.addAll(getInvolvedActors(capability));
    involvedComponents.addAll(getInvolvedSystemComponents(capability));

    return involvedComponents;
  }
  
  /**
   * Return list of upper CapabilityRealization linked by RefinementLink from  CapabilityRealization given in parameter
   */
  public static List<CapabilityRealization> getParentsCapabilityRealizationLinked(CapabilityRealization srcCapa) {
	  List<CapabilityRealization> upperCapaList = new ArrayList<CapabilityRealization>();

	  for (CapellaElement upperCapa : RefinementLinkExt.getRefinementRelatedTargetElements(srcCapa, InteractionPackage.Literals.ABSTRACT_CAPABILITY)) {
		  if (upperCapa instanceof CapabilityRealization) {
			  upperCapaList.add((CapabilityRealization) upperCapa);
			  upperCapaList.addAll(CapabilityRealizationExt.getParentsCapabilityRealizationLinked((CapabilityRealization) upperCapa));  
		  }
	  }
	  return upperCapaList;
  }

  public static List<LogicalComponent> retrieveLcInvolvedByUpperCapabilityRealization(AbstractCapability currentCapa) {
	  List<LogicalComponent> involvedLcByUpperCapa = new ArrayList<LogicalComponent>();

	  if (currentCapa != null) {
		  for (CapabilityRealization upperCapa : CapabilityRealizationExt.getParentsCapabilityRealizationLinked((CapabilityRealization) currentCapa)) {
			  for (LogicalComponent currentLc : CapabilityRealizationExt.getInvolvedLogicalComponents(upperCapa)) {
				  if (!involvedLcByUpperCapa.contains(currentLc))
					  involvedLcByUpperCapa.add(currentLc);
			  }
		  }
	  }
	  return involvedLcByUpperCapa;
  }

  public static List<PhysicalComponent> retrievePcInvolvedByUpperCapabilityRealization(AbstractCapability currentCapa) {
    List<PhysicalComponent> involvedPcByUpperCapa = new ArrayList<PhysicalComponent>();

    if (currentCapa != null) {
      for (CapabilityRealization upperCapa : CapabilityRealizationExt.getParentsCapabilityRealizationLinked((CapabilityRealization) currentCapa)) {
        for (PhysicalComponent currentPc : CapabilityRealizationExt.getInvolvedPhysicalComponents(upperCapa)) {
          if (!involvedPcByUpperCapa.contains(currentPc))
            involvedPcByUpperCapa.add(currentPc);
        }
      }
    }
    return involvedPcByUpperCapa;
  }
  
  /**
   * used in context
   * @param context
   * @param preSource
   * @param preTarget
   * @return
   */
  public static boolean isComponentTargetAvailableForCapInvolvement(EObject context, EObject preSource, EObject preTarget) {
    if ((null == preSource) || (null == preTarget)) {
      return false;
    }

    if (preSource instanceof Capability) {
      Capability cap = (Capability) preSource;
      EList<ActorCapabilityInvolvement> ownedActInvol = cap.getOwnedActorCapabilityInvolvements();
      if (!ownedActInvol.isEmpty()) {
        if (ownedActInvol.contains(preTarget)) {
          return false;
        }
      }
    } else if (preSource instanceof Mission) {
      Mission mis = (Mission) preSource;
      EList<ActorMissionInvolvement> ownedActInvol = mis.getOwnedActorMissionInvolvements();
      if (!ownedActInvol.isEmpty()) {
        if (ownedActInvol.contains(preTarget)) {
          return false;
        }
      }
    } else if (preSource instanceof CapabilityRealization) {
    	CapabilityRealization capReal = (CapabilityRealization) preSource;
        EList<SystemComponentCapabilityRealizationInvolvement> capRealization = capReal.getOwnedSystemComponentCapabilityRealizations();
        for (SystemComponentCapabilityRealizationInvolvement element : capRealization) {
			InvolvedElement involved = element.getInvolved();
			if (null != involved && involved.equals(preTarget)) {
				return false;
			}
		}
    }

    return true;
  }
  
  /**
   * This method retrieves all the capability realizations from the model.
   * @param currentElement
   * @return List<CapabilityRealization>
   */
  public static List<CapabilityRealization> getAllCapabilityRealizationOfOneLayer(CapellaElement currentElement) {
	List<CapabilityRealization> capabilityRealizationsList = new ArrayList<CapabilityRealization>();
	if(null == currentElement) return capabilityRealizationsList;
	
    BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(currentElement);
    if (null != arch) {
    	Set<EObject> capabilityRealizationsSet = EObjectExt.getAll(arch, LaPackage.Literals.CAPABILITY_REALIZATION);
        for (EObject obj : capabilityRealizationsSet) {
          capabilityRealizationsList.add((CapabilityRealization) obj);
        }	
	}
    
    return capabilityRealizationsList;
  }
}

