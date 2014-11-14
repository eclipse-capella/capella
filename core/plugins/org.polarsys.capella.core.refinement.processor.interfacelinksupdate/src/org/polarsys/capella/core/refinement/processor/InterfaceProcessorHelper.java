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
package org.polarsys.capella.core.refinement.processor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.la.ContextInterfaceRealization;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 */
public class InterfaceProcessorHelper {

  /**
   * Add Communication link computed between Component and Exchange item
   */
  public static void addCommunicationLink(Component cpnt_p, List<CommunicationLink> communicationLinksToAdd_p, List<CommunicationLink> communicationLinksToRemove_p) {
    for (CommunicationLink communicationLink : communicationLinksToAdd_p) {
      if (!communicationLinksToRemove_p.contains(communicationLink)) {
        ComponentExt.addCommunicationLink(cpnt_p, communicationLink);
      }
    }
  }

  /**
   * Add Use link computed between Component and Interface
   */
  public static void addUseInterface(Component implementorCpnt_p, List<Interface> usedItf_p, List<Interface> unUsedItf_p) {
    for (Interface itf : usedItf_p) {
      if (!unUsedItf_p.contains(itf)) {
        // Try to retrieve a clone Interface to connect it
        Interface itfTransf = retrieveTransformedInterface(itf);
        if (null != itfTransf)
          ComponentExt.addUsedInterface(implementorCpnt_p, itfTransf);
        else
          ComponentExt.addUsedInterface(implementorCpnt_p, itf);
      }
    }
  }

  /**
   * Add Implementation link computed between Component and Interface
   */
  public static void addImplementationInterface(Component implementorCpnt, List<Interface> implementedItf, List<Interface> unImplementedItf) {
    for (Interface itf : implementedItf) {
      if (!unImplementedItf.contains(itf)) {
        // Try to retrieve a clone Interface to connect it
        Interface itfTransf = retrieveTransformedInterface(itf);
        if (null != itfTransf)
          ComponentExt.addImplementedInterface(implementorCpnt, itfTransf);
        else
          ComponentExt.addImplementedInterface(implementorCpnt, itf);
      }
    }
  }

  /**
   * Add require Interface computed to Component 
   */
  public static void addRequireInterface(Component implementorCpnt, List<Interface> requiredItf_p, List<Interface> unRequiredItf_p) {
    for (Interface itf : requiredItf_p) {
      if (!unRequiredItf_p.contains(itf)) {
    	// Try to retrieve a clone Interface to connect it
        Interface itfTransf = retrieveTransformedInterface(itf);
        if (null != itfTransf)
          ComponentExt.addRequiredInterface(implementorCpnt, itfTransf);
        else
          ComponentExt.addRequiredInterface(implementorCpnt, itf);
      }
    }
  }

  /**
   * Add provide Interface computed to Component 
   */
  public static void addProvideInterface(Component implementorCpnt, List<Interface> providedItf_p, List<Interface> unProvidedItf_p) {
    for (Interface itf : providedItf_p) {
      if (!unProvidedItf_p.contains(itf)) {
        // Try to retrieve a clone Interface to connect it
        Interface itfTransf = retrieveTransformedInterface(itf);
        if (null != itfTransf)
          ComponentExt.addProvidedInterface(implementorCpnt, itfTransf);
        else
          ComponentExt.addProvidedInterface(implementorCpnt, itf);
      }
    }
  }

  /**
   * Remove Communication link computed between Component and Exchange Item
   */
  public static void removeCommunicationLink(Component cpnt_p, List<CommunicationLink> communicationLinksToRemove_p) {
    for (CommunicationLink communicationLink : communicationLinksToRemove_p) {
      ComponentExt.removeCommunicationLink(cpnt_p, communicationLink);
    }
  }

  /**
   * Remove Use link computed between Component and Interface
   */
  public static void removeUseInterface(Component implementorCpnt_p, List<Interface> unUsedItf_p) {
    for (Interface itf : unUsedItf_p) {
      // Try to retrieve a clone Interface to disconnect it
      Interface itfTransf = retrieveTransformedInterface(itf);
      if (null != itfTransf)
        ComponentExt.removeUsedInterface(implementorCpnt_p, itfTransf);
      else
        ComponentExt.removeUsedInterface(implementorCpnt_p, itf);
    }
  }

