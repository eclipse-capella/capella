/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.pa.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.pa.PhysicalComponentKind;
import org.polarsys.capella.core.data.pa.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class PhysicalComponentKindGroup extends AbstractSemanticKindGroup {
  private Button pcKindBtnUnset;
  private Button pcKindBtnData;
  private Button pcKindBtnFacilities;
  private Button pcKindBtnFirmware;
  private Button pcKindBtnHardware;
  private Button pcKindBtnHardwareComputer;
  private Button pcKindBtnMaterials;
  private Button pcKindBtnPerson;
  private Button pcKindBtnProcesses;
  private Button pcKindBtnServices;
  private Button pcKindBtnSoftware;
  private Button pcKindBtnSoftwareApplication;
  private Button pcKindBtnSoftwareDeploymentUnit;
  private Button pcKindBtnSoftwareExecutionUnit;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param enabled
   */
  public PhysicalComponentKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean enabled) {
    super(parent, widgetFactory, Messages.getString("PhysicalComponentKind.Label"), 4); //$NON-NLS-1$

    pcKindBtnUnset = createButton(PhysicalComponentKind.UNSET, enabled);
    pcKindBtnData = createButton(PhysicalComponentKind.DATA, enabled);
    pcKindBtnFacilities = createButton(PhysicalComponentKind.FACILITIES, enabled);
    pcKindBtnFirmware = createButton(PhysicalComponentKind.FIRMWARE, enabled);
    pcKindBtnHardware = createButton(PhysicalComponentKind.HARDWARE, enabled);
    pcKindBtnHardwareComputer = createButton(PhysicalComponentKind.HARDWARE_COMPUTER, enabled);
    pcKindBtnMaterials = createButton(PhysicalComponentKind.MATERIALS, enabled);
    pcKindBtnPerson = createButton(PhysicalComponentKind.PERSON, enabled);
    pcKindBtnProcesses = createButton(PhysicalComponentKind.PROCESSES, enabled);
    pcKindBtnServices = createButton(PhysicalComponentKind.SERVICES, enabled);
    pcKindBtnSoftware = createButton(PhysicalComponentKind.SOFTWARE, enabled);
    pcKindBtnSoftwareApplication = createButton(PhysicalComponentKind.SOFTWARE_APPLICATION, enabled);
    pcKindBtnSoftwareDeploymentUnit = createButton(PhysicalComponentKind.SOFTWARE_DEPLOYMENT_UNIT, enabled);
    pcKindBtnSoftwareExecutionUnit = createButton(PhysicalComponentKind.SOFTWARE_EXECUTION_UNIT, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(pcKindBtnUnset);
    fields.add(pcKindBtnData);
    fields.add(pcKindBtnFacilities);
    fields.add(pcKindBtnFirmware);
    fields.add(pcKindBtnHardware);
    fields.add(pcKindBtnHardwareComputer);
    fields.add(pcKindBtnMaterials);
    fields.add(pcKindBtnPerson);
    fields.add(pcKindBtnProcesses);
    fields.add(pcKindBtnServices);
    fields.add(pcKindBtnSoftware);
    fields.add(pcKindBtnSoftwareApplication);
    fields.add(pcKindBtnSoftwareDeploymentUnit);
    fields.add(pcKindBtnSoftwareExecutionUnit);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return pcKindBtnUnset;
  }
}
