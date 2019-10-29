package org.polarsys.capella.test.diagram.filters.ju.cei;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class CEIDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new HideInterfaces());
    tests.add(new HideExchangeItemAllocations());
    tests.add(new HideExchangeItems());
    tests.add(new HideComponentPorts());
    tests.add(new HideUseLinks());
    tests.add(new HideImplementLinks());
    tests.add(new HideProvideLinks());
    tests.add(new HideRequireLinks());
    tests.add(new HideCommunicationLinks());
    tests.add(new HideGeneralizationLinks());
    tests.add(new HidePropertyValues());
    tests.add(new HideSimplifiedComponentInteractions());
    tests.add(new HideTechnicalInterfaceForCEI());
    return tests;
  }
}
