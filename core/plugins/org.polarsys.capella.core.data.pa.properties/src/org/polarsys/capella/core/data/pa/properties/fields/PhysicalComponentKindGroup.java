/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
  private Button _pcKindBtnUnset;
  private Button _pcKindBtnData;
  private Button _pcKindBtnFacilities;
  private Button _pcKindBtnFirmware;
  private Button _pcKindBtnHardware;
  private Button _pcKindBtnHardwareComputer;
  private Button _pcKindBtnMaterials;
  private Button _pcKindBtnPerson;
  private Button _pcKindBtnProcesses;
  private Button _pcKindBtnServices;
  private Button _pcKindBtnSoftware;
  private Button _pcKindBtnSoftwareApplication;
  private Button _pcKindBtnSoftwareDeploymentUnit;
  private Button _pcKindBtnSoftwareExecutionUnit;

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   * @param enabled_p
   */
  public PhysicalComponentKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean enabled_p) {
    super(parent_p, widgetFactory_p, Messages.getString("PhysicalComponentKind.Label"), 4); //$NON-NLS-1$

    _pcKindBtnUnset = createButton(PhysicalComponentKind.UNSET, enabled_p);
    _pcKindBtnData = createButton(PhysicalComponentKind.DATA, enabled_p);
    _pcKindBtnFacilities = createButton(PhysicalComponentKind.FACILITIES, enabled_p);
    _pcKindBtnFirmware = createButton(PhysicalComponentKind.FIRMWARE, enabled_p);
    _pcKindBtnHardware = createButton(PhysicalComponentKind.HARDWARE, enabled_p);
    _pcKindBtnHardwareComputer = createButton(PhysicalComponentKind.HARDWARE_COMPUTER, enabled_p);
    _pcKindBtnMaterials = createButton(PhysicalComponentKind.MATERIALS, enabled_p);
    _pcKindBtnPerson = createButton(PhysicalComponentKind.PERSON, enabled_p);
    _pcKindBtnProcesses = createButton(PhysicalComponentKind.PROCESSES, enabled_p);
    _pcKindBtnServices = createButton(PhysicalComponentKind.SERVICES, enabled_p);
    _pcKindBtnSoftware = createButton(PhysicalComponentKind.SOFTWARE, enabled_p);
    _pcKindBtnSoftwareApplication = createButton(PhysicalComponentKind.SOFTWARE_APPLICATION, enabled_p);
    _pcKindBtnSoftwareDeploymentUnit = createButton(PhysicalComponentKind.SOFTWARE_DEPLOYMENT_UNIT, enabled_p);
    _pcKindBtnSoftwareExecutionUnit = createButton(PhysicalComponentKind.SOFTWARE_EXECUTION_UNIT, enabled_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_pcKindBtnUnset);
    fields.add(_pcKindBtnData);
    fields.add(_pcKindBtnFacilities);
    fields.add(_pcKindBtnFirmware);
    fields.add(_pcKindBtnHardware);
    fields.add(_pcKindBtnHardwareComputer);
    fields.add(_pcKindBtnMaterials);
    fields.add(_pcKindBtnPerson);
    fields.add(_pcKindBtnProcesses);
    fields.add(_pcKindBtnServices);
    fields.add(_pcKindBtnSoftware);
    fields.add(_pcKindBtnSoftwareApplication);
    fields.add(_pcKindBtnSoftwareDeploymentUnit);
    fields.add(_pcKindBtnSoftwareExecutionUnit);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _pcKindBtnUnset;
  }
}
