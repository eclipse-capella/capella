package org.polarsys.capella.test.model.ju.activityExplorer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.sa.NewDetailedInterfaceDiagramOnSystemAdapter;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class CDIActivityExplorerTestCase extends SystemActivityExplorerTestCase {

  @Override
  public void initLink() {
    link = new MyNewDetailedInterfaceDiagramOnSystemAdapter();
    
  }

  @Override
  public ModelElement getTestModelElement() {
    return ((MyNewDetailedInterfaceDiagramOnSystemAdapter) link).getMyModelElement(project);
  }

  @Override
  public Component getStructure() {
    return context.getSemanticElement(SA__SYSTEM);

  }

  @Override
  public String getDiagramName() {
    return IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME;
  }

  @Override
  public String getDefaultName() {
    return "[CDI] System";
  }

  @Override
  public boolean getResultOfCreateDiagram() {
    return ((MyNewDetailedInterfaceDiagramOnSystemAdapter) link).myCreateDiagram(structure, session);
  }
  
  class MyNewDetailedInterfaceDiagramOnSystemAdapter extends NewDetailedInterfaceDiagramOnSystemAdapter {
    
    @Override
    protected boolean useDefaultName() {
      return true;
    }

    public ModelElement getMyModelElement(EObject rootSemanticModel) {
      return getModelElement(rootSemanticModel);
    }

    public boolean myCreateDiagram(final EObject project, final Session session) {
      return createDiagram(project, session);
    }

  }

}
