/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.fragmentation.ju.utils;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.ProjectHelper;
import org.polarsys.capella.test.fragmentation.ju.messages.FragmentationMessages;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * Utility class for fragmentation testing purpose.
 */
public class FragmentUtils {

  /**
   * Compare the {@link Resource} of a given {@link Resource} with the expected one.
   * 
   * @param object_p
   *          the {@link EObject} to check
   * @param expectedResource_p
   *          expected {@link Resource}
   */
  public static void checkEResource(EObject object_p, Resource expectedResource_p) {

    Resource resource = object_p.eResource();

    assertEquals(NLS.bind(FragmentationMessages.fragmentUtils_resourceDoesNotMatch,
        new Object[] { EObjectExt.getText(object_p), resource.getURI().toString(),
            expectedResource_p.getURI().toString() }),
        resource, expectedResource_p);

    return;
  }

  /**
   * Return a list of {@link EObject} with non-null eResource feature.
   * 
   * @param resource
   *          the target {@link Resource}
   * @return the list of {@link EObject} with non-null eResource feature.
   */
  public static List<EObject> getFragmentedRootObjects(Resource resource) {

    List<EObject> result = new ArrayList<EObject>();

    EObject root = resource.getContents().get(0);
    TreeIterator<EObject> it = root.eAllContents();
    EObject current = null;
    while (it.hasNext()) {
      current = it.next();
      if (null != getDirectResource(current)) {
        result.add(current);
      }
    }

    return result;
  }

  /**
   * Return the number of children (direct and indirect) of a given {@link EObject}.
   * 
   * @param eObject_p
   *          the target {@link EObject}.
   * @return the number of children.
   */
  static public int getNumberOfProperEObjects(EObject eObject_p) {
    return getProperContents(eObject_p).size();
  }

  /**
   * Return all proper contents of a given {@link EObject} as a {@link List}
   * 
   * @param eObject_p
   *          the target {@link EObject}
   * @return a {@link List} of all its proper contents.
   */
  public static List<EObject> getProperContents(EObject eObject_p) {

    ArrayList<EObject> result = new ArrayList<EObject>();

    Iterator<EObject> it = EcoreUtil.getAllProperContents(eObject_p, true);
    while (it.hasNext()) {
      result.add(it.next());
    }

    return result;
  }

  /**
   * See code
   * 
   * @param eObject_p
   * @return
   */
  public static Map<EObject, Integer> getProperContentsRefCount(EObject eObject_p) {

    Map<EObject, Integer> result = new HashMap<EObject, Integer>();
    ResourceSet resourceSet = eObject_p.eResource().getResourceSet();

    List<EObject> list = getProperContents(eObject_p);
    list.add(eObject_p);

    int count = -1;
    for (EObject eObject : list) {

      count = 0;
      Map<EObject, Set<EStructuralFeature>> map = new HashMap<EObject, Set<EStructuralFeature>>();
      for (Setting setting : EcoreUtil.UsageCrossReferencer.find(eObject, resourceSet)) {
        if (!map.containsKey(setting.getEObject())) {
          Set<EStructuralFeature> set = new HashSet<EStructuralFeature>();
          map.put(setting.getEObject(), set);
        }
        map.get(setting.getEObject()).add(setting.getEStructuralFeature());
      }
      for (EObject eobj : map.keySet()) {
        Set<EStructuralFeature> set = map.get(eobj);
        count += set.size();
      }

      result.put(eObject, Integer.valueOf(count));
    }

    return result;
  }

