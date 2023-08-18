package org.polarsys.capella.test.validation.rules.ju.testcases.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class Bug2438TestCase extends BasicTestCase {

  private static String testModelName = "Bug2438";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(testModelName);
  }

  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel(testModelName);
    Project project = getProject(model);
    Diagnostician diagnostician = new Diagnostician();
    Diagnostic diagnostic = diagnostician.validate(project);

    assertTrue(Diagnostic.ERROR == diagnostic.getSeverity());

    List<BasicDiagnostic> basicDiagnostics = getBasicDiagnostics(diagnostic);
    assertTrue(basicDiagnostics.size() == 3);

    assertTrue(containsData(basicDiagnostics, ComponentExchangeRealization.class,
        ModellingcorePackage.eINSTANCE.getAbstractTrace_SourceElement()));
    assertTrue(containsData(basicDiagnostics, ComponentExchangeRealization.class,
        FaPackage.eINSTANCE.getExchangeSpecificationRealization_RealizingExchangeSpecification()));
    assertTrue(containsData(basicDiagnostics, ComponentExchangeRealization.class,
        FaPackage.eINSTANCE.getComponentExchangeRealization_AllocatingComponentExchange()));
  }

  private boolean containsData(List<BasicDiagnostic> basicDiagnostics, Class<ComponentExchangeRealization> clazz,
      EReference eRef) {
    for (BasicDiagnostic diag : basicDiagnostics) {
      List<?> data = diag.getData();
      assertTrue(data.size() == 2);
      
      if (clazz.isAssignableFrom(data.get(0).getClass()) && data.get(1).equals(eRef)) {
        return true;
      }
    }
    return false;
  }

  private Project getProject(ICapellaModel model) {
    return model.getProject(getSessionForTestModel(testModelName).getTransactionalEditingDomain());
  }

  private List<BasicDiagnostic> getBasicDiagnostics(Diagnostic diagnostic) {
    List<BasicDiagnostic> result = new ArrayList<>();
    for (Diagnostic child : diagnostic.getChildren()) {
      if (child instanceof BasicDiagnostic) {
        result.add((BasicDiagnostic) child);
      }
    }
    return result;
  }
}
