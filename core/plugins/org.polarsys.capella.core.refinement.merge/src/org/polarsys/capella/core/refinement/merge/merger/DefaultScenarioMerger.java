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
package org.polarsys.capella.core.refinement.merge.merger;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.IConstraintFilter;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.helpers.interaction.services.ScenarioExt;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.refinement.merge.MergeActivator;
import org.polarsys.capella.core.refinement.merge.exception.MergeException;
import org.polarsys.capella.core.refinement.merge.helpers.MergeHelper;
import org.polarsys.capella.core.refinement.merge.helpers.ScenarioHelper;
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;
import org.polarsys.capella.core.refinement.merge.update.UpdateMergedScenario;
import org.polarsys.capella.core.refinement.merge.utils.MergeUtils;
import org.polarsys.capella.core.refinement.merge.validation.MergeConstraintFilter;
import org.polarsys.capella.core.refinement.preferences.services.RefinementPrefServices;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.core.validation.CapellaValidationActivator;

/**
 * A (default) scenario merger implementation
 */
public class DefaultScenarioMerger implements IScenarioMerger {

  protected Scenario _result = null;

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.IScenarioMerger#doMerge(org.polarsys.capella.core.data.interaction.Scenario)
   */
  public Scenario doMerge(final Scenario sc_p) throws MergeException {

    try {

      Scenario previousMergeResult = MergeHelper.hasBeenAlreadyMerged(sc_p);

      _result = null == previousMergeResult ? performMerge(sc_p) : performUpdate(sc_p, previousMergeResult);

      CapabilityRealization cr = (CapabilityRealization) sc_p.eContainer();
      
      // Let's add the new scenario
      HoldingResourceHelper.ensureMoveElement(_result, cr);
      cr.getOwnedScenarios().add(_result);

    } catch (MergeException exception_p) {

      String errDetails = exception_p.getMessage();
      CapabilityRealization cr = (CapabilityRealization) sc_p.eContainer();

      String msg = NLS.bind(
          MergeMessages.mergeErrorTopMessage,
          new Object[] { sc_p.getName(), cr.getName(), errDetails }
      );

      throw new MergeException(msg);
    }

    return _result;
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.IScenarioMerger#postTreatment(org.polarsys.capella.core.data.interaction.Scenario)
   */
  public boolean postTreatment(Scenario sc_p) throws ProcessorException {
    // Do Nothing
    return true;
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.IScenarioMerger#preTreatment(org.polarsys.capella.core.data.interaction.Scenario)
   */
  public boolean preTreatment(final Scenario sc_p) throws ProcessorException {

    boolean result = true;

    
    if (RefinementPrefServices.isPreValidationForMergeActivated()) {
      int severity = validate(sc_p);

      Logger logger = MergeActivator.getDefault().getLogger();
      
      EmbeddedMessage emsg = null;
      
      switch (severity) {
        case Diagnostic.ERROR:
          emsg = new EmbeddedMessage(
              MergeMessages.preValidationRaiseErrors,
              IReportManagerDefaultComponents.REFINEMENT, 
              sc_p
          );
          
          if (RefinementPrefServices.isErrorOnPreValidationStopMerge()) {
            logger.error(emsg);
            result = false;
          } else {
            logger.warn(emsg);
          }
          
          break;
        case Diagnostic.WARNING:
          emsg = new EmbeddedMessage(
              MergeMessages.preValidationRaiseWarnings,
              IReportManagerDefaultComponents.REFINEMENT, 
              sc_p
          );
          logger.warn(emsg);
          break;
        case Diagnostic.INFO:
          emsg = new EmbeddedMessage(
              MergeMessages.preValidationRaiseInfos,
              IReportManagerDefaultComponents.REFINEMENT, 
              sc_p
          );
          logger.info(emsg);
          break;
      }

    }

    return result;
  }

  private int validate(Scenario sc_p) {
    
    IBatchValidator validator = CapellaValidationActivator.getDefault().getCapellaValidatorAdapter().getValidator();

    IConstraintFilter filter = new MergeConstraintFilter();

    validator.addConstraintFilter(filter);
    
    int errorLevel = Diagnostic.OK;
    for (Scenario sc: ScenarioHelper.getRefinedScenarii(sc_p,true)) {
      Diagnostician diagnostician = new Diagnostician();
      Diagnostic diagnostic = diagnostician.validate(sc);
      if ( diagnostic.getSeverity() > errorLevel ) {
        errorLevel = diagnostic.getSeverity();
      }
    }
    
    validator.removeConstraintFilter(filter);
    
    return errorLevel;
  }
  
  
  /**
   * Perform a merge operation from "scratch".
   * @param sc_p the root {@link Scenario} for this merge operation
   * @return result of the merge operation.
   * @throws MergeException
   */
  protected Scenario performMerge(Scenario sc_p) throws MergeException {

    IMerger merger = new RecursiveMergerUML2(sc_p);
    merger.performMerge();

    return merger.getResult();
  }

  /**
   * Perform a merge operation with updating.
   * @param sc_p the root {@link Scenario} for this merge operation
   * @param scm_p the previous merge operation result
   * @return result of the merge operation.
   * @throws MergeException
   */
  @SuppressWarnings("unchecked")
  protected Scenario performUpdate(Scenario sc_p, Scenario scm_p) throws MergeException {

    Scenario newMergedResult = null;

    // First of all, let's create a new merged scenario
    newMergedResult = performMerge(sc_p);

    // Let's update Refinement Link from the logical layer to physical one.
    UpdateMergedScenario update = new UpdateMergedScenario(scm_p);

    update.update(newMergedResult);

    //
    // Let's clean old merged scenario and all connected Link
    //

    List<EObject> objectsToDelete = new ArrayList<EObject>();

    // the old scenario itself
    objectsToDelete.add(scm_p);

    // remove its internal MergeLink

    List<EStructuralFeature> features = ScenarioExt.getElementOfInterestOnScenario();

    for (EStructuralFeature feature : features) {
      objectsToDelete.addAll((List<? extends EObject>) scm_p.eGet(feature));
    }

    MergeUtils.deleteElements(objectsToDelete);

    CapabilityRealization cr = (CapabilityRealization) sc_p.eContainer();
    cr.getOwnedScenarios().remove(scm_p);

    return newMergedResult;
  }
  

  /**
   * @param eContainer_p
   * @param result_p
   */
  public void reconnectInvolvment(CapabilityRealization capability_p, Scenario scenario_p) {
    for (InstanceRole ir : scenario_p.getOwnedInstanceRoles()) {
      AbstractInstance ai = ir.getRepresentedInstance();
      ensureCapabilityInvolvment (capability_p, ai);
    }    
  }
  
  /**
   * @param capability_p
   * @param ai_p
   */
  private void ensureCapabilityInvolvment(CapabilityRealization capability_p, AbstractInstance ai_p) {
    boolean found  = false;
    AbstractType type = ai_p.getAbstractType();
    
    for ( SystemComponentCapabilityRealizationInvolvement sccr : capability_p.getInvolvedSystemComponents()) {
        if (sccr.getInvolved() == type) {
          found = true;
        }
    }
    for ( ActorCapabilityRealizationInvolvement acri : capability_p.getInvolvedActors()) {
      if (acri.getInvolved() == type) {
        found = true;
      }
  }
    
    
    if (!found) {
      SystemComponentCapabilityRealizationInvolvement sccr = CsFactory.eINSTANCE.createSystemComponentCapabilityRealizationInvolvement();
      capability_p.getOwnedSystemComponentCapabilityRealizations().add(sccr);
      sccr.setInvolver(capability_p);
      sccr.setInvolved((InvolvedElement) type);
    }    
  }

}
