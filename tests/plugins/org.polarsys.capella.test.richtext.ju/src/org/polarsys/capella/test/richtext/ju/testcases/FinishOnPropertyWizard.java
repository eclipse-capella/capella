/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.richtext.ju.testcases;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.ui.properties.richtext.fields.CapellaElementDescriptionGroup;
import org.polarsys.capella.core.ui.properties.wizards.EditCapellaCustomPropertyWizard;
import org.polarsys.capella.core.ui.properties.wizards.EditCapellaCustomPropertyWizardPage;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.richtext.ju.mocks.CapellaDescriptionPropertySectionMock;
import org.polarsys.capella.test.richtext.ju.mocks.CapellaElementDescriptionGroupMock;

import junit.framework.Test;

/**
 * This test case tests that when the Finish button on a Property wizard is pressed, the Richtext is saved
 */
public class FinishOnPropertyWizard extends BasicTestCase {
  public static String ROOT_SF = "8aec9c74-487d-4d8d-8ea2-5f733b69f23f";
  private Shell tmpShell;
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("RichtextTestModel");
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    tmpShell = new Shell();
  }
  
  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel("RichtextTestModel");
    IScope scope = new ScopeModelWrapper(model);
    SystemFunction rootSF = (SystemFunction) IdManager.getInstance().getEObject(ROOT_SF, scope);

    IViewPart explorer = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("capella.project.explorer");
    
    EditCapellaCustomPropertyWizard wizard = new EditCapellaCustomPropertyWizard(explorer, rootSF);
    wizard.addPages();

    EditCapellaCustomPropertyWizardPage capellaPage = null;
    for (IWizardPage page : wizard.getPages()) {
      if (page instanceof EditCapellaCustomPropertyWizardPage) {
        capellaPage = (EditCapellaCustomPropertyWizardPage) page;
        capellaPage.createControl(tmpShell);
        break;
      }
    }

    CapellaElementDescriptionGroup descriptionGroup = null;

    if (capellaPage != null) {
      Optional<CapellaDescriptionPropertySectionMock> descriptionSection = capellaPage.getSections().stream()
          .filter(CapellaDescriptionPropertySectionMock.class::isInstance)
          .map(CapellaDescriptionPropertySectionMock.class::cast).findFirst();
      if (descriptionSection.isPresent()) {
        descriptionGroup = descriptionSection.get().getDescriptionGroup();
      }
    }

    wizard.performFinish();
    assertTrue(descriptionGroup instanceof CapellaElementDescriptionGroupMock
        && ((CapellaElementDescriptionGroupMock) descriptionGroup).isSaved());
  }

  
  @Override
  protected void tearDown() throws Exception {
    tmpShell.dispose();
    super.tearDown();
  }
  
  public static Test suite() {
    return new FinishOnPropertyWizard();
  }
}
