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
   * @param capability_p the capability in which the component will be involved in
   * @param component_p the involved component
   */
  public static void addInvolvedComponent(CapabilityRealization capability_p, Component component_p) {
    if (component_p instanceof AbstractActor) {
      addInvolvedActor(capability_p, (AbstractActor) component_p);
    } else if (component_p instanceof SystemComponent) {
      addInvolvedSystemComponent(capability_p, (SystemComponent) component_p);
    }
  }

  /**
   * This method adds an involved actor.
   *
   * @param capabilityRealization_p the capability realization in which the actor will be involved in
   * @param actor_p the involved actor
   */
  public static void addInvolvedActor(CapabilityRealization capabilityRealization_p, AbstractActor actor_p) {
    if ((capabilityRealization_p != null) && (actor_p != null)) {
      if (!getInvolvedActors(capabilityRealization_p).contains(actor_p)) {
        ActorCapabilityRealizationInvolvement involvementLnk = CsFactory.eINSTANCE.createActorCapabilityRealizationInvolvement();

        capabilityRealization_p.getOwnedActorCapabilityRealizations().add(involvementLnk);

        involvementLnk.setInvolver(capabilityRealization_p);
        involvementLnk.setInvolved(actor_p);
      }
    }
  }

  /**
   * This method adds an involved component.
   *
   * @param capabilityRealization_p the capability realization in which the component will be involved in
   * @param systemComponent_p the involved component
   */
  public static void addInvolvedSystemComponent(CapabilityRealization capabilityRealization_p, SystemComponent systemComponent_p) {
    if ((capabilityRealization_p != null) && (systemComponent_p != null)) {
      if (!getInvolvedSystemComponents(capabilityRealization_p).contains(systemComponent_p)) {
        SystemComponentCapabilityRealizationInvolvement involvementLnk = CsFactory.eINSTANCE.createSystemComponentCapabilityRealizationInvolvement();
    
        capabilityRealization_p.getOwnedSystemComponentCapabilityRealizations().add(involvementLnk);

        involvementLnk.setInvolver(capabilityRealization_p);
        involvementLnk.setInvolved(systemComponent_p);
      }
    }
  }

  /**
   * This method removes an involved component.
   * @param capability_p the capability in which the component will not be involved in
   * @param component_p the non involved component
   */
  public static void removeInvolvedComponent(CapabilityRealization capability_p, Component component_p) {
    if (component_p instanceof AbstractActor) {
      removeInvolvedActor(capability_p, (AbstractActor) component_p);
    } else if (component_p instanceof SystemComponent) {
      removeInvolvedSystemComponent(capability_p, (SystemComponent) component_p);
    }
  }

  /**
   * This method removes an involved actor.
   * @param capability_p the capability in which the actor will not be involved in
   * @param actor_p the non involved actor
   */
  public static void removeInvolvedActor(CapabilityRealization capability_p, AbstractActor actor_p) {
    ActorCapabilityRealizationInvolvement actorCapabilityInvolvement = null;
    for (ActorCapabilityRealizationInvolvement involvement : capability_p.getInvolvedActors()) {
      if (involvement.getInvolved().equals(actor_p)) {
        actorCapabilityInvolvement = involvement;
      }
    }

    if (actorCapabilityInvolvement != null) {
      actorCapabilityInvolvement.destroy();
    }
  }

  /**
   * This method removes an involved system component.
   * @param capability_p the capability in which the system component will not be involved in
   * @param systemComponent_p the non involved system component
   */
  public static void removeInvolvedSystemComponent(CapabilityRealization capability_p, SystemComponent systemComponent_p) {
    SystemComponentCapabilityRealizationInvolvement systemComponentCapabilityInvolvement = null;
    for (SystemComponentCapabilityRealizationInvolvement involvement : capability_p.getInvolvedSystemComponents()) {
      if (involvement.getInvolved().equals(systemComponent_p)) {
        systemComponentCapabilityInvolvement = involvement;
      }
    }

    if (systemComponentCapabilityInvolvement != null) {
      systemComponentCapabilityInvolvement.destroy();
    }
  }

  /**
   * This method retrieves the involved logical components.
   * @param capability_p The capability realization whose contributing logical components will be retrieved
   * @return The contributing logical components
   */
  public static List<LogicalComponent> getInvolvedLogicalComponents(CapabilityRealization capability_p) {
    List<LogicalComponent> contributingComponents = new ArrayList<LogicalComponent>();
    List<SystemComponent> cpntSet = getInvolvedSystemComponents(capability_p);
    for (SystemComponent cpnt : cpntSet) {
      if (cpnt instanceof LogicalComponent) {
        contributingComponents.add((LogicalComponent) cpnt);
      }
    }
    return contributingComponents;
  }

  /**
   * This method retrieves the involved physical components.
   * @param capability_p The capability realization whose contributing physical components will be retrieved
   * @return The contributing physical components
   */
  public static List<PhysicalComponent> getInvolvedPhysicalComponents(CapabilityRealization capability_p) {
    List<PhysicalComponent> contributingComponents = new ArrayList<PhysicalComponent>();
    List<SystemComponent> cpntSet = getInvolvedSystemComponents(capability_p);
    for (SystemComponent cpnt : cpntSet) {
      if (cpnt instanceof PhysicalComponent) {
        contributingComponents.add((PhysicalComponent) cpnt);
      }
    }
    return contributingComponents;
  }

  /**
   * This method retrieves the involved configuration items.
   * @param capability_p The capability realization whose contributing configuration items will be retrieved
   * @return The contributing configuration items
   */
  public static List<ConfigurationItem> getInvolvedConfigurationItems(CapabilityRealization capability_p) {
    List<ConfigurationItem> contributingComponents = new ArrayList<ConfigurationItem>();
    List<SystemComponent> cpntSet = getInvolvedSystemComponents(capability_p);
    for (SystemComponent cpnt : cpntSet) {
      if (cpnt instanceof ConfigurationItem) {
        contributingComponents.add((ConfigurationItem) cpnt);
      }
    }
    return contributingComponents;
  }

  /**
   * This method retrieves the involved components.
   * @param capability_p The capability whose contributing system components will be retrieved
   * @return The contributing system components.
   */
  public static List<SystemComponent> getInvolvedSystemComponents(CapabilityRealization capability_p) {
    List<SystemComponent> contributingComponents = new ArrayList<SystemComponent>();
    List<SystemComponentCapabilityRealizationInvolvement> contributionSet = capability_p.getInvolvedSystemComponents();
    for (SystemComponentCapabilityRealizationInvolvement contrib : contributionSet) {
      contributingComponents.add((SystemComponent) contrib.getInvolved());
    }
    return contributingComponents;
  }

  /**
   * This method retrieves the involved actors.
   * @param capability_p the capability whose contributing actors will be retrieved
   * @return the contributing actors
   */
  public static List<AbstractActor> getInvolvedActors(CapabilityRealization capability_p) {
    List<AbstractActor> contributingComponents = new ArrayList<AbstractActor>();
    List<ActorCapabilityRealizationInvolvement> contributionSet = capability_p.getInvolvedActors();
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
   * @param capability_p the capability whose contributing components will be retrieved
   * @return the contributing components
   */
  public static List<Component> getInvolvedComponents(CapabilityRealization capability_p) {
    List<Component> involvedComponents = new ArrayList<Component>();

    involvedComponents.addAll(getInvolvedActors(capability_p));
    involvedComponents.addAll(getInvolvedSystemComponents(capability_p));

    return involvedComponents;
  }
  
  /**
   * Return list of upper CapabilityRealization linked by RefinementLink from  CapabilityRealization given in parameter
   */
  public static List<CapabilityRealization> getParentsCapabilityRealizationLinked(CapabilityRealization srcCapa_p) {
	  List<CapabilityRealization> upperCapaList = new ArrayList<CapabilityRealization>();

	  for (CapellaElement upperCapa : RefinementLinkExt.getRefinementRelatedTargetElements(srcCapa_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY)) {
		  if (upperCapa instanceof CapabilityRealization) {
			  upperCapaList.add((CapabilityRealization) upperCapa);
			  upperCapaList.addAll(CapabilityRealizationExt.getParentsCapabilityRealizationLinked((CapabilityRealization) upperCapa));  
		  }
	  }
	  return upperCapaList;
  }

  public static List<LogicalComponent> retrieveLcInvolvedByUpperCapabilityRealization(AbstractCapability currentCapa_p) {
	  List<LogicalComponent> involvedLcByUpperCapa = new ArrayList<LogicalComponent>();

	  if (currentCapa_p != null) {
		  for (CapabilityRealization upperCapa : CapabilityRealizationExt.getParentsCapabilityRealizationLinked((CapabilityRealization) currentCapa_p)) {
			  for (LogicalComponent currentLc : CapabilityRealizationExt.getInvolvedLogicalComponents(upperCapa)) {
				  if (!involvedLcByUpperCapa.contains(currentLc))
					  involvedLcByUpperCapa.add(currentLc);
			  }
		  }
	  }
	  return involvedLcByUpperCapa;
  }

  public static List<PhysicalComponent> retrievePcInvolvedByUpperCapabilityRealization(AbstractCapability currentCapa_p) {
    List<PhysicalComponent> involvedPcByUpperCapa = new ArrayList<PhysicalComponent>();

    if (currentCapa_p != null) {
      for (CapabilityRealization upperCapa : CapabilityRealizationExt.getParentsCapabilityRealizationLinked((CapabilityRealization) currentCapa_p)) {
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
   * @param currentElement_p
   * @return List<CapabilityRealization>
   */
  public static List<CapabilityRealization> getAllCapabilityRealizationOfOneLayer(CapellaElement currentElement_p) {
	List<CapabilityRealization> capabilityRealizationsList = new ArrayList<CapabilityRealization>();
	if(null == currentElement_p) return capabilityRealizationsList;
	
    BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(currentElement_p);
    if (null != arch) {
    	Set<EObject> capabilityRealizationsSet = EObjectExt.getAll(arch, LaPackage.Literals.CAPABILITY_REALIZATION);
        for (EObject obj : capabilityRealizationsSet) {
          capabilityRealizationsList.add((CapabilityRealization) obj);
        }	
	}
    
    return capabilityRealizationsList;
  }
}

