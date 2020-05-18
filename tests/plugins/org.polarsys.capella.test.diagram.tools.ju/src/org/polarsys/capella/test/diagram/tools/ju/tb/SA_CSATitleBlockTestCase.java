package org.polarsys.capella.test.diagram.tools.ju.tb;

public class SA_CSATitleBlockTestCase extends CSATitleBlockTestCase {

  private static final String ACTOR1_ID = "435c21b9-b55a-46b7-b41e-cf1161fe34ad";
  private static final String ACTOR2_ID = "2291abec-ec1d-486c-9cee-38a63e3c3521";
  private static final String ACTOR3_ID = "ce36682b-e4b8-4650-85e6-8e2d383e7375";
  private static final String COMPONENT_ID = "dde2b030-28f4-4b9d-bee5-36361298400a";
  private static final String DIAGRAM_NAME = "[CSA] System";
  private static final String SYSTEM_PKG = "dde2b030-28f4-4b9d-bee5-36361298400a";

  @Override
  protected void initData() {
    elementsId.add(ACTOR1_ID);
    elementsId.add(ACTOR2_ID);
    elementsId.add(ACTOR3_ID);
    elementsId.add(COMPONENT_ID);
    diagramName = DIAGRAM_NAME;
    systemPKG = SYSTEM_PKG;
  }

}
