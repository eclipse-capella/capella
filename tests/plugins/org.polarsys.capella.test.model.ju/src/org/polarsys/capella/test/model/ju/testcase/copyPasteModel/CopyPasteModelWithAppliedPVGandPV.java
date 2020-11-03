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
package org.polarsys.capella.test.model.ju.testcase.copyPasteModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.UnresolvedProxyCrossReferencer;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.IntegerPropertyValue;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCopyToClipboardCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaPasteCommand;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class CopyPasteModelWithAppliedPVGandPV extends MiscModel {

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() throws Exception {
    //
    // Test data creation.
    //
    ICapellaModel model = getTestModel();
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    TransactionalEditingDomain ted = session.getTransactionalEditingDomain();
    final Project project = ((ICapellaModel) model).getProject(ted);
    // Create a PropertyValueGroup, a PropertyValue, an OperationalActivity and apply the PVG and the PV to the OA.
    final String activity1Name = "Activity1";

    final OperationalActivity[] activity1 = {null};
    
    ExecutionManager executionManager = TestHelper.getExecutionManager(project);
    executionManager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        SystemEngineering systemEngineering = ModelQueryHelper.getSystemEngineering(project);
        // Create Property Value Group
        PropertyValueGroup pvg = CapellacoreFactory.eINSTANCE.createPropertyValueGroup("PVG1");
        systemEngineering.getOwnedPropertyValueGroups().add(pvg);

        // Create a Property Value
        IntegerPropertyValue ipv = CapellacoreFactory.eINSTANCE.createIntegerPropertyValue("Integer PV1");
        systemEngineering.getOwnedPropertyValues().add(ipv);
        
        // Create an OA and apply PVG and PV on it
        activity1[0] = OaFactory.eINSTANCE.createOperationalActivity(activity1Name);
        ModelQueryHelper.getRootOperationalActivity(project).getOwnedFunctions().add(activity1[0]);
        
        activity1[0].getAppliedPropertyValueGroups().add(pvg);
        activity1[0].getAppliedPropertyValues().add(ipv);
      }
    });

    //
    // Copy paste the activity
    //
    // Copy
    CapellaCommonNavigator capellaProjectView = (CapellaCommonNavigator) PlatformUI.getWorkbench()
        .getActiveWorkbenchWindow().getActivePage().showView(CapellaCommonNavigator.ID);
    CapellaCopyToClipboardCommand capellaCopyToClipboardCommand = new CapellaCopyToClipboardCommand(ted, Collections.singleton(activity1[0]), capellaProjectView.getCommonViewer());
    ted.getCommandStack().execute(capellaCopyToClipboardCommand);
    // Paste
    OperationalActivity rootOperationalActivity = ModelQueryHelper.getRootOperationalActivity(project);
    List<EObject> contentBeforePaste = new ArrayList<EObject>(rootOperationalActivity.eContents());
    CapellaPasteCommand capellaPasteCommand = new CapellaPasteCommand(ted, rootOperationalActivity, null,  CommandParameter.NO_INDEX);
    ted.getCommandStack().execute(capellaPasteCommand);
    
    //
    // Checks
    //
    List<EObject> addedElements = new ArrayList<EObject>(rootOperationalActivity.eContents());
    addedElements.removeAll(contentBeforePaste);
    assertTrue("1 additional element of type OperationalActivity is expected in RootOperationalActivity", addedElements.size() == 1);
    OperationalActivity pastedOA = (OperationalActivity) EcoreUtil.getObjectByType(addedElements, OaPackage.Literals.OPERATIONAL_ACTIVITY);
    
    assertTrue("Feature appliedPropertyValueGroups must be empty in copied OA", pastedOA.getAppliedPropertyValueGroups().isEmpty());
    assertTrue("Feature getAppliedPropertyValues must be empty in copied OA", pastedOA.getAppliedPropertyValues().isEmpty());
    
    assertTrue("There must be no unresolved proxies", UnresolvedProxyCrossReferencer.find(project).isEmpty());
  }
}
