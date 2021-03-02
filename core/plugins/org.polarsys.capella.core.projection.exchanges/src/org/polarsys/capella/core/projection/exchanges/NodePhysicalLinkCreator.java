package org.polarsys.capella.core.projection.exchanges;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;

public class NodePhysicalLinkCreator extends PhysicalLinksCreator {

  public NodePhysicalLinkCreator(Component component_p, Part part_p) {
    super(component_p, part_p);
  }

  /**
   * Returns whether a component which will be a bound of a created exchange is valid or not
   * 
   * @param component
   * @return
   */
  @Override
  protected boolean isValidBound(Component component) {
    if (component instanceof PhysicalComponent) {
      PhysicalComponent cpnt = (PhysicalComponent) component;
      PhysicalComponentNature nature = cpnt.getNature();
      if ((cpnt instanceof PhysicalComponent) && (nature == PhysicalComponentNature.NODE)) {
        return true;
      }
    }
    return false;
  }

  @Override
  protected void createPLsFromCESameLevel(Component container) {
    // do nothing
  }

}