  /**
   * Remove Implementation link computed between Component and Interface
   */
  public static void removeImplementationInterface(Component implementorCpnt, List<Interface> unImplementedItf_p) {
    for (Interface itf : unImplementedItf_p) {
      // Try to retrieve a clone Interface to disconnect it
      Interface itfTransf = retrieveTransformedInterface(itf);
      if (null != itfTransf)
        ComponentExt.removeImplementedInterface(implementorCpnt, itfTransf);
      else
        ComponentExt.removeImplementedInterface(implementorCpnt, itf);
    }
  }

  /**
   * Remove require Interface computed from Component
   */
  public static void removeRequireInterface(Component implementorCpnt, List<Interface> unRequiredItf_p) {
    for (Interface itf : unRequiredItf_p) {
      // Try to retrieve a clone Interface to disconnect it
      Interface itfTransf = retrieveTransformedInterface(itf);
      if (null != itfTransf)
        ComponentExt.removeRequiredInterface(implementorCpnt, itfTransf);
      else
        ComponentExt.removeRequiredInterface(implementorCpnt, itf);
    }
  }

  /**
   * Remove provide Interface computed from Component
   */
  public static void removeProvideInterface(Component implementorCpnt, List<Interface> unProvidedItf_p) {
    for (Interface itf : unProvidedItf_p) {
      // Try to retrieve a clone Interface to disconnect it
      Interface itfTransf = retrieveTransformedInterface(itf);
      if (null != itfTransf)
        ComponentExt.removeProvidedInterface(implementorCpnt, itfTransf);
      else
        ComponentExt.removeProvidedInterface(implementorCpnt, itf);
    }
  }

  /**
   * Compute communication links to add
   * @return communication links from given Component
   */
  public static List<CommunicationLink> computeCommunicationLinksToAdd(Component implementorCpnt) {
    List<CommunicationLink> communicationLinks = new ArrayList<CommunicationLink>();
    for (Component implementedCpnt : implementorCpnt.getAllocatedComponents()) {
      for (CommunicationLink communicationLink : implementedCpnt.getOwnedCommunicationLinks()) {
        if (!communicationLinks.contains(communicationLink))
          communicationLinks.add(communicationLink);
      }
    }
    return communicationLinks;
  }

  /**
   * Compute used Interfaces to add
   * @return all Interfaces Use by all Components realized by the given Component
   */
  public static List<Interface> computeUsageToAdd(Component implementorCpnt) {
    List<Interface> usedInterfaces = new ArrayList<Interface>();
    for (Component implementedCpnt : implementorCpnt.getAllocatedComponents()) {
      for (Interface itf : ComponentExt.getUsedInterfaces(implementedCpnt)) {
        if (!usedInterfaces.contains(itf))
          usedInterfaces.add(itf);
      }
    }
    return usedInterfaces;
  }

  /**
   * Compute implemented Interfaces to add
   * @return all Interfaces Implemented by all Components realized by the given Component
   */
  public static List<Interface> computeImplementationToAdd(Component implementorCpnt) {
    List<Interface> implementedInterfaces = new ArrayList<Interface>();
    for (Component implementedCpnt : implementorCpnt.getAllocatedComponents()) {
      for (Interface itf : ComponentExt.getImplementedInterfaces(implementedCpnt)) {
        if (!implementedInterfaces.contains(itf))
          implementedInterfaces.add(itf);
      }
    }
    return implementedInterfaces;
  }

  /**
   * Compute required Interfaces to add
   * @return all Interfaces required by all Components realized by the given Component
   */
  public static List<Interface> computeRequireToAdd(Component implementorCpnt_p) {
    List<Interface> requiredItf = new ArrayList<Interface>();
    for (Component implementedCpnt : implementorCpnt_p.getAllocatedComponents()) {
      for (Interface itf : implementedCpnt.getRequiredInterfaces()) {
        if (!requiredItf.contains(itf))
          requiredItf.add(itf);
      }
    }
    return requiredItf;
  }

  /**
   * Compute provided Interfaces to add
   * @return all Interfaces provided by all Components realized by the given Component
   */
  public static List<Interface> computeProvideToAdd(Component implementorCpnt_p) {
    List<Interface> providedItf = new ArrayList<Interface>();
    for (Component implementedLC : implementorCpnt_p.getAllocatedComponents()) {
        for (Interface itf : implementedLC.getProvidedInterfaces()) {
          if (!providedItf.contains(itf))
            providedItf.add(itf);
        }
    }
    return providedItf;
  }

