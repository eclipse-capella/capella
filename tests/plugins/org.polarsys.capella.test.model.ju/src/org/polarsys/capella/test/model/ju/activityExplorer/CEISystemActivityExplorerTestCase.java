package org.polarsys.capella.test.model.ju.activityExplorer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.sa.NewExternalInterfaceDiagramOnSystemAdapter;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.model.ju.activityExplorer.SystemActivityExplorerTestCase;

public class CEISystemActivityExplorerTestCase extends SystemActivityExplorerTestCase {

  @Override
  public ModelElement getTestModelElement() {
    return ((MyNewExternalInterfaceDiagramOnSystemAdapter) link).getMyModelElement(project);
  }

  @Override
  public Component getStructure() {
    return context.getSemanticElement(SA__SYSTEM);

  }

  @Override
  public String getDiagramName() {
    return IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME;
  }

  @Override
  public void initLink() {
    link = new MyNewExternalInterfaceDiagramOnSystemAdapter();
  }

  @Override
  public String getDefaultName() {
    return "[CEI] System";
  }

  @Override
  public boolean getResultOfCreateDiagram() {
    return ((MyNewExternalInterfaceDiagramOnSystemAdapter) link).myCreateDiagram(structure, session);
  }

  class MyNewExternalInterfaceDiagramOnSystemAdapter extends NewExternalInterfaceDiagramOnSystemAdapter {
    
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
