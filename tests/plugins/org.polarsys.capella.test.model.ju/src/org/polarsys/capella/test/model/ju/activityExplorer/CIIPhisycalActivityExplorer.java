package org.polarsys.capella.test.model.ju.activityExplorer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.pa.NewInternalInterfaceDiagramOnPSAdapter;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class CIIPhisycalActivityExplorer extends SystemActivityExplorerTestCase{

  @Override
  public ModelElement getTestModelElement() {
    return ((MyNewInternalInterfaceDiagramOnPSAdapter) link).getMyModelElement(project);
  }

  @Override
  public Component getStructure() {
    return context.getSemanticElement(PA__PHYSICAL_SYSTEM);
  }

  @Override
  public String getDiagramName() {
    return IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME;
  }

  @Override
  public void initLink() {
    link = new MyNewInternalInterfaceDiagramOnPSAdapter();
  }

  @Override
  public String getDefaultName() {
     return "[CII] Physical System";
  }

  @Override
  public boolean getResultOfCreateDiagram() {
    return ((MyNewInternalInterfaceDiagramOnPSAdapter) link).myCreateDiagram(structure, session);
  }
  
  class MyNewInternalInterfaceDiagramOnPSAdapter extends NewInternalInterfaceDiagramOnPSAdapter {
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
