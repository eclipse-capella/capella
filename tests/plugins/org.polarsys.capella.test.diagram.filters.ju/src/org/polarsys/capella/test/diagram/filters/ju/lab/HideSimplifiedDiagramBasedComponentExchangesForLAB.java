package org.polarsys.capella.test.diagram.filters.ju.lab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DefaultActivatedFilterTestCase;

public class HideSimplifiedDiagramBasedComponentExchangesForLAB extends DefaultActivatedFilterTestCase {

  private final String COMPONENT_PORT_1_ID = "790c935f-36fa-4b1c-90fb-520a445a0428";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[LAB] Logical System";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_LAB_HIDE_SIMPLIFIED_DIAGRAM_BASED_COMPONENT_EXCHANGES;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { COMPONENT_PORT_1_ID });
  }

}