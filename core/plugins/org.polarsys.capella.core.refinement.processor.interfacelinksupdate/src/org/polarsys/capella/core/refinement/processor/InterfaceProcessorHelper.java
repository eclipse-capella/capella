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
import static org.polarsys.capella.core.model.helpers.ModelHelpers.ComponentExt;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 */
public class InterfaceProcessorHelper {

  /**
   * Add Communication link computed between Component and Exchange item
   */
  public static void addCommunicationLink(Component cpnt, List<CommunicationLink> communicationLinksToAdd, List<CommunicationLink> communicationLinksToRemove) {
    for (CommunicationLink communicationLink : communicationLinksToAdd) {
      if (!communicationLinksToRemove.contains(communicationLink)) {
        ComponentExt.addCommunicationLink(cpnt, communicationLink);
      }
    }
  }

  /**
   * Add Use link computed between Component and Interface
   */
  public static void addUseInterface(Component implementorCpnt, List<Interface> usedItf, List<Interface> unUsedItf) {
    for (Interface itf : usedItf) {
      if (!unUsedItf.contains(itf)) {
        // Try to retrieve a clone Interface to connect it
        Interface itfTransf = retrieveTransformedInterface(itf);
        if (null != itfTransf)
          ComponentExt.addUsedInterface(implementorCpnt, itfTransf);
        else
          ComponentExt.addUsedInterface(implementorCpnt, itf);
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
  public static void addRequireInterface(Component implementorCpnt, List<Interface> requiredItf, List<Interface> unRequiredItf) {
    for (Interface itf : requiredItf) {
      if (!unRequiredItf.contains(itf)) {
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
  public static void addProvideInterface(Component implementorCpnt, List<Interface> providedItf, List<Interface> unProvidedItf) {
    for (Interface itf : providedItf) {
      if (!unProvidedItf.contains(itf)) {
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
  public static void removeCommunicationLink(Component cpnt, List<CommunicationLink> communicationLinksToRemove) {
    for (CommunicationLink communicationLink : communicationLinksToRemove) {
      ComponentExt.removeCommunicationLink(cpnt, communicationLink);
    }
  }

  /**
   * Remove Use link computed between Component and Interface
   */
  public static void removeUseInterface(Component implementorCpnt, List<Interface> unUsedItf) {
    for (Interface itf : unUsedItf) {
      // Try to retrieve a clone Interface to disconnect it
      Interface itfTransf = retrieveTransformedInterface(itf);
      if (null != itfTransf)
        ComponentExt.removeUsedInterface(implementorCpnt, itfTransf);
      else
        ComponentExt.removeUsedInterface(implementorCpnt, itf);
    }
  }

  /**
   * Remove Implementation link computed between Component and Interface
   */
  public static void removeImplementationInterface(Component implementorCpnt, List<Interface> unImplementedItf) {
    for (Interface itf : unImplementedItf) {
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
  public static void removeRequireInterface(Component implementorCpnt, List<Interface> unRequiredItf) {
    for (Interface itf : unRequiredItf) {
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
  public static void removeProvideInterface(Component implementorCpnt, List<Interface> unProvidedItf) {
    for (Interface itf : unProvidedItf) {
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
  public static List<Interface> computeRequireToAdd(Component implementorCpnt) {
    List<Interface> requiredItf = new ArrayList<Interface>();
    for (Component implementedCpnt : implementorCpnt.getAllocatedComponents()) {
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
  public static List<Interface> computeProvideToAdd(Component implementorCpnt) {
    List<Interface> providedItf = new ArrayList<Interface>();
    for (Component implementedLC : implementorCpnt.getAllocatedComponents()) {
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
  public static List<CommunicationLink> computeCommunicationLinksToRemove(Component cpnt, List<CommunicationLink> realizedCommunicationLinks, EClass arch) {
    List<CommunicationLink> communicationLinks = new ArrayList<CommunicationLink>();
    for (CommunicationLink communicationLink : cpnt.getOwnedCommunicationLinks()) {
      AbstractExchangeItem exchangeItem = communicationLink.getExchangeItem();
      if (null != exchangeItem) {
        if (!EcoreUtil2.isContainedBy(exchangeItem, arch)) {
          boolean isContained = false;
          for (CommunicationLink lnk : realizedCommunicationLinks) {
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
  public static List<Interface> computeUsageToRemove(Component cpnt, List<Interface> usedInterfaces, EClass arch) {
    List<Interface> unUsedInterfaces = new ArrayList<Interface>();
    for (Interface usedItfByCpnt : ComponentExt.getUsedInterfaces(cpnt)) {
      if (!EcoreUtil2.isContainedBy(usedItfByCpnt, arch)) {
        if (!usedInterfaces.contains(usedItfByCpnt)) {
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
  public static List<Interface> computeImplementationToRemove(Component cpnt, List<Interface> implementedItf, EClass arch) {
    List<Interface> unImplementedInterfaces = new ArrayList<Interface>();
    for (Interface implementedItfByCpnt : ComponentExt.getImplementedInterfaces(cpnt)) {
      if (!EcoreUtil2.isContainedBy(implementedItfByCpnt, arch)) {
        if (!implementedItf.contains(implementedItfByCpnt)) {
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
  public static List<Interface> computeRequireToRemove(Component cpnt, List<Interface> requiredItf, EClass arch) {
    List<Interface> unRequiredItf = new ArrayList<Interface>();
    for (Interface requiredItfByCpnt : cpnt.getRequiredInterfaces()) {
      if (!EcoreUtil2.isContainedBy(requiredItfByCpnt, arch)) {
        if (!requiredItf.contains(requiredItfByCpnt)) {
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
  public static List<Interface> computeProvideToRemove(Component cpnt, List<Interface> providedItf, EClass arch) {
    List<Interface> unProvidedItf = new ArrayList<Interface>();
    for (Interface providedItfByCpnt : cpnt.getProvidedInterfaces()) {
      if (!EcoreUtil2.isContainedBy(providedItfByCpnt, arch)) {
        if (!providedItf.contains(providedItfByCpnt)) {
          if (!unProvidedItf.contains(providedItfByCpnt))
            unProvidedItf.add(providedItfByCpnt);
        }
      }
    }
    return unProvidedItf;
  }

  private static Interface retrieveTransformedInterface(Interface srcItf) {
    for (AbstractTrace trace : srcItf.getIncomingTraces()) {
      if ((trace instanceof ContextInterfaceRealization || trace instanceof LogicalInterfaceRealization)
        && trace.getSourceElement() instanceof Interface)
      {
        return (Interface) trace.getSourceElement();
      }
    }
    return null;
  }
}
