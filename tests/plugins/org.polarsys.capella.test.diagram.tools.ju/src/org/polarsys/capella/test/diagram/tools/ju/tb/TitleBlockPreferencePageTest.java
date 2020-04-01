/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.tb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.common.ui.MdeCommonUiActivator;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;
import org.polarsys.capella.test.framework.context.SessionContext;

public class TitleBlockPreferencePageTest extends CDBCommunication {

  private ScopedPreferenceStore preferenceStore;

  public TitleBlockPreferencePageTest(CDBProjectSettings settings) {
    super(settings);
    preferenceStore = (ScopedPreferenceStore) MdeCommonUiActivator.getDefault().getPreferenceStore();
  }

  @Override
  protected void testCDB() {
    cdb = CDBDiagram.createDiagram(context, settings.DATAPKG);
    test2();
  }

  /*
   * @Override public void test() throws Exception { // TODO Auto-generated method stub System.out.println("lalq");
   * 
   * updateTitleBlockPreferencePageColumnsNumber(4); updateTitleBlockPreferencePageLinesNumber(3);
   * updateTitleBlockPreferencePageDefault(true); test2(); }
   */
  public void test2() {
    Session session = getSession("TitleBlockModel");
    SessionContext context = new SessionContext(session);
    System.out.println(context);
    updateTitleBlockPreferencePageDefault(true);
    // PABDiagram pabDiagram = PABDiagram.openDiagram(context, "[PAB] Physical System");
    // PABDiagram pabDiagram = PABDiagram.createDiagram(context, "8432f30b-5f28-4f51-9cd2-871fbdfcdf57");
    CDBDiagram cdb = CDBDiagram.createDiagram(context, "8432f30b-5f28-4f51-9cd2-871fbdfcdf57");
    // cdb.getDiagramId();
    // System.out.println(TitleBlockServices.getService().isUniqueDiagramTitleBlock(cdb.getDiagram()));
    String cls = cdb.createClass();
    // cdb.createTB("TB_1");
  }

  private void updateTitleBlockPreferencePageColumnsNumber(int value) {
    preferenceStore.setValue("columnsNumberTitleBlock", value);
    int newValue = preferenceStore.getInt("columnsNumberTitleBlock");
    assertTrue(value == newValue);
  }

  private void updateTitleBlockPreferencePageLinesNumber(int value) {
    preferenceStore.setValue("linesNumberTitleBlock", value);
    int newValue = preferenceStore.getInt("linesNumberTitleBlock");
    System.out.println("value:" + value);
    System.out.println("newValue: " + newValue);
    assertTrue(value == newValue);
  }

  private void updateTitleBlockPreferencePageDefault(boolean value) {
    preferenceStore.setValue("defaultTitleBlock", value);
    boolean newValue = preferenceStore.getBoolean("defaultTitleBlock");
    assertTrue(value == newValue);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return new ArrayList<String>(Arrays.asList("TitleBlockModel"));
  }

  @Override
  public String getRequiredTestModel() {
    return "TitleBlockModel";
  }

}
