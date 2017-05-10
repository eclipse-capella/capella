//Generated with EGF 1.4.1.v20161010-1511
package org.polarsys.capella.common.extension.migration.egf;

import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;
import org.eclipse.egf.common.helper.*;

public class replaceOldImports {

  public replaceOldImports() {
    //Here is the constructor
    // add initialisation of the pattern variables (declaration has been already done).
  }

  public void generate(Object argument) throws Exception {
    InternalPatternContext ctx = (InternalPatternContext) argument;
    IQuery.ParameterDescription paramDesc = null;
    Map<String, String> queryCtx = null;
    Node.Container currentNode = ctx.getNode();
    List<Object> iFileList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)

    for (Object iFileParameter : iFileList) {

      this.iFile = (org.eclipse.core.resources.IFile) iFileParameter;

      if (preCondition(ctx)) {
        ctx.setNode(new Node.Container(currentNode, getClass()));
        orchestration((PatternContext) argument);

      }
    }
    if (ctx.useReporter()) {
      ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
    }
  }

  public String orchestration(PatternContext ctx) throws Exception {
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    Node.Container currentNode = ictx.getNode();
    method_body(new StringBuffer(), ictx);
    ictx.setNode(currentNode);
    if (ictx.useReporter()) {
      Map<String, Object> parameterValues = new HashMap<String, Object>();
      parameterValues.put("iFile", this.iFile);
      String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
      String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
      ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
    }
    return null;
  }

  protected void method_body(final StringBuffer out, final PatternContext ctx) throws Exception {
    new org.polarsys.capella.common.extension.migration.egf.FileModificationTask().doExecute(iFile,
        "import org.polarsys.capella.common.mdsofa.emf.extension.IChildCreationExtender;",
        "import org.eclipse.emf.edit.provider.IChildCreationExtender;");
    new org.polarsys.capella.common.extension.migration.egf.FileModificationTask().doExecute(iFile,
        "import org.polarsys.capella.common.mdsofa.emf.extension.ExtensionModelManager;",
        "import org.polarsys.kitalpha.emde.ui.ExtensionModelManager;");
    //new org.polarsys.capella.common.extension.migration.egf.FileModificationTask().doExecute(iFile, "import org.polarsys.capella.common.mdsofa.emf.xmi.SAXExtensionXMIHandler;", "import org.polarsys.kitalpha.emde.xmi.SAXExtensionXMIHandler;");
    //new org.polarsys.capella.common.extension.migration.egf.FileModificationTask().doExecute(iFile, "import org.polarsys.capella.common.mdsofa.emf.xmi.XMIExtensionHelperImpl;", "import org.polarsys.kitalpha.emde.xmi.XMIExtensionHelperImpl;");
    //new org.polarsys.capella.common.extension.migration.egf.FileModificationTask().doExecute(iFile, "import org.polarsys.capella.common.mdsofa.emf.xmi.XMIExtensionLoadImpl;", "import org.polarsys.kitalpha.emde.xmi.XMIExtensionLoadImpl;");

    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "body", out.toString());
  }

  public boolean preCondition(PatternContext ctx) throws Exception {
    return true;
  }

  protected org.eclipse.core.resources.IFile iFile;

  public void set_iFile(org.eclipse.core.resources.IFile iFile) {
    this.iFile = iFile;
  }

  public Map<String, Object> getParameters() {
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("iFile", this.iFile);
    return parameters;
  }

}
