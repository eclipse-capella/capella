package org.polarsys.capella.test.business.queries.ju.views;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.test.business.queries.ju.QueryResult;
import org.polarsys.capella.test.business.queries.ju.ResultItem.Kind;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

public class BqContentProvider implements ITreeContentProvider {

  Object input;

  @Override
  public Object[] getElements(Object inputElement) {
    return ((List) inputElement).toArray();
  }

  @Override
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof IFile) {
      if (((IFile) parentElement).getFileExtension().equals("testSuite")) {
        String serializedData = IResourceHelpers.readFileAsString((IFile) parentElement);
        List<QueryResult> testCases = QueryResult.deserialize(serializedData);
        return testCases.toArray();
      }
    } else if (parentElement instanceof QueryResult) {
      QueryResult query = (QueryResult) parentElement;
      Session session = SessionManager.INSTANCE.getSessions().iterator().next();
      return query.getResults(session).stream().filter(x -> x.getKind() != Kind.OK).collect(Collectors.toList()).toArray();
    }
    return Arrays.asList().toArray();
  }

  @Override
  public Object getParent(Object element) {
    return null;
  }

  @Override
  public boolean hasChildren(Object element) {
    return true;
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    ITreeContentProvider.super.inputChanged(viewer, oldInput, newInput);
    input = newInput;
  }

}
