/*******************************************************************************
 * Copyright (c) 2020, 2024 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.ui.properties.richtext.clipboard.RichTextLinksHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

public class RichTextLinksHelperTest extends BasicTestCase {

  private static final String TEST_MODEL = "RichtextTestModel";

  public static String ROOT_SF = "8aec9c74-487d-4d8d-8ea2-5f733b69f23f";
  public static String SYSTEM = "9a5f0860-a8fb-4d6a-9a9b-f8b891f82c58";

  public static String SDFB = "_la3j4DNYEeieBf4kJpEPiQ";
  public static String TABLE = "_0URZYDNeEeieBf4kJpEPiQ";

  public static final String MODEL_ELEMENTS_HTML = "<a href=\"hlink://8aec9c74-487d-4d8d-8ea2-5f733b69f23f\">Root System Function</a>"
      + " <a href=\"hlink://9a5f0860-a8fb-4d6a-9a9b-f8b891f82c58\">System</a>";

  public static final String DESCRIPTORS_HTML = "<a href=\"hlink://_lccRMDNYEeieBf4kJpEPiQ\">[SDFB] Root System Function</a> "
      + "<a href=\"hlink://_0UTOkTNeEeieBf4kJpEPiQ\">New State Machine and Capability Function Matrix</a>";

  private Session session;
  private SessionContext context;
  private RichTextLinksHelper linksHelper;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(TEST_MODEL);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    session = getSessionForTestModel(TEST_MODEL);
    context = new SessionContext(session);
    linksHelper = RichTextLinksHelper.getInstance();
  }

  @Override
  public void test() throws Exception {

    assertModelElements();
    assertDescriptors();    
  }

  private void assertModelElements() {
    SystemFunction function = context.getSemanticElement(ROOT_SF);
    SystemComponent system = context.getSemanticElement(SYSTEM);
    List<EObject> elements = Arrays.asList(function, system);

    String copiedElementsLinksHtml = linksHelper.getElementsLinksHtml(elements);
    assertEquals(MODEL_ELEMENTS_HTML, copiedElementsLinksHtml);

  }

  private void assertDescriptors() {
    Collection<EObject> descriptors = new ArrayList<>(DialectManager.INSTANCE.getAllRepresentationDescriptors(session));

    String copiedElementsLinksHtml = linksHelper.getElementsLinksHtml(descriptors);
    assertEquals(DESCRIPTORS_HTML, copiedElementsLinksHtml);
  }

}
