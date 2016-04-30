package com.github.alexthe666.archipelago.classtransformer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ArchipelagoClassTransformer implements IClassTransformer{
	@Override
	public byte[] transform(String name, String transformedName, byte[] classBytes) {
		{
			boolean obf;
			ClassNode classNode = new ClassNode();
			if ((obf = "bfk".equals(name)) || "net.minecraft.client.renderer.EntityRenderer".equals(name)){
				ClassReader classReader = new ClassReader(classBytes);
				classReader.accept(classNode, 0);
				String setupCameraTransformName = obf ? "a" : "setupFog";
				String setupCameraTransformDesc = "(IF)V";
				for(MethodNode methodNode : classNode.methods){
					if(setupCameraTransformName.equals(methodNode.name) && setupCameraTransformDesc.equals(methodNode.desc)){
						InsnList insnList = methodNode.instructions;
						InsnList inject = new InsnList();
						List<AbstractInsnNode> nodesInLine = new ArrayList<>();
						for (AbstractInsnNode node : methodNode.instructions.toArray()) {
							if (node instanceof LineNumberNode) {
								boolean target = false;
								for (AbstractInsnNode lineNode : nodesInLine) {
									if(lineNode instanceof MethodInsnNode){
										MethodInsnNode method_0 = (MethodInsnNode)lineNode;
										System.out.println(method_0.name);
										if((method_0.name.equals("getRespirationModifier") && method_0.owner.equals("net/minecraft/enchantment/EnchantmentHelper"))|| (method_0.name.equals("a") && method_0.owner.equals("ack"))){
											System.out.println(method_0.name);
											target = true;
										}
									}
								}
								if (target) {
									for (AbstractInsnNode lineNode : nodesInLine) {
										inject.remove(lineNode);
									} 
								}
								nodesInLine.clear();
							}
							inject.add(node);
							nodesInLine.add(node);
						}
						methodNode.instructions.clear();
						inject.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "com/github/alexthe666/archipelago/classtransformer/ArchipelagoHooks", "renderUnderwaterFog", "()V", false));
						methodNode.instructions.add(inject);
					}
					break;
				}
				ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
				classNode.accept(classWriter);
				saveBytecode(name, classWriter);
				return classWriter.toByteArray();
			}
		}
		return classBytes;
	}

	private void saveBytecode(String name, ClassWriter cw) {
		try {
			File debugDir = new File("archipelago/debug/");
			debugDir.mkdirs();
			FileOutputStream out = new FileOutputStream(new File(debugDir, name + ".class"));
			out.write(cw.toByteArray());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
