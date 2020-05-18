/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.testcase.copyPasteModel;

import java.util.ArrayList;
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
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCopyToClipboardCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaPasteCommand;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class CopyPasteModelWithFunctionalAllocation extends MiscModel {

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() throws Exception {
    //
    // Data creation.
    //
    ICapellaModel model = getTestModel();
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    TransactionalEditingDomain ted = session.getTransactionalEditingDomain();
    final Project project = model.getProject(ted);
    // Create an OperationalActivity and an OperationalActor allocating this OperationalActivity.
    final String actor1Name = "Actor1";
    final String activity1Name = "Activity1";

    final Entity[] actor1 = { null };

    ExecutionManager executionManager = TestHelper.getExecutionManager(project);
    executionManager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        // Add an Actor1 at OA level
        actor1[0] = OaFactory.eINSTANCE.createEntity(actor1Name);
        actor1[0].setActor(true);
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
    SystemComponent systemActor = (SystemComponent) EcoreUtil.getObjectByType(getSourceElements(actor1[0]),
        CtxPackage.Literals.SYSTEM_COMPONENT);
    CapellaCommonNavigator capellaProjectView = (CapellaCommonNavigator) PlatformUI.getWorkbench()
        .getActiveWorkbenchWindow().getActivePage().showView(CapellaCommonNavigator.ID);
    CapellaCopyToClipboardCommand capellaCopyToClipboardCommand = new CapellaCopyToClipboardCommand(ted,
        Collections.singleton(systemActor), capellaProjectView.getCommonViewer());
    ted.getCommandStack().execute(capellaCopyToClipboardCommand);
    // Paste
    ComponentPkg actorPkg = ModelQueryHelper.getSystemComponentPkg(project);
    List<EObject> contentBeforePaste = new ArrayList<>(actorPkg.eContents());
    CapellaPasteCommand capellaPasteCommand = new CapellaPasteCommand(ted, actorPkg, null, CommandParameter.NO_INDEX);
    ted.getCommandStack().execute(capellaPasteCommand);

    //
    // Checks
    //
    List<EObject> addedElements = new ArrayList<>(actorPkg.eContents());
    addedElements.removeAll(contentBeforePaste);
    assertTrue("2 additional elements (new Actor and its part) are expected in Actors package", addedElements.size() == 2);
    SystemComponent pastedActor = (SystemComponent) EcoreUtil.getObjectByType(addedElements,
        CtxPackage.Literals.SYSTEM_COMPONENT);

    assertTrue("Feature Owned Traces must be empty in copied Actor", pastedActor.getOwnedTraces().isEmpty());
    assertTrue("Feature Owned Functional Allocation must be empty in copied Actor",
        pastedActor.getOwnedFunctionalAllocation().isEmpty());

    assertTrue("There must be no unresolved proxies", UnresolvedProxyCrossReferencer.find(project).isEmpty());
  }

  private IPropertyContext _propertiesContext = null;

  /**
   * Set Transition preferences.
   * 
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
   * 
   * @param object
   * @return
   */
  public static Collection<EObject> getSourceElements(EObject object) {
    List<EObject> result = new ArrayList<>();
    if (object instanceof TraceableElement) {
      for (AbstractTrace trace : ((TraceableElement) object).getIncomingTraces()) {
        result.add(trace.getSourceElement());
      }
    }
    return result;
  }

}
