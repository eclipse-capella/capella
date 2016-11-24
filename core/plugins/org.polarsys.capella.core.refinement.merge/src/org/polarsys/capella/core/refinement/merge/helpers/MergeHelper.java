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
package org.polarsys.capella.core.refinement.merge.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.helpers.interaction.services.ScenarioExt;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.refinement.merge.exception.MergeToolException;
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;
import org.polarsys.capella.core.refinement.merge.utils.LinkEnum;
import org.polarsys.capella.core.refinement.merge.utils.LinkUtils;
import org.polarsys.capella.core.refinement.merge.utils.CapellaCopier;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Utility class in order to perform merge.
 * Note that all these methods suppose that the meta-model is consistent for merge process
 */
public class MergeHelper {

  /**
   * Check whether a given Scenario has been already merged.
   * @param sc the {@link Scenario} to check
   * @return the merged {@link Scenario} whether it exists, <code>null</code> otherwise. 
   */
  public static Scenario hasBeenAlreadyMerged(Scenario sc) {
    
    if (
        null == sc || // null arg 
        !LinkUtils.hasIncomingLinks(sc, LinkEnum.MERGE_LINK)) { // No merge Link
      return null;
    }
    
    return (Scenario) LinkUtils.getIncomingLinkTargets(sc, LinkEnum.MERGE_LINK).get(0);    
  }
 
  /**
   * return, for a given {@link InstanceRole} the list of decomposed ones.
   * Whether the target {@link InstanceRole} was obtained from a merge process,
   * we backward to its originator, otherwise, ir is analyzed
   * @param ir the {@link InstanceRole} to check.
   * @return an empty list whether the the {@link AbstractType} does not represent
   * a decomposable {@link SystemComponent} or more simply a {@link SystemComponent}.
   */
  public static List<InstanceRole> getDecomposedIR(InstanceRole ir) throws MergeToolException {
    
    List<InstanceRole> result = new ArrayList<InstanceRole>();
    InstanceRole instanceRole = null;
    SystemComponent sysComponent0;
    
    //
    // First of all, let's check whether the abstractType is a LogicalComponent
    //
    
    if (!InstanceRoleHelper.isIRrepresentASysyemComponent(ir)) {
      return result;
    }
    
    sysComponent0 = (SystemComponent) InstanceRoleHelper.getAbstractType(ir);
    
    //
    // Let's check ML (it should be only one)
    //
    
    List<TraceableElement> trElts =  
      ScenarioExt.hasLinkOftype(
          ir,
          InteractionPackage.Literals.MERGE_LINK,
          ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES,
          ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT
      )
    ;
    
    switch (trElts.size()) {
      case 0: // No Merge Link
        instanceRole = ir;
        break;
      case 1: // We backward on the originator InstanceRole
        instanceRole = (InstanceRole) trElts.get(0);
        break;
      default: // Something is wrong...
        //TODO Log an error and/or change message
        throw new MergeToolException(MergeMessages.genericInternalError);
    }
    
    SystemComponent sysComponent1 = (SystemComponent) InstanceRoleHelper.getAbstractType(instanceRole);
    
    if (!sysComponent1.equals(sysComponent0)) {
      //TODO Log an error and/or change message
      throw new MergeToolException(MergeMessages.genericInternalError);
    }
    
    //
    // Main job
    //
    
    trElts =  
      ScenarioExt.hasLinkOftype(
          instanceRole,
          InteractionPackage.Literals.REFINEMENT_LINK,
          ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES,
          ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT
      )
    ;
    
    InstanceRole current;
    for (TraceableElement tr : trElts) {
      current = (InstanceRole) tr;
      if (!current.getRepresentedInstance().equals(instanceRole.getRepresentedInstance())) {
        result.add(current);
      }
    }
    
    return result;
  }
   
  /**
   * Add new {@link InstanceRole} to a {@link Scenario} whether the represented part is 
   * not yet available on the Scenario. A {@link MergeLink} is added in case of creation. 
   * @param sc the target {@link Scenario}
   * @param ir the {@link InstanceRole} to add
   * @return the new {@link InstanceRole} if Part was not represented, the corresponding one otherwise
   * @throws MergeToolException
   */
  static public InstanceRole addInstanceRole(Scenario sc, InstanceRole ir) throws MergeToolException {
    
    InstanceRole newIR = null;
    
    newIR = ScenarioHelper.isPartAlreadyRepresented(sc, ir);
    
    if (null == newIR) { // We need to create new IR
      newIR = (InstanceRole) CapellaCopier.copyObject(ir, true);
      // let's add this new InstanceRole to the Scenario
      sc.getOwnedInstanceRoles().add(newIR);
      // Let's create merge link
      addMergeLink(newIR, ir);
    }    

    return newIR;
  }
  
  /**
   * Create a {@link MergeLink} between two {@link CapellaElement} and
   * add it to the given {@link Namespace}. The source, or its first 
   * container of type {@link Namespace} is used as container for this new link. 
   * @param src the source element
   * @param tgt the target element
   * @return the container for the newly created link, <code>null</code> whether any problem occurs.
   * @throws MergeToolException 
   */
  public static Namespace addMergeLink(final CapellaElement src, final CapellaElement tgt) throws MergeToolException {
    return addMergeLink(src, tgt, null);
  }
    
  
  /**
   * Create a {@link MergeLink} between two {@link CapellaElement} and
   * add it to the given {@link Namespace}. If this last ones is null,
   * the first container of the source of type {@link Namespace} is used
   * as container for this new link. 
   * @param src the source element
   * @param tgt the target element
   * @return the container for the newly created link, <code>null</code> whether any problem occurs.
   * @throws MergeToolException 
   */
  public static Namespace addMergeLink(final CapellaElement src, final CapellaElement tgt, final Namespace container) throws MergeToolException {
    
    Namespace owner = container;
    
    MergeLink mergeLink = createMergeLink(src, tgt);
    
    if (null == owner) {
      // We look for the first container of type Namespace available
      
      EClass eNamespace = CapellacorePackage.Literals.NAMESPACE;     
      
      EObject current = src;
      while ( null != current &&  null == owner ) {
        if ( eNamespace.isSuperTypeOf(current.eClass()) ) {
          owner = (Namespace) current;
        }
        current = current.eContainer();
      }

    }   
    
    if (null != owner) {
        owner.getOwnedTraces().add(mergeLink);
    }

    return owner;
  }
  
  /**
   * Create a {@link MergeLink} between two {@link CapellaElement}. 
   * @param src the source element
   * @param tgt the target element
   * @return the created {@link MergeLink}
   * @throws MergeToolException 
   */
  private static MergeLink createMergeLink (final CapellaElement src, final CapellaElement tgt) throws MergeToolException {

    if ( null == src || null == tgt ) {
      throw new MergeToolException(MergeMessages.unableToCreateMergeLinkDueToNullElt);
    }
    
    MergeLink result = InteractionFactory.eINSTANCE.createMergeLink();
    result.setSourceElement(src);
    result.setTargetElement(tgt);

    return result;
  }
  
}
