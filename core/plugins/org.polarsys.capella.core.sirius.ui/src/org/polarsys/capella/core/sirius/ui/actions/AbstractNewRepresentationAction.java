package org.polarsys.capella.core.sirius.ui.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;

public abstract class AbstractNewRepresentationAction extends BaseSelectionListenerAction{

  protected boolean isCanceled; 
  protected EObject selectedEObject;
  protected Session session;
  protected boolean openRepresentation;
  protected String message;
  
  protected AbstractNewRepresentationAction(String text) {
    super(text);
  }

  protected String getDescriptionLabel(RepresentationDescription description) {
    return MessageTranslator.INSTANCE.getMessage(description, new IdentifiedElementQuery(description).getLabel());
  }

  protected ImageDescriptor getDescriptionImageDescriptor(RepresentationDescription description) {
    ImageDescriptor imageDescriptor = EObjectLabelProviderHelper.getImage(description.eClass(),(AdapterFactoryEditingDomain)TransactionUtil.getEditingDomain(description));

    if (null == imageDescriptor) {
      imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
    }
    return imageDescriptor;
  }
  
  public boolean isCanceled() {
    return isCanceled;
  }

  public void setCanceled(boolean isCanceled) {
    this.isCanceled = isCanceled;
  }
}
