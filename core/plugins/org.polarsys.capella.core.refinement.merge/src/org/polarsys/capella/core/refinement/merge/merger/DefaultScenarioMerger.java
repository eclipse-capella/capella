/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
  public Scenario doMerge(final Scenario sc) throws MergeException {

    try {

      Scenario previousMergeResult = MergeHelper.hasBeenAlreadyMerged(sc);

      _result = null == previousMergeResult ? performMerge(sc) : performUpdate(sc, previousMergeResult);

      CapabilityRealization cr = (CapabilityRealization) sc.eContainer();
      
      // Let's add the new scenario
      HoldingResourceHelper.ensureMoveElement(_result, cr);
      cr.getOwnedScenarios().add(_result);

    } catch (MergeException exception) {

      String errDetails = exception.getMessage();
      CapabilityRealization cr = (CapabilityRealization) sc.eContainer();

      String msg = NLS.bind(
          MergeMessages.mergeErrorTopMessage,
          new Object[] { sc.getName(), cr.getName(), errDetails }
      );

      throw new MergeException(msg, exception);
    }

    return _result;
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.IScenarioMerger#postTreatment(org.polarsys.capella.core.data.interaction.Scenario)
   */
  public boolean postTreatment(Scenario sc) throws ProcessorException {
    // Do Nothing
    return true;
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.IScenarioMerger#preTreatment(org.polarsys.capella.core.data.interaction.Scenario)
   */
  public boolean preTreatment(final Scenario sc) throws ProcessorException {

    boolean result = true;

    
    if (RefinementPrefServices.isPreValidationForMergeActivated()) {
      int severity = validate(sc);

      Logger logger = MergeActivator.getDefault().getLogger();
      
      EmbeddedMessage emsg = null;
      
      switch (severity) {
        case Diagnostic.ERROR:
          emsg = new EmbeddedMessage(
              MergeMessages.preValidationRaiseErrors,
              IReportManagerDefaultComponents.REFINEMENT, 
              sc
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
              sc
          );
          logger.warn(emsg);
          break;
        case Diagnostic.INFO:
          emsg = new EmbeddedMessage(
              MergeMessages.preValidationRaiseInfos,
              IReportManagerDefaultComponents.REFINEMENT, 
              sc
          );
          logger.info(emsg);
          break;
      }

    }

    return result;
  }

  private int validate(Scenario sc) {
    
    IBatchValidator validator = CapellaValidationActivator.getDefault().getCapellaValidatorAdapter().getValidator();

    IConstraintFilter filter = new MergeConstraintFilter();

    validator.addConstraintFilter(filter);
    
    int errorLevel = Diagnostic.OK;
    for (Scenario s: ScenarioHelper.getRefinedScenarii(sc,true)) {
      Diagnostician diagnostician = new Diagnostician();
      Diagnostic diagnostic = diagnostician.validate(s);
      if ( diagnostic.getSeverity() > errorLevel ) {
        errorLevel = diagnostic.getSeverity();
      }
    }
    
    validator.removeConstraintFilter(filter);
    
    return errorLevel;
  }
  
  
  /**
   * Perform a merge operation from "scratch".
   * @param sc the root {@link Scenario} for this merge operation
   * @return result of the merge operation.
   * @throws MergeException
   */
  protected Scenario performMerge(Scenario sc) throws MergeException {

    IMerger merger = new RecursiveMergerUML2(sc);
    merger.performMerge();

    return merger.getResult();
  }

  /**
   * Perform a merge operation with updating.
   * @param sc the root {@link Scenario} for this merge operation
   * @param scm the previous merge operation result
   * @return result of the merge operation.
   * @throws MergeException
   */
  @SuppressWarnings("unchecked")
  protected Scenario performUpdate(Scenario sc, Scenario scm) throws MergeException {

    Scenario newMergedResult = null;

    // First of all, let's create a new merged scenario
    newMergedResult = performMerge(sc);

    // Let's update Refinement Link from the logical layer to physical one.
    UpdateMergedScenario update = new UpdateMergedScenario(scm);

    update.update(newMergedResult);

    //
    // Let's clean old merged scenario and all connected Link
    //

    List<EObject> objectsToDelete = new ArrayList<EObject>();

    // the old scenario itself
    objectsToDelete.add(scm);

    // remove its internal MergeLink

    List<EStructuralFeature> features = ScenarioExt.getElementOfInterestOnScenario();

    for (EStructuralFeature feature : features) {
      objectsToDelete.addAll((List<? extends EObject>) scm.eGet(feature));
    }

    MergeUtils.deleteElements(objectsToDelete);

    CapabilityRealization cr = (CapabilityRealization) sc.eContainer();
    cr.getOwnedScenarios().remove(scm);

    return newMergedResult;
  }
  

  /**
   * @param eContainer
   * @param result
   */
  public void reconnectInvolvment(CapabilityRealization capability, Scenario scenario) {
    for (InstanceRole ir : scenario.getOwnedInstanceRoles()) {
      AbstractInstance ai = ir.getRepresentedInstance();
      ensureCapabilityInvolvment (capability, ai);
    }    
  }
  
  /**
   * @param capability
   * @param ai
   */
  private void ensureCapabilityInvolvment(CapabilityRealization capability, AbstractInstance ai) {
    boolean found  = false;
    AbstractType type = ai.getAbstractType();
    
    for ( SystemComponentCapabilityRealizationInvolvement sccr : capability.getInvolvedSystemComponents()) {
        if (sccr.getInvolved() == type) {
          found = true;
        }
    }
    for ( ActorCapabilityRealizationInvolvement acri : capability.getInvolvedActors()) {
      if (acri.getInvolved() == type) {
        found = true;
      }
  }
    
    
    if (!found) {
      SystemComponentCapabilityRealizationInvolvement sccr = CsFactory.eINSTANCE.createSystemComponentCapabilityRealizationInvolvement();
      capability.getOwnedSystemComponentCapabilityRealizations().add(sccr);
      sccr.setInvolved((InvolvedElement) type);
    }    
  }

}
