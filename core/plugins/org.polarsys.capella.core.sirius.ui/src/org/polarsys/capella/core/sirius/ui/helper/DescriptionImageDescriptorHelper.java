package org.polarsys.capella.core.sirius.ui.helper;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class DescriptionImageDescriptorHelper {

  public static ImageDescriptor getDescriptionImageDescriptor(RepresentationDescription description) {
    ImageDescriptor imageDescriptor = null;
    // Handle specific representations : Table ones.
    if (description instanceof CrossTableDescription) {
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(TableUIPlugin.ID,
          "/icons/full/obj16/CrossTableDescription.gif"); //$NON-NLS-1$

    } else if (description instanceof EditionTableDescription) {
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(TableUIPlugin.ID, "/icons/full/obj16/DTable.gif"); //$NON-NLS-1$

    } else if (description instanceof SequenceDiagramDescription) {
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.sirius.diagram.sequence.edit", //$NON-NLS-1$
          "/icons/full/obj16/TSequenceDiagram.gif"); //$NON-NLS-1$

    } else {
      // Standard diagram.
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(DiagramUIPlugin.ID,
          "/icons/full/obj16/DDiagram.gif"); //$NON-NLS-1$
    }

    if (null == imageDescriptor) {
      imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
    }
    return imageDescriptor;
  }
}
