package org.polarsys.capella.test.diagram.filters.ju;

public abstract class DefaultActivatedFilterTestCase extends DiagramObjectFilterTestCase {

  @Override
  protected void checkAndInsertFilter() {
    
    // We only need to check that the filter is already present on the diagram before the test starts
    assertFilterActive(diagram, filterName);
  }
}
