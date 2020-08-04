/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.scope.DependenciesHandlerHelper;
import org.polarsys.capella.common.re.handlers.scope.IDependenciesHandler;
import org.polarsys.capella.core.data.capellamodeller.Library;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.re.project.diffmerge.SkeletonUtil;
import org.polarsys.capella.core.re.project.handlers.ProjectRecHandler;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionSteps;
import org.polarsys.capella.core.transition.common.handlers.activity.IActivityExtender;
import org.polarsys.capella.core.transition.common.handlers.options.DefaultOptionsHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.test.recrpl.ju.RecRplCommandManager;
import org.polarsys.capella.test.recrpl.ju.RecRplTestCase;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * Test whether a Physical Link or a Component Exchange associated to an Actor is located by default under root
 * ComponentPkg, not a Component
 */
public class DependenciesOnSkeleton extends RecRplTestCase {
  public static final String INTERFACE_1 = "35d038a6-cc7e-4c37-8ae9-e8f203454ab6"; //$NON-NLS-1$
  public static final String C_1 = "f5b55163-882a-4692-a814-978066c11390"; //$NON-NLS-1$
  public static final String PL_1 = "72d1855c-543a-432a-a264-2c9b3f9ac6d6"; //$NON-NLS-1$
  public static final String CP_1 = "814727d2-d029-4dc5-ad55-50eaf6d50b74"; //$NON-NLS-1$
  public static final String PP_2 = "6c7d337a-f2e1-4cb1-ae6e-a36f49487342"; //$NON-NLS-1$
  public static final String INTERFACE_IMPLEMENTATION_TO_INTERFACE_1 = "96aadd8a-1d60-43ac-b45f-a7ede6194421"; //$NON-NLS-1$
  public static final String SA_2 = "602d64ce-733c-41ea-bb0c-b67f59cb06c6"; //$NON-NLS-1$
  public static final String ROOT_SYSTEM_FUNCTION = "f27404de-6eb6-4834-98d1-20f4dcb1460d"; //$NON-NLS-1$
  public static final String STRUCTURE = "d78f2b5e-a7f4-4792-beb8-dbbbe1d333e9"; //$NON-NLS-1$
  public static final String SYSTEM = "f2b387ab-4aa7-42d3-9f40-7bb3c77d7704"; //$NON-NLS-1$
  public static final String ACP_1 = "d27163b0-31f0-48e8-a847-737e82beacf3"; //$NON-NLS-1$
  public static final String APP_2 = "44d90b06-9115-49ea-8451-982b474fac3e"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { "dependencies" }); //$NON-NLS-1$
  }

  @Override
  public void test() throws Exception {
    ttTestProjectScope();
    ttSkeletonHelper();
    ttDependencies();
  }

  public void ttSkeletonHelper() {
    SkeletonUtil util = new SkeletonUtil((EObject) getObject(INTERFACE_1));
    Collection<EObject> roots = util.getSkeletonElements();
    assertTrue(!roots.contains(getObject(INTERFACE_1)));
    assertTrue(!roots.contains(getObject(C_1)));
    assertTrue(!roots.contains(getObject(SA_2)));
    assertTrue(!roots.contains(getObject(APP_2)));

    assertTrue(roots.contains(getObject(SYSTEM)));
    assertTrue(roots.contains(getObject(STRUCTURE)));
    assertTrue(roots.contains(getObject(ROOT_SYSTEM_FUNCTION)));
  }

  /**
   * This test ensures that ProjectRecHandler.isProjectCommand is returning the correct value according to the launched
   * command
   */
  public void ttTestProjectScope() {
    
    // When a Create REC from whole content is called, it shall returns true
    RecRplCommandManager.setChecker(new IActivityExtender() {

      @Override
      public IStatus postActivity(IContext context, String activityIdentifier, ActivityParameters activityParams) {
        if (ITransitionSteps.INITIALIZE_TRANSITION.equals(activityIdentifier)) {
          assertTrue(ProjectRecHandler.isProjectCommand(context));
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

    });

    Library library = (Library) ProjectExt.getProject(getObject(SYSTEM));
    createRECWholeContent(library);

    // When a Create REC is called, it shall returns false
    RecRplCommandManager.setChecker(new IActivityExtender() {

      @Override
      public IStatus postActivity(IContext context, String activityIdentifier, ActivityParameters activityParams) {
        if (ITransitionSteps.INITIALIZE_TRANSITION.equals(activityIdentifier)) {
          assertFalse(ProjectRecHandler.isProjectCommand(context));
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

    });

    createREC(getObjects(SYSTEM));
  }

  public void ttDependencies() {
    RecRplCommandManager.setChecker(new IActivityExtender() {

      @Override
      public IStatus postActivity(IContext context, String activityIdentifier, ActivityParameters activityParams) {
        if (ITransitionSteps.INITIALIZE_TRANSITION.equals(activityIdentifier)) {
          // In case of REC from whole library contents, skeleton elements are not included, but it shall be ok to refer
          // them without raising a warning.
          // Ports and interfaces references shall not depends to Skeleton elements
          mustNotDepends(getObject(C_1), getObject(SYSTEM), context);
          mustNotDepends(getObject(CP_1), getObject(SYSTEM), context);
          mustNotDepends(getObject(PL_1), getObject(SYSTEM), context);
          mustNotDepends(getObject(PP_2), getObject(SYSTEM), context);
          mustNotDepends(getObject(INTERFACE_IMPLEMENTATION_TO_INTERFACE_1), getObject(SYSTEM), context);
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

    });

    Library library = (Library) ProjectExt.getProject(getObject(SYSTEM));
    createRECWholeContent(library);
  }

  protected void mustNotDepends(EObject source, EObject notReferenced, IContext context) {
    IPropertyContext propertyContext = ((DefaultOptionsHandler) OptionsHandlerHelper.getInstance(context))
        .getPropertyContext(context, (String) context.get(ITransitionConstants.OPTIONS_SCOPE));
    Collection<EObject> scopeElements = (Collection) propertyContext
        .getCurrentValue(propertyContext.getProperties().getProperty(IReConstants.PROPERTY__SCOPE));
    IDependenciesHandler dp = DependenciesHandlerHelper.getInstance(context);
    assertTrue(!dp.getDependencies(Arrays.asList(source), scopeElements, context).contains(notReferenced));
  }

}
