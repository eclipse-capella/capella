package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;

public class ShowHideFunctions extends AbstractDiagramTestCase {

  public static final String PROJECT_NAME = "ShowHideFunctions";
  
  public Type type;
  public String F_P;
  public String F_1;
  public String F_1_1;
  public String F_1_2;
  public String F_2;
  public String C_P;
  public String C_1;
  public String C_1_1;
  public String C_2;
  public String C_P_TYPE;
  public String C_1_TYPE;
  public String C_1_1_TYPE;
  public String C_2_TYPE;
  
  public static final String NODE_PC = "a90ac19d-e5de-4a9d-a912-acd6af3e7a94";
  public static final String NODE_PC_TYPE = "15dfcd16-c022-4737-adac-65680c53dbdb";
  
  private void setUpTest() {
    switch (type) {
    case OA:
      F_P   = "2822ed6a-3a64-4b3a-8320-f8e507055fd3";
      F_1   = "73341665-3929-4cd5-be16-5b423d7a6b43";
      F_1_1 = "ac1b07bf-a18b-4b94-b2d5-982f135e8355";
      F_1_2 = "7164ac01-6f46-4792-b22b-ae795d76a38a";
      F_2   = "3374a74d-9089-466d-a650-32025c1eff1b";
      C_P   = "129072ae-9bfb-4cb3-ae75-addda3343cfa";
      C_1   = "2ab542fd-0a34-4f1c-9862-2b037f25ee38";
      C_1_1 = "bdc19f15-dfe9-4eb9-8d3b-493b463bffbd";
      C_2   = "557ad7ee-15c7-4c89-895d-151400f48d68";
      C_P_TYPE   = C_P;
      C_1_TYPE   = C_1;
      C_1_1_TYPE = C_1_1;
      C_2_TYPE   = C_2;
      break;
    case LA:
      F_P   ="2e2f293b-d410-4cba-839a-7a605afa67a2";
      F_1   ="b2f74945-dc8e-462f-9a5b-288e663c4acd";
      F_1_1 ="9e772201-4269-4578-aa84-8d3da1b819d0";
      F_1_2 ="e858d675-01e3-416e-88c0-51809a4d53c1";
      F_2   ="7ad43381-4a55-411d-be8a-1504ca96e14a";
      C_P   ="6ab6bdbe-31f7-4d5e-a591-bf3aaef9e2e8";
      C_1   ="3e3a4b99-32b5-4e0d-bed4-02b2f68bbba1";
      C_1_1 ="2fc039eb-e5ca-4036-affb-95493d1233e8";
      C_2   ="53db276d-1b6c-43d6-9bcd-23d142225131";
      C_P_TYPE   = "e76e5068-bbd3-4c1a-be7f-15b7eb070fc2";
      C_1_TYPE   = "acb7234e-aa13-4931-bc39-b76dd86da179";
      C_1_1_TYPE = "f81ccea8-9d33-4a37-b128-2a7969a5c9ff";
      C_2_TYPE   = "463abfbb-b533-4ec2-943b-a696101e2495";
      
      break;
    case PA:
      F_P   = "d9eafddb-24a4-4bce-9a42-5707bbd74a29";
      F_1   = "7e4c62d0-a249-49f8-8677-fe0260933f45";
      F_1_1 = "a02e3904-38d9-4649-8d10-1366ef596459";
      F_1_2 = "8d75d69f-2edd-42ef-a8b4-41c2fa17a13c";
      F_2   = "ff769e7c-9710-4897-a61d-f468d3766fbe";
      C_P   = "4396bb2a-56ad-42b9-8cd1-20687f6837dc";
      C_1   = "6a42879d-f626-43d3-8e57-d3c8b895e38f";
      C_1_1 = "dac071af-39ad-4fb4-8489-a20ce895e936";
      C_2   = "00e08cba-e978-4668-9b02-bfc5de5b0440";
      C_P_TYPE   = "15dfcd16-c022-4737-adac-65680c53dbdb";
      C_1_TYPE   = "4f2b1fe5-0ecc-4595-90a8-fd79627da5d6";
      C_1_1_TYPE = "d9f242e9-38d8-4317-ab77-cd6694edcdde";
      C_2_TYPE   = "73ba7105-a4b1-4163-82f1-38512b2abe8e";
      break;
    default:
      fail();
      break;
    }
  }
  
  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    for (Type xab : new Type[]{Type.OA}) {
      this.type = xab;
      setUpTest();
      
      testLeafFunctionInComponent(context);
      testLeafFunctionInParentComponent(context);
      testParentFunctionInComponent(context);
      testParentFunctionInParentComponent(context);
      testFunctionsMustHideIfShowSubComponent(context);
    }
  }
  
  @Override
  public String getRequiredTestModel() {
    return PROJECT_NAME;
  }
  
  private XABDiagram setUpDiagram(SessionContext context) {
    XABDiagram xab = XABDiagram.createDiagram(context, C_P);
    
    if (type == Type.OA) {
      xab.insertComponent(C_P, xab.getDiagramId());
    } else if (type == Type.PA) {
      xab.insertComponent(NODE_PC_TYPE, NODE_PC, xab.getDiagramId());
      xab.insertComponent(C_P, NODE_PC);
    }
    
    xab.insertComponent(C_1  , C_P);
    xab.insertComponent(C_1_1, C_1);
    xab.insertComponent(C_2  , C_P);
    
    return xab;
  }
  
  /**
   * test show leaf function in component (not italic, not dash)
   * @param context
   */
  private void testLeafFunctionInComponent(SessionContext context) {
    XABDiagram xab = setUpDiagram(context);
    
    xab.hasntView(F_1_1);
    xab.hasntView(F_1_2);
    xab.hasntView(F_2);
    xab.allocateFunction(F_1_1, C_1_1);
    xab.allocateFunction(F_1_2, C_1_1);
    xab.allocateFunction(F_2  , C_2);
    checkFunction(xab, F_1_1, false, false);
    checkFunction(xab, F_1_2, false, false);
    checkFunction(xab, F_2  , false, false);
  }

  /**
   * test show leaf function in parent component (not italic, dash)
   * @param context
   */
  private void testLeafFunctionInParentComponent(SessionContext context) {
    XABDiagram xab = setUpDiagram(context);
    
    xab.removeComponent(C_1_1);
    xab.hasntView(F_1_1);
    xab.hasntView(F_1_2);
    xab.allocateFunction(F_1_1, C_1);
    xab.allocateFunction(F_1_2, C_1);
    checkFunction(xab, F_1_1, false, true);
    checkFunction(xab, F_1_2, false, true);
    
    xab.removeComponent(C_1);
    xab.removeComponent(C_2);
    xab.hasntView(F_1_1);
    xab.hasntView(F_1_2);
    xab.hasntView(F_2);
    xab.allocateFunction(F_1_1, C_P);
    xab.allocateFunction(F_1_2, C_P);
    xab.allocateFunction(F_2  , C_P);
    checkFunction(xab, F_1_1, false, true);
    checkFunction(xab, F_1_2, false, true);
    checkFunction(xab, F_2  , false, true);
  }

  /**
   * test show parent function in component (italic, not dash)
   * @param context
   */
  private void testParentFunctionInComponent(SessionContext context) {
    XABDiagram xab = setUpDiagram(context);
    
    xab.hasntView(F_1);
    xab.allocateFunction(F_1, C_1_1);
    checkFunction(xab, F_1, true, false);
  }

  /**
   * test show parent function in parent component (italic, dash)
   * @param context
   */
  private void testParentFunctionInParentComponent(SessionContext context) {
    XABDiagram xab = setUpDiagram(context);
    
    xab.removeComponent(C_1_1);
    xab.allocateFunction(F_1, C_1);
    checkFunction(xab, F_1, true, true);
    
    xab.removeComponent(C_1);
    xab.hasntView(F_1);
    xab.allocateFunction(F_1, C_P);
    checkFunction(xab, F_1, true, true);
  }

  /**
   * test that if sub component is shown, allocated functions in parent component must hide
   * @param context
   */
  private void testFunctionsMustHideIfShowSubComponent(SessionContext context) {
    XABDiagram xab = setUpDiagram(context);
    
    xab.removeComponent(C_2);
    xab.allocateFunction(F_2, C_P);
    xab.insertComponent(C_2, C_P);
    xab.hasntView(F_2);
  }

  private void checkFunction(XABDiagram xab, String id, boolean mustBeItalic, boolean mustBeDash) {
    
    DSemanticDecorator view = xab.getView(id);
    if (view instanceof DNode) {
      DNode node = (DNode) view;
      NodeStyle style = node.getOwnedStyle();

      LineStyle lineStyle =style.getBorderLineStyle();
      boolean isDash = "dash".equals(lineStyle.getLiteral());
      assertEquals(mustBeDash, isDash);
      
      boolean isItalic = false;
      EList<FontFormat> labelFormat = style.getLabelFormat();
      for (FontFormat format : labelFormat) {
        if ("italic".equals(format.getLiteral())) {
          isItalic = true;
        }
      }
      assertEquals(mustBeItalic, isItalic);
      
    } else {
      fail();
    }
  }
}