  /**
   * Compute realized communication links to Remove from Component according to the given list
   */
  public static List<CommunicationLink> computeCommunicationLinksToRemove(Component cpnt_p, List<CommunicationLink> realizedCommunicationLinks_p, EClass arch_p) {
    List<CommunicationLink> communicationLinks = new ArrayList<CommunicationLink>();
    for (CommunicationLink communicationLink : cpnt_p.getOwnedCommunicationLinks()) {
      AbstractExchangeItem exchangeItem = communicationLink.getExchangeItem();
      if (null != exchangeItem) {
        if (!EcoreUtil2.isContainedBy(exchangeItem, arch_p)) {
          boolean isContained = false;
          for (CommunicationLink lnk : realizedCommunicationLinks_p) {
            if (exchangeItem.equals(lnk.getExchangeItem())) {
              isContained = true;
            }
          }
          if (!isContained) {
            if (!communicationLinks.contains(communicationLink))
              communicationLinks.add(communicationLink);
          }
        }
      }
    }
    return communicationLinks;
  }

  /**
   * Compute used Interfaces to Remove from Component according to given Interface list
   */
  public static List<Interface> computeUsageToRemove(Component cpnt_p, List<Interface> usedInterfaces_p, EClass arch_p) {
    List<Interface> unUsedInterfaces = new ArrayList<Interface>();
    for (Interface usedItfByCpnt : ComponentExt.getUsedInterfaces(cpnt_p)) {
      if (!EcoreUtil2.isContainedBy(usedItfByCpnt, arch_p)) {
        if (!usedInterfaces_p.contains(usedItfByCpnt)) {
          if (!unUsedInterfaces.contains(usedItfByCpnt))
            unUsedInterfaces.add(usedItfByCpnt);
        }
      }
    }
    return unUsedInterfaces;
  }

  /**
   * Compute implemented Interfaces to Remove from Component according to the given Interface list
   */
  public static List<Interface> computeImplementationToRemove(Component cpnt_p, List<Interface> implementedItf_p, EClass arch_p) {
    List<Interface> unImplementedInterfaces = new ArrayList<Interface>();
    for (Interface implementedItfByCpnt : ComponentExt.getImplementedInterfaces(cpnt_p)) {
      if (!EcoreUtil2.isContainedBy(implementedItfByCpnt, arch_p)) {
        if (!implementedItf_p.contains(implementedItfByCpnt)) {
          if (!unImplementedInterfaces.contains(implementedItfByCpnt))
            unImplementedInterfaces.add(implementedItfByCpnt);
        }
      }
    }
    return unImplementedInterfaces;
  }

  /**
   * Compute required Interfaces to Remove from Component according to given Interface list
   */
  public static List<Interface> computeRequireToRemove(Component cpnt_p, List<Interface> requiredItf_p, EClass arch_p) {
    List<Interface> unRequiredItf = new ArrayList<Interface>();
    for (Interface requiredItfByCpnt : cpnt_p.getRequiredInterfaces()) {
      if (!EcoreUtil2.isContainedBy(requiredItfByCpnt, arch_p)) {
        if (!requiredItf_p.contains(requiredItfByCpnt)) {
          if (!unRequiredItf.contains(requiredItfByCpnt))
            unRequiredItf.add(requiredItfByCpnt);
        }
      }
    }
    return unRequiredItf;
  }

  /**
   * Compute provided Interfaces to Remove from Component according to the given Interface list
   */
  public static List<Interface> computeProvideToRemove(Component cpnt_p, List<Interface> providedItf_p, EClass arch_p) {
    List<Interface> unProvidedItf = new ArrayList<Interface>();
    for (Interface providedItfByCpnt : cpnt_p.getProvidedInterfaces()) {
      if (!EcoreUtil2.isContainedBy(providedItfByCpnt, arch_p)) {
        if (!providedItf_p.contains(providedItfByCpnt)) {
          if (!unProvidedItf.contains(providedItfByCpnt))
            unProvidedItf.add(providedItfByCpnt);
        }
      }
    }
    return unProvidedItf;
  }

  private static Interface retrieveTransformedInterface(Interface srcItf_p) {
    for (AbstractTrace trace : srcItf_p.getIncomingTraces()) {
      if ((trace instanceof ContextInterfaceRealization || trace instanceof LogicalInterfaceRealization)
        && trace.getSourceElement() instanceof Interface)
      {
        return (Interface) trace.getSourceElement();
      }
    }
    return null;
  }
}