  /**
   * see code, for test purpose only.
   * 
   * @param root_p
   * @param old_p
   * @param new_p
   */
  public static void compareRefCountForTest(EObject root_p, Map<EObject, Integer> old_p, Map<EObject, Integer> new_p,
      boolean fragment_p) {
    Iterator<Entry<EObject, Integer>> it = old_p.entrySet().iterator();
    Entry<EObject, Integer> entry = null;
    EObject current = null;
    int old = -1;
    int neww = -1;
    while (it.hasNext()) {
      entry = it.next();
      current = entry.getKey();

      old = entry.getValue().intValue();
      neww = new_p.get(current).intValue();

      if (current == root_p) {
        if (fragment_p) { // models...
          neww -= 1 + (getLinkedDRepresentation(current).isEmpty() ? 0 : 1);
        } else {
          neww += 1 + (getLinkedDRepresentation(current).isEmpty() ? 0 : 1);
        }
      }

      assertEquals(
          NLS.bind(FragmentationMessages.fragmentUtils_numberOfRefDoesNotMatch,
              EObjectExt.getText(current)),
          old, neww);
    }
  }

  /**
   * All the {@link DRepresentation} on this object and its proper children
   * 
   * @param eObject_p
   * @return
   */
  static public List<DRepresentation> getLinkedDRepresentation(EObject eObject_p) {

    Session session = SessionManager.INSTANCE.getSession(eObject_p);

    List<DRepresentation> result = new ArrayList<DRepresentation>();

    result.addAll(DialectManager.INSTANCE.getRepresentations(eObject_p, session));
    Iterator<EObject> it = EcoreUtil.getAllProperContents(eObject_p, true);
    while (it.hasNext()) {
      final EObject root = it.next();
      result.addAll(DialectManager.INSTANCE.getRepresentations(root, session));
    }

    return result;
  }

  /**
   * All the {@link DRepresentationDescriptor} on this object and its proper children
   * 
   * @param eObject_p
   * @return
   */
  static public List<DRepresentationDescriptor> getLinkedDRepresentationDescriptor(EObject eObject_p) {

    Session session = SessionManager.INSTANCE.getSession(eObject_p);

    List<DRepresentationDescriptor> result = new ArrayList<DRepresentationDescriptor>();

    // aici e bugul
    result.addAll(DialectManager.INSTANCE.getRepresentationDescriptors(eObject_p, session));
    Iterator<EObject> it = EcoreUtil.getAllProperContents(eObject_p, true);
    while (it.hasNext()) {
      final EObject root = it.next();
      result.addAll(DialectManager.INSTANCE.getRepresentationDescriptors(root, session));
    }

    return result;
  }

  /**
   * Return the resources attribute of a given {@link EObject}.
   * 
   * @param eObject_p
   * @return the {@link Resource} whether found, <code>null</code> otherwise.
   */
  static public Resource getDirectResource(EObject eObject_p) {
    return ((InternalEObject) eObject_p).eDirectResource();
  }

  /**
   * Get the resource that contains the analysis which contains the specified object.
   * 
   * @param eobject_p
   *          The object to search
   * @return the corresponding resource if found, false otherwise.
   */
  static public Set<Resource> getAirdResourceWithAnalysisOn(final EObject eobject_p) {

    Session session = SessionManager.INSTANCE.getSession(eobject_p);

    Set<Resource> results = new HashSet<Resource>();

    if ((null != session) && (session instanceof DAnalysisSession)) {
      for (final Resource resource : session.getAllSessionResources()) {
        for (final DAnalysis analysis : getAnalyses(resource)) {
          if (analysis.getModels().contains(eobject_p)) {
            results.add(resource);
          }
        }
      }
    }

    return results;
  }

  static private Collection<DAnalysis> getAnalyses(final Resource airdResource_p) {
    final Collection<DAnalysis> analyses = new ArrayList<DAnalysis>();
    for (EObject root : airdResource_p.getContents()) {
      if (root instanceof DAnalysis) {
        analyses.add((DAnalysis) root);
      }
    }

    return analyses;
  }

