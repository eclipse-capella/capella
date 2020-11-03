/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.recrpl.ju.testcases;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Assert;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionSteps;
import org.polarsys.capella.core.transition.common.handlers.activity.IActivityExtender;
import org.polarsys.capella.core.transition.common.handlers.options.DefaultOptionsHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.test.recrpl.ju.RecRplCommandManager;
import org.polarsys.capella.test.recrpl.ju.handlers.scope.ExternalInclusion;
import org.polarsys.capella.test.recrpl.ju.model.Re;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class CreateRPL_SharedElements_ExternalResource extends Re {

  @Override
  public void performTest() throws Exception {

    ExternalInclusion.setEnabled(true);
    EObject context = getObject(LC_2);
    Resource resource = ExternalInclusion.getExternalResource(context);

    // Create a REC with 1 LC linked to Interfaces
    RecRplCommandManager.setChecker(checkCreate);
    CatalogElement REC = createREC(getObjects(LC_2));
    mustNotReference(REC, resource.getEObject(ExternalInclusion.ACTOR_1));
    mustNotReference(REC, resource.getEObject(ExternalInclusion.ACTOR_2));
    mustNotReference(REC, resource.getEObject(ExternalInclusion.ACTOR_3));

    // Create a RPL, elements should be created, RPL element should be linked to EI, but not the RPL
    RecRplCommandManager.setChecker(checkCreate);
    CatalogElement RPL = createReplica(getObjects(LF1), REC);
    mustNotReference(RPL, resource.getEObject(ExternalInclusion.ACTOR_1));
    mustNotReference(RPL, resource.getEObject(ExternalInclusion.ACTOR_2));
    mustNotReference(RPL, resource.getEObject(ExternalInclusion.ACTOR_3));

    ExternalInclusion.setEnabled(false);

  }

  IActivityExtender checkCreate = new IActivityExtender() {

    @Override
    public IStatus postActivity(IContext context, String activityIdentifier, ActivityParameters activityParams) {
      if (ITransitionSteps.INITIALIZE_TRANSITION.equals(activityIdentifier)) {
        IPropertyContext propertyContext = ((DefaultOptionsHandler) OptionsHandlerHelper.getInstance(context))
            .getPropertyContext(context, (String) context.get(ITransitionConstants.OPTIONS_SCOPE));

        EObject object = getObject(LC_2);

        if (ExternalInclusion.isEnabled()) {
          Collection<EObject> shared = (Collection<EObject>) propertyContext
              .getCurrentValue(propertyContext.getProperties().getProperty(IReConstants.PROPERTY__SHARED_ELEMENTS));
          Collection<EObject> invalid = (Collection<EObject>) propertyContext.getCurrentValue(
              propertyContext.getProperties().getProperty(IReConstants.PROPERTY__INVALID_SHARED_ELEMENTS));

          Resource resource = ExternalInclusion.getExternalResource(object);

          Assert.assertTrue("Actor_1 should be shared",
              shared.contains(resource.getEObject(ExternalInclusion.ACTOR_1)));
          Assert.assertTrue("Actor_2 should be shared",
              shared.contains(resource.getEObject(ExternalInclusion.ACTOR_2)));
          Assert.assertTrue("Actor_3 should be shared",
              shared.contains(resource.getEObject(ExternalInclusion.ACTOR_3)));

          Assert.assertTrue("Actor_1 should not be invalid",
              !invalid.contains(resource.getEObject(ExternalInclusion.ACTOR_1)));
          Assert.assertTrue("Actor_2 should not be invalid",
              !invalid.contains(resource.getEObject(ExternalInclusion.ACTOR_2)));
          Assert.assertTrue("Actor_3 should be invalid",
              invalid.contains(resource.getEObject(ExternalInclusion.ACTOR_3)));

        }

      }
      return Status.OK_STATUS;
    }

    @Override
    public IStatus init(IContext context) {
      return Status.OK_STATUS;
    }

    @Override
    public IStatus dispose(IContext context) {
      return Status.OK_STATUS;
    }

    @Override
    public IStatus preActivity(IContext context, String activityIdentifier, ActivityParameters activityParams) {
      return Status.OK_STATUS;
    }

  };
}
