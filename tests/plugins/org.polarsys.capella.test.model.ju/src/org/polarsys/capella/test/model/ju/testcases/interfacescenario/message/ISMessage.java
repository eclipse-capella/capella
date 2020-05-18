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
package org.polarsys.capella.test.model.ju.testcases.interfacescenario.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.algorithms.SelectionAlgorithms;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.InterfaceCommunication;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class ISMessage extends BasicTestCase {

  public static String MODEL_NAME = "ISMessage"; //$NON-NLS-1$ 

  public static String TEST__LA__LC_1 = "7fe2da61-e2a5-4189-bfc9-449e26f53f46"; //$NON-NLS-1$ 
  public static String TEST__LA__LC_2 = "9f7ee875-5024-488d-b5b0-fea6bc972447"; //$NON-NLS-1$ 
  public static String TEST__LA__LOGICAL_ACTORS__LA_1 = "4f88d40c-9a30-4fa1-a68b-c1cac0b9bf56"; //$NON-NLS-1$ 
  public static String TEST__LA__LOGICAL_ACTORS__LA_2 = "5cbc0e6a-8b2b-4cfb-ba36-237dba00babb"; //$NON-NLS-1$ 

  public static String TEST__LA__INTERFACEPKG__INTERFACE_1 = "a071b424-7c4c-4ac9-a350-143a02a28ff6"; //$NON-NLS-1$ 
  public static String TEST__LA__INTERFACEPKG__INTERFACE_2 = "e052f1b5-5fad-4e98-8de7-87a5dccde3cd"; //$NON-NLS-1$ 
  public static String TEST__LA__INTERFACEPKG__INTERFACE_3 = "bbb20baf-de66-4247-aa5f-fbd0bdc4f85d"; //$NON-NLS-1$ 
  public static String TEST__LA__INTERFACEPKG__INTERFACE_4 = "7d84f293-d11a-412c-a007-398dec29fe9e"; //$NON-NLS-1$ 
  public static String TEST__LA__INTERFACEPKG__INTERFACE_5 = "dc0a572d-28e0-4a80-a294-2caca43fdcfe"; //$NON-NLS-1$ 

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
  public void test() {
    ICapellaModel model = getTestModel(MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);

    Component lc1 = (Component) IdManager.getInstance().getEObject(TEST__LA__LC_1, scope);
    Component lc2 = (Component) IdManager.getInstance().getEObject(TEST__LA__LC_2, scope);
    Component la1 = (Component) IdManager.getInstance().getEObject(TEST__LA__LOGICAL_ACTORS__LA_1, scope);
    Component la2 = (Component) IdManager.getInstance().getEObject(TEST__LA__LOGICAL_ACTORS__LA_2, scope);

    Interface i1 = (Interface) IdManager.getInstance().getEObject(TEST__LA__INTERFACEPKG__INTERFACE_1, scope);
    Interface i2 = (Interface) IdManager.getInstance().getEObject(TEST__LA__INTERFACEPKG__INTERFACE_2, scope);
    Interface i3 = (Interface) IdManager.getInstance().getEObject(TEST__LA__INTERFACEPKG__INTERFACE_3, scope);
    Interface i4 = (Interface) IdManager.getInstance().getEObject(TEST__LA__INTERFACEPKG__INTERFACE_4, scope);
    Interface i5 = (Interface) IdManager.getInstance().getEObject(TEST__LA__INTERFACEPKG__INTERFACE_5, scope);

    /*
     * A sequence message between LC 1 and LA 1 must take into account Interface 1 and Interface 2
     */
    List<InterfaceCommunication> interfaceCommunications = SelectionAlgorithms.getInterfaceCommunications(lc1, la1);
    List<Interface> interfaceLst = new ArrayList<Interface>();
    for (InterfaceCommunication ic : interfaceCommunications)
      interfaceLst.add(ic.getAllocatingInterface());
    assertTrue("A sequence message between LC 1 and LA 1 must take into account Interface 1", interfaceLst.contains(i1));
    assertTrue("A sequence message between LC 1 and LA 1 must take into account Interface 2", interfaceLst.contains(i2));

    /*
     * A sequence message between LC 2 and LA 2 must take into account Interface 1 - 5
     */
    interfaceCommunications = SelectionAlgorithms.getInterfaceCommunications(lc2, la2);
    interfaceLst = new ArrayList<Interface>();
    for (InterfaceCommunication ic : interfaceCommunications)
      interfaceLst.add(ic.getAllocatingInterface());
    assertTrue("A sequence message between LC 2 and LA 2 must take into account Interface 1", interfaceLst.contains(i1));
    assertTrue("A sequence message between LC 2 and LA 2 must take into account Interface 2", interfaceLst.contains(i2));
    assertTrue("A sequence message between LC 2 and LA 2 must take into account Interface 3", interfaceLst.contains(i3));
    assertTrue("A sequence message between LC 2 and LA 2 must take into account Interface 4", interfaceLst.contains(i4));
    assertTrue("A sequence message between LC 2 and LA 2 must take into account Interface 5", interfaceLst.contains(i5));
  }

}
