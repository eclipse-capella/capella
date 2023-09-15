//Generated with EGF 1.6.4.202309111303
package org.polarsys.capella.common.tig.model;

import org.eclipse.egf.emf.pattern.base.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.ecore.genmodel.impl.*;
import org.eclipse.emf.codegen.ecore.genmodel.generator.*;
import org.eclipse.emf.codegen.util.*;
import org.eclipse.emf.ecore.util.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;

public class Class extends org.polarsys.kitalpha.emde.egf.model.Class {
  protected static String nl;

  public static synchronized Class create(String lineSeparator) {
    nl = lineSeparator;
    Class result = new Class();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "package ";
  protected final String TEXT_3 = ";";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL
      + " * An implementation of the model object '<em><b>";
  protected final String TEXT_6 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_7 = NL + " * <p>" + NL + " * The following features are implemented:" + NL + " * </p>"
      + NL + " * <ul>";
  protected final String TEXT_8 = NL + " *   <li>{@link ";
  protected final String TEXT_9 = "#";
  protected final String TEXT_10 = " <em>";
  protected final String TEXT_11 = "</em>}</li>";
  protected final String TEXT_12 = NL + " * </ul>";
  protected final String TEXT_13 = NL + " *";
  protected final String TEXT_14 = NL + " * ";
  protected final String TEXT_15 = NL + " * @generated" + NL + " */";
  protected final String TEXT_16 = NL + "@Deprecated";
  protected final String TEXT_17 = NL + "@SuppressWarnings(\"deprecation\")";
  protected final String TEXT_18 = NL + "public";
  protected final String TEXT_19 = " abstract";
  protected final String TEXT_20 = " class ";
  protected final String TEXT_21 = NL + "public interface ";
  protected final String TEXT_22 = NL + "{";
  protected final String TEXT_23 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_24 = " copyright = ";
  protected final String TEXT_25 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_26 = " mofDriverNumber = \"";
  protected final String TEXT_27 = "\";";
  protected final String TEXT_28 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
      + "\tprivate static final long serialVersionUID = 1L;" + NL;
  protected final String TEXT_29 = NL + "\t/**" + NL
      + "\t * An array of objects representing the values of non-primitive features." + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_30 = NL + "\t@";
  protected final String TEXT_31 = NL + "\tprotected Object[] ";
  protected final String TEXT_32 = ";" + NL;
  protected final String TEXT_33 = NL + "\t/**" + NL
      + "\t * A bit field representing the indices of non-primitive feature values." + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_34 = NL + "\tprotected int ";
  protected final String TEXT_35 = NL + "\t/**" + NL
      + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set."
      + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL
      + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_36 = " = 0;" + NL;
  protected final String TEXT_37 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_38 = "() <em>";
  protected final String TEXT_39 = "</em>}' array accessor." + NL
      + "\t * This is specialized for the more specific element type known in this context." + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_40 = "()";
  protected final String TEXT_41 = NL + "\t * ";
  protected final String TEXT_42 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_43 = NL + "\t@Deprecated";
  protected final String TEXT_44 = NL + "\t@SuppressWarnings(\"rawtypes\")";
  protected final String TEXT_45 = NL + "\tprotected static final ";
  protected final String TEXT_46 = "[] ";
  protected final String TEXT_47 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_48 = " [0]";
  protected final String TEXT_49 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_50 = " = ";
  protected final String TEXT_51 = ".getFeatureID(";
  protected final String TEXT_52 = ") - ";
  protected final String TEXT_53 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->";
  protected final String TEXT_54 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_55 = NL + "\tprivate static final int ";
  protected final String TEXT_56 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
      + "\tprivate static final int EOPERATION_OFFSET_CORRECTION = ";
  protected final String TEXT_57 = ".getOperationID(";
  protected final String TEXT_58 = "public";
  protected final String TEXT_59 = "protected";
  protected final String TEXT_60 = " ";
  protected final String TEXT_61 = "()" + NL + "\t{";
  protected final String TEXT_62 = NL + "\t\t";
  protected final String TEXT_63 = " |= ";
  protected final String TEXT_64 = "_EFLAG";
  protected final String TEXT_65 = "_DEFAULT";
  protected final String TEXT_66 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_67 = NL + "\t@Override";
  protected final String TEXT_68 = NL + "\tprotected ";
  protected final String TEXT_69 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_70 = ";" + NL + "\t}" + NL;
  protected final String TEXT_71 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_72 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_73 = NL + "\tpublic ";
  protected final String TEXT_74 = " eInverseAdd(";
  protected final String TEXT_75 = " otherEnd, int featureID, ";
  protected final String TEXT_76 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_77 = ")" + NL + "\t\t{";
  protected final String TEXT_78 = NL + "\t\t\tcase ";
  protected final String TEXT_79 = ":";
  protected final String TEXT_80 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_81 = "(";
  protected final String TEXT_82 = ".InternalMapView";
  protected final String TEXT_83 = ")";
  protected final String TEXT_84 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_85 = NL + "\t\t\t\treturn (";
  protected final String TEXT_86 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_87 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL
      + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_88 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_89 = "((";
  protected final String TEXT_90 = ")otherEnd, msgs);";
  protected final String TEXT_91 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_92 = ", msgs);";
  protected final String TEXT_93 = NL + "\t\t\t\t";
  protected final String TEXT_94 = " = (";
  protected final String TEXT_95 = ")eVirtualGet(";
  protected final String TEXT_96 = ");";
  protected final String TEXT_97 = "basicGet";
  protected final String TEXT_98 = "();";
  protected final String TEXT_99 = NL + "\t\t\t\tif (";
  protected final String TEXT_100 = " != null)";
  protected final String TEXT_101 = NL + "\t\t\t\t\tmsgs = ";
  protected final String TEXT_102 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_103 = ", null, msgs);";
  protected final String TEXT_104 = ".eInverseRemove(this, ";
  protected final String TEXT_105 = ", ";
  protected final String TEXT_106 = ".class, msgs);";
  protected final String TEXT_107 = NL + "\t\t\tdefault:";
  protected final String TEXT_108 = NL + "\t\t}";
  protected final String TEXT_109 = "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_110 = "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_111 = NL + "\t}" + NL;
  protected final String TEXT_112 = " eInverseRemove(";
  protected final String TEXT_113 = ")((";
  protected final String TEXT_114 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_115 = ".Internal.Wrapper)";
  protected final String TEXT_116 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_117 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_118 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_119 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_120 = "(msgs);";
  protected final String TEXT_121 = "(null, msgs);";
  protected final String TEXT_122 = "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_123 = "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_124 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_125 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_126 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_127 = "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_128 = "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_129 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL
      + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_130 = NL + "\t\t\t\treturn ";
  protected final String TEXT_131 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_132 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_133 = "());";
  protected final String TEXT_134 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_135 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_136 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_137 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_138 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_139 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_140 = "().map();";
  protected final String TEXT_141 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_142 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_143 = ".Internal)";
  protected final String TEXT_144 = "()).getWrapper();";
  protected final String TEXT_145 = "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_146 = "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_147 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_148 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_149 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL
      + "\t\tswitch (featureID";
  protected final String TEXT_150 = NL + "\t\t\t\t((";
  protected final String TEXT_151 = ".Internal)((";
  protected final String TEXT_152 = "()).featureMap()).set(newValue);";
  protected final String TEXT_153 = "()).set(newValue);";
  protected final String TEXT_154 = ".Setting)((";
  protected final String TEXT_155 = "()).eMap()).set(newValue);";
  protected final String TEXT_156 = ".Setting)";
  protected final String TEXT_157 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_158 = "().addAll((";
  protected final String TEXT_159 = "<? extends ";
  protected final String TEXT_160 = ">";
  protected final String TEXT_161 = ")newValue);";
  protected final String TEXT_162 = NL + "\t\t\t\tset";
  protected final String TEXT_163 = "(((";
  protected final String TEXT_164 = ")newValue).";
  protected final String TEXT_165 = NL + "\t\t\t\t// begin-extension-code" + NL
      + "\t\t\t\tif (newValue == null || newValue instanceof ";
  protected final String TEXT_166 = ") {" + NL + "\t\t\t\t// end-extension-code";
  protected final String TEXT_167 = NL + "\t\t\t\t\tset";
  protected final String TEXT_168 = NL + "\t\t\t\t// begin-extension-code" + NL + "\t\t\t\t}" + NL
      + "\t\t\t\t// end-extension-code";
  protected final String TEXT_169 = NL + "          set";
  protected final String TEXT_170 = "(newValue);";
  protected final String TEXT_171 = NL + "\t\t\t\treturn;";
  protected final String TEXT_172 = "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_173 = "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_174 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_175 = " baseClass)" + NL + "\t{";
  protected final String TEXT_176 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_177 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_178 = ")" + NL + "\t\t\t{";
  protected final String TEXT_179 = NL + "\t\t\t\tcase ";
  protected final String TEXT_180 = ": return ";
  protected final String TEXT_181 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_182 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL
      + "\t}";
  protected final String TEXT_183 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_184 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_185 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_186 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_187 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL
      + "\t}" + NL;
  protected final String TEXT_188 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_189 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_190 = NL
      + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL
      + "\t\t}";
  protected final String TEXT_191 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_192 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL
      + "\t}" + NL;
  protected final String TEXT_193 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_194 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_195 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL
      + "\t\t";
  protected final String TEXT_196 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_197 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL
      + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_198 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_199 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL
      + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_200 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL
      + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_201 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_202 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_203 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL
      + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_204 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_205 = "\"unchecked\"";
  protected final String TEXT_206 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_207 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_208 = " arguments) throws ";
  protected final String TEXT_209 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_210 = NL + "\t\t\t\ttry" + NL + "\t\t\t\t{";
  protected final String TEXT_211 = "arguments.get(";
  protected final String TEXT_212 = ").";
  protected final String TEXT_213 = ");" + NL + "\t\t\t\t";
  protected final String TEXT_214 = "return null;";
  protected final String TEXT_215 = "return ";
  protected final String TEXT_216 = "new ";
  protected final String TEXT_217 = NL + "\t\t\t\t}" + NL + "\t\t\t\tcatch (";
  protected final String TEXT_218 = " throwable)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tthrow new ";
  protected final String TEXT_219 = "(throwable);" + NL + "\t\t\t\t}";
  protected final String TEXT_220 = "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_221 = "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_222 = NL + "\tpublic String toString()" + NL + "\t{" + NL
      + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\t";
  protected final String TEXT_223 = " result = new ";
  protected final String TEXT_224 = "(super.toString());";
  protected final String TEXT_225 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_226 = ": \");";
  protected final String TEXT_227 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_228 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_229 = ")) result.append(eVirtualGet(";
  protected final String TEXT_230 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_231 = NL + "\t\tif (";
  protected final String TEXT_232 = " & ";
  protected final String TEXT_233 = "_ESETFLAG) != 0";
  protected final String TEXT_234 = "ESet";
  protected final String TEXT_235 = ") result.append((";
  protected final String TEXT_236 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_237 = ") result.append(";
  protected final String TEXT_238 = "_EFLAG_VALUES[(";
  protected final String TEXT_239 = "_EFLAG) >>> ";
  protected final String TEXT_240 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_241 = "); else result.append(\"<unset>\");";
  protected final String TEXT_242 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_243 = "));";
  protected final String TEXT_244 = NL + "\t\tresult.append((";
  protected final String TEXT_245 = "_EFLAG) != 0);";
  protected final String TEXT_246 = NL + "\t\tresult.append(";
  protected final String TEXT_247 = "_EFLAG_OFFSET]);";
  protected final String TEXT_248 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}"
      + NL;
  protected final String TEXT_249 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_250 = NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL
      + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_251 = " theKey = getKey();" + NL
      + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}"
      + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL
      + "\t * @generated" + NL + "\t */";
  protected final String TEXT_252 = NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;"
      + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->"
      + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_253 = " getKey()" + NL + "\t{";
  protected final String TEXT_254 = NL + "\t\treturn new ";
  protected final String TEXT_255 = "(getTypedKey());";
  protected final String TEXT_256 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_257 = NL + "\tpublic void setKey(";
  protected final String TEXT_258 = " key)" + NL + "\t{";
  protected final String TEXT_259 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_260 = "key);";
  protected final String TEXT_261 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_262 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_263 = ")key).";
  protected final String TEXT_264 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_265 = ")key);";
  protected final String TEXT_266 = " getValue()" + NL + "\t{";
  protected final String TEXT_267 = "(getTypedValue());";
  protected final String TEXT_268 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_269 = " setValue(";
  protected final String TEXT_270 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_271 = " oldValue = getValue();";
  protected final String TEXT_272 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_273 = "value);";
  protected final String TEXT_274 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_275 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_276 = ")value).";
  protected final String TEXT_277 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_278 = ")value);";
  protected final String TEXT_279 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_280 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_281 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_282 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_283 = NL + "} //";

  public Class() {
    //Here is the constructor
    StringBuffer stringBuffer = new StringBuffer();

    // add initialisation of the pattern variables (declaration has been already done).

  }

  public String generate(Object argument) throws Exception {
    final StringBuffer stringBuffer = new StringBuffer();

    InternalPatternContext ctx = (InternalPatternContext) argument;
    Map<String, String> queryCtx = null;
    IQuery.ParameterDescription paramDesc = null;
    Node.Container currentNode = ctx.getNode();

    List<Object> parameterList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)

    for (Object parameterParameter : parameterList) {

      this.parameter = (org.eclipse.emf.codegen.ecore.genmodel.GenClass) parameterParameter;

      if (preCondition(ctx)) {
        ctx.setNode(new Node.Container(currentNode, getClass()));
        orchestration(ctx);
      }

    }
    ctx.setNode(currentNode);
    if (ctx.useReporter()) {
      ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
    }

    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }

  public String orchestration(PatternContext ctx) throws Exception {
    InternalPatternContext ictx = (InternalPatternContext) ctx;

    super.orchestration(new SuperOrchestrationContext(ictx));

    if (ictx.useReporter()) {
      Map<String, Object> parameterValues = new HashMap<String, Object>();
      parameterValues.put("parameter", this.parameter);
      String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
      String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
      ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
    }
    return null;
  }

  public Map<String, Object> getParameters() {
    final Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("parameter", this.parameter);
    return parameters;
  }

  protected void method_doGenerate(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

    /**
     * Copyright (c) 2002-2019 IBM Corporation and others.
     * All rights reserved.   This program and the accompanying materials
     * are made available under the terms of the Eclipse Public License v2.0
     * which accompanies this distribution, and is available at
     * http://www.eclipse.org/legal/epl-v20.html
     *
     * Contributors:
     *   IBM - Initial API and implementation
     *   Alexander Fedorov <alexander.fedorov@arsysop.ru> - Bug 546714
     */

    final GenClass genClass = (GenClass) ((Object[]) argument)[0];
    final GenPackage genPackage = genClass.getGenPackage();
    final GenModel genModel = genPackage.getGenModel();
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    final boolean isInterface = Boolean.TRUE.equals(((Object[]) argument)[1]);
    final boolean isImplementation = Boolean.TRUE.equals(((Object[]) argument)[2]);
    final boolean useInterfaceOverrideAnnotation = genModel.useInterfaceOverrideAnnotation()
        && !(isInterface && isImplementation);
    final boolean isGWT = genModel.getRuntimePlatform() == GenRuntimePlatform.GWT;
    final boolean forceDefaultCase = genModel.isSwitchMissingDefaultCase();
    final String indentDefaultCase = forceDefaultCase ? "\t\t" : "";
    final String publicStaticFinalFlag = isImplementation ? "public static final " : "";
    final String singleWildcard = isJDK50 ? "<?>" : "";
    final String negativeOffsetCorrection = genClass.hasOffsetCorrection()
        ? " - " + genClass.getOffsetCorrectionField(null)
        : "";
    final String positiveOffsetCorrection = genClass.hasOffsetCorrection()
        ? " + " + genClass.getOffsetCorrectionField(null)
        : "";
    final String negativeOperationOffsetCorrection = genClass.hasOffsetCorrection() ? " - EOPERATION_OFFSET_CORRECTION"
        : "";
    final String positiveOperationOffsetCorrection = genClass.hasOffsetCorrection() ? " + EOPERATION_OFFSET_CORRECTION"
        : "";
    stringBuffer.append(TEXT_1);
    {
      //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern.base/egf/EMF_Pattern_Base.fcore#LogicalName=org.eclipse.egf.emf.pattern.base.HeaderJava" args="parameter:argument"%>

      InternalPatternContext ictx = (InternalPatternContext) ctx;
      new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
      stringBuffer.setLength(0);

      final Map<String, Object> callParameters = new HashMap<String, Object>();
      callParameters.put("argument", parameter);
      CallHelper.executeWithParameterInjection(
          "platform:/plugin/org.eclipse.egf.emf.pattern.base/egf/EMF_Pattern_Base.fcore#_XHLrsCwtEd-jc5T-XaRJlg",
          new ExecutionContext((InternalPatternContext) ctx), callParameters);
      stringBuffer.setLength(0);
    }

    if (isInterface) {
      stringBuffer.append(TEXT_2);
      stringBuffer.append(genPackage.getInterfacePackageName());
      stringBuffer.append(TEXT_3);
    } else {
      stringBuffer.append(TEXT_2);
      stringBuffer.append(genPackage.getClassPackageName());
      stringBuffer.append(TEXT_3);
    }
    stringBuffer.append(TEXT_4);
    genModel.markImportLocation(stringBuffer, genPackage);
    if (isImplementation) {
      genClass.addClassPsuedoImports();
    }
    stringBuffer.append(TEXT_4);
    if (isInterface) {
      stringBuffer.append(TEXT_4);
      {
        //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.interface.javadoc.override" args="genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

        InternalPatternContext ictx = (InternalPatternContext) ctx;
        new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
        stringBuffer.setLength(0);

        final Map<String, Object> callParameters = new HashMap<String, Object>();
        callParameters.put("genClass", genClass);
        callParameters.put("genPackage", genPackage);
        callParameters.put("genModel", genModel);
        callParameters.put("isJDK50", isJDK50);
        callParameters.put("isInterface", isInterface);
        callParameters.put("isImplementation", isImplementation);
        callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
        callParameters.put("isGWT", isGWT);
        callParameters.put("forceDefaultCase", forceDefaultCase);
        callParameters.put("indentDefaultCase", indentDefaultCase);
        callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
        callParameters.put("singleWildcard", singleWildcard);
        callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
        callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
        callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
        callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
        CallHelper.executeWithParameterInjection(
            "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_C2iO4eXDEeCxnsoQRwU99Q",
            new ExecutionContext((InternalPatternContext) ctx), callParameters);
        stringBuffer.setLength(0);
      }

      //Class/interface.javadoc.override.javajetinc
    } else {
      stringBuffer.append(TEXT_5);
      stringBuffer.append(genClass.getFormattedName());
      stringBuffer.append(TEXT_6);
      if (!genClass.getImplementedGenFeatures().isEmpty()) {
        stringBuffer.append(TEXT_7);
        for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
          stringBuffer.append(TEXT_8);
          stringBuffer.append(genClass.getQualifiedClassName());
          stringBuffer.append(TEXT_9);
          stringBuffer.append(genFeature.getGetAccessor());
          stringBuffer.append(TEXT_10);
          stringBuffer.append(genFeature.getFormattedName());
          stringBuffer.append(TEXT_11);
        }
        stringBuffer.append(TEXT_12);
      }
      stringBuffer.append(TEXT_13);
      if (genClass.hasImplicitAPITags()) {
        stringBuffer.append(TEXT_14);
        stringBuffer.append(genClass.getImplicitAPITags(genModel.getIndentation(stringBuffer)));
      }
      stringBuffer.append(TEXT_15);
    }
    if (isJDK50 && genClass.hasImplicitAPIDeprecatedTag()) {
      stringBuffer.append(TEXT_16);
    }
    if (isImplementation) {
      if (isJDK50 && !genClass.hasAPIDeprecatedTag()
          && GenModelUtil.hasImplicitAPIDeprecatedTag(genClass.getEGetGenFeatures(), genClass.getEIsSetGenFeatures(),
              genClass.getESetGenFeatures(), genClass.getEUnsetGenFeatures(), genClass.getEInverseAddGenFeatures(),
              genClass.getEInverseRemoveGenFeatures(), genClass.getEBasicRemoveFromContainerGenFeatures(),
              genClass.getToStringGenFeatures())) {
        stringBuffer.append(TEXT_17);
      }
      stringBuffer.append(TEXT_18);
      if (genClass.isAbstract()) {
        stringBuffer.append(TEXT_19);
      }
      stringBuffer.append(TEXT_20);
      stringBuffer.append(genClass.getClassName());
      stringBuffer.append(genClass.getTypeParameters().trim());
      stringBuffer.append(genClass.getClassExtends());
      stringBuffer.append(genClass.getClassImplements());
    } else {
      stringBuffer.append(TEXT_21);
      stringBuffer.append(genClass.getInterfaceName());
      stringBuffer.append(genClass.getTypeParameters().trim());
      stringBuffer.append(genClass.getInterfaceExtends());
    }
    stringBuffer.append(TEXT_22);
    if (genModel.hasCopyrightField()) {
      stringBuffer.append(TEXT_23);
      stringBuffer.append(publicStaticFinalFlag);
      stringBuffer.append(genModel.getImportedName("java.lang.String"));
      stringBuffer.append(TEXT_24);
      stringBuffer.append(genModel.getCopyrightFieldLiteral());
      stringBuffer.append(TEXT_3);
      stringBuffer.append(genModel.getNonNLS());
      stringBuffer.append(TEXT_4);
    }
    if (isImplementation && genModel.getDriverNumber() != null) {
      stringBuffer.append(TEXT_25);
      stringBuffer.append(genModel.getImportedName("java.lang.String"));
      stringBuffer.append(TEXT_26);
      stringBuffer.append(genModel.getDriverNumber());
      stringBuffer.append(TEXT_27);
      stringBuffer.append(genModel.getNonNLS());
      stringBuffer.append(TEXT_4);
    }
    if (isImplementation && genClass.isJavaIOSerializable()) {
      stringBuffer.append(TEXT_28);
    }
    if (isImplementation && genModel.isVirtualDelegation()) {
      String eVirtualValuesField = genClass.getEVirtualValuesField();
      if (eVirtualValuesField != null) {
        stringBuffer.append(TEXT_29);
        if (isGWT) {
          stringBuffer.append(TEXT_30);
          stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
        }
        stringBuffer.append(TEXT_31);
        stringBuffer.append(eVirtualValuesField);
        stringBuffer.append(TEXT_32);
      }
      {
        List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
        if (!eVirtualIndexBitFields.isEmpty()) {
          for (String eVirtualIndexBitField : eVirtualIndexBitFields) {
            stringBuffer.append(TEXT_33);
            if (isGWT) {
              stringBuffer.append(TEXT_30);
              stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
            }
            stringBuffer.append(TEXT_34);
            stringBuffer.append(eVirtualIndexBitField);
            stringBuffer.append(TEXT_32);
          }
        }
      }
    }
    if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled()
        && genModel.getBooleanFlagsReservedBits() == -1) {
      stringBuffer.append(TEXT_35);
      if (isGWT) {
        stringBuffer.append(TEXT_30);
        stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
      }
      stringBuffer.append(TEXT_34);
      stringBuffer.append(genModel.getBooleanFlagsField());
      stringBuffer.append(TEXT_36);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
      for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
        if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType()
            && !genFeature.isMapType()) {
          String rawListItemType = genFeature.getRawListItemType(genClass);
          int index = rawListItemType.indexOf('[');
          String head = rawListItemType;
          String tail = "";
          if (index != -1) {
            head = rawListItemType.substring(0, index);
            tail = rawListItemType.substring(index);
          }
          stringBuffer.append(TEXT_37);
          stringBuffer.append(genFeature.getGetArrayAccessor());
          stringBuffer.append(TEXT_38);
          stringBuffer.append(genFeature.getFormattedName());
          stringBuffer.append(TEXT_39);
          stringBuffer.append(genFeature.getGetArrayAccessor());
          stringBuffer.append(TEXT_40);
          if (genFeature.hasAPITags()) {
            stringBuffer.append(TEXT_41);
            stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
          }
          stringBuffer.append(TEXT_42);
          if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
            stringBuffer.append(TEXT_43);
          }
          if (genFeature.getQualifiedListItemType(genClass).contains("<")
              || genFeature.getArrayItemType(genClass).contains("<")) {
            stringBuffer.append(TEXT_44);
          }
          stringBuffer.append(TEXT_45);
          stringBuffer.append(rawListItemType);
          stringBuffer.append(TEXT_46);
          stringBuffer.append(genFeature.getUpperName());
          stringBuffer.append(TEXT_47);
          stringBuffer.append(head);
          stringBuffer.append(TEXT_48);
          stringBuffer.append(tail);
          stringBuffer.append(TEXT_32);
        }
      }
      for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
        stringBuffer.append(TEXT_4);
        {
          //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.declaredFieldGenFeature.override" args="genFeature:genFeature,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

          InternalPatternContext ictx = (InternalPatternContext) ctx;
          new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
          stringBuffer.setLength(0);

          final Map<String, Object> callParameters = new HashMap<String, Object>();
          callParameters.put("genFeature", genFeature);
          callParameters.put("genClass", genClass);
          callParameters.put("genPackage", genPackage);
          callParameters.put("genModel", genModel);
          callParameters.put("isJDK50", isJDK50);
          callParameters.put("isInterface", isInterface);
          callParameters.put("isImplementation", isImplementation);
          callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
          callParameters.put("isGWT", isGWT);
          callParameters.put("forceDefaultCase", forceDefaultCase);
          callParameters.put("indentDefaultCase", indentDefaultCase);
          callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
          callParameters.put("singleWildcard", singleWildcard);
          callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
          callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
          callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
          callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
          CallHelper.executeWithParameterInjection(
              "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0jpGTmJ-Ed-FqczH3ESmRw",
              new ExecutionContext((InternalPatternContext) ctx), callParameters);
          stringBuffer.setLength(0);
        }

        //Class/declaredFieldGenFeature.override.javajetinc
      }
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
      stringBuffer.append(TEXT_49);
      stringBuffer.append(genClass.getOffsetCorrectionField(null));
      stringBuffer.append(TEXT_50);
      stringBuffer.append(genClass.getQualifiedClassifierAccessor());
      stringBuffer.append(TEXT_51);
      stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
      stringBuffer.append(TEXT_52);
      stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
      stringBuffer.append(TEXT_32);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
      for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
        GenFeature reverseFeature = genFeature.getReverse();
        if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
          stringBuffer.append(TEXT_53);
          if (genFeature.hasAPITags()) {
            stringBuffer.append(TEXT_41);
            stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
          }
          stringBuffer.append(TEXT_54);
          if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
            stringBuffer.append(TEXT_43);
          }
          stringBuffer.append(TEXT_55);
          stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
          stringBuffer.append(TEXT_50);
          stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
          stringBuffer.append(TEXT_51);
          stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
          stringBuffer.append(TEXT_52);
          stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
          stringBuffer.append(TEXT_32);
        }
      }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection()
        && !genClass.getImplementedGenOperations().isEmpty()) {
      stringBuffer.append(TEXT_56);
      stringBuffer.append(genClass.getQualifiedClassifierAccessor());
      stringBuffer.append(TEXT_57);
      stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
      stringBuffer.append(TEXT_52);
      stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
      stringBuffer.append(TEXT_32);
    }
    if (isImplementation) {
      stringBuffer.append(TEXT_23);
      if (genModel.isPublicConstructors()) {
        stringBuffer.append(TEXT_58);
      } else {
        stringBuffer.append(TEXT_59);
      }
      stringBuffer.append(TEXT_60);
      stringBuffer.append(genClass.getClassName());
      stringBuffer.append(TEXT_61);
      stringBuffer.append(TEXT_4);
      {
        //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.egfCustom.constructor.override" args="genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

        InternalPatternContext ictx = (InternalPatternContext) ctx;
        new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
        stringBuffer.setLength(0);

        final Map<String, Object> callParameters = new HashMap<String, Object>();
        callParameters.put("genClass", genClass);
        callParameters.put("genPackage", genPackage);
        callParameters.put("genModel", genModel);
        callParameters.put("isJDK50", isJDK50);
        callParameters.put("isInterface", isInterface);
        callParameters.put("isImplementation", isImplementation);
        callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
        callParameters.put("isGWT", isGWT);
        callParameters.put("forceDefaultCase", forceDefaultCase);
        callParameters.put("indentDefaultCase", indentDefaultCase);
        callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
        callParameters.put("singleWildcard", singleWildcard);
        callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
        callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
        callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
        callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
        CallHelper.executeWithParameterInjection(
            "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_JLBM0HroEeC0XN9kbwkPYQ",
            new ExecutionContext((InternalPatternContext) ctx), callParameters);
        stringBuffer.setLength(0);
      }

      for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
        stringBuffer.append(TEXT_62);
        stringBuffer.append(genClass.getFlagsField(genFeature));
        stringBuffer.append(TEXT_63);
        stringBuffer.append(genFeature.getUpperName());
        stringBuffer.append(TEXT_64);
        if (!genFeature.isBooleanType()) {
          stringBuffer.append(TEXT_65);
        }
        stringBuffer.append(TEXT_3);
      }
      stringBuffer.append(TEXT_66);
      if (genModel.useClassOverrideAnnotation()) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_68);
      stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
      stringBuffer.append(TEXT_69);
      stringBuffer.append(genClass.getQualifiedClassifierAccessor());
      stringBuffer.append(TEXT_70);
    }
    if (isImplementation && genModel.isDynamicDelegation()) {
      stringBuffer.append(TEXT_4);
      {
        //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.dynamicDelegation" args="genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

        InternalPatternContext ictx = (InternalPatternContext) ctx;
        new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
        stringBuffer.setLength(0);

        final Map<String, Object> callParameters = new HashMap<String, Object>();
        callParameters.put("genClass", genClass);
        callParameters.put("genPackage", genPackage);
        callParameters.put("genModel", genModel);
        callParameters.put("isJDK50", isJDK50);
        callParameters.put("isInterface", isInterface);
        callParameters.put("isImplementation", isImplementation);
        callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
        callParameters.put("isGWT", isGWT);
        callParameters.put("forceDefaultCase", forceDefaultCase);
        callParameters.put("indentDefaultCase", indentDefaultCase);
        callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
        callParameters.put("singleWildcard", singleWildcard);
        callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
        callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
        callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
        callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
        CallHelper.executeWithParameterInjection(
            "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_WUeasCSWEemxeP6B0lLOpA",
            new ExecutionContext((InternalPatternContext) ctx), callParameters);
        stringBuffer.setLength(0);
      }

    }
    stringBuffer.append(TEXT_4);
    {
      //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.reflectiveDelegation.override" args="genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

      InternalPatternContext ictx = (InternalPatternContext) ctx;
      new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
      stringBuffer.setLength(0);

      final Map<String, Object> callParameters = new HashMap<String, Object>();
      callParameters.put("genClass", genClass);
      callParameters.put("genPackage", genPackage);
      callParameters.put("genModel", genModel);
      callParameters.put("isJDK50", isJDK50);
      callParameters.put("isInterface", isInterface);
      callParameters.put("isImplementation", isImplementation);
      callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
      callParameters.put("isGWT", isGWT);
      callParameters.put("forceDefaultCase", forceDefaultCase);
      callParameters.put("indentDefaultCase", indentDefaultCase);
      callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
      callParameters.put("singleWildcard", singleWildcard);
      callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
      callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
      callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
      callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
      CallHelper.executeWithParameterInjection(
          "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0kFyMGJ-Ed-FqczH3ESmRw",
          new ExecutionContext((InternalPatternContext) ctx), callParameters);
      stringBuffer.setLength(0);
    }

    //Class/reflectiveDelegation.override.javajetinc
    if (isImplementation) {
      new Runnable() {
        public void run() {
          GenClass classExtendsGenClass = genClass.getClassExtendsGenClass();
          List<GenFeature> classExtendsAllGenFeatures = classExtendsGenClass == null
              ? Collections.<GenFeature> emptyList()
              : classExtendsGenClass.getAllGenFeatures();
          for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
            stringBuffer.append(TEXT_4);
            {
              //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.genFeatureReified.override" args="genFeature:genFeature,classExtendsGenClass:classExtendsGenClass,classExtendsAllGenFeatures:classExtendsAllGenFeatures,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

              InternalPatternContext ictx = (InternalPatternContext) ctx;
              new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
              stringBuffer.setLength(0);

              final Map<String, Object> callParameters = new HashMap<String, Object>();
              callParameters.put("genFeature", genFeature);
              callParameters.put("classExtendsGenClass", classExtendsGenClass);
              callParameters.put("classExtendsAllGenFeatures", classExtendsAllGenFeatures);
              callParameters.put("genClass", genClass);
              callParameters.put("genPackage", genPackage);
              callParameters.put("genModel", genModel);
              callParameters.put("isJDK50", isJDK50);
              callParameters.put("isInterface", isInterface);
              callParameters.put("isImplementation", isImplementation);
              callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
              callParameters.put("isGWT", isGWT);
              callParameters.put("forceDefaultCase", forceDefaultCase);
              callParameters.put("indentDefaultCase", indentDefaultCase);
              callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
              callParameters.put("singleWildcard", singleWildcard);
              callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
              callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
              callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
              callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
              CallHelper.executeWithParameterInjection(
                  "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_odzp0LM-EeKd56X4hcZPSw",
                  new ExecutionContext((InternalPatternContext) ctx), callParameters);
              stringBuffer.setLength(0);
            }

            //Class/genFeatureReified.override.javajetinc
          }
        }
      }.run();
    }
    new Runnable() {
      public void run() {
        for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures()
            : genClass.getDeclaredGenFeatures())) {
          stringBuffer.append(TEXT_4);
          {
            //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.genFeature.override" args="genFeature:genFeature,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

            InternalPatternContext ictx = (InternalPatternContext) ctx;
            new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
            stringBuffer.setLength(0);

            final Map<String, Object> callParameters = new HashMap<String, Object>();
            callParameters.put("genFeature", genFeature);
            callParameters.put("genClass", genClass);
            callParameters.put("genPackage", genPackage);
            callParameters.put("genModel", genModel);
            callParameters.put("isJDK50", isJDK50);
            callParameters.put("isInterface", isInterface);
            callParameters.put("isImplementation", isImplementation);
            callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
            callParameters.put("isGWT", isGWT);
            callParameters.put("forceDefaultCase", forceDefaultCase);
            callParameters.put("indentDefaultCase", indentDefaultCase);
            callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
            callParameters.put("singleWildcard", singleWildcard);
            callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
            callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
            callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
            callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
            CallHelper.executeWithParameterInjection(
                "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0kO8IGJ-Ed-FqczH3ESmRw",
                new ExecutionContext((InternalPatternContext) ctx), callParameters);
            stringBuffer.setLength(0);
          }

          //Class/genFeature.override.javajetinc
        } //for
      }
    }.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations()
        : genClass.getDeclaredGenOperations())) {
      stringBuffer.append(TEXT_4);
      {
        //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.genOperation.override" args="genOperation:genOperation,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

        InternalPatternContext ictx = (InternalPatternContext) ctx;
        new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
        stringBuffer.setLength(0);

        final Map<String, Object> callParameters = new HashMap<String, Object>();
        callParameters.put("genOperation", genOperation);
        callParameters.put("genClass", genClass);
        callParameters.put("genPackage", genPackage);
        callParameters.put("genModel", genModel);
        callParameters.put("isJDK50", isJDK50);
        callParameters.put("isInterface", isInterface);
        callParameters.put("isImplementation", isImplementation);
        callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
        callParameters.put("isGWT", isGWT);
        callParameters.put("forceDefaultCase", forceDefaultCase);
        callParameters.put("indentDefaultCase", indentDefaultCase);
        callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
        callParameters.put("singleWildcard", singleWildcard);
        callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
        callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
        callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
        callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
        CallHelper.executeWithParameterInjection(
            "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0tBrwGJ-Ed-FqczH3ESmRw",
            new ExecutionContext((InternalPatternContext) ctx), callParameters);
        stringBuffer.setLength(0);
      }

      //Class/implementedGenOperation.override.javajetinc
    } //for
    if (isImplementation && !genModel.isReflectiveDelegation()
        && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
      stringBuffer.append(TEXT_71);
      if (genModel.useGenerics()) {
        for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
          if (genFeature.isUncheckedCast(genClass)) {
            stringBuffer.append(TEXT_72);
            break;
          }
        }
      }
      if (genModel.useClassOverrideAnnotation()) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_73);
      stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
      stringBuffer.append(TEXT_74);
      stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
      stringBuffer.append(TEXT_75);
      stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
      stringBuffer.append(TEXT_76);
      stringBuffer.append(negativeOffsetCorrection);
      stringBuffer.append(TEXT_77);
      for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
        stringBuffer.append(TEXT_78);
        stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
        stringBuffer.append(TEXT_79);
        if (genFeature.isListType()) {
          String cast = "(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList")
              + (!genModel.useGenerics() ? ")"
                  : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)("
                      + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
          if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
            stringBuffer.append(TEXT_80);
            stringBuffer.append(cast);
            stringBuffer.append(TEXT_81);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
            stringBuffer.append(TEXT_82);
            stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
            stringBuffer.append(TEXT_83);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_84);
          } else {
            stringBuffer.append(TEXT_85);
            stringBuffer.append(cast);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_86);
          }
        } else if (genFeature.isContainer()) {
          stringBuffer.append(TEXT_87);
          if (genFeature.isBasicSet()) {
            stringBuffer.append(TEXT_88);
            stringBuffer.append(genFeature.getAccessorName());
            stringBuffer.append(TEXT_89);
            stringBuffer.append(genFeature.getImportedType(genClass));
            stringBuffer.append(TEXT_90);
          } else {
            stringBuffer.append(TEXT_91);
            stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
            stringBuffer.append(positiveOffsetCorrection);
            stringBuffer.append(TEXT_92);
          }
        } else {
          if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
            stringBuffer.append(TEXT_93);
            stringBuffer.append(genFeature.getImportedType(genClass));
            stringBuffer.append(TEXT_60);
            stringBuffer.append(genFeature.getSafeName());
            stringBuffer.append(TEXT_94);
            stringBuffer.append(genFeature.getImportedType(genClass));
            stringBuffer.append(TEXT_95);
            stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
            stringBuffer.append(positiveOffsetCorrection);
            stringBuffer.append(TEXT_96);
          } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
            stringBuffer.append(TEXT_93);
            stringBuffer.append(genFeature.getImportedType(genClass));
            stringBuffer.append(TEXT_60);
            stringBuffer.append(genFeature.getSafeName());
            stringBuffer.append(TEXT_50);
            if (genFeature.isResolveProxies()) {
              stringBuffer.append(TEXT_97);
              stringBuffer.append(genFeature.getAccessorName());
            } else {
              stringBuffer.append(genFeature.getGetAccessor());
            }
            stringBuffer.append(TEXT_98);
          }
          stringBuffer.append(TEXT_99);
          stringBuffer.append(genFeature.getSafeName());
          stringBuffer.append(TEXT_100);
          if (genFeature.isEffectiveContains()) {
            stringBuffer.append(TEXT_101);
            stringBuffer.append(genFeature.getAsInternalEObject(genFeature.getSafeName(), true));
            stringBuffer.append(TEXT_102);
            stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
            stringBuffer.append(negativeOffsetCorrection);
            stringBuffer.append(TEXT_103);
          } else {
            GenFeature reverseFeature = genFeature.getReverse();
            GenClass targetClass = reverseFeature.getGenClass();
            String reverseOffsetCorrection = targetClass.hasOffsetCorrection()
                ? " + " + genClass.getOffsetCorrectionField(genFeature)
                : "";
            stringBuffer.append(TEXT_101);
            stringBuffer.append(genFeature.getAsInternalEObject(genFeature.getSafeName(), true));
            stringBuffer.append(TEXT_104);
            stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
            stringBuffer.append(reverseOffsetCorrection);
            stringBuffer.append(TEXT_105);
            stringBuffer.append(targetClass.getRawImportedInterfaceName());
            stringBuffer.append(TEXT_106);
          }
          stringBuffer.append(TEXT_88);
          stringBuffer.append(genFeature.getAccessorName());
          stringBuffer.append(TEXT_89);
          stringBuffer.append(genFeature.getImportedType(genClass));
          stringBuffer.append(TEXT_90);
        }
      }
      if (forceDefaultCase) {
        stringBuffer.append(TEXT_107);
      } else {
        stringBuffer.append(TEXT_108);
      }
      if (genModel.isMinimalReflectiveMethods()) {
        stringBuffer.append(TEXT_4);
        stringBuffer.append(indentDefaultCase);
        stringBuffer.append(TEXT_109);
      } else {
        stringBuffer.append(TEXT_4);
        stringBuffer.append(indentDefaultCase);
        stringBuffer.append(TEXT_110);
      }
      if (forceDefaultCase) {
        stringBuffer.append(TEXT_108);
      }
      stringBuffer.append(TEXT_111);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()
        && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
      stringBuffer.append(TEXT_71);
      if (genModel.useClassOverrideAnnotation()) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_73);
      stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
      stringBuffer.append(TEXT_112);
      stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
      stringBuffer.append(TEXT_75);
      stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
      stringBuffer.append(TEXT_76);
      stringBuffer.append(negativeOffsetCorrection);
      stringBuffer.append(TEXT_77);
      for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
        stringBuffer.append(TEXT_78);
        stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
        stringBuffer.append(TEXT_79);
        if (genFeature.isListType()) {
          if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
            stringBuffer.append(TEXT_80);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
            stringBuffer.append(singleWildcard);
            stringBuffer.append(TEXT_113);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
            stringBuffer.append(TEXT_82);
            stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
            stringBuffer.append(TEXT_83);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_114);
          } else if (genFeature.isWrappedFeatureMapType()) {
            stringBuffer.append(TEXT_80);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
            stringBuffer.append(singleWildcard);
            stringBuffer.append(TEXT_113);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
            stringBuffer.append(TEXT_115);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_116);
          } else {
            stringBuffer.append(TEXT_80);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
            stringBuffer.append(singleWildcard);
            stringBuffer.append(TEXT_83);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_117);
          }
        } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
          stringBuffer.append(TEXT_118);
          stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
          stringBuffer.append(positiveOffsetCorrection);
          stringBuffer.append(TEXT_92);
        } else if (genFeature.isUnsettable()) {
          stringBuffer.append(TEXT_119);
          stringBuffer.append(genFeature.getAccessorName());
          stringBuffer.append(TEXT_120);
        } else {
          stringBuffer.append(TEXT_88);
          stringBuffer.append(genFeature.getAccessorName());
          stringBuffer.append(TEXT_121);
        }
      }
      if (forceDefaultCase) {
        stringBuffer.append(TEXT_107);
      } else {
        stringBuffer.append(TEXT_108);
      }
      if (genModel.isMinimalReflectiveMethods()) {
        stringBuffer.append(TEXT_4);
        stringBuffer.append(indentDefaultCase);
        stringBuffer.append(TEXT_122);
      } else {
        stringBuffer.append(TEXT_4);
        stringBuffer.append(indentDefaultCase);
        stringBuffer.append(TEXT_123);
      }
      if (forceDefaultCase) {
        stringBuffer.append(TEXT_108);
      }
      stringBuffer.append(TEXT_111);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()
        && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
      stringBuffer.append(TEXT_71);
      if (genModel.useClassOverrideAnnotation()) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_73);
      stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
      stringBuffer.append(TEXT_124);
      stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
      stringBuffer.append(TEXT_125);
      stringBuffer.append(negativeOffsetCorrection);
      stringBuffer.append(TEXT_77);
      for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
        GenFeature reverseFeature = genFeature.getReverse();
        GenClass targetClass = reverseFeature.getGenClass();
        String reverseOffsetCorrection = targetClass.hasOffsetCorrection()
            ? " + " + genClass.getOffsetCorrectionField(genFeature)
            : "";
        stringBuffer.append(TEXT_78);
        stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
        stringBuffer.append(TEXT_126);
        stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
        stringBuffer.append(reverseOffsetCorrection);
        stringBuffer.append(TEXT_105);
        stringBuffer.append(targetClass.getRawImportedInterfaceName());
        stringBuffer.append(TEXT_106);
      }
      if (forceDefaultCase) {
        stringBuffer.append(TEXT_107);
      } else {
        stringBuffer.append(TEXT_108);
      }
      if (genModel.isMinimalReflectiveMethods()) {
        stringBuffer.append(TEXT_4);
        stringBuffer.append(indentDefaultCase);
        stringBuffer.append(TEXT_127);
      } else {
        stringBuffer.append(TEXT_4);
        stringBuffer.append(indentDefaultCase);
        stringBuffer.append(TEXT_128);
      }
      if (forceDefaultCase) {
        stringBuffer.append(TEXT_108);
      }
      stringBuffer.append(TEXT_111);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()
        && genClass.implementsAny(genClass.getEGetGenFeatures())) {
      stringBuffer.append(TEXT_71);
      if (genModel.useClassOverrideAnnotation()) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_129);
      stringBuffer.append(negativeOffsetCorrection);
      stringBuffer.append(TEXT_77);
      for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
        stringBuffer.append(TEXT_78);
        stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
        stringBuffer.append(TEXT_79);
        if (genFeature.isPrimitiveType()) {
          if (isJDK50) {
            stringBuffer.append(TEXT_130);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_98);
          } else if (genFeature.isBooleanType()) {
            stringBuffer.append(TEXT_130);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_131);
          } else {
            stringBuffer.append(TEXT_132);
            stringBuffer.append(genFeature.getObjectType(genClass));
            stringBuffer.append(TEXT_81);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_133);
          }
        } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
          stringBuffer.append(TEXT_134);
          stringBuffer.append(genFeature.getGetAccessor());
          stringBuffer.append(TEXT_135);
          stringBuffer.append(genFeature.getAccessorName());
          stringBuffer.append(TEXT_98);
        } else if (genFeature.isMapType()) {
          if (genFeature.isEffectiveSuppressEMFTypes()) {
            stringBuffer.append(TEXT_136);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
            stringBuffer.append(TEXT_82);
            stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
            stringBuffer.append(TEXT_83);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_137);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_98);
          } else {
            stringBuffer.append(TEXT_138);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_139);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_140);
          }
        } else if (genFeature.isWrappedFeatureMapType()) {
          stringBuffer.append(TEXT_136);
          stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
          stringBuffer.append(TEXT_115);
          stringBuffer.append(genFeature.getGetAccessor());
          stringBuffer.append(TEXT_141);
          stringBuffer.append(genFeature.getGetAccessor());
          stringBuffer.append(TEXT_98);
        } else if (genFeature.isFeatureMapType()) {
          stringBuffer.append(TEXT_138);
          stringBuffer.append(genFeature.getGetAccessor());
          stringBuffer.append(TEXT_142);
          stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
          stringBuffer.append(TEXT_143);
          stringBuffer.append(genFeature.getGetAccessor());
          stringBuffer.append(TEXT_144);
        } else {
          stringBuffer.append(TEXT_130);
          stringBuffer.append(genFeature.getGetAccessor());
          stringBuffer.append(TEXT_98);
        }
      }
      if (forceDefaultCase) {
        stringBuffer.append(TEXT_107);
      } else {
        stringBuffer.append(TEXT_108);
      }
      if (genModel.isMinimalReflectiveMethods()) {
        stringBuffer.append(TEXT_4);
        stringBuffer.append(indentDefaultCase);
        stringBuffer.append(TEXT_145);
      } else {
        stringBuffer.append(TEXT_4);
        stringBuffer.append(indentDefaultCase);
        stringBuffer.append(TEXT_146);
      }
      if (forceDefaultCase) {
        stringBuffer.append(TEXT_108);
      }
      stringBuffer.append(TEXT_111);
    }
    // begin-CDO : enable derived features in reflective way when using feature delagation
    if (isImplementation && genModel.isReflectiveDelegation()
        && genClass.implementsAny(genClass.getEGetGenFeatures())) {
      stringBuffer.append(TEXT_71);
      if (genModel.useClassOverrideAnnotation()) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_129);
      stringBuffer.append(negativeOffsetCorrection);
      stringBuffer.append(TEXT_77);
      for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
        if (genFeature.isDerived()) {
          stringBuffer.append(TEXT_78);
          stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
          stringBuffer.append(TEXT_79);
          if (genFeature.isPrimitiveType()) {
            if (genFeature.isBooleanType()) {
              stringBuffer.append(TEXT_130);
              stringBuffer.append(genFeature.getGetAccessor());
              stringBuffer.append(TEXT_131);
            } else {
              stringBuffer.append(TEXT_132);
              stringBuffer.append(genFeature.getObjectType(genClass));
              stringBuffer.append(TEXT_81);
              stringBuffer.append(genFeature.getGetAccessor());
              stringBuffer.append(TEXT_133);
            }
          } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
            stringBuffer.append(TEXT_130);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_98);
          } else if (genFeature.isMapType()) {
            if (genFeature.isEffectiveSuppressEMFTypes()) {
              stringBuffer.append(TEXT_136);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
              stringBuffer.append(TEXT_82);
              stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
              stringBuffer.append(TEXT_83);
              stringBuffer.append(genFeature.getGetAccessor());
              stringBuffer.append(TEXT_137);
              stringBuffer.append(genFeature.getGetAccessor());
              stringBuffer.append(TEXT_98);
            } else {
              stringBuffer.append(TEXT_138);
              stringBuffer.append(genFeature.getGetAccessor());
              stringBuffer.append(TEXT_139);
              stringBuffer.append(genFeature.getGetAccessor());
              stringBuffer.append(TEXT_140);
            }
          } else if (genFeature.isWrappedFeatureMapType()) {
            stringBuffer.append(TEXT_136);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
            stringBuffer.append(TEXT_115);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_141);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_98);
          } else if (genFeature.isFeatureMapType()) {
            stringBuffer.append(TEXT_138);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_142);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
            stringBuffer.append(TEXT_143);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_144);
          } else {
            stringBuffer.append(TEXT_130);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_98);
          }
        } // fin isDerived
      }
      stringBuffer.append(TEXT_108);
      if (genModel.isMinimalReflectiveMethods()) {
        stringBuffer.append(TEXT_147);
      } else {
        stringBuffer.append(TEXT_148);
      }
      stringBuffer.append(TEXT_111);
    }
    // end-CDO 
    if (isImplementation && !genModel.isReflectiveDelegation()
        && genClass.implementsAny(genClass.getESetGenFeatures())) {
      stringBuffer.append(TEXT_71);
      if (genModel.useGenerics()) {
        for (GenFeature genFeature : genClass.getESetGenFeatures()) {
          if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
            stringBuffer.append(TEXT_72);
            break;
          }
        }
      }
      if (genModel.useClassOverrideAnnotation()) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_149);
      stringBuffer.append(negativeOffsetCorrection);
      stringBuffer.append(TEXT_77);
      for (GenFeature genFeature : genClass.getESetGenFeatures()) {
        stringBuffer.append(TEXT_78);
        stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
        stringBuffer.append(TEXT_79);
        if (genFeature.isListType()) {
          if (genFeature.isWrappedFeatureMapType()) {
            stringBuffer.append(TEXT_150);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
            stringBuffer.append(TEXT_151);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
            stringBuffer.append(TEXT_115);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_152);
          } else if (genFeature.isFeatureMapType()) {
            stringBuffer.append(TEXT_150);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
            stringBuffer.append(TEXT_143);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_153);
          } else if (genFeature.isMapType()) {
            if (genFeature.isEffectiveSuppressEMFTypes()) {
              stringBuffer.append(TEXT_150);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
              stringBuffer.append(TEXT_154);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
              stringBuffer.append(TEXT_82);
              stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
              stringBuffer.append(TEXT_83);
              stringBuffer.append(genFeature.getGetAccessor());
              stringBuffer.append(TEXT_155);
            } else {
              stringBuffer.append(TEXT_150);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
              stringBuffer.append(TEXT_156);
              stringBuffer.append(genFeature.getGetAccessor());
              stringBuffer.append(TEXT_153);
            }
          } else {
            stringBuffer.append(TEXT_93);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_157);
            stringBuffer.append(genFeature.getGetAccessor());
            stringBuffer.append(TEXT_158);
            stringBuffer.append(genModel.getImportedName("java.util.Collection"));
            if (isJDK50) {
              stringBuffer.append(TEXT_159);
              stringBuffer.append(genFeature.getListItemType(genClass));
              stringBuffer.append(TEXT_160);
            }
            stringBuffer.append(TEXT_161);
          }
        } else if (!isJDK50 && genFeature.isPrimitiveType()) {
          stringBuffer.append(TEXT_162);
          stringBuffer.append(genFeature.getAccessorName());
          stringBuffer.append(TEXT_163);
          stringBuffer.append(genFeature.getObjectType(genClass));
          stringBuffer.append(TEXT_164);
          stringBuffer.append(genFeature.getPrimitiveValueFunction());
          stringBuffer.append(TEXT_133);
        } else {
          if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()
              || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
            if (!genFeature.getEcoreFeature().getEGenericType().getETypeArguments().isEmpty()) {
              stringBuffer.append(TEXT_165);
              stringBuffer.append(genFeature.getRawImportedBoundType());
              stringBuffer.append(TEXT_166);
            }
            stringBuffer.append(TEXT_167);
            stringBuffer.append(genFeature.getAccessorName());
            stringBuffer.append(TEXT_89);
            stringBuffer.append(genFeature.getObjectType(genClass));
            stringBuffer.append(TEXT_161);
            if (!genFeature.getEcoreFeature().getEGenericType().getETypeArguments().isEmpty()) {
              stringBuffer.append(TEXT_168);
            }
          } else {
            stringBuffer.append(TEXT_169);
            stringBuffer.append(genFeature.getAccessorName());
            stringBuffer.append(TEXT_170);
          }
        }
        stringBuffer.append(TEXT_171);
      }
      if (forceDefaultCase) {
        stringBuffer.append(TEXT_107);
      } else {
        stringBuffer.append(TEXT_108);
      }
      if (genModel.isMinimalReflectiveMethods()) {
        stringBuffer.append(TEXT_4);
        stringBuffer.append(indentDefaultCase);
        stringBuffer.append(TEXT_172);
        if (forceDefaultCase) {
          stringBuffer.append(TEXT_171);
        }
      } else {
        stringBuffer.append(TEXT_4);
        stringBuffer.append(indentDefaultCase);
        stringBuffer.append(TEXT_173);
        if (forceDefaultCase) {
          stringBuffer.append(TEXT_171);
        }
      }
      if (forceDefaultCase) {
        stringBuffer.append(TEXT_108);
      }
      stringBuffer.append(TEXT_111);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()
        && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
      stringBuffer.append(TEXT_4);
      {
        //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.eUnset.override" args="genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

        InternalPatternContext ictx = (InternalPatternContext) ctx;
        new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
        stringBuffer.setLength(0);

        final Map<String, Object> callParameters = new HashMap<String, Object>();
        callParameters.put("genClass", genClass);
        callParameters.put("genPackage", genPackage);
        callParameters.put("genModel", genModel);
        callParameters.put("isJDK50", isJDK50);
        callParameters.put("isInterface", isInterface);
        callParameters.put("isImplementation", isImplementation);
        callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
        callParameters.put("isGWT", isGWT);
        callParameters.put("forceDefaultCase", forceDefaultCase);
        callParameters.put("indentDefaultCase", indentDefaultCase);
        callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
        callParameters.put("singleWildcard", singleWildcard);
        callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
        callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
        callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
        callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
        CallHelper.executeWithParameterInjection(
            "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0uN-kGJ-Ed-FqczH3ESmRw",
            new ExecutionContext((InternalPatternContext) ctx), callParameters);
        stringBuffer.setLength(0);
      }

      //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation()
        && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
      stringBuffer.append(TEXT_4);
      {
        //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.eIsSet.override" args="genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

        InternalPatternContext ictx = (InternalPatternContext) ctx;
        new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
        stringBuffer.setLength(0);

        final Map<String, Object> callParameters = new HashMap<String, Object>();
        callParameters.put("genClass", genClass);
        callParameters.put("genPackage", genPackage);
        callParameters.put("genModel", genModel);
        callParameters.put("isJDK50", isJDK50);
        callParameters.put("isInterface", isInterface);
        callParameters.put("isImplementation", isImplementation);
        callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
        callParameters.put("isGWT", isGWT);
        callParameters.put("forceDefaultCase", forceDefaultCase);
        callParameters.put("indentDefaultCase", indentDefaultCase);
        callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
        callParameters.put("singleWildcard", singleWildcard);
        callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
        callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
        callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
        callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
        CallHelper.executeWithParameterInjection(
            "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0uXvkGJ-Ed-FqczH3ESmRw",
            new ExecutionContext((InternalPatternContext) ctx), callParameters);
        stringBuffer.setLength(0);
      }

      //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty()
        || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
      if (!genClass.getMixinGenFeatures().isEmpty()) {
        stringBuffer.append(TEXT_71);
        if (genModel.useClassOverrideAnnotation()) {
          stringBuffer.append(TEXT_67);
        }
        stringBuffer.append(TEXT_174);
        stringBuffer.append(singleWildcard);
        stringBuffer.append(TEXT_175);
        for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
          stringBuffer.append(TEXT_176);
          stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
          stringBuffer.append(TEXT_177);
          stringBuffer.append(negativeOffsetCorrection);
          stringBuffer.append(TEXT_178);
          for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
            stringBuffer.append(TEXT_179);
            stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
            stringBuffer.append(TEXT_180);
            stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
            stringBuffer.append(TEXT_3);
          }
          stringBuffer.append(TEXT_181);
        }
        stringBuffer.append(TEXT_182);
      }
      stringBuffer.append(TEXT_183);
      if (genModel.useClassOverrideAnnotation()) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_184);
      stringBuffer.append(singleWildcard);
      stringBuffer.append(TEXT_175);
      for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
        stringBuffer.append(TEXT_176);
        stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
        stringBuffer.append(TEXT_185);
        for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
          stringBuffer.append(TEXT_179);
          stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
          stringBuffer.append(TEXT_180);
          stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
          stringBuffer.append(positiveOffsetCorrection);
          stringBuffer.append(TEXT_3);
        }
        stringBuffer.append(TEXT_181);
      }
      if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
        stringBuffer.append(TEXT_176);
        stringBuffer.append(genClass.getRawImportedInterfaceName());
        stringBuffer.append(TEXT_186);
        stringBuffer.append(negativeOffsetCorrection);
        stringBuffer.append(TEXT_178);
        for (GenFeature genFeature : genClass.getGenFeatures()) {
          stringBuffer.append(TEXT_179);
          stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
          stringBuffer.append(TEXT_180);
          stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
          stringBuffer.append(positiveOffsetCorrection);
          stringBuffer.append(TEXT_3);
        }
        stringBuffer.append(TEXT_181);
      }
      stringBuffer.append(TEXT_187);
    }
    if (genModel.isOperationReflection() && isImplementation
        && (!genClass.getMixinGenOperations().isEmpty() || !genClass
            .getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations())
            .isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
      stringBuffer.append(TEXT_71);
      if (genModel.useClassOverrideAnnotation()) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_188);
      stringBuffer.append(singleWildcard);
      stringBuffer.append(TEXT_175);
      for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) {
        List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations();
        List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
        if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
          stringBuffer.append(TEXT_176);
          stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
          stringBuffer.append(TEXT_189);
          for (GenOperation genOperation : extendedImplementedGenOperations) {
            GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
            if (implementedGenOperations.contains(overrideGenOperation)) {
              stringBuffer.append(TEXT_179);
              stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
              stringBuffer.append(TEXT_180);
              stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
              stringBuffer.append(positiveOperationOffsetCorrection);
              stringBuffer.append(TEXT_3);
            }
          }
          stringBuffer.append(TEXT_190);
        }
      }
      for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
        stringBuffer.append(TEXT_176);
        stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
        stringBuffer.append(TEXT_189);
        for (GenOperation genOperation : mixinGenClass.getGenOperations()) {
          GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
          stringBuffer.append(TEXT_179);
          stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
          stringBuffer.append(TEXT_180);
          stringBuffer.append(
              genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
          stringBuffer.append(positiveOperationOffsetCorrection);
          stringBuffer.append(TEXT_3);
        }
        stringBuffer.append(TEXT_181);
      }
      if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
        stringBuffer.append(TEXT_176);
        stringBuffer.append(genClass.getRawImportedInterfaceName());
        stringBuffer.append(TEXT_191);
        stringBuffer.append(negativeOperationOffsetCorrection);
        stringBuffer.append(TEXT_178);
        for (GenOperation genOperation : genClass.getGenOperations()) {
          stringBuffer.append(TEXT_179);
          stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
          stringBuffer.append(TEXT_180);
          stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
          stringBuffer.append(positiveOperationOffsetCorrection);
          stringBuffer.append(TEXT_3);
        }
        stringBuffer.append(TEXT_181);
      }
      stringBuffer.append(TEXT_192);
    }
    if (isImplementation && genModel.isVirtualDelegation()) {
      String eVirtualValuesField = genClass.getEVirtualValuesField();
      if (eVirtualValuesField != null) {
        stringBuffer.append(TEXT_71);
        if (genModel.useClassOverrideAnnotation()) {
          stringBuffer.append(TEXT_67);
        }
        stringBuffer.append(TEXT_193);
        stringBuffer.append(eVirtualValuesField);
        stringBuffer.append(TEXT_194);
        if (genModel.useClassOverrideAnnotation()) {
          stringBuffer.append(TEXT_67);
        }
        stringBuffer.append(TEXT_195);
        stringBuffer.append(eVirtualValuesField);
        stringBuffer.append(TEXT_196);
      }
      {
        List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
        if (!eVirtualIndexBitFields.isEmpty()) {
          List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
          stringBuffer.append(TEXT_71);
          if (genModel.useClassOverrideAnnotation()) {
            stringBuffer.append(TEXT_67);
          }
          stringBuffer.append(TEXT_197);
          for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
            stringBuffer.append(TEXT_78);
            stringBuffer.append(i);
            stringBuffer.append(TEXT_198);
            stringBuffer.append(allEVirtualIndexBitFields.get(i));
            stringBuffer.append(TEXT_3);
          }
          stringBuffer.append(TEXT_199);
          if (genModel.useClassOverrideAnnotation()) {
            stringBuffer.append(TEXT_67);
          }
          stringBuffer.append(TEXT_200);
          for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
            stringBuffer.append(TEXT_78);
            stringBuffer.append(i);
            stringBuffer.append(TEXT_201);
            stringBuffer.append(allEVirtualIndexBitFields.get(i));
            stringBuffer.append(TEXT_202);
          }
          stringBuffer.append(TEXT_203);
        }
      }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
      stringBuffer.append(TEXT_71);
      if (genModel.useClassOverrideAnnotation()) {
        stringBuffer.append(TEXT_67);
      }
      if (genModel.useGenerics()) {
        boolean isUnchecked = false;
        boolean isRaw = false;
        LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods()
            ? genClass.getImplementedGenOperations()
            : genClass.getAllGenOperations())) {
          for (GenParameter genParameter : genOperation.getGenParameters()) {
            if (genParameter.isUncheckedCast()) {
              if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) {
                isUnchecked = true;
              }
              if (genParameter.usesOperationTypeParameters()
                  && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) {
                isRaw = true;
                break LOOP;
              }
            }
          }
        }
        if (isUnchecked) {
          stringBuffer.append(TEXT_204);
          if (!isRaw) {
            stringBuffer.append(TEXT_205);
          } else {
            stringBuffer.append(TEXT_206);
          }
          stringBuffer.append(TEXT_83);
        }
      }
      stringBuffer.append(TEXT_207);
      stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
      stringBuffer.append(singleWildcard);
      stringBuffer.append(TEXT_208);
      stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException"
          : "java.lang.reflect.InvocationTargetException"));
      stringBuffer.append(TEXT_209);
      stringBuffer.append(negativeOperationOffsetCorrection);
      stringBuffer.append(TEXT_77);
      for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations()
          : genClass.getAllGenOperations())) {
        List<GenParameter> genParameters = genOperation.getGenParameters();
        int size = genParameters.size();
        boolean hasCheckedException = genOperation.hasCheckedException();
        String indent = hasCheckedException ? "\t" : "";
        GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
        stringBuffer.append(TEXT_78);
        stringBuffer.append(
            genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
        stringBuffer.append(TEXT_79);
        if (hasCheckedException) {
          stringBuffer.append(TEXT_210);
          /*}*/}
        if (genOperation.isVoid()) {
          stringBuffer.append(TEXT_93);
          stringBuffer.append(indent);
          stringBuffer.append(genOperation.getName());
          stringBuffer.append(TEXT_81);
          for (int i = 0; i < size; i++) {
            GenParameter genParameter = genParameters.get(i);
            if (!isJDK50 && genParameter.isPrimitiveType()) {
              stringBuffer.append(TEXT_81);
            }
            if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()
                || !genParameter.usesOperationTypeParameters()
                    && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
              stringBuffer.append(TEXT_81);
              stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType()
                  : genParameter.getObjectType(genClass));
              stringBuffer.append(TEXT_83);
            }
            stringBuffer.append(TEXT_211);
            stringBuffer.append(i);
            stringBuffer.append(TEXT_83);
            if (!isJDK50 && genParameter.isPrimitiveType()) {
              stringBuffer.append(TEXT_212);
              stringBuffer.append(genParameter.getPrimitiveValueFunction());
              stringBuffer.append(TEXT_40);
            }
            if (i < (size - 1)) {
              stringBuffer.append(TEXT_105);
            }
          }
          stringBuffer.append(TEXT_213);
          stringBuffer.append(indent);
          stringBuffer.append(TEXT_214);
        } else {
          stringBuffer.append(TEXT_93);
          stringBuffer.append(indent);
          stringBuffer.append(TEXT_215);
          if (!isJDK50 && genOperation.isPrimitiveType()) {
            stringBuffer.append(TEXT_216);
            stringBuffer.append(genOperation.getObjectType(genClass));
            stringBuffer.append(TEXT_81);
          }
          stringBuffer.append(genOperation.getName());
          stringBuffer.append(TEXT_81);
          for (int i = 0; i < size; i++) {
            GenParameter genParameter = genParameters.get(i);
            if (!isJDK50 && genParameter.isPrimitiveType()) {
              stringBuffer.append(TEXT_81);
            }
            if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()
                || !genParameter.usesOperationTypeParameters()
                    && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
              stringBuffer.append(TEXT_81);
              stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType()
                  : genParameter.getObjectType(genClass));
              stringBuffer.append(TEXT_83);
            }
            stringBuffer.append(TEXT_211);
            stringBuffer.append(i);
            stringBuffer.append(TEXT_83);
            if (!isJDK50 && genParameter.isPrimitiveType()) {
              stringBuffer.append(TEXT_212);
              stringBuffer.append(genParameter.getPrimitiveValueFunction());
              stringBuffer.append(TEXT_40);
            }
            if (i < (size - 1)) {
              stringBuffer.append(TEXT_105);
            }
          }
          stringBuffer.append(TEXT_83);
          if (!isJDK50 && genOperation.isPrimitiveType()) {
            stringBuffer.append(TEXT_83);
          }
          stringBuffer.append(TEXT_3);
        }
        if (hasCheckedException) {/*{*/
          stringBuffer.append(TEXT_217);
          stringBuffer.append(genModel.getImportedName("java.lang.Throwable"));
          stringBuffer.append(TEXT_218);
          stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException"
              : "java.lang.reflect.InvocationTargetException"));
          stringBuffer.append(TEXT_219);
        }
      }
      if (forceDefaultCase) {
        stringBuffer.append(TEXT_107);
      } else {
        stringBuffer.append(TEXT_108);
      }
      if (genModel.isMinimalReflectiveMethods()) {
        stringBuffer.append(TEXT_4);
        stringBuffer.append(indentDefaultCase);
        stringBuffer.append(TEXT_220);
      } else {
        stringBuffer.append(TEXT_4);
        stringBuffer.append(indentDefaultCase);
        stringBuffer.append(TEXT_221);
      }
      if (forceDefaultCase) {
        stringBuffer.append(TEXT_108);
      }
      stringBuffer.append(TEXT_111);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation()
        && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
      stringBuffer.append(TEXT_71);
      if (genModel.useClassOverrideAnnotation()) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_222);
      stringBuffer.append(genModel.useGenerics() ? "StringBuilder" : "StringBuffer");
      stringBuffer.append(TEXT_223);
      stringBuffer.append(genModel.useGenerics() ? "StringBuilder" : "StringBuffer");
      stringBuffer.append(TEXT_224);
      {
        boolean first = true;
        for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
          if (!genFeature.getName().equals("description")) {
            if (first) {
              first = false;
              stringBuffer.append(TEXT_225);
              stringBuffer.append(genFeature.getName());
              stringBuffer.append(TEXT_226);
              stringBuffer.append(genModel.getNonNLS());
            } else {
              stringBuffer.append(TEXT_227);
              stringBuffer.append(genFeature.getName());
              stringBuffer.append(TEXT_226);
              stringBuffer.append(genModel.getNonNLS());
            }
            if (genFeature.isUnsettable() && !genFeature.isListType()) {
              if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
                stringBuffer.append(TEXT_228);
                stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
                stringBuffer.append(positiveOffsetCorrection);
                stringBuffer.append(TEXT_229);
                stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
                stringBuffer.append(positiveOffsetCorrection);
                stringBuffer.append(TEXT_230);
                stringBuffer.append(genModel.getNonNLS());
              } else {
                if (genClass.isFlag(genFeature)) {
                  if (genFeature.isBooleanType()) {
                    stringBuffer.append(TEXT_231);
                    if (genClass.isESetFlag(genFeature)) {
                      stringBuffer.append(TEXT_81);
                      stringBuffer.append(genClass.getESetFlagsField(genFeature));
                      stringBuffer.append(TEXT_232);
                      stringBuffer.append(genFeature.getUpperName());
                      stringBuffer.append(TEXT_233);
                    } else {
                      stringBuffer.append(genFeature.getUncapName());
                      stringBuffer.append(TEXT_234);
                    }
                    stringBuffer.append(TEXT_235);
                    stringBuffer.append(genClass.getFlagsField(genFeature));
                    stringBuffer.append(TEXT_232);
                    stringBuffer.append(genFeature.getUpperName());
                    stringBuffer.append(TEXT_236);
                    stringBuffer.append(genModel.getNonNLS());
                  } else {
                    stringBuffer.append(TEXT_231);
                    if (genClass.isESetFlag(genFeature)) {
                      stringBuffer.append(TEXT_81);
                      stringBuffer.append(genClass.getESetFlagsField(genFeature));
                      stringBuffer.append(TEXT_232);
                      stringBuffer.append(genFeature.getUpperName());
                      stringBuffer.append(TEXT_233);
                    } else {
                      stringBuffer.append(genFeature.getUncapName());
                      stringBuffer.append(TEXT_234);
                    }
                    stringBuffer.append(TEXT_237);
                    stringBuffer.append(genFeature.getUpperName());
                    stringBuffer.append(TEXT_238);
                    stringBuffer.append(genClass.getFlagsField(genFeature));
                    stringBuffer.append(TEXT_232);
                    stringBuffer.append(genFeature.getUpperName());
                    stringBuffer.append(TEXT_239);
                    stringBuffer.append(genFeature.getUpperName());
                    stringBuffer.append(TEXT_240);
                    stringBuffer.append(genModel.getNonNLS());
                  }
                } else {
                  stringBuffer.append(TEXT_231);
                  if (genClass.isESetFlag(genFeature)) {
                    stringBuffer.append(TEXT_81);
                    stringBuffer.append(genClass.getESetFlagsField(genFeature));
                    stringBuffer.append(TEXT_232);
                    stringBuffer.append(genFeature.getUpperName());
                    stringBuffer.append(TEXT_233);
                  } else {
                    stringBuffer.append(genFeature.getUncapName());
                    stringBuffer.append(TEXT_234);
                  }
                  stringBuffer.append(TEXT_237);
                  stringBuffer.append(genFeature.getSafeName());
                  stringBuffer.append(TEXT_241);
                  stringBuffer.append(genModel.getNonNLS());
                }
              }
            } else {
              if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
                stringBuffer.append(TEXT_242);
                stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
                stringBuffer.append(positiveOffsetCorrection);
                if (!genFeature.isListType() && !genFeature.isReferenceType()) {
                  stringBuffer.append(TEXT_105);
                  stringBuffer.append(genFeature.getEDefault());
                }
                stringBuffer.append(TEXT_243);
              } else {
                if (genClass.isFlag(genFeature)) {
                  if (genFeature.isBooleanType()) {
                    stringBuffer.append(TEXT_244);
                    stringBuffer.append(genClass.getFlagsField(genFeature));
                    stringBuffer.append(TEXT_232);
                    stringBuffer.append(genFeature.getUpperName());
                    stringBuffer.append(TEXT_245);
                  } else {
                    stringBuffer.append(TEXT_246);
                    stringBuffer.append(genFeature.getUpperName());
                    stringBuffer.append(TEXT_238);
                    stringBuffer.append(genClass.getFlagsField(genFeature));
                    stringBuffer.append(TEXT_232);
                    stringBuffer.append(genFeature.getUpperName());
                    stringBuffer.append(TEXT_239);
                    stringBuffer.append(genFeature.getUpperName());
                    stringBuffer.append(TEXT_247);
                  }
                } else {
                  stringBuffer.append(TEXT_246);
                  stringBuffer.append(genFeature.getSafeName());
                  stringBuffer.append(TEXT_96);
                }
              }
            }
          }
        }
      }
      stringBuffer.append(TEXT_248);
    }
    if (isImplementation && genClass.isMapEntry()) {
      GenFeature keyFeature = genClass.getMapEntryKeyFeature();
      GenFeature valueFeature = genClass.getMapEntryValueFeature();
      String objectType = genModel.getImportedName("java.lang.Object");
      String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
      String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
      String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap")
          + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
      stringBuffer.append(TEXT_71);
      if (isGWT) {
        stringBuffer.append(TEXT_30);
        stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
      }
      stringBuffer.append(TEXT_249);
      if (useInterfaceOverrideAnnotation) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_250);
      stringBuffer.append(objectType);
      stringBuffer.append(TEXT_251);
      if (useInterfaceOverrideAnnotation) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_252);
      if (useInterfaceOverrideAnnotation) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_73);
      stringBuffer.append(keyType);
      stringBuffer.append(TEXT_253);
      if (!isJDK50 && keyFeature.isPrimitiveType()) {
        stringBuffer.append(TEXT_254);
        stringBuffer.append(keyFeature.getObjectType(genClass));
        stringBuffer.append(TEXT_255);
      } else {
        stringBuffer.append(TEXT_256);
      }
      stringBuffer.append(TEXT_66);
      if (useInterfaceOverrideAnnotation) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_257);
      stringBuffer.append(keyType);
      stringBuffer.append(TEXT_258);
      if (keyFeature.isListType()) {
        stringBuffer.append(TEXT_259);
        if (!genModel.useGenerics()) {
          stringBuffer.append(TEXT_81);
          stringBuffer.append(genModel.getImportedName("java.util.Collection"));
          stringBuffer.append(TEXT_83);
        }
        stringBuffer.append(TEXT_260);
      } else if (isJDK50) {
        stringBuffer.append(TEXT_261);
      } else if (keyFeature.isPrimitiveType()) {
        stringBuffer.append(TEXT_262);
        stringBuffer.append(keyFeature.getObjectType(genClass));
        stringBuffer.append(TEXT_263);
        stringBuffer.append(keyFeature.getPrimitiveValueFunction());
        stringBuffer.append(TEXT_133);
      } else {
        stringBuffer.append(TEXT_264);
        stringBuffer.append(keyFeature.getImportedType(genClass));
        stringBuffer.append(TEXT_265);
      }
      stringBuffer.append(TEXT_66);
      if (useInterfaceOverrideAnnotation) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_73);
      stringBuffer.append(valueType);
      stringBuffer.append(TEXT_266);
      if (!isJDK50 && valueFeature.isPrimitiveType()) {
        stringBuffer.append(TEXT_254);
        stringBuffer.append(valueFeature.getObjectType(genClass));
        stringBuffer.append(TEXT_267);
      } else {
        stringBuffer.append(TEXT_268);
      }
      stringBuffer.append(TEXT_66);
      if (useInterfaceOverrideAnnotation) {
        stringBuffer.append(TEXT_67);
      }
      stringBuffer.append(TEXT_73);
      stringBuffer.append(valueType);
      stringBuffer.append(TEXT_269);
      stringBuffer.append(valueType);
      stringBuffer.append(TEXT_270);
      stringBuffer.append(valueType);
      stringBuffer.append(TEXT_271);
      if (valueFeature.isListType()) {
        stringBuffer.append(TEXT_272);
        if (!genModel.useGenerics()) {
          stringBuffer.append(TEXT_81);
          stringBuffer.append(genModel.getImportedName("java.util.Collection"));
          stringBuffer.append(TEXT_83);
        }
        stringBuffer.append(TEXT_273);
      } else if (isJDK50) {
        stringBuffer.append(TEXT_274);
      } else if (valueFeature.isPrimitiveType()) {
        stringBuffer.append(TEXT_275);
        stringBuffer.append(valueFeature.getObjectType(genClass));
        stringBuffer.append(TEXT_276);
        stringBuffer.append(valueFeature.getPrimitiveValueFunction());
        stringBuffer.append(TEXT_133);
      } else {
        stringBuffer.append(TEXT_277);
        stringBuffer.append(valueFeature.getImportedType(genClass));
        stringBuffer.append(TEXT_278);
      }
      stringBuffer.append(TEXT_279);
      if (genModel.useGenerics()) {
        stringBuffer.append(TEXT_72);
      }
      stringBuffer.append(TEXT_73);
      stringBuffer.append(eMapType);
      stringBuffer.append(TEXT_280);
      stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
      stringBuffer.append(TEXT_281);
      stringBuffer.append(eMapType);
      stringBuffer.append(TEXT_282);
    }
    stringBuffer.append(TEXT_4);
    {
      //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.insert" args="genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

      InternalPatternContext ictx = (InternalPatternContext) ctx;
      new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
      stringBuffer.setLength(0);

      final Map<String, Object> callParameters = new HashMap<String, Object>();
      callParameters.put("genClass", genClass);
      callParameters.put("genPackage", genPackage);
      callParameters.put("genModel", genModel);
      callParameters.put("isJDK50", isJDK50);
      callParameters.put("isInterface", isInterface);
      callParameters.put("isImplementation", isImplementation);
      callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
      callParameters.put("isGWT", isGWT);
      callParameters.put("forceDefaultCase", forceDefaultCase);
      callParameters.put("indentDefaultCase", indentDefaultCase);
      callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
      callParameters.put("singleWildcard", singleWildcard);
      callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
      callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
      callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
      callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
      CallHelper.executeWithParameterInjection(
          "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0ug5gGJ-Ed-FqczH3ESmRw",
          new ExecutionContext((InternalPatternContext) ctx), callParameters);
      stringBuffer.setLength(0);
    }

    stringBuffer.append(TEXT_283);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
  }
}