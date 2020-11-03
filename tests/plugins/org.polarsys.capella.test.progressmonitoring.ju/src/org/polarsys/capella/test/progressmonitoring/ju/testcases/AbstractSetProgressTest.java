/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.progressmonitoring.ju.testcases;

import static org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.BOOLEANPROPERTYVALUE_BOOLEANPROPERTY_ID;
import static org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.DATAPKG_DATAPACKAGE_ID;
import static org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.DRAFT;
import static org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.ENUMERATIONPROPERTYVALUE_ENUMPROPERTY_ID;
import static org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.MODEL_NAME;
import static org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.STRINGTYPE_STRING_ID;
import static org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.SYSTEMFUNCTION_ROOTSYSTEMFUNCTION_ID;
import static org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.SYSTEMFUNCTION_SF11_ID;
import static org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.SYSTEMFUNCTION_SF1_ID;
import static org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.SYSTEMFUNCTION_SF22_ID;
import static org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.SYSTEMFUNCTION_SF2_ID;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.core.data.capellacore.BooleanPropertyValue;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.ui.metric.actions.ProgressMonitoringSetAction;
import org.polarsys.capella.core.ui.metric.actions.ProgressSetDialog;
import org.polarsys.capella.core.ui.metric.utils.SetProgressRunSetup;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public abstract class AbstractSetProgressTest extends BasicTestCase {

  protected SystemFunction rootSysFunc;
  protected SystemFunction sf1;
  protected SystemFunction sf11;
  protected SystemFunction sf2;
  protected SystemFunction sf22;

  protected DataPkg dataPackage;
  protected BooleanPropertyValue booleanProperty;
  protected EnumerationPropertyValue enumerationProperty;
  protected StringType string;

  protected Collection<DRepresentationDescriptor> representations;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  @Override
  public void test() throws Exception {
    initializeModelElements();
    doTest();
  }

  protected abstract void doTest() throws Exception;

  protected void initializeModelElements() {
    ICapellaModel model = getTestModel(MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);

    rootSysFunc = (SystemFunction) IdManager.getInstance().getEObject(SYSTEMFUNCTION_ROOTSYSTEMFUNCTION_ID, scope);
    assertNotNull(rootSysFunc);

    sf1 = (SystemFunction) IdManager.getInstance().getEObject(SYSTEMFUNCTION_SF1_ID, scope);
    assertNotNull(sf1);

    sf11 = (SystemFunction) IdManager.getInstance().getEObject(SYSTEMFUNCTION_SF11_ID, scope);
    assertNotNull(sf11);

    sf2 = (SystemFunction) IdManager.getInstance().getEObject(SYSTEMFUNCTION_SF2_ID, scope);
    assertNotNull(sf2);

    sf22 = (SystemFunction) IdManager.getInstance().getEObject(SYSTEMFUNCTION_SF22_ID, scope);
    assertNotNull(sf22);

    dataPackage = (DataPkg) IdManager.getInstance().getEObject(DATAPKG_DATAPACKAGE_ID, scope);
    assertNotNull(dataPackage);

    booleanProperty = (BooleanPropertyValue) IdManager.getInstance().getEObject(BOOLEANPROPERTYVALUE_BOOLEANPROPERTY_ID,
        scope);
    assertNotNull(booleanProperty);

    enumerationProperty = (EnumerationPropertyValue) IdManager.getInstance()
        .getEObject(ENUMERATIONPROPERTYVALUE_ENUMPROPERTY_ID, scope);
    assertNotNull(enumerationProperty);

    string = (StringType) IdManager.getInstance().getEObject(STRINGTYPE_STRING_ID, scope);
    assertNotNull(string);

    representations = RepresentationHelper.getAllRepresentationDescriptorsTargetedBy(Arrays.asList(rootSysFunc));
    
    assertTrue(representations.size() == 2);
  }

  protected ProgressMonitoringSetAction createSetProgressAction(final SetProgressRunSetup runSetup) {
    ProgressMonitoringSetAction action = new ProgressMonitoringSetAction() {

      @Override
      protected ProgressSetDialog getRunSetup(Collection<EObject> selectedObjects) {
        ProgressSetDialog progressSetDialog = new ProgressSetDialog(Display.getDefault().getActiveShell(),
            selectedObjects) {
          @Override
          public boolean isPropagateToRepresentations() {
            return runSetup.isPropagateToRepresentations();
          }

          @Override
          public EnumerationPropertyLiteral getSelectedEnum() {
            return runSetup.getEnumPropertyLiteral();
          }

        };

        return progressSetDialog;

      }
    };
    return action;
  }

  protected SetProgressRunSetup createRunSetup(EnumerationPropertyLiteral propertyLiteral,
      boolean propagateToAllElements, boolean propagateToRepresentations) {
    return new SetProgressRunSetup(propertyLiteral, propagateToAllElements, propagateToRepresentations);
  }

  protected EnumerationPropertyLiteral getDraftPropertyLiteral(EObject element) {
    EnumerationPropertyType ept = CapellaProjectHelper.getEnumerationPropertyType(element,
        CapellaProjectHelper.PROGRESS_STATUS_KEYWORD);
    assertNotNull(ept);
    for (EnumerationPropertyLiteral enumLiteral : ept.getOwnedLiterals()) {
      if (enumLiteral.getLabel().equals(DRAFT)) {
        return enumLiteral;
      }
    }
    return null;
  }
}
