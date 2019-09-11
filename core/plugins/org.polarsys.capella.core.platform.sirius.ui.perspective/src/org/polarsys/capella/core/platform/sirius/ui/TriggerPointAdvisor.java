package org.polarsys.capella.core.platform.sirius.ui;

import java.util.Set;

import org.eclipse.ui.activities.IActivity;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IIdentifier;
import org.eclipse.ui.activities.WorkbenchTriggerPointAdvisor;

public class TriggerPointAdvisor extends WorkbenchTriggerPointAdvisor {

  @Override
  public boolean computeEnablement(IActivityManager activityManager, IIdentifier identifier) {
    // Activities without expressions are the one displayed to the user through Preferences/Capabilities
    // Activities with expressions are Programmatic ones

    // First sample, to refine.
    // an identifier is disabled if a programmatic activity is disabled or if user has disabled it
    final Set<String> activityIds = identifier.getActivityIds();
    if (activityIds.isEmpty()) {
      return true;
    }

    for (String activityId : activityIds) {
      IActivity activity = activityManager.getActivity(activityId);
      if (activity.getExpression() != null && !activity.isEnabled()) {
        return false;
      }
    }

    for (String activityId : activityIds) {
      IActivity activity = activityManager.getActivity(activityId);
      if (activity.getExpression() == null && !activity.isEnabled()) {
        return false;
      }
    }

    return true;
  }

}
