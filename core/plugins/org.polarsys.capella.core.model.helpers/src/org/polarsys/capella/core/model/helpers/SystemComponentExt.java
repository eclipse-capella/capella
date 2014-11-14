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
import java.util.ListIterator;

import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;


/**
 * SystemComponent helpers
 * 
 */
public class SystemComponentExt {

  /**
   * Checks whether a capability realization is involved with the component
   * 
   * 
   * @param component_p the component
   * @param capabilityRealization_p the capability realization
   * @return true if the capability realization is involved with the component
   */
  static public boolean isRealizationInvolved(SystemComponent component_p, CapabilityRealization capabilityRealization_p) {
    boolean isRealizationInvolved = false;

    for (SystemComponentCapabilityRealizationInvolvement systemComponentCapabilityRealizationInvolvement : component_p.getParticipationsInCapabilityRealizations()) {
      if (systemComponentCapabilityRealizationInvolvement.getInvolver().equals(capabilityRealization_p)) {
        isRealizationInvolved = true;
        break;
      }
    }

    return isRealizationInvolved;
  }

  /**
   * This method removes an involved actor.
   * 
   * @param cpnt_p the actor in which the capability will not be involved to
   * @param capabilityUseCase_p the non involved capability
   */
  public static void removeInvolvedCapabilityRealisationUseCase(SystemComponent cpnt_p, CapabilityRealization capabilityRealisation_p) {
    SystemComponentCapabilityRealizationInvolvement systemCapabilityInvolvement = null;
    ListIterator<SystemComponentCapabilityRealizationInvolvement> it = cpnt_p.getParticipationsInCapabilityRealizations().listIterator();
    while (it.hasNext()) {
      SystemComponentCapabilityRealizationInvolvement inv = it.next();
      if (inv.getInvolver().equals(capabilityRealisation_p)) {
        systemCapabilityInvolvement = inv;
      }
    }
    if (systemCapabilityInvolvement != null) {
      ((CapabilityRealization) capabilityRealisation_p.eContainer()).getOwnedSystemComponentCapabilityRealizations().remove(systemCapabilityInvolvement);
      systemCapabilityInvolvement.destroy();
    }
  }

  /**
   * This method retrieves the involved capabilities.
   * 
   * @param cpnt_p the actor whose involved capabilities will be retrieved
   * @return the involved capabilities
   */
  public static List<CapabilityRealization> getInvolvedCapabilityRealisations(SystemComponent cpnt_p) {
    return getInvolvedCapabilityRealisations(cpnt_p, false);
  }

  /**
   * This method retrieves the involved capabilities.
   * 
   * @param cpnt_p the actor whose involved capabilities will be retrieved
   * @param onlyGenerated_p
   * @return the involved capabilities
   */
  public static List<CapabilityRealization> getInvolvedCapabilityRealisations(SystemComponent cpnt_p, boolean onlyGenerated_p) {
    List<CapabilityRealization> involvedCapabilities = new ArrayList<CapabilityRealization>();

    for (SystemComponentCapabilityRealizationInvolvement systemCapabilityInvolvement : cpnt_p.getParticipationsInCapabilityRealizations()) {
      CapabilityRealization capabilityRealizationUseCase = (CapabilityRealization) systemCapabilityInvolvement.getInvolver();
      if (capabilityRealizationUseCase != null) {
        involvedCapabilities.add(capabilityRealizationUseCase);
      }
    }

    return involvedCapabilities;
  }

  /**
   * This method adds an involved capability.
   * 
   * @param cpnt_p the actor in which the capability will be involved to
   * @param capabilityUseCase_p the involved capability
   */
  public static void addInvolvedCapabilityRealisation(SystemComponent cpnt_p, CapabilityRealization capabilityRealisationUseCase_p) {
    if ((cpnt_p != null) && (capabilityRealisationUseCase_p != null)) {
      if (!getInvolvedCapabilityRealisations(cpnt_p).contains(capabilityRealisationUseCase_p)) {
        SystemComponentCapabilityRealizationInvolvement capabilityInv = CsFactory.eINSTANCE.createSystemComponentCapabilityRealizationInvolvement();
        capabilityInv.setInvolved(cpnt_p);
        capabilityInv.setInvolver(capabilityRealisationUseCase_p);
        ((CapabilityRealization) capabilityRealisationUseCase_p.eContainer()).getOwnedSystemComponentCapabilityRealizations().add(capabilityInv);
      }
    }
  }
  
  /**
   * Gets a filtered list of {@link CapabilityRealizationUseCase} from the capella element
   *
   * @param currentComponent_p the SystemComponent to be filtered
   * @param element_p the capella element from which list of CapabilityRealizationUseCases to be got
   * @return list of filtered CapabilityRealizationUseCases
   */
  public static List<CapellaElement> getCapabilityRealizationUseCasesFiltered(SystemComponent currentComponent_p, CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    for (CapabilityRealization capabilityRealization : CapellaElementExt.getAllCapabilityRealizationInvolvedWith(element_p)) {
      if (isRealizationInvolved(currentComponent_p, capabilityRealization))
        continue;
      availableElements.add(capabilityRealization);
    }
    return availableElements;
  }
}
