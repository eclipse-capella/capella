package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class DWF_DF_14_Resolver extends AbstractCapellaMarkerResolution {

  /**
   * Synchronize invoked operation according to sequence message kind
   */
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);

    if (!modelElements.isEmpty()) {

      // read write command
      AbstractReadWriteCommand collectElementsCommand = new AbstractReadWriteCommand() {

        public void run() {
          for (EObject object : modelElements) {
            if (object instanceof FunctionalChainInvolvementLink) {
              FunctionalChainInvolvementLink msg = (FunctionalChainInvolvementLink) object;
              msg.getExchangedItems().removeAll(FunctionalChainExt.getInvalidExchangeItems(msg));
            }
          }
        }

      };

      // execute the command
      TransactionHelper.getExecutionManager(modelElements).execute(collectElementsCommand);

      try {
        marker.delete();
      } catch (CoreException exception) {
        StatusManager.getManager().handle(new Status(IStatus.ERROR, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), exception.getMessage(), exception));
      }

    }

  }

}
