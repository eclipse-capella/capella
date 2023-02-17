/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.platform.sirius.ted.DataNotifier;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.model.handler.markers.ICapellaValidationConstants;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.model.handler.validation.CapellaDiagnostician;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.ImagePathRemoveResolver;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase;

public class Rule_I_47_IncorrectImagePath extends ValidationRulePartialTestCase {

  /**
   * Could be any other element of the model. It just have to have the feature description.
   */
  public static final String INVALID_LC2_PART = "44394260-1494-487b-94f1-659ace72ab84"; //$NON-NLS-1$

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(INVALID_LC2_PART);
  }

  @Override
  protected EClass getTargetedEClass() {
    return ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.platform.sirius.sirius.validation.I_47";
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition(INVALID_LC2_PART, 1));
  }

  @Override
  public void test() throws Exception {
    Part partToCheck = prepareI47();

    CapellaDiagnostician capellaDiagnostician = new CapellaDiagnostician(
        CapellaAdapterFactoryProvider.getInstance().getAdapterFactory(), new NullProgressMonitor());
    Diagnostic validate = capellaDiagnostician.validate(partToCheck);

    // Checks
    int severity = validate.getSeverity();
    assertEquals("Bad diagnostic severity", Diagnostic.ERROR, severity);
    List<Diagnostic> children = validate.getChildren();
    assertEquals("Bad diagnostic children number", 1, children.size());
    assertEquals("Bad diagnostic severity", Diagnostic.ERROR, children.get(0).getSeverity());
    assertTrue("Bad diagnotic message", children.get(0).getMessage().contains("(Image) The absolute path"));
    List<IMarker> markers = new ArrayList<IMarker>();
    for (Diagnostic childDiagnostic : children) {
      IFile resourceFile = EcoreUtil2.getFile(partToCheck.eResource());
      IMarker marker = LightMarkerRegistry.getInstance().createMarker(resourceFile, childDiagnostic,
          ICapellaValidationConstants.CAPELLA_MARKER_ID);

      if (marker != null) {
        markers.add(marker);
      }
    }
    if (getCheckQuickFix()) {
      IStatus status = testCheckQuickFix(markers);
      if (status != null && !status.isOK()) {
        fail(status.getMessage());
      }
    }
  }

  /**
   * To check that I47 is raising an error, we modify the model to associate a documentation that contains an invalid
   * absolute path to an image.<br/>
   */
  private Part prepareI47() {
    CapellaModel model = getTestModel(getRequiredTestModel());
    IScope scope = new ScopeModelWrapper(model);
    Part part = (Part) IdManager.getInstance().getEObject(INVALID_LC2_PART, scope);
    part.eResource().eAdapters().removeIf(DataNotifier.class::isInstance);
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().getExecutionManager(model.getEditingDomain());
    manager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        part.setDescription("<p><img src=\"file:/C:/INVALID/PATH/11_Images/circle_ws2.png\" /></p>");
      }
    });
    return part;
  }

  @Override
  protected String getRequiredTestModel() {
    return "exchange-item-instance-and-part-model";
  }

  @Override
  protected boolean getCheckQuickFix() {
    return true;
  }

  @Override
  protected IStatus testCheckQuickFix(List<IMarker> markers) {
    ImagePathRemoveResolver imagePathRemoveResolver = new ImagePathRemoveResolver();
    CapellaElement element = (CapellaElement) MarkerViewHelper.getModelElementsFromMarker(markers.get(0)).get(0);
    imagePathRemoveResolver.run(markers.get(0));
    String imageTag = "<img src=\\\"file:/C:/INVALID/PATH/11_Images/circle_ws2.png\\\" />";
    if (element.getDescription().contains(imageTag)) {
      return Status.error("The I_47 Resolver has failed");
    }
    return Status.OK_STATUS;
  }

}
