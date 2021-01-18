package org.polarsys.capella.core.data.common.validation.statetransition;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

public class MDCHK_State_Mode_Siblings extends AbstractModelConstraint {
  
  public boolean isMixedHierarchyAllowed() {
    return CapellaModelPreferencesPlugin.getDefault().isMixedModeStateAllowed();
  }
  
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (isMixedHierarchyAllowed())
      return ctx.createSuccessStatus();
    
    StateMachine stateMachine = (StateMachine) ctx.getTarget();
    
    // check if state/mode has no mode/state as "brother"
    EList<Region> regions = stateMachine.getOwnedRegions();
    Collection<AbstractState> states = new ArrayList<AbstractState>();
    Collection<AbstractState> modes = new ArrayList<AbstractState>();
    for (Region region : regions) {
      for (AbstractState st : region.getOwnedStates()) {
        if (st instanceof Mode) {
          modes.add(st);
        } else if (st instanceof State) {
          states.add(st);
        }
      }
    }
    if (modes.size() != 0 && states.size() != 0) {
      return createFailureStatus(ctx, stateMachine);
    }
    return ctx.createSuccessStatus();
  }
  
  /**
   * @param ctx
   * @param state
   */
  private IStatus createFailureStatus(IValidationContext ctx, StateMachine stateMachine) {
    return ctx.createFailureStatus(new Object[] {stateMachine.getName()});
  }
}