  public static void copyFragmentFileWithSpace(IProject project_p, String fileName_p, String plugin_p,
      String fragmentFolder_p) {

    ProjectHelper.refreshProject(project_p, new NullProgressMonitor());

    String fileRelativePath_p = plugin_p + fragmentFolder_p + ICommonConstants.SLASH_CHARACTER + fileName_p;
    IFolder folder = project_p.getFolder(fragmentFolder_p);

    ProjectHelper.refreshProject(project_p, new NullProgressMonitor());

    if (!folder.exists()) {
      try {
        folder.create(true, true, new NullProgressMonitor());

      } catch (CoreException exception_p) {
        exception_p.printStackTrace();
      }
    }
    ProjectHelper.refreshProject(project_p, new NullProgressMonitor());

    writeFile(project_p.getName() + ICommonConstants.SLASH_CHARACTER + fragmentFolder_p
        + ICommonConstants.SLASH_CHARACTER + fileName_p, true, fileRelativePath_p);

    ProjectHelper.refreshProject(project_p, new NullProgressMonitor());

    GuiActions.flushASyncGuiThread();

  }

  static void writeFile(String filePath_p, boolean ensureFolders_p, String sourceFile_p) {
    try {

      GuiActions.flushASyncGuiThread();

      URI source = URI.createPlatformPluginURI(sourceFile_p, false);
      URL url = FileLocator.resolve(new URL(source.toString()));

      URI aTarget = URI.createPlatformResourceURI(filePath_p, false);
      URL url2 = FileLocator.resolve(new URL(aTarget.toString()));

      File asource = new File(url.getFile());
      File target = new File(url2.getFile());

      if (url.getProtocol().equals("jar")) {//$NON-NLS-1$
        JarURLConnection jarConnection = (JarURLConnection) url.openConnection();
        InputStream is = jarConnection.getInputStream();
        FileOutputStream fos = new FileOutputStream(target);
        while (is.available() > 0) {
          fos.write(is.read());
        }
        fos.close();
        is.close();
      } else {
        copyFile(asource, target);
      }
      GuiActions.flushASyncGuiThread();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static void copyFile(final File srcFile, final File destFile) throws IOException {
    FileInputStream fis = null;
    FileOutputStream fos = null;
    FileChannel iChannel = null;
    FileChannel oChannel = null;

    try {
      fis = new FileInputStream(srcFile);
      fos = new FileOutputStream(destFile);
      iChannel = fis.getChannel();
      oChannel = fos.getChannel();

      long size = iChannel.size();
      long length = 0;
      long pos = 0;

      while (pos < size) {
        long rr = size - pos;
        length = Math.min(100000, rr);
        long res = oChannel.transferFrom(iChannel, pos, length);
        if (res == 0) {
          break;
        }
        pos += res;
      }

    } finally {
      iChannel.close();
      oChannel.close();
      fis.close();
      fos.close();
    }

  }

  /**
   * set attribute on the file
   * 
   * @param file_p
   * @param value_p.
   *          true->RedaObly, false>ReadWrite
   * @throws CoreException
   */
  private static void setIFileAttribute(IFile file_p, boolean bReadOnly) throws CoreException {
    ResourceAttributes resourceAttribute = new ResourceAttributes();
    resourceAttribute.setReadOnly(bReadOnly);
    try {
      file_p.setResourceAttributes(resourceAttribute);
    } catch (CoreException e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * Set the read only attribute for the file
   * 
   * @param file_p
   *          the file to set the read only value
   * @throws CoreException
   */
  public static void setIFileReadOnly(IFile file_p) throws CoreException {
    setIFileAttribute(file_p, true);
  }

  /**
   * Set the read only attribute for the the list of files
   * 
   * @param file_p
   *          the file to set the read only value
   * @throws CoreException
   */
  public static void setIFileListReadOnly(Collection<IFile> fileList_p) throws CoreException {
    for (IFile currentFile : fileList_p) {
      setIFileReadOnly(currentFile);
    }
  }

  /**
   * Set the read write attribute for a file
   * 
   * @param file_p
   *          the file to set the read write value
   * @throws CoreException
   */
  public static void setIFileWrite(IFile file_p) throws CoreException {
    setIFileAttribute(file_p, false);
  }

  /**
   * Set the read write attribute for a list of file
   * 
   * @param fileList_p
   *          list of files to set read write attribute
   * @throws CoreException
   */
  public static void setIFileListWrite(Collection<IFile> fileList_p) throws CoreException {
    for (IFile currentFile : fileList_p) {
      setIFileWrite(currentFile);
    }
  }

}
