package org.polarsys.capella.test.business.queries.ju;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.core.model.handler.helpers.SemanticResourcesScope;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.kitalpha.emde.model.Element;

public class QueryAdapterFactory implements IAdapterFactory {

  @Override
  public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {

    if (adaptableObject instanceof QueryResult) {
      Session session = SessionManager.INSTANCE.getSessions().iterator().next();
      IScope capellaSemanticResourceScope = new SemanticResourcesScope(session.getTransactionalEditingDomain().getResourceSet());
      EObject element =  (EObject)IdManager.getInstance().getEObject(((QueryResult) adaptableObject).getInputId(), capellaSemanticResourceScope);
      return (T) element;
    }

    if (adaptableObject instanceof ResultItem) {
      EObject element = ((ResultItem) adaptableObject).getResult();
      return (T) element;
    }
    return null;
  }

  @Override
  public Class<?>[] getAdapterList() {
    return new Class[] { Element.class };
  }

}
