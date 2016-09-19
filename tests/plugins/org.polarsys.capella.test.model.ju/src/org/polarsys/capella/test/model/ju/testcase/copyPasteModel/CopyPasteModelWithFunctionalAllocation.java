/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.testcase.copyPasteModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.UnresolvedProxyCrossReferencer;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.flexibility.properties.loader.PropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCopyToClipboardCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaPasteCommand;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;

public class CopyPasteModelWithFunctionalAllocation extends BasicTestCase {
  public static final String MODEL_NAME = "miscmodel"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() throws Exception {
    //
    // Data creation.
    //
    ICapellaModel model = getTestModel(MODEL_NAME);
    Session session = getSessionForTestModel(MODEL_NAME);
    TransactionalEditingDomain ted = session.getTransactionalEditingDomain();
    final Project project = ((ICapellaModel) model).getProject(ted);
    // Create an OperationalActivity and an OperationalActor allocating this OperationalActivity.
    final String actor1Name = "Actor1";
    final String activity1Name = "Activity1";

    final OperationalActor[] actor1 = { null };

    ExecutionManager executionManager = TestHelper.getExecutionManager(project);
    executionManager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        // Add an Actor1 at OA level
        actor1[0] = OaFactory.eINSTANCE.createOperationalActor(actor1Name);
        ModelQueryHelper.getOperationalEntityPkg(project).getOwnedEntities().add(actor1[0]);
        CsServices.getService().createRepresentingPartIfNone(actor1[0]);

        // Add an OperationalActivity
        OperationalActivity activity1 = OaFactory.eINSTANCE.createOperationalActivity(activity1Name);
        ModelQueryHelper.getRootOperationalActivity(project).getOwnedFunctions().add(activity1);
        // Allocate the activity to the actor
        FaServices.getFaServices().allocateToComponent(activity1, actor1[0]);

      }
    });
    // Perform an an Actor Transition (with functions).
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL, Boolean.TRUE);
    List<Object> oa = Collections.singletonList((Object) ModelQueryHelper.getOperationalAnalysis(project));
    executionManager
        .execute(TransitionCommandHelper.getInstance().getOE2ActorTransitionCommand(oa, new NullProgressMonitor()));

    //
    // Copy paste the transitioned actor.
    //
    // Copy
    Actor systemActor = (Actor) EcoreUtil.getObjectByType(getSourceElements(actor1[0]), CtxPackage.Literals.ACTOR);
    CapellaCommonNavigator capellaProjectView = (CapellaCommonNavigator) PlatformUI.getWorkbench()
        .getActiveWorkbenchWindow().getActivePage().showView(CapellaCommonNavigator.ID);
    CapellaCopyToClipboardCommand capellaCopyToClipboardCommand = new CapellaCopyToClipboardCommand(ted,
        Collections.singleton(systemActor), capellaProjectView.getCommonViewer());
    ted.getCommandStack().execute(capellaCopyToClipboardCommand);
    // Paste
    ActorPkg actorPkg = ModelQueryHelper.getSystemActorPkg(project);
    List<EObject> contentBeforePaste = new ArrayList<EObject>(actorPkg.eContents());
    CapellaPasteCommand capellaPasteCommand = new CapellaPasteCommand(ted,
        actorPkg, null, CommandParameter.NO_INDEX);
    ted.getCommandStack().execute(capellaPasteCommand);

    //
    // Checks
    //
    List<EObject> addedElements = new ArrayList<EObject>(actorPkg.eContents());
    addedElements.removeAll(contentBeforePaste);
    assertTrue("1 additional element of type Actor is expected in Actors package", addedElements.size() == 1);
    Actor pastedActor = (Actor) EcoreUtil.getObjectByType(addedElements, CtxPackage.Literals.ACTOR);

    assertTrue("Feature Owned Traces must be empty in copied Actor", pastedActor.getOwnedTraces().isEmpty());
    assertTrue("Feature Owned Functional Allocation must be empty in copied Actor",
        pastedActor.getOwnedFunctionalAllocation().isEmpty());

    assertTrue("There must be no unresolved proxies", UnresolvedProxyCrossReferencer.find(project).isEmpty());
  }

  private IPropertyContext _propertiesContext = null;

  /**
   * Set Transition preferences.
   * @param id
   * @param value
   */
  protected void setPreferenceValue(String id, Object value) {
    if (_propertiesContext == null) {
      IProperties properties = new PropertiesLoader().getProperties(ITopDownConstants.OPTIONS_SCOPE__PREFERENCES);
      _propertiesContext = new PropertyContext(properties);
    }

    IProperty property = _propertiesContext.getProperties().getProperty(id);
    if (property == null) {
      assertFalse("Property not found : " + id, true);
    }
    _propertiesContext.setCurrentValue(property, value);
    _propertiesContext.write(property);
  }

  /**
   * Given a target object, get the source object using Traces.
   * @param object
   * @return
   */
  public static Collection<EObject> getSourceElements(EObject object) {
    List<EObject> result = new ArrayList<EObject>();
    if (object instanceof TraceableElement) {
      for (AbstractTrace trace : ((TraceableElement) object).getIncomingTraces()) {
        result.add(trace.getSourceElement());
      }
    }
    return result;
  }

}
