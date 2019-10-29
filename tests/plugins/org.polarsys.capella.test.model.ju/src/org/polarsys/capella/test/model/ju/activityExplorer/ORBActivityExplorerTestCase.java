package org.polarsys.capella.test.model.ju.activityExplorer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.oa.NewRoleBlankDiagramAdapter;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class ORBActivityExplorerTestCase extends DiagramActivityExplorerTestCase {

  @Override
  public void initLink() {
    link = new MyNewRoleBlankDiagramAdapter();
  }

  @Override
  public ModelElement getTestModelElement() {
    return ((MyNewRoleBlankDiagramAdapter) link).getMyModelElement(project);
  }

  @Override
  public ComponentPkg getStructure() {
    return context.getSemanticElement(OA__OPERATIONAL_CONTEXT);
  }

  @Override
  public String getDefaultName() {
    return "[ORB] Operational Entities";
  }

  @Override
  public String getDiagramName() {
    return IDiagramNameConstants.OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME;
  }

  @Override
  public boolean getResultOfCreateDiagram() {
    return ((MyNewRoleBlankDiagramAdapter) link).myCreateDiagram(structure, session);
  }

  class MyNewRoleBlankDiagramAdapter extends NewRoleBlankDiagramAdapter {

    @Override
    protected boolean useDefaultName() {
      return true;
    }

    public ModelElement getMyModelElement(EObject rootSemanticModel) {
      return (ModelElement) getModelElement(rootSemanticModel);
    }

    public boolean myCreateDiagram(final EObject project, final Session session) {
      return createDiagram(project, session);
    }
  }
}
