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
import java.util.ListIterator;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.la.CapabilityRealization;


/**
 * SystemComponent helpers
 * 
 */
public class SystemComponentExt {

  /**
   * Checks whether a capability realization is involved with the component
   * 
   * 
   * @param component the component
   * @param capabilityRealization the capability realization
   * @return true if the capability realization is involved with the component
   */
  static public boolean isRealizationInvolved(SystemComponent component, CapabilityRealization capabilityRealization) {
    boolean isRealizationInvolved = false;

    for (SystemComponentCapabilityRealizationInvolvement systemComponentCapabilityRealizationInvolvement : component.getParticipationsInCapabilityRealizations()) {
      if (systemComponentCapabilityRealizationInvolvement.getInvolver().equals(capabilityRealization)) {
        isRealizationInvolved = true;
        break;
      }
    }

    return isRealizationInvolved;
  }

  /**
   * This method removes an involved actor.
   * 
   * @param cpnt the actor in which the capability will not be involved to
   * @param capabilityUseCase_p the non involved capability
   */
  public static void removeInvolvedCapabilityRealisationUseCase(SystemComponent cpnt, CapabilityRealization capabilityRealisation) {
    SystemComponentCapabilityRealizationInvolvement systemCapabilityInvolvement = null;
    ListIterator<SystemComponentCapabilityRealizationInvolvement> it = cpnt.getParticipationsInCapabilityRealizations().listIterator();
    while (it.hasNext()) {
      SystemComponentCapabilityRealizationInvolvement inv = it.next();
      if (inv.getInvolver().equals(capabilityRealisation)) {
        systemCapabilityInvolvement = inv;
      }
    }
    if (systemCapabilityInvolvement != null) {
      ((CapabilityRealization) capabilityRealisation.eContainer()).getOwnedSystemComponentCapabilityRealizations().remove(systemCapabilityInvolvement);
      systemCapabilityInvolvement.destroy();
    }
  }

  /**
   * This method retrieves the involved capabilities.
   * 
   * @param cpnt the actor whose involved capabilities will be retrieved
   * @return the involved capabilities
   */
  public static List<CapabilityRealization> getInvolvedCapabilityRealisations(SystemComponent cpnt) {
    return getInvolvedCapabilityRealisations(cpnt, false);
  }

  /**
   * This method retrieves the involved capabilities.
   * 
   * @param cpnt the actor whose involved capabilities will be retrieved
   * @param onlyGenerated
   * @return the involved capabilities
   */
  public static List<CapabilityRealization> getInvolvedCapabilityRealisations(SystemComponent cpnt, boolean onlyGenerated) {
    List<CapabilityRealization> involvedCapabilities = new ArrayList<CapabilityRealization>();

    for (SystemComponentCapabilityRealizationInvolvement systemCapabilityInvolvement : cpnt.getParticipationsInCapabilityRealizations()) {
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
   * @param cpnt the actor in which the capability will be involved to
   * @param capabilityUseCase_p the involved capability
   */
  public static void addInvolvedCapabilityRealisation(SystemComponent cpnt, CapabilityRealization capabilityRealisationUseCase) {
    if ((cpnt != null) && (capabilityRealisationUseCase != null)) {
      if (!getInvolvedCapabilityRealisations(cpnt).contains(capabilityRealisationUseCase)) {
        SystemComponentCapabilityRealizationInvolvement capabilityInv = CsFactory.eINSTANCE.createSystemComponentCapabilityRealizationInvolvement();
        capabilityInv.setInvolved(cpnt);
        ((CapabilityRealization) capabilityRealisationUseCase.eContainer()).getOwnedSystemComponentCapabilityRealizations().add(capabilityInv);
      }
    }
  }
  
  /**
   * Gets a filtered list of {@link CapabilityRealizationUseCase} from the capella element
   *
   * @param currentComponent the SystemComponent to be filtered
   * @param element the capella element from which list of CapabilityRealizationUseCases to be got
   * @return list of filtered CapabilityRealizationUseCases
   */
  public static List<CapellaElement> getCapabilityRealizationUseCasesFiltered(SystemComponent currentComponent, CapellaElement element) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    for (CapabilityRealization capabilityRealization : CapellaElementExt.getAllCapabilityRealizationInvolvedWith(element)) {
      if (isRealizationInvolved(currentComponent, capabilityRealization))
        continue;
      availableElements.add(capabilityRealization);
    }
    return availableElements;
  }
}
