package org.polarsys.capella.test.model.ju.activityExplorer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.la.NewInternalInterfaceDiagramOnLSAdapter;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class CIILogicalActivityExplorerTestCase extends SystemActivityExplorerTestCase {

  @Override
  public ModelElement getTestModelElement() {
    return ((MyNewInternalInterfaceDiagramOnLSAdapter) link).getMyModelElement(project);

  }

  @Override
  public Component getStructure() {
    return context.getSemanticElement(LA__LOGICAL_SYSTEM);
  }

  @Override
  public String getDiagramName() {
    return IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME;
  }

  @Override
  public void initLink() {
    link = new MyNewInternalInterfaceDiagramOnLSAdapter();
  }

  @Override
  public String getDefaultName() {

    return "[CII] Logical System";
  }

  @Override
  public boolean getResultOfCreateDiagram() {
    return ((MyNewInternalInterfaceDiagramOnLSAdapter) link).myCreateDiagram(structure, session);
  }
  
  class MyNewInternalInterfaceDiagramOnLSAdapter extends NewInternalInterfaceDiagramOnLSAdapter {
    
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